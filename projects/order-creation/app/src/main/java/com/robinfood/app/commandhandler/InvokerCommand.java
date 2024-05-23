package com.robinfood.app.commandhandler;

import com.robinfood.app.commandhandler.commands.ICommand;
import com.robinfood.app.commandhandler.factories.CommandFactory;
import com.robinfood.app.commandhandler.factories.ICommandFactory;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.DataInMemoryConstants.KEY_COMMANDS;
import static com.robinfood.core.constants.DataInMemoryConstants.REQUEST_ORDER_CREATED;

@Component
@Slf4j
public class InvokerCommand implements IInvokerCommand {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final CommandFactory commandFactory;

    public InvokerCommand(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            CommandFactory commandFactory
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.commandFactory = commandFactory;
    }

    private Map<Integer, List<CommandsEnum>> groupByOrderPriority(List<CommandsEnum> commandsEnums) {

        Map<Integer, List<CommandsEnum>> response = new HashMap<>();

        commandsEnums.forEach((CommandsEnum command) -> {
            List<CommandsEnum> group = response.getOrDefault(command.getPriority(), new ArrayList<>());
            group.add(command);
            response.put(command.getPriority(), group);
        });

        return response;
    }

    private void runCommandGroup(
            TransactionRequestDTO transactionRequestDTO,
            Map<Integer, List<CommandsEnum>> commandsEnumsByGroup
    ) {
        List<ICommand> allCommands = new ArrayList<>();

        commandsEnumsByGroup.forEach((Integer priority, List<CommandsEnum> commandsEnums) -> {

            final List<ICommand> commands = commandsInBlockGroups(commandsEnums);

            allCommands.addAll(commands);

            log.info("Running commands with priority: {}, yours commands: {}", priority, commands);
            execute(commands, transactionRequestDTO);
            log.info("Finished commands with priority: {}, yours commands: {}, have finish", priority, commands);

            SaveDataInMemoryUtil.setData(KEY_COMMANDS, allCommands);
        });
    }

    private List<ICommand> commandsInBlockGroups(
            List<CommandsEnum> commandsEnums
    ) {
        final List<ICommand> commands = new ArrayList<>();

        commandsEnums.forEach((CommandsEnum commandsEnum) -> {

                    final ICommandFactory getCommandFactory = commandFactory.invoke(commandsEnum.getName());

                    if (Objects.nonNull(getCommandFactory)) {
                        commands.add(getCommandFactory.getCommand());
                    }
                }
        );

        return commands;
    }

    private void execute(
            List<ICommand> commands,
            TransactionRequestDTO transactionRequestDTO
    ) {
        log.info("Running the following commands: {}", commands);

        final String token = getTokenBusinessCapabilityUseCase.invoke().getAccessToken();

        commands.forEach((ICommand command) -> command.execute(token, transactionRequestDTO));
    }

    @Override
    public void group(
            String commandGroup,
            TransactionRequestDTO transactionRequestDTO
    ) {
        final Map<String, List<CommandsEnum>> commandEnumGroups = Arrays.stream(
                CommandsEnum.values()).filter((CommandsEnum commandEnum) -> commandEnum.getGroup().equals(commandGroup)
        ).sorted(Comparator.comparing(CommandsEnum::getGroup))
                .collect(Collectors.groupingBy(CommandsEnum::getGroup));

        commandEnumGroups.forEach((String group, List<CommandsEnum> getCommandsEnums) -> {

            final Map<Integer, List<CommandsEnum>> commandsEnumsByGroup = this.groupByOrderPriority(getCommandsEnums);
            runCommandGroup(transactionRequestDTO, commandsEnumsByGroup);
        });
    }

    @Override
    public void single(
            String command,
            TransactionRequestDTO transactionRequestDTO
    ) {
        final ICommandFactory getCommandFactory = commandFactory.invoke(command);

        if (Objects.nonNull(getCommandFactory)) {
            final List<ICommand> commands = Collections.singletonList(getCommandFactory.getCommand());
            execute(commands, transactionRequestDTO);
        }
    }

    @Override
    public void rollback() {

        final List<ICommand> commandsExecuted = (List<ICommand>) SaveDataInMemoryUtil.getValue(KEY_COMMANDS);

        log.info("Running rollback of the following commands: {}", commandsExecuted);

        final String token = getTokenBusinessCapabilityUseCase.invoke().getAccessToken();

        final TransactionRequestDTO transactionRequestDTO = (TransactionRequestDTO) SaveDataInMemoryUtil
                .getValue(REQUEST_ORDER_CREATED);

        if (Objects.nonNull(transactionRequestDTO)) {
            commandsExecuted.forEach((ICommand command) -> command.rollback(token, transactionRequestDTO));
        }
    }
}
