package com.manu.lumos;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

/**
 * Created by manugupta on 23/03/17.
 */

public class AdapterImplemented extends SimpleCursorAdapter {
    public AdapterImplemented(Context c, int layout, Cursor cu, String[] from, int[] to, int flag) {

        super(c, layout, cu, from, to, flag);

    }
}
