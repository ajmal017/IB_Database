package ib.database;

        import ib.database.constant.SQLCommand;
        import ib.database.util.DBOperator;
        import ib.database.view.TableView;
        import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.ScrollView;
        import android.widget.Spinner;
        import android.widget.Toast;

public class MasterStock extends Activity implements OnClickListener {

    Button backBtn,showlistBtn;
    ScrollView scrollView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_stock);
        //copy database file
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        backBtn=(Button)this.findViewById(R.id.goback_btn);
        backBtn.setOnClickListener(this);
        showlistBtn=(Button)this.findViewById(R.id.check_btn);
        showlistBtn.setOnClickListener(this);
        scrollView=(ScrollView)this.findViewById(R.id.scrollView);
    }

    public void onClick(View v)
    {
        String sql="";
        int id=v.getId();
        if (id == R.id.check_btn) {
            // show checkout list result

            sql = SQLCommand.MASTER_STOCK;
            Intent intent = new Intent(getApplicationContext(),
                    ShowStock.class);
            intent.putExtra("sql", sql);
            startActivity(intent);
        }

        else if (id==R.id.goback_btn){
            //go back to main screen
            Intent intent = new Intent(this, ProfilePage.class);
            this.startActivity(intent);
        }

        }

    }

