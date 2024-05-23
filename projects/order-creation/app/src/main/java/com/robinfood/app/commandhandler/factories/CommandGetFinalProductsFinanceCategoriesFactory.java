package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandGetFinalProductFinanceCategories;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandGetFinalProductsFinanceCategoriesFactory implements ICommandFactory{

    private final CommandGetFinalProductFinanceCategories commandGetFinalProductsFinanceCategories;

    public CommandGetFinalProductsFinanceCategoriesFactory(CommandGetFinalProductFinanceCategories commandGetFinalProductsFinanceCategories) {
        this.commandGetFinalProductsFinanceCategories = commandGetFinalProductsFinanceCategories;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_GET_FINAL_PRODUCT_FINANCE_CATEGORIES.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandGetFinalProductsFinanceCategories;
    }
}
