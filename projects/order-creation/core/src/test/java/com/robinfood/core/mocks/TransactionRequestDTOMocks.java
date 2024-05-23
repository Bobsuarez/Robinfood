package com.robinfood.core.mocks;

import com.robinfood.core.dtos.transactionrequestdto.*;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TransactionRequestDTOMocks {

    @Value("${payment.method-foodcoins-id}")
    private Long foodcoinsPaymentMethodId;

    LocalDate date1 = LocalDate.of(2017, 1, 13);
    LocalTime time = LocalTime.of(10,43,12);

    private final CompanyDTO companyDTO = new CompanyDTO(
        "COP",
        1L
    );

    private final DeliveryDTO deliveryDTO = new DeliveryDTO(
            "direccion",
            "descripcion",
            date1,
            time,
            "code",
            "1",
            "notes",
            1,
            "payment",
            BigDecimal.valueOf(500.0),
            BigDecimal.valueOf(500.0),
            BigDecimal.valueOf(500.0),
            "type",
            "User Name"
            );

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

    private final List<FinalProductDiscountDTO> finalProductDiscountDTOS = new ArrayList<>(
        Collections.singletonList(
            FinalProductDiscountDTO.builder()
                    .value(BigDecimal.valueOf(3000.0))
                    .build()
        )
    );

    private final List<FinalProductDTO> finalProductDTOs = Collections.singletonList(
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
            .build()
    );

    private final List<CouponDTO> couponDTOS = Collections.singletonList(
            new CouponDTO(
                    "CUPONOK",
                    new BigDecimal(100.0)
            )
    );

    public final List<OrderDTO> orderDTOs = Collections.singletonList(
        OrderDTO.builder()
            .billingResolutionId(1L)
            .brand(brandDTO)
                .id(1l)
            .criteriaInfo(new HashMap<>())
            .deliveryTypeId(1L)
            .discounts(new ArrayList<>())
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

    public List<OrderDTO> ordersDTOS = Arrays.asList(
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .id(1l)
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
            ,
            OrderDTO.builder()
                    .billingResolutionId(1L)
                    .id(2l)
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
                    .notes("Notes2")
                    .origin(originDTO)
                    .paymentModelId(1L)
                    .services(Collections.singletonList(serviceDTO))
                    .store(storeDTO)
                    .taxCriteria(new HashMap<>())
                    .total(BigDecimal.valueOf(7900.0))
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

    private final PaymentMethodDetailDTO paymentMethodDetailDTO = PaymentMethodDetailDTO.builder()
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

    public final TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(couponDTOS)
            .device(deviceDTO)
            .delivery(deliveryDTO)
            .orderCreatedAt("2020-01-01")
            .id(1l)
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
            .total(BigDecimal.valueOf(7800.0))
            .totalPaidPayments(BigDecimal.ZERO)
            .user(userDTO)
            .build();

    public final TransactionRequestDTO transactionRequestDTOMultipleOrders = TransactionRequestDTO.builder()
            .updateOrder(false)
            .company(companyDTO)
            .coupons(new ArrayList<>())
            .device(deviceDTO)
            .delivery(deliveryDTO)
            .orderCreatedAt("2020-01-01")
            .id(1l)
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

    public final TransactionRequestDTO transactionRequestDTOWithPricePaymentsError = TransactionRequestDTO.builder()
        .company(companyDTO)
        .coupons(new ArrayList<>())
        .device(deviceDTO)
        .orders(orderDTOs)
        .origin(originDTO)
            .id(1l)
        .paid(false)
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
            .orderCreatedAt("2020-01-01")
        .build();

    public final TransactionRequestDTO transactionRequestDTOWithOtherTotal = TransactionRequestDTO.builder()
        .company(companyDTO)
        .coupons(new ArrayList<>())
        .device(deviceDTO)
        .orders(orderDTOs)
        .origin(originDTO)
        .paid(false)
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
        .paid(false)
        .paymentMethods(new ArrayList<>())
        .total(BigDecimal.valueOf(10000.0))
        .totalPaidPayments(BigDecimal.ZERO)
        .user(userDTO)
        .build();

    public final TransactionRequestDTO paidTransactionRequestDTO = TransactionRequestDTO.builder()
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


    public final TransactionRequestDTO transactionRequestWithDiscounts = TransactionRequestDTO.builder()
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
}
