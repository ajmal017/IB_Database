package ib.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;



/**
 * Created by YijiaChen on 10/20/2016.
 */
public class LoginActivity extends AppCompatActivity {

    EditText et1,et2;
    //&& !isPasswordValid(password)

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1 = (EditText) this.findViewById(R.id.editText);
        et2 = (EditText) this.findViewById(R.id.editText2);
    }

    public void onClick(View view)
    {
        nextpage();
        attemptLogin();
    }

    private void attemptLogin() {
        et1.setError(null);
        et2.setError(null);
        String username = et1.getText().toString();
        String password = et2.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(password) ) {
            et2.setError(getString(R.string.error_invalid_password));
            focusView = et2;
            cancel = true;
        }else if(isPasswordInvalid(password)){
            et2.setError(getString(R.string.error_incorrect_password));
            focusView = et2;
            cancel=true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            et1.setError(getString(R.string.error_field_required));
            focusView = et1;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }else {
            nextpage();
        }
    }
    public boolean isPasswordInvalid(String password) {
        //return password.length() > 0;
        if (et1.getText().toString().trim().equals("yijia")
                && et2.getText().toString().trim().equals("123")) {
            return false;
        } else if (et1.getText().toString().trim().equals("ankita")
                && et2.getText().toString().trim().equals("123")) {
            return false;
        } else if (et1.getText().toString().trim().equals("catherine")
                && et2.getText().toString().trim().equals("123")) {
            return false;
        } else if (et1.getText().toString().trim().equals("xinjie")
                && et2.getText().toString().trim().equals("123")) {
            return false;
        } else if (et1.getText().toString().trim().equals("si")
                && et2.getText().toString().trim().equals("123")) {
            return false;
        } else if (et1.getText().toString().trim().equals("qian")
                && et2.getText().toString().trim().equals("a")) {
            return false;
        }else {
            return true;
        }
    }


    //public void login_check() {

    //}

    public void nextpage()
    {
        Intent intent = new Intent(this, DashboardActivity.class);
        this.startActivity(intent);
    }
}

