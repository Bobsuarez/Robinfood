package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandSavePickupTime;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandSavePickupTimeFactory implements ICommandFactory {

    private final CommandSavePickupTime commandSavePickupTime;

    public CommandSavePickupTimeFactory(CommandSavePickupTime commandSavePickupTime) {
        this.commandSavePickupTime = commandSavePickupTime;
    }
    
    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_SAVE_PICKUP_TIME.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandSavePickupTime;
    }
}
