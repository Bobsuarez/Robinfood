package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandGetMenuHallProductDetail;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandGetMenuHallProductDetailFactory implements ICommandFactory {

    private final CommandGetMenuHallProductDetail commandGetMenuHallProductDetail;

    public CommandGetMenuHallProductDetailFactory(CommandGetMenuHallProductDetail commandGetMenuHallProductDetail) {
        this.commandGetMenuHallProductDetail = commandGetMenuHallProductDetail;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_GET_MENU_HALL_PRODUCT_DETAIL.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandGetMenuHallProductDetail;
    }
}
