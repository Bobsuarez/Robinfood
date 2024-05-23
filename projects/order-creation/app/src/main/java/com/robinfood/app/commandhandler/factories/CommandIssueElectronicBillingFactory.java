package com.robinfood.app.commandhandler.factories;

import com.robinfood.app.commandhandler.CommandsEnum;
import com.robinfood.app.commandhandler.commands.CommandIssueElectronicBilling;
import com.robinfood.app.commandhandler.commands.ICommand;
import org.springframework.stereotype.Component;

@Component
public class CommandIssueElectronicBillingFactory implements ICommandFactory{

    private final CommandIssueElectronicBilling commandIssueElectronicBilling;

    public CommandIssueElectronicBillingFactory(CommandIssueElectronicBilling commandIssueElectronicBilling) {
        this.commandIssueElectronicBilling = commandIssueElectronicBilling;
    }

    @Override
    public Boolean apply(String command) {
        return CommandsEnum.COMMAND_ISSUE_ELECTRONIC_BILLING.getName().equals(command);
    }

    @Override
    public ICommand getCommand() {
        return commandIssueElectronicBilling;
    }

}
