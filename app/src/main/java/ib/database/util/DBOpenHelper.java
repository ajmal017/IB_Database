package ib.database.util;

/**
 * Created by chen on 11/7/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ib.database.constant.DBConstant;

/**
 * Class used to open database file
 */

public class DBOpenHelper extends SQLiteOpenHelper  {

    static final String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
    static final int version = DBConstant.DATABASE_VERSION;

    public DBOpenHelper(Context context){
        super(context, path, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}