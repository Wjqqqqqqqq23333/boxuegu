package android.gdmec.edu.cn.boxuegu.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.gdmec.edu.cn.boxuegu.bean.UserBean;
import android.gdmec.edu.cn.boxuegu.utils.AnalysisUtils;
import android.gdmec.edu.cn.boxuegu.utils.DBUtils;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.gdmec.edu.cn.boxuegu.R;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_signature;
    private RelativeLayout rl_signature;
    private TextView tv_sex;
    private RelativeLayout rl_sex;
    private TextView tv_user_name;
    private TextView tv_nickName;
    private RelativeLayout rl_nickName;
    private String spUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        spUserName = AnalysisUtils.readLoginUserName(this);
        init();
        initDate();
        setLinstener();
    }

    private void setLinstener() {
        tv_nickName.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        tv_signature.setOnClickListener(this);
    }


    private void init() {
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        TextView tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        RelativeLayout rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

        rl_nickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        tv_nickName = (TextView) findViewById(R.id.tv_nickName);

        tv_user_name = (TextView) findViewById(R.id.tv_user_name);

        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        tv_sex = (TextView) findViewById(R.id.tv_sex);

        rl_signature = (RelativeLayout) findViewById(R.id.rl_signature);
        tv_signature = (TextView) findViewById(R.id.tv_signature);
    }

    //从数据库中获取数据
    private void initDate() {
        UserBean bean = null;
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //先判断数据库中是否有数据
        if (bean == null){
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "问答精灵";
            bean.sex = "男";
            bean.signature = "问答精灵";
            //保存用户信息到数据库
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    //为界面控件设置值
    private void setValue(UserBean bean) {
        tv_nickName.setText(bean.nickName);
        tv_user_name.setText(bean.userName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName://呢称的点击事件
                break;
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature://签名的点击事件
                break;
            default:
                    break;
        }
    }

    //设置性别的弹出框
    private void sexDialog(String sex) {
        int sexFlag = 0;
        if ("男".equals(sex)){
            sexFlag = 0;
        }else if ("女".equals(sex)){
            sexFlag = 1;
        }

        final String items[] = {"男","女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");//标题
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, items[which],Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
    }

    //更新界面上的性别数据
    private void setSex(String sex) {
        tv_sex.setText(sex);
        //更新数据库的性别数据
        DBUtils.getInstance(this).updateUserInfo("sex",sex,spUserName);
    }
}
