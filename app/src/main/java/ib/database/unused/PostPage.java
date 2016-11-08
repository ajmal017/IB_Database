package ib.database.unused;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ib.database.R;
import ib.database.ShowBuyListActivity;
import ib.database.constant.SQLCommand;
import ib.database.dbtest.SQLController;
import ib.database.util.DBOperator;

public class PostPage extends AppCompatActivity {

    private ListView listView;
    private TextView post_title;
    private TextView post_desc;
    private TextView st_first_name;
    private SQLController dbController;
    public static String postID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_page);
        //dbOperator = new DBOperator();


        //dbController = new SQLController(this);
        //dbController.open();

        post_title = (TextView) findViewById(R.id.post_title);
        post_desc = (TextView) findViewById(R.id.post_desc);
        st_first_name = (TextView) findViewById(R.id.st_first_name);

        Intent intent = this.getIntent();

        String st_first_name1 = intent.getStringExtra("st_first_name");
        String post_title1 = intent.getStringExtra("post_title");
        String post_desc1 = intent.getStringExtra("post_desc");
        String[] post_id = {intent.getStringExtra("post_id")};
        postID=post_id[0];
        post_title.setText(post_title1);
        st_first_name.setText(st_first_name1);
        post_desc.setText(post_desc1);
        //String[] string1 = {post_id};
        //new String[1];
        //System.out.println(post_id + st_first_name1 + string1);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/
//listview
        listView = (ListView) this.findViewById(R.id.itemlistView);
        listView.setOnItemClickListener(new ItemClickListener());

        // get the sql string delivered from the QueryActivity

        // execute the sql

       /* Cursor cursor = DBOperator.getInstance().execQuery();
                PlayerEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if (cursor != null)
            cursor.moveToFirst();*/
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_display_items, post_id);
        String[] from = new String[]{"item_name","item_desc","item_price"};
        int[] to = new int[]{R.id.item_name, R.id.item_desc,R.id.item_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.listitem_itempage, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        if (cursor != null && cursor.moveToFirst())
        {
            System.out.println(cursor.getString(0));
        }
}
    private class ItemClickListener implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String item_name = cursor.getString(1);
            String item_desc = cursor.getString(2);
            String item_price = cursor.getString(3);
            //String coreturned = cursor.getString(3);
            // String cofine = cursor.getString(4);
            // String stname = cursor.getString(5);
            //Toast.makeText(getApplicationContext(),"Student Name: " + st_first_name+ "\nPost Title: " + post_title+ "\nPost Description: " + post_desc, Toast.LENGTH_LONG).show();
           /* Intent intent = new Intent(getApplicationContext(), PostPage.class);
            intent.putExtra("item_name", item_name);
            intent.putExtra("item_desc", item_desc);
            intent.putExtra("item_price", item_price);
            startActivity(intent);*/
        }
    }


    public void onClick(View view){
        int id=view.getId();
        if(id==R.id.goback_btn)
        {
            Intent intent = new Intent(getApplicationContext(), ShowBuyListActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.add_wishlist)
        {

            Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_2);
        }

    }
}
