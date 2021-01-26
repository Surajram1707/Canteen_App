package com.example.munch_it;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class menu extends AppCompatActivity {
    TextView menu, htv, btv, ftv, rtv, fitv, dtv;
    TextView hptv, bptv, fptv, rptv, fiptv, dptv;
    String choices = "";
    String prices, usd_price_string,USER_NAME;
    double usd_price = 0;
    Button biriyani, haleem, fried, plain, firni, drinks;

    //removing button

    Button rm_biriyani, rm_haleem, rm_fried, rm_firni, rm_plain, rm_drinks, order;

    int bp = 0, hp = 0, pp = 0, drp = 0, frp = 0, fip = 0;
    int tbp = 0, thp = 0, tpp = 0, tdrp = 0, tfrp = 0, tfip = 0;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent1=getIntent();
        USER_NAME=intent1.getStringExtra("USER_NAME");

        menu = findViewById(R.id.menuTv);
        htv = findViewById(R.id.haleem_textView);
        btv = findViewById(R.id.biriyani_textView);
        ftv = findViewById(R.id.fried_textView);
        rtv = findViewById(R.id.plain_textView);
        fitv = findViewById(R.id.firni_textView);
        dtv = findViewById(R.id.drinks_textView);
//button of iteams add

        biriyani = (Button) findViewById(R.id.biriyani_button);
        haleem = (Button) findViewById(R.id.haleem_button);
        fried = (Button) findViewById(R.id.fried_button);
        plain = (Button) findViewById(R.id.plain_button);
        firni = (Button) findViewById(R.id.firni_button);
        drinks = (Button) findViewById(R.id.drinks_button);

        // removing button identifing

        rm_biriyani = (Button) findViewById(R.id.biriyani_button_rm);
        rm_haleem = (Button) findViewById(R.id.haleem_button_rm);
        rm_fried = (Button) findViewById(R.id.fried_button_rm);
        rm_firni = (Button) findViewById(R.id.firni_button_rm);
        rm_plain = (Button) findViewById(R.id.plain_button_rm);
        rm_drinks = (Button) findViewById(R.id.drinks_button_rm);


        /// order Button

        order = (Button) findViewById(R.id.order_button);


        hptv = findViewById(R.id.haleem_price);
        bptv = findViewById(R.id.biriyani_price);
        fptv = findViewById(R.id.fried_price);
        rptv = findViewById(R.id.plain_price);
        fiptv = findViewById(R.id.firni_price);
        dptv = findViewById(R.id.drinks_price);

        /*Typeface french_font = ResourcesCompat.getFont(this, R.font.french);
        Typeface gatholic = ResourcesCompat.getFont(this, R.font.gatholic);

        menu.setTypeface(french_font);

        htv.setTypeface(french_font);
        btv.setTypeface(french_font);
        ftv.setTypeface(french_font);
        rtv.setTypeface(french_font);
        fitv.setTypeface(french_font);
        dtv.setTypeface(french_font);


        hptv.setTypeface(gatholic);
        bptv.setTypeface(gatholic);
        fptv.setTypeface(gatholic);
        rptv.setTypeface(gatholic);
        fiptv.setTypeface(gatholic);
        dptv.setTypeface(gatholic);

        // setting order button font

        order.setTypeface(gatholic);*/

    }


    public void place_order(View view) {

        balancesheet();
        Intent i = new Intent(menu.this, orderDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("choice_iteams", choices);
        bundle.putString("bdt_price", prices);
        bundle.putString("usd_price", usd_price_string);
        i.putExtra("USER_NAME",USER_NAME);
        i.putExtras(bundle);
        startActivity(i);
        choices = "";
    }


    public void add_to_list(View view) {



        if (view == findViewById(R.id.haleem_button)) {
            Toast.makeText(this, "Tea Added", Toast.LENGTH_SHORT).show();
            hp = hp + 1;


            haleem.setText(Integer.toString(hp));

        } else if (view == findViewById(R.id.biriyani_button)) {
            Toast.makeText(this, "Full Meals Added", Toast.LENGTH_SHORT).show();

            bp = bp + 1;

            biriyani.setText(Integer.toString(bp));

        } else if (view == findViewById(R.id.fried_button)) {
            Toast.makeText(this, "Veg Puffs Added", Toast.LENGTH_SHORT).show();

            frp = frp + 1;
            fried.setText(Integer.toString(frp));

        } else if (view == findViewById(R.id.plain_button)) {
            Toast.makeText(this, "Brinji Rice Added", Toast.LENGTH_SHORT).show();

            pp = pp + 1;

            plain.setText(Integer.toString(pp));
        } else if (view == findViewById(R.id.firni_button)) {
            Toast.makeText(this, "Cake Added", Toast.LENGTH_SHORT).show();

            fip = fip + 1;

            firni.setText(Integer.toString(fip));
        } else if (view == findViewById(R.id.drinks_button)) {
            Toast.makeText(this, "Cool Drinks Added", Toast.LENGTH_SHORT).show();

            drp = drp + 1;

            drinks.setText(Integer.toString(drp));
        }

    }

    // removing iteam

    public void rmv_from_list(View view) {



        if (view == findViewById(R.id.haleem_button_rm)) {
            if (hp > 0) {

                hp = hp - 1;
                haleem.setText(Integer.toString(hp));
                Toast.makeText(this, "Tea Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();



        } else if (view == findViewById(R.id.biriyani_button_rm)) {


            if (bp > 0) {
                bp = bp - 1;

                biriyani.setText(Integer.toString(bp));
                Toast.makeText(this, "Full Meals Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();

        } else if (view == findViewById(R.id.fried_button_rm)) {



            if (frp > 0) {

                frp = frp - 1;
                fried.setText(Integer.toString(frp));
                Toast.makeText(this, "Veg Puffs Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();

        } else if (view == findViewById(R.id.plain_button_rm)) {


            if (pp > 0) {

                pp = pp - 1;
                plain.setText(Integer.toString(pp));
                Toast.makeText(this, "Brinji Rice Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();


        } else if (view == findViewById(R.id.firni_button_rm)) {


            if (fip > 0) {

                fip = fip - 1;
                firni.setText(Integer.toString(fip));
                Toast.makeText(this, "Cake Removed", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();


        } else if (view == findViewById(R.id.drinks_button_rm)) {


            if (drp > 0) {
                drp = drp - 1;
                drinks.setText(Integer.toString(drp));
                Toast.makeText(this, "Cool Drinks Removed", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Add Items First", Toast.LENGTH_SHORT).show();

        }

    }

    public void balancesheet() {
        tbp = bp * 80;
        tdrp = drp * 35;
        tfip = fip * 50;
        tfrp = frp * 35;
        thp = hp * 20;
        tpp = pp * 40;
        total = tbp + tdrp + tfip + thp + tpp + tfrp;
        usd_price = total / 80;
        prices = Integer.toString(total);
        usd_price_string = Double.toString(usd_price);

        if (bp > 0) {
            choices = choices + "Full Meals      (" + bp + " x 80) =Rs " + tbp;
        }

        if (drp > 0) {
            choices = choices + "\n\nCold Drinks  (" + drp + " x 35) =Rs " + tdrp;
        }

        if (fip > 0) {
            choices = choices + "\n\nCake     (" + fip + " x 50) =Rs " + tfip;
        }

        if (frp > 0) {
            choices = choices + "\n\nVeg Puffs (" + frp + " x 35) =Rs " + tfrp;
        }
        if (hp > 0) {
            choices = choices + "\n\nTea   (" + hp + " x 20) =Rs " + thp;
        }

        if (pp > 0) {
            choices = choices + "\n\n Brinji Rice       (" + pp + " x 40) =Rs " + tpp;
        }


    }

   public void banner_about(View view) {

        final Dialog dialog = new Dialog(this); // Context, this, etc.
        dialog.setContentView(R.layout.about_dialogue);
        dialog.setTitle("About Developer");
        dialog.show();



        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 800000);
    }
}


