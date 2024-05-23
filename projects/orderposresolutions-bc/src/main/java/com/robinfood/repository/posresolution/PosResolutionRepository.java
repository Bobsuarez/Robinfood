package com.robinfood.repository.posresolution;

import com.robinfood.databases.DatabaseManager;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.entities.db.mysql.PosResolutionEntityEnum;
import com.robinfood.enums.OrderByEnum;
import com.robinfood.exceptions.BusinessRuleException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.robinfood.constants.GeneralConstants.DEFAULT_ONE_INTEGER;
import static com.robinfood.databases.queries.PosResolutionQuery.FIND_BY_RESOLUTION_ID;
import static com.robinfood.databases.queries.PosResolutionQuery.FIND_BY_CURRENT_AND_POS_ID;
import static com.robinfood.databases.queries.PosResolutionQuery.SAVE;
import static com.robinfood.databases.queries.PosResolutionQuery.UPDATE_CURRENT_AND_DIC_STATUS_ID_BY_ID;
import static com.robinfood.databases.queries.PosResolutionQuery.UPDATE_POS_RESOLUTIONS_BY_RESOLUTION_ID;
import static com.robinfood.databases.queries.PosResolutionQuery.COUNT_SEARCH_RESOLUTION_BY_CRITERIA;
import static com.robinfood.databases.queries.PosResolutionQuery.SEARCH_RESOLUTION_BY_CRITERIA;
import static com.robinfood.databases.queries.PosResolutionQuery.LIMIT;
import static com.robinfood.databases.queries.PosResolutionQuery.OFFSET;
import static com.robinfood.databases.queries.PosResolutionQuery.ORDER_BY_END_DATE;
import static com.robinfood.databases.queries.PosResolutionQuery.PAGINATOR;
import static com.robinfood.databases.queries.PosResolutionQuery.SQL_AND_CURRENT;
import static com.robinfood.databases.queries.PosResolutionQuery.SQL_AND_END_DATE;
import static com.robinfood.databases.queries.PosResolutionQuery.SQL_LIKE_NAME;
import static com.robinfood.databases.queries.PosResolutionQuery.SQL_WITH_POS;
import static com.robinfood.databases.queries.PosResolutionQuery.SQL_WITHOUT_POS;
import static com.robinfood.databases.queries.PosResolutionQuery.SQL_RESOLUTION_ID;
import static com.robinfood.databases.queries.PosResolutionQuery.FIND_BY_ID;

@Getter
@Slf4j
public class PosResolutionRepository implements IPosResolutionRepository {

    private final DatabaseManager databaseManager;
    private static PosResolutionRepository instance;

