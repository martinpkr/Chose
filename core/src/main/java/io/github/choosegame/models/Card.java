package io.github.choosegame.models;

public class Card {
    private String name;
    private Rarity rarity;
    private String description;
    private String image;
    private int health;
    private int attack;
    private Effect effect;
    private Action action;

    public Card() {
    }

    public Card(String name, Rarity rarity, String description, int health, int attack) {
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.effect = Effect.NONE;
        this.action = Action.NONE;
    }
    public Card(String name, Rarity rarity, String description, int health, int attack, Action action, Effect effect) {
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.effect = effect;
        this.action = action;
    }

    public Card(String name, Rarity rarity, String description, String image, int health, int attack, Action action, Effect effect) {
        this.name = name;
        this.rarity = rarity;
        this.description = description;
        this.image = image;
        this.health = health;
        this.attack = attack;
        this.effect = effect;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}
