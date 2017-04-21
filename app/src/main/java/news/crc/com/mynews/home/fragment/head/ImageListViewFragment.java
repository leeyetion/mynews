package news.crc.com.mynews.home.fragment.head;


import android.os.Handler;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import news.crc.com.mynews.R;
import news.crc.com.mynews.config.WebConfig;
import news.crc.com.mynews.home.http.NewListRequest;
import news.crc.com.mynews.home.model.DataBean;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.home.model.WebNews;
import news.crc.com.mynews.view.CarouselView;


/**
 * Created by liyesheng on 2017/3/26.
 */

public class ImageListViewFragment extends Fragment {

    ListView lv_news=null;
    List<WebNews> nlist=null;
    List<DataBean> datlist=null;


    private LayoutInflater mHeadInflater;//ListView头视图

    private PtrClassicFrameLayout pfl_main_refresh;  //下拉刷新空间

    MyAdapter adapter=new MyAdapter();

    Handler mRequestHandler= new Handler(){
        public void handleMessage(android.os.Message msg){

            datlist=(ArrayList<DataBean>)msg.obj;
            Log.i("mytag",msg.what+"-------------"+datlist.size());
            lv_news.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };

    public static ImageListViewFragment newInstance(RequestModel rm) {
        ImageListViewFragment newFragment = new ImageListViewFragment();
        Bundle bundle = new Bundle();
        String weburl= WebConfig.url+"?tableNum="+rm.getTableNum()+"&pagesize="+rm.getPageSize();
        bundle.putString("weburl", weburl);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initDate();

        View view = inflater.inflate(R.layout.fragment_headline_list, container, false);//关联布局文件

        lv_news=(ListView)view.findViewById(R.id.lv_news);

        mHeadInflater = LayoutInflater.from(getActivity());

        View headView = mHeadInflater.inflate(R.layout.fragment_headline_list_header,null);

        CarouselView carouselView = (CarouselView)headView.findViewById(R.id.cv_mycview);

        carouselView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(280)));

        lv_news.addHeaderView(carouselView);

        carouselView.setAdapter(new CarouselView.Adapter() {
            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public View getView(int position) {
                View view = mHeadInflater.inflate(R.layout.fragment_headline_list_header_item,null);
                ImageView imageView = (ImageView) view.findViewById(R.id.image);
                TextView tv_title=(TextView)view.findViewById(R.id.tv_title);

                ImageOptions imageOptions=new ImageOptions.Builder()
                        .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                        .setImageScaleType(ImageView.ScaleType.FIT_XY)
                        .setFailureDrawableId(R.drawable.imge_test)
                        .setLoadingDrawableId(R.mipmap.ic_launcher)
                        .build();
                x.image().bind(imageView,datlist.get(datlist.size()-1-position).getTop_image(), imageOptions);
                tv_title.setText(datlist.get(datlist.size()-1-position).getTitle());

               // imageView.setImageResource(mImagesSrc[position]);
                return view;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });





        pfl_main_refresh=(PtrClassicFrameLayout)view.findViewById(R.id.pfl_main_refresh);
        pfl_main_refresh.setLastUpdateTimeRelateObject(this);
        pfl_main_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content, header);
                //return false;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pfl_main_refresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        newListRequest.startConnection(weburl);
                        //  mRequestHandler.sendMessage(Message.obtain());
                        pfl_main_refresh.refreshComplete();
                    }
                }, 1500);

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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

            ImageListViewFragment.ViewHolder viewHolder;

            if (view == null) {
                view = View.inflate(getActivity(),R.layout.fragment_headline_list_item,null);
                viewHolder = new ImageListViewFragment.ViewHolder();
                viewHolder.siv_image=(ImageView) view.findViewById(R.id.siv_image);
                viewHolder.txt_title=(TextView)view.findViewById(R.id.txt_title);
                viewHolder.txt_from=(TextView)view.findViewById(R.id.txt_from);
                view.setTag(viewHolder);
            }else{
                viewHolder = (ImageListViewFragment.ViewHolder) view.getTag();
            }
            DataBean news=(DataBean)getItem(i);

            ImageOptions imageOptions=new ImageOptions.Builder()
                    .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                    .setImageScaleType(ImageView.ScaleType.FIT_XY)
                    .setFailureDrawableId(R.drawable.imge_test)
                    .setLoadingDrawableId(R.mipmap.ic_launcher)
                    .build();
            //设置Item的值
            x.image().bind( viewHolder.siv_image,news.getTop_image(), imageOptions);

            String title="";
            if(news.getTitle().length()>35){
                title=news.getTitle().substring(0,34)+"...";
            }else {
                title=news.getTitle();

            }

            viewHolder.txt_title.setText(title);

            viewHolder.txt_from.setText("来源："+news.getSource());

            return view;
        }
    }

    static class ViewHolder{

        ImageView siv_image;
        TextView txt_title;
        TextView txt_from;
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
