package ib.database;

import android.database.Cursor;

/**
 * Created by Chen on 11/12/2016.

 * This Code Converts a Cursor to a two dimentional String
 * Usage from calling class

 String sql = SQLCommand.QUERY_STUDENT12;
 Cursor cursor = DBOperator.getInstance().execQuery(sql);
 StringArray stringArray = new StringArray();
 String ars[][]= stringArray.toStr(cursor);

 *
 */
public class StringArray {
    int j;
    public String[][] toStr(Cursor cursor)
    {

        String[] ars = cursor.getColumnNames();
        String[][] val = new String[cursor.getCount()][ars.length];
        if (cursor != null) {


/*
            for (int i = 0; i < ars.length; i++) {
                val[0][i] = ars[i];
            }
*/
            for (int i = 0; i < ars.length; i++) {

                if (cursor.moveToFirst()) {
                    j=0;
                    do {
                        // Get version from Cursor
                        if (j<cursor.getCount()) {
                            val[j][i] = cursor.getString(cursor.getColumnIndex(ars[i]));
                            //System.out.println(val[j][i]+ " j= " +j +" i= " +i);

                            System.out.println("J = " + j);
                            j++;
                        }
                        System.out.println("JJ = " +j);
                    } while (cursor.moveToNext());

                }

            }


        }


        System.out.println("Start");
        System.out.println(ars.length);
        System.out.println(cursor.getCount()+1);
        for(int i=0;i<ars.length;i++)
            for(int j=0;j<cursor.getCount();j++)
            {
                System.out.println(" j= " +j  +" i= " +i  +" " +val[j][i]);
            }

        return val;
    }
}
