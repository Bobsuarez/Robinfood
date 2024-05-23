package com.robinfood.app.mocks;

import com.robinfood.app.mocks.domain.configuration.ChannelMock;
import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.transactionrequestdto.CompanyDTO;
import com.robinfood.core.dtos.transactionrequestdto.CorporateDTO;
import com.robinfood.core.dtos.transactionrequestdto.CouponDTO;
import com.robinfood.core.dtos.transactionrequestdto.DeviceDTO;
import com.robinfood.core.dtos.transactionrequestdto.DiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductArticleDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductBrandDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductDiscountDTO;
import com.robinfood.core.dtos.transactionrequestdto.FinalProductSizeDTO;
import com.robinfood.core.dtos.transactionrequestdto.FlagDTO;
import com.robinfood.core.dtos.transactionrequestdto.GroupDTO;
import com.robinfood.core.dtos.transactionrequestdto.NewUserDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.dtos.transactionrequestdto.OriginDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDTO;
import com.robinfood.core.dtos.transactionrequestdto.PaymentMethodDetailDTO;
import com.robinfood.core.dtos.transactionrequestdto.PitsDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionDTO;
import com.robinfood.core.dtos.transactionrequestdto.PortionProductDTO;
import com.robinfood.core.dtos.transactionrequestdto.ServiceDTO;
import com.robinfood.core.dtos.transactionrequestdto.StoreDTO;
import com.robinfood.core.dtos.transactionrequestdto.SubmarineDTO;
import com.robinfood.core.dtos.transactionrequestdto.TogoDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.core.dtos.transactionrequestdto.ThirdPartyDTO;
import com.robinfood.core.dtos.transactionresponsedto.OrderDiscountResponseDTO;
import com.robinfood.core.models.domain.configuration.City;
import com.robinfood.core.models.domain.configuration.StoreInformation;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.robinfood.core.constants.GlobalConstants.NUMBER_DECIMAL_PLACES;

public class TransactionRequestDTOMock {

    @Value("${payment.method-foodcoins-id}")
    private Long foodcoinsPaymentMethodId;

    private final CompanyDTO companyDTO = new CompanyDTO(
            "COP",
            1L
    );

    private final List<DeductionDTO> deduction = new ArrayList<>();

    private final DeviceDTO deviceDTO = new DeviceDTO(
            "192.168.1.1",
            1L,
            "America/Bogota",
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
            10L,
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
            "1234567"
    );

    private final ThirdPartyDTO thirdPartyDTO = ThirdPartyDTO.builder()
            .documentType(1L)
            .documentNumber("12345689")
            .email("email@gmail.com")
            .fullName("Nombre completo")
            .phone("3003222222")
            .build();

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

