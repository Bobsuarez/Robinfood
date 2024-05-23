package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandSaveInputRequest;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandSaveInputRequestFactory implements ICommandFactory {

    private final CommandSaveInputRequest commandSaveInputRequest;

    public CommandSaveInputRequestFactory(CommandSaveInputRequest commandSaveInputRequest) {
        this.commandSaveInputRequest = commandSaveInputRequest;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_SAVE_INPUT_REQUEST.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandSaveInputRequest;
    }
}
