package com.manu.lumos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        DatabaseSequel ds=new DatabaseSequel(getApplicationContext(),"Databasedb",null,1);
        SQLiteDatabase db=ds.getReadableDatabase();
        Cursor c=db.query("Credits",new String[]{"_id", "Username", "Name", "Email", "Phoneno"},null,null,null,null,null);
        String[] from=new String[]{"_id", "Username", "Name", "Email", "Phoneno"};
        int[] to=new int[]{R.id.id, R.id.username, R.id.name, R.id.email, R.id.phone};
        int flag=1;

        SimpleCursorAdapter sca=new SimpleCursorAdapter(getApplicationContext(), R.layout.layout, c, from, to, flag);

        ListView lv=(ListView)findViewById(R.id.listviewed);
        lv.setAdapter(sca);
    }
}
