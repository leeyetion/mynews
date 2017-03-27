package news.crc.com.mynews.home.model;

/**
 * Created by crcement on 2017/3/26.
 */

/*
tableNum 对应的板块结构代码：

    *        1 =>  头条
    *        2 =>  娱乐
    *        3 =>  军事
    *        4 =>  汽车
    *        5 =>  财经
    *        6 =>  笑话
    *        7 =>  体育
    *        8 =>  科技
    *

 */
public class RequestModel {
    private String tableName;
    private int tableNum;
    private  int pageSize;

    public RequestModel(String tableName, int tableNum, int pageSize) {
        this.tableName = tableName;
        this.tableNum = tableNum;
        this.pageSize = pageSize;
    }
    public RequestModel() {

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
