package com.paradise.ddp.entity;

/**
 * @author dzhang
 */
@lombok.Data
public class PoemEntity {
    private String status;
    private String token;
    private String ipAddress;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

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
