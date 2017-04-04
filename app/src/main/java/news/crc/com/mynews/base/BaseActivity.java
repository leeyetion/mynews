package news.crc.com.mynews.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import news.crc.com.mynews.R;

public abstract class  BaseActivity extends FragmentActivity {

    LayoutInflater mLayoutInfilater=null;
    LinearLayout ll_nain=null;
    TextView tv_title=null;

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
        setContentView(R.layout.activity_base);

        init();

        initView(savedInstanceState);
    }


    private  void init(){
        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_title.setText(setTittle());
        ll_nain=(LinearLayout)findViewById(R.id.ll_main);
        mLayoutInfilater = LayoutInflater.from(this);

        View v = mLayoutInfilater.inflate(getLayoutId(), null);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ll_nain.addView(v);
    }


    /**
     * 对界面做初始化工作
     */
    public abstract void initView(Bundle bundle);

    /**
     * 获取填充在ll_main中的布局ID
     */
    protected abstract int getLayoutId();

    /**
     * 设置中间tittle的值
     *
     */
    public abstract int setTittle();

    public void btnAll(View view){
        Toast.makeText(this,"btnAll被单击",Toast.LENGTH_SHORT).show();
    }
    public void btnCalendar(View view){
        Toast.makeText(this,"btnCalendar被单击",Toast.LENGTH_SHORT).show();
    }
    public void btnElectronics(View view){
        Toast.makeText(this,"btnElectronics被单击",Toast.LENGTH_SHORT).show();
    }
    public void btnJewelry(View view){
        Toast.makeText(this,"btnJewelry被单击",Toast.LENGTH_SHORT).show();
    }
    public void btnAccount(View view){
        Toast.makeText(this,"btnAccount被单击",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "再按一次推出app", Toast.LENGTH_SHORT).show();
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
