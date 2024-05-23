package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.ICommand;
import com.robinfood.app.commandhandler.commands.CommandValidateService;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateServiceFactory implements ICommandFactory {

    private final CommandValidateService commandValidateService;

    public CommandValidateServiceFactory(CommandValidateService commandValidateService) {
        this.commandValidateService = commandValidateService;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_SERVICE.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandValidateService;
    }
}
