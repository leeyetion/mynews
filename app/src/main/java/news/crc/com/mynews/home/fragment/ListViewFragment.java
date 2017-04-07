package news.crc.com.mynews.home.fragment;


import android.os.Handler;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.config.WebConfig;
import news.crc.com.mynews.home.http.NewListRequest;
import news.crc.com.mynews.home.model.BaseNews;
import news.crc.com.mynews.home.model.DataBean;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.home.model.WebNews;


/**
 * Created by liyesheng on 2017/3/26.
 */

public class ListViewFragment extends Fragment {

    ListView lv_news=null;
    List<WebNews> nlist=null;
    List<DataBean> datlist=null;

    MyAdapter adapter=new MyAdapter();

    Handler mRequestHandler= new Handler(){
        public void handleMessage(android.os.Message msg){

            datlist=(ArrayList<DataBean>)msg.obj;
            Log.i("mytag",msg.what+"-------------"+datlist.size());
            lv_news.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };

    public static ListViewFragment newInstance(RequestModel rm) {
        ListViewFragment newFragment = new ListViewFragment();
        Bundle bundle = new Bundle();
        String weburl= WebConfig.url+"?tableNum="+rm.getTableNum()+"&pagesize="+rm.getPageSize();
        bundle.putString("weburl", weburl);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_news, container, false);//关联布局文件

        lv_news=(ListView)view.findViewById(R.id.lv_news);

        nlist=new ArrayList<WebNews>();

        datlist=new ArrayList<DataBean>();
        datlist.add(new DataBean());

        //initweb();
        initwebs();

        return view;
    }



    //定义适配器
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datlist.size();
        }

        @Override
        public Object getItem(int i) {
            return datlist.get(datlist.size()-i-1);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ListViewFragment.ViewHolder viewHolder;

            if (view == null) {
                view = View.inflate(getActivity(),R.layout.list_news_item,null);
                viewHolder = new ListViewFragment.ViewHolder();
                viewHolder.siv_image=(SmartImageView) view.findViewById(R.id.siv_image);
                viewHolder.txt_title=(TextView)view.findViewById(R.id.txt_title);
                viewHolder.txt_from=(TextView)view.findViewById(R.id.txt_from);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ListViewFragment.ViewHolder) view.getTag();
            }
            DataBean news=(DataBean)getItem(i);
            //设置Item的值

            viewHolder.siv_image.setImageUrl(news.getTop_image());

            String title="";
            if(news.getTitle().length()>20){
                title=news.getTitle().substring(1,15)+"...";
            }else {
                title=news.getTitle();
            }

            viewHolder.txt_title.setText(title);

            viewHolder.txt_from.setText("来源："+news.getSource());

            return view;
        }
    }

    static class ViewHolder{

        SmartImageView siv_image;
        TextView txt_title;
        TextView txt_from;
    }



    private void initwebs(){
        Bundle bundle = getArguments();
        String weburl=bundle.getString("weburl");
        NewListRequest newListRequest=new NewListRequest(getActivity(),mRequestHandler);
        newListRequest.startConnection(weburl);

    }

}
