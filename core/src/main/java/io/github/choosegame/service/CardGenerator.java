package io.github.choosegame.service;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.choosegame.Utils;
import io.github.choosegame.models.Card;
import io.github.choosegame.models.Deck;

import java.util.List;

public class CardGenerator {
    private static TextureAtlas atlas;

    public void addPlayerCardsToSprites(List<Sprite> sprites) {
        atlas = new TextureAtlas(Utils.getInternalPath("Atlas/choose_atlas.atlas"));
        Deck deck = new Deck(4);
        int move = 250;
        int x = -1000;
        List<Card> cards = deck.getCards();
        for (int i = 0; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            Sprite cardSprite = generateCardWithDetails(currentCard, x, -700);
            sprites.add(cardSprite);
            x += move;
        }
    }

    public Sprite generateCard(float x, float y) {
        Sprite cardTemplate = new Sprite(atlas.findRegion("cardback"));
        cardTemplate.setBounds(x, y, 125, 175);
        return cardTemplate;
    }

    private Sprite generateCardWithDetails(Card currentCard, float x, float y) {
        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        frameBuffer.begin();

        ScreenUtils.clear(0, 0, 0, 0);

        SpriteBatch newSpriteBatch = new SpriteBatch();
        newSpriteBatch.begin();

        Sprite cardTemplate = new Sprite(atlas.findRegion("cardback"));
        cardTemplate.setBounds(0, 1, 200, 260);
        cardTemplate.flip(true, false);

        cardTemplate.draw(newSpriteBatch);

        Sprite cardImage = new Sprite(atlas.findRegion(currentCard.getImage()));
        cardImage.setBounds(30, 50, 130, 150);
        cardImage.flip(true, false);
        cardImage.draw(newSpriteBatch);

        String name = currentCard.getName();
        BitmapFont cardNamefont = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        cardNamefont.setColor(Color.BLACK);
        cardNamefont.getData().setScale(0.9f);
        cardNamefont.draw(newSpriteBatch, name, 20, 250);

        BitmapFont attackFont = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        attackFont.setColor(Color.RED);
        int attack = currentCard.getAttack();
        attackFont.draw(newSpriteBatch, Integer.toString(attack), 10, 25);
        BitmapFont healthFont = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        healthFont.setColor(Color.GREEN);
        int health = currentCard.getHealth();
        healthFont.draw(newSpriteBatch, Integer.toString(health), 150, 25);

        newSpriteBatch.end();

        frameBuffer.end();
        Texture cardTexture = frameBuffer.getColorBufferTexture();

        Sprite finalCardSprite = new Sprite(cardTexture);
        finalCardSprite.flip(false,true);
        finalCardSprite.setBounds(x, y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        return finalCardSprite;
    }

//    public Sprite generateImages(Card currentCard, float x, float y) {
//        Sprite cardImage = new Sprite(atlas.findRegion(currentCard.getImage()));
//        cardImage.setBounds(x + 15.5f, y + 30, 100, 100);
//        return cardImage;
//    }

    public void renderPlayerCardsSprite(List<Sprite> sprites, SpriteBatch batch) {
        for (Sprite sprite : sprites) {
            sprite.draw(batch);
        }
    }

}