    public PosResolutionRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public static PosResolutionRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PosResolutionRepository(new DatabaseManager());
        }
        return instance;
    }

    @Override
    public List<BigInteger> saveAll(List<PosResolutionEntity> posResolutionEntities) {

        final List<BigInteger> ids = new ArrayList<>();

        posResolutionEntities.forEach((PosResolutionEntity posResolutionEntity) -> {
            Map<String, Object> parameters = parametersSaveAll(posResolutionEntity);
            ids.add(databaseManager.executeInsert(SAVE, parameters));
        });

        return ids;
    }

    @Override
    public Boolean existsByPosIdAndStatus(Long posId, Boolean status) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, DEFAULT_ONE_INTEGER);
        parameters.put(PosResolutionEntityEnum.COLUMN_POS_ID.message, posId);

        final PosResolutionEntity posResolutionEntity = Optional.ofNullable(databaseManager.executeQuery(
                FIND_BY_CURRENT_AND_POS_ID,
                PosResolutionEntity.class, parameters
        )).orElse(PosResolutionEntity.builder().build());

        if (Objects.nonNull(posResolutionEntity.getId())) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public PosResolutionEntity findByPosIdAndStatus(Long posId, Boolean status) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, DEFAULT_ONE_INTEGER);
        parameters.put(PosResolutionEntityEnum.COLUMN_POS_ID.message, posId);

        return Optional.ofNullable(databaseManager.executeQuery(
                FIND_BY_CURRENT_AND_POS_ID,
                PosResolutionEntity.class, parameters
        )).orElse(PosResolutionEntity.builder().build());
    }

    @Override
    public BigInteger updateCurrentAndDicStatusIdById(PosResolutionEntity posResolutionEntity) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, posResolutionEntity.getCurrent());
        parameters.put(PosResolutionEntityEnum.COLUMN_DIC_STATUS_ID.message, posResolutionEntity.getDic_status_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_ID.message, posResolutionEntity.getId());

        return databaseManager.executeInsert(UPDATE_POS_RESOLUTIONS_BY_RESOLUTION_ID, parameters);
    }

    @Override
    public PosResolutionEntity findByResolutionId(Long resolutionId) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(PosResolutionEntityEnum.COLUMN_RESOLUTION_ID.message, resolutionId);

        return Optional.ofNullable(databaseManager.executeQuery(
                FIND_BY_RESOLUTION_ID,
                PosResolutionEntity.class, parameters
        )).orElse(PosResolutionEntity.builder().build());
    }

    @Override
    public BigInteger updateByResolutionId(PosResolutionEntity posResolutionEntity) {

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(PosResolutionEntityEnum.COLUMN_POS_ID.message, posResolutionEntity.getPos_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_NAME.message, posResolutionEntity.getName());
        parameters.put(PosResolutionEntityEnum.COLUMN_PREFIX.message, posResolutionEntity.getPrefix());
        parameters.put(PosResolutionEntityEnum.COLUMN_INVOICE_NUMBER_INITIAL.message,
                posResolutionEntity.getInvoice_number_initial());
        parameters.put(PosResolutionEntityEnum.COLUMN_INVOICE_NUMBER_END.message,
                posResolutionEntity.getInvoice_number_end());
        parameters.put(PosResolutionEntityEnum.COLUMN_INITIAL_DATE.message, posResolutionEntity.getInitial_date());
        parameters.put(PosResolutionEntityEnum.COLUMN_END_DATE.message, posResolutionEntity.getEnd_date());
        parameters.put(PosResolutionEntityEnum.COLUMN_INVOICE_NUMBER_RESOLUTIONS.message,
                posResolutionEntity.getInvoice_number_resolutions());
        parameters.put(PosResolutionEntityEnum.COLUMN_INVOICE_TEXT.message, posResolutionEntity.getInvoice_text());
        parameters.put(PosResolutionEntityEnum.COLUMN_DIC_STATUS_ID.message, posResolutionEntity.getDic_status_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, posResolutionEntity.getCurrent());
        parameters.put(PosResolutionEntityEnum.COLUMN_ID.message, posResolutionEntity.getId());

        return databaseManager.executeInsert(UPDATE_CURRENT_AND_DIC_STATUS_ID_BY_ID, parameters);
    }

    @Override
    public PosResolutionEntity findById(Long id) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put(PosResolutionEntityEnum.COLUMN_RESOLUTION_ID.message, id);

        return Optional.ofNullable(databaseManager.executeQuery(
                FIND_BY_ID,
                PosResolutionEntity.class, parameters
        )).orElseThrow(() -> new BusinessRuleException(
                HttpStatus.SC_NOT_FOUND,
                String.format(
                        "The resolution with identifier %s is not found",
                        id
                )
        ));
    }

    @Override
    public List<PosResolutionEntity> findAllByPosIdAndStatus(Long posId, Integer status) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, status);
        parameters.put(PosResolutionEntityEnum.COLUMN_RESOLUTION_ID.message, posId);

        return Optional.of(databaseManager.executeQueryList(
                FIND_BY_CURRENT_AND_POS_ID,
                PosResolutionEntity.class, parameters
        )).orElse(Collections.emptyList());
    }

    @Override
    public Integer countSearchResolutions(
            Long resolutionId,
            Boolean status,
            String valueCustomFilter,
            Boolean withPos
    ) {
        Map<String, Object> parameters = new LinkedHashMap<>();

        String sql = COUNT_SEARCH_RESOLUTION_BY_CRITERIA;

        sql = getSqlByCriteria(status, valueCustomFilter, parameters, sql, withPos, resolutionId);

        return databaseManager.executeQueryCount(sql, Integer.class, parameters);

    }

    @Override
    public List<PosResolutionEntity> searchResolutions(
            Boolean status,
            OrderByEnum orderByEnum,
            Integer page,
            Long resolutionId,
            Integer size,
            String valueCustomFilter,
            Boolean withPos
    ) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        String sql = SEARCH_RESOLUTION_BY_CRITERIA;

        sql = getSqlByCriteria(status, valueCustomFilter, parameters,  sql, withPos, resolutionId);

        if(orderByEnum != null){
            sql += ORDER_BY_END_DATE + orderByEnum.name();
        }

        sql += PAGINATOR;

        parameters.put(LIMIT, size);
        parameters.put(OFFSET, (page - DEFAULT_ONE_INTEGER) * size);

        return Optional.of(databaseManager.executeQueryList(
                sql,
                PosResolutionEntity.class, parameters
        )).orElse(Collections.emptyList());
    }

    private static String getSqlByCriteria(
            Boolean status,
            String valueCustomFilter,
            Map<String, Object> parameters,
            String sql,
            Boolean withPos,
            Long resolutionId
    ) {

        if(Objects.nonNull(status)){
            parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, status);
            sql += SQL_AND_CURRENT;

            if(Boolean.TRUE.equals(status)){
                sql += SQL_AND_END_DATE;
            }
        }

        if(Objects.nonNull(valueCustomFilter) && !valueCustomFilter.trim().isEmpty()){

            parameters.put(PosResolutionEntityEnum.COLUMN_NAME.message,  "%" +
                    valueCustomFilter.trim() + "%");

            sql += SQL_LIKE_NAME;
        }

        if(Objects.nonNull(withPos)){
            if(Boolean.TRUE.equals(withPos)){
                sql += SQL_WITH_POS;

            }else{
                sql += SQL_WITHOUT_POS;
            }
        }

        if(Objects.nonNull(resolutionId)){
            parameters.put(PosResolutionEntityEnum.COLUMN_RESOLUTION_ID.message, resolutionId);
            sql += SQL_RESOLUTION_ID;
        }
        return sql;

    }


    private static Map<String, Object> parametersSaveAll(PosResolutionEntity posResolutionEntity) {

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put(PosResolutionEntityEnum.COLUMN_RESOLUTION_ID.message, posResolutionEntity.getResolution_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_POS_ID.message, posResolutionEntity.getPos_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_STORE_ID.message, posResolutionEntity.getStore_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_DIC_STATUS_ID.message, posResolutionEntity.getDic_status_id());
        parameters.put(PosResolutionEntityEnum.COLUMN_NAME.message, posResolutionEntity.getName());
        parameters.put(
                PosResolutionEntityEnum.COLUMN_ORDER_NUMBER_INITIAL.message,
                posResolutionEntity.getOrder_number_initial()
        );
        parameters.put(PosResolutionEntityEnum.COLUMN_PREFIX.message, posResolutionEntity.getPrefix());
        parameters.put(
                PosResolutionEntityEnum.COLUMN_INVOICE_NUMBER_INITIAL.message,
                posResolutionEntity.getInvoice_number_initial()
        );
        parameters.put(
                PosResolutionEntityEnum.COLUMN_INVOICE_NUMBER_END.message,
                posResolutionEntity.getInvoice_number_end()
        );
        parameters.put(PosResolutionEntityEnum.COLUMN_CURRENT.message, posResolutionEntity.getCurrent());
        parameters.put(PosResolutionEntityEnum.COLUMN_INITIAL_DATE.message, posResolutionEntity.getInitial_date());
        parameters.put(PosResolutionEntityEnum.COLUMN_END_DATE.message, posResolutionEntity.getEnd_date());
        parameters.put(PosResolutionEntityEnum.COLUMN_TYPE_DOCUMENT.message, posResolutionEntity.getType_document());
        parameters.put(
                PosResolutionEntityEnum.COLUMN_INVOICE_NUMBER_RESOLUTIONS.message,
                posResolutionEntity.getInvoice_number_resolutions()
        );
        parameters.put(PosResolutionEntityEnum.COLUMN_INVOICE_TEXT.message, posResolutionEntity.getInvoice_text());

        return parameters;
    }

}
