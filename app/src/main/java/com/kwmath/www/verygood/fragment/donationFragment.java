package com.kwmath.www.verygood.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwmath.www.verygood.DbOpenHelper;
import com.kwmath.www.verygood.R;


public class donationFragment extends Fragment {
    public DbOpenHelper mDbOpenHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        TextView textView =(TextView)view.findViewById(R.id.textViewinDona);

        DbOpenHelper dbOpenhelper = new DbOpenHelper(getActivity());
        try {
            dbOpenhelper.open();
        }catch (Exception e)
        {

        }

        return view;
    }
}
