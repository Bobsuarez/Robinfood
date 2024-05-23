package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandRedeemCoupons;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandRedeemCouponsFactory implements ICommandFactory {

    private final CommandRedeemCoupons commandRedeemCoupons;

    public CommandRedeemCouponsFactory(CommandRedeemCoupons commandRedeemCoupons) {
        this.commandRedeemCoupons = commandRedeemCoupons;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_REDEEM_COUPONS.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandRedeemCoupons;
    }
}
