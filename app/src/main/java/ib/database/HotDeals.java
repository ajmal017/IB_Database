package ib.database;

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

public class HotDeals extends AppCompatActivity {
    private ListView listView;
    private Spinner spinner;

    public static String testId;
    @SuppressLint("NewApi")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_page);

        listView = (ListView) this.findViewById(R.id.buy_list);
        listView.setOnItemClickListener(new ItemClickListener());

        // get the sql string delivered from the QueryActivity
        //Intent intent = this.getIntent();
        //String sql = intent.getStringExtra("sql");
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.showhotbuylist, null);
        String[] from = new String[]{"post_title","post_desc"};
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
    int pos = spinner.getSelectedItemPosition();
    if (pos == Spinner.INVALID_POSITION) {
        //User doesn't choose any query, show warning
        Toast.makeText(getApplicationContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
        return;
    }
    if (pos == 0) {
        Toast.makeText(getApplicationContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
        return;
    }
    if (pos == 1) {
        sql = SQLCommand.QUERY_category_spinner_1;
    }
    if (pos == 2) {
        sql = SQLCommand.QUERY_category_spinner_2;
    }
    if (pos == 3) {
        sql = SQLCommand.QUERY_category_spinner_3;
    }
    if (pos == 4) {
        sql = SQLCommand.QUERY_category_spinner_4;
    }
    Cursor cursor = DBOperator.getInstance().execQuery(sql, null);
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
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_1, null);
        // bind the data to list
        String[] from = new String[]{"post_title","post_desc"};
        int[] to = new int[]{R.id.Buystock_name, R.id.stock_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        spinner.setSelection(0);
    }


}






  /*  public void listview() {
        listView = (ListView) this.findViewById(R.id.buy_list);
        listView.setOnItemClickListener(new ItemClickListener());

        // get the sql string delivered from the QueryActivity
        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(sql, null);
        String[] from = new String[] { "st_first_name", "post_title" };
        int [] to = new int[] { R.id.st_first_name, R.id.post_title};
        // bind the data to list
        System.out.println(SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from,to,SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }
*/



    public List<String> getAllLabels()
    {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = SQLCommand.QUERY_market_spinner;

        //SQLiteDatabase db = DBOperator.copyDB(getBaseContext()).getReadableDatabase();
        Cursor cursor = DBOperator.getInstance().execQuery(selectQuery, null);
        labels.add(0,"Select Category");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        //cursor.close();


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
   /* public class SpinnerClickListener implements AdapterView.OnItemSelectedListener {


        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String sql="";
            // On selecting a spinner item
            String label = parent.getItemAtPosition(position).toString();
            int pos = spinner.getSelectedItemPosition();
            if (pos==Spinner.INVALID_POSITION){
                //User doesn't choose any query, show warning
                Toast.makeText(getApplicationContext(), "Please choose a query!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (pos==0){
                sql=SQLCommand.QUERY_category_spinner_0;
            }
            if (pos==1){
                sql=SQLCommand.QUERY_category_spinner_1;
            }
            if (pos==2){
                sql=SQLCommand.QUERY_category_spinner_2;
            }
            if (pos==3){
                sql=SQLCommand.QUERY_category_spinner_3;
            }
            Cursor cursor=DBOperator.getInstance().execQuery(sql);
            String[] from = new String[] { "st_first_name", "post_title" };
            int [] to = new int[] { R.id.st_first_name, R.id.post_title};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    getApplicationContext(), R.layout.buy_listview, cursor,
                    from,to,SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
            listView.setAdapter(adapter);
        }


       */


    private class ItemClickListener implements OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String post_id = cursor.getString(0);
            String user_first_name = cursor.getString(1);
            String user_last_name = cursor.getString(2);
            String post_title = cursor.getString(3);
            String post_desc = cursor.getString(4);


            int count1;
            String count= "SELECT post_hit_counter FROM POST where post_id="+post_id;
            Cursor cursor1 = DBOperator.getInstance().execQuery(count);
            StringArray stringArray = new StringArray();
            String ars[][]= stringArray.toStr(cursor1);
            count1=Integer.parseInt(ars[0][0]);
            count1=count1+1;

            String sql=SQLCommand.updatehitcount;
            String value[]=new String[2];
            value[0]=Integer.toString(count1);
            value[1]=post_id;
            System.out.println(sql);
            DBOperator.getInstance().execSQL(sql,value);

            testId=post_id;
            System.out.println( "Post ID " + testId);
            //String coreturned = cursor.getString(3);
            // String cofine = cursor.getString(4);
            // String stname = cursor.getString(5);
            //Toast.makeText(getApplicationContext(),"Student Name: " + st_first_name+ "\nPost Title: " + post_title+ "\nPost Description: " + post_desc, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), showStockDetail.class);
            intent.putExtra("user_first_name", user_first_name);
            intent.putExtra("user_last_name", user_last_name);
            intent.putExtra("post_title", post_title);
            intent.putExtra("post_desc", post_desc);
            intent.putExtra("post_id",post_id);




            startActivity(intent);
        }
    }

}


