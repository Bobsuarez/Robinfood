package com.robinfood.app.commandhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_CONVERT_IDS_TRANSACTION_TO_NULL;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_CREATION_TRANSACTION;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_ISSUE_ELECTRONIC_BILLING;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_GET_CHANNEL;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_GET_FINAL_PRODUCT_FINANCE_CATEGORIES;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_GET_FINAL_PRODUCT_TAXES;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_GET_MENU_HALL_PRODUCT_DETAIL;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_GET_TRANSACTION_UUID;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_INFORMATION_BY_STORE;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_INPUT_REQUEST_VALIDATION;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_POS_INFORMATION;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_REDEEM_COUPONS;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_REDEEM_FOOD_COINS;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_ROUND_VALUES_TRANSACTION;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_SAVE_INPUT_REQUEST;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_SAVE_PICKUP_TIME;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_SAVE_REQUEST_ORDER_CREATED;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_SAVE_TEMPORARY_TRANSACTION_VALIDATING_PAYMENT_METHODS;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_SEND_ORDER_DISCARDED_TO_QUEUE;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_CO2;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_COUPONS;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_DISCOUNTS;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_FOOD_COINS;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_MENU;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_PAYMENT_METHOD_PAID;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_COMMAND_VALIDATE_SERVICE;
import static com.robinfood.core.constants.CommandHandlerConstants.EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_EIGHT;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_ELEVEN;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_FIVE;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_FOUR;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_NINE;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_ONE;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_SEVEN;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_SIX;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_TEN;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_THREE;
import static com.robinfood.core.constants.CommandHandlerConstants.PRIORITY_TWO;

@AllArgsConstructor
@Getter
public enum CommandsEnum {

    COMMAND_GET_TRANSACTION_UUID                                            (EXECUTE_COMMAND_GET_TRANSACTION_UUID, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_SAVE_INPUT_REQUEST                                              (EXECUTE_COMMAND_SAVE_INPUT_REQUEST, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_ROUND_VALUES_TRANSACTION                                        (EXECUTE_COMMAND_ROUND_VALUES_TRANSACTION, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_SEND_ORDER_DISCARDED_TO_QUEUE                                   (EXECUTE_COMMAND_SEND_ORDER_DISCARDED_TO_QUEUE, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_CONVERT_IDS_TRANSACTION_TO_NULL                                 (EXECUTE_COMMAND_CONVERT_IDS_TRANSACTION_TO_NULL, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_INPUT_REQUEST_VALIDATION                                        (EXECUTE_COMMAND_INPUT_REQUEST_VALIDATION, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_INFORMATION_BY_STORE                                            (EXECUTE_COMMAND_INFORMATION_BY_STORE, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ONE),
    COMMAND_GET_CHANNEL                                                     (EXECUTE_COMMAND_GET_CHANNEL, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_TWO),
    COMMAND_VALIDATE_SERVICE                                                (EXECUTE_COMMAND_VALIDATE_SERVICE, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_TWO),
    COMMAND_VALIDATE_CO2                                                    (EXECUTE_COMMAND_VALIDATE_CO2, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_TWO),
    COMMAND_VALIDATE_MENU                                                   (EXECUTE_COMMAND_VALIDATE_MENU, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_TWO),
    COMMAND_GET_MENU_HALL_PRODUCT_DETAIL                                    (EXECUTE_COMMAND_GET_MENU_HALL_PRODUCT_DETAIL, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_TWO),
    COMMAND_VALIDATE_DISCOUNTS                                              (EXECUTE_COMMAND_VALIDATE_DISCOUNTS, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_THREE),
    COMMAND_VALIDATE_COUPONS                                                (EXECUTE_COMMAND_VALIDATE_COUPONS, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_THREE),
    COMMAND_VALIDATE_FOOD_COINS                                             (EXECUTE_COMMAND_VALIDATE_FOOD_COINS, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_THREE),
    COMMAND_GET_FINAL_PRODUCT_TAXES                                         (EXECUTE_COMMAND_GET_FINAL_PRODUCT_TAXES, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_FOUR),
    COMMAND_GET_FINAL_PRODUCT_FINANCE_CATEGORIES                            (EXECUTE_COMMAND_GET_FINAL_PRODUCT_FINANCE_CATEGORIES, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_FOUR),
    COMMAND_POS_INFORMATION                                                 (EXECUTE_COMMAND_POS_INFORMATION, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_FOUR),
    COMMAND_CREATION_TRANSACTION                                            (EXECUTE_COMMAND_CREATION_TRANSACTION, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_FIVE),
    COMMAND_SAVE_REQUEST_ORDER_CREATED                                      (EXECUTE_COMMAND_SAVE_REQUEST_ORDER_CREATED, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_SIX),
    COMMAND_SAVE_TEMPORARY_TRANSACTION_VALIDATING_PAYMENT_METHODS           (EXECUTE_COMMAND_SAVE_TEMPORARY_TRANSACTION_VALIDATING_PAYMENT_METHODS, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_SIX),
    COMMAND_SAVE_PICKUP_TIME                                                (EXECUTE_COMMAND_SAVE_PICKUP_TIME, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_SEVEN),
    COMMAND_REDEEM_COUPONS                                                  (EXECUTE_COMMAND_REDEEM_COUPONS, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_EIGHT),
    COMMAND_REDEEM_FOOD_COINS                                               (EXECUTE_COMMAND_REDEEM_FOOD_COINS, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_EIGHT),
    COMMAND_CREATION_TRANSACTION_FINAL                                      (EXECUTE_COMMAND_CREATION_TRANSACTION, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_NINE),
    COMMAND_ISSUE_ELECTRONIC_BILLING                                        (EXECUTE_COMMAND_ISSUE_ELECTRONIC_BILLING, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_TEN),
    COMMAND_VALIDATE_PAYMENT_METHOD_PAID                                    (EXECUTE_COMMAND_VALIDATE_PAYMENT_METHOD_PAID, EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION, PRIORITY_ELEVEN);

    private final String name;
    private final String group;
    private final Integer priority;
}
