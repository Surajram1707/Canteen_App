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

public class Signup extends AppCompatActivity implements View.OnClickListener{
    Button add,rem,view1,update,login1;
    TextView usern,userpwd,email;
    EditText name,pass,mail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        add = (Button)findViewById(R.id.add);
        rem = (Button)findViewById(R.id.remove);
        login1 = (Button)findViewById(R.id.loginb2);
        view1 = (Button)findViewById(R.id.view);
        update = (Button)findViewById(R.id.Update);
        usern = (TextView)findViewById(R.id.name);
        userpwd = (TextView) findViewById(R.id.passd);
        email = (TextView)findViewById(R.id.eid);
        name = (EditText)findViewById(R.id.uname1);
        pass = (EditText)findViewById(R.id.upwd);
        mail = (EditText)findViewById(R.id.eid1);
        add.setOnClickListener(this);
        login1.setOnClickListener(this);
        rem.setOnClickListener(this);
        update.setOnClickListener(this);
        view1.setOnClickListener(this);
        db=openOrCreateDatabase("CanteenDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,mail VARCHAR,pass VARCHAR);");

    }
    public void onClick(View view)
    {
        if (view == login1){
            Intent intent=new Intent(Signup.this,MainActivity.class);
            startActivity(intent);
        }
        if (view == add ){

            if(name.getText().toString().trim().length()==0||
                    mail.getText().toString().trim().length()==0||
                    pass.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO users VALUES('"+name.getText()+
                    "','"+mail.getText()+"','"+pass.getText()+"');");
            showMessage("Success", "User added");
            clearText();

        }
        if(view == rem){
            if(name.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter UserName");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM users WHERE name='"
                    +name.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM users WHERE name='"+name.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid UserName");
            }
            clearText();

        }
        if (view == update){
            if(name.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter UserName");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM users WHERE name='"
                    +name.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE users SET mail='" + mail.getText() + "',pass='" +
                        pass.getText() +"' WHERE name='"+name.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid UserName");
            }
            clearText();

        }
        if(view== view1) {

            // Checking for empty name
            if (name.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter UserName");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM users WHERE name='"
                    + name.getText() + "'", null);
            if (c.moveToFirst()) {
                name.setText(c.getString(0));
                mail.setText(c.getString(1));
                pass.setText("....");
            } else {
                showMessage("Error", "Invalid UserName");
                clearText();
            }
        }
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
        name.setText("");
        mail.setText("");
        pass.setText("");
        name.requestFocus();
    }


}

