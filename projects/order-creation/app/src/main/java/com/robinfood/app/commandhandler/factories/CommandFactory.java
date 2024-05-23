package com.robinfood.app.commandhandler.factories;

import com.robinfood.core.exceptions.WriteInTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandFactory {

    private final List<ICommandFactory> commandFactories;

    public CommandFactory(List<ICommandFactory> commandFactories) {
        this.commandFactories = commandFactories;
    }

    public ICommandFactory invoke(String command) {

        final boolean isApply = commandFactories.stream().anyMatch(commandFactory -> commandFactory.apply(command));

        if (isApply) {
            return commandFactories.stream().filter(getCommandFactory -> getCommandFactory.apply(command))
                    .findFirst()
                    .orElseThrow(() -> new WriteInTransactionException(HttpStatus.NOT_FOUND, "Command not found"));
        }

        return null;
    }
}