    private final FinalProductBrandDTO finalProductBrandDTOTwo = new FinalProductBrandDTO(
            2L,
            "Pixi"
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
                    BigDecimal.valueOf(0),
                    portionProductDTO,
                    1,
                    null,
                    "sku",
                    1L,
                    1L
            )
    );

    private final List<PortionDTO> portionWithAdditionDTOs = Collections.singletonList(
            new PortionDTO(
                    BigDecimal.ZERO,
                    0,
                    1L,
                    true,
                    "Portion",
                    BigDecimal.valueOf(3000),
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

    private final List<GroupDTO> groupWithAdditionDTOs = Collections.singletonList(
            new GroupDTO(
                    1L,
                    "Group",
                    portionWithAdditionDTOs,
                    "sku"
            )
    );

    private final List<FinalProductDiscountDTO> finalProductDiscountDTOS = new ArrayList<>(
            Collections.singletonList(
                    FinalProductDiscountDTO.builder()
                            .value(BigDecimal.valueOf(3000.0))
                            .build()


            )
    );

    private final List<FinalProductDiscountDTO> finalProductDiscountDTOSTax = new ArrayList<>(
            Collections.singletonList(
                    new FinalProductDiscountDTO(
                            BigDecimal.valueOf(3000.0),
                            true,
                            false,
                            ""
                    )
            )
    );

    private final List<FinalProductDTO> finalProductDTOs = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .category(FinalProductCategoryDTOMocks.build())
                    .discounts(finalProductDiscountDTOS)
                    .groups(groupDTOs)
                    .deduction(deduction)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<FinalProductDTO> finalProductDTOsTaxesInfo = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .category(FinalProductCategoryDTOMocks.build())
                    .discounts(finalProductDiscountDTOSTax)
                    .groups(groupDTOs)
                    .deduction(deduction)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<FinalProductDTO> finalProductDTOsTax = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(finalProductDiscountDTOSTax)
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<FinalProductDTO> finalProductDTOsDifferentBrand = Arrays.asList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(finalProductDiscountDTOS)
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build(),

            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTOTwo)
                    .discounts(finalProductDiscountDTOS)
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<FinalProductDTO> finalProductWithAdditionDTOs = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(finalProductDiscountDTOS)
                    .groups(groupWithAdditionDTOs)
                    .deduction(deduction)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<FinalProductDTO> finalProductDTOCo2 = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .co2Total(BigDecimal.valueOf(50L))
                    .discounts(finalProductDiscountDTOS)
                    .groups(groupDTOs)
                    .deduction(deduction)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<CouponDTO> couponsDTOs = Collections.singletonList(
            new CouponDTO(
                    "CUPONOK",
                    BigDecimal.valueOf(100.0)
            )
    );

    private final List<DeductionDTO> deductionsDTO = Collections.singletonList(
            new DeductionDTO(
                    1L,
                    BigDecimal.valueOf(2000)
            )
    );

    private final List<DeductionDTO> deductionsDTOTotal = Collections.singletonList(
            new DeductionDTO(
                    1L,
                    BigDecimal.valueOf(8000.0000)
            )
    );


    public final List<OrderDTO> orderDTOs = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .coupons(couponsDTOs)
                    .couponCriteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .deductions(deduction)
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0000).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .build()
    );

    public final List<OrderDTO> orderDTOsTaxes = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .couponCriteriaInfo(new HashMap<>())
                    .criteriaInfo(new HashMap<>())
                    .coupons(couponsDTOs)
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .deductions(deduction)
                    .finalProducts(finalProductDTOsTaxesInfo)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.00).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .thirdParty(thirdPartyDTO)
                    .uuid(UUID.randomUUID())
                    .build()
    );

    public final List<OrderDTO> orderDTOsWithOutServices = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .couponCriteriaInfo(new HashMap<>())
                    .criteriaInfo(new HashMap<>())
                    .coupons(couponsDTOs)
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .deductions(deduction)
                    .finalProducts(finalProductDTOsTaxesInfo)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.00).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .build()
    );

    public final List<OrderDTO> orderDTOWithoutServices = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .couponCriteriaInfo(new HashMap<>())
                    .criteriaInfo(new HashMap<>())
                    .coupons(couponsDTOs)
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .deductions(deduction)
                    .finalProducts(finalProductDTOsTaxesInfo)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.emptyList())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.00).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .build()
    );

    public final List<OrderDTO> orderDTOsServices = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .couponCriteriaInfo(new HashMap<>())
                    .criteriaInfo(new HashMap<>())
                    .coupons(couponsDTOs)
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .deductions(deduction)
                    .finalProducts(finalProductDTOsTaxesInfo)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.00).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .build()
    );

    public final List<OrderDTO> orderDTOsWithTwoProducts = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductDTOsDifferentBrand)
                    .flags(flagDTO)
                    .isMultiBrand(Boolean.FALSE)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.00).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .build()
    );

    public final List<OrderDTO> orderDTOsWithOtherTotal = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .notes("notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(10000.0))
                    .build()
    );

    public final List<OrderDTO> orderDTOsWithDiscount = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>(Collections.singletonList(
                            DiscountDTO.builder()
                                    .id(1L)
                                    .value(BigDecimal.valueOf(4900.0))
                                    .build()
                    )))
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );
    public List<OrderDTO> ordersDTOS = Arrays.asList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .id(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(Arrays.asList(
                            DiscountDTO.builder()
                                    .id(1L)
                                    .value(BigDecimal.valueOf(3000.0))
                                    .build(),
                            DiscountDTO.builder()
                                    .id(10L)
                                    .value(BigDecimal.valueOf(3000.0))
                                    .build()
                    ))
                    .discountsApplied(Collections.singletonList(
                            new OrderDiscountResponseDTO(
                                    1L,
                                    1L,
                                    1L,
                                    BigDecimal.valueOf(2000.0)
                            )
                    ))
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .isMultiBrand(Boolean.FALSE)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
            ,
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .id(2L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(Arrays.asList(
                            DiscountDTO.builder()
                                    .id(1L)
                                    .value(BigDecimal.valueOf(3000.0))
                                    .build(),
                            DiscountDTO.builder()
                                    .id(10L)
                                    .value(BigDecimal.valueOf(3000.0))
                                    .build()
                    ))
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .notes("Notes2")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );

    public final List<OrderDTO> orderDTOsWithConsumptionAndOtherDiscount = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(Arrays.asList(
                            DiscountDTO.builder()
                                    .id(1L)
                                    .value(BigDecimal.valueOf(3000.0))
                                    .build(),
                            DiscountDTO.builder()
                                    .id(10L)
                                    .value(BigDecimal.valueOf(3000.0))
                                    .build()
                    ))
                    .finalProducts(finalProductDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );
    public final List<OrderDTO> orderDTOsWithConsumptionAndOtherDiscountTaxDistributedProductDiscount = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .finalProducts(finalProductDTOsTax)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );

    public final List<OrderDTO> orderWithAdditionDTOs = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .coupons(couponsDTOs)
                    .couponCriteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .deductions(deduction)
                    .finalProducts(finalProductWithAdditionDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(18900.0000).setScale(
                            NUMBER_DECIMAL_PLACES,
                            RoundingMode.UNNECESSARY)
                    )
                    .build()
    );

    public final List<OrderDTO> orderCo2DTO = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .finalProducts(finalProductDTOCo2)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final NewUserDTO userDTO = new NewUserDTO(
            1L,
            "User",
            "Test",
            "57",
            "9999999999",
            "test@test.com"
    );

    private final List<PaymentMethodDTO> paymentMethodDTOS = Arrays.asList(
            new PaymentMethodDTO(
                    1L,
                    1L,
                    null,
                    BigDecimal.valueOf(2000.0)
            ),
            new PaymentMethodDTO(
                    foodcoinsPaymentMethodId,
                    1L,
                    null,
                    BigDecimal.valueOf(4000.0)
            )
    );


    private final List<PaymentMethodDTO> paymentMethodDTOSPaid = Collections.singletonList(
            new PaymentMethodDTO(
                    7L,
                    1L,
                    null,
                    BigDecimal.valueOf(2000.0)
            )
    );

    public final PaymentMethodDetailDTO paymentMethodDetailDTO = PaymentMethodDetailDTO.builder()
            .paymentGetawayId(1L)
            .creditCard(4L)
            .installmentsNumber(3L)
            .build();

    public final List<PaymentMethodDTO> paymentMethodsPaid = List.of(
            new PaymentMethodDTO(
                    1L,
                    1L,
                    null,
                    BigDecimal.valueOf(7900.0)
            ),
            new PaymentMethodDTO(
                    6L,
                    1L,
                    paymentMethodDetailDTO,
                    BigDecimal.valueOf(8000.0)
            )
    );

    public final TransactionRequestDTO transactionRequestDTOWithDeductions = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOs)
            .deductions(deductionsDTO)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithDeductionsTotalOrder = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOs)
            .deductions(deductionsDTOTotal)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .channel(ChannelMock.build())
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsTaxes)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0))
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .uuid(UUID.randomUUID())
            .storeInfo(StoreInformation
                    .builder()
                    .postalCode("11011")
                    .city(City
                            .builder()
                            .code("11011")
                            .name("Bogota")
                            .build())
                    .build())
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithOutServices = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsWithOutServices)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0))
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithServices = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsServices)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0))
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestWithoutServicesDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOWithoutServices)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0))
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithCouponsWithOutServices = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsWithOutServices)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7800.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7800.0))
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();


    public final TransactionRequestDTO transactionRequestDTOWithoutOrders = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(34L)
            .orders(null)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithNoMatchingPayments = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(1000.0)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithPricePaymentsError = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(6900.0)
                            )
                    )
            )
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithOtherTotal = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(new ArrayList<>())
            .total(BigDecimal.valueOf(10000.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithTotalOrderOther = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .orders(orderDTOsWithOtherTotal)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(new ArrayList<>())
            .total(BigDecimal.valueOf(10000.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO paidTransactionRequestDTO = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(1L)
            .id(1L)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(paymentMethodDTOS)
            .paymentsPaid(paymentMethodDTOSPaid)
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .uuid(UUID.randomUUID())
            .build();


    public final TransactionRequestDTO transactionRequestWithDiscounts = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .orders(orderDTOsWithDiscount)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(Collections.singletonList(PaymentMethodDTO.builder()
                    .value(BigDecimal.valueOf(7900.0))
                    .build()))
            .total(BigDecimal.valueOf(12900.0000))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();


    public final TransactionRequestDTO transactionRequestWithDiscountsAndNoPaidAmount = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .orders(orderDTOsWithDiscount)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(Collections.singletonList(PaymentMethodDTO.builder()
                    .value(BigDecimal.valueOf(7900.0))
                    .build()))
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestWithConsumptionAndNormalDiscounts = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .id(null)
            .orders(orderDTOsWithConsumptionAndOtherDiscount)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(new ArrayList<>())
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithDiscountTaxes = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsWithConsumptionAndOtherDiscountTaxDistributedProductDiscount)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.0)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithPaymentsPaid = TransactionRequestDTO.builder()
            .company(companyDTO)
            .device(deviceDTO)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(6900.0)
                            )
                    )
            )
            .paymentsPaid(Collections.singletonList(
                    new PaymentMethodDTO(
                            7L,
                            4L,
                            null,
                            BigDecimal.valueOf(1000.0)

                    )))
            .total(BigDecimal.valueOf(6900.0))
            .totalPaidPayments(BigDecimal.valueOf(1000.0))
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestWithAdditionDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderWithAdditionDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(18800.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(18800.0))
            .total(BigDecimal.valueOf(18800.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestCo2DTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .co2Total(BigDecimal.valueOf(0L))
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderCo2DTO)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0))
            .total(BigDecimal.valueOf(7900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    /***
     * Mocks for Coupons
     */
    private final List<FinalProductDTO> finalProductValidateRangeDTOs = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    public final List<OrderDTO> orderValidateRangeDTOs = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductValidateRangeDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );

    public final List<OrderDTO> orderValidateRangeDTOsWithOutService = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductValidateRangeDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );

    private final List<FinalProductDTO> finalProductSecondsOptionDTOs = Arrays.asList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(finalProductDiscountDTOS)
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build(),
            FinalProductDTO.builder()
                    .article(new FinalProductArticleDTO(
                            2L,
                            1L,
                            1L,
                            "ARTICLE2"
                    ))
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(8000.0))
                    .quantity(2)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(16000.0))
                    .build()

    );

    public final List<OrderDTO> orderDTOsVariousProducts = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductSecondsOptionDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(23900))
                    .build()
    );

    public final List<OrderDTO> orderDTOsVariousProductsWitOutService = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductSecondsOptionDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(23900))
                    .build()
    );

    public final TransactionRequestDTO transactionRequestWithCouponsDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7800.0)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(4900.0))
            .total(BigDecimal.valueOf(4800.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOWithVariousProducts = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsVariousProductsWitOutService)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(20800.0)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(20900.0))
            .total(BigDecimal.valueOf(23900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();


    public final TransactionRequestDTO paidTransactionRequestWithCouponsDTO = TransactionRequestDTO.builder()
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .flowId(1L)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(true)
            .paymentMethods(paymentMethodDTOS)
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestForCouponsNoPaidDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOs)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7800.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0))
            .total(BigDecimal.valueOf(7800.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();


    private final List<FinalProductDTO> finalProductSecondsOptionDiferenceDTOs = Arrays.asList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(8000.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(8000.0))
                    .build(),
            FinalProductDTO.builder()
                    .article(new FinalProductArticleDTO(
                            2L,
                            1L,
                            1L,
                            "ARTICLE2"
                    ))
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(8000.0))
                    .quantity(2)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(16000.0))
                    .build()

    );

    private final List<FinalProductDTO> finalProductSecondsOptionDiferenceDTOsRange = Arrays.asList(
            FinalProductDTO.builder()
                    .article(new FinalProductArticleDTO(
                            2L,
                            1L,
                            1L,
                            "ARTICLE2"
                    ))
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(3333.3332))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(3333.3332))
                    .build(),
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(3333.3333))
                    .quantity(2)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(3333.3333))
                    .build()

    );

    private final List<FinalProductDTO> finalProductSecondsOptionDiferenceDTOsNotExceeds = Arrays.asList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(6666.6667))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(6666.6667))
                    .build(),
            FinalProductDTO.builder()
                    .article(new FinalProductArticleDTO(
                            2L,
                            1L,
                            1L,
                            "ARTICLE2"
                    ))
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(3333.3333))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(3333.3333))
                    .build()
    );

    public final List<OrderDTO> orderDTOsRequesDiferrenceDTO = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductSecondsOptionDiferenceDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(24000))
                    .build()
    );

    public final List<OrderDTO> orderDTOsRequesDiferrenceDTOWithOutService = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductSecondsOptionDiferenceDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(24000))
                    .build()
    );

    public final List<OrderDTO> orderDTOsRequesDiferrenceDTOWithOutServiceRange = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductSecondsOptionDiferenceDTOsRange)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(10000))
                    .build()
    );

    public final List<OrderDTO> orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>())
                    .discountsApplied(new ArrayList<>())
                    .finalProducts(finalProductSecondsOptionDiferenceDTOsNotExceeds)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(new ArrayList<>())
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(10000))
                    .build()
    );


    public final TransactionRequestDTO transactionRequesDiferrenceDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsRequesDiferrenceDTO)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(23900.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(24000.0))
            .total(BigDecimal.valueOf(23900.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequesDiferrenceDTORange = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(List.of(new CouponDTO("eo", BigDecimal.valueOf(2000))))
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsRequesDiferrenceDTOWithOutServiceRange)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(10000.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(8000.00))
            .total(BigDecimal.valueOf(10000.0).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequesDiferrenceDTONotExceeds = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(List.of(new CouponDTO("eo", BigDecimal.valueOf(2000))))
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsRequesDiferrenceDTOWithOutServiceNotExceeds)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(10000.00)
                                            .setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(8000.00))
            .total(BigDecimal.valueOf(8000.00).setScale(NUMBER_DECIMAL_PLACES, RoundingMode.UNNECESSARY))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    private final List<FinalProductDTO> finalProductValidateDTOs = Collections.singletonList(
            FinalProductDTO.builder()
                    .article(finalProductArticleDTO)
                    .brand(finalProductBrandDTO)
                    .discounts(new ArrayList<>())
                    .groups(groupDTOs)
                    .id(1L)
                    .image("image.png")
                    .name("Product")
                    .price(BigDecimal.valueOf(7900.0))
                    .quantity(1)
                    .removedPortions(new ArrayList<>())
                    .size(sizeDTO)
                    .sku("sku")
                    .taxes(new ArrayList<>())
                    .totalPrice(BigDecimal.valueOf(7900.0))
                    .build()
    );

    public final List<OrderDTO> orderDTOsWithValidateDiscount = Collections.singletonList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .brand(brandDTO)
                    .criteriaInfo(new HashMap<>())
                    .deliveryTypeId(1L)
                    .discounts(new ArrayList<>(Collections.singletonList(
                            DiscountDTO.builder()
                                    .id(1L)
                                    .value(BigDecimal.valueOf(4900.0))
                                    .build()
                    )))
                    .finalProducts(finalProductValidateDTOs)
                    .flags(flagDTO)
                    .notes("Notes")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
                    .build()
    );

    public final TransactionRequestDTO transactionRequestValidateRangeDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderValidateRangeDTOs)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7800)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0090))
            .total(BigDecimal.valueOf(7800.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestValidateDiscountDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponsDTOs)
            .device(deviceDTO)
            .flowId(34L)
            .orders(orderDTOsWithValidateDiscount)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(3000)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .subtotalOnlyWithDiscount(BigDecimal.valueOf(7900.0000))
            .total(BigDecimal.valueOf(3000.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOMultipleOrders = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .orderCreatedAt("2020-01-01")
            .id(1L)
            .flowId(34L)
            .orders(ordersDTOS)
            .origin(originDTO)
            .paid(false)
            .paymentMethods(
                    Collections.singletonList(
                            new PaymentMethodDTO(
                                    1L,
                                    1L,
                                    null,
                                    BigDecimal.valueOf(7900.0)
                            )
                    )
            )
            .pickupTimeConsumption(Boolean.TRUE)
            .total(BigDecimal.valueOf(7900.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();
}
