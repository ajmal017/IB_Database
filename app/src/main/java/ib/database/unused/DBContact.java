package ib.database.unused;

/**
 * Created by Chen on 11/6/2016.
 */
public class DBContact {
    int st_id;
    String  st_email, pass,st_first_name,st_last_name;

    public void setId(int st_id){
        this.st_id=st_id;
    }

    public int getId(){
        return this.st_id;
    }

    public void setfirstName(String st_first_name){
        this.st_first_name=st_first_name;
    }

    public String getfirstName(){
        return this.st_first_name;
    }

    public void setlastName(String st_last_name){
        this.st_last_name=st_last_name;
    }

    public String getlastName(){
        return this.st_last_name;
    }
    public void setEmail(String st_email){
        this.st_email=st_email;
    }

    public String getEmail(){
        return this.st_email;
    }


    public void setPass(String pass){
        this.pass=pass;
    }

    public String getPass(){
        return this.pass;
    }
}
