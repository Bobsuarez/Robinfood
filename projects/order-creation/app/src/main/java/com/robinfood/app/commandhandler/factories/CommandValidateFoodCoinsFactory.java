package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandValidateFoodCoins;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateFoodCoinsFactory implements ICommandFactory{

    private final CommandValidateFoodCoins commandValidateFoodCoins;

    public CommandValidateFoodCoinsFactory(CommandValidateFoodCoins commandValidateFoodCoins) {
        this.commandValidateFoodCoins = commandValidateFoodCoins;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_FOOD_COINS.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandValidateFoodCoins;
    }
}
