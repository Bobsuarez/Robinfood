package com.robinfood.repository.menu;

import com.robinfood.core.dtos.MenuValidationDTO;
import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationEntity;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import com.robinfood.core.mappers.MenuValidationMappers;
import com.robinfood.core.models.domain.menu.Brand;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;

import static com.robinfood.core.util.ObjectMapperSingleton.objectToJson;

/**
 * Implementation of IMenuRepository
 */
@Slf4j
public class MenuRepository implements IMenuRepository {

    private final IMenuRemoteDataSource menuRemoteDataSource;
    private final ModelMapper modelMapper;

    public MenuRepository(IMenuRemoteDataSource menuRemoteDataSource, ModelMapper modelMapper) {
        this.menuRemoteDataSource = menuRemoteDataSource;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Brand> getBrandsByCountryId(
            String token,
            Long countryId
    ) {
        log.info("Going out to get brands by country id {}", countryId);

        return menuRemoteDataSource.getBrandsByCountryId(token, countryId).stream()
                .map(response -> modelMapper.map(response, Brand.class))
                .collect(Collectors.toList());
    }

    @Async
    @Override
    public CompletableFuture<Boolean> validateMenu(
            String token,
            MenuValidationDTO menuValidationDTO
    ) {
        CreateTransactionCustomLog.addUuid();
        log.info("Going out to validate menu with data: {} and timezone: {}",
                objectToJson(menuValidationDTO.getOrder()), menuValidationDTO.getTimezone());

        final MenuValidationEntity menuValidationEntity = MenuValidationMappers
                .toMenuValidationEntity(menuValidationDTO);

        CreateTransactionCustomLog.removeHits();
        return menuRemoteDataSource.validateMenu(token, menuValidationDTO.getTimezone(), menuValidationEntity);
    }

    @Override
    public MenuHallProductDetailDTO getMenuHallProductDetail(String token, Long menuHallProductId) {

        CreateTransactionCustomLog.addUuid();

        log.info("Going out to get menu hall product detail by id {}", menuHallProductId);

        CreateTransactionCustomLog.removeHits();
        return modelMapper.map(
                menuRemoteDataSource.getMenuHallProductDetail(token, menuHallProductId),
                MenuHallProductDetailDTO.class
        );
    }
}
