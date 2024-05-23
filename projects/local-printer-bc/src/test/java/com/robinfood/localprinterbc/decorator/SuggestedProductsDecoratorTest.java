package com.robinfood.localprinterbc.decorator;

import com.robinfood.localprinterbc.decorador.IRuler;
import com.robinfood.localprinterbc.decorador.SuggestedProductsDecorator;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.mocks.OrderDetailDTOMock;
import com.robinfood.localprinterbc.mocks.TemplateDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SuggestedProductsDecoratorTest {

    @Mock
    private IRuler mockRuler;

    @InjectMocks
    private SuggestedProductsDecorator suggestedProductsDecorator;

    private final OrderDetailDTOMock orderDetailDTOMock = new OrderDetailDTOMock();
    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();

    @Test
    public void testCreateOrderPrintWithSuggestedProducts() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        suggestedProductsDecorator = new SuggestedProductsDecorator(mockRuler, orderDetailDTO);
        suggestedProductsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void testCreateOrderPrintWithSuggested_Not_Products() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        orderDetailDTO.setProducts(new ArrayList<>());

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        suggestedProductsDecorator = new SuggestedProductsDecorator(mockRuler, orderDetailDTO);
        suggestedProductsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void testCreateOrderPrintWithSuggested_Not_Products_Groups() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        orderDetailDTO.getProducts().get(0).setGroups(new ArrayList<>());

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        suggestedProductsDecorator = new SuggestedProductsDecorator(mockRuler, orderDetailDTO);
        suggestedProductsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(1, orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void testCreateOrderPrintWithSuggested_Not_Products_Dessert() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        orderDetailDTO.getProducts().get(0).setCategoryName("Postres");

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        suggestedProductsDecorator = new SuggestedProductsDecorator(mockRuler, orderDetailDTO);
        suggestedProductsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(0, orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void testCreateOrderPrintWithSuggested_Params_Is_Empty() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProducts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        templateDTO.setRules(new ArrayList<>());

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        suggestedProductsDecorator = new SuggestedProductsDecorator(mockRuler, orderDetailDTO);
        suggestedProductsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(null, orderPrintDTO.getSuggestedProducts());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

}

