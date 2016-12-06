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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;

import static ib.database.R.id.editText;
import static ib.database.R.id.showDesc;

/**
 * Created by Chen on 11/7/2016.
 */
public class showStockDetail extends AppCompatActivity
//        implements View.OnClickListener
{

    private ListView listView;
    private String item_name;
    private TextView showName, showPrice, showIndustry, showMarket, showDesc;
    private String stock_name, stock_price, industry_type, related_market, stock_description, stock_id;
    private Button Updatebtn, AddtoWishBtn;
    private static Integer contactnotif = 0;

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showstockdetail);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        /*listView = (ListView) this.findViewById(R.id.post_details);
        listView.setOnItemClickListener(new ItemClickListener());*/
        showName = (TextView) this.findViewById(R.id.showName);
        showPrice = (TextView) this.findViewById(R.id.showPrice);
        showIndustry = (TextView) this.findViewById(R.id.showIndustry);
        showMarket = (TextView) this.findViewById(R.id.showMarket);
        showDesc = (TextView) this.findViewById(R.id.showDesc);
        Updatebtn = (Button) this.findViewById(R.id.button2);
        AddtoWishBtn = (Button) this.findViewById(R.id.button3);

        AddtoWishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Add to favorite", Toast.LENGTH_SHORT).show();
                String sql=SQLCommand.addwishlist;

                DBOperator.getInstance().execSQL(sql);

            }
        });

        Intent intent = this.getIntent();
        stock_name = intent.getStringExtra("stock_name");
        stock_price = intent.getStringExtra("stock_price");
        industry_type = intent.getStringExtra("industry_type");
        related_market = intent.getStringExtra("related_market");
        stock_description = intent.getStringExtra("stock_description");
        stock_id = intent.getStringExtra("stock_id");

        String value[] = new String[1];
        value[0] = stock_id;
        /*
        //Get Post Details using the PostId from ShowBuyList or History
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.getpostdetails, value);
        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String related_market = ars [0][1];
        String stock_description = ars [0][2];
        showName.setText(related_market);
        showPrice.setText(stock_description);*/

        showName.setText(stock_name);
        showPrice.setText(stock_price);
        showIndustry.setText(industry_type);
        showMarket.setText(related_market);
        showDesc.setText(stock_description);


        if (ShowBuyListActivity.BLFlag == 1) {
            //UpdatePost(view, item_id);
//            AddtoWishlistDialog(stock_id);
            ShowBuyListActivity.BLFlag = 0;
            ShowSellList.BLFlag=0;
            AddtoWishBtn.setVisibility(View.VISIBLE);

        } else if (ShowSellList.BLFlag == 1) {
            ShowSellList.BLFlag = 0;
            ShowBuyListActivity.BLFlag=0;
            AddtoWishBtn.setVisibility(View.GONE);
        }
    }


    public void onClick(View view) {
        //String sql="";
        int id = view.getId();
        if (id == R.id.button3) {

            Toast.makeText(getApplicationContext(),"Add to favorite", Toast.LENGTH_SHORT).show();
            String sql=SQLCommand.addwishlist;

            DBOperator.getInstance().execSQL(sql);


        }
    }
   /* public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.button2)
        {
            String value[]=new String[1];
//            value[0]= editText.getText().toString();


            String sql=SQLCommand.buy;
            System.out.println(sql);
            DBOperator.getInstance().execSQL(sql, value);
            Toast.makeText(getApplicationContext(),"Buying Updated", Toast.LENGTH_SHORT).show();
        }
        else if (id==R.id.button3){}
    }
*/






    //Get Item Details and Display them
        /*Cursor cursor2 = DBOperator.getInstance().execQuery(SQLCommand.getitemdetails, value);
        String[] from = new String[]{"item_name","item_desc","item_price"};
        int[] to = new int[]{R.id.Favstock_name, R.id.item_desc,R.id.Favstock_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.post_selected_layout, cursor2,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if(contactnotif==0)
        {
            Toast.makeText(showstockdetail.this,"You can click on the Contact Number to Directly call the Seller", Toast.LENGTH_SHORT).show();
            contactnotif = 1;
        }*/

}

    /*@Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.contact_details)
        {
            //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("Phone:", industry_type, null));
            String uri = "tel:" + industry_type.trim() ;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            startActivity(intent);
        }
    }*/

