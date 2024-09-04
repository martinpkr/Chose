package io.github.choosegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final CardGame game;
    public SpriteBatch batch;
    OrthographicCamera camera;

    public MainMenuScreen(final CardGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 2000, 2000);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        String welcomeText = "Welcome to Chose!";
        String tapToBeginText = "Tap anywhere to begin!";
        GlyphLayout layout = new GlyphLayout();
        layout.setText(game.font, welcomeText);

        game.font = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear.Linear, Texture.TextureFilter.Linear);
        game.font.getData().setScale(1.5f);
        drawCenteredText(welcomeText, camera, layout, false);
        drawCenteredText(tapToBeginText, camera, layout, true);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new CardGameScreen(game));
        }
    }

    protected void drawCenteredText(String text, OrthographicCamera camera, GlyphLayout layout, boolean under) {
        float screenWidth = camera.viewportWidth;
        float screenHeight = camera.viewportHeight;
        float textWidth = layout.width;
        float x = (screenWidth - textWidth) / 2;
        float y = screenHeight / 2 + layout.height;
        if (under) {
            y = screenHeight / 2 - layout.height;
        }
        game.font.draw(game.batch, text, x, y);
    }

    @Override
    public void resize(int width, int height) {
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
    }
}
