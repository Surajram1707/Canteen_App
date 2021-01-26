package com.example.munch_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login,signup;
    TextView un,pwd;
    EditText user,passwd;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.loginb);
        signup = (Button)findViewById(R.id.signb);
        un = (TextView)findViewById(R.id.uname);
        pwd = (TextView)findViewById(R.id.pass);
        user = (EditText)findViewById(R.id.uname1);
        passwd = (EditText) findViewById(R.id.pass1);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        db=openOrCreateDatabase("CanteenDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,mail VARCHAR,pass VARCHAR);");



    }
    public void onClick(View view1)
    {
        if(view1 == login){
            if(user.getText().toString().trim().length()==0||
                    passwd.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                clearText();
            }
            db=openOrCreateDatabase("CanteenDB", Context.MODE_PRIVATE, null);
            Cursor c=db.rawQuery("SELECT * from users WHERE name='" + user.getText() + "'AND pass='" + passwd.getText() +"';", null);
            if(c.moveToFirst())
            {
                showMessage("Success","Login Successful");
                menupage();

            }
            else
            {
                showMessage("Error", "Invalid Login Details");
                clearText();
            }
        }
        if(view1 == signup){
            Intent intent=new Intent(MainActivity.this,Signup.class);
            startActivity(intent);
        }
    }
    public void menupage(){
        Intent intent1=new Intent(MainActivity.this,menu.class);

        intent1.putExtra("USER_NAME","user");
        startActivity(intent1);
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        user.setText("");
        passwd.setText("");
        user.requestFocus();
    }
}