//    private class ItemClickListener implements AdapterView.OnItemClickListener {
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//            String stock_id = cursor.getString(0);
//            String stock_name1 = cursor.getString(1);
//            stock_name = stock_name1;
//            /*int count1,count2;
//            String [] value = new String[1];
//            value[0] = stock_id;
//
//            String getwdid= SQLCommand.getwdid;
//            Cursor cursor3 = DBOperator.getInstance().execQuery(getwdid);
//            StringArray stringArray3 = new StringArray();
//            String ars[][]= stringArray3.toStr(cursor3);
//            count1=Integer.parseInt(ars[0][0]);
//            count1=count1+1;
//
//            System.out.println(count1);*/
//            String value [] = new String[1];
//            value [0] = stock_id;
//            //Update or Delete Button Condition
//            Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getuserid,value);
//            StringArray stringArray1 = new StringArray();
//            String ars1[][]= stringArray1.toStr(cursor1);
//            String post_user_id = ars1 [0][0];
//
//            System.out.println("Queried User ID" + post_user_id);
//            System.out.println("Logged in User ID"+LoginActivity.user_id);
//
//

//
//            else
//            {
//                Toast.makeText(showStockDetail.this, item_name + "already exists in your favorite", Toast.LENGTH_SHORT).show();
//            }
//           /* String getwishid= SQLCommand.getwishid;
//            String bp_id[] = new String[1];
//            userid[0] = LoginActivity.bp_id;
//            Cursor cursor2 = DBOperator.getInstance().execQuery(getwishid,bp_id);
//            StringArray stringArray2 = new StringArray();
//            String ars2[][]= stringArray2.toStr(cursor2);
//            count2=Integer.parseInt(ars2[0][0]);*/
//
//
//        }
//    }
//
//
//  //  private void UpdatePost(View view, final String item_id) {
//
//        //System.out.print("Item ID: "+item_id);
//
//    private void UpdatePost(final String item_id) {
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        final AlertDialog dialog;
//        LayoutInflater inflater = (LayoutInflater)
//                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View v2 = inflater.inflate(R.layout.update_item,null);
//
//        alert.setView(v2);
//        alert.setPositiveButton("Update",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        Intent intent = new Intent(getApplicationContext(), UpdateItemDetails.class);
//                        intent.putExtra("item_id", item_id);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//
//        alert.setNegativeButton("Delete",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        String value[] = new String[1];
//                        value[0] = item_id;
//                        DBOperator.getInstance().execSQL(SQLCommand.deleteitem, value);
//
//                        Toast.makeText(showStockDetail.this, item_name + "has been deleted", Toast.LENGTH_SHORT).show();
//                        //Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.deleteitem, value);
//                        //DBOperator.getInstance();
//                        //Toast.makeText(showstockdetail.this, item_id+"has been deleted", Toast.LENGTH_SHORT).show();
//                   }
//
//                });
//        dialog = alert.create();
//        dialog.show();
//    }
//
//    private void AddtoWishlistDialog(final String id) {
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        final AlertDialog dialog;
//        LayoutInflater inflater = (LayoutInflater)
//                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View v1 = inflater.inflate(R.layout.add_to_wishlist,null);
//
//        alert.setView(v1);
//        alert.setPositiveButton("Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                        //String getwishId = SQLCommand.getwishid;
//                        /*Cursor cursor = DBOperator.getInstance().execQuery(getwishId, userid);
//                        StringArray stringArray = new StringArray();
//                        String ars[][] = stringArray.toStr(cursor);
//                        String wishId = Integer.toString(Integer.parseInt(ars[0][0]));*/
//
//                        int count1;
//                        String getwdid = SQLCommand.getwdid;
//                        Cursor cursor1 = DBOperator.getInstance().execQuery(getwdid,null);
//                        /*StringArray stringArray1 = new StringArray();
//                        String ars1[][] = stringArray1.toStr(cursor1);
//                        count1 = Integer.parseInt(ars1[0][0]);
//                        count1 = count1+1;*/
//                        StringArray stringArray = new StringArray();
//                        String ars[][]= stringArray.toStr(cursor1);
//                        count1=Integer.parseInt(ars[0][0]);
//                        count1=count1+1;
//
//                        String sql = SQLCommand.addtowishlist;
//                        String[] value = new String[3];
//                        value[0] = Integer.toString(count1);
//                        value[1] = LoginActivity.user_id;
//                        value[2] = id;
//                        DBOperator.getInstance().execSQL(sql, value);
//
//
//
//                        Toast.makeText(showStockDetail.this, item_name + " has been added to Your Wishlist", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//            }
//        });
//
//        dialog = alert.create();
//        dialog.show();
//
//    }
//
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
//    }


