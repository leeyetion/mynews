package news.crc.com.mynews.util;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import news.crc.com.mynews.home.fragment.head.ImageListViewFragment;
import news.crc.com.mynews.home.fragment.head.ReFreshListViewFragment;
import news.crc.com.mynews.home.model.RequestModel;
import news.crc.com.mynews.home.model.WebNews;

/**
 * Created by LIYESHENG on 2017-5-2.
 */

public class FragmentController {

    List<RequestModel> rmlist = null;
    List<Fragment> f_list = null;

    private int containerId;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    private static FragmentController controller;

    public static FragmentController getInstance(FragmentActivity activity, int containerId,List<RequestModel> rmlist,List<Fragment> f_list) {
        if (controller == null) {
            controller = new FragmentController(activity, containerId,rmlist,f_list);
        }
        return controller;
    }

    private FragmentController(FragmentActivity activity, int containerId,List<RequestModel> rmlist,List<Fragment> f_list) {
        this.f_list=f_list;
        this.rmlist=rmlist;
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment(activity);
    }

    private void initFragment(Context context) {

        f_list = new ArrayList<Fragment>();
        fragments = new ArrayList<Fragment>();
        for (int i = 0; i < rmlist.size(); i++) {
            if (i == 5) {
                f_list.add(ReFreshListViewFragment.newInstance(rmlist.get(i)));
            } else {
                f_list.add(ImageListViewFragment.newInstance(rmlist.get(i)));
            }
        }

        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commit();
    }

    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commit();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            if(fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
    public static void destoryController(){
        controller = null;
    }
}
