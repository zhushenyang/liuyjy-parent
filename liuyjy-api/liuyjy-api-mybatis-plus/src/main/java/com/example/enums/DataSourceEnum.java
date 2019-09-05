package com.example.enums;

public enum DataSourceEnum {
    /**
     * 主
     */
    DB_MASTER("dbmaster"),
    /**
     * 从
     */
    DB_SLAVE1("dbslave1");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
