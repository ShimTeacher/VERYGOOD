package com.kwmath.www.verygood.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.kwmath.www.verygood.Intro;
import com.kwmath.www.verygood.R;


public class developerPageFragment extends Fragment {
    private WebView webview;

    @Override
    public void onStart() {
        System.gc();
        super.onStart();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //개발자가 정의한 디자인 파일을 인플레이션 시킨후 반환된 뷰를 현재 메서드의 반환값으로 지정
        View view = inflater.inflate(R.layout.fragment_developer_page, container, false);
        // 웹뷰에서 자바스크립트실행가능
        Button button = (Button)view.findViewById(R.id.AppIntro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Intro.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
