package news.crc.com.mynews.home.model;

import java.util.List;

/**
 * Created by crcement on 2017/3/26.
 */



public class WebNews {


    /**
     * status : 200
     * error :
     * count : 2
     * data : [{"news_id":"47350","title":"贵州盘县发生岩层下滑致5人被困 1人已获救","top_image":"http://cms-bucket.nosdn.127.net/6fdf21debd9b40d7bcf1ac438b319d3b20170326202551.png","text_image0":"http://s.cimg.163.com/i/cms-bucket.nosdn.127.net/dfce01ebcc704928abecf3d3083935ac20170326202431.jpeg.170x220.auto.jpg","text_image1":"http://s.cimg.163.com/i/cms-bucket.nosdn.127.net/3eacae588caa43b98e0cb797632d65cb2017","source":"中国新闻网","content":"\n     中新网贵阳3月26日电 记者26日从贵州盘县消防获悉，经全力救援，贵州盘县山体滑坡中失踪的4人被陆续救出，但已无生命迹象。此次灾害致1人受伤，4人遇难。        图为灾害救援现场。许柯摄    灾害发生后，当地政府立即组织公安、消防官兵等300余人全力开展救援。9时53分，救援人员成功救出第1名被困人员，其生命体征平稳。    至17时18分，另外失踪的4人被陆续救出，但已无生命迹象。现场救援宣告结束。    图为灾害救援现场。许柯摄    目前，灾害原因正在进一步调查之中。    作者：蒲文思\n ","digest":"中新网贵阳3月26日电记者26日从贵州盘县消防获悉，经全力救援，贵州盘县山体滑坡中失踪的4人被陆续救出，但已无生命迹象。此次灾害致1人受伤，4人遇难。灾害发生后","reply_count":"64","edit_time":"1490510100"},{"news_id":"47349","title":"大连商人实名举报宝能系姚振华:掠夺数百亿元土地","top_image":"http://cms-bucket.nosdn.127.net/a5c2b9e4fd8947b6a434a82d2503fb2420170326201327.png","text_image0":"http://s.cimg.163.com/i/cms-bucket.nosdn.127.net/128607c6cee94673bd99918071b2145c20170326200850.jpeg.170x220.auto.jpg","text_image1":"http://s.cimg.163.com/i/cms-bucket.nosdn.127.net/e544d570f52c477e912d0e4b0e2e66172017","source":"甘肃新闻网","content":"\n     近日，一则\u201c大连商人实名举报姚振华\u201d的消息在网上传开。微博认证名为\u201c深圳市和信吉实业发展有限公司董事长陈谷嘉\u201d、网名为\u201c老海螺陈谷嘉\u201d    微博截图    近日，一则\u201c大连商人实名举报姚振华\u201d的消息在网上传开。微博认证名为\u201c深圳市和信吉实业发展有限公司董事长陈谷嘉\u201d、网名为\u201c老海螺陈谷嘉\u201d的博主发布了一则视频，题为：\u201c大连商人陈谷嘉对宪法起誓，视频实名举报宝能系姚振华、姚建辉兄弟与大晟文化（600892）惊天罪行！！！\u201d，陈谷嘉实名举报姚振华姚建辉及上市公司大晟文化的实际控制人周镇科、周镇宏兄弟。理由是\u201c他们于2001至2012年10余年间，相互勾结，对我公司合法财产通过违法手段，进行抢劫，掠夺我司价值数百亿元的深圳土地。\u201d截至记者发稿，该视频转发次数已经超过三万。    举报视频内容如下：<\/strong>                事实上，早在2016年，陈谷嘉就曾举行发布会举报姚建辉等。        \n \n         举报信    截止发稿时，尚未联系到双方对此回应。日前，保监会根据现场检查中发现的违法违规问题，依法对前海人寿及相关责任人进行了行政处罚。经查，前海人寿主要存在编制提供虚假材料、违规运用保险资金等问题。在深入开展调查取证的基础上，保监会严格按照有关法定程序，依据《中华人民共和国保险法》等法律法规对前海人寿及相关责任人员分别作出了警告、罚款、撤销任职资格及行业禁入等处罚措施。其中，对时任前海人寿董事长姚振华给予撤销任职资格并禁入保险业10年的处罚。\n ","digest":"近日，一则\u201c大连商人实名举报姚振华\u201d的消息在网上传开。微博认证名为\u201c深圳市和信吉实业发展有限公司董事长陈谷嘉\u201d、网名为\u201c老海螺陈谷嘉\u201d近日，一则\u201c大连商人实名举","reply_count":"3703","edit_time":"1490530440"}]
     */

    private int status;
    private String error;
    private int count;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


}
