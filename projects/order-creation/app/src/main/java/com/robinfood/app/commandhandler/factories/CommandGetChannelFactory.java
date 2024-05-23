package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandGetChannel;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandGetChannelFactory implements ICommandFactory {

    private final CommandGetChannel commandGetChannel;

    public CommandGetChannelFactory(CommandGetChannel commandGetChannel) {
        this.commandGetChannel = commandGetChannel;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_GET_CHANNEL.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandGetChannel;
    }
}
