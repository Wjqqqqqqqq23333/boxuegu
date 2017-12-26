package android.gdmec.edu.cn.boxuegu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private FrameLayout mBodyLayout;
    private LinearLayout mBottomLayout;
    private RelativeLayout mCourseBtn;
    private TextView tv_course;
    private RelativeLayout mExerisesBtn;
    private RelativeLayout mMyInfoBtn;
    private TextView tv_exercises;
    private TextView tv_myinfo;
    private ImageView iv_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initBottomBar();
        setListener();

    }
    private void setListener(){
        for(int i=0;i<mBottomLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setOnClickListener(this);
        }
    }
    private void initBottomBar(){
         mBottomLayout = (LinearLayout)findViewById(R.id.main_bottom_bar);
         mCourseBtn = (RelativeLayout)findViewById(R.id.bottom_bar_course_btn);
         mExerisesBtn = (RelativeLayout)findViewById(R.id.bottom_bar_exercises_btn);
         mMyInfoBtn= (RelativeLayout)findViewById(R.id.bottom_bar_myinfo_btn);

        tv_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        tv_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        tv_myinfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);

        iv_course = (ImageView)findViewById(R.id.bottom_bar_image_course);
    }
    private void init(){
        tv_back = ((TextView) findViewById(R.id.tv_back));
        tv_main_title = ((TextView) findViewById(R.id.tv_main_title));
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }
    private void initBodyLayout(){
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //课程的点击事件
            case R.id.bottom_bar_course_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            //习题的点击事件
            case R.id.bottom_bar_exercises_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            //我的点击事件
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
        }
    }

    private void selectDisplayView(int i) {
    }

    private void clearBottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myinfo.setTextColor(Color.parseColor("#666666"));

        iv_course
    }
}
//111