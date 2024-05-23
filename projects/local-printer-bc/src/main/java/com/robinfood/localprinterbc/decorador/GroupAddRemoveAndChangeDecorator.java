package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.GroupPortionsPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.RemovePortionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailRemovedPortionDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.utils.ConvertToObject;
import com.robinfood.localprinterbc.utils.GetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.GROUP_ADDED_PORTIONS;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.GROUP_CHANGED_PORTIONS;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.GROUP_REMOVED_PORTIONS;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TITLE;

public class GroupAddRemoveAndChangeDecorator extends AbstractRules {

    public GroupAddRemoveAndChangeDecorator(IRuler ruler) {
        super(ruler);
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        super.createOrderPrint(orderPrintDTO, templateDTO);
        groupAddRemoveAndChangePortion(orderPrintDTO, templateDTO);
    }

    private static void groupAddRemoveAndChangePortion(OrderPrintDTO orderPrintDTO
            , TemplateDTO templateDTO) {

        orderPrintDTO.getSuggestedProducts().forEach((OrderDetailProductDTO orderDetailProductDTO) -> {
            orderDetailProductDTO.setToAdd(groupAddedPortions(orderDetailProductDTO, templateDTO));
            orderDetailProductDTO.setToRemove(groupRemovedPortions(orderDetailProductDTO, templateDTO));
            orderDetailProductDTO.setToChange(groupChangedPortions(orderDetailProductDTO, templateDTO));
        });

    }

    private static GroupPortionsPrintDTO groupAddedPortions(OrderDetailProductDTO orderDetailProductDTO,
                                                            TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, GROUP_ADDED_PORTIONS);

        if (params.isEmpty()) {
            return GroupPortionsPrintDTO.builder().build();
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);

        String title = (String) propertyRule.get(TITLE);
        Map<String, Object> notInArray = (Map<String, Object>) propertyRule.get("notInArray");
        List<Integer> removalGroupsList = (List<Integer>) notInArray.get("removalGroups");

        List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOList = new ArrayList<>();

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                orderDetailProductGroupDTO.getPortions().forEach((OrderDetailProductGroupPortionDTO portion) -> {
                    if (portion.getAddition().equals(Boolean.TRUE)
                            && !removalGroupsList.contains(Integer.parseInt(
                            orderDetailProductGroupDTO.getId().toString()))) {
                        orderDetailProductGroupPortionDTOList.add(portion);
                    }
                })
        );

        return GroupPortionsPrintDTO.builder()
                .hasItem(!orderDetailProductGroupPortionDTOList.isEmpty())
                .title(title)
                .items(orderDetailProductGroupPortionDTOList)
                .build();

    }

    private static RemovePortionDTO groupRemovedPortions(OrderDetailProductDTO orderDetailProductDTO,
                                                         TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, GROUP_REMOVED_PORTIONS);

        if (params.isEmpty()) {
            return RemovePortionDTO.builder().build();
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);
        String title = (String) propertyRule.get("title");

        List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOArrayList = new ArrayList<>();

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                orderDetailProductGroupDTO.getRemovedPortions().forEach(orderDetailRemovedPortionDTOArrayList::add)
        );

        return RemovePortionDTO.builder()
                .hasItem(!orderDetailRemovedPortionDTOArrayList.isEmpty())
                .title(title)
                .items(orderDetailRemovedPortionDTOArrayList)
                .build();
    }

    private static GroupPortionsPrintDTO groupChangedPortions(OrderDetailProductDTO orderDetailProductDTO,
                                                              TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, GROUP_CHANGED_PORTIONS);

        if (params.isEmpty()) {
            return GroupPortionsPrintDTO.builder().build();
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);

        String title = (String) propertyRule.get("title");

        List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOList = new ArrayList<>();

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) ->
                orderDetailProductGroupDTO.getPortions().forEach((OrderDetailProductGroupPortionDTO
                                                                          orderDetailProductGroupPortionDTO) -> {
                    if (orderDetailProductGroupPortionDTO.getAddition().equals(Boolean.FALSE)
                            && Objects.nonNull(orderDetailProductGroupPortionDTO.getChangedPortion())) {
                        orderDetailProductGroupPortionDTOList.add(orderDetailProductGroupPortionDTO);
                    }
                })
        );

        return GroupPortionsPrintDTO.builder()
                .hasItem(!orderDetailProductGroupPortionDTOList.isEmpty())
                .title(title)
                .items(orderDetailProductGroupPortionDTOList)
                .build();
    }

}

