package news.crc.com.mynews.home.fragment.head.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.activity.HomeActivity;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.util.SharedPreUtils;
import news.crc.com.mynews.view.DragListView;
import news.crc.com.mynews.view.adapt.DragListAdapter;

public class MoreTabsActivity extends Activity {
    private DragListAdapter mAdapter = null;

    private ArrayList<RequestModel> list = null;
    private ArrayList<RequestModel> groupkey = new ArrayList<RequestModel>();
    private ArrayList<RequestModel> friendList = new ArrayList<RequestModel>();
    private ArrayList<RequestModel> moreList = new ArrayList<RequestModel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_more);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        initData();// 在对ListView操作前要先完成数据的初始化
        final DragListView dragListView = (DragListView) findViewById(R.id.other_drag_list);
        mAdapter = new DragListAdapter(this, list, groupkey, friendList, moreList);
        dragListView.setAdapter(mAdapter);


    }

    public void getBack(View view){
        Intent intent = new Intent(this,HomeActivity.class);
        // 标准模式在同一个APP中所有Activity都在同一个栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        // 启动主页面
        startActivity(intent);
    }

    public void getList(View view){
        List<RequestModel> flist= mAdapter.getfriendList();
        List<RequestModel> mlist= mAdapter.getMoreList();

        for(int i=0;i<mlist.size();i++){
            Log.i("mytag",mlist.get(i).getTableName());
        }
        Gson gson = new Gson();
        String news_Category = gson.toJson(flist);
        String news_More_Category = gson.toJson(mlist);
        SharedPreUtils.setString(this, "news_Category", news_Category);
        SharedPreUtils.setString(this, "news_More_Category", news_More_Category);

    }

    public void initData() {

        String news_Category =SharedPreUtils.getString(this, "news_Category", null);
        String news_More_Category =SharedPreUtils.getString(this, "news_More_Category", null);
        Gson gson = new Gson();
        List<RequestModel> rmlist=new ArrayList<>();
        if(news_Category!=null){
            rmlist = gson.fromJson(news_Category, new TypeToken<List<RequestModel>>() { }.getType());
        }

        List<RequestModel> mlist=new ArrayList<>();
        if (news_Category!=null){
            mlist = gson.fromJson(news_More_Category, new TypeToken<List<RequestModel>>() { }.getType());
        }

        list = new ArrayList<>();
        groupkey.add(new RequestModel("我关注的分类", 100, 15));
        groupkey.add(new RequestModel("更多分类", 100, 15));
        for (int i = 0; i < rmlist.size(); i++) {
            friendList.add(rmlist.get(i));
        }
        list.add(new RequestModel("我关注的分类", 100, 15));
        list.addAll(friendList);

        int n=0;
        if(mlist!=null){n=mlist.size();}
        for (int j = 0; j < n;j++) {
            moreList.add(mlist.get(j));
        }
        list.add(new RequestModel("更多分类", 100, 15));
        list.addAll(moreList);
    }
}
