package com.examplechen.my;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.chen.myapplication.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MycontactEditActivity extends AppCompatActivity {
    ListView lvEditList;
    Button btnEdit=null;
    List<Map<String,Object>> data=null;
    SimpleAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycontact_edit);
        lvEditList=(ListView)findViewById(R.id.lvEditList);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        android.support.v7.app.ActionBar bar=getSupportActionBar();
        bar.setTitle("更改信息");
        //接收数据
        Intent intent=getIntent();
        Map<String,Object> contact=(Map<String,Object>)intent.getSerializableExtra("row");
        //数据部分
        data=new ArrayList<Map<String, Object>>();
        //row1姓名
        Map<String,Object> row1= new HashMap<String,Object>();
        String name=(String)contact.get("name");
        row1.put("key1","姓名");
        row1.put("key2",name.split("\\(")[0]);
        row1.put("key3",R.drawable.forward_25);
        data.add(row1);
        //row2证件类型
        Map<String,Object> row2= new HashMap<String,Object>();
        String id=(String)contact.get("id");
        row2.put("key1","证件类型");
        row2.put("key2",id.split(":")[0]);
        row2.put("key3",null);
        data.add(row2);
        //row3证件类型
        Map<String,Object> row3= new HashMap<String,Object>();
        row3.put("key1","证件号码");
        row3.put("key2",id.split(":")[1]);
        row3.put("key3",null);
        data.add(row3);
        //row3乘客类型
        Map<String,Object> row4= new HashMap<String,Object>();
        row4.put("key1","乘客类型");
        row4.put("key2",name.split("\\(")[1].split("\\)")[0]);
        row4.put("key3",R.drawable.forward_25);
        data.add(row4);
        //row1电话号码
        Map<String,Object> row5= new HashMap<String,Object>();
        String tel=(String)contact.get("tel");
        row5.put("key1","电话号码");
        row5.put("key2",tel.split(":")[1]);
        row5.put("key3",R.drawable.forward_25);
        data.add(row5);
        //适配器
        adapter=new SimpleAdapter(MycontactEditActivity.this,data,R.layout.activity_item_mycontact_edit,
                new String[]{"key1","key2","key3"},new int[]{R.id.tvEdit1,R.id.tvEdit2,R.id.img1});
        lvEditList.setAdapter(adapter);

        lvEditList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                switch (position){
                    case 0:
                        final EditText edtName=new EditText(MycontactEditActivity.this);
                        edtName.setText((String)(data.get(position).get("key2")));
                        edtName.selectAll();//默认全选中
                        new AlertDialog.Builder(MycontactEditActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setTitle("请输入姓名")
                                .setView(edtName)
                                .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog,int which){
                                        // 验证
                                        String name=edtName.getText().toString();
                                        if (TextUtils.isEmpty(name)){
                                            //设置对话框不能自动关闭
                                            setClosable(dialog,false);
                                            edtName.setError("请输入姓名");
                                            edtName.requestFocus();
                                        }else {
                                            data.get(position).put("key2",edtName.getText().toString());
                                        //更新listView
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }).setNegativeButton("取消",new DialogInterface.OnClickListener(){
                           public void onClick(DialogInterface dialog,int which){
                               setClosable(dialog,true);
                           }
                        }).show();
                        break;
                    case 3:
                        final String[] types=new String[]{"成人","学生","儿童","其他"};
                        String key2=(String)(data.get(position).get("key2"));
                        int idx=0;
                        for(int i=0;i<types.length;i++){
                            if(types[i].equals(key2)){
                                idx=i;
                                break;
                            }
                        }
                        new  AlertDialog.Builder(MycontactEditActivity.this).setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("请选择乘客类型")
                                .setSingleChoiceItems(types,idx,new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog,int which){
                                        data.get(position).put("key2",types[which]);
                                        adapter.notifyDataSetChanged();
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消",null).show();
                        break;
                    case 4:
                        final EditText edtTel=new EditText(MycontactEditActivity.this);
                        edtTel.setText((String)(data.get(position).get("key2")));
                        edtTel.selectAll();//默认全选中
                        new AlertDialog.Builder(MycontactEditActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setTitle("请输入电话号码")
                                .setView(edtTel)
                                .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                                    @Override
                                    public void onClick(DialogInterface dialog,int which){
                                        // 验证
                                        String name=edtTel.getText().toString();
                                        if (TextUtils.isEmpty(name)){
                                            //设置对话框不能自动关闭
                                            setClosable(dialog,false);
                                            edtTel.setError("请输入电话号码");
                                            edtTel.requestFocus();
                                        }else {
                                            data.get(position).put("key2",edtTel.getText().toString());
                                            //更新listView
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }).setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){
                                setClosable(dialog,true);
                            }
                        }).show();
                        break;
                }
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1数据保存到服务器上
                //2finish()
                finish();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        //访问服务器，更新数据
        Toast.makeText(MycontactEditActivity.this,"刷新",Toast.LENGTH_SHORT).show();
    }

    private  void  setClosable(DialogInterface dialog,boolean b){
        Field field;
        try{
            field=dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog,b);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_contact,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {

            case R.id.mn_contact_add:
                //跳转添加新用户
                Intent intent=new Intent(MycontactEditActivity.this,MycontactnewActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
