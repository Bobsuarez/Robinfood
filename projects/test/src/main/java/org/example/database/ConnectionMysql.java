package org.example.database;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.example.exceptions.DataBaseException;
import org.example.mappers.ResponseMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

import static org.example.enums.ErrorLogsEnum.ERROR_CONNECTION;

@Slf4j
public class ConnectionMysql {


    private Connection connection = null;

    public void connectionMYSQL() {

        try {
            DriverManager.setLoginTimeout(10);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://ou-db-rw.muydev.com:3306/routing_integrations?zeroDateTimeBehavior=convertToNull&enabledTLSProtocols=TLSv1.2",
                    "routing_integrations",
                    "Uz99cRzsVpwG8vSk"
            );
        } catch (Exception exception) {
            throw new DataBaseException(ResponseMapper
                    .buildWithError(
                            HttpStatus.SC_CONFLICT,
                            ERROR_CONNECTION.getMessage(),
                            Boolean.TRUE),
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
