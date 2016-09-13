package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
public class StuNotice {

    private String id;
    private String noticeTitle;
    private String stuId;
    private int isRead;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }


    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getStuId() {
        return stuId;
    }

    public int getIsRead() {
        return isRead;
    }
}
