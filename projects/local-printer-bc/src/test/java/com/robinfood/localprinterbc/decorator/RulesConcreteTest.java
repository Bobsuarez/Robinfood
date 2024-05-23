package com.robinfood.localprinterbc.decorator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localprinterbc.decorador.RulesConcrete;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.mocks.OrderDetailDTOMock;
import com.robinfood.localprinterbc.mocks.TemplateDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class RulesConcreteTest {

    @InjectMocks
    private RulesConcrete rulesConcrete;

    private final OrderDetailDTOMock orderDetailDTOMock = new OrderDetailDTOMock();
    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();

    @Test
    public void test_When_Rules_Concrete_To_Go() throws JsonProcessingException {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        rulesConcrete = new RulesConcrete(orderDetailDTO);
        rulesConcrete.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertNotNull(orderPrintDTO.getToGo());
    }

    @Test
    public void test_When_Rules_Concrete_To_Go_Delivery() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        orderDetailDTO.setDeliveryTypeId(3L);

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        rulesConcrete = new RulesConcrete(orderDetailDTO);
        rulesConcrete.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(Boolean.TRUE, orderPrintDTO.getToGo().getToGo());
    }

    @Test
    public void test_When_Rules_Concrete_To_Go_Template_Is_Empty() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        templateDTO.setRules(new ArrayList<>());

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        rulesConcrete = new RulesConcrete(orderDetailDTO);
        rulesConcrete.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertNull(orderPrintDTO.getToGo());
    }

    @Test
    public void test_When_Rules_Concrete_User_Is_Null() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        orderDetailDTO.setUser(null);

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        rulesConcrete = new RulesConcrete(orderDetailDTO);
        rulesConcrete.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertNull(orderPrintDTO.getUserAndIdOrder());
    }

    @Test
    public void test_When_Rules_Concrete_User_Is_Integration() {
        OrderDetailDTO orderDetailDTO = orderDetailDTOMock.drinksAndDesserts();
        TemplateDTO templateDTO = templateDTOMock.templateDTO();

        orderDetailDTO.setOrderIsIntegration(Boolean.TRUE);

        OrderPrintDTO orderPrintDTO = new OrderPrintDTO();
        rulesConcrete = new RulesConcrete(orderDetailDTO);
        rulesConcrete.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertNotNull(orderPrintDTO.getUserAndIdOrder());
    }

}
