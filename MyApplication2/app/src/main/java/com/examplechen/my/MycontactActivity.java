package com.examplechen.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.chen.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MycontactActivity extends AppCompatActivity {
    ListView lvMycontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycontact);
        //  返回菜单
         android.support.v7.app.ActionBar bar=getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        lvMycontact=(ListView)findViewById(R.id.lvMycontact);

        final List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
        Map<String,Object> row=new HashMap<String,Object>();
        row.put("name","陈龙驰(学生)");
        row.put("id","身份证号:13132168431346515");
        row.put("tel","电话:182454646465");
        data.add(row);

        row=new HashMap<String,Object>();
        row.put("name","于善淼(学生)");
        row.put("id","身份证号:131165413216546515");
        row.put("tel","电话:182454651465");
        data.add(row);

        row=new HashMap<String,Object>();
        row.put("name","李明(成人)");
        row.put("id","身份证号:131316654654546515");
        row.put("tel","电话:182454445465");
        data.add(row);

        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.activity_item_mycontact,
                new String[]{"name","id","tel"},new int[]{R.id.tvName,R.id.tvId,R.id.tvTel});
        //绑定
        lvMycontact.setAdapter(adapter);
        lvMycontact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MycontactActivity.this,MycontactEditActivity.class);
                //传递数据
                intent.putExtra("row",(Serializable)data.get(position));//Map
                startActivity(intent);
            }
        });

    }
    //加载
@Override
    public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.my_contact,menu);
    return true;
    }
//事件处理

//返回执行
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            case R.id.mn_contact_add:
                //跳转添加新用户
                Intent intent=new Intent(MycontactActivity.this,MycontactnewActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
