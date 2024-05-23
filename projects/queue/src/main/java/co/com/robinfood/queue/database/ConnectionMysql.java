package co.com.robinfood.queue.database;

import co.com.robinfood.queue.Exceptions.ApplicationException;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@NoArgsConstructor
@Slf4j
public class ConnectionMysql {

    private static Connection connection = null;

    private static String urlBd;
    private static String userBd;
    private static String passBd;

    public ConnectionMysql(String urlBd, String userBd, String passBd) {
        this.urlBd = urlBd;
        this.userBd = userBd;
        this.passBd = passBd;
        this.connectionMYSQL();
    }

    public void connectionMYSQL() {

        try {
            DriverManager.setLoginTimeout(15);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    urlBd,
                    userBd,
                    passBd
            );

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ApplicationException(-2000, "Error de conexión");
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection.isClosed()) {
            connectionMYSQL();
        }
        return connection;
    }

    public static void closeConnection() {

        if (Objects.isNull(connection)) {
            return;
        }
        try {
            connection.close();
            log.info("Connection closed");
        } catch (Exception exception) {
            log.error("Error de conexión closed {}", exception.getMessage());
        }
    }
}
