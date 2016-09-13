package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/7.
 */

public class StuExcel {
    private int id;
    private String stuId;
    private String excelName;
    private String tableName;
    private int isFinished;
    private int isReminded;
    private int isCollected;

    public void setId(int id) {
        this.id = id;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public void setIsReminded(int isReminded) {
        this.isReminded = isReminded;
    }

    public void setIsCollected(int isCollected) {
        this.isCollected = isCollected;
    }

    public int getId() {

        return id;
    }

    public String getStuId() {
        return stuId;
    }

    public String getExcelName() {
        return excelName;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public int getIsReminded() {
        return isReminded;
    }

    public int getIsCollected() {
        return isCollected;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {

        return tableName;
    }
}
