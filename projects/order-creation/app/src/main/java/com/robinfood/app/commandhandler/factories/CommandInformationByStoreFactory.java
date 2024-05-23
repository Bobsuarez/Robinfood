package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.ICommand;
import com.robinfood.app.commandhandler.commands.CommandInformationByStore;
import org.springframework.stereotype.Component;

@Component
public class CommandInformationByStoreFactory implements ICommandFactory {

    private final CommandInformationByStore commandInformationByStore;

    public CommandInformationByStoreFactory(CommandInformationByStore commandInformationByStore) {
        this.commandInformationByStore = commandInformationByStore;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_INFORMATION_BY_STORE.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandInformationByStore;
    }
}
