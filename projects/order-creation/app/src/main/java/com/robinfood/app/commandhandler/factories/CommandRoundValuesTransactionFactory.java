package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandRoundValuesTransaction;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandRoundValuesTransactionFactory implements ICommandFactory {

    private final CommandRoundValuesTransaction commandRoundValuesTransaction;

    public CommandRoundValuesTransactionFactory(CommandRoundValuesTransaction commandRoundValuesTransaction) {
        this.commandRoundValuesTransaction = commandRoundValuesTransaction;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_ROUND_VALUES_TRANSACTION.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandRoundValuesTransaction;
    }
}
