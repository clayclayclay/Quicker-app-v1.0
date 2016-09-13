package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/5.
 */
public class ExcelAndTable {

    private int id;
    private String excelName;
    private String tableName;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }



    public String getExcelName() {

        return excelName;
    }

    public String getTableName() {
        return tableName;
    }
}


