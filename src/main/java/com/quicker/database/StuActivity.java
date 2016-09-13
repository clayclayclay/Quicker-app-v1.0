package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/13.
 */
public class StuActivity {

    private int id;
    private String activityTitle;
    private String stuId;
    private int isCollected;

    public void setId(int id) {
        this.id = id;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setIsCollected(int isCollected) {
        this.isCollected = isCollected;
    }

    public int getId() {

        return id;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public String getStuId() {
        return stuId;
    }

    public int getIsCollected() {
        return isCollected;
    }
}
