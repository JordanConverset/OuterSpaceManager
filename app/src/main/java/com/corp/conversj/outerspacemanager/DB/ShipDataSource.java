package com.corp.conversj.outerspacemanager.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by conversj on 21/03/2017.
 */
public class ShipDataSource {
    private Gson gson;
    private SQLiteDatabase database;
    private OuterSpaceManagerDB dbHelper;
    private String[] allColumns = {
            OuterSpaceManagerDB.KEY_SHIP_ID,
            OuterSpaceManagerDB.KEY_SHIP_NAME,
    };
    public ShipDataSource(Context context) {
        dbHelper = new OuterSpaceManagerDB(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public void createShip(Ship ship) {

        if(!verifyShipExist(ship.getId())){
            ContentValues values = new ContentValues();

            values.put(OuterSpaceManagerDB.KEY_SHIP_ID, ship.getId());
            values.put(OuterSpaceManagerDB.KEY_SHIP_NAME,  ship.getName());

            database.insert(OuterSpaceManagerDB.SHIP_TABLE_NAME, null,
                    values);
        }

    }

    private boolean verifyShipExist(int shipId) {
        Cursor cursor = database.query(OuterSpaceManagerDB.SHIP_TABLE_NAME,
                allColumns,OuterSpaceManagerDB.KEY_SHIP_ID
                        + " = ?" , new String[]{String.valueOf(shipId)}, null, null, null);
        return cursor.getCount() > 0;

    }

    public String getShipNameById(int shipId) {
        Cursor cursor = database.query(OuterSpaceManagerDB.SHIP_TABLE_NAME,
                allColumns,OuterSpaceManagerDB.KEY_SHIP_ID
                        + " = ?" , new String[]{String.valueOf(shipId)}, null, null, null);
        cursor.moveToFirst();
        String name = cursor.getString(1);
        return name;
    }

    public List<Ship> getAllShips() {
        List<Ship> lesShips = new ArrayList<Ship>();

        Cursor cursor = database.query(OuterSpaceManagerDB.SHIP_TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ship aShip = cursorToShip(cursor);
            lesShips.add(aShip);
            cursor.moveToNext();
        }
        cursor.close();
        return lesShips;
    }

    private Ship cursorToShip(Cursor cursor) {
        Ship comment = new Ship();
        comment.setId(cursor.getInt(0));
        comment.setName(cursor.getString(1));
        return comment;
    }

    public void deleteShip(Ship aShip) {
        int id = aShip.getId();
        database.delete(OuterSpaceManagerDB.SHIP_TABLE_NAME, OuterSpaceManagerDB.KEY_SHIP_ID
                + " = \"" + id+"\"", null);
    }
}
