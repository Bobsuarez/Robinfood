package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandConvertIdsTransactionToNull;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Service;

@Service
public class CommandConvertIdsTransactionToNullFactory implements ICommandFactory {

    private final CommandConvertIdsTransactionToNull commandConvertIdsTransactionToNull;

    public CommandConvertIdsTransactionToNullFactory(
            CommandConvertIdsTransactionToNull commandConvertIdsTransactionToNull
    ) {
        this.commandConvertIdsTransactionToNull = commandConvertIdsTransactionToNull;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_CONVERT_IDS_TRANSACTION_TO_NULL.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandConvertIdsTransactionToNull;
    }
}
