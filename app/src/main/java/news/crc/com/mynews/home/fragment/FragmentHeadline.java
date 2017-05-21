package news.crc.com.mynews.home.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import news.crc.com.mynews.R;

import news.crc.com.mynews.home.fragment.head.activity.MoreTabsActivity;
import news.crc.com.mynews.home.fragment.head.fragment.ImageListViewFragment;
import news.crc.com.mynews.home.fragment.head.fragment.ReFreshListViewFragment;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.util.DensityUtils;
import news.crc.com.mynews.util.SharedPreUtils;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_headline)
public class FragmentHeadline extends Fragment {


    @ViewInject(R.id.tabs)
    private PagerSlidingTabStrip tabs;

    @ViewInject(R.id.vp_fragement)
    private ViewPager vp_fragement;

    @ViewInject(R.id.iv_tabs_more)
    private ImageView iv_tabs_more;

    List<RequestModel> rmlist = null;

    String news_Category = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("mytag", "FragmentHeadline----->onCreateView----------------------" + inflater);
        return x.view().inject(this, inflater, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i("mytag", "FragmentHeadline---->onViewCreated----------------------" + view);

        List<Fragment> f_list = null;
        InitData();

        f_list = new ArrayList<Fragment>();
        for (int i = 0; i < rmlist.size(); i++) {
            if (rmlist.get(i).getTableNum() == 6) {
                f_list.add(ReFreshListViewFragment.newInstance(rmlist.get(i)));
            } else {
                f_list.add(ImageListViewFragment.newInstance(rmlist.get(i)));
            }
        }
        iv_tabs_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getActivity(), MoreTabsActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "iv_tabs_more", Toast.LENGTH_SHORT).show();
            }
        });


        //给ViewPager设置适配器,注fragment里面嵌套fragment此处要用getChildFragmentManager获得管理，否则会导致数据丢失
        vp_fragement.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), f_list));
        vp_fragement.setCurrentItem(0);
        // vp_fragement.setCurrentItem(0);//设置当前显示标签页为第一页

        //tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // 字体大小
        tabs.setTextSize(DensityUtils.dpi2px(getActivity(), 18));
        // 未选中状态下的字体颜色
        tabs.setTextColor(0x77000000);
        // 点击时候的背景颜色
        //tabs.setTabBackground(R.color.abc_background_cache_hint_selector_material_dark);
        // 选中状态下的字体颜色
        //tabs.setSelectedTabTextColor(R.color.colorAccent);

        // 滚动线的高度
        tabs.setIndicatorHeight(DensityUtils.dpi2px(getActivity(), (int) 5.0f));
        tabs.setIndicatorColor(Color.parseColor("#00AB94"));
        // 滚动线背景的高度
        tabs.setUnderlineHeight(DensityUtils.dpi2px(getActivity(), (int) 1.0f));
        tabs.setUnderlineColor(Color.parseColor("#EEEDEB"));

        // 标签分割线颜色
        tabs.setDividerColor(Color.TRANSPARENT);
        // 标签分割线边距
        tabs.setDividerPadding(DensityUtils.dpi2px(getActivity(), 9));
        tabs.setShouldExpand(true);
        // 绑定适配器
        // 是否可以滚动
        tabs.setViewPager(vp_fragement);

        tabs.setShouldExpand(false);


        //设置监听器，监听viewpager页面切换
        vp_fragement.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //切换过程执行的方法
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("mytag", "FragmentHeadline-->onPageScrolled" + "---------------");

            }

            //切换成功执行的方法
            @Override
            public void onPageSelected(int position) {
                Log.i("mytag", "FragmentHeadline-->onPageScrolled" + "---------------position--" + position);

            }

            //状态切换执行方法
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i("mytag", "FragmentHeadline-->onPageScrollStateChanged" + "---------------state--" + state);

            }
        });

    }

    private void InitData() {
        Gson gson = new Gson();
        news_Category = SharedPreUtils.getString(getActivity(), "news_Category", null);
        if (news_Category == null) {
            rmlist = new ArrayList<RequestModel>();
            rmlist.add(new RequestModel("头条", 1, 15));
            rmlist.add(new RequestModel("娱乐", 2, 15));
            rmlist.add(new RequestModel("军事", 3, 15));
            rmlist.add(new RequestModel("汽车", 4, 15));
            rmlist.add(new RequestModel("财经", 5, 15));
            rmlist.add(new RequestModel("笑话", 6, 15));
            rmlist.add(new RequestModel("体育", 7, 15));
            rmlist.add(new RequestModel("科技", 8, 15));
            String news_Category = gson.toJson(rmlist);
            SharedPreUtils.setString(getActivity(), "news_Category", news_Category);
        } else {
            rmlist = gson.fromJson(news_Category, new TypeToken<List<RequestModel>>() {
            }.getType());
        }

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> f_list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> f_list) {
            super(fm);
            this.f_list = f_list;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return rmlist.get(position).getTableName();
        }

        @Override
        public int getCount() {
            return f_list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return f_list.get(arg0);
        }
    }



}
