package com.quicker.database;

/**
 * Created by Nanguoyu on 2016/7/14.
 */
public class StuDepartment {

    private int id;
    private String username;
    private String password;
    private String college;
    private String departmentName;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getId() {

        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCollege() {
        return college;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
