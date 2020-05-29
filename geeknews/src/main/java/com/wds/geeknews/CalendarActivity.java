package com.wds.geeknews;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.wds.bean.CalendarEvent;
import com.wds.util.DateUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

public class CalendarActivity extends BaseActivity {


    private Toolbar mToolbar;
    private MaterialCalendarView mCalender;

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCalender = (MaterialCalendarView) findViewById(R.id.calender);
        mToolbar.setTitle("日历");
        setSupportActionBar(mToolbar);
    }


    private CalendarDay calendarDay;
    @Override
    protected void initData() {

        mCalender.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2018,8,8))
                .setMaximumDate(CalendarDay.from(DateUtils.getCurrentYear(),DateUtils.getCurrentMonth(),DateUtils.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mCalender.setOnDateChangedListener(new OnDateSelectedListener() {


            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                calendarDay= date;
                //10

                //发送日期数据
                CalendarEvent calendarEvent = new CalendarEvent();
                // 填补月的数据
                String month = calendarDay.getMonth()+1+"";
                  month = month.length()==1? "0"+month:month;

                  //填补日期的数据
                String day = calendarDay.getDay()+"";
                day = day.length()==1? "0"+day:day;
                //20200511
                calendarEvent.date =calendarDay.getYear()+""+month+day;
                EventBus.getDefault().post(calendarEvent);
                //关闭页面
                finish();
            }
        });

    }



    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }
}
