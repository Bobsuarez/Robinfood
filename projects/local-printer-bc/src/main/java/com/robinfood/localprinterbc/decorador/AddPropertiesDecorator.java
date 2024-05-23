package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailProductGroupPortionDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailRemovedPortionDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.utils.ConvertToObject;
import com.robinfood.localprinterbc.utils.GetParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.ADDITION;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.ADD_PROPERTY;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.CHANGED_PORTION;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.CHILD_PATH_TO;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.ID;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.IS_ADDITION;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.PROP_IN;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.SYMBOL;
import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.WHEREIN;

public class AddPropertiesDecorator extends AbstractRules {

    public AddPropertiesDecorator(IRuler ruler) {
        super(ruler);
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        super.createOrderPrint(orderPrintDTO, templateDTO);
        addProperties(orderPrintDTO, templateDTO);
    }

    private static void addProperties(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {

        List<String> properties = GetParams.getParamsTemplate(templateDTO, ADD_PROPERTY);

        for (String p : properties) {
            if (!p.contains("item")) {
                continue;
            }

            if (p.contains("pathTo")) {
                List<Map<String, Object>> symbol = getSymbol(p);
                putProperty(orderPrintDTO, symbol);
            }

        }

    }

    private static List<Map<String, Object>> getSymbol(String rule) {

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(rule);
        List<Map<String, Object>> properties = (List<Map<String, Object>>) propertyRule.get("properties");

        List<Map<String, Object>> result = new ArrayList<>();

        String symbol = null;
        Map<String, Object> mapProperties;


        for (Map<String, Object> p : properties) {

            mapProperties = new HashMap<>();
            mapProperties.put(IS_ADDITION, Boolean.FALSE);
            mapProperties.put(SYMBOL, symbol);
            mapProperties.put(WHEREIN, new ArrayList<Integer>());
            mapProperties.put(PROP_IN, new ArrayList<String>());
            mapProperties.put(CHILD_PATH_TO, null);

            Map<String, Object> item = (Map<String, Object>) p.get("item");
            mapProperties.put(SYMBOL, item.get(SYMBOL));

            if (p.containsKey("whereEq")) {
                List<String> whereEq = (List<String>) p.get("whereEq");
                mapProperties.put(IS_ADDITION, validateAdditionString(whereEq));
            }

            if (p.containsKey(WHEREIN)) {
                List<Object> whereIn = (List<Object>) p.get(WHEREIN);
                mapProperties.put(WHEREIN, getIdGroups(whereIn));
            }

            if (p.containsKey(PROP_IN)) {
                List<String> propIn = (List<String>) p.get(PROP_IN);
                mapProperties.put(PROP_IN, propIn);
                String childPathTo = (String) p.get(CHILD_PATH_TO);
                mapProperties.put(CHILD_PATH_TO, childPathTo);
            }

            result.add(mapProperties);
        }

        return result;
    }

    private static List<Integer> getIdGroups(List<Object> ids) {

        List<Integer> idsInteger = new ArrayList<>();

        for (Object id : ids) {
            if (id.toString().equals(ID)) {
                continue;
            }

            idsInteger = (List<Integer>) id;
        }

        return idsInteger;
    }

    private static Boolean validateAdditionString(List<String> values) {
        Boolean isAddition = Boolean.FALSE;
        for (Object addition : values) {
            if (addition.toString().equals(ADDITION)) {
                continue;
            }
            isAddition = Boolean.parseBoolean(addition.toString());
        }

        return isAddition;
    }


    private static void putProperty(OrderPrintDTO orderPrintDTO, List<Map<String, Object>> mapListProperties) {

        Map<String, Object> mapProperties = mapListProperties.get(0);

        String symbol = mapProperties.get(SYMBOL).toString();
        Boolean isAddition = Boolean.parseBoolean(mapProperties.get(IS_ADDITION).toString());

        orderPrintDTO.getSuggestedProducts().forEach((OrderDetailProductDTO orderDetailProductDTO) -> {
            putPropertyRemove(orderDetailProductDTO, symbol);
            if (isAddition.equals(Boolean.TRUE)) {
                putPropertyAddition(orderDetailProductDTO, symbol);
            }

            for (Map<String, Object> properties : mapListProperties) {
                putPropertyChange(orderDetailProductDTO, properties);
            }

        });
    }

    private static void putPropertyRemove(OrderDetailProductDTO orderDetailProductDTO, String symbol) {
        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO group) ->
                group.getRemovedPortions().forEach((OrderDetailRemovedPortionDTO orderDetailRemovedPortionDTO) ->
                        orderDetailRemovedPortionDTO.setSymbol(symbol)
                )
        );
    }

    private static void putPropertyAddition(OrderDetailProductDTO orderDetailProductDTO, String symbol) {
        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO group) ->
                group.getPortions().forEach((OrderDetailProductGroupPortionDTO portion) -> {
                    if (portion.getAddition().equals(Boolean.TRUE)) {
                        portion.setSymbol(symbol);
                    }
                })
        );
    }

    private static void putPropertyChange(OrderDetailProductDTO orderDetailProductDTO
            , Map<String, Object> mapProperties) {

        List<String> propIn = (List<String>) mapProperties.get(PROP_IN);

        if (!propIn.isEmpty() && !propIn.get(0).equals(CHANGED_PORTION)) {
            return;
        }

        String symbol = mapProperties.get(SYMBOL).toString();

        List<Integer> groupWhereIn = (List<Integer>) mapProperties.get("whereIn");

        orderDetailProductDTO.getGroups().forEach((OrderDetailProductGroupDTO group) ->
                group.getPortions().forEach((OrderDetailProductGroupPortionDTO portion) -> {
                    if (Objects.nonNull(portion.getChangedPortion()) && !propIn.isEmpty()) {
                        portion.setSymbol(symbol);
                    }
                    if (groupWhereIn.contains(Integer.parseInt(group.getId().toString()))) {
                        portion.setSymbol(symbol);
                    }
                })
        );
    }

}

