package com.kwmath.www.verygood;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class calendarFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //개발자가 정의한 디자인 파일을 인플레이션 시킨후 반환된 뷰를 현재 메서드의 반환값으로 지정
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);


        return view;
    }
}
