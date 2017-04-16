package news.crc.com.mynews.view.adapt;

import android.view.View;

/**
 * Created by LIYESHENG on 2017/4/15.
 */

public interface Adapter{
    boolean isEmpty();
    View getView(int position);
    int getCount();
}