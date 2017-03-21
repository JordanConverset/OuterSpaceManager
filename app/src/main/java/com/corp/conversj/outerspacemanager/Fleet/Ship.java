package com.corp.conversj.outerspacemanager.Fleet;

/**
 * Created by mac15 on 13/03/2017.
 */

public class Ship {
    private int shipId;
    private int gasCost;
    private int mineralCost;
    private int life;
    private int minAttack;
    private int maxAttack;
    private int shield;
    private int spatioportLevelNeeded;
    private int speed;
    private String name;
    private int timeToBuild;
    private int amount;
    private int capacity;

    public Ship(int amount){
        this.amount = amount;
    }

    public Ship(int amount, int shipId){
        this.amount = amount;
        this.shipId = shipId;
    }

    public int getShipId() {
        return shipId;
    }

    public int getGasCost() {
        return gasCost;
    }

    public int getMineralCost() {
        return mineralCost;
    }

    public int getLife() {
        return life;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public int getShield() {
        return shield;
    }

    public int getSpatioportLevelNeeded() {
        return spatioportLevelNeeded;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public int getTimeToBuild() {
        return timeToBuild;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAmount() {
        return amount;
    }

}

