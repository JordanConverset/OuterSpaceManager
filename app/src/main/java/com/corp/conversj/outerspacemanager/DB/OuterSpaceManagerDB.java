package com.corp.conversj.outerspacemanager.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by mac15 on 20/03/2017.
 */

public class OuterSpaceManagerDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "OuterSpaceManager.db";
    public static final String ATTACK_TABLE_NAME = "Attack";
    public static final String KEY_ID = "id";
    public static final String KEY_SHIPS = "ships";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ATTACKTIME = "attackTime";
    public static final String KEY_BEGINATTACKTIME = "beginAttackTime";
    private static final String ATTACK_TABLE_CREATE = "CREATE TABLE " + ATTACK_TABLE_NAME + " (" + KEY_ID + " TEXT, " +
            KEY_SHIPS + " TEXT, " + KEY_USERNAME + " TEXT, " + KEY_ATTACKTIME + " REAL, " + KEY_BEGINATTACKTIME + " REAL);";
    public OuterSpaceManagerDB(Context context) {
        super(context, Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME, null, DATABASE_VERSION); }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ATTACK_TABLE_CREATE); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("DROP TABLE IF EXISTS " + ATTACK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ATTACK_TABLE_NAME);
        onCreate(db); }
}
