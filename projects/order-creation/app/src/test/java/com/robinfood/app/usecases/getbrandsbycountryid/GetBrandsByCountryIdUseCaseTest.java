package com.robinfood.app.usecases.getbrandsbycountryid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.robinfood.app.mocks.domain.menu.BrandMock;
import com.robinfood.core.models.domain.menu.Brand;
import com.robinfood.repository.menu.IMenuRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetBrandsByCountryIdUseCaseTest {

    @Mock
    private IMenuRepository menuRepository;

    @InjectMocks
    private GetBrandsByCountryIdUseCase useCase;

    @Test
    void get_brands_by_country_id() {
        // Arrange
        String token = "token";
        Long countryId = 3L;

        List<Brand> brands = Collections.singletonList(
            BrandMock.build()
        );

        when(menuRepository.getBrandsByCountryId(any(), any())).thenReturn(brands);

        // Act
        List<Brand> result = useCase.invoke(token, countryId);

        // Assert
        assertEquals(brands.get(0).getId(), result.get(0).getId());
        assertEquals(brands.get(0).getCountryId(), result.get(0).getCountryId());
        assertEquals(brands.get(0).getFranchiseId(), result.get(0).getFranchiseId());
    }

}
