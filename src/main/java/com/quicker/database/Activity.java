package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
public class Activity {

    private int id;
    private String title;
    private String content;
    private int clickNum;
    private String publishTime;
    private String belongDepar;

    public void setBelongDepar(String belongDepar) {
        this.belongDepar = belongDepar;
    }

    public String getBelongDepar() {

        return belongDepar;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishTime() {

        return publishTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getClickNum() {
        return clickNum;
    }
}


