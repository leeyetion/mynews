package news.crc.com.mynews.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.google.gson.Gson;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.model.DataBean;

public class NewsDetailActivity extends Activity {

    private TextView tv_title=null;
    private TextView tv_content=null;
    private BridgeWebView wv_content=null;

    private Bundle mBundle=null;
    private DataBean mDataBean=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Bundle bundle = this.getIntent().getExtras();
        DataBean mDataBean=(DataBean)bundle.get("mDataBean");
        Log.i("mytag","新闻标题为："+mDataBean.getTitle());
        Log.i("mytag","新闻内容为"+mDataBean.getContent());
        wv_content=(BridgeWebView)findViewById(R.id.wv_content);
        WebSettings settings=wv_content.getSettings();
        settings.setJavaScriptEnabled(true);
        wv_content.loadUrl("file:///android_asset/wwww/new_detail.html");
        //wv_content.addJavascriptInterface(new AndriodAndH5(),"AndriodClass");
        Gson gson = new Gson();
        String msg=gson.toJson(mDataBean);
        Log.i("mytag","新闻json"+msg);




        wv_content.callHandler("functionInJ",msg, new CallBackFunction() {
            @Override
            public void onCallBack(String data) {

            }
        });

    }

    public void inCallWeb(View view){
        wv_content.loadUrl("javascript:andriodCallWeb('abcd')");
    }

    @Override
    protected void onDestroy() {
        wv_content.destroy();
        super.onDestroy();

    }
    class AndriodAndH5{
        @JavascriptInterface
        public void callMathod(String msg){
            Log.i("mytage","消息为"+msg);
        }
    }
}
