package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandValidateCoupons;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateCouponsFactory implements ICommandFactory {

    private final CommandValidateCoupons commandValidateCoupons;

    public CommandValidateCouponsFactory(CommandValidateCoupons commandValidateCoupons) {
        this.commandValidateCoupons = commandValidateCoupons;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_COUPONS.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandValidateCoupons;
    }
}
