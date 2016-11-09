package ib.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;

/**
 * Created by Chen on 11/7/2016.
 */
public class PostSelectedPageTest extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private String item_name;
    private TextView post_title1,post_desc1,seller_name,seller_contact;
    private String user_first_name,user_last_name,user_phone,post_title,post_desc,post_id;
    private static Integer contactnotif=0;

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_click_page);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        listView = (ListView) this.findViewById(R.id.post_details);
        listView.setOnItemClickListener(new ItemClickListener());
        post_title1 =(TextView) this.findViewById(R.id.post_title1);
        post_desc1 =(TextView) this.findViewById(R.id.post_desc1);
        seller_name =(TextView) this.findViewById(R.id.contact_name);
        seller_contact =(TextView) this.findViewById(R.id.contact_details);


        Intent intent = this.getIntent();
        user_first_name =  intent.getStringExtra("user_first_name");
        user_last_name =  intent.getStringExtra("user_last_name");
        user_phone =  intent.getStringExtra("user_phone");
        post_title =  intent.getStringExtra("post_title");
        post_desc =  intent.getStringExtra("post_desc");
        post_id =  intent.getStringExtra("post_id");

        String value [] = new String[1];
        value [0] = post_id;
        /*
        //Get Post Details using the PostId from ShowBuyList or History
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.getpostdetails, value);
        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String post_title = ars [0][1];
        String post_desc = ars [0][2];
        post_title1.setText(post_title);
        post_desc1.setText(post_desc);*/

        post_title1.setText(post_title);
        post_desc1.setText(post_desc);
        //String seller_details = String.
        seller_name.setText(user_first_name+" "+user_last_name);
        seller_contact.setText(user_phone);

        //Get Item Details and Display them
        Cursor cursor2 = DBOperator.getInstance().execQuery(SQLCommand.getitemdetails, value);
        String[] from = new String[]{"item_name","item_desc","item_price"};
        int[] to = new int[]{R.id.item_name, R.id.item_desc,R.id.item_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.post_selected_layout, cursor2,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if(contactnotif==0)
        {
            Toast.makeText(PostSelectedPageTest.this,"You can click on the Contact Number to Directly call the Seller", Toast.LENGTH_SHORT).show();
            contactnotif = 1;
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.contact_details)
        {
            //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("Phone:", user_phone, null));
            String uri = "tel:" + user_phone.trim() ;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            startActivity(intent);
        }
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String item_id = cursor.getString(0);
            String item_name1 = cursor.getString(1);
            item_name = item_name1;
            /*int count1,count2;
            String [] value = new String[1];
            value[0] = item_id;

            String getwdid= SQLCommand.getwdid;
            Cursor cursor3 = DBOperator.getInstance().execQuery(getwdid);
            StringArray stringArray3 = new StringArray();
            String ars[][]= stringArray3.toStr(cursor3);
            count1=Integer.parseInt(ars[0][0]);
            count1=count1+1;

            System.out.println(count1);*/
            String value [] = new String[1];
            value [0] = post_id;
            //Update or Delete Button Condition
            Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getuserid,value);
            StringArray stringArray1 = new StringArray();
            String ars1[][]= stringArray1.toStr(cursor1);
            String post_user_id = ars1 [0][0];

            System.out.println("Queried User ID" + post_user_id);
            System.out.println("Logged in User ID"+LoginActivity.user_id);


            if(ShowBuyListActivity.BLFlag==1||HotDeals1.BLFlag==1||NewDeals1.BLFlag==1|| History.BLFlag==1) {
                if (post_user_id.equals(LoginActivity.user_id)) {
                    //UpdatePost(view, item_id);
                    UpdatePost(item_id);
                    ShowBuyListActivity.BLFlag=0;
                    HotDeals1.BLFlag=0;
                    NewDeals1.BLFlag=0;

                } else {
                    AddtoWishlistDialog(item_id);
                    ShowBuyListActivity.BLFlag=0;
                    HotDeals1.BLFlag=0;
                    NewDeals1.BLFlag=0;
                }
            }
            else
            {
                Toast.makeText(PostSelectedPageTest.this, item_name + "already exists in your favorite", Toast.LENGTH_SHORT).show();
            }
           /* String getwishid= SQLCommand.getwishid;
            String userid[] = new String[1];
            userid[0] = LoginActivity.user_id;
            Cursor cursor2 = DBOperator.getInstance().execQuery(getwishid,userid);
            StringArray stringArray2 = new StringArray();
            String ars2[][]= stringArray2.toStr(cursor2);
            count2=Integer.parseInt(ars2[0][0]);*/


        }
    }


  //  private void UpdatePost(View view, final String item_id) {

        //System.out.print("Item ID: "+item_id);

    private void UpdatePost(final String item_id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog dialog;
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v2 = inflater.inflate(R.layout.update_item,null);

        alert.setView(v2);
        alert.setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(getApplicationContext(), UpdateItemDetails.class);
                        intent.putExtra("item_id", item_id);
                        startActivity(intent);
                        finish();
                    }
                });

        alert.setNegativeButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value[] = new String[1];
                        value[0] = item_id;
                        DBOperator.getInstance().execSQL(SQLCommand.deleteitem, value);

                        Toast.makeText(PostSelectedPageTest.this, item_name + "has been deleted", Toast.LENGTH_SHORT).show();
                        //Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.deleteitem, value);
                        //DBOperator.getInstance();
                        //Toast.makeText(PostSelectedPageTest.this, item_id+"has been deleted", Toast.LENGTH_SHORT).show();
                   }

                });
        dialog = alert.create();
        dialog.show();
    }

    private void AddtoWishlistDialog(final String id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog dialog;
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v1 = inflater.inflate(R.layout.add_to_wishlist,null);

        alert.setView(v1);
        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        //String getwishId = SQLCommand.getwishid;
                        /*Cursor cursor = DBOperator.getInstance().execQuery(getwishId, userid);
                        StringArray stringArray = new StringArray();
                        String ars[][] = stringArray.toStr(cursor);
                        String wishId = Integer.toString(Integer.parseInt(ars[0][0]));*/

                        int count1;
                        String getwdid = SQLCommand.getwdid;
                        Cursor cursor1 = DBOperator.getInstance().execQuery(getwdid,null);
                        /*StringArray stringArray1 = new StringArray();
                        String ars1[][] = stringArray1.toStr(cursor1);
                        count1 = Integer.parseInt(ars1[0][0]);
                        count1 = count1+1;*/
                        StringArray stringArray = new StringArray();
                        String ars[][]= stringArray.toStr(cursor1);
                        count1=Integer.parseInt(ars[0][0]);
                        count1=count1+1;

                        String sql = SQLCommand.addtowishlist;
                        String[] value = new String[3];
                        value[0] = Integer.toString(count1);
                        value[1] = LoginActivity.user_id;
                        value[2] = id;
                        DBOperator.getInstance().execSQL(sql, value);



                        Toast.makeText(PostSelectedPageTest.this, item_name + " has been added to Your Wishlist", Toast.LENGTH_SHORT).show();
                    }
                });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        dialog = alert.create();
        dialog.show();

    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

}
