package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
public class Notice {

    private int id;
    private String title;
    private String content;
//    private String counUser;

//    public void setCounUser(String counUser) {
//        this.counUser = counUser;
//    }
//
//    public String getCounUser() {
//
//        return counUser;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
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

}
