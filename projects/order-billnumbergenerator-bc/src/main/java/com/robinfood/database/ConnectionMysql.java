package com.robinfood.database;

import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.constants.MysqlConstants;
import com.robinfood.exceptions.DataBaseException;
import com.robinfood.mappers.ResponseMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.DEFAULT_BOOLEAN_TRUE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_CONNECTION;

public sealed class ConnectionMysql permits DatabaseManager {

    private Connection connection = null;

    public void connectionMYSQL() {

        try {
            DriverManager.setLoginTimeout(MysqlConstants.LOGIN_TIME_OUT_DB);
            Class.forName(MysqlConstants.DRIVER_CLASS_NAME_DB);
            connection = DriverManager.getConnection(
                    MysqlConstants.URL_DB,
                    MysqlConstants.USERNAME_DB,
                    MysqlConstants.PASSWORD_DB
            );
        } catch (Exception exception) {
            throw new DataBaseException(ResponseMapper
                    .buildWithError(
                            HttpStatusConstants.SC_CONFLICT.getCodeHttp(),
                            ERROR_CONNECTION.getMessage(),
                            DEFAULT_BOOLEAN_TRUE),
                    ERROR_CONNECTION.getMessage());
        }
    }

    public Connection getConnection() {
        if (Objects.isNull(connection)) {
            connectionMYSQL();
        }
        return connection;
    }

    public void closeConnection() {

    }
}
