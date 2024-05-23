package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class OrdersItem {
    private Object consumptionValue;
    private String notes;
    private Origin origin;
    private boolean hasConsumption;
    private List<Object> services;
    private Store store;
    private String uuid;
    private int deliveryTypeId;
    private double total;
    private int billingResolutionId;
    private List<Object> discounts;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ThirdParty thirdParty;

    private List<Object> coupons;
    private List<FinalProductsItem> finalProducts;
    private int paymentModelId;
    private TaxCriteria taxCriteria;
    private Object id;
    private Brand brand;
    private CouponCriteriaInfo couponCriteriaInfo;
}