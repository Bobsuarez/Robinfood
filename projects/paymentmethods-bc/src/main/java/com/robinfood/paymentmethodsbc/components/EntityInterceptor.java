package com.robinfood.paymentmethodsbc.components;

import com.robinfood.paymentmethodsbc.constants.AppConstants;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.model.PaymentGatewayCreditCard;
import com.robinfood.paymentmethodsbc.security.CipherUtility;
import com.robinfood.paymentmethodsbc.utils.Utilities;
import java.io.Serializable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Interceptor encargado de ejecutar acciones de encriptar datos de tarjeta de crédito previo a
 * almacenar en BD.
 */
public class EntityInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(
        Object entity,
        Serializable id,
        Object[] state,
        String[] propertyNames,
        Type[] types
    ) {
        if (entity instanceof CreditCard) {
            encryptModelAttributes((CreditCard) entity);
        }

        if (entity instanceof PaymentGatewayCreditCard) {
            encryptCreditCardToken((PaymentGatewayCreditCard) entity);
        }

        return super.onSave(entity, id, state, propertyNames, types);
    }

    private void encryptModelAttributes(CreditCard creditCard) {
        CipherUtility cipherUtility = BaseApplicationContextAware
            .getApplicationContext()
            .getBean(CipherUtility.class);

        String newNumber = Utilities.getStringLastChars(
            creditCard.getNumber(),
            AppConstants.CREDIT_CARD_LAST_NUMBER_SIZE
        );

        creditCard.setNumber(cipherUtility.encryptTextWithPublicKey(newNumber));

        creditCard.setCardHolderName(
            cipherUtility.encryptTextWithPublicKey(
                creditCard.getCardHolderName()
            )
        );
        creditCard.setExpirationMonth(
            cipherUtility.encryptTextWithPublicKey(
                creditCard.getExpirationMonth()
            )
        );
        creditCard.setExpirationYear(
            cipherUtility.encryptTextWithPublicKey(
                creditCard.getExpirationYear()
            )
        );
        creditCard.setUserIdentificationNumber(
            cipherUtility.encryptTextWithPublicKey(
                creditCard.getUserIdentificationNumber()
            )
        );
        creditCard.setUserEmail(
            cipherUtility.encryptTextWithPublicKey(
                creditCard.getUserEmail()
            )
        );

        creditCard.setUserAddress(
            cipherUtility.encryptTextWithPublicKey(creditCard.getUserAddress())
        );

        creditCard.setUserCity(
            cipherUtility.encryptTextWithPublicKey(creditCard.getUserCity())
        );
    }

    private void encryptCreditCardToken(
        PaymentGatewayCreditCard paymentGatewayCreditCard
    ) {
        CipherUtility cipherUtility = BaseApplicationContextAware
            .getApplicationContext()
            .getBean(CipherUtility.class);

        paymentGatewayCreditCard.setToken(
            cipherUtility.encryptTextWithPublicKey(
                paymentGatewayCreditCard.getToken()
            )
        );
    }
}
