package qj.ccmtjf.com.lib.entity;

import java.util.List;

/**
 * Created by tongqinyuan on 2018/3/5.
 */

public class ListData {

    /**
     * offset: 20,
     over: false,
     pageCount: 55,
     size: 20,
     total: 1084
     curPage: 2,
     datas:
     */

    public int offset;//: 20,
    public boolean over;//: false,
    public int pageCount;//: 55,
    public int size;//: 20,
    public int total;//: 1084
    public int curPage;//: 2,
    public List<ListItem> datas;//

    @Override
    public String toString() {
        return "ListData{" +
                "offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", curPage=" + curPage +
                ", datas=" + datas +
                '}';
    }
}
