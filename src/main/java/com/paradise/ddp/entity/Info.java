

package com.paradise.ddp.entity;

public class Info  implements Cloneable{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected Info clone() throws CloneNotSupportedException {
        return (Info)super.clone();
    }
}
