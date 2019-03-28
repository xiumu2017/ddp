package com.paradise.transit.oracle;

import lombok.Data;

/**
 * @author dzhang
 */
@Data
public class OracleInfo {
    private String ip;
    private String port;
    private String url;
    private String user;
    private String password;

    public OracleInfo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public OracleInfo() {
    }
}
