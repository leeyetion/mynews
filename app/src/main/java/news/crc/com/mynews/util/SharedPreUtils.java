package news.crc.com.mynews.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 实现key value 格式的数据存储到系统文件中，app中清楚出数据会清楚该类存储的数据
 * Created by liyesheng on 2017/3/26.
 */

public class SharedPreUtils {

    private final static String config = "new";

    // 设置boolean值

    /**
     * 设置boolean值保存到文件中
     * @param ctx 上下文
     * @param key 数据得主键
     * @param value 数据的值
     */
    public static void setBoolean(Context ctx, String key, Boolean value) {
        SharedPreferences ref = ctx.getSharedPreferences(config, Context.MODE_PRIVATE);
        ref.edit().putBoolean(key, value).commit();
    }

    /**
     * 根据key获取对应的值
     * @param ctx 上下文
     * @param key
     * @param defValue 如果获取失败设置一个默认值
     * @return 返回参数key对应的boolean,如果不存在返回参数defValue
     */
    public static boolean getBoolean(Context ctx, String key, Boolean defValue) {
        SharedPreferences ref = ctx.getSharedPreferences(config, Context.MODE_PRIVATE);
        return ref.getBoolean(key, defValue);
    }

    /**
     * 设置boolean值保存到文件中
     * @param ctx 上下文
     * @param key 数据得主键
     * @param value 数据的值
     */
    public static void setString(Context ctx, String key, String value) {
        SharedPreferences ref = ctx.getSharedPreferences(config, Context.MODE_PRIVATE);
        ref.edit().putString(key, value).commit();
    }

    /**
     * 根据key获取对应的值
     * @param ctx 上下文
     * @param key
     * @param defValue 如果获取失败设置一个默认值
     * @return 返回参数key对应的boolean,如果不存在返回参数defValue
     */
    public static String getString(Context ctx, String key,String defValue) {
        SharedPreferences ref = ctx.getSharedPreferences(config, Context.MODE_PRIVATE);
        return ref.getString(key,defValue);
    }


}
