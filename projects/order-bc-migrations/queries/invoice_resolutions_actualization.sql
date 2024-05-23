-- Insert into orders.pos_resolutions
TRUNCATE TABLE orders.pos_resolutions;

INSERT INTO orders.pos_resolutions (
     resolution_id,
     pos_id,
     store_id,
     dic_status_id,
     name,
     order_number_initial,
     prefix,
     invoice_number_initial,
     invoice_number_end,
     current,
     initial_date,
     end_date,
     type_document,
     invoice_number_resolutions,
     invoice_text,
     created_at,
     updated_at
 )

-- Select sentence to the insert of resolutions into orders database
(SELECT res.id,
           pos.id posId,
           pos.store_id,
           IF(pos.id IS NULL, 2, res.dic_status_id) dic_status_id, -- 2: The object is inactive
           res.name,
           0                                        order_number_initial,
           res.prefix,
           0                                        invoice_number_initial,
           res.number_end,
           IF(pos.id IS NULL, 0, res.current)       current,
           res.initial_date,
           res.end_date,
           res.type_document,
           res.number_resolutions,
           res.text,
           res.created_at,
           res.updated_at

        FROM sgi.resolutions res

        left join sgi.pos pos
        on pos.resolution_id = res.id
    );