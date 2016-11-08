package ib.database.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import ib.database.constant.DBConstant;
import ib.database.constant.SQLCommand;
import ib.database.util.DBOpenHelper;

/**
 * Created by Chen on 11/6/2016.
 */
public class SQLController {
    private DBOpenHelper dbOpenHelper;

    private Context ourcontext;
    private SQLiteDatabase database;
    private static SQLController instance = null;


    public SQLController(Context c){
        ourcontext = c;
    }
   /*public static SQLController getInstance() {
        if (instance==null) instance = new SQLController();
        return instance;

    }*/



    public SQLController open() throws SQLException {
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        int ver = DBConstant.DATABASE_VERSION;
        dbOpenHelper = new DBOpenHelper(ourcontext) {
        };
        database = dbOpenHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbOpenHelper.close();
    }

    /*public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.TODO_SUBJECT, name);
        contentValue.put(DBhelper.TODO_DESC, desc);
        database.insert(DBhelper.TABLE_NAME, null, contentValue);
    }*/

    public Cursor fetch(String tblname)
    {
        //c = null;
        String[] columns = new String[] {};
        Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null,null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.TODO_SUBJECT, name);
        contentValues.put(DBhelper.TODO_DESC, desc);
        int i = database.update(DBhelper.TABLE_NAME, contentValues,
                DBhelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBhelper.TABLE_NAME, DBhelper._ID + "=" + _id, null);
    }
}

