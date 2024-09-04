package io.github.choosegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.choosegame.models.Card;
import io.github.choosegame.service.CardGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardGameScreen extends InputAdapter implements Screen {
    final CardGame game;

    private final CardGenerator cardGenerator = new CardGenerator();
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private List<Sprite> sprites;
    private OrthographicCamera camera;
    private boolean zoomed;
    public Map<String, Card> cardsInHand;
    private Map<Sprite, Float> originalYPositions;  // Store original Y positions of cards

    public CardGameScreen (final CardGame game){
        this.game = game;
    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera(2000,2400);
        batch = new SpriteBatch();
        sprites = new ArrayList<>();
        cardsInHand = cardGenerator.addPlayerCardsToSprites(sprites);

        originalYPositions = new HashMap<>();
        for (Sprite sprite : sprites) {
            originalYPositions.put(sprite, sprite.getY());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        cardGenerator.generateCard(0,0);
        cardGenerator.renderPlayerCardsSprite(sprites, batch);
        batch.end();
        renderBorder();
        handleHover();
    }

    public void renderBorder() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        int lineWidth = 14;
        Gdx.gl20.glLineWidth(lineWidth);

        shapeRenderer.setColor(Color.LIGHT_GRAY);
        shapeRenderer.line(0, 0, w, 0);
        shapeRenderer.line(0, h, w, h);
        shapeRenderer.line(0, 0, 0, h);
        shapeRenderer.line(w, 0, w, h);
        shapeRenderer.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                if (zoomed) {
                    camera.viewportWidth = 2000;
                    camera.viewportHeight = 1600;
                    zoomed = false;
                } else {
                    camera.viewportWidth = 2500;
                    camera.viewportHeight = 1800;
                    zoomed = true;
                }
                camera.update();
                break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = sprites.get(i);
            System.out.println("Sprite " + i + " position: " + sprite.getX() + ", " + sprite.getY());
        }

        // Convert screen coordinates to world coordinates
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        float touchX = worldCoordinates.x;
        float touchY = worldCoordinates.y;

        // Check each sprite to see if it contains the touch coordinates
        for (int i = 0; i < sprites.size(); i++) {
            Sprite sprite = sprites.get(i);
            String cardName = cardsInHand.keySet().toArray(new String[0])[i];

            System.out.println("Sprite " + i + ": " + sprite.getX() + ", " + sprite.getY());
            System.out.println("Card " + i + ": " + cardName);

            if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                // This sprite was clicked
                System.out.println("Clicked on sprite at: " + sprite.getX() + ", " + sprite.getY());
                System.out.println("clicked on card: " + cardName);

                return true;
            }
        }
        return false;
    }

    private void handleHover() {
        Vector3 worldCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        float mouseX = worldCoordinates.x;
        float mouseY = worldCoordinates.y;
        for (Sprite sprite : sprites) {
            if (sprite.getBoundingRectangle().contains(mouseX, mouseY)) {
                float hoverOffset = 20f;
                sprite.setY(originalYPositions.get(sprite) + hoverOffset);
            } else {
                sprite.setY(originalYPositions.get(sprite));
            }
        }
    }


    @Override
    public void resize(int width, int height) {
        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
