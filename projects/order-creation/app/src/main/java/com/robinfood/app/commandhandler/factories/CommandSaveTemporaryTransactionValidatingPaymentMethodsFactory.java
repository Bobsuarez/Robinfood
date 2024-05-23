package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandSaveTemporaryTransactionValidatingPaymentMethods;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandSaveTemporaryTransactionValidatingPaymentMethodsFactory implements ICommandFactory {

    private final CommandSaveTemporaryTransactionValidatingPaymentMethods
            commandSaveTemporaryTransactionValidatingPaymentMethods;

    public CommandSaveTemporaryTransactionValidatingPaymentMethodsFactory(
            CommandSaveTemporaryTransactionValidatingPaymentMethods
                    commandSaveTemporaryTransactionValidatingPaymentMethods
    ) {
        this.commandSaveTemporaryTransactionValidatingPaymentMethods =
                commandSaveTemporaryTransactionValidatingPaymentMethods;
    }


    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_SAVE_TEMPORARY_TRANSACTION_VALIDATING_PAYMENT_METHODS.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandSaveTemporaryTransactionValidatingPaymentMethods;
    }
}
