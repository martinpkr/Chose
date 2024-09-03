package io.github.choosegame.models;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Integer> playerSlots;
    private List<Integer> enemySlots;

    public Table(List<Integer> playerSlots, List<Integer> enemySlots) {
        this.playerSlots = List.of(1,2,3,4);
        this.enemySlots = List.of(1,2,3,4);
    }

    public List<Integer> getPlayerSlots() {
        return playerSlots;
    }

    public void setPlayerSlots(List<Integer> playerSlots) {
        this.playerSlots = playerSlots;
    }

    public List<Integer> getEnemySlots() {
        return enemySlots;
    }

    public void setEnemySlots(List<Integer> enemySlots) {
        this.enemySlots = enemySlots;
    }
}
