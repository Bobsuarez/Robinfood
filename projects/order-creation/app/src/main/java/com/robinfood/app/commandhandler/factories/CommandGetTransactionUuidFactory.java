package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandGetTransactionUuid;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandGetTransactionUuidFactory implements ICommandFactory {

    private final CommandGetTransactionUuid commandGetTransactionUuid;

    public CommandGetTransactionUuidFactory(CommandGetTransactionUuid commandGetTransactionUuid) {
        this.commandGetTransactionUuid = commandGetTransactionUuid;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_GET_TRANSACTION_UUID.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return this.commandGetTransactionUuid;
    }
}
