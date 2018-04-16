package com.example.sempiternalsearch.hideme2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Setup extends AppCompatActivity{
    Context context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, login;
    EditText editText;
    private int[] key;
    ImageButton next;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        Log.d("Debuggind", "You suck");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        next = (ImageButton) findViewById(R.id.imageButton);
        context = this;
    }
    public void nextLayout(View v){
        setContentView(R.layout.setup_1);
        editText = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        login = (Button) findViewById(R.id.login);


    }

    public void buttonClick(View v) {
        Button b = (Button) v;
        String buttonString = b.getText().toString();
        buttonAppend(buttonString);
    }
    public void buttonLogin(View v){

        if (editText.length() >= 4){
            String pin = editText.getText().toString();
            editor.putInt("key", Integer.parseInt(pin));
            editor.putBoolean("setup", true);
            editor.commit();
            setContentView(R.layout.setup_final);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                        Log.d("Intent", "Setup Acitivty");
                        Intent setupIntent = new Intent(context, Login.class);
                        startActivity(setupIntent);
                    finish();
                }
            }, 1500);
            //do {
                //Add code to create everything while user waits

           // }
            //while ()
            //Add code to create everything while user waits
        }
    }
    private void buttonAppend(String buttonString){
        String editString = editText.getText().toString();
        String text = editText.getText().toString();
        if (!buttonString.equals("DEL")) {
            int number = Integer.parseInt(buttonString);
            editString += buttonString;
            editText.append(buttonString);
            //Log.d("DEBUG",editText.getText().toString());
        }

        if (editString.length() >= 4) {
            login.setBackgroundResource(R.drawable.login_yes);
        }
        else {
            login.setBackgroundResource(R.drawable.login_not);
        }
        if(buttonString.equals("DEL") && editString.length() > 0) {
            text = text.substring(0, editString.length() - 1);
            editText.setText(text);
            if (text.length() < 4){
                login.setBackgroundResource(R.drawable.login_not);
            }
        }

    }
  }
