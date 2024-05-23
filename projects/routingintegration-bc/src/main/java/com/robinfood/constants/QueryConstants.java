package com.robinfood.constants;

public class QueryConstants {

    public static final String SAVE_EVENTS = "INSERT INTO flow_event_logs " +
            "(event_id, flow_id, payload, created_at, updated_at)" +
            " VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";


    public static final String SELECT_CHANNEL_ID_AND_FLOW_ID_RULES =
            "SELECT * FROM routes rts WHERE rts.channel_id = ? AND rts.flow_id = ?;";

    public static final String SUBSCRIBERS_SELECT_ALL_WHERE_CHANNEL_ID_AND_FLOW_ID =
            "SELECT * FROM subscriber_channels WHERE channel_id = ? AND flow_id = ?;";
    public static final String SUBSCRIBERS_SELECT_SUBSCRIBERS_ONE_WHERE_ID = "SELECT * FROM subscribers WHERE id = ?;";
    public static final String SUBSCRIBERS_SELECT_SUBSCRIBER_TYPE_ONE_WHERE_ID = "SELECT * FROM subscriber_types " +
            "WHERE id = ?;";
    public static final String SUBSCRIBERS_SELECT_SUBSCRIBER_PROPERTIES_ONE_WHERE_SUBSCRIBER_ID =
            "SELECT * FROM subscriber_properties WHERE subscriber_id = ?;";


    public static final String SUBSCRIBERS_EVENT_HISTORY_SAVE = "INSERT INTO subscriber_event_history_logs " +
            "(flow_event_log_id, event_dispatched, subscriber_id, created_at, updated_at)" +
            " VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

    public static final String SUBSCRIBERS_EVENT_HISTORY_SELECT_BY_ID =
            "SELECT * FROM subscriber_event_history_logs WHERE id = ?;";


    public static final String FLOW_SELECT_CODE_FLOW = "SELECT * FROM flows flw WHERE flw.code = ?;";

    public static final String FLOW_SELECT_EVENT_AND_FLOW_ID =
            "SELECT * FROM flow_event_logs WHERE event_id = ? AND flow_id = ?;";

}
