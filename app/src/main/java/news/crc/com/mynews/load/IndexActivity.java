package news.crc.com.mynews.load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import news.crc.com.mynews.R;
import news.crc.com.mynews.guide.GuideActivity;
import news.crc.com.mynews.home.HomeActivity;
import news.crc.com.mynews.util.SharedPreUtils;

public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                if(SharedPreUtils.getBoolean(IndexActivity.this,"welcome_guide",false)){

                    Intent intent = new Intent(IndexActivity.this,HomeActivity.class);
                    // 标准模式在同一个APP中所有Activity都在同一个栈
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    // 启动主页面
                    startActivity(intent);
                }else {
                    startActivity(new Intent(IndexActivity.this, GuideActivity.class));
                }
            }
        },2000);
    }
}
