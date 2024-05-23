package com.robinfood.databases.queries;

public final class PosResolutionQuery {

    private static final String TABLE = "pos_resolutions";

    public static final String FIND_BY_CURRENT_AND_POS_ID = "SELECT * FROM " + TABLE + " " +
            "WHERE current = ? AND pos_id = ?;";

    public static final String FIND_BY_RESOLUTION_ID = "SELECT * FROM " + TABLE + " " +
            "WHERE resolution_id = ?;";

    public static final String FIND_BY_ID = "SELECT * FROM " + TABLE + " " +
            "WHERE id = ?;";

    public static final String SAVE = "INSERT INTO " + TABLE + " " +
            "(resolution_id, pos_id, store_id, dic_status_id, name, order_number_initial, prefix, " +
            "invoice_number_initial, invoice_number_end, current, initial_date, end_date, type_document, " +
            "invoice_number_resolutions, invoice_text, created_at, updated_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";

    public static final String UPDATE_CURRENT_AND_DIC_STATUS_ID_BY_ID = "UPDATE pos_resolutions " +
            "SET pos_id = ?,  name = ?,  " +
            "prefix = ?, invoice_number_initial = ?, invoice_number_end = ?, initial_date = ?, " +
            "end_date = ?,  invoice_number_resolutions = ?, invoice_text = ?, dic_status_id = ?, current = ?, " +
            "updated_at = CURRENT_TIMESTAMP " +
            "WHERE id = ?;";

    public static final String UPDATE_POS_RESOLUTIONS_BY_RESOLUTION_ID = "UPDATE pos_resolutions " +
            "SET current = ?, dic_status_id = ? " +
            "WHERE id = ?;";

    public static final String COUNT_SEARCH_RESOLUTION_BY_CRITERIA = "SELECT COUNT(r.id) FROM pos_resolutions r " +
            "WHERE 1=1 ";

    public static final String SEARCH_RESOLUTION_BY_CRITERIA = "SELECT r.* FROM pos_resolutions r WHERE 1=1 ";

    public static final String LIMIT = "limit";

    public static final String OFFSET = "offset";

    public static final String ORDER_BY_END_DATE = " ORDER BY end_date ";

    public static final String PAGINATOR = " LIMIT ? OFFSET ? ";

    public static final String SQL_AND_CURRENT = " AND  r.current = ? ";

    public static final String SQL_AND_END_DATE = " AND end_date  > CURDATE() ";

    public static final String SQL_LIKE_NAME = " AND  LOWER(r.name) LIKE LOWER(?) ";

    public static final String SQL_WITH_POS = " AND  (r.pos_id IS NOT NULL AND r.pos_id > 0) ";

    public static final String SQL_WITHOUT_POS = " AND  r.pos_id IS NULL ";

    public static final String SQL_RESOLUTION_ID = " AND  r.resolution_id = ? ";

    private PosResolutionQuery() {
        throw new IllegalStateException("Queries class");
    }
}
