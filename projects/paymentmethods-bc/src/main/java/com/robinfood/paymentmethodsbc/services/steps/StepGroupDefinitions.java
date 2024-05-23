package com.robinfood.paymentmethodsbc.services.steps;

import com.robinfood.paymentmethodsbc.dto.steps.StepGroupDTO;
import com.robinfood.paymentmethodsbc.enums.StepType;

import java.util.ArrayList;
import java.util.List;

public final class StepGroupDefinitions {
    /**
     * Estos pasos permiten la creación de tarjeta de credito
     * el primer grupo valida los datos entregados por el usuario
     * el segundo grupo crea la tarjeta de credito en BD
     * el tercer grupo realiza la tokenización a través de los BCI para cada gateway
     * el cuarto grupo permite tomar la informacion de la tarjeta de credito y encriptarla para enviar a usuario
     */
    public static final List<StepGroupDTO> CREATE_CREDITCARD_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_CREDIT_CARD)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.CREATE_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.TOKENIZE_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_ENCRYPTED_CREDIT_CARD))
            )
        )
    );

    /**
     * Estos pasos permiten listar las tarjetas de credito para un usuario específico
     * el primer grupo valida el pais
     * el segundo grupo genera el listado a entregar al usuario
     */
    public static final List<StepGroupDTO> USERLIST_CREDITCARD_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_COUNTRY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.USER_LIST_CREDIT_CARD))
            )
        )
    );

    /**
     * Estos pasos permiten actualizar el estado de una transacción (activemq)
     * el primer grupo es para validar la transacción
     * el segundo grupo es para actualizar los datos de la transacción (sin usar BCI)
     * el tercer grupo notifica el estado de la transaccion a través de activemq
     */
    public static final List<StepGroupDTO> JMS_UPDATE_TRANSACTION_STATUS_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_SETTINGS_PAYMENT_GATEWAY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_UPDATE_STATUS_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_STATUS_TRANSACTION))
            )
        )
    );
    /**
     * Pasos para realizar una transacción inicial,
     * el primer grupo es de validación y carga inicial de datos
     * el segundo grupo es para validar el tipo de plataforma
     * el tercer grupo crea la transacción inicial con estado pendiente
     */
    public static final List<StepGroupDTO> INITIAL_TRANSACTION_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.VALIDATE_TOTALS),
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY),
                    new StepGroupDTO.StepDTO(StepType.GET_CREDIT_CARD)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_PAYMENT_GATEWAY_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.INITIAL_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_GENERATE_TRANSACTION))
            )
        )
    );
    /**
     * Estos pasos permiten actualizar el estado de una transacción (webhook)
     * el primer grupo es para validar la transacción
     * el segundo grupo es para actualizar los datos de la transacción usando el BCI correspondiente
     * el tercer grupo notifica el estado de la transaccion a través de activemq
     */
    public static final List<StepGroupDTO> UPDATE_TRANSACTION_STATUS_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_SETTINGS_PAYMENT_GATEWAY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_DATAPHONE_SETTINGS))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.UPDATE_STATUS_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_STATUS_TRANSACTION))
            )
        )
    );
    /**
     * Estos pasos permiten generar la transacción, esto se ejecuta desde consumer de activemq
     * el primer grupo es para cargar datos de BD de cada entidad requerida
     * el segundo grupo es para cargar datos de plataforma
     * el tercer grupo es para realizar la transacción a traves de los bci
     * el cuarto grupo notifica el estado de la transaccion a través de activemq
     */
    public static final List<StepGroupDTO> GENERATE_TRANSACTION_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY),
                    new StepGroupDTO.StepDTO(StepType.GET_CREDIT_CARD),
                    new StepGroupDTO.StepDTO(StepType.GET_TRANSACTION)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_PAYMENT_GATEWAY_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_SETTINGS_PAYMENT_GATEWAY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GENERATE_PAYMENT))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_STATUS_TRANSACTION))
            )
        )
    );
    /**
     * Estos pasos permiten reemolsar una transacción
     * el primer grupo valida la transacción
     * el segundo grupo crea la transacción inicial (de tipo refund)
     * el tercer grupo genera el reembolso a través de los BCI
     * el cuarto grupo notifica el estado a través de activemq
     */
    public static final List<StepGroupDTO> REFUND_TRANSACTION_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_SETTINGS_PAYMENT_GATEWAY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.INITIAL_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GENERATE_REFUND))
            )
        )
    );
    /**
     * Estos pasos permiten eliminar una tarjeta de crédito
     * el primer grupo valida la tarjeta de crédito
     * el segundo grupo elimina las tarjetas a través de los BCI para cada gateway
     * el tercer grupo elimina la tarjeta en la BD
     */
    public static final List<StepGroupDTO> DELETE_CREDITCARD_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.REMOVE_TOKEN_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.DELETE_CREDIT_CARD))
            )
        )
    );
    /**
     * Pasos para realizar una transacción inicial desde datafono
     * el primer grupo es de validación y carga inicial de datos
     * el segundo grupo crea la transacción inicial con estado pendiente
     * el tercer grupo encola la transacción para ser procesada en asincrono
     */
    public static final List<StepGroupDTO> INITIAL_TRANSACTION_DATAPHONE_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY),
                    new StepGroupDTO.StepDTO(StepType.GET_DATAPHONE_INFO)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.INITIAL_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_GENERATE_TRANSACTION))
            )
        )
    );
    /**
     * Estos pasos permiten generar la transacción, esto se ejecuta desde consumer de activemq
     * el primer grupo es para cargar datos de BD de cada entidad requerida
     * el segundo grupo es para realizar la transacción a traves de los bci
     * el cuarto grupo notifica el estado de la transaccion a través de activemq
     */
    public static final List<StepGroupDTO> GENERATE_TRANSACTION_DATAPHONE_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY),
                    new StepGroupDTO.StepDTO(StepType.GET_DATAPHONE_INFO),
                    new StepGroupDTO.StepDTO(StepType.GET_TRANSACTION)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_SETTINGS_PAYMENT_GATEWAY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_DATAPHONE_SETTINGS))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.CANCEL_PREVIOUS_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GENERATE_PAYMENT))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_STATUS_TRANSACTION))
            )
        )
    );
    /**
     * Pasos para realizar una transacción inicial con foodcoins
     * el primer grupo es de validación y carga inicial de datos
     * el segundo grupo crea la transacción inicial con estado pendiente
     * el tercer grupo encola la transacción para ser procesada en asincrono
     */
    public static final List<StepGroupDTO> INITIAL_TRANSACTION_FOODCOINS_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY),
                    new StepGroupDTO.StepDTO(StepType.VALIDATE_TOTALS)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.INITIAL_TRANSACTION))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_GENERATE_TRANSACTION))
            )
        )
    );

    /**
     * Estos pasos permiten generar la transacción, esto se ejecuta desde consumer de activemq
     * el primer grupo es para cargar datos de BD de cada entidad requerida
     * el segundo grupo es para realizar la transacción a traves de los bci
     * el cuarto grupo notifica el estado de la transaccion a través de activemq
     */
    public static final List<StepGroupDTO> GENERATE_TRANSACTION_FOODCOINS_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY),
                    new StepGroupDTO.StepDTO(StepType.VALIDATE_TOTALS),
                    new StepGroupDTO.StepDTO(StepType.GET_TRANSACTION)
                )
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_SETTINGS_PAYMENT_GATEWAY))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GENERATE_PAYMENT))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.JMS_SEND_STATUS_TRANSACTION))
            )
        )
    );

    /**
     * Pasos para realizar una transacción inicial desde plataformas sin validaciones
     * el primer grupo es de validación y carga inicial de datos
     * el segundo grupo crea la transacción inicial con estado pendiente
     * el tercer grupo encola la transacción para ser procesada en asincrono
     */
    public static final List<StepGroupDTO> GENERATE_TRANSACTION_NO_VALIDATION_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.VALIDATE_TOTALS),
                    new StepGroupDTO.StepDTO(StepType.GET_COUNTRY),
                    new StepGroupDTO.StepDTO(StepType.GET_ENTITY)
                )
            ),
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(StepType.GENERATE_PAYMENT_NO_VALIDATION)
                )
            ),
            new StepGroupDTO(
                List.of(
                    new StepGroupDTO.StepDTO(
                        StepType.JMS_SEND_GENERATE_TRANSACTION
                    )
                )
            )
        )
    );


   /**
     * This steps allow to update a credit card
     */
    public static final List<StepGroupDTO> UPDATE_CREDITCARD_STEPS = new ArrayList<>(
        List.of(
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.BUILD_UPDATE_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.UPDATE_TOKENIZE_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.UPDATE_CREDIT_CARD))
            ),
            new StepGroupDTO(
                List.of(new StepGroupDTO.StepDTO(StepType.GET_DECRYPTED_CREDIT_CARD))
            )
        )
    );
    
    private StepGroupDefinitions() {}
}
