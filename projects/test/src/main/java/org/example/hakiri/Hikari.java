package org.example.hakiri;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.example.ObjectMapperSingletonUtil;
import org.example.dtos.createeventflow.request.EventRequestDTO;
import org.example.entities.FlowEventLogsEntity;
import org.example.mappers.request.EventRequestDTOToEntityMapper;
import org.example.repository.floweventlogs.FlowEventLogsRepository;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.Objects;

import static org.example.enums.AppLogsEnum.SEND_INFORMATION_FLOWS;

@Slf4j
public class Hikari {

    private static HikariConfig config = new HikariConfig();

    private static HikariDataSource dataSource;

    public static void main(String[] args) {

//        {
//            "created_at": null,
//                "event_id": "1db0e433-6697-4050-8b8c-64cbb28258f",
//                "flow_id": 1,
//                "id": null,
//                "payload": "{\"notes\":\"Seentregalaordenaldomicilario\",\"orderId\":\"\",
//                \"orderUuid\":\"b3ba6d34-f9ba-4483-b648-15f3990d7b4a\",\"origin\":\"LocalServer\",
//                \"statusCode\":\"ORDER_PROCESS\",\"statusId\":2,\"userId\":1}",
//                "updated_at": null
//        }

        EventRequestDTO dtos = EventRequestDTO.builder()
                .eventId("1db0e433-6697-4050-8b8c-64cbb28258f")
                .flowId(1L)
                .payload("{\"notes\":\"Seentregalaordenaldomicilario\",\"orderId\":\"\"," +
                        "\"orderUuid\":\"b3ba6d34-f9ba-4483-b648-15f3990d7b4a\",\"origin\":\"LocalServer\"," +
                        "\"statusCode\":\"ORDER_PROCESS\",\"statusId\":2,\"userId\":1}")
                .build();

        final FlowEventLogsEntity eventLogsEntity = EventRequestDTOToEntityMapper
                .buildToFlowEventLogsEntity(dtos);

        log.info(SEND_INFORMATION_FLOWS.getMessageWithCode(), ObjectMapperSingletonUtil.objectToJson(eventLogsEntity));

        final BigInteger getIdInserted = FlowEventLogsRepository.getInstance().save(eventLogsEntity);

        System.out.println(getIdInserted);

//        getConnection();
    }


    private static void connectionMYSQL() {
        config.setJdbcUrl("jdbc:mysql://ou-db-rw.muydev.com:3306/routing_integrations?zeroDateTimeBehavior" +
                "=convertToNull&enabledTLSProtocols=TLSv1.2");
        config.setUsername("mladino");
        config.setPassword("5iMAPQXxjZ");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(800);
        dataSource = new HikariDataSource(config);
    }

    private static Connection getConnection() {
        try {
            if (Objects.isNull(dataSource)) {
                connectionMYSQL();
            }
            return dataSource.getConnection();
        } catch (Exception e) {
            System.out.println("-----------" + e);
            return null;
        }
    }

}
