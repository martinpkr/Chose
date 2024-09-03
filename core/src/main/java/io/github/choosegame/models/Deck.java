package io.github.choosegame.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int DECK_CARD_LIMIT = 20;

    private int cardCount;
    private List<Card> cards;
    private CardPool currentCardPool;

    public Deck(int cardCount) {
        this.cardCount = cardCount;
        this.cards = getRandomCardsFromPool(cardCount);
    }

    private List<Card> getRandomCardsFromPool(int numberOfCards) {
        List<Card> allCards = CardPool.getCards();
        List<Card> shuffledCards = new ArrayList<>(allCards);
        Collections.shuffle(shuffledCards);

        int numCardsToPick = Math.min(numberOfCards, shuffledCards.size());

        return shuffledCards.subList(0, numCardsToPick);
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CardPool getCurrentCardPool() {
        return currentCardPool;
    }

    public void setCurrentCardPool(CardPool currentCardPool) {
        this.currentCardPool = currentCardPool;
    }
}
