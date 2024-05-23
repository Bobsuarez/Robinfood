package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandCreationTransactionFinal;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandCreationTransactionFinalFactory implements ICommandFactory {

    private final CommandCreationTransactionFinal commandCreationTransactionFinal;

    public CommandCreationTransactionFinalFactory(CommandCreationTransactionFinal commandCreationTransactionFinal) {
        this.commandCreationTransactionFinal = commandCreationTransactionFinal;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_CREATION_TRANSACTION_FINAL.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return this.commandCreationTransactionFinal;
    }
}
