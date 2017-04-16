package news.crc.com.mynews.home.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.fragment.head.ListViewFragment;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.util.DensityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHeadlinebak extends Fragment {


    private PagerSlidingTabStrip tabs = null;
    private ViewPager vp_fragement = null;

    List<Fragment> f_list = null;

    List<RequestModel> rmlist = null;
    public FragmentHeadlinebak() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_headline, container, false);
        tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        vp_fragement = (ViewPager) view.findViewById(R.id.vp_fragement);

        InitData();

        return view;
    }


    private void InitData() {
        rmlist = new ArrayList<RequestModel>();
        rmlist.add(new RequestModel("头条", 1, 15));
        rmlist.add(new RequestModel("娱乐", 2, 15));
        rmlist.add(new RequestModel("军事", 3, 15));
        rmlist.add(new RequestModel("汽车", 4, 15));
        rmlist.add(new RequestModel("财经", 5, 15));
        rmlist.add(new RequestModel("笑话", 6, 15));
        rmlist.add(new RequestModel("体育", 7, 15));
        rmlist.add(new RequestModel("科技", 8, 15));

        f_list = new ArrayList<Fragment>();
        for (int i = 0; i < rmlist.size(); i++) {
            f_list.add(ListViewFragment.newInstance(rmlist.get(i)));
        }
        //给ViewPager设置适配器
        vp_fragement.setAdapter(new MyFragmentPagerAdapter(getFragmentManager(), f_list));
        vp_fragement.setCurrentItem(0);//设置当前显示标签页为第一页


        //tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // 字体大小
        tabs.setTextSize(DensityUtils.dpi2px(getActivity(), 18));
        // 未选中状态下的字体颜色
        tabs.setTextColor(0x77000000);
        // 点击时候的背景颜色
        // tabStrip.setTabBackground(R.color.transparent);
        // 选中状态下的字体颜色
        //tabStrip.setSelectedTabTextColor(R.color.colorAccent);

        // 滚动线的高度
        tabs.setIndicatorHeight(DensityUtils.dpi2px(getActivity(), (int) 5.0f));
        tabs.setIndicatorColor(Color.parseColor("#00AB94"));
        // 滚动线背景的高度
        tabs.setUnderlineHeight(DensityUtils.dpi2px(getActivity(), (int) 1.0f));
        tabs.setUnderlineColor(Color.parseColor("#EEEDEB"));

        // 标签分割线颜色
        tabs.setDividerColor(Color.TRANSPARENT);
        // 标签分割线边距
        tabs.setDividerPadding(DensityUtils.dpi2px(getActivity(), (int) 1.0f));
        tabs.setShouldExpand(true);
        // 绑定适配器
        // 是否可以滚动

        tabs.setViewPager(vp_fragement);
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
