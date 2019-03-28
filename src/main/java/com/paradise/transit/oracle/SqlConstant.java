package com.paradise.transit.oracle;

class SqlConstant {
    static final String QUERY_LAST_TIME = "select *" +
            "  from (select t.apptime from TBL_SMS_MOBILE t order by t.apptime desc)" +
            " where rownum <= 1";

    static final String QUERY_COUNT_MOBILE = "";
    static final String QUERY_SYSDATE = "select sysdate from dual";
}
