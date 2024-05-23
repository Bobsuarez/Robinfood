package com.robinfood.paymentmethodsbc.sample;

import com.robinfood.paymentmethodsbc.model.TerminalPaymentMethodSetting;
import java.util.List;

/**
 * Obtiene objetos de prueba para test unitarios
 */
public class TerminalPaymentMethodSettingSample {

    public static List<TerminalPaymentMethodSetting> getTerminalPaymentMethodSettingList(){
        return List.of(
            getTerminalPaymentMethodSetting(),
            getTerminalPaymentMethodSetting()
        );
    }

    public static TerminalPaymentMethodSetting getTerminalPaymentMethodSetting(){
        return TerminalPaymentMethodSetting
            .builder()
            .id(1L)
            .terminal(TerminalSample.getTerminal())
            .paymentGateway(PaymentGatewaySamples.getPaymentGateway())
            .terminalParameter(TerminalParameterSample.getTerminalParameter())
            .value("Value")
            .build();
    }
}
