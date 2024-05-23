package com.robinfood.constants;

public class MysqlConstants {

    public static final String URL_DB = System.getenv("URL_DB");
    public static final String USERNAME_DB = System.getenv("USERNAME_DB");
    public static final String PASSWORD_DB = System.getenv("PASSWORD_DB");
    public static final String DRIVER_CLASS_NAME_DB = System.getenv("DRIVER_CLASS_NAME_DB");
    public static final int LOGIN_TIME_OUT_DB = Integer.parseInt(System.getenv("LOGIN_TIME_OUT_DB"));

    private MysqlConstants() {
        throw new IllegalStateException("Utility class");
    }
}
