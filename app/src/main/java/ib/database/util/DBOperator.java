package ib.database.util;

/**
 * Created by chen on 11/6/16.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ib.database.constant.DBConstant;
import ib.database.constant.SQLCommand;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Class to manipulate tables & data
 * Uses singleton pattern to create single instance
 */

public class DBOperator {


    public static String c;
    private String b;
    private static DBOperator instance = null;
    private SQLiteDatabase db;

    public DBOperator() {
        //path of database file
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /*
     * Singleton Pattern
     * Why should we avoid multiple instances here?
     */
    public static DBOperator getInstance() {
        if (instance == null) instance = new DBOperator();
        return instance;
    }

    /**
     * Copy database file
     * From assets folder (in the project) to android folder (on device)
     */
    public static void copyDB(Context context) throws IOException, FileNotFoundException {
        String path = DBConstant.DATABASE_PATH + "/" + DBConstant.DATABASE_FILE;
        File file = new File(path);
        if (!file.exists()) {
            DBOpenHelper dbhelper = new DBOpenHelper(context);
            dbhelper.getWritableDatabase();
            InputStream is = context.getAssets().open(DBConstant.DATABASE_FILE);
            OutputStream os = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.flush();
            os.close();
        }
    }


    //check password
    public String searchPass(String uname) {
        System.out.println("User Name = "+uname);
        String query = SQLCommand.logincheck;
        //System.out.println("QUERY "+query);
        //db.execSQL(query);
        Cursor cursor_2 = db.query("business_partner", new String[] {"bp_id", "bp_email", "bp_pass"}, "bp_email = ?", new String[]{ ""+uname+"" }, null, null, null, null);

        if (cursor_2 == null)
            return b="null";
        try{

            if (cursor_2.moveToFirst()) // Here we try to move to the first record
            {
                c = cursor_2.getString(0);
                b = cursor_2.getString(2);
            }
            //edittext.setText(cursor_2.getString(2)); // Only assign string value if we moved to first record
        }finally {
            cursor_2.close();
        }
        //Cursor cursor = db.execSQL(query, value);
        //String a, b;
        //b = "not found";



        /*
        if(cursor.moveToFirst()){
            do {
                c = cursor.getString(0);
                a = cursor.getString(2);
                System.out.println("value of column a "+a);
                System.out.println("value of uname "+uname);
                if(a.equals(uname))
                {
                    b=cursor.getString(2);
                    System.out.println("value of column b "+b);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        */
        //System.out.println(c);
        //return b;

        System.out.println("User Pass = "+b+" business_partner Id = "+c);
        return b;
    }
    /**
     * execute sql without returning data, such as alter
     * @param sql
     */
    public void execSQL(String sql) throws SQLException
    {
        db.execSQL(sql);
    }
    /**
     * execute sql such as update/delete/insert
     * @param sql
     * @param args
     * @throws SQLException
     */
    public void execSQL(String sql, Object[] args) throws SQLException
    {
        db.execSQL(sql, args);
    }
    /**
     * execute sql query
     * @param sql
     * @param selectionArgs
     * @return cursor
     * @throws SQLException
     */
    public Cursor execQuery(String sql,String[] selectionArgs) throws SQLException
    {
        return db.rawQuery(sql, selectionArgs);
    }
    /**
     * execute query without arguments
     * @param sql
     * @return
     * @throws SQLException
     */
    public Cursor execQuery(String sql) throws SQLException
    {
        return this.execQuery(sql, null);
    }
    /**
     * close database
     */
    public void closeDB()
    {
        if (db!=null) db.close();
    }
}