package com.kwmath.www.verygood;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class homeFragment extends Fragment {

    boolean isclicked = false;
    View view;
    ImageView img;
    SharedPreferences mPref;

    @Override
    public void onStart() {


        //시작과 동시에 이전에 따봉버튼을 눌렀는지 확인한다
        isclicked = mPref.getBoolean("key", false);
        if(isclicked)
        {
            img.setImageResource(R.drawable.white);
        }

        super.onStart();
    }

    void setCheckValue()
    {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean("key", isclicked);
        editor.commit();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);
        mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());




        img = (ImageView)view.findViewById(R.id.goodBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isclicked) {
                    Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    long[] pattern = {100, 100, 100, 100, 100, 500};          // 진동, 무진동, 진동 무진동 숫으로 시간을 설정한다.
                    vibe.vibrate(pattern, -1);                                         // 패턴을 지정하고 반복횟수를 지정
                    img.setImageResource(R.drawable.white);
                    isclicked = true;

                }
                else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("It will be unchecked.")
                            .setConfirmText("Yes,I want to uncheck it!")
                            .setCancelText("No")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    img.setImageResource(R.drawable.black);
                                    sDialog
                                            .setTitleText("Unchecked!")
                                            .setContentText("Your ThumbsUpButton has been unchecked!")
                                            .setConfirmText("OK")

                                            .setConfirmClickListener(null)
                                            .showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    isclicked = false;
                                }
                            })
                            .show();


                }
                setCheckValue();
            }
        });



        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                img.setImageResource(R.drawable.white);
                isclicked = true;
                return true;
            }
        });



        return view;
    }





}