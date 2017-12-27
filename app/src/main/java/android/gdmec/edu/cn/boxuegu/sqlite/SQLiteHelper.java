package android.gdmec.edu.cn.boxuegu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 17/12/27.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "bxg.db";
    private static final String U_USERINFO = "userinfo";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建个人信息表
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINREMENT, "
                + "userName VARCHAR, "
                + "nickName VARCHAR, "
                + "sex VARCHAR, "
                + "signature VARCHAR"
                + ")" );
    }


    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISIS " + U_USERINFO);
        onCreate(db);
    }
}
