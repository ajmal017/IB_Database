package ib.database;

/**
 * Created by Chen on 10/26/2016.
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;

public class ShowBuyListActivity extends AppCompatActivity {
    private ListView listView;
    private Spinner spinner;

    public static int BLFlag = 0;
    public static String stockID;
    @SuppressLint("NewApi")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_page);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        listView = (ListView) this.findViewById(R.id.buy_list);
        listView.setOnItemClickListener(new ItemClickListener());
//        listView.setVerticalScrollBarEnabled();

        // get the sql string delivered from the QueryActivity
        //Intent intent = this.getIntent();
        //String sql = intent.getStringExtra("sql");
        // execute the sql
        //String[] value= new String[1];
        //value[0]= LoginActivity.user_id;
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.showbuylist, null);
        String[] from = new String[]{"stock_name","stock_price"};
        int[] to = new int[]{R.id.Buystock_name, R.id.stock_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);
        //Spinner entries
        loadSpinnerData();

    }

    public void onClick(View v){
    String sql = "";

    int id=v.getId();
    if(id==R.id.sortbtn)
    {
        String[] value= new String[1];
//        value[0]= LoginActivity.user_id;

        int pos = spinner.getSelectedItemPosition();
    if (pos == Spinner.INVALID_POSITION) {
        //User doesn't choose any query, show warning
        Toast.makeText(getApplicationContext(), "Please choose a market!", Toast.LENGTH_SHORT).show();
        return;
    }
    if (pos == 0) {
        Toast.makeText(getApplicationContext(), "Please choose a market!", Toast.LENGTH_SHORT).show();
        return;
    }
    if (pos == 1) {
        //sql = SQLCommand.QUERY_category_spinner_1;
        value[0]="NASDAQ";
    }
    if (pos == 2) {
        value[0]="NYSE";
        //sql = SQLCommand.QUERY_category_spinner_2;
    }
    if (pos == 3) {
        value[1]="302";
        //sql = SQLCommand.QUERY_category_spinner_3;
    }
    if (pos == 4) {
        value[1]="303";
        //sql = SQLCommand.QUERY_category_spinner_4;
    }
    if (pos == 5) {
        value[1]="304";
        //sql = SQLCommand.QUERY_category_spinner_5;
    }
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.query_spinner, value);
        String[] from = new String[]{"stock_name","stock_price"};
        int[] to = new int[]{R.id.Buystock_name, R.id.stock_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }
    if(id==R.id.clrbtn)
    {
        //String[] value= new String[1];
        //value[0]= LoginActivity.user_id;
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.showbuylist);
        String[] from = new String[]{"stock_name","stock_price"};
        int[] to = new int[]{R.id.Buystock_name, R.id.stock_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

        spinner.setSelection(0);
    }

}

    public List<String> getAllLabels()
    {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = SQLCommand.QUERY_market_spinner;

        //SQLiteDatabase db = DBOperator.copyDB(getBaseContext()).getReadableDatabase();
        Cursor cursor = DBOperator.getInstance().execQuery(selectQuery, null);
        labels.add(0,"Select Market");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // returning lables
        return labels;

    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData()
    {
        // Spinner Drop down elements
        List<String> labels = this.getAllLabels();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, labels);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private class ItemClickListener implements OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String stock_id = cursor.getString(0);
            String stock_name = cursor.getString(1);
            String stock_price = cursor.getString(2);
            String industry_type = cursor.getString(3);
            String related_market = cursor.getString(4);
            String stock_description = cursor.getString(5);

            int count1;
            String [] value = new String[1];
            value[0] = stock_id;

            /*String getcount= SQLCommand.gethitcount;
            Cursor cursor1 = DBOperator.getInstance().execQuery(getcount,value);
            StringArray stringArray = new StringArray();
            String ars[][]= stringArray.toStr(cursor1);
            count1=Integer.parseInt(ars[0][0]);
            count1=count1+1;

            String sql=SQLCommand.updatehitcount;
            String value1[]=new String[2];
            value1[0]=Integer.toString(count1);
            value1[1]=stock_id;
            DBOperator.getInstance().execSQL(sql,value1);
*/
            stockID =stock_id;
            System.out.println( "Stock ID = " + stockID);
            Intent intent = new Intent(getApplicationContext(), showStockDetail.class);
            intent.putExtra("stock_name", stock_name);
            intent.putExtra("stock_price", stock_price);
            intent.putExtra("industry_type", industry_type);
            intent.putExtra("related_market", related_market);
            intent.putExtra("stock_description", stock_description);
            intent.putExtra("stock_id",stock_id);
            BLFlag=1;
            startActivity(intent);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

}


