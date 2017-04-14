package news.crc.com.mynews.util;

/**
 * Created by LIYESHENG on 2017/4/14.
 */

public class TimeUtils {
    public static String changeTime(int i){
        int time =(i*i*100/60);
        String strTime="";
        if(time<60){
            strTime=time+"分钟前";
        }else if(time<(60*60)){
            strTime=(int)time/60+"小时前";
        }else {
            strTime="1天以前";
        }
        return strTime;
    }
}
