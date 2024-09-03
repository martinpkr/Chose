package io.github.choosegame;

import com.badlogic.gdx.Game;

public class CardGame extends Game {

    @Override
    public void create() {
        setScreen(new LoadingScreen(this));
    }
}
