package org.example.database;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.example.ObjectMapperSingletonUtil;
import org.example.enums.DataBaseLogEnum;
import org.example.exceptions.DataBaseException;
import org.example.mappers.ResponseMapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.example.enums.DataBaseLogEnum.INSERTED_ID_RETURN;
import static org.example.enums.DataBaseLogEnum.INSERTED_ROWS;
import static org.example.enums.ErrorLogsEnum.ERROR_INSERT_DATA;
import static org.example.enums.ErrorLogsEnum.ERROR_INSERT_NOT_COMPLETED;


@Slf4j
public class DatabaseManager extends ConnectionMysql {

    private static void setParameters(
            PreparedStatement preparedStatement,
            Map<String, Object> params
    ) throws SQLException {
        int index = 1;
        for (String key : params.keySet()) {
            Object paramValue = params.get(key);
            preparedStatement.setObject(index, paramValue);
            index++;
        }
    }

    private static <T> T mapResultSetToObject(
            ResultSet resultSet,
            Class<T> resultClass
    ) throws SQLException {

        try {
            T resultObject = resultClass.getDeclaredConstructor().newInstance();

            Field[] fields = resultClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = resultSet.getObject(field.getName());
                field.set(resultObject, value);
            }
            return resultObject;

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new SQLException("Failed to map ResultSet to object", e);
        }
    }

    public <T> T executeQuery(String sql, Class<T> resultClass) throws DataBaseException {
        return executeQuery(sql, resultClass, Collections.emptyMap());
    }

    public <T> List<T> executeQueryList(String sql, Class<T> resultClass) throws DataBaseException {
        return executeQueryList(sql, resultClass, Collections.emptyMap());
    }

    public <T> List<T> executeQueryList(
            String sql, Class<T> resultClass,
            Map<String, Object> params
    ) throws DataBaseException {

        List<T> resultList = new ArrayList<>();

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            setParameters(preparedStatement, params);

            log.info(DataBaseLogEnum.EXECUTE_QUERY.getMessageWithCode(), preparedStatement.toString());

            log.info(DataBaseLogEnum.EXECUTE_PARAMETERS.getMessageWithCode(),
                    ObjectMapperSingletonUtil.objectToJson(params));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T resultObject = mapResultSetToObject(resultSet, resultClass);
                    resultList.add(resultObject);
                }
            }
        } catch (Exception e) {
            throw new DataBaseException(e);
        } finally {
            closeConnection();
        }
        return resultList;
    }

    public <T> T executeQuery(
            String sql, Class<T> resultClass,
            Map<String, Object> params
    ) throws DataBaseException {

        T resultList = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            setParameters(preparedStatement, params);

            log.info(DataBaseLogEnum.EXECUTE_QUERY.getMessageWithCode(), preparedStatement.toString());

            log.info(DataBaseLogEnum.EXECUTE_PARAMETERS.getMessageWithCode(),
                    ObjectMapperSingletonUtil.objectToJson(params));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultList = mapResultSetToObject(resultSet, resultClass);
                }
            }
        } catch (Exception e) {
            throw new DataBaseException(e);
        } finally {
            closeConnection();
        }
        return resultList;
    }

    public BigInteger executeInsert(
            String sql,
            Map<String, Object> params
    ) {

        final int columnIndexOne = 1;

        BigInteger getIdInserted = new BigInteger(String.valueOf(-1));

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS
        )) {

            log.info(DataBaseLogEnum.EXECUTE_QUERY.getMessageWithCode(), preparedStatement.toString());

            log.info(DataBaseLogEnum.EXECUTE_PARAMETERS.getMessageWithCode(),
                    ObjectMapperSingletonUtil.objectToJson(params));

            setParameters(preparedStatement, params);

            final int affectedRows = preparedStatement.executeUpdate();

            log.info(INSERTED_ROWS.getMessageWithCode(), affectedRows);

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

                    if (generatedKeys.next()) {
                        getIdInserted = (BigInteger) generatedKeys.getObject(columnIndexOne);
                        log.info(INSERTED_ID_RETURN.getMessageWithCode(), getIdInserted);
                    }
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {

            throw new DataBaseException(ResponseMapper
                    .buildWithError(
                            HttpStatus.SC_CONFLICT,
                            e.getMessage(),
                            true),
                    e.getMessage());

        } catch (SQLException e) {
            throw new DataBaseException(ResponseMapper
                    .buildWithError(
                            HttpStatus.SC_CONFLICT,
                            ERROR_INSERT_DATA.getMessage(),
                            true),
                    ERROR_INSERT_DATA.getMessage());

        } finally {
            closeConnection();
        }
        return getIdInserted;
    }
}
