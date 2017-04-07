package news.crc.com.mynews.load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.xutils.view.annotation.ContentView;

import java.util.Timer;
import java.util.TimerTask;

import news.crc.com.mynews.R;
import news.crc.com.mynews.guide.GuideActivity;
import news.crc.com.mynews.home.activity.HomeActivity;
import news.crc.com.mynews.util.SharedPreUtils;

@ContentView(R.layout.activity_index)
public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_index);
        /*
        *主界面停留2秒后进行判断是直接跳到 HomeActivity.class还是跳转到GuideActivity.class
        * */
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                if(SharedPreUtils.getBoolean(IndexActivity.this,"welcome_guide",false)){

                    Intent intent = new Intent(IndexActivity.this,HomeActivity.class);
                    // 标准模式在同一个APP中所有Activity都在同一个栈
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    // 启动主页面

                    startActivity(intent);

                    //System.exit(0);

                }else {
                    startActivity(new Intent(IndexActivity.this, GuideActivity.class));
                }
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("mytag","IndexActivity------------");
    }
}
