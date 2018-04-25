package com.manu.lumos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    int counter = 3;
    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);

             b1 = (Button)findViewById(R.id.button);
             ed1 = (EditText)findViewById(R.id.editText);
             ed2 = (EditText)findViewById(R.id.editText2);

             b2 = (Button)findViewById(R.id.button2);
             tx1 = (TextView)findViewById(R.id.textView3);
           //  tx1.setVisibility(View.GONE);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseSequel ds=new DatabaseSequel(v.getContext(),"Databasedb",null,1);
                    SQLiteDatabase db=ds.getReadableDatabase();
                    Cursor c=db.query("Credits",new String[]{"Username", "Password"},"Username=?",new String[]{ed1.getText().toString()},null,null,null);

                    c.moveToFirst();
                    if(c.getString(c.getColumnIndex("Username")).equals(ed1.getText().toString()) && (c.getString(c.getColumnIndex("Password")).equals(ed2.getText().toString())))
                    {
                        Intent i = new Intent(v.getContext(), MapsActivity.class);
                        i.putExtra("abcd",ed1.getText().toString());
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(Main2Activity.this, "LOL, No.", Toast.LENGTH_SHORT).show();
                        tx1.setVisibility(View.VISIBLE);
                        tx1.setBackgroundColor(Color.RED);
                        counter--;
                        tx1.setText(Integer.toString(counter));

                        if (counter == 0) {
                            b1.setEnabled(false);
                        }
                    }

                   /* if(ed1.getText().toString().equals("admin") &&
                            ed2.getText().toString().equals("admin")) {
                 //       Intent i = new Intent(v.getContext(),MainActivity.class);
                        Toast.makeText(getApplicationContext(),"Redirecting...",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                                tx1.setVisibility(View.VISIBLE);
                        tx1.setBackgroundColor(Color.RED);
                        counter--;
                        tx1.setText(Integer.toString(counter));

                        if (counter == 0) {
                            b1.setEnabled(false);
                        }
                    }*/
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            
//           Cursor c=db.query("Credits",new String[]{"Name"},"Phoneno=?",new String[]{"9910106462"},null,null,null);
//           String inf=c.getString(0);
//           ed1.setText(inf);
        }
    }