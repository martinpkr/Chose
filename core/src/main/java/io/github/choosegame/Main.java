package io.github.choosegame;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.choosegame.service.CardGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ApplicationListener} implementation shared by all platforms.
 */
public class Main extends InputAdapter implements ApplicationListener {
    private final CardGenerator cardGenerator = new CardGenerator();
    ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private List<Sprite> sprites;
    private OrthographicCamera camera;
    boolean zoomed;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                if (zoomed) {
                    camera.viewportWidth = 2100;
                    camera.viewportHeight = 1200;
                    zoomed = false;
                } else {
                    camera.viewportWidth = 2500;
                    camera.viewportHeight = 1600;
                    zoomed = true;
                }
                camera.update();

                break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("We are at x:" + screenX + " and y:" + screenY + "!");

        return true;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera(2500, 1600);
        batch = new SpriteBatch();
        sprites = new ArrayList<>();
        cardGenerator.addPlayerCardsToSprites(sprites);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        cardGenerator.renderPlayerCardsSprite(sprites, batch);
        batch.end();
        renderBorder();
    }

    public void renderBorder(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        int lineWidth = 14;
        Gdx.gl20.glLineWidth(lineWidth);

        shapeRenderer.setColor(Color.GOLDENROD);
        shapeRenderer.line(0, 0, w, 0);
        shapeRenderer.line(0, h, w, h);
        shapeRenderer.line(0, 0, 0, h);
        shapeRenderer.line(w, 0, w, h);
        shapeRenderer.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        batch.setProjectionMatrix(camera.combined);
    }
}
