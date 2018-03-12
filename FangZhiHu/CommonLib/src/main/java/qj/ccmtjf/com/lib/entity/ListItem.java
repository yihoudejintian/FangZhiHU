package qj.ccmtjf.com.lib.entity;

/**
 * Created by tongqinyuan on 2018/3/5.
 */

public class ListItem {

    /**
     * {
     * apkLink: "",
     * author: "叶应是叶",
     * chapterId: 78,
     * chapterName: "性能优化",
     * collect: false,
     * courseId: 13,
     * desc: "",
     * envelopePic: "",
     * id: 2420,
     * link: "http://blog.csdn.net/new_one_object/article/details/69056294",
     * niceDate: "2018-02-27",
     * origin: "",
     * projectLink: "",
     * publishTime: 1519745606000,
     * title: "Android 内存泄漏分析",
     * visible: 1,
     * zan: 0
     * },
     */


    public String apkLink;//: "",
    public String author;//: "叶应是叶",
    public int chapterId;// 78,
    public String chapterName;//: "性能优化",
    public boolean collect;//: false,
    public int courseId;//: 13,
    public String desc;//: "",
    public String envelopePic;//: "",
    public int id;//: 2420,
    public String link;//: "http://blog.csdn.net/new_one_object/article/details/69056294",
    public String niceDate;//: "2018-02-27",
    public String origin;//: "",
    public String projectLink;//: "",
    public String publishTime;//: 1519745606000,
    public String title;//: "Android 内存泄漏分析",
    public int visible;//: 1,
    public int zan;//: 0

    @Override
    public String toString() {
        return "ListItem{" +
                "apkLink='" + apkLink + '\'' +
                ", author='" + author + '\'' +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", title='" + title + '\'' +
                ", visible=" + visible +
                ", zan=" + zan +
                '}';
    }
}
