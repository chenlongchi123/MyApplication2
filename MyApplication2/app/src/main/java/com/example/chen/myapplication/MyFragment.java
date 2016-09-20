package com.example.chen.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.examplechen.my.MyaccountActivity;
import com.examplechen.my.MycontactActivity;
import com.examplechen.my.MypasswordActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    Button btnLogin;
    ListView listView;
    public MyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

            listView=(ListView)getActivity().findViewById(R.id.listView);
            btnLogin=(Button)getActivity().findViewById(R.id.btnLogin);
            //每行布局
            //数据
            String[] data=getResources().getStringArray(R.array.my_list_data);
            //适配器
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent();
                    switch (position) {
                        case 0:
                            intent.setClass(getActivity(), MycontactActivity.class);
                            break;
                        case 1:
                            intent.setClass(getActivity(), MyaccountActivity.class);
                            break;
                        case 2:
                            intent.setClass(getActivity(), MypasswordActivity.class);
                            break;
                    }
                    startActivity(intent);
                }
            });
            MyButtonListener listener=new MyButtonListener();
            btnLogin.setOnClickListener(listener);
        }
        class MyButtonListener implements View.OnClickListener {
            public void onClick(View v){
                Intent intent=new Intent();
                intent.setClass(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        }
}


