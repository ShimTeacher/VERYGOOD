package com.kwmath.www.verygood.fragment;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwmath.www.verygood.DatabaseHelper;
import com.kwmath.www.verygood.DbOpenHelper;
import com.kwmath.www.verygood.R;

import java.text.SimpleDateFormat;
import java.util.Locale;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class homeFragment extends Fragment {

    public static final String TAG = homeFragment.class.getSimpleName();
    static boolean isclicked = false;

    View view;
    ImageView img;
    public static SharedPreferences mPref;
    TextView textView;
    java.util.Date today = new java.util.Date();
    String todayString;
    public static TextView testText;
    DbOpenHelper dbOpenhelper;
    String dbYear; //데이터베이스에 넣을 Year, Month, Day
    String dbDay;
    String dbMonth;
    String basicString = " ";
    Vibrator vibe;
    long[] pattern = {100, 100, 100, 100, 100, 500};


    public void CheckDB()
    {
        dbOpenhelper.allSelect();

    }

    public void isclickedFunc()
    {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("It will be unchecked.")
                .setConfirmText("Yes,I want")
                .setCancelText("No")
                .showCancelButton(true)



                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        img.setImageResource(R.drawable.black);
                        sDialog
                                .setTitleText("Unchecked!")
                                .setContentText("VERYGOOD has been unchecked")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .showCancelButton(false)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        isclicked = false;
                        dbOpenhelper.updateRow(dbYear, dbMonth, dbDay, 0, basicString);
                        CheckDB();
                    }
                });



                sweetAlertDialog.setCancelable(true);
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                    }
                }).show();



    }

    @Override
    public void onDestroyView() {
        System.gc();
        super.onDestroyView();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        textView = (TextView)view.findViewById(R.id.timeText);
        img  = (ImageView)view.findViewById(R.id.goodBtn);


        //날짜 셋팅.
        SimpleDateFormat formatTime = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH);
        SimpleDateFormat formatTimeYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        SimpleDateFormat formatTimeDay = new SimpleDateFormat("dd", Locale.ENGLISH);
        SimpleDateFormat formatTimeMonth = new SimpleDateFormat("MM", Locale.ENGLISH);

        dbYear = formatTimeYear.format(today);
        dbDay = formatTimeDay.format(today);
        dbMonth = formatTimeMonth.format(today);
        todayString = formatTime.format(today);

        textView.setText(todayString);

        //리스너 등록
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclicked) { //클릭되어있지 않았다면
                    vibe.vibrate(pattern, -1);
                    sqlAction();

                } else { //클릭되어 있다면
                    isclickedFunc();
                }
            }
        });

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (!isclicked) { //클릭되어있지 않았다면

                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                    sweetAlertDialog.setTitleText("Very Good!")
                            .setContentText(" How about record your today's best moments?")
                            .setCustomImage(R.drawable.white)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    vibe.vibrate(pattern, -1);
                                    String EditMessage = sweetAlertDialog.getEditTextString();
                                    sqlActionWithMsg(EditMessage);
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            })
                            .show();



                }else{
                    isclickedFunc();

                }


                return true;
            }


            //Test입니다 적당히 워줘야 합니다지//Test입니다 적당히 워줘야 합니다지//Test입니다 적당히 워줘야 합니다지//Test입니다 적당히 워줘야 합니다지
            //Test입니다 적당히 워줘야 합니다지//Test입니다 적당히 워줘야 합니다지//Test입니다 적당히 워줘야 합니다지//Test입니다 적당히 워줘야 합니다지







        });

        try {
            dbOpenhelper = new DbOpenHelper(getActivity());
            dbOpenhelper.open();

        }catch (Exception e)
        {
            Log.v(TAG,"데이터베이스 열리지 않습니다");
        }

        if(hasTodayDB())
        {
            //오늘자 데이터베이스가 있다면 checked값을 돌려받자
            String sql = "SELECT checked FROM " + DatabaseHelper._TABLENAME + " WHERE year = " + dbYear + " and month = " + dbMonth + " and day = " + dbDay + "; ";
            Cursor cursor = dbOpenhelper.ReturnCursorInSql(sql);
            cursor.moveToFirst();
            int dbChecked = new Integer(cursor.getString(0));

            if(dbChecked==1)
            {
                img.setImageResource(R.drawable.white);
                isclicked = true;
            }
            else {
                img.setImageResource(R.drawable.black);
                isclicked = false;
            }


        }
        else
        {
            Toast.makeText(getActivity(),R.string.AppIntroFirstTitle,Toast.LENGTH_SHORT).show();
            img.setImageResource(R.drawable.black);
            isclicked = false;
         }


        return view;
    }




    void sqlAction()
    {

        isclicked = true;
        img.setImageResource(R.drawable.white);

        String findSQL = "SELECT * FROM " + DatabaseHelper._TABLENAME + " WHERE year = " + dbYear + " and month = " + dbMonth + " and day = " + dbDay + "; ";

        Cursor cursor = dbOpenhelper.ReturnCursorInSql(findSQL);
        cursor.moveToFirst();
        if(cursor.getCount()==0)
        {
            dbOpenhelper.insertRow(dbYear, dbMonth, dbDay, 1, basicString);
        }
        else {
            dbOpenhelper.updateRow(dbYear, dbMonth, dbDay, 1, basicString);
        }
        CheckDB();
    }

    void sqlActionWithMsg(String str)
    {
        isclicked = true;
        img.setImageResource(R.drawable.white);

        String findSQL = "SELECT * FROM " + DatabaseHelper._TABLENAME + " WHERE year = " + dbYear + " and month = " + dbMonth + " and day = " + dbDay + "; ";

        Cursor cursor = dbOpenhelper.ReturnCursorInSql(findSQL);
        cursor.moveToFirst();
        if(cursor.getCount()==0)
        {
            dbOpenhelper.insertRow(dbYear, dbMonth, dbDay, 1, str);
        }
        else {
            dbOpenhelper.updateRow(dbYear, dbMonth, dbDay, 1, str);
        }
        CheckDB();
    }

    boolean hasTodayDB()
    {
        String sql = "SELECT * FROM " + DatabaseHelper._TABLENAME + " WHERE year = " + dbYear + " and month = " + dbMonth + " and day = " + dbDay + "; ";

        Cursor cursor =  dbOpenhelper.ReturnCursorInSql(sql);
        cursor.moveToFirst();
        if(cursor.getCount()==0)
            return false;

        else
            return true;

    }



}