package news.crc.com.mynews.home.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.List;

import news.crc.com.mynews.R;
import news.crc.com.mynews.home.model.RequestModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCalendar extends Fragment {


    private PagerSlidingTabStrip tabs = null;

    List<Fragment> f_list = null;

    List<RequestModel> rmlist = null;
    private TextView tv_title=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_calendar, container, false);
        tv_title=(TextView)view.findViewById(R.id.tv_title);
        tv_title.setText(R.string.calendar_title);

        TextView tv_content=(TextView)view.findViewById(R.id.tv_content);
        tv_content.setText("时间链");
        return view;
    }






}
