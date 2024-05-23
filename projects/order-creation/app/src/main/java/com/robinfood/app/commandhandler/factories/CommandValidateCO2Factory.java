package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.ICommand;
import com.robinfood.app.commandhandler.commands.CommandValidateCO2;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateCO2Factory implements ICommandFactory {

    private final CommandValidateCO2 commandValidateCO2;

    public CommandValidateCO2Factory(CommandValidateCO2 commandValidateCO2) {
        this.commandValidateCO2 = commandValidateCO2;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_CO2.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandValidateCO2;
    }
}