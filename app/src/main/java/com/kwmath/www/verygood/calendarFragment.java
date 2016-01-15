package com.kwmath.www.verygood;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;
import java.util.Locale;


public class calendarFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //개발자가 정의한 디자인 파일을 인플레이션 시킨후 반환된 뷰를 현재 메서드의 반환값으로 지정
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        TextView textView = (TextView)view.findViewById(R.id.textView);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        }, 2015, 5, 22);

        dpd.show();


        return view;
    }
}
