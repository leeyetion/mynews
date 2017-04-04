package news.crc.com.mynews.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.activity.HomeActivity;
import news.crc.com.mynews.util.DensityUtils;
import news.crc.com.mynews.util.SharedPreUtils;

/**
 * 引导页面，用户第一次打开app进入该页面
 */
public class GuideActivity extends Activity {

    private ViewPager vp_guide=null;   //存放引导页4张图片
    private LinearLayout ll_dot=null;   //存放四个点水平布局
    private ImageView iv_dot_red=null;  //红点

    private List<View> iList=null;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();

        initData();

    }

    //开始体验按钮被单击事件
    public void start(View view){
        SharedPreUtils.setBoolean(this,"welcome_guide",true);
        Intent intent = new Intent(this,HomeActivity.class);
        // 标准模式在同一个APP中所有Activity都在同一个栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        // 启动主页面
        startActivity(intent);
    }

    private void initView() {
        vp_guide=(ViewPager)findViewById(R.id.vp_guide);
        ll_dot=(LinearLayout)findViewById(R.id.ll_dot);
        iv_dot_red=(ImageView)findViewById(R.id.iv_dot_red);
    }

    private void initData() {
        vp_guide.setAdapter(new MyAdapter());

        //初始化引导页图片上面的圆点
        for(int i=0;i<iList.size();i++){
            View dotView=new View(this);
            dotView.setBackgroundResource(R.drawable.guide_point_gray);
            int px= DensityUtils.dpi2px(this,10);//dp转px
            //设置view圆点的属性
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(px,px);
            if (i>0){
                params.leftMargin=DensityUtils.dpi2px(this,20);
            }
            dotView.setLayoutParams(params);
            ll_dot.addView(dotView);
        }


        //设置监听器，监听viewpager页面切换
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //切换过程执行的方法
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //切换成功执行的方法
            @Override
            public void onPageSelected(int position) {
                RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams)iv_dot_red.getLayoutParams();
                param.leftMargin = position * DensityUtils.dpi2px(GuideActivity.this,30);
                iv_dot_red.setLayoutParams(param);

            }

            //状态切换执行方法
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MyAdapter extends PagerAdapter{
        private int[] ids = null;

        public MyAdapter() {
            ids = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
            iList = new ArrayList<View>();
            // 创建一个ListView<ImageView>来存储图片,
            for (int i = 0; i < ids.length; i++) {
                ImageView imageView = new ImageView(GuideActivity.this);
                // 给当前ImageView设置背景图
                imageView.setBackgroundResource(ids[i]);
                iList.add(imageView);
            }
            iList.add(View.inflate(GuideActivity.this,R.layout.guide_start,null));
        }

        @Override  // 返回集合大小
        public int getCount() {
            return iList.size();
        }

        @Override // 实例化没一个Item,其实就是View
        public Object instantiateItem(ViewGroup container, int position) {
            View view = (View) iList.get(position);
            Log.i("jxy", "当前的ViewPager对象:" + container + ",position:" + position + ",ImageView:" + view);
            // 返回之前必须把当前View对象添加到容器中
            container.addView(view); // lv.addView(view); 只能使用适配器
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("jxy", "当前销毁的对象:" + object);
            container.removeView((View) object);
        }

        @Override  //
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
