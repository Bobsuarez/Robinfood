package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.utils.ConvertToObject;
import com.robinfood.localprinterbc.utils.GetParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.SUGGESTED_PRODUCTS;

public class SuggestedProductsDecorator extends AbstractRules {

    private OrderDetailDTO orderDetailDTO;

    public SuggestedProductsDecorator(IRuler ruler, OrderDetailDTO orderDetailDTO) {
        super(ruler);
        this.orderDetailDTO = orderDetailDTO;
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        super.createOrderPrint(orderPrintDTO, templateDTO);
        suggestedProducts(orderPrintDTO, templateDTO);
    }

    private void suggestedProducts(OrderPrintDTO orderPrintDTO,
                                   TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, SUGGESTED_PRODUCTS);

        if (params.isEmpty()) {
            return;
        }

        String rule = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);

        Map<String, Object> notInArrayMap = (Map<String, Object>) propertyRule.get("notInArray");
        List<String> categoriesList = (List<String>) notInArrayMap.get("categories");

        List<OrderDetailProductDTO> suggestedProductsDTOList = new ArrayList<>();

        orderDetailDTO.getProducts().forEach((OrderDetailProductDTO orderDetailProductDTO) -> {
            boolean noCategoriesSuggested = categoriesList.contains(orderDetailProductDTO.getCategoryName());
            if(!noCategoriesSuggested){
                suggestedProductsDTOList.add(orderDetailProductDTO);
            }
        });
        orderPrintDTO.setSuggestedProducts(suggestedProductsDTOList);
    }
}

