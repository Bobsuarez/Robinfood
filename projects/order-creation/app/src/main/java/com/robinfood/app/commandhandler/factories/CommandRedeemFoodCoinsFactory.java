package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandRedeemFoodCoins;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandRedeemFoodCoinsFactory implements ICommandFactory {

    private final CommandRedeemFoodCoins commandRedeemFoodCoins;

    public CommandRedeemFoodCoinsFactory(CommandRedeemFoodCoins commandRedeemFoodCoins) {
        this.commandRedeemFoodCoins = commandRedeemFoodCoins;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_REDEEM_FOOD_COINS.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandRedeemFoodCoins;
    }
}
