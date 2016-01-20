package com.kwmath.www.verygood;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.kwmath.www.verygood.fragment.alarmFragment;
import com.kwmath.www.verygood.fragment.calendarFragment;
import com.kwmath.www.verygood.fragment.developerPageFragment;
import com.kwmath.www.verygood.fragment.donationFragment;
import com.kwmath.www.verygood.fragment.homeFragment;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import java.util.ArrayList;
import java.util.Calendar;


//TODO 3. 시간관련 명언을 모아둔 데이터베이스를 이용하여 새로 앱을 실행할 때마다 띄워준다.

// 그외의 ************TO DO LIST***********

//TODO 달력, 알람시간, 기부페이지, 만든이
//...->
    //TODO 달력-> THUMBSUP 누른 날은 달력에 표시되어햔다.
    //TODO 알람시간->을 설정하여 알람을 알릴 날을 설정한다.
    //TODO 기부페이지-> 내부 결제 페이지를 어떻게 해야하는지 알아본다.
    //TODO 만든이 -> 이미지정보 간단한 프로필을 보여준다.


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener{


    DbOpenHelper dbOpenhelper;
    FragmentManager manager;
    FragmentTransaction trans;
    ImageView img;
    homeFragment homeFg;
    calendarFragment calenderFg = new calendarFragment();
    developerPageFragment developerFg  = new developerPageFragment();
    donationFragment donationFg = new donationFragment();
    alarmFragment alarmFg = new alarmFragment();



    @Override
    protected void onStart() {

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        String start = prefs.getString("key", null);


        if(start==null)
        {
            Intent intent = new Intent(this, Intro.class);
            startActivity(intent);
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putString("key", "kwangwoon");
            editor.commit();

        }

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intro aActivity = (Intro)Intro.AActivity;

        if(aActivity !=null)
        {
            aActivity.finish();
        }



        menuItemSetting(); //반드시 이 위치에 실행되어야 한다.
        img =(ImageView) findViewById(R.id.goodBtn);



    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void menuItemSetting()
    {
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//

        final ImageView icon = new ImageView(this); // Create an icon
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView info_icon = new ImageView(this);   //메뉴 아이템 5개 만들기
        ImageView dollar_icon = new ImageView(this);
        ImageView alarm_icon = new ImageView(this);
        ImageView calendar_icon = new ImageView(this);
        ImageView home_icon = new ImageView(this);

        icon.setImageResource(R.drawable.new_add);        // 메뉴버튼 아이콘
        info_icon.setImageResource(R.drawable.info_icon); //만든이 정보 아이콘
        dollar_icon.setImageResource(R.drawable.dollar_icon); //기부 정보 아이콘
        alarm_icon.setImageResource(R.drawable.alarm_icon);  // 알람 설정 아이콘
        calendar_icon.setImageResource(R.drawable.calendar_icon); // 캘린더 정보 아이콘
        home_icon.setImageResource(R.drawable.home_icon_256); // 홈화면 아이콘콘


        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        final FloatingActionMenu rightLowerMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(info_icon).build())
                .addSubActionView(rLSubBuilder.setContentView(dollar_icon).build())
                //.addSubActionView(rLSubBuilder.setContentView(alarm_icon).build())
                .addSubActionView(rLSubBuilder.setContentView(calendar_icon).build())
                .addSubActionView(rLSubBuilder.setContentView(home_icon).build())
                .attachTo(rightLowerButton)
                .build();

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                icon.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(icon, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                icon.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(icon, pvhR);
                animation.start();
            }
        });

        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//
        //**********************************Circular Floating Menu Button 라이브러리 코드****************************//






        //이 부분에서 메뉴아이콘들의 Listener를 달아준다
        //이 부분에서 메뉴아이콘들의 Listener를 달아준다
        //이 부분에서 메뉴아이콘들의 Listener를 달아준다
        //이 부분에서 메뉴아이콘들의 Listener를 달아준다
        //이 부분에서 메뉴아이콘들의 Listener를 달아준다

        manager = getFragmentManager();
        homeFg = (homeFragment)manager.findFragmentById(R.id.home_fg);

        home_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setEnabled(true);
                trans = manager.beginTransaction();
                trans.replace(R.id.home_fg, homeFg);
                trans.commit();
            }
        });
        calendar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbOpenhelper = new DbOpenHelper(getBaseContext());
                    dbOpenhelper.open();

                }catch (Exception e)
                {
                   // Log.v(TAG,"데이터베이스 열리지 않습니다");
                }


                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );




                Cursor cursor = dbOpenhelper.allSelect();
                int temp=0;
                try{

                    Calendar cal;
                    ArrayList<Calendar> CalendarList = new ArrayList<Calendar>();


                    if(cursor.getCount()==0)
                    {
                        cal= Calendar.getInstance();
                        cal.set(2016,11,31);
                        CalendarList.add(cal);
                        temp=1;
                    }
                    else {
                        if (cursor.moveToFirst()) {
                            do {

                                int dbYear = new Integer(cursor.getString(1));
                                int dbMonth = new Integer(cursor.getString(2)) - 1;
                                int dbDay = new Integer(cursor.getString(3));
                                int checked = new Integer(cursor.getString(4));


                                if (checked == 1) {
                                    cal = Calendar.getInstance();
                                    cal.set(dbYear, dbMonth, dbDay);
                                    CalendarList.add(cal);
                                    temp=0;
                                }
                                else {
                                    cal= Calendar.getInstance();
                                    cal.set(2016,11,31);
                                    CalendarList.add(cal);
                                    temp=1;
                                }

                            } while (cursor.moveToNext());
                        }
                    }



                    //Integer[] place = al3.toArray(new Integer[al.size()]);
                    Calendar[] calendars = CalendarList.toArray(new Calendar[CalendarList.size()]);
                    dpd.setSelectableDays(calendars);
                    dpd.setHighlightedDays(calendars);
                    dpd.setAccentColor(getResources().getColor(R.color.date_picker_accent));
                    dpd.setThemeDark(true);
                    dpd.setCancelable(false);
                    dpd.setDB(dbOpenhelper.getDB());
                    dpd.setMonthText("");
                    DbOpenHelper dbOpenhelper;
                        Cursor myCursor=null;
                    try {
                        dbOpenhelper = new DbOpenHelper(getApplicationContext());
                        dbOpenhelper.open();
                        myCursor = dbOpenhelper.ReturnCursorInSql("SELECT COUNT(id) FROM " + DatabaseHelper._TABLENAME + " WHERE month = " + now.get(Calendar.MONTH)+1 + " and checked = 1;");
                    }catch (Exception e)
                    {

                    }
                    myCursor.moveToFirst();
                    String highlightMonth = myCursor.getString(0);
                    //"SELECT * FROM " + DatabaseHelper._TABLENAME + " WHERE year = " + dbYear + " and month = " + dbMonth + " and day = " + dbDay + "; ";

                    String highlightDays = new Integer(dpd.getSelectableDays().length-temp).toString();
                    dpd.set365Text(highlightDays+ "/365");
                    Calendar today = Calendar.getInstance();
                    dpd.setMonthText(highlightMonth+"/"+today.getActualMaximum(Calendar.DATE));
                    dpd.show(getFragmentManager(), "Datepickerdialog");

                }catch (Exception e) {

                }

            }
        });



        info_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setEnabled(false);
                trans = manager.beginTransaction();
                trans.replace(R.id.home_fg, developerFg);
                trans.commit();

            }
        });

        dollar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setEnabled(false);
                trans = manager.beginTransaction();
                trans.replace(R.id.home_fg, donationFg);
                trans.commit();

            }
        });

    }


    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1, int i2) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
     //   dateTextView.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
