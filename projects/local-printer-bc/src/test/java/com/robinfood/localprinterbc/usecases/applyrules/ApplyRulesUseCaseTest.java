package com.robinfood.localprinterbc.usecases.applyrules;

import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.mappers.orderprint.IOrderPrintMapper;
import com.robinfood.localprinterbc.mocks.OrderDetailDTOMock;
import com.robinfood.localprinterbc.mocks.OrderPrintDTOMock;
import com.robinfood.localprinterbc.mocks.TemplateDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyRulesUseCaseTest {

    @Mock
    private IOrderPrintMapper orderPrintMapper;

    @InjectMocks
    private ApplyRulesUseCase applyRulesUseCase;

    private final OrderDetailDTOMock orderDetailDTOMock = new OrderDetailDTOMock();
    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();
    private final OrderPrintDTOMock orderPrintDTOMock = new OrderPrintDTOMock();


    @Test
    public void test_When_Apply_Rules_UseCase_Decorator() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        when(orderPrintMapper.orderDetailDTOToOrderPrintDTO(any(OrderDetailDTO.class)))
                .thenReturn(orderPrintDTOMock.suggestedProducts());

        OrderPrintDTO orderPrintDTOResult = applyRulesUseCase.invoke(orderDetailDTO, templateDTO);

        assertNotNull(orderPrintDTOResult.getToGo());
    }
}
