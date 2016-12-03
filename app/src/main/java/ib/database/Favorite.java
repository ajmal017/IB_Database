package ib.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;

/**
 * Created by Chen on 11/8/2015.
 */
public class Favorite extends AppCompatActivity {

    private ListView listView;

    public static int BLFlag = 0;
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        listView = (ListView) this.findViewById(R.id.wish_listView);

        String [] value= new String[1];
        value[0] = LoginActivity.user_id;
        /*String sql= SQLCommand.getwishlist;
        Cursor cursor = DBOperator.getInstance().execQuery(sql,value);

        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String wish_id = ars[0][0];

        String [] value1= new String[1];
        value1[0]= wish_id;*/

        String sql1= SQLCommand.showwishlist;
        Cursor cursor1 = DBOperator.getInstance().execQuery(sql1,value);
        String[] from = new String[]{"stock_name","stock_price"};
        int[] to = new int[]{R.id.Favstock_name, R.id.Favstock_price};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.favorite_layout, cursor1,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemClickListener());

    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String item_id = cursor.getString(0);

            String value [] = new String[1];
            value [0] = item_id;

            Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getposterdetails,value);
            StringArray stringArray1 = new StringArray();
            String ars1[][]= stringArray1.toStr(cursor1);
            String post_id = ars1 [0][0];
            String user_first_name = ars1 [0][1];
            String user_last_name = ars1 [0][2];
            String user_phone = ars1 [0][3];
            String post_title = ars1 [0][4];
            String post_desc = ars1 [0][5];



            Intent intent = new Intent(getApplicationContext(), showStockDetail.class);
            intent.putExtra("stock_name", user_first_name);
            intent.putExtra("stock_price", user_last_name);
            intent.putExtra("industry_type", user_phone);
            intent.putExtra("related_market", post_title);
            intent.putExtra("stock_description", post_desc);

            startActivity(intent);

        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
