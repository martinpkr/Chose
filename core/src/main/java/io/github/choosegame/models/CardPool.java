package io.github.choosegame.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.choosegame.Utils;

import java.io.IOException;
import java.util.List;

public class CardPool {

    private int cardCount;
    public static List<Card> cards;

    static {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(Utils.getInternalPath("cards.json"));
        try {
            cards = mapper.readValue(Utils.getInternalPath("cards.json").file(), new TypeReference<List<Card>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public static List<Card> getCards() {
        return cards;
    }

    public static void setCards(List<Card> cards) {
        CardPool.cards = cards;
    }
}
