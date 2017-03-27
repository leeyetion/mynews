package news.crc.com.mynews.home.http;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import news.crc.com.mynews.config.WebConfig;
import news.crc.com.mynews.home.fragment.ListViewFragment;
import news.crc.com.mynews.home.model.DataBean;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.home.model.WebNews;

/**
 * Created by crcement on 2017/3/27.
 */

public class NewListRequest {

    protected Handler mRequestHandler;
    protected Context mContext;

    public NewListRequest(Context mContext, Handler mRequestHandler) {
        this.mContext=mContext;
        this.mRequestHandler=mRequestHandler;
    }



    public void startConnection( String weburl) {
        HttpUtils http = new HttpUtils();

        //String weburl= WebConfig.url+"?tableNum="+request.getTableNum()+"&pagesize="+request.getPageSize();
        Log.i("mytag","url:"+weburl);

        http.send(HttpRequest.HttpMethod.GET,
                weburl,
                new RequestCallBack<String>() {

                    @Override
                    // 从服务器返回了json字符串
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        // 获取返回的json数据
                        String json = responseInfo.result;
                        Log.i("mytag",json);
                        Gson gson = new Gson();
                        WebNews webNew=new WebNews();
                        webNew=gson.fromJson(json,
                                new TypeToken<WebNews>() {
                                }.getType());
                        // ListView通过适配器模式加载数据

                        int i=webNew.getData().size();
                        Log.i("mytag","--------"+i);
                        List<DataBean> datlist=webNew.getData();

                        mRequestHandler.sendMessage(mRequestHandler.obtainMessage(200, datlist));
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(mContext, "请求失败!",
                                Toast.LENGTH_LONG).show();
                        Log.i("mytag","error:"+error+"----msg"+msg);
                    }
                });

    }


}
