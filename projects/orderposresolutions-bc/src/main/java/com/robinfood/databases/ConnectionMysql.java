package com.robinfood.databases;

import com.robinfood.constants.MysqlConstants;
import com.robinfood.exceptions.DataBaseException;
import com.robinfood.mappers.ResponseMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import static com.robinfood.enums.DataBaseLogEnum.CONNECTION_CLOSED;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_CLOSED_CONNECTION;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_CONNECTION;

@Slf4j
public class ConnectionMysql {

    private Connection connection = null;

    public void connectionMYSQL() {

        try {
            Class.forName(MysqlConstants.DRIVER_CLASS_NAME_DB);
            connection = DriverManager.getConnection(
                    MysqlConstants.URL_DB,
                    MysqlConstants.USERNAME_DB,
                    MysqlConstants.PASSWORD_DB
            );
        } catch (Exception exception) {
            throw new DataBaseException(ResponseMapper
                    .buildWithError(
                            HttpStatus.SC_CONFLICT,
                            ERROR_CONNECTION.getMessage(),
                            Boolean.TRUE
                    ),
                    ERROR_CONNECTION.getMessage());
        }
    }

    public Connection getConnection() {
        if (Objects.isNull(connection)) {
            connectionMYSQL();
        }
        return connection;
    }

    @SneakyThrows
    public void closeConnection() {

        if (Objects.nonNull(connection)) {
            try {
                connection.close();
                connection = null;
                log.info(CONNECTION_CLOSED.getMessageWithCode());
            } catch (SQLException e) {
                log.error("Error :" + e);
                throw new DataBaseException(ResponseMapper
                        .buildWithError(
                                HttpStatus.SC_CONFLICT,
                                e.getMessage(),
                                Boolean.TRUE
                        ),
                        ERROR_CLOSED_CONNECTION.getMessage()
                );
            }
        }
    }
}
