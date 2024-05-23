package com.robinfood.localprinterbc.decorador;

import com.robinfood.localprinterbc.dtos.decorator.IdAndUserDTO;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.decorator.ToGoDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.utils.ConvertToObject;
import com.robinfood.localprinterbc.utils.GetParams;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.robinfood.localprinterbc.configs.constants.GlobalConstants.TOGO;

public class RulesConcrete implements IRuler {

    private OrderDetailDTO orderDetailDTO;

    public RulesConcrete(OrderDetailDTO orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }

    @Override
    public void createOrderPrint(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {
        toGo(orderPrintDTO, templateDTO);
        setIdAndUser(orderPrintDTO);
    }

    private void toGo(OrderPrintDTO orderPrintDTO, TemplateDTO templateDTO) {

        List<String> params = GetParams.getParamsTemplate(templateDTO, TOGO);

        if (params.isEmpty()) {
            return;
        }

        String ruleToGo = params.get(0);

        Map<String, Object> propertyRule = ConvertToObject.getMapObject(ruleToGo);
        String title = (String) propertyRule.get("title");
        Integer togoId = (Integer) propertyRule.get("TogoId");

        ToGoDTO toGoDTO = ToGoDTO.builder().title(title).build();

        if (orderDetailDTO.getDeliveryTypeId().equals(Long.parseLong(togoId.toString()))) {
            toGoDTO.setToGo(Boolean.TRUE);
        }

        orderPrintDTO.setToGo(toGoDTO);
    }

    private void setIdAndUser(OrderPrintDTO orderPrintDTO) {
        if (Objects.nonNull(orderDetailDTO.getUser())) {
            orderPrintDTO.setUserAndIdOrder(IdAndUserDTO.builder()
                    .id("(".concat(orderDetailDTO.getOrderNumber()).concat(")"))
                    .user(orderDetailDTO.getUser().getFirstName()
                            .concat(" ")
                            .concat(orderDetailDTO.getUser().getLastName()))
                    .build());
        }

        if (orderDetailDTO.getOrderIsIntegration().equals(Boolean.TRUE)) {
            orderPrintDTO.setUserAndIdOrder(IdAndUserDTO.builder()
                    .id(orderDetailDTO.getOrderIntegrationId().concat(" (")
                            .concat(orderDetailDTO.getOrderIntegrationCode().concat(")")))
                    .user(orderDetailDTO.getOrderIntegrationUser())
                    .orderIntegrationCode(orderDetailDTO.getOrderIntegrationCode())
                    .orderIntegrationId(orderDetailDTO.getOrderIntegrationId())
                    .build());
        }
    }

}
