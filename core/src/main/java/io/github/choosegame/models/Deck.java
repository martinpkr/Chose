package io.github.choosegame.models;

import java.util.*;

public class Deck {

    private static final int DECK_CARD_LIMIT = 20;

    private int cardCount;
    private Map<String, Card> cards;
    private CardPool currentCardPool;

    public Deck(int cardCount) {
        this.cardCount = cardCount;
        this.cards = getRandomCardsFromPool(cardCount);
    }

    private Map<String, Card> getRandomCardsFromPool(int numberOfCards) {
        List<Card> allCards = CardPool.getCards();

        List<Card> shuffledCards = new ArrayList<>(allCards);
        Collections.shuffle(shuffledCards);

        int numCardsToPick = Math.min(numberOfCards, shuffledCards.size());

        Map<String, Card> selectedCardsMap = new HashMap<>();

        for (int i = 0; i < numCardsToPick; i++) {
            Card card = shuffledCards.get(i);
            String nameOfCard = card.getName();
            selectedCardsMap.put(nameOfCard, card);
        }
        return selectedCardsMap;
    }

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public void setCards(Map<String, Card> cards) {
        this.cards = cards;
    }

    public CardPool getCurrentCardPool() {
        return currentCardPool;
    }

    public void setCurrentCardPool(CardPool currentCardPool) {
        this.currentCardPool = currentCardPool;
    }
}
