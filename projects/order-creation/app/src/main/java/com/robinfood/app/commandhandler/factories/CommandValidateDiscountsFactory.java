package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandValidateDiscounts;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateDiscountsFactory implements ICommandFactory {

    private final CommandValidateDiscounts commandValidateDiscounts;

    public CommandValidateDiscountsFactory(CommandValidateDiscounts commandValidateDiscounts) {
        this.commandValidateDiscounts = commandValidateDiscounts;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_DISCOUNTS.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandValidateDiscounts;
    }
}
