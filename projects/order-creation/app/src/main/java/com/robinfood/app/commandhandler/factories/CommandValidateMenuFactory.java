package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandValidateMenu;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandValidateMenuFactory implements ICommandFactory{

    private final CommandValidateMenu commandValidateMenu;

    public CommandValidateMenuFactory(CommandValidateMenu commandValidateMenu) {
        this.commandValidateMenu = commandValidateMenu;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_VALIDATE_MENU.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandValidateMenu;
    }
}
