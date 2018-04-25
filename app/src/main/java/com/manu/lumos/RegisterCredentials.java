package com.manu.lumos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterCredentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_credentials);
        EditText e3 = (EditText)findViewById(R.id.editText3);
        EditText e4 = (EditText)findViewById(R.id.editText4);
        EditText e5 = (EditText)findViewById(R.id.editText5);
        EditText e6 = (EditText)findViewById(R.id.editText6);
        EditText e7 = (EditText)findViewById(R.id.editText7);

        if(e4.getText().toString().trim().equals(""))
        {

            e4.setError("Username is required!");

        }

        Button b = (Button) findViewById(R.id.button5);

        final String str=e3.getText().toString();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseSequel help=new DatabaseSequel(v.getContext(),"Databasedb",null,1);
                //DatabaseSequel help=new DatabaseSequel(v.getContext());
                SQLiteDatabase db=help.getWritableDatabase();

                String name=((EditText) findViewById(R.id.editText3)).getText().toString();
                String phone=((EditText) findViewById(R.id.editText6)).getText().toString();
                String username=((EditText) findViewById(R.id.editText4)).getText().toString();
                String password=((EditText) findViewById(R.id.editText5)).getText().toString();
                String email=((EditText) findViewById(R.id.editText7)).getText().toString();


                //Writing Data
                ContentValues cv=new ContentValues();
                cv.put("Name",name);
                cv.put("Phoneno",phone);
                cv.put("Username",username);
                cv.put("Email",email);
                cv.put("Password",password);

                db.insert("Credits",null,cv);

                //Intent
                Intent i = new Intent(v.getContext(), Main2Activity.class);
                startActivity(i);
                Toast.makeText(RegisterCredentials.this, "Data Stored", Toast.LENGTH_SHORT).show();
            }
        });

    }
}