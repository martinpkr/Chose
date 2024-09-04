package io.github.choosegame.service;


import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.choosegame.Utils;
import io.github.choosegame.models.Card;
import io.github.choosegame.models.Deck;

import java.util.List;
import java.util.Map;

public class CardGenerator {
    private static TextureAtlas atlas;

    public Map<String, Card> addPlayerCardsToSprites(List<Sprite> sprites) {
        atlas = new TextureAtlas(Utils.getInternalPath("Atlas/choose_atlas.atlas"));
        Deck deck = new Deck(4);
        int move = 258;
        int x = -1000;
        Map<String, Card> cards = deck.getCards();
        for (int i = 0; i < cards.entrySet().size(); i++) {
            String cardName = cards.keySet().toArray()[i].toString();
            Card currentCard = cards.get(cardName);
            Sprite cardSprite = generateCardWithDetails(currentCard, x, -1000);
//            Sprite cardSprite = generateCard(0,0);
            sprites.add(cardSprite);
            x += move;
        }
        return cards;
    }

    public Sprite generateCard(float x, float y) {
        Sprite cardTemplate = new Sprite(atlas.findRegion("cardback"));
        cardTemplate.setBounds(x, y, 125, 175);
        return cardTemplate;
    }

    private Sprite generateCardWithDetails(Card currentCard, float x, float y) {
        int cardWidth = 250; // Card width
        int cardHeight = 350; // Card height

        int borderWidth = 1; // Border width
        Color borderColor = Color.BLACK;

        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, cardWidth, cardHeight, false);
        frameBuffer.begin();

        ScreenUtils.clear(0, 0, 0, 0);

        SpriteBatch newSpriteBatch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, cardWidth, cardHeight);
        //this fixed my problem with small framebuffer sprites
        newSpriteBatch.setProjectionMatrix(camera.combined);
        newSpriteBatch.begin();

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(borderColor);

        shapeRenderer.rect(0, 0, cardWidth, borderWidth);


        Sprite cardTemplate = new Sprite(atlas.findRegion("cardback"));
        cardTemplate.setBounds(0, 0, cardWidth, cardHeight);
        cardTemplate.draw(newSpriteBatch);

        // Draw card image
        Sprite cardImage = new Sprite(atlas.findRegion(currentCard.getImage()));
        cardImage.setBounds(30, 50, 130, 150); // Adjust as needed
        cardImage.draw(newSpriteBatch);

        BitmapFont attackFont = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        attackFont.setColor(Color.RED);
        attackFont.draw(newSpriteBatch, Integer.toString(currentCard.getAttack()), 10, 40);

        BitmapFont healthFont = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        healthFont.setColor(Color.GREEN);
        healthFont.draw(newSpriteBatch, Integer.toString(currentCard.getHealth()), cardWidth - 10, 60);
        // Draw card name
        BitmapFont cardNamefont = new BitmapFont(Utils.getInternalPath("font1.fnt"));
        cardNamefont.setColor(Color.BLACK);
        cardNamefont.getData().setScale(0.9f);
        cardNamefont.draw(newSpriteBatch, currentCard.getName(), 20, cardHeight - 10);

        // Draw attack and health text
        shapeRenderer.end();
        newSpriteBatch.end();
        frameBuffer.end();

        Texture cardTexture = frameBuffer.getColorBufferTexture();
        Sprite finalCardSprite = new Sprite(cardTexture);
        finalCardSprite.flip(false, true);
        finalCardSprite.setBounds(x, y, cardWidth, cardHeight);

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
