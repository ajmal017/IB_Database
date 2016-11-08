package ib.database.unused;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ib.database.LoginActivity;
import ib.database.R;

/**
 * Created by Chen on 11/6/2015.
 */
public class ForgotPassword extends Activity {

    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);
        TextView tv=(TextView)findViewById(R.id.textView);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Vizel.otf");
        tv.setTypeface(face);
    }

    public void onForgotClick(View v){

        if(v.getId()==R.id.sub_button){
            Toast pass2 = Toast.makeText(ForgotPassword.this,"Password sent to registerd Email",Toast.LENGTH_SHORT);
            pass2.show();
            Intent it1 = new Intent(ForgotPassword.this,LoginActivity.class);
            startActivity(it1);
        }
    }*/
}
