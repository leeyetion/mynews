package news.crc.com.mynews.home.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.model.RequestModel;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_account)
public class FragmentAccount extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.ll_history)
    private LinearLayout ll_history;

    @ViewInject(R.id.ll_delete_cache)
    private LinearLayout ll_delete_cache;

    @ViewInject(R.id.ll_my_collect)
    private LinearLayout ll_my_collect;

    @ViewInject(R.id.sw_important_push)
    private Switch sw_important_push;

    @ViewInject(R.id.sw_use_internet)
    private Switch sw_use_internet;

    @ViewInject(R.id.ll_option_feedback)
    private LinearLayout ll_option_feedback;

    @ViewInject(R.id.ll_check_version)
    private LinearLayout ll_check_version;

    @ViewInject(R.id.ll_abort)
    private LinearLayout ll_abort;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  View view=inflater.inflate(R.layout.fragment_account, container, false);

        //TextView tv_content=(TextView)view.findViewById(R.id.tv_content);
        // tv_content.setText("个人配置");
        return x.view().inject(this, inflater, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll_history.setOnClickListener(this);
        ll_delete_cache.setOnClickListener(this);
        ll_my_collect.setOnClickListener(this);
        sw_important_push.setOnClickListener(this);
        sw_use_internet.setOnClickListener(this);
        ll_option_feedback.setOnClickListener(this);
        ll_check_version.setOnClickListener(this);
        ll_abort.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_history:
                goHistory();
                break;
            case R.id.ll_delete_cache:
                deleteCache();
                break;
            case R.id.ll_my_collect:
                myCollect();
                break;
            case R.id.sw_important_push:
                importantPush();
                break;
            case R.id.sw_use_internet:
                useInternet();
                break;
            case R.id.ll_option_feedback:
                optioneedback();
                break;
            default:optioneedback();

        }
    }

    private void goHistory() {
        Toast.makeText(getActivity(), "历史纪录按钮", Toast.LENGTH_SHORT).show();
    }

    private void deleteCache() {
        Toast.makeText(getActivity(), "清空缓存数据", Toast.LENGTH_SHORT).show();
    }

    private void myCollect() {
        Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();
    }

    private void importantPush() {
        Toast.makeText(getActivity(), "重要消息推送", Toast.LENGTH_SHORT).show();
    }

    private void useInternet() {
        Toast.makeText(getActivity(), "使用网络开关", Toast.LENGTH_SHORT).show();
    }

    private void optioneedback() {
        Toast.makeText(getActivity(), "意见反馈", Toast.LENGTH_SHORT).show();
    }


}
