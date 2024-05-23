package com.robinfood.paymentmethodsbc.repositories;

import com.robinfood.paymentmethodsbc.model.TerminalPaymentMethodSetting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface que permite habilitar el acceso a la capa de datos a la entidad
 * TerminalPaymentMethodSetting para el uso de CRUD, utilizando como interface JpaRepository
 * @author Hernán A. Ramirez O.
 */
public interface TerminalPaymentMethodSettingsRepository extends JpaRepository<TerminalPaymentMethodSetting, Long> {
    List<TerminalPaymentMethodSetting> findByTerminalIdAndPaymentGatewayIdAndTerminalParameterVisible(
        Long terminalId,
        Long paymentGatewayId,
        boolean visible
    );

    List<TerminalPaymentMethodSetting> findByTerminalIdAndPaymentGatewayId(
        Long terminalId,
        Long paymentGatewayId
    );
}

