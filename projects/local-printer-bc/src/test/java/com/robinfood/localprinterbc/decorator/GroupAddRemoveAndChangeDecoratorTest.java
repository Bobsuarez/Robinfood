package com.robinfood.localprinterbc.decorator;

import com.robinfood.localprinterbc.decorador.GroupAddRemoveAndChangeDecorator;
import com.robinfood.localprinterbc.decorador.IRuler;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.mocks.OrderPrintDTOMock;
import com.robinfood.localprinterbc.mocks.TemplateDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GroupAddRemoveAndChangeDecoratorTest {
    @Mock
    private IRuler mockRuler;

    @InjectMocks
    private GroupAddRemoveAndChangeDecorator groupAddRemoveAndChangeDecorator;

    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();
    private final OrderPrintDTOMock orderPrintDTOMock = new OrderPrintDTOMock();

    @Test
    public void test_Group_Add_Remove_And_Change_Decorator_With_Drinks() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange1();

        groupAddRemoveAndChangeDecorator = new GroupAddRemoveAndChangeDecorator(mockRuler);
        groupAddRemoveAndChangeDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToRemove().getItems().size() > 0);
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToChange().getItems().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_Group_Add_Remove_And_Change_Decorator_Template_isEmpty() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        templateDTO.setRules(new ArrayList<>());
        groupAddRemoveAndChangeDecorator = new GroupAddRemoveAndChangeDecorator(mockRuler);
        groupAddRemoveAndChangeDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_Group_Add_Remove_And_Change_Decorator_Remove_isEmpty() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).setRemovedPortions(new ArrayList<>());
        groupAddRemoveAndChangeDecorator = new GroupAddRemoveAndChangeDecorator(mockRuler);
        groupAddRemoveAndChangeDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToRemove().getItems().size() == 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_Group_Add_Remove_And_Change_Decorator_Remove_Gruops_Contains_Rule() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions().get(0).setAddition(Boolean.TRUE);
        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).setId(70L);
        groupAddRemoveAndChangeDecorator = new GroupAddRemoveAndChangeDecorator(mockRuler);
        groupAddRemoveAndChangeDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToAdd().getItems().size() == 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_Group_Add_Remove_And_Change_Decorator_Remove_Gruops_Change_Is_Null() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions()
                .forEach(orderDetailProductGroupPortionDTO -> orderDetailProductGroupPortionDTO.setChangedPortion(null)
                );
        orderPrintDTO.getSuggestedProducts().get(0).getToChange().setItems(new ArrayList<>());
        groupAddRemoveAndChangeDecorator = new GroupAddRemoveAndChangeDecorator(mockRuler);
        groupAddRemoveAndChangeDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToChange().getItems().size() == 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }
}
