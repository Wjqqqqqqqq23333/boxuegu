package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.gdmec.edu.cn.boxuegu.R;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeUserInfoActivity extends AppCompatActivity {

    private String title;
    private String content;
    private int flag;
    private TextView tv_back;
    private TextView tv_save;
    private ImageView iv_delect;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);

        init();
    }

    private void init() {
        //从个人资料界面传递过来的标题和内容
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag", 0);

        tv_back = (TextView) findViewById(R.id.tv_back);
        TextView tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("title");
        RelativeLayout rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_save.setVisibility(View.VISIBLE);
        iv_delect = (ImageView) findViewById(R.id.iv_delect);
        et_content = (EditText) findViewById(R.id.et_content);
        if (!TextUtils.isEmpty(content)){
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeUserInfoActivity.this.finish();
            }
        });
        iv_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_content.setText("");
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                String etContent = et_content.getText().toString().trim();
                switch (flag){
                    case 1:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("nickName", etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"呢称不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("signature", etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else {
                            Toast.makeText(ChangeUserInfoActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    //监听个人资料修改界面的输入文字
    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Editable editable = et_content.getText();
                int len = editable.length();
                if (len>0){
                    iv_delect.setVisibility(View.VISIBLE);
                }else {
                    iv_delect.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1://呢称不能超过8个字符，超过的截取掉
                        if (len>8){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            String newStr = str.substring(0, 8);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;

                    case 2://签名不能超过16个字符，超过的截取掉
                        if (len>16){
                            int selEndIndex = Selection.getSelectionEnd(editable);
                            String str = editable.toString();
                            String newStr = str.substring(0, 16);
                            et_content.setText(newStr);
                            editable = et_content.getText();
                            int newLen = editable.length();
                            if (selEndIndex > newLen){
                                selEndIndex = editable.length();
                            }
                            //设置新光标所在的位置
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}