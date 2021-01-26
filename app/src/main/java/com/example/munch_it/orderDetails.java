package com.example.munch_it;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
//Declaration of variables and buttons
public class orderDetails extends AppCompatActivity {
    TextView ordered__list,bdt_price_view,show_price_view,orderlistText;
    TextToSpeech t1;
    EditText rollno;
    String bdt_price,usd_price,u_name;
    Button b1,b2;
    SQLiteDatabase db;
    FirebaseDatabase df = FirebaseDatabase.getInstance();
    DatabaseReference myRef=df.getReference("Order Details");
    long maxnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        b1= findViewById(R.id.listen);
        b2= findViewById(R.id.btncon);
        rollno=findViewById(R.id.roll);

        db=openOrCreateDatabase("CanteenDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,mail VARCHAR,pass VARCHAR);");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                     maxnum=(dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Bundle bundle = getIntent().getExtras();
        String data= bundle.getString("choice_iteams");
        bdt_price= bundle.getString("bdt_price");
       // usd_price = bundle.getString("usd_price");
        Intent i=getIntent();
       // u_name=i.getStringExtra("USER_NAME");




        orderlistText = findViewById(R.id.orderlistTextview);
        ordered__list = findViewById(R.id.orderDetailstextView);


        show_price_view = findViewById(R.id.showprice);
        show_price_view.setText("TOTAL : Rs"+bdt_price+" \n");

        ordered__list.setText(data);








        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = ordered__list.getText().toString();
                t1.setPitch(0.8f);
                t1.setSpeechRate(0.8f);

                //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moving();

            }
        });




    }



    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }




    public void moving(){

        String oddet=ordered__list.getText().toString();



        String amt=show_price_view.getText().toString();
        String roll=rollno.getText().toString();

       // Cursor c=db.rawQuery("SELECT * from users WHERE name='");
        if(!TextUtils.isEmpty(oddet)&&!TextUtils.isEmpty(amt)&&!TextUtils.isEmpty(roll))
        {

            SSS orders=new SSS(roll,oddet,amt);

           myRef.child(String.valueOf(maxnum+1)).setValue(orders);
            Toast.makeText(orderDetails.this,"Order successful.Thank you for ordering.",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(orderDetails.this,"Please  go back and select your items or enter your name.",Toast.LENGTH_LONG).show();
        }



    }



}







