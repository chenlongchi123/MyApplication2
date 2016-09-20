package com.example.chen.myapplication;

import java.util.Calendar;

import com.stationlist.StationListActivity;
import com.ticket.TicketResultStep1Activity;


import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class TicketFragment extends Fragment {
    TextView tvTicketStationFrom;
    TextView tvTicketStationTo;
    ImageView imgTicketExchange;
    TextView tvTicketDateFrom;
    Button btnTicketQuery;


    public TicketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        tvTicketStationFrom = (TextView) getActivity().findViewById(
                R.id.tvTicketStationFrom);
        tvTicketStationTo = (TextView) getActivity().findViewById(
                R.id.tvTicketStationTo);
        imgTicketExchange=(ImageView)getActivity().findViewById(R.id.imgTicketExchange);
        tvTicketDateFrom=(TextView)getActivity().findViewById(R.id.tvTicketDateFrom);
        btnTicketQuery=(Button)getActivity().findViewById(R.id.btnTicketQuery);



        tvTicketStationFrom.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), StationListActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        tvTicketStationTo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), StationListActivity.class);
                startActivityForResult(intent, 101);
            }
        });
        imgTicketExchange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stationFrom=tvTicketStationFrom.getText().toString();
                final String stationTo=tvTicketStationTo.getText().toString();
                TranslateAnimation animationFrom=new TranslateAnimation(0,150,0,0);//设置平移距离
                animationFrom.setInterpolator(new AccelerateInterpolator());//设置为加速平移
                animationFrom.setDuration(300);//设置时间为300毫秒
                animationFrom.setAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    //开始的方法
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //结束的方法
                        tvTicketStationTo.setText(stationFrom);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //重复的方法
                    }
                });//移动监听器
                TranslateAnimation animationTo=new TranslateAnimation(0,-150,0,0);//设置平移距离
                animationTo.setInterpolator(new AccelerateInterpolator());//设置为加速平移
                animationTo.setDuration(300);//设置时间为300毫秒
                animationTo.setAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //开始的方法
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //结束的方法
                        tvTicketStationFrom.setText(stationTo);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //重复的方法
                    }
                });//移动监听器
                tvTicketStationFrom.startAnimation(animationFrom);
                tvTicketStationTo.startAnimation(animationTo);
            }
        });
        tvTicketDateFrom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldDateFrom=tvTicketDateFrom.getText().toString();
                int oldYear=Integer.parseInt(oldDateFrom.split(" ")[0].split("-")[0]);
                int oldMonthOfYear=Integer.parseInt(oldDateFrom.split(" ")[0].split("-")[1]);
                int oldDayOfMonth=Integer.parseInt(oldDateFrom.split(" ")[0].split("-")[2]);
                new DatePickerDialog(getActivity(), new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar c2=Calendar.getInstance();
                        c2.set(year,monthOfYear,dayOfMonth);
                        String weekDay=DateUtils.formatDateTime(getActivity(),c2.getTimeInMillis(),
                                DateUtils.FORMAT_SHOW_WEEKDAY|DateUtils.FORMAT_ABBREV_WEEKDAY);
                    tvTicketDateFrom.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth+" "+weekDay);
                    }
                },oldYear,oldMonthOfYear,oldDayOfMonth).show();
            }
        });
        btnTicketQuery.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), TicketResultStep1Activity.class);
                startActivity(intent);
            }
        });







    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        String name = data.getStringExtra("name");
        if (!TextUtils.isEmpty(name)) {
            switch (requestCode) {
                case 100:
                    tvTicketStationFrom.setText(name);
                    break;
                case 101:
                    tvTicketStationTo.setText(name);
                    break;
            }
        }

    }

}
