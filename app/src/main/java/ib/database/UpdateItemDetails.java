package ib.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;
/**
 * Created by Chen on 11/8/2016.
 */
public class UpdateItemDetails extends AppCompatActivity implements View.OnClickListener {

    private EditText update_item_name;
    private EditText update_item_quantity;
    private EditText update_item_price;
    private EditText update_item_description;
    private Spinner set_item_category;
    private String item_id;
    private int update_item_category;

    protected void onCreate(Bundle savedInstanceState)
    {
        //System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item_details);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        update_item_name = (EditText) this.findViewById(R.id.txt_update_item_name);
        update_item_quantity = (EditText) this.findViewById(R.id.txt_update_item_quantity);
        update_item_price = (EditText) this.findViewById(R.id.txt_update_item_price);
        update_item_description = (EditText) this.findViewById(R.id.txt_update_item_description);
        set_item_category = (Spinner) this.findViewById(R.id.spn_update_item_category);


        Intent intent = this.getIntent();
        item_id = intent.getStringExtra("item_id");
        System.out.println("Item ID_Update: "+item_id);
        //temp_item_id = item_id;
        String[] value= new String[1];
        value[0] = item_id;
        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getitemdetails_updatePage, value);
        StringArray stringArray = new StringArray();
        String itemDetails[][] = stringArray.toStr(cursor1);
        /*int index = 0;
        for(int i=0; i<update_item_category.getCount(); i++)
        {
            if(update_item_category.getItemAtPosition(i).toString().equals(itemDetails[0][4]))
                index = i;
        }*/

        update_item_name.setText(itemDetails[0][0]);
        update_item_quantity.setText(itemDetails[0][1]);
        update_item_price.setText(itemDetails[0][2]);
        update_item_description.setText(itemDetails[0][3]);
        update_item_category = Integer.parseInt(itemDetails[0][4]);
        int pos=-1;
        if(update_item_category == 300)
        {
            pos = 0;
        }
        else if(update_item_category == 301)
        {
            pos = 1;
        }
        else if(update_item_category == 302)
        {
            pos = 2;
        }
        else if(update_item_category == 303)
        {
            pos = 3;
        }
        set_item_category.setSelection(pos);

    }

    public void onClick(View view)
    {
        int id=view.getId();
        if (id==R.id.btn_home_update)
        {
            Intent intent = new Intent(this, DashboardActivity.class);
            this.startActivity(intent);
        }

        if(id==R.id.btn_update_post)
        {
            String[] value= new String[6];
            value[0] = update_item_name.getText().toString().trim();
            value[1] = update_item_quantity.getText().toString().trim();
            value[2] = update_item_price.getText().toString().trim();
            value[3] = update_item_description.getText().toString().trim();

            /*String[] tempCat = new String[1];
            tempCat[0]= update_item_category.getAdapter().toString();
            Cursor cursor1 = DBOperator.getInstance().execQuery("select cat_id from category where cat_name=?", tempCat);
            StringArray stringArray1 = new StringArray();

            String[][] category = stringArray1.toStr(cursor1);

            /*if(category[0][0]==null)
                System.out.println("category[][] is null");*/

           // System.out.println("Category ID: "+category[0][0]);
            //int c = Integer.parseInt(category[0][0]);
            int pos = set_item_category.getSelectedItemPosition();
            if(pos == 0)
            {
                update_item_category = 300;
            }
            else if(pos == 1)
            {
                update_item_category = 301;
            }
            else if(pos == 2)
            {
                update_item_category = 302;
            }
            else if(pos == 3)
            {
                update_item_category = 303;
            }

            value[4] = Integer.toString(update_item_category);
            value[5] = item_id;
            DBOperator.getInstance().execSQL(SQLCommand.updateItemDetails, value);
            Toast.makeText(getApplicationContext(), "Item Details Successfully Updated", Toast.LENGTH_SHORT).show();
            /*StringArray stringArray2 = new StringArray();
            String itemDetails[][] = stringArray2.toStr(cursor2);*/
        }

    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
