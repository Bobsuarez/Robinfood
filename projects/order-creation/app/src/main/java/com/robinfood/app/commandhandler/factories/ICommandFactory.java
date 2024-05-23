package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.commands.ICommand;

public interface ICommandFactory {

        Boolean apply(String command);
        ICommand getCommand();
}
