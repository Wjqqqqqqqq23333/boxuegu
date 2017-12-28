package android.gdmec.edu.cn.boxuegu.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.gdmec.edu.cn.boxuegu.bean.UserBean;
import android.gdmec.edu.cn.boxuegu.utils.AnalysisUtils;
import android.gdmec.edu.cn.boxuegu.utils.DBUtils;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.gdmec.edu.cn.boxuegu.R;
import android.text.TextUtils;
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
    private TextView tv_back;

    private static final int CHANGE_NICKNAME = 1;//修改呢称的自定义常量
    private static final int CHANGE_SIGNATURE = 2;//修改签名的自定义常量
    private String new_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        spUserName = AnalysisUtils.readLoginUserName(this);
        init();
        initDate();
        setLinstener();
    }

    public void enterActivityForResult(Class<?> to,int requestCode,Bundle b){
        Intent i=new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestCode);
    }

    private void setLinstener() {
        tv_back.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
    }


    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
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
                String name = tv_nickName.getText().toString();
                Bundle bdName = new Bundle();
                bdName.putString("content",name);//传递界面上的呢称数据
                bdName.putString("title","呢称");
                bdName.putInt("flag",1);//flag为1表示是修改呢称
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_NICKNAME,bdName);
                break;
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature://签名的点击事件
                String signature = tv_signature.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content",signature);//传递界面上的呢称数据
                bdSignature.putString("title","签名");
                bdSignature.putInt("flag",2);//flag为1表示是修改呢称
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_SIGNATURE,bdSignature);
                break;
            default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_NICKNAME:
                if (data!=null){
                    new_info = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_nickName.setText(new_info);
                    //更新数据库中的呢称
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("nickName", new_info,spUserName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data!=null){
                    new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_signature.setText(new_info);
                    //更新数据库中的签名
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature", new_info,spUserName);
                }
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
        builder.show();
    }

    //更新界面上的性别数据
    private void setSex(String sex) {
        tv_sex.setText(sex);
        //更新数据库的性别数据
        DBUtils.getInstance(this).updateUserInfo("sex",sex,spUserName);
    }
}
