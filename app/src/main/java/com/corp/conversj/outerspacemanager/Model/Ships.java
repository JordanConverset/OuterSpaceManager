package com.corp.conversj.outerspacemanager.Model;

import com.corp.conversj.outerspacemanager.Model.Ship;

import java.util.List;

/**
 * Created by mac15 on 13/03/2017.
 */

public class Ships {
    private List<Ship> ships;

    public Ships(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship){
        this.ships.add(ship);
    }

}
