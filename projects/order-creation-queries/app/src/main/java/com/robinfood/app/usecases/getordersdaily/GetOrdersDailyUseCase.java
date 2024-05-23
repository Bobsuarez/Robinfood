package com.robinfood.app.usecases.getordersdaily;

import com.robinfood.app.usecases.gettokenbusinesscapability.IGetTokenBusinessCapabilityUseCase;
import com.robinfood.core.dtos.BrandDTO;
import com.robinfood.core.dtos.OrderDailyDTO;
import com.robinfood.core.dtos.configuration.StoreDTO;
import com.robinfood.core.dtos.menu.MenuBrandDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.BrandMappers;
import com.robinfood.repository.configuration.store.IStoreRepository;
import com.robinfood.repository.menubrandstore.IMenuBrandStoreRepository;
import com.robinfood.repository.orderdaily.IOrderDailyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of IGetOrdersDailyUseCase
 */
@Service
@Slf4j
public class GetOrdersDailyUseCase implements IGetOrdersDailyUseCase {

    private final IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase;
    private final IMenuBrandStoreRepository menuBrandStoreRepository;
    private final IOrderDailyRepository orderDailyRepository;
    private final IStoreRepository storeRepository;

    public GetOrdersDailyUseCase(
            IGetTokenBusinessCapabilityUseCase getTokenBusinessCapabilityUseCase,
            IMenuBrandStoreRepository menuBrandStoreRepository,
            IOrderDailyRepository orderDailyRepository,
            IStoreRepository storeRepository
    ) {
        this.getTokenBusinessCapabilityUseCase = getTokenBusinessCapabilityUseCase;
        this.menuBrandStoreRepository = menuBrandStoreRepository;
        this.orderDailyRepository = orderDailyRepository;
        this.storeRepository = storeRepository;
    }

    public Result<List<OrderDailyDTO>> invoke(Long storeId, String timeZone) {

        log.info("Execute Orders Daily UseCase storeId {}, timeZone {}", storeId, timeZone);

        var token = getTokenBusinessCapabilityUseCase.invoke();

        Result<List<OrderDailyDTO>> orderDailyDTOS = this.orderDailyRepository.getOrderDaily(
                storeId,
                timeZone,
                token.getAccessToken()
        );

        if (orderDailyDTOS instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) orderDailyDTOS).getHttpStatus(),
                    ((Result.Error) orderDailyDTOS).getException().getMessage()
            );
        }

        final List<OrderDailyDTO> orderDailyDTOList = ((Result.Success<List<OrderDailyDTO>>) orderDailyDTOS).getData();

        log.info("Result Order Daily Repository {}", orderDailyDTOList);

        Result<List<MenuBrandDTO>> menuBrandDTOS = this.menuBrandStoreRepository.getMenuBrandStore(
                storeId,
                token.getAccessToken()
        );

        if (menuBrandDTOS instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) menuBrandDTOS).getHttpStatus(),
                    ((Result.Error) menuBrandDTOS).getException().getMessage()
            );
        }

        List<MenuBrandDTO> menuBrandDTOList = ((Result.Success<List<MenuBrandDTO>>) menuBrandDTOS).getData();

        log.info("Result Menu Brand Store Repository {}", menuBrandDTOList);

        Result<StoreDTO> storeDTOResult = this.storeRepository.getStore(
                storeId,
                token.getAccessToken()
        );

        if (storeDTOResult instanceof Result.Error) {
            throw new ResponseStatusException(
                    ((Result.Error) storeDTOResult).getHttpStatus(),
                    ((Result.Error) storeDTOResult).getException().getMessage()
            );
        }

        final StoreDTO storeDTO = ((Result.Success<StoreDTO>) storeDTOResult).getData();

        orderDailyDTOList.forEach((OrderDailyDTO orderDailyDTO) -> {

            BrandDTO brand = this.getBrand(orderDailyDTO, menuBrandDTOList, storeDTO);
            orderDailyDTO.setBrand(brand);
        });

        return orderDailyDTOS;
    }

    private BrandDTO getBrand(OrderDailyDTO orderDailyDTO, List<MenuBrandDTO> menuBrandDTOList, StoreDTO storeDTO) {

        Optional<MenuBrandDTO> currentMenuBrandOptional = menuBrandDTOList.stream().filter(
                menuBrandDTO -> orderDailyDTO.getBrandId().equals(menuBrandDTO.getFranchiseId())
        ).findFirst();

        BrandDTO currentBrand = null;
        if (currentMenuBrandOptional.isPresent()) {
            log.info("Current menu brand is Present {}", currentMenuBrandOptional.get());
            currentBrand = BrandMappers.menuBrandDtoToBrandDTO(currentMenuBrandOptional.get());
        }

        if (currentMenuBrandOptional.isEmpty()) {
            log.info("Current menu brand not is Present");

            currentBrand = BrandMappers.multiBrandDtoToBrandDTO(storeDTO.getMultiBrand());
        }

        return currentBrand;
    }
}
