package com.robinfood.localprinterbc.usecases.applyrules;

import com.robinfood.localprinterbc.decorador.AddPropertiesDecorator;
import com.robinfood.localprinterbc.decorador.DrinksAndDessertsDecorator;
import com.robinfood.localprinterbc.decorador.GroupAddRemoveAndChangeDecorator;
import com.robinfood.localprinterbc.decorador.GroupIncludedPortionsDecorator;
import com.robinfood.localprinterbc.decorador.IRuler;
import com.robinfood.localprinterbc.decorador.RulesConcrete;
import com.robinfood.localprinterbc.decorador.SuggestedProductsDecorator;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.printingtemplate.TemplateDTO;
import com.robinfood.localprinterbc.mappers.orderprint.IOrderPrintMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplyRulesUseCase implements IApplyRulesUseCase {

    private final IOrderPrintMapper orderPrintMapper;

    public ApplyRulesUseCase(IOrderPrintMapper orderPrintMapper) {
        this.orderPrintMapper = orderPrintMapper;
    }

    @Override
    public OrderPrintDTO invoke(OrderDetailDTO orderDetailDTO, TemplateDTO templateDTO) {

        OrderPrintDTO orderPrintDTO = orderPrintMapper.orderDetailDTOToOrderPrintDTO(orderDetailDTO);

        IRuler rulesConcrete = new RulesConcrete(orderDetailDTO);
        rulesConcrete.createOrderPrint(orderPrintDTO, templateDTO);

        IRuler suggestedProductsDecorator = new SuggestedProductsDecorator(new RulesConcrete(orderDetailDTO)
                ,orderDetailDTO);
        IRuler drinksAndDesserts = new DrinksAndDessertsDecorator(suggestedProductsDecorator,orderDetailDTO);
        IRuler groupIncludedPortionsDecorator = new GroupIncludedPortionsDecorator(drinksAndDesserts);
        IRuler groupAddRemoveAndChangeDecorator = new GroupAddRemoveAndChangeDecorator(groupIncludedPortionsDecorator);
        IRuler addPropertiesDecorator = new AddPropertiesDecorator(groupAddRemoveAndChangeDecorator);

        addPropertiesDecorator.createOrderPrint(orderPrintDTO, templateDTO);

        return orderPrintDTO;
    }
}
