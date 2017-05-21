package news.crc.com.mynews.home.fragment.head.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import news.crc.com.mynews.R;
import news.crc.com.mynews.config.WebConfig;
import news.crc.com.mynews.home.http.NewListRequest;
import news.crc.com.mynews.home.model.DataBean;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.home.model.WebNews;
import news.crc.com.mynews.view.ReFreshListView;


/**
 * Created by liyesheng on 2017/3/26.
 */

public class ReFreshListViewFragment extends Fragment {

    ReFreshListView lv_news=null;
    List<WebNews> nlist=null;
    List<DataBean> datlist=null;


    private LayoutInflater mHeadInflater;//ListView头视图

    private PtrClassicFrameLayout pfl_main_refresh;  //下拉刷新空间

    MyAdapter adapter=new MyAdapter();

    Handler mRequestHandler= new Handler(){
        public void handleMessage(android.os.Message msg){

            datlist=(ArrayList<DataBean>)msg.obj;

            lv_news.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            lv_news.endPulldownToRefresh();
        }
    };

    public static ReFreshListViewFragment newInstance(RequestModel rm) {
        ReFreshListViewFragment newFragment = new ReFreshListViewFragment();
        Bundle bundle = new Bundle();
        String weburl= WebConfig.url+"?tableNum="+rm.getTableNum()+"&pagesize="+rm.getPageSize();
        Log.i("mytag","ReFreshListViewFragment----->newInstance----->"+weburl);
        bundle.putString("weburl", weburl);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("mytag","ReFreshListViewFragment----->onCreateView----->"+inflater);
        initDate();

        View view = inflater.inflate(R.layout.fragment_headline_refresh_list, container, false);//关联布局文件

        lv_news=(ReFreshListView)view.findViewById(R.id.lv_news);
        lv_news.setAdapter(new MyAdapter());
        lv_news.setRefreshListener(new ReFreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDate();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("mytag","ReFreshListViewFragment----->onViewCreated----->"+view);
        super.onViewCreated(view, savedInstanceState);

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

            ReFreshListViewFragment.ViewHolder viewHolder;

            if (view == null) {
                view = View.inflate(getActivity(),R.layout.fragment_headline_refresh_list_item,null);
                viewHolder = new ReFreshListViewFragment.ViewHolder();

                viewHolder.tv_content=(TextView)view.findViewById(R.id.tv_content);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ReFreshListViewFragment.ViewHolder) view.getTag();
            }
            DataBean news=(DataBean)getItem(i);
            viewHolder.tv_content.setText(news.getDigest());


            return view;
        }
    }

    static class ViewHolder{
        TextView tv_content;
    }



    private void initwebs(){
        Bundle bundle = getArguments();
        String weburl=bundle.getString("weburl");
        NewListRequest newListRequest=new NewListRequest(getActivity(),mRequestHandler);
        newListRequest.startConnection(weburl);

    }

    private void initDate(){
        nlist=new ArrayList<WebNews>();
        datlist=new ArrayList<DataBean>();
        //initweb();
        initwebs();
    }


}
