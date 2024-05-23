package com.robinfood.app.usecases.getmenuproducts;

import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.menu.IMenuRepository;
import java.util.List;
import java.util.stream.Collectors;

import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of IGetMenuProductsUseCase
 */
@Slf4j
public class GetMenuProductsUseCase implements IGetMenuProductsUseCase {

    private final IMenuRepository menuRepository;

    public GetMenuProductsUseCase(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuHallProductResponseDTO> invoke(
            Long brandId,
            Long countryId,
            Long flowId,
            List<Long> productsIds,
            Long storeId,
            String token
    ) {
        log.info("Starting process to get the current Menu Product  with: BrandId: {}, countryId: {}, flowId: {}," +
                        " storeId: {}",
                brandId, countryId, flowId, storeId);

        final List<Result<MenuHallProductResponseDTO>> productResponseDTOResults = CollectionsKt.map(
                productsIds,
                (Long productId) -> menuRepository.getProductDetail(
                        brandId,
                        countryId,
                        flowId,
                        productId,
                        storeId,
                        token
                )
        );

        productResponseDTOResults.forEach((Result<MenuHallProductResponseDTO> productResponseDTOResult) -> {
            if (productResponseDTOResult instanceof Result.Error) {
                throw new ResponseStatusException(
                        ((Result.Error) productResponseDTOResult).getHttpStatus(),
                        ((Result.Error) productResponseDTOResult).getException().getMessage()
                );
            }
        });

        return productResponseDTOResults.stream().map(menuHallProductResponseDTOResult ->
                ((Result.Success<MenuHallProductResponseDTO>) menuHallProductResponseDTOResult).getData()
        ).collect(Collectors.toList());
    }
}
