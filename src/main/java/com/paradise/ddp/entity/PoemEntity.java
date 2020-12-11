package com.paradise.ddp.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dzhang
 */
@Getter
@Setter
public class PoemEntity {
    private String status;
    private String token;
    private String ipAddress;
    private Data data;

    @Override
    public String toString() {
        return "PoemEntity{" +
                "status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
