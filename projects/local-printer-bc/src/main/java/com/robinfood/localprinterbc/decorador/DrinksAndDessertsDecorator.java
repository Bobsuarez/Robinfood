package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.GroupPortionsPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.utils.ConvertToObject;
import com.robinfood.localprinterbc.utils.GetParams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.DRINKS_AND_DESSERTS;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants
        .REMOVE_DRINKS_AND_DESSERTS_TO_SUGGESTED_PRODUCTS;

public class DrinksAndDessertsDecorator extends AbstractRules {

    private OrderDetailDTO orderDetailDTO;

    public DrinksAndDessertsDecorator(IRuler ruler, OrderDetailDTO orderDetailDTO) {
        super(ruler);
        this.orderDetailDTO = orderDetailDTO;
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        super.createOrderPrint(orderPrintDTO, templateDTO);
        drinksAndDesserts(orderPrintDTO, templateDTO);
    }

    private void drinksAndDesserts(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, DRINKS_AND_DESSERTS);

        if (params.isEmpty()) {
            return;
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);

        String title = (String) propertyRule.get("title");
        Map<String, Object> inArray = (Map<String, Object>) propertyRule.get("inArray");
        List<String> categoriesList = (List<String>) inArray.get("categories");
        List<Integer> drinksAndDessertsGroupsList = (List<Integer>) inArray.get("drinksAndDessertsGroups");

        List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOList = new ArrayList<>();
        orderDetailDTO.getProducts().forEach((OrderDetailProductDTO orderDetailProductDTO) -> {

            if (categoriesList.contains(orderDetailProductDTO.getCategoryName())
                    && orderDetailProductDTO.getGroups().isEmpty()) {
                orderDetailProductGroupPortionDTOList.add(buildOrderDetailGroupPortion(orderDetailProductDTO));
            } else {
                drinksAndDessertsGroups(drinksAndDessertsGroupsList, orderDetailProductDTO,
                        orderDetailProductGroupPortionDTOList);
            }
        });

        GroupPortionsPrintDTO drinksAndDessertsDTO = GroupPortionsPrintDTO.builder()
                .hasItem(!orderDetailProductGroupPortionDTOList.isEmpty())
                .title(title)
                .items(orderDetailProductGroupPortionDTOList)
                .build();

        orderPrintDTO.setDrinksAndDesserts(drinksAndDessertsDTO);
        removeDrinksAndDessertsToSuggestedProducts(orderPrintDTO.getSuggestedProducts(), templateDTO);

    }

    private static OrderDetailProductGroupPortionDTO buildOrderDetailGroupPortion(
            OrderDetailProductDTO orderDetailProductDTO) {

        return OrderDetailProductGroupPortionDTO.builder()
                .addition(Boolean.FALSE)
                .name(orderDetailProductDTO.getName())
                .quantity(orderDetailProductDTO.getQuantity()).build();
    }

    private static void drinksAndDessertsGroups(List<Integer> drinksAndDessertsGroupsList,
                                                OrderDetailProductDTO orderDetailProductDTO
            , List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOList) {

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO orderDetailProductGroupDTO) -> {
            if (drinksAndDessertsGroupsList.contains(Integer.parseInt(orderDetailProductGroupDTO.getId().toString()))) {
                orderDetailProductGroupDTO.getPortions().forEach((OrderDetailProductGroupPortionDTO portion) -> {
                    portion.setQuantity(
                            portion.getQuantity() * orderDetailProductDTO.getQuantity());
                    orderDetailProductGroupPortionDTOList.add(portion);
                });
            }
        });
    }

    private static void removeDrinksAndDessertsToSuggestedProducts(
            List<OrderDetailProductDTO> suggestedProductsDTOList, TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO,
                REMOVE_DRINKS_AND_DESSERTS_TO_SUGGESTED_PRODUCTS);

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);
        Map<String, Object> inArray = (Map<String, Object>) propertyRule.get("inArray");
        List<Integer> drinksAndDessertsGroupsList = (List<Integer>) inArray.get("drinksAndDessertsGroups");

        suggestedProductsDTOList.forEach((OrderDetailProductDTO orderDetailProductDTO) -> {
            Iterator<OrderDetailProductGroupDTO> iterator = orderDetailProductDTO.getGroups().iterator();
            while (iterator.hasNext()) {
                OrderDetailProductGroupDTO orderDetailProductGroupDTO = iterator.next();
                if (drinksAndDessertsGroupsList.contains(Integer.parseInt(
                        orderDetailProductGroupDTO.getId().toString()))) {
                    iterator.remove();
                }
            }
        });
    }

}

