package news.crc.com.mynews.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import news.crc.com.mynews.R;
import news.crc.com.mynews.home.http.NewListRequest;
import news.crc.com.mynews.home.model.DataBean;
import news.crc.com.mynews.home.model.WebNews;
import news.crc.com.mynews.util.SharedPreUtils;
import news.crc.com.mynews.util.TimeUtils;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_calendar)
public class FragmentCalendar extends Fragment {

    List<DataBean> datlist=null;
    CalentdarAdpter adaper=new CalentdarAdpter();

    NewListRequest newListRequest=null;
    String  weburl="http://api.dagoogle.cn/news/get-news?tableNum=1&pagesize=15";

    @ViewInject(R.id.lv_content)
    private ListView lv_content;

    @ViewInject(R.id.pfl_main_refresh)
    private PtrClassicFrameLayout pfl_main_refresh;

    Handler mRequestHandler= new Handler(){
        public void handleMessage(android.os.Message msg){

            switch (msg.what){
                case 200:
                    datlist=(ArrayList<DataBean>)msg.obj;
                    Log.i("mytag",msg.what+"-------------"+datlist.size());
                    adaper.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "已刷新", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_SHORT).show();

            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initDate();
        return x.view().inject(this,inflater,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //获取相对布局
        RelativeLayout ViewLayout = (RelativeLayout) RelativeLayout.inflate(getActivity(),R.layout.fragment_calendar_list_header,null);
        TextView tv_date=(TextView) ViewLayout.findViewById(R.id.tv_date);
        TextView tv_nowday=(TextView)ViewLayout.findViewById(R.id.tv_nowday);
        SimpleDateFormat df = new SimpleDateFormat("MM月dd日 yyyy年");//设置日期格式yyyy-MM-dd HH:mm:ss
        //System.out.println();// new Date()为获取当前系统时间
        tv_date.setText(df.format(new Date()));


        lv_content.addHeaderView(ViewLayout);     //listview增加头文件

        lv_content.setAdapter(adaper);

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

                        newListRequest.startConnection(weburl);
                        //  mRequestHandler.sendMessage(Message.obtain());
                        pfl_main_refresh.refreshComplete();
                    }
                }, 1500);

            }
        });

    }

    class  CalentdarAdpter extends BaseAdapter{

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
            ViewHolder holder=null;
            if(view==null){
                view=View.inflate(getActivity(),R.layout.fragment_calendar_list_item,null);
                holder=new ViewHolder();
                holder.tv_date=(TextView)view.findViewById(R.id.tv_date);
                holder.tv_title=(TextView)view.findViewById(R.id.tv_title);
                holder.iv_image_news=(ImageView)view.findViewById(R.id.iv_image_news);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            DataBean date=(DataBean)getItem(i);

            holder.tv_date.setText(TimeUtils.changeTime(i));

            holder.tv_title.setText(date.getTitle());

            ImageOptions imageOptions=new ImageOptions.Builder()
                    .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                    .setImageScaleType(ImageView.ScaleType.FIT_XY)
                    .setFailureDrawableId(R.drawable.imge_test)
                    .setLoadingDrawableId(R.mipmap.ic_launcher)
                    .build();
            x.image().bind(holder.iv_image_news, date.getTop_image(), imageOptions);

            //holder.iv_image_news
            return view;
        }
    }

    class ViewHolder{
        TextView tv_date;
        TextView tv_title;
        ImageView iv_image_news;
    }

    private void initDate(){
        datlist=new ArrayList<DataBean>();
        newListRequest=new NewListRequest(getActivity(),mRequestHandler);
        if(SharedPreUtils.getString(getActivity(),weburl,null)==null){
            newListRequest.startConnection(weburl);//获取网络资源
        }else {

            Log.i("mytag",SharedPreUtils.getString(getActivity(),weburl,null));
            Gson gson = new Gson();
            WebNews webNew=new WebNews();
            webNew=gson.fromJson(SharedPreUtils.getString(getActivity(),weburl,null),
                    new TypeToken<WebNews>() {
                    }.getType());
            // ListView通过适配器模式加载数据

            int i=webNew.getData().size();
            datlist=webNew.getData();
        }
    }

}
