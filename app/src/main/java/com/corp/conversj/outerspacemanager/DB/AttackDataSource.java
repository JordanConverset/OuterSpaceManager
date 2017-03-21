package com.corp.conversj.outerspacemanager.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.corp.conversj.outerspacemanager.Fleet.Ships;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mac15 on 20/03/2017.
 */

public class AttackDataSource {
    // Database fields
    private SQLiteDatabase database;
    private Gson gson;
    private OuterSpaceManagerDB dbHelper;
    private String[] allColumns = { OuterSpaceManagerDB.KEY_ID,OuterSpaceManagerDB.KEY_SHIPS, OuterSpaceManagerDB.KEY_USERNAME, OuterSpaceManagerDB.KEY_ATTACKTIME, OuterSpaceManagerDB.KEY_BEGINATTACKTIME };

    public AttackDataSource(Context context) {
        dbHelper = new OuterSpaceManagerDB(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        gson = new Gson();
    }

    public void close() {
        dbHelper.close();
    }

    public void createAttack(Attack anAttack) {
        ContentValues values = new ContentValues();
        values.put(OuterSpaceManagerDB.KEY_SHIPS, gson.toJson(anAttack.getShips()));
        values.put(OuterSpaceManagerDB.KEY_USERNAME, anAttack.getUsername());
        values.put(OuterSpaceManagerDB.KEY_ATTACKTIME, anAttack.getAttackTime());
        UUID newID = UUID.randomUUID();
        values.put(OuterSpaceManagerDB.KEY_ID, newID.toString());
        long currentTime = System.currentTimeMillis();
        values.put(OuterSpaceManagerDB.KEY_BEGINATTACKTIME, currentTime);
        database.insert(OuterSpaceManagerDB.ATTACK_TABLE_NAME, null, values);
    }

    public List<Attack> getAllAttacks() {
        List<Attack> lesAttacks = new ArrayList<Attack>();
        Cursor cursor = database.query(OuterSpaceManagerDB.ATTACK_TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Attack anAttack = cursorToAttack(cursor);
            lesAttacks.add(anAttack);
            cursor.moveToNext();
        }
// make sure to close the cursor cursor.close();
        return lesAttacks;
    }

    private Attack cursorToAttack(Cursor cursor) {
        Attack comment = new Attack();
        Gson gson = new Gson();
        String result = cursor.getString(0);
        comment.setId(UUID.fromString(result));
        result = cursor.getString(1);
        comment.setShips(gson.fromJson(result, Ships.class));
        comment.setUsername(cursor.getString(2));
        comment.setAttackTime(cursor.getLong(3));
        comment.setBeginAttackTime(cursor.getLong(4));
        return comment;
    }

    public void deleteAttack(Attack anAttack) {
        UUID id = anAttack.getId(); database.delete(OuterSpaceManagerDB.ATTACK_TABLE_NAME, OuterSpaceManagerDB.KEY_ID + " = \"" + id.toString()+"\"", null);
    }
}
