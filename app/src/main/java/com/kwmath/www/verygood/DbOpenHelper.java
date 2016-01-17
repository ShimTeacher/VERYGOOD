package com.kwmath.www.verygood;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by adslbna2 on 2016. 1. 11..
 */
public class DbOpenHelper {
    private Context mContext;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DbOpenHelper(Context context){
        mContext = context;
    }

    public DatabaseHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(mContext, DatabaseHelper._TABLENAME, null, 1);
        db = dbHelper.getWritableDatabase();
        return dbHelper;
    }

    public SQLiteDatabase getDB()
    {
        return db;
    }
    public void insertRow(String year,String month,String day,int checked, String msg){
        db.execSQL("insert into "+DatabaseHelper._TABLENAME+" (year,month,day,checked,msg) values(" + year + "," + month + "," + day + "," + new Integer(checked).toString() + ",'"+ msg +"');");
    }
    public void updateRow(String year,String month,String day,int checked, String msg){
        db.execSQL("update "+DatabaseHelper._TABLENAME+" set checked = " + new Integer(checked).toString() +" , msg = '"+msg+"' "+" WHERE year='" + year + "' and month ='" + month + "' and day= '" + day + "';");
    }

    public Cursor allSelect(){
        Cursor cursor = db.rawQuery("select * from "+DatabaseHelper._TABLENAME, null);
        String str = "";

        if(cursor.getCount()==0)
        {

        }
        else {
            if(cursor.moveToFirst()){
                do{
                    str += cursor.getString(0)+ " " + cursor.getString(1)+ " " + cursor.getString(2)+ " " + cursor.getString(3)+" "+cursor.getString(4) + " " +cursor.getString(5)+"\n";

                }while(cursor.moveToNext());
                Log.v("asdf",str);
            }

        }


        return cursor;
    }

    public Cursor ReturnCursorInSql(String sql)
    {
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public void db_close(){
        db.close();
    }
}