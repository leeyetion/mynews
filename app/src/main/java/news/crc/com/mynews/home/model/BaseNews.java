package news.crc.com.mynews.home.model;

import java.util.Date;

/**
 * Created by liyesheng on 2017/3/26.
 */

public class BaseNews {
    private String title;
    private String content;
    private String imageurl;
    private Date date;

    public BaseNews(String title, String content, String imageurl, Date date) {
        this.title = title;
        this.content = content;
        this.imageurl = imageurl;
        this.date = date;
    }
    public BaseNews() {

    }
    public BaseNews(String title, String imageurl, Date date) {
        this.title = title;
        this.imageurl = imageurl;
        this.date = date;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
