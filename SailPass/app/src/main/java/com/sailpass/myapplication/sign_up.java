package com.sailpass.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import javax.xml.transform.Templates;

public class sign_up extends AppCompatActivity {
    private Button btn_register;//注册按钮
    //用户名，密码，再次输入的密码的控件
    private EditText user,password,password1;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName,psw,pswAgain;
    private Object MD5Utils;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_sign_up);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {

        btn_register=findViewById(R.id.btn_register);
        user=findViewById(R.id.user);
        password=findViewById(R.id.password);
        password1=findViewById(R.id.password1);
        btn_back = findViewById(R.id.btn_back_sign);
        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                getEditString();
                //判断输入框内容
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(sign_up.this, "Please set your username.", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(sign_up.this, "Please set your password.", Toast.LENGTH_SHORT).show();

                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(sign_up.this, "Please enter the same password again.", Toast.LENGTH_SHORT).show();

                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(sign_up.this, "Those passwords didn’t match. Try again.", Toast.LENGTH_SHORT).show();

                    /*
                     *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                     */
                }else if(isExistUserName(userName)){
                    Toast.makeText(sign_up.this, "This username has already been taken.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(sign_up.this, "Sign up successfully! Congrats!", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存到sp里面
                    /*
                     * 保存账号和密码到SharedPreferences中
                     */
                    saveRegisterInfo(userName, psw);
                    //注册成功后把账号传递到LoginActivity.java中
                    // 返回值到loginActivity显示
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    sign_up.this.finish();
                    startActivity(new Intent(sign_up.this, log_in.class));
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_up.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        userName=user.getText().toString().trim();
        psw=password.getText().toString().trim();
        pswAgain=password1.getText().toString().trim();
    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     */
    private void saveRegisterInfo(String userName,String psw){
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, psw);
        //提交修改 editor.commit();
        editor.apply();
    }
}

