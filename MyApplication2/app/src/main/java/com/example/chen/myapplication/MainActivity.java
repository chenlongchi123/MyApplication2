package com.example.chen.myapplication;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    ViewPager pager=null;

    @SuppressWarnings("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //导航条
        final android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);//设计模式


//  获取ViewPager
         pager= (ViewPager) findViewById(R.id.pager);
        //绑定
        pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        //事件处理
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int arg0) {
                bar.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        //添加
        bar.addTab(bar.newTab().setText("车票").setTabListener((android.support.v7.app.ActionBar.TabListener) new MyTabListener()));
        bar.addTab(bar.newTab().setText("订单").setTabListener((android.support.v7.app.ActionBar.TabListener) new MyTabListener()));
        bar.addTab(bar.newTab().setText("我的").setTabListener((android.support.v7.app.ActionBar.TabListener) new MyTabListener()));
    }

    //适配器类
    class TabFragmentPagerAdapter extends FragmentPagerAdapter{
        public TabFragmentPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment f=null;
            switch (arg0){
                case 0:
                    f=new TicketFragment();
                    break;
                case 1:
                    f=new OrderFragment();
                    break;
                case 2:
                    f=new MyFragment();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    class MyTabListener implements android.support.v7.app.ActionBar.TabListener {

        @Override
        public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            pager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

}
