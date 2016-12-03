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
 * Created by Chen on 11/6/2016.
 */
public class History extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    public static int BLFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_posts);

        setTitle("My Posts");
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        listView = (ListView) this.findViewById(R.id.my_post_list);
        listView.setOnItemClickListener(new ItemClickListener());

        String[] value= new String[1];
        value[0]= LoginActivity.user_id;
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.mypostlist, value);
        String[] from = new String[]{"stock_name","stock_price"};
        int[] to = new int[]{R.id.Buystock_name, R.id.stock_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String post_id = cursor.getString(0);
            String user_first_name = cursor.getString(1);
            String user_last_name = cursor.getString(2);
            String user_phone = cursor.getString(3);
            String post_title = cursor.getString(4);
            String post_desc = cursor.getString(5);

            System.out.println( "Post ID = " + post_id);
            Intent intent = new Intent(getApplicationContext(), showStockDetail.class);
            intent.putExtra("user_first_name", user_first_name);
            intent.putExtra("user_last_name", user_last_name);
            intent.putExtra("user_phone", user_phone);
            intent.putExtra("post_title", post_title);
            intent.putExtra("post_desc", post_desc);
            intent.putExtra("post_id",post_id);
            BLFlag=1;
            startActivity(intent);

        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
