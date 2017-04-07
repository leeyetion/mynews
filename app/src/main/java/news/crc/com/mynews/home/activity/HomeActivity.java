package news.crc.com.mynews.home.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;


import news.crc.com.mynews.R;
import news.crc.com.mynews.home.fragment.FragmentAccount;
import news.crc.com.mynews.home.fragment.FragmentCalendar;
import news.crc.com.mynews.home.fragment.FragmentDiscover;
import news.crc.com.mynews.home.fragment.FragmentHeadline;
import news.crc.com.mynews.home.fragment.FragmentTelevision;

public class HomeActivity extends FragmentActivity {
    FragmentHeadline fragmentHeadline = null;
    FragmentCalendar fragmentCalendar = null;
    FragmentTelevision fragmentTelevision = null;
    FragmentDiscover fragmentDiscover = null;
    FragmentAccount fragmentAccount = null;

    FragmentTransaction transaction = null;
    FragmentManager fm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        fragmentHeadline = new FragmentHeadline();
        transaction.replace(R.id.fm_content, fragmentHeadline);
        transaction.commit();

    }


    public void btnAll(View view) {

        // 开启Fragment事务
        transaction = fm.beginTransaction();
        fragmentHeadline = new FragmentHeadline();
        transaction.replace(R.id.fm_content, fragmentHeadline);
        transaction.commit();
        Toast.makeText(this, "btnAll被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnCalendar(View view) {
        transaction = fm.beginTransaction();
        fragmentCalendar = new FragmentCalendar();
        transaction.replace(R.id.fm_content, fragmentCalendar);
        transaction.commit();
        Toast.makeText(this, "btnCalendar被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnElectronics(View view) {
        transaction = fm.beginTransaction();
        fragmentTelevision = new FragmentTelevision();
        transaction.replace(R.id.fm_content, fragmentTelevision);
        transaction.commit();
        Toast.makeText(this, "btnElectronics被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnJewelry(View view) {
        transaction = fm.beginTransaction();
        fragmentDiscover = new FragmentDiscover();
        transaction.replace(R.id.fm_content, fragmentDiscover);
        transaction.commit();
        Toast.makeText(this, "btnJewelry被单击", Toast.LENGTH_SHORT).show();
    }

    public void btnAccount(View view) {
        transaction = fm.beginTransaction();
        fragmentAccount = new FragmentAccount();
        transaction.replace(R.id.fm_content, fragmentAccount);
        transaction.commit();
        Toast.makeText(this, "btnAccount被单击", Toast.LENGTH_SHORT).show();
    }

}
