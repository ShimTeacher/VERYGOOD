package com.kwmath.www.verygood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adslbna2 on 2016. 1. 11..
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String _ID = "id";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String CHECKED = "checked";
    public static final String MSG = "msg";


    public static final String _TABLENAME = "diary";
    public static final String _CREATE =
            "create table "+_TABLENAME + "(id INTEGER NOT NULL primary key autoincrement, "
                    +YEAR+" INTEGER NOT NULL , "
                    +MONTH+" INTEGER NOT NULL , "
                    +DAY+" INTEGER NOT NULL , "
                    +CHECKED+" INTEGER NOT NULL , "
                    +MSG+" VARCHAR(80) );";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DatabaseHelper._TABLENAME);
        onCreate(db);
    }
}