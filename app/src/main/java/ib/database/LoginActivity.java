package ib.database;

/**
 * Created by Chen on 10/26/2016.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ib.database.util.DBOperator;


public class LoginActivity extends AppCompatActivity {

    //test
    EditText et1,et2;
    public static String user_id;
    DBHelper helper = new DBHelper(this);
    protected void onCreate(Bundle savedInstanceState) {
        //System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);

        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

        //Font on LoginActivity page
        TextView tv=(TextView)findViewById(R.id.textView);
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/jm.ttf");
        tv.setTypeface(face);

        et1 = (EditText) this.findViewById(R.id.userName);
        et2 = (EditText) this.findViewById(R.id.userPass);

    }

    public void onClick(View view)
    {
        DBOperator dbOperator= new DBOperator();
        if (view.getId()==R.id.login_button) {

            //attemptLogin();
            //Intent intent = new Intent(this, MainActivity.class);
            //this.startActivity(intent);
            EditText uname = (EditText)findViewById(R.id.userName);
            String uname_str = uname.getText().toString();
            EditText upass = (EditText)findViewById(R.id.userPass);
            String upass_str = upass.getText().toString();
            View focusView = null;
            boolean cancel = false;

            String pword = dbOperator.searchPass(uname_str);
            System.out.println("Required Password = "+pword);
            System.out.println("Entered Password = "+upass_str);

            // Check for a valid username.
            if (TextUtils.isEmpty(uname_str)) {
                et1.setError(getString(R.string.error_enter_username));
                focusView = et1;
                cancel = true;
            }
            else
            if (TextUtils.isEmpty(upass_str)) {
                et2.setError(getString(R.string.error_enter_password));
                focusView = et2;
                cancel = true;
            }
            else
            if(upass_str.equals(pword)){
                user_id=DBOperator.c;
                Intent intent = new Intent(this, DashboardActivity.class);
                this.startActivity(intent);
                /*et1.setText("");
                et2.setText("");
                finish();*/

            }else
            {
                et2.setError(getString(R.string.error_incorrect_password));
            }
            /*else if(isPasswordInvalid(upass_Str)){
                et2.setError(getString(R.string.error_incorrect_password));
                focusView = et2;
                cancel=true;
            }*/
            if (cancel) {
                // There was an error; don't attempt LoginActivity1 and focus the first
                // form field with an error.
                focusView.requestFocus();

          /*  else if (uname_Str.isEmpty()){
                et2.setError(getString(R.string.error_invalid_password);
                //Toast temp1 = Toast.makeText(LoginActivity1.this,"Please enter Username & Password",Toast.LENGTH_SHORT);
                //temp1.show();
            }else if(upass_Str.isEmpty()){
                et2.setError(getString(R.string.error_invalid_password);
                focusView = et2;
                cancel = true;
            }


            else {
                Toast temp = Toast.makeText(LoginActivity1.this,"Username & Password don't match",Toast.LENGTH_SHORT);
                temp.show();
            }*/

            }
        }}

   /* public void onForgotClick(View v){
        if(v.getId()==R.id.forgot_pass){
            Intent it1 = new Intent(LoginActivity1.this,ForgotPassword.class);
            startActivity(it1);
        }
    }


    public void onSignupClick(View v){
        if (v.getId()==R.id.btn_signup){
            Intent intent = new Intent(LoginActivity1.this, SignUp.class);
            startActivity(intent);
        }
    }*/








/*




    private void attemptLogin() {
        et1.setError(null);
        et2.setError(null);
        String username = et1.getText().toString();
        String password = et2.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(password) ) {
            et2.setError(getString(R.string.error_enter_password));
            focusView = et2;
            cancel = true;
        }else if(isPasswordInvalid(password)){
            et2.setError(getString(R.string.error_incorrect_password));
            focusView = et2;
            cancel=true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            et1.setError(getString(R.string.error_enter_username));
            focusView = et1;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt LoginActivity1 and focus the first
            // form field with an error.
            focusView.requestFocus();
        }else {
            nextpage();
        }
    }
    public boolean isPasswordInvalid(String password) {
        //TODO: Replace this with your own logic
        //return password.length() > 0;
            if (et1.getText().toString().trim().equals("yijia")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("ankita")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("si")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("xinjie")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("catherine")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("qian")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            }else {
                return true;
            }
    }
*/


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
