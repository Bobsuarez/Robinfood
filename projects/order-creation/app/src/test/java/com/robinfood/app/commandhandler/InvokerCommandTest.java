package com.robinfood.app.commandhandler;

import com.robinfood.app.commandhandler.commands.CommandRoundValuesTransaction;
import com.robinfood.app.commandhandler.factories.CommandFactory;
import com.robinfood.app.commandhandler.factories.CommandRoundValuesTransactionFactory;
import com.robinfood.app.commandhandler.factories.ICommandFactory;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.models.domain.Token;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class InvokerCommandTest {

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @Mock
    private IGetTokenBusinessCapabilityUseCase mockGetTokenBusinessCapabilityUseCase;

    @Mock
    private ICommandFactory mockICommandFactory;

    @Mock
    private CommandFactory mockCommandFactory;

    @Mock
    private CommandRoundValuesTransactionFactory mockCommandRoundValuesTransactionFactory;

    @Mock
    private CommandRoundValuesTransaction mockCommandRoundValuesTransaction;

    @InjectMocks
    private InvokerCommand invokerCommand;

    @Test
    void test_Single_Success() {

        final String command = "EXECUTE_COMMAND_INPUT_REQUEST_VALIDATION";
        final Token token = Token.builder().build();

        lenient().when(mockCommandFactory.invoke(command)).thenReturn(mockCommandRoundValuesTransactionFactory);
        lenient().when(mockCommandRoundValuesTransactionFactory.getCommand()).thenReturn(mockCommandRoundValuesTransaction);
        lenient().when(mockGetTokenBusinessCapabilityUseCase.invoke()).thenReturn(token);

        invokerCommand.single(command, transactionRequestDTO);
    }

    @Test
    void test_Single_When_Is_Null() {

        final String command = "EXECUTE_COMMAND_INPUT_REQUEST_VALIDATION";
        final Token token = Token.builder().build();

        lenient().when(mockCommandFactory.invoke(command)).thenReturn(null);
        lenient().when(mockCommandRoundValuesTransactionFactory.getCommand()).thenReturn(mockCommandRoundValuesTransaction);
        lenient().when(mockGetTokenBusinessCapabilityUseCase.invoke()).thenReturn(token);

        invokerCommand.single(command, transactionRequestDTO);
    }

    @Test
    void test_Group_Success() {

        final String command = "EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION";
        final String commandFactory = "EXECUTE_COMMAND_ROUND_VALUES_TRANSACTION";
        final Token token = Token.builder().build();

        lenient().when(mockCommandFactory.invoke(commandFactory)).thenReturn(mockCommandRoundValuesTransactionFactory);
        lenient().when(mockCommandRoundValuesTransactionFactory.getCommand()).thenReturn(mockCommandRoundValuesTransaction);
        lenient().when(mockGetTokenBusinessCapabilityUseCase.invoke()).thenReturn(token);

        invokerCommand.group(command, transactionRequestDTO);
    }

    @Test
    void test_Group_When_Is_Null() {

        final String command = "EXECUTE_GROUP_COMMAND_CREATE_TRANSACTION";
        final Token token = Token.builder().build();

        lenient().when(mockCommandFactory.invoke(command)).thenReturn(null);
        lenient().when(mockCommandRoundValuesTransactionFactory.getCommand()).thenReturn(mockCommandRoundValuesTransaction);
        lenient().when(mockGetTokenBusinessCapabilityUseCase.invoke()).thenReturn(token);

        invokerCommand.group(command, transactionRequestDTO);
    }

    @Test
    void test_Rollback_Success() {

        final String command = "EXECUTE_COMMAND_INPUT_REQUEST_VALIDATION";
        final Token token = Token.builder().build();

        SaveDataInMemoryUtil.setData("REQUEST_ORDER_CREATED", transactionRequestDTO);

        lenient().when(mockICommandFactory.apply(command)).thenReturn(Boolean.TRUE);
        lenient().when(mockCommandFactory.invoke(command)).thenReturn(mockCommandRoundValuesTransactionFactory);
        lenient().when(mockCommandRoundValuesTransactionFactory.getCommand()).thenReturn(mockCommandRoundValuesTransaction);
        lenient().when(mockGetTokenBusinessCapabilityUseCase.invoke()).thenReturn(token);

        invokerCommand.rollback();
    }

    @Test
    void test_Rollback_Command_Null() {

        final String command = "EXECUTE_COMMAND_INPUT_REQUEST_VALIDATION";
        final Token token = Token.builder().build();

        SaveDataInMemoryUtil.setData("KEY_REQUEST", transactionRequestDTO);

        lenient().when(mockICommandFactory.apply(command)).thenReturn(Boolean.TRUE);
        lenient().when(mockCommandFactory.invoke(command)).thenReturn(null);
        lenient().when(mockCommandRoundValuesTransactionFactory.getCommand()).thenReturn(mockCommandRoundValuesTransaction);
        lenient().when(mockGetTokenBusinessCapabilityUseCase.invoke()).thenReturn(token);

        invokerCommand.rollback();
    }
}
