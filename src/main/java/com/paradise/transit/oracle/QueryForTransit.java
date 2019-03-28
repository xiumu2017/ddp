package com.paradise.transit.oracle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;

/**
 * 过境前台Oracle查询方法
 *
 * @author dzhang
 */
@Slf4j
public class QueryForTransit {

    private static Connection getConnection(OracleInfo oracleInfo) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(oracleInfo.getUrl(), oracleInfo.getUser(), oracleInfo.getPassword());
    }

    /**
     * 查询过境前台数据库最近一条号码数据的时间 appTime
     *
     * @param oracleInfo 数据库信息
     * @return String appTime
     */
    public static Long queryLastPushTime(OracleInfo oracleInfo) {
        paraCheck(oracleInfo);
        Long res = null;
        Date appDate = null;
        Date oracleSysDate = null;
        Connection connection = null;
        try {
            connection = getConnection(oracleInfo);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SqlConstant.QUERY_LAST_TIME);
            while (resultSet.next()) {
                appDate = resultSet.getDate(1);
            }
            log.info(String.valueOf(appDate));

            ResultSet set = statement.executeQuery(SqlConstant.QUERY_SYSDATE);
            while (set.next()) {
                oracleSysDate = set.getDate(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("close sql connection error :" + e.getLocalizedMessage());
                    log.error(e.getSQLState(), e);
                }
            }
        }
        if (appDate != null && oracleSysDate != null) {
            res = oracleSysDate.getTime() - appDate.getTime();
        }
        return res;
    }

    private static void paraCheck(OracleInfo info) {
        if (null == info) {
            throw new OracleInfoException("OracleInfo can't be null");
        } else if (StringUtils.isBlank(info.getUrl())) {
            throw new OracleInfoException("OracleInfo: url can't be null");
        } else if (StringUtils.isBlank(info.getUser())) {
            throw new OracleInfoException("OracleInfo: user can't be null");
        } else if (StringUtils.isBlank(info.getPassword())) {
            throw new OracleInfoException("OracleInfo: password can't be null");
        }
    }
}
