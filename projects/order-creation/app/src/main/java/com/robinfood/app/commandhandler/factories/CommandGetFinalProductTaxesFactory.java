package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandGetFinalProductTaxes;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandGetFinalProductTaxesFactory implements ICommandFactory{

    private final CommandGetFinalProductTaxes commandGetFinalProductTaxes;

    public CommandGetFinalProductTaxesFactory(CommandGetFinalProductTaxes commandGetFinalProductTaxes) {
        this.commandGetFinalProductTaxes = commandGetFinalProductTaxes;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_GET_FINAL_PRODUCT_TAXES.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandGetFinalProductTaxes;
    }
}
