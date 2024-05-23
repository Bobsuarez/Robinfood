package com.robinfood.paymentmethodsbc.services.steps;

import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;

/**
 * Interfaz que debe implementar de pasos
 */
public interface StepActions {
    void invoke(Object pipe) throws PaymentStepException, EntityNotFoundException;
}
