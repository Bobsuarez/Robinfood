package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandInputRequestValidation;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandInputRequestValidationFactory implements ICommandFactory {

    private final CommandInputRequestValidation commandInputRequestValidation;

    public CommandInputRequestValidationFactory(CommandInputRequestValidation commandInputRequestValidation) {
        this.commandInputRequestValidation = commandInputRequestValidation;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_INPUT_REQUEST_VALIDATION.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandInputRequestValidation;
    }
}
