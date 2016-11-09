package ib.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ib.database.constant.SQLCommand;
import ib.database.util.DBOperator;


/**
 * Created by Chen on 11/6/2016.
 */
public class UpdateEventDetails extends AppCompatActivity implements View.OnClickListener {

    private EditText event_title1;
    private EditText event_desc1;
    private String event_id,event_title,event_desc;


    protected void onCreate(Bundle savedInstanceState) {
        //System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_event_details);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        event_title1 = (EditText) this.findViewById(R.id.event_title);
        event_desc1 = (EditText) this.findViewById(R.id.event_desc);


        Intent intent = this.getIntent();
        event_id =  intent.getStringExtra("event_id");
        event_title = intent.getStringExtra("event_title");
        event_desc = intent.getStringExtra("event_desc");

        event_title1.setText(event_title);
        event_desc1.setText(event_desc);
    }


    @Override
    public void onClick(View v) {
        String value[] = new String[3];

        value[0] = event_title1.getText().toString().trim();
        value[1] = event_desc1.getText().toString().trim();
        value[2] = event_id.trim();

        System.out.println("event_title1 = " + value[0]);
        System.out.println("event_desc1 = " + value[1]);
        System.out.println("event id = " +value[2]);

        String sql = SQLCommand.updateEventDetails;
        DBOperator.getInstance().execSQL(sql,value);

        Toast.makeText(getApplicationContext(), "Event Details Successfully Updated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DashboardActivity.class);
        this.startActivity(intent);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}