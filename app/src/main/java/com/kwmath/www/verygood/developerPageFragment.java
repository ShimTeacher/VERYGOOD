package com.kwmath.www.verygood;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class developerPageFragment extends Fragment {
    private WebView webview;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //개발자가 정의한 디자인 파일을 인플레이션 시킨후 반환된 뷰를 현재 메서드의 반환값으로 지정
        View view = inflater.inflate(R.layout.fragment_developer_page, container, false);
        // 웹뷰에서 자바스크립트실행가능
        webview = (WebView)view.findViewById(R.id.webView);
        webview.setWebViewClient(new android.webkit.WebViewClient()); // 응룡프로그램에서 직접 url 처리
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl("http://localhost/");


        return view;
    }
}
