package ib.database.unused;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ib.database.LoginActivity;
import ib.database.R;
import ib.database.ShowBuyListActivity;
import ib.database.StringArray;
import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;

/**
 * Created by Chen on 12/1/2015.
 */
public class MajorList extends AppCompatActivity implements RecyclerViewClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView title;
    private TextView desc;
    private String itemId;
    private String itemName;
    private String postTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //Toast.makeText(this,"Click on an item to add to Favorite" , Toast.LENGTH_SHORT).show();
        title =(TextView) this.findViewById(R.id.title);
        desc=(TextView) this.findViewById(R.id.desc);

        String sql1= SQLCommand.getpostdetails;
        Cursor cursor1 = DBOperator.getInstance().execQuery(sql1);
        StringArray stringArray1 = new StringArray();
        String ars[][]= stringArray1.toStr(cursor1);
        title.setText(ars[0][0]);
        desc.setText(ars[0][1]);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String value[]=new String[1];
        value[0]= ShowBuyListActivity.stockID;


        String sql = SQLCommand.getitemdetails;
        Cursor cursor = DBOperator.getInstance().execQuery(sql,value);
        StringArray stringArray = new StringArray();
        String ars1[][]= stringArray.toStr(cursor);
        itemId = ars1[0][0];
        itemName = ars1[0][1];
        //System.out.println("Item Name="+itemName);

        String sql2 = SQLCommand.getposttitle;
        Cursor cursor2 = DBOperator.getInstance().execQuery(sql2,value);
        StringArray stringArray2 = new StringArray();
        String ars2[][]= stringArray2.toStr(cursor2);
        postTitle = ars2[0][0];

        // mAdapter = new MyAdapter(ars);
        mAdapter = new MajorMyAdapter(getApplicationContext(), this,ars1);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        //System.out.println(position);
        /*
        Intent intent = new Intent(this, Student_detailsMajor.class);
        this.startActivity(intent);
    */
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog dialog;
        LayoutInflater inflater = (LayoutInflater)
        this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v1 = inflater.inflate(R.layout.add_to_wishlist,null);

        alert.setView(v1);
        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String getwishId = SQLCommand.getwdid;
                        String userid[] = new String[1];
                        userid[0] = LoginActivity.user_id;
                        Cursor cursor = DBOperator.getInstance().execQuery(getwishId, userid);
                        StringArray stringArray = new StringArray();
                        String ars[][] = stringArray.toStr(cursor);
                        String wishId = Integer.toString(Integer.parseInt(ars[0][0]));

                        int count1;
                        String getwdid = SQLCommand.getwdid;
                        Cursor cursor1 = DBOperator.getInstance().execQuery(getwdid, null);
                        StringArray stringArray1 = new StringArray();
                        String ars1[][] = stringArray1.toStr(cursor1);
                        count1 = Integer.parseInt(ars1[0][0]);
                        count1 = count1 + 1;

                        String sql = SQLCommand.addtowishlist;
                        String[] value = new String[3];
                        value[0] = Integer.toString(count1);
                        value[1] = wishId;
                        value[2] = itemId;
                        DBOperator.getInstance().execSQL(sql, value);



                        Toast.makeText(MajorList.this, itemName + " from " + postTitle + " has been added to Your Wishlist", Toast.LENGTH_SHORT).show();
                    }
                });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        dialog = alert.create();
        dialog.show();

        }


    }


