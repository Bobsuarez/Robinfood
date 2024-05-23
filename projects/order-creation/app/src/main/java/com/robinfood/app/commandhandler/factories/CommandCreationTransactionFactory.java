package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandCreationTransaction;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandCreationTransactionFactory implements ICommandFactory {

    private final CommandCreationTransaction commandCreationTransaction;

    public CommandCreationTransactionFactory(CommandCreationTransaction commandCreationTransaction) {
        this.commandCreationTransaction = commandCreationTransaction;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_CREATION_TRANSACTION.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandCreationTransaction;
    }
}
