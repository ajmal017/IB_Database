package ib.database;

/**
 * Created by Ankita on 11/18/2016.
 */
import ib.database.util.DBOperator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowStock extends Activity {
    private ListView listView;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_stock);

        listView = (ListView) this.findViewById(R.id.master_stock);
        listView.setOnItemClickListener(new ItemClickListener());

        // get the sql string delivered from the QueryActivity
        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        // bind the data to list
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.master_stock, cursor,
                new String[] { "mskid", "mstkname", "mstkprice" }, new int[] {
                R.id.mstk_id, R.id.mstkname, R.id.mstkprice },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
    }

    private class ItemClickListener implements OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String mstk_id = cursor.getString(0);
            String mstkname = cursor.getString(1);
            String mstkprice = cursor.getString(2);

            Toast.makeText(getApplicationContext(),"Master Stock ID: " + mstk_id + "\nMaster Stock Name: " + mstkname+ "\nMaster Stock Price: " + mstkprice, Toast.LENGTH_LONG).show();
        }
    }
}



