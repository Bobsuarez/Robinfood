package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandSaveRequestOrderCreated;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandSaveRequestOrderCreatedFactory implements ICommandFactory {

    private final CommandSaveRequestOrderCreated commandSaveRequestOrderCreated;

    public CommandSaveRequestOrderCreatedFactory(CommandSaveRequestOrderCreated commandSaveRequestOrderCreated) {
        this.commandSaveRequestOrderCreated = commandSaveRequestOrderCreated;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_SAVE_REQUEST_ORDER_CREATED.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandSaveRequestOrderCreated;
    }
}
