package ib.database;

/**
 * Created by chen on 11/6/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ib.database.unused.DBContact;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "iBuy.db";
    private static final String TABLE_NAME = "STUDENT";
    private static final String COLUMN_ID = "st_id";
    private static final String COLUMN_FIRST_NAME = "st_first_name";
    private static final String COLUMN_LAST_NAME = "st_last_name";
    private static final String COLUMN_EMAIL = "st_email";
    private static final String COLUMN_PASS = "st_contact";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table "+TABLE_NAME+"(st_id integer primary key not null,"+"st_first_name text not null,st_last_name text not null, st_email text not null, st_contact text not null)";

    public DBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }

    public String searchPass(String uname){

        db = this.getReadableDatabase();
        String query = "select st_email,st_contact from "+TABLE_NAME;
        //System.out.println("QUERY "+query);
        //db.execSQL(query);
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b="not found";

        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(1);
                System.out.println("value of column a "+a);
                System.out.println("value of uname "+uname);
                if(a.equals(uname))
                {    System.out.println("I'm inside IF loop ");
                    b=cursor.getString(1);
                    System.out.println("value of column b "+b);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS" +TABLE_NAME;
    }

    public void insertContact(DBContact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(COLUMN_ID,c.getId());
        values.put(COLUMN_FIRST_NAME,c.getfirstName());
        values.put(COLUMN_LAST_NAME,c.getlastName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASS, c.getPass());
        db.insert(TABLE_NAME,null,values);
    }
}