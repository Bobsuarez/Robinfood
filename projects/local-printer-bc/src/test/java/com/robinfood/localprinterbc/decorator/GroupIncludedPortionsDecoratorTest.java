package com.robinfood.localprinterbc.decorator;

import com.robinfood.localprinterbc.decorador.GroupIncludedPortionsDecorator;
import com.robinfood.localprinterbc.decorador.IRuler;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailChangedPortionDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TransformRulesDTO;
import com.robinfood.localprinterbc.mocks.OrderPrintDTOMock;
import com.robinfood.localprinterbc.mocks.TemplateDTOMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.GROUP_INCLUDED_PORTIONS_BASE_CUSTOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GroupIncludedPortionsDecoratorTest {

    @Mock
    private IRuler mockRuler;

    @InjectMocks
    private GroupIncludedPortionsDecorator groupIncludedPortionsDecorator;

    private final TemplateDTOMock templateDTOMock = new TemplateDTOMock();
    private final OrderPrintDTOMock orderPrintDTOMock = new OrderPrintDTOMock();

    @Test
    public void test_When_Group_Included_Portions_Decorator_Test() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProducts();

        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertEquals(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size()
                , orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions().size());
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Template_is_Empty() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.suggestedProducts();

        templateDTO.setRules(new ArrayList<>());
        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Change_Is_Null() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();


        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions()
                .forEach(orderDetailProductGroupPortionDTO -> orderDetailProductGroupPortionDTO.setChangedPortion(null)
                );
        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Change_Not_Null() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions()
                .forEach(orderDetailProductGroupPortionDTO -> orderDetailProductGroupPortionDTO.setChangedPortion(
                                OrderDetailChangedPortionDTO.builder()
                                        .id(1L)
                                        .name("test")
                                        .parentId(1L)
                                        .unitId(1L)
                                        .sku("testsku")
                                        .unitNumber(8978.00)
                                        .build()
                        )
                );
        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() == 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Portion_Name_Is_Null() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();


        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions()
                .forEach(orderDetailProductGroupPortionDTO -> orderDetailProductGroupPortionDTO.setName(null)
                );
        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() == 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Portion_Template_Base_Custom_Null() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();


        templateDTO.getRules().stream().filter(transformRulesDTO ->
                transformRulesDTO.getName().equals(GROUP_INCLUDED_PORTIONS_BASE_CUSTOM)).forEach(transformRulesDTO -> {
            templateDTO.getRules().remove(transformRulesDTO);
        });

        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Portion_Name_Category_Id_Custom() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        templateDTO.getRules().add(TransformRulesDTO.builder()
                .description("Suggested Products")
                .name("groupIncludedPortionsBaseCustom")
                .params("{\"title\": \"Ingredientes\", \"customCategories\": [2, 27, 14, 37, 20, 42, 43, 24, 34]}")
                .build());

        orderPrintDTO.getSuggestedProducts().get(0).setCategoryId(2L);
        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Portion_Name_Category_Portions_Name_Is_Null() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        templateDTO.getRules().add(TransformRulesDTO.builder()
                .description("Suggested Products")
                .name("groupIncludedPortionsBaseCustom")
                .params("{\"title\": \"Ingredientes\", \"customCategories\": [2, 27, 14, 37, 20, 42, 43, 24, 34]}")
                .build());

        orderPrintDTO.getSuggestedProducts().get(0).getGroups().get(0).getPortions()
                .forEach(orderDetailProductGroupPortionDTO -> orderDetailProductGroupPortionDTO.setName(null)
                );
        orderPrintDTO.getSuggestedProducts().get(0).setCategoryId(2L);
        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() == 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }

    @Test
    public void test_When_Group_Included_Portions_Decorator_Portion_Name_Category_Id_Custom_Not_Match() {
        TemplateDTO templateDTO = templateDTOMock.templateDTO();
        OrderPrintDTO orderPrintDTO = orderPrintDTOMock.toIncludeToRemoveToChange();

        templateDTO.getRules().add(TransformRulesDTO.builder()
                .description("Suggested Products")
                .name("groupIncludedPortionsBaseCustom")
                .params("{\"title\": \"Ingredientes\", \"customCategories\": [2, 27, 14, 37, 20, 42, 43, 24, 34]}")
                .build());

        groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(mockRuler);
        groupIncludedPortionsDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        // Assert
        assertTrue(orderPrintDTO.getSuggestedProducts().get(0).getToInclude().getItems().size() > 0);
        verify(mockRuler).createOrderPrint(orderPrintDTO, templateDTO);
    }


}
