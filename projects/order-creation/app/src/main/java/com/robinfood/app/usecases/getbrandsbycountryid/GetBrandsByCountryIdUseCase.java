package com.robinfood.app.usecases.getbrandsbycountryid;

import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.repository.menu.IMenuRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetBrandsByCountryIdUseCase implements IGetBrandsByCountryIdUseCase {

    private final IMenuRepository menuRepository;

    public GetBrandsByCountryIdUseCase(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Brand> invoke(
            String token,
            Long countryId
    ) {

        return menuRepository.getBrandsByCountryId(
                token,
                countryId
        );
    }
}
