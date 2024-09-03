package io.github.choosegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen implements Screen {

    private final Game game;
    private SpriteBatch batch;

    public LoadingScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        // Load resources, display a loading animation, etc.
    }

    @Override
    public void render(float delta) {
        // Perform loading logic
        // If loading is done, switch to the main menu or game screen
        game.setScreen(new CardGameScreen());  // Switch to the CardGameScreen after loading
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
