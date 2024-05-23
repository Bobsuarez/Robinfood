package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandValidatePaymentMethodPaid;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandValidatePaymentMethodPaidFactory implements ICommandFactory {

    private final CommandValidatePaymentMethodPaid commandValidatePaymentMethodPaid;

    public CommandValidatePaymentMethodPaidFactory(CommandValidatePaymentMethodPaid commandValidatePaymentMethodPaid) {
        this.commandValidatePaymentMethodPaid = commandValidatePaymentMethodPaid;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_PAYMENT_METHOD_PAID.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return this.commandValidatePaymentMethodPaid;
    }
}
