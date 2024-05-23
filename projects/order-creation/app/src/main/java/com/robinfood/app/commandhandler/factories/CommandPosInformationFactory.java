package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandPosInformation;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandPosInformationFactory implements ICommandFactory {

    private final CommandPosInformation commandPosInformation;

    public CommandPosInformationFactory(CommandPosInformation commandPosInformation) {
        this.commandPosInformation = commandPosInformation;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_POS_INFORMATION.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandPosInformation;
    }
}
