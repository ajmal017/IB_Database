package ib.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;

/**
 * Created by Chen on 12/8/2016.
 */
public class AddNewItem extends AppCompatActivity implements View.OnClickListener {
    private EditText item_name;
    private EditText item_qoh;
    private EditText item_price;
    private EditText item_desc;
    private Button post;
    private Button back;
    private Spinner querySpinner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        item_name = (EditText) this.findViewById(R.id.item_name);
        item_qoh = (EditText) this.findViewById(R.id.item_qoh);
        item_price = (EditText) this.findViewById(R.id.item_price);
        item_desc = (EditText) this.findViewById(R.id.item_desc);

        post = (Button) this.findViewById(R.id.post);
        post.setOnClickListener(this);

        back = (Button) this.findViewById(R.id.home);
        back.setOnClickListener(this);

        querySpinner = (Spinner) this.findViewById(R.id.spinner_item_cat);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        String cat_id = null;
        if (id == R.id.post) {

            item_name.setError(null);
            item_price.setError(null);
            item_desc.setError(null);
            //et2.setError(null);
            item_qoh.setError(null);
            String name = item_name.getText().toString().trim();
            String price = item_price.getText().toString().trim();
            String desc = item_desc.getText().toString().trim();
            String qoh = item_qoh.getText().toString().trim();

            View focusView = null;
            boolean cancel = false;
            if (TextUtils.isEmpty(name)) {
                item_name.setError("Please Enter Item Name");
                focusView = item_name;
                cancel = true;
            } else if (TextUtils.isEmpty(qoh)) {
                item_qoh.setError("Please Enter Item Quantity");
                focusView = item_qoh;
                cancel = true;
            } else if (TextUtils.isEmpty(price)) {
                item_price.setError("Please Enter Item Price");
                focusView = item_price;
                cancel = true;
            } else if (TextUtils.isEmpty(desc)) {
                item_desc.setError("Please Enter Item Description");
                focusView = item_desc;
                cancel = true;
            } else if (cancel) {
                // There was an error; don't attempt LoginActivity1 and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {


                //show query result
                int pos = querySpinner.getSelectedItemPosition();
                if (pos == 0) {
                    //show all books
                    cat_id = "300";
                } else if (pos == 1) {
                    //list the call numbers of books with the title ‘Database Management’
                    cat_id = "301";
                } else if (pos == 2) {
                    cat_id = "302";
                } else if (pos == 3) {
                    cat_id = "303";
                } else if (pos == 4) {
                    cat_id = "304";
                }

                //Post Creation
                int post_hit_counter = 1;
                String count = SQLCommand.getpostid;
                Cursor cursor = DBOperator.getInstance().execQuery(count);
                StringArray stringArray = new StringArray();
                String ars[][] = stringArray.toStr(cursor);
                int count1 = Integer.parseInt(ars[0][0]);
                System.out.println("Original PostId"+count1);
                count1 = count1 + 1;
                System.out.println("New PostId"+count1);

                String value[] = new String[5];
                value[0] = Integer.toString(count1);
                value[1] = SellPage.title;
                value[2] = SellPage.description;
                value[3] = Integer.toString(post_hit_counter);
                value[4] = LoginActivity.user_id;

                String sql = SQLCommand.insertpost;
                DBOperator.getInstance().execSQL(sql,value);

                //Item Addition to Post
                int item_id;
                String count2 = SQLCommand.getitemid;
                Cursor cursor2 = DBOperator.getInstance().execQuery(count2);
                StringArray stringArray2 = new StringArray();
                String ars2[][] = stringArray2.toStr(cursor2);
                item_id = Integer.parseInt(ars2[0][0]);
                System.out.println("Original ItemID="+item_id);
                item_id = item_id + 1;
                System.out.println("New ItemID="+item_id);

                String value2[] = new String[7];
                value2[0] = Integer.toString(item_id);
                value2[1] = Integer.toString(count1);
                value2[2] = cat_id;
                value2[3] = item_name.getText().toString();
                value2[4] = item_qoh.getText().toString();
                value2[5] = item_price.getText().toString();
                value2[6] = item_desc.getText().toString();



                String sql2 = SQLCommand.insertitem;
                System.out.println(sql2);
                DBOperator.getInstance().execSQL(sql2,value2);

                Toast.makeText(getApplicationContext(), "Item Successfully Posted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, AddNewItem.class);
                this.startActivity(intent);
                finish();

            }
        }

            if (id == R.id.home)
            {
                Intent intent = new Intent(this, DashboardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                finish();
            }

    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

}
