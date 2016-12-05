package ib.database;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Date;

import ib.database.constant.SQLCommand;
import ib.database.mppiechart.PieChartActivity;
import ib.database.util.DBOperator;

/**
 * Created by Chen on 10/26/2016.
 */
public class DashboardActivity extends AppCompatActivity {


    private ListView listView;


    //SQLiteDatabse mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_test);
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView = (ListView) this.findViewById(R.id.event_listView);
//        listView.setOnItemClickListener(new ItemClickListener());

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.recent_trades, null);
        String[] from = new String[]{"stk_name", "trade_date"};
        int[] to = new int[]{R.id.stock_name, R.id.trade_date};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.event_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        String[] value = new String[1];
        value[0] = LoginActivity.user_id;
        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getname, value);
        StringArray stringArray = new StringArray();
        String username[][] = stringArray.toStr(cursor1);
        setTitle("Welcome " + username[0][0]);
    }


    //    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
//        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//            String trade_id = cursor.getString(0);
//            /*String event_title = cursor.getString(1);
//            String event_desc = cursor.getString(2);*/
//
//            Intent intent = new Intent(getApplicationContext(), showStockDetail.class);
//            intent.putExtra("trade_id",trade_id);
//            /*intent.putExtra("event_title",event_title);
//            intent.putExtra("event_desc", event_desc);*/
//            /*View sharedView = listView;
//            String tr = "eventlist";
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView,tr);
//            startActivity(intent, options.toBundle());*/
//            startActivity(intent);
//        }
//    }
    public void onClick(View view) {
        //String sql="";
        int id = view.getId();
        if (id == R.id.buy_button) {

            //sql = SQLCommand.QUERY_1;
            Intent intent = new Intent(getApplicationContext(), ShowBuyListActivity.class);
            //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,)
            //intent.putExtra("sql", sql);
            startActivity(intent);

        }
        if (id == R.id.sell_button) {
            Intent intent = new Intent(getApplicationContext(), ShowSellList.class);
            this.startActivity(intent);
        }
        /*if(id==R.id.hot_text)
        {
            Intent intent = new Intent(this, HotDeals1.class);
            this.startActivity(intent);
        }
        if(id==R.id.new_text)
        {
            Intent intent = new Intent(this, NewDeals1.class);
            this.startActivity(intent);
        }*/
        if (view.getId() == R.id.wish_button) {
            Intent intent = new Intent(this, Favorite.class);
            this.startActivity(intent);

        }
        if (view.getId() == R.id.history) {
            String count1 = SQLCommand.history;
            Cursor cursor1 = DBOperator.getInstance().execQuery(count1);
            StringArray stringArray1 = new StringArray();
            String ars1[][] = stringArray1.toStr(cursor1);
            String tradeDate = (ars1[0][1]);
            String tradeType = ars1[0][2];
            Integer tradeStlkQuantity = Integer.parseInt(ars1[0][3]);
            String stlkName = ars1[0][4];

            Integer tradeTypeNumber = 0;
            if (tradeType == "buy") { tradeTypeNumber = 1;}
            if (tradeType == "sell") { tradeTypeNumber = 0;}

            Intent intent = new Intent(this, PieChartActivity.class);
                intent.putExtra("tradeDate", tradeDate);
                intent.putExtra("tradeType", tradeTypeNumber);
                intent.putExtra("tradeStlkQuantity", tradeStlkQuantity);
                intent.putExtra("stlkName", stlkName);
                this.startActivity(intent);
            }
            if (view.getId() == R.id.prof_button) {
                Intent it = new Intent(this, ProfilePage.class);
                startActivity(it);
            }
            if (view.getId() == R.id.logout_button) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                finish();
            }

        }

    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);

        this.startActivity(intent);
        finish();
    }


}
