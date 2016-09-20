package com.example.chen.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView tvForget;
    EditText etAccount;
    EditText etPassword;
    CheckBox ckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=(Button)findViewById(R.id.Login);
        tvForget=(TextView) findViewById(R.id.tvForget);
        etAccount=(EditText) findViewById(R.id.etAccount);
        etPassword=(EditText) findViewById(R.id.etPassword);
        ckBox=(CheckBox)findViewById(R.id.ckBox);
        //忘记密码链接
        tvForget.setText(Html.fromHtml("<a href=\"https://kyfw.12306.cn/otn/forgetPassword/initforgetMyPassword\">忘记密码？</a>"));
        tvForget.setMovementMethod(LinkMovementMethod.getInstance());
        //绑定监听器
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etAccount.getText().toString())){
                etAccount.setError("请输入用户名");
                    etAccount.requestFocus();
                }
                else if(TextUtils.isEmpty(etPassword.getText().toString())){
                    etPassword.setError("请输入密码");
                    etPassword.requestFocus();//移动光标到EditText处
                }
                else {
                    if (ckBox.isChecked()){
                        SharedPreferences pref=getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();//编辑器
                        editor.putString("username",etAccount.getText().toString());
                        editor.putString("password",etPassword.getText().toString());
                        editor.commit();//最后要提交
                    }
                    else{
                        SharedPreferences pref=getSharedPreferences("user",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.commit();
                    }
                Intent intent=new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();}
            }
        });
    }


}
