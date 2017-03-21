package com.corp.conversj.outerspacemanager.Model;

/**
 * Created by mac15 on 07/03/2017.
 */

public class Search {
    private int searchId;
    private int amountOfEffectByLevel;
    private int amountOfEffectLevel0;
    private String effect;
    private String effectAdded;
    private Boolean building;
    private int gasCostByLevel;
    private int gasCostLevel0;
    private int level;
    private int mineralCostByLevel;
    private int mineralCostLevel0;
    private String name;
    private int timeToBuildByLevel;
    private int timeToBuildLevel0;


    public int getAmoutOfEffectByLevel(){
        return amountOfEffectByLevel;
    }

    public int getAmountOfEffectLevel0(){
        return amountOfEffectLevel0;
    }

    public String getEffect() {
        return effect;
    }

    public int getGasCostByLevel() {
        return gasCostByLevel;
    }

    public int getGasCostLevel0() {
        return gasCostLevel0;
    }

    public int getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public int getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public int getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public int getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public String getEffectAdded() {
        return effectAdded;
    }

    public Boolean getBuilding() {
        return building;
    }

    public int getLevel() {
        return level;
    }

    public int getBuildingId() {
        return searchId;
    }
}
