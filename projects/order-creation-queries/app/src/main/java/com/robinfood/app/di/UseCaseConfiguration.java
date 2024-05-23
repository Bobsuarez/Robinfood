package com.robinfood.app.di;

import com.robinfood.app.usecases.completeorderproductsdata.CompleteOrderProductsDataFromMenuCurrentUseCase;
import com.robinfood.app.usecases.completeorderproductsdata.ICompleteOrderProductsDataFromMenuCurrentUseCase;
import com.robinfood.app.usecases.completeproducthalldata.CompleteMenuProductsDataFromMenuHallsUseCase;
import com.robinfood.app.usecases.completeproducthalldata.ICompleteMenuProductsDataFromMenuHallsUseCase;
import com.robinfood.app.usecases.completereplacementportionsdata.CompleteReplacementPortionsDataUseCase;
import com.robinfood.app.usecases.completereplacementportionsdata.ICompleteReplacementPortionsDataUseCase;
import com.robinfood.app.usecases.getmenucurrent.GetMenuCurrentUseCase;
import com.robinfood.app.usecases.getmenucurrent.IGetMenuCurrentUseCase;
import com.robinfood.app.usecases.getmenuproducts.GetMenuProductsUseCase;
import com.robinfood.app.usecases.getmenuproducts.IGetMenuProductsUseCase;
import com.robinfood.app.usecases.getorderdetail.GetOrderDetailUseCase;
import com.robinfood.app.usecases.getorderdetail.IGetOrderDetailUseCase;
import com.robinfood.app.usecases.getorderhistory.GetOrderHistoryUseCase;
import com.robinfood.app.usecases.getorderhistory.IGetOrderHistoryUseCase;
import com.robinfood.app.usecases.getsuggestedportions.GetSuggestedPortionsUseCase;
import com.robinfood.app.usecases.getsuggestedportions.IGetSuggestedPortionsUseCase;
import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.repository.menu.IMenuRepository;
import com.robinfood.repository.menu.MenuRepository;
import com.robinfood.repository.orderdetail.IOrderDetailRepository;
import com.robinfood.repository.orderhistory.IOrderHistoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    ICompleteMenuProductsDataFromMenuHallsUseCase provideCompleteMenuProductsDataFromMenuHallsUseCase(
        IGetMenuProductsUseCase getMenuProductsUseCase,
        ICompleteReplacementPortionsDataUseCase completeReplacementPortionsDataUseCase
    ) {
        return new CompleteMenuProductsDataFromMenuHallsUseCase(
            getMenuProductsUseCase,
            completeReplacementPortionsDataUseCase
        );
    }

    @Bean
    ICompleteOrderProductsDataFromMenuCurrentUseCase provideCompleteOrderProductsDataFromMenuCurrentUseCase(
        IGetMenuCurrentUseCase menuCurrentUseCase
    ) {
        return new CompleteOrderProductsDataFromMenuCurrentUseCase(menuCurrentUseCase);
    }

    @Bean
    ICompleteReplacementPortionsDataUseCase provideCompleteReplacementPortionsDataUseCase(
        IGetSuggestedPortionsUseCase getSuggestedPortionsUseCase
    ) {
        return new CompleteReplacementPortionsDataUseCase(getSuggestedPortionsUseCase);
    }

    @Bean
    IGetMenuCurrentUseCase provideGetMenuCurrentUseCase(MenuRepository menuRepository) {
        return new GetMenuCurrentUseCase(menuRepository);
    }

    @Bean
    IGetMenuProductsUseCase provideGetMenuProductsUseCase(MenuRepository menuRepository) {
        return new GetMenuProductsUseCase(menuRepository);
    }

    @Bean
    IGetOrderDetailUseCase provideGetOrderDetailUseCase(
        IOrderDetailRepository orderDetailRepository,
        ICompleteMenuProductsDataFromMenuHallsUseCase completeMenuProductsDataFromMenuHallsUseCase,
        ICompleteOrderProductsDataFromMenuCurrentUseCase completeOrderProductsDataFromMenuCurrentUseCase,
        IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        return new GetOrderDetailUseCase(
            orderDetailRepository,
            completeMenuProductsDataFromMenuHallsUseCase,
            completeOrderProductsDataFromMenuCurrentUseCase,
            getTokenBusinessCapabilityUseCase
        );
    }

    @Bean
    IGetOrderHistoryUseCase provideGetOrderHistoryUseCase(
        IOrderHistoryRepository orderHistoryRepository,
        IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase
    ) {
        return new GetOrderHistoryUseCase(
            orderHistoryRepository,
            getTokenBusinessCapabilityUseCase
        );
    }

    @Bean
    IGetSuggestedPortionsUseCase provideGetSuggestedPortionsUseCase(IMenuRepository menuRepository) {
        return new GetSuggestedPortionsUseCase(menuRepository);
    }

}
