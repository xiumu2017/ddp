
package com.paradise.ddp.entity;

import java.io.Serializable;

public class Student implements Serializable, Cloneable {
    private String name;
    private String memo;
    private Info info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.setInfo(this.info.clone());
        return student;
    }
}
