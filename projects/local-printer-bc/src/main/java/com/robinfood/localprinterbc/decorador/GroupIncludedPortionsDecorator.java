package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.GroupPortionsPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.utils.ConvertToObject;
import com.robinfood.localprinterbc.utils.GetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.GROUP_INCLUDED_PORTIONS;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.GROUP_INCLUDED_PORTIONS_BASE_CUSTOM;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TITLE;

public class GroupIncludedPortionsDecorator extends AbstractRules {

    public GroupIncludedPortionsDecorator(IRuler ruler) {
        super(ruler);
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        super.createOrderPrint(orderPrintDTO, templateDTO);
        groupIncludedPortions(orderPrintDTO, templateDTO);
    }

    private static void groupIncludedPortions(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, GROUP_INCLUDED_PORTIONS);

        if (params.isEmpty()) {
            return;
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);

        String title = (String) propertyRule.get(TITLE);

        orderPrintDTO.getSuggestedProducts().forEach((OrderDetailProductDTO orderDetailProductDTO) ->
                orderDetailProductDTO.setToInclude(toIncludedGroupPortions(orderDetailProductDTO, title, templateDTO))
        );

    }

    private static GroupPortionsPrintDTO toIncludedGroupPortions(OrderDetailProductDTO orderDetailProductDTO,
                                                                 String title,
                                                                 TemplateDTO templateDTO) {

        List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOList = new ArrayList<>();

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                orderDetailProductGroupDTO.getPortions().forEach((OrderDetailProductGroupPortionDTO portion) -> {
                    if (portion.getAddition().equals(Boolean.FALSE)
                            && Objects.nonNull(portion.getName())
                            && Objects.isNull(portion.getChangedPortion())) {
                        orderDetailProductGroupPortionDTOList.add(portion);
                    }
                })
        );

        GroupPortionsPrintDTO groupPortionsPrintDTO = GroupPortionsPrintDTO.builder()
                .hasItem(!orderDetailProductGroupPortionDTOList.isEmpty())
                .title(title)
                .items(orderDetailProductGroupPortionDTOList)
                .build();

        groupIncludedPortionsBaseCustom(orderDetailProductDTO, groupPortionsPrintDTO, templateDTO);

        return groupPortionsPrintDTO;
    }

    /**
     * Valid if the dish category is a custom dish category
     *
     * @param orderDetailProductDTO Info product
     * @param groupPortionsPrintDTO group portions
     */
    private static void groupIncludedPortionsBaseCustom(OrderDetailProductDTO orderDetailProductDTO
            , GroupPortionsPrintDTO groupPortionsPrintDTO, TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, GROUP_INCLUDED_PORTIONS_BASE_CUSTOM);

        if (params.isEmpty()) {
            return;
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);
        List<Integer> customCategoriesList = (List<Integer>) propertyRule.get("customCategories");

        if (!customCategoriesList.contains(Integer.parseInt(orderDetailProductDTO.getCategoryId().toString()))) {
            return;
        }

        List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOList = new ArrayList<>();

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                orderDetailProductGroupDTO.getPortions().forEach((OrderDetailProductGroupPortionDTO portion) -> {
                    if (portion.getAddition().equals(Boolean.FALSE)
                            && Objects.nonNull(portion.getName())
                            && Objects.isNull(portion.getChangedPortion())) {
                        orderDetailProductGroupPortionDTOList.add(portion);
                    }
                })
        );

        orderDetailProductGroupPortionDTOList.forEach(orderDetailProductGroupPortionDTO ->
                groupPortionsPrintDTO.getItems().add(orderDetailProductGroupPortionDTO)
        );

    }

}

