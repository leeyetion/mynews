package news.crc.com.mynews.home.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.model.RequestModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDiscover extends Fragment {
    private WebView wv_content=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_headlineo, container, false);

        wv_content=(WebView)view.findViewById(R.id.wv_comtent);
        WebSettings settings=wv_content.getSettings();
        settings.setJavaScriptEnabled(true);
        //settings.seten.setPluginsEnabled(true);
        //设置自适应屏幕，两者合用
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //其他细节操作
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        settings.setAllowFileAccess(true); //设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式

        wv_content.loadUrl("file:///android_asset/apps/H53BD8E9F/www/headline.html");
        return view;
    }






}
