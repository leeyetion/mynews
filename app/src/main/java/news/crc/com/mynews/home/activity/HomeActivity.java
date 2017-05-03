package news.crc.com.mynews.home.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import news.crc.com.mynews.R;
import news.crc.com.mynews.home.fragment.FragmentAccount;
import news.crc.com.mynews.home.fragment.FragmentCalendar;
import news.crc.com.mynews.home.fragment.FragmentDiscover;
import news.crc.com.mynews.home.fragment.FragmentHeadline;
import news.crc.com.mynews.home.fragment.FragmentTelevision;

/**
 *
 */

public class HomeActivity extends FragmentActivity {
    FragmentHeadline fragmentHeadline = null;
    FragmentCalendar fragmentCalendar = null;
    FragmentTelevision fragmentTelevision = null;
    FragmentDiscover fragmentDiscover = null;
    FragmentAccount fragmentAccount = null;

  //  FragmentTransaction transaction = null;
    FragmentManager fm = null;

    private boolean isAppExit = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                isAppExit = false;
                return;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragmentHeadline = new FragmentHeadline();
        transaction.replace(R.id.fm_content, fragmentHeadline);
        transaction.commit();

    }


    public void btnAll(View view) {

        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentHeadline==null) {
            fragmentHeadline = new FragmentHeadline();
        }
        transaction.replace(R.id.fm_content, fragmentHeadline);

        transaction.commit();
        Toast.makeText(this, "btnAll被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnCalendar(View view) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragmentCalendar==null) {
            fragmentCalendar = new FragmentCalendar();
        }
        transaction.replace(R.id.fm_content, fragmentCalendar);
        transaction.commit();
        Toast.makeText(this, "btnCalendar被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnElectronics(View view) {
        FragmentTransaction transaction = fm.beginTransaction();
        fragmentTelevision = new FragmentTelevision();
        transaction.replace(R.id.fm_content, fragmentTelevision);
        transaction.commit();
        Toast.makeText(this, "btnElectronics被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnJewelry(View view) {
        FragmentTransaction transaction = fm.beginTransaction();
        fragmentDiscover = new FragmentDiscover();
        transaction.replace(R.id.fm_content, fragmentDiscover);
        transaction.commit();
        Toast.makeText(this, "btnJewelry被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnAccount(View view) {
        FragmentTransaction transaction = fm.beginTransaction();
        fragmentAccount = new FragmentAccount();
        transaction.replace(R.id.fm_content, fragmentAccount);
        transaction.commit();
        Toast.makeText(this, "btnAccount被单击", Toast.LENGTH_SHORT).show();
    }


    @Override //捕获KEYCODE_BACK事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppExit();
            return false;
        } else {
            //通过源码可知,正常会触发onBackPressed方法
            return super.onKeyDown(keyCode, event);
        }
    }

    public void AppExit() {
        if (!isAppExit) {
            isAppExit = true;
            Toast.makeText(this, "再按一次推出应用", Toast.LENGTH_SHORT).show();
            // 主线程Handler中还原AppExit的值
            //2秒后发送一个状态,1会赋值到 msg.what=what;
            handler.sendEmptyMessageDelayed(1, 2000);
        } else {//2s内再次按back时,isExit=true，执行以下操作，app退出
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("mytag","HomeActivity------------");
    }

}
