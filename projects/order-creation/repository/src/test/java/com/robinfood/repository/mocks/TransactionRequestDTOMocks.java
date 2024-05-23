package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.transactionrequestdto.CompanyDTO;
import com.robinfood.core.dtos.transactionrequestdto.CorporateDTO;
import com.robinfood.core.dtos.transactionrequestdto.DeviceDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductArticleDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductBrandDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductCategoryDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductSizeDTO;
import com.robinfood.core.dtos.transactionrequestdto.FlagDTO;
import com.robinfood.core.dtos.transactionrequestdto.GroupDTO;
import com.robinfood.core.dtos.transactionrequestdto.NewUserDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.OriginDTO;
import com.robinfood.core.dtos.transactionrequestdto.PitsDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.SubmarineDTO;
import com.robinfood.core.dtos.transactionrequestdto.TogoDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.models.domain.configuration.StoreInformation;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TransactionRequestDTOMocks {

    private final CompanyDTO companyDTO = new CompanyDTO(
            "COP",
            1L
    );

    private final DeviceDTO deviceDTO = new DeviceDTO(
            "1.0.0",
            1L,
            "America/Colombia",
            "1.0"
    );

    private final FinalProductBrandDTO brandDTO = new FinalProductBrandDTO(
            1L,
            "RobinFood"
    );

    private final CorporateDTO corporateDTO = new CorporateDTO(
            false
    );

    private final PitsDTO pitsDTO = new PitsDTO(
            "ABC123",
            false
    );

    private final SubmarineDTO submarineDTO = new SubmarineDTO(
            false
    );

    private final TogoDTO togoDTO = new TogoDTO(
            true,
            1L
    );

    private final FlagDTO flagDTO = new FlagDTO(
            corporateDTO,
            pitsDTO,
            submarineDTO,
            togoDTO
    );

    private final OriginDTO originDTO = new OriginDTO(
            1L,
            "POSv2"
    );

    private final ServiceDTO serviceDTO = new ServiceDTO(
            BigDecimal.valueOf(2000),
            1L,
            BigDecimal.valueOf(9407.4074),
            BigDecimal.valueOf(8),
            BigDecimal.valueOf(592.5926),
            BigDecimal.valueOf(8000),
            BigDecimal.valueOf(10000)
    );

    private final StoreDTO storeDTO = new StoreDTO(
            "code",
            1L,
            "Store test",
            1L,
            "postalCode"
    );

    private final FinalProductArticleDTO finalProductArticleDTO = new FinalProductArticleDTO(
            1L,
            1L,
            1L,
            "ARTICLE"
    );

    private final FinalProductBrandDTO finalProductBrandDTO = new FinalProductBrandDTO(
            1L,
            "RobinFood"
    );

    public final FinalProductCategoryDTO categoryDTO = new FinalProductCategoryDTO(
            1L,
            "Category"
    );

    private final FinalProductSizeDTO sizeDTO = new FinalProductSizeDTO(
            1L,
            "MUY"
    );

    private final PortionProductDTO portionProductDTO = new PortionProductDTO(
            1L,
            "Portion product"
    );

    private final List<PortionDTO> portionDTOs = Collections.singletonList(
            new PortionDTO(
                    BigDecimal.ZERO,
                    1,
                    1L,
                    true,
                    "Portion",
                    BigDecimal.valueOf(500.0),
                    portionProductDTO,
                    1,
                    null,
                    "sku",
                    1L,
                    1L
            )
    );

    private final List<GroupDTO> groupDTOs = Collections.singletonList(
            new GroupDTO(
                    1L,
                    "Group",
                    portionDTOs,
                    "sku"
            )
    );

    public final List<FinalProductDTO> finalProductDTOs = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(Collections.emptyList())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(10000.0))
                    .quantity(1)
                    .removedPortions(Collections.emptyList())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(Collections.emptyList())
                    .totalPrice(BigDecimal.valueOf(10000.0))
                    .build());

    private final List<OrderDTO> orderDTOs = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(Collections.emptyList())
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .isMultiBrand(Boolean.TRUE)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(10000.0))
                    .uuid(UUID.randomUUID())
                    .build());

    private final NewUserDTO userDTO = new NewUserDTO(
            1L,
            "User",
            "Test",
            "00",
            "9999999999",
            "test@test.com"
    );

    private final StoreInformation storeInfo = StoreInformation.builder()
            .id(123L)
            .name("Tienda ficticia 123")
            .build();

    public final TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder()
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(1L)
            .coupons(List.of())
            .performCouponResponseDTOS(List.of())
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(Collections.emptyList())
            .total(BigDecimal.valueOf(10000.0))
            .user(userDTO)
            .uuid(UUID.randomUUID())
            .build();

    public final TransactionRequestDTO transactionRequestDTOServices = TransactionRequestDTO.builder()
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(1L)
            .coupons(List.of())
            .performCouponResponseDTOS(List.of())
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(Collections.emptyList())
            .total(BigDecimal.valueOf(10000.0))
            .user(userDTO)
            .storeInfo(storeInfo)
            .build();
}
