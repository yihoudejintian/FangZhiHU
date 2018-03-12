package com.kotlinokhttp.entity

/**
 * Created by tongqinyuan on 2018/3/5.
 */

class ListItem {

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


    var apkLink: String? = null//: "",
    var author: String? = null//: "叶应是叶",
    var chapterId: Int = 0// 78,
    var chapterName: String? = null//: "性能优化",
    var collect: Boolean = false//: false,
    var courseId: Int = 0//: 13,
    var desc: String? = null//: "",
    var envelopePic: String? = null//: "",
    var id: Int = 0//: 2420,
    var link: String? = null//: "http://blog.csdn.net/new_one_object/article/details/69056294",
    var niceDate: String? = null//: "2018-02-27",
    var origin: String? = null//: "",
    var projectLink: String? = null//: "",
    var publishTime: String? = null//: 1519745606000,
    var title: String? = null//: "Android 内存泄漏分析",
    var visible: Int = 0//: 1,
    var zan: Int = 0//: 0

    override fun toString(): String {
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
                '}'
    }
}
