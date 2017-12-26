package android.gdmec.edu.cn.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by student on 17/12/26.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;

    }
}
