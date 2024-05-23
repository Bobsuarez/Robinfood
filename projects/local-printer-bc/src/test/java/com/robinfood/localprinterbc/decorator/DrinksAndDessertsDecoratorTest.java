package com.robinfood.localprinterbc.decorator;

import com.robinfood.localprinterbc.decorador.DrinksAndDessertsDecorator;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DrinksAndDessertsDecoratorTest {

    @Mock
    private IRuler mockRuler;

    @InjectMocks
    private DrinksAndDessertsDecorator drinksAndDessertsDecorator;

    private final OrderDetailDTOMock orderDetailDTOMock = new OrderDetailDTOMock();
    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();
    private final OrderPrintDTOMock orderPrintDTOMock = new OrderPrintDTOMock();

    @Test
    public void test_When_Drinks_And_Desserts_Decorator_With_Drinks() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProducts();

        drinksAndDessertsDecorator = new DrinksAndDessertsDecorator(mockRuler, orderDetailDTO);
        drinksAndDessertsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Drinks_And_Desserts_Decorator_Params_Template_IsEmpty() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();

        templateDTO.setRules(new ArrayList<>());
        drinksAndDessertsDecorator = new DrinksAndDessertsDecorator(mockRuler, orderDetailDTO);
        drinksAndDessertsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(null, orderPrintDTO.getSuggestedProducts());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Drinks_And_Desserts_Decorator_Product_Not_Group() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDessertsNotGroup();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProducts();

        drinksAndDessertsDecorator = new DrinksAndDessertsDecorator(mockRuler, orderDetailDTO);
        drinksAndDessertsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Drinks_And_Desserts_Decorator_Product_With_Group_Contains() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDessertsGroupContains();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProductsGroupContains();

        drinksAndDessertsDecorator = new DrinksAndDessertsDecorator(mockRuler, orderDetailDTO);
        drinksAndDessertsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Drinks_And_Desserts_Decorator_Product_Not_Group_Contains() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDessertsNotGroup();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProductsNotGroups();

        drinksAndDessertsDecorator = new DrinksAndDessertsDecorator(mockRuler, orderDetailDTO);
        drinksAndDessertsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Drinks_And_Desserts_Decorator_Product_With_Group_Contains_And_Drinks() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.suggestedProductsWithDrinksAndGroup();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedDrinksWithGroup();

        drinksAndDessertsDecorator = new DrinksAndDessertsDecorator(mockRuler, orderDetailDTO);
        drinksAndDessertsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderDetailDTO.getProducts().size(), orderPrintDTO.getSuggestedProducts().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }
}
