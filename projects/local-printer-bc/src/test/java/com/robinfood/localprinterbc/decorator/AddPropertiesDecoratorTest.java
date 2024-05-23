package com.robinfood.localprinterbc.decorator;

import com.robinfood.localprinterbc.decorador.AddPropertiesDecorator;
import com.robinfood.localprinterbc.decorador.IRuler;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.mocks.OrderDetailDTOMock;
import com.robinfood.localprinterbc.mocks.OrderPrintDTOMock;
import com.robinfood.localprinterbc.mocks.TemplateDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddPropertiesDecoratorTest {
    @Mock
    private IRuler mockRuler;

    @InjectMocks
    private AddPropertiesDecorator addPropertiesDecorator;

    private final OrderDetailDTOMock orderDetailDTOMock = new OrderDetailDTOMock();
    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();
    private final OrderPrintDTOMock orderPrintDTOMock = new OrderPrintDTOMock();

    @Test
    public void testCreateOrderPrintProducts() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProducts();

        addPropertiesDecorator = new AddPropertiesDecorator(mockRuler);
        addPropertiesDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint( orderPrintDTO, templateDTO);
    }

    @Test
    public void testCreateOrderPrintItems() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProducts();

        addPropertiesDecorator = new AddPropertiesDecorator(mockRuler);
        addPropertiesDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint( orderPrintDTO, templateDTO);
    }

    @Test
    public void testCreateOrderPrintToIncludeToRemoveToChange() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        addPropertiesDecorator = new AddPropertiesDecorator(mockRuler);
        addPropertiesDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint( orderPrintDTO, templateDTO);
    }
}
