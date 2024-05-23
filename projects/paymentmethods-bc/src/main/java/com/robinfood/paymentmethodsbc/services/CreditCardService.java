package com.robinfood.paymentmethodsbc.services;

import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import java.util.List;

/**
 * Interface que permite la administracion de tarjetas de crédito
 * @author Edwin Artunduaga
 */
public interface CreditCardService {
    /**
     * Metodo que permite listar tarjeta de crédito de un usuario por país
     *
     * @param userId id del usuario
     * @param countryId id del país
     * @param generalPaymentMethodId id del método de pago 
     * @return List<CreditCard>
     * @throws EntityNotFoundException
     * @throws PaymentStepException 
     * @throws BaseException 
     */
    List<CreditCard> findAllByUserAndCountryAndPaymentMethodId(
        Long userId,
        Long countryId,
        Long generalPaymentMethodId
    )
        throws BaseException, EntityNotFoundException, PaymentStepException;
    /**
     * Metodo que permite registrar una tarjeta de crédito
     *
     * @param creditCardDTO información del registro de tarjeta de crédito
     * @return CreditCard
     * @throws BaseException
     * @throws EntityNotFoundException
     * @author Edwin Artunduaga
     * @throws PaymentStepException 
     */
    CreditCard create(CreateCreditCardRequestDTO creditCardDTO)
        throws BaseException, EntityNotFoundException, PaymentStepException;

    /**
     * Metodo que permite eliminar una tarjeta de crédito
     * @param userId id usuario propietario de la tarjeta de crédito
     * @param creditCardId id de la tarjeta de crédito
     * @return String
     * @throws BaseException
     * @throws EntityNotFoundException
     * @author Edwin Artunduaga
     * @throws PaymentStepException 
     */
    String delete(Long userId, Long creditCardId)
        throws BaseException, EntityNotFoundException, PaymentStepException;


    /**
     * Method a for update a credit card
     * @param userId user ID owner
     * @param creditCardId Credit Card ID
     * @param updateCreditCardRequestDTO Updated Object
     * @return String
     * @throws BaseException
     * @throws EntityNotFoundException
     * @throws PaymentStepException 
     */
    CreditCard update(
        Long userId,
        Long creditCardId,
        UpdateCreditCardRequestDTO updateCreditCardRequestDTO
    ) throws BaseException, EntityNotFoundException, PaymentStepException;
}
