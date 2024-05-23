package com.robinfood.app.usecases.processorderfromconsumer;

import com.robinfood.app.commandhandler.IInvokerCommand;
import com.robinfood.app.mocks.TransactionRequestDTOMock;
import com.robinfood.app.mocks.queue.paymentmethod.order.OrderToCreateDTOMock;
import com.robinfood.app.usecases.messageproducer.IMessageProducerUseCase;
import com.robinfood.app.usecases.transformmenuerrormessage.ITransformMenuErrorMessageUseCase;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.exceptions.TransactionCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessOrderFromConsumerUseCaseTest {

    private final TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTOMock().transactionRequestDTO;

    @Mock
    private IInvokerCommand invokerCommand;

    @Mock
    private IMessageProducerUseCase messageProducerUseCase;

    @Mock
    private ITransformMenuErrorMessageUseCase transformMenuErrorMessageUseCase;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProcessOrderFromConsumerUseCase processOrderFromConsumerUseCase;

    @Test
    void test_Message_Success() {

        final String messageFrom = "from";
        final String messageCountry = "country";

        lenient().doNothing().when(invokerCommand).group(anyString(), eq(transactionRequestDTO));

        lenient().doNothing().when(messageProducerUseCase).invoke(
                OrderToCreateDTOMock.getDataDefault(),
                messageFrom,
                messageCountry
        );

        processOrderFromConsumerUseCase.invoke(OrderToCreateDTOMock.getDataDefault(), messageFrom, messageCountry);
    }

    @Test
    void test_Message_Error() throws TransactionCreationException {

        final String messageFrom = "from";
        final String messageCountry = "country";
        final String messageException = "Some exception";

        when(modelMapper.map(any(), any())).thenReturn(transactionRequestDTO);

        doThrow(new TransactionCreationException(HttpStatus.BAD_REQUEST, messageException))
                .when(invokerCommand)
                .group(anyString(), eq(transactionRequestDTO));

        lenient().doNothing().when(transformMenuErrorMessageUseCase).invoke(any(), any());

        try {
            processOrderFromConsumerUseCase.invoke(OrderToCreateDTOMock.getDataDefault(), messageFrom, messageCountry);
        } catch (TransactionCreationException transactionCreationException) {
            assertTrue(transactionCreationException.getLocalizedMessage().contains(messageException));
        }
    }
}
