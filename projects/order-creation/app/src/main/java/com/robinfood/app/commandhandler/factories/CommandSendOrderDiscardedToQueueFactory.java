package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandSendOrderDiscardedToQueue;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandSendOrderDiscardedToQueueFactory implements ICommandFactory {

    private final CommandSendOrderDiscardedToQueue commandSendOrderDiscardedToQueue;

    public CommandSendOrderDiscardedToQueueFactory(CommandSendOrderDiscardedToQueue commandSendOrderDiscardedToQueue) {
        this.commandSendOrderDiscardedToQueue = commandSendOrderDiscardedToQueue;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_SEND_ORDER_DISCARDED_TO_QUEUE.getName().equals(command);
    }


    @Override
    public ICommand getCommand() {
        return this.commandSendOrderDiscardedToQueue;
    }
}
