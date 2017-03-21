package com.corp.conversj.outerspacemanager.DB;

import com.corp.conversj.outerspacemanager.Fleet.Ships;

import java.util.UUID;

/**
 * Created by mac15 on 20/03/2017.
 */

public class Attack {
    private UUID id;
    private Ships ships;
    private String username;
    private long attackTime;
    private long beginAttackTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Ships getShips() {
        return ships;
    }

    public void setShips(Ships ships) {
        this.ships = ships;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(long attackTime) {
        this.attackTime = attackTime;
    }

    public long getBeginAttackTime() {
        return beginAttackTime;
    }

    public void setBeginAttackTime(long beginAttackTime) {
        this.beginAttackTime = beginAttackTime;
    }

    public int getProgress() {
        return Integer.parseInt(String.valueOf(((System.currentTimeMillis()-beginAttackTime) * 100)/(attackTime-beginAttackTime)));
    }
}
