package news.crc.com.mynews.home.activity;

import android.app.Activity;
import android.os.Bundle;

import news.crc.com.mynews.R;
import news.crc.com.mynews.base.BaseActivity;

public class CalendarActivity extends BaseActivity {



    @Override
    public void initView(Bundle bundle) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    public int setTittle() {
        return R.string.calendar_title;
    }
}
