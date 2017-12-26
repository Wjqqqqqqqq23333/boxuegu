package android.gdmec.edu.cn.boxuegu.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.gdmec.edu.cn.boxuegu.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_main_title;
    private TextView tv_back;
    private RelativeLayout rl_title_bar;
    private Button btn_register;
    private EditText et_user_name;
    private EditText et_psw;
    private EditText et_psw_again;
    private String userName;
    private String pwd;
    private String pwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    private void init(){
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back = ((TextView) findViewById(R.id.tv_back));
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);

         btn_register = (Button) findViewById(R.id.btn_register);
         et_user_name = (EditText) findViewById(R.id.et_user_name);
         et_psw = (EditText) findViewById(R.id.et_pwd);
         et_psw_again = (EditText) findViewById(R.id.et_pwd_again);
         tv_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 RegisterActivity.this.finish();
             }
         });
        btn_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getEditString();
            }
        });
    }
    private void getEditString(){
        userName = et_user_name.getText().toString().trim();
        pwd = et_psw.getText().toString();
        pwdAgain = et_psw_again.getText().toString().trim();
    }
}
//kkkkkkk11
//ss