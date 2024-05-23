package com.robinfood.ordereports_bc_muyapp.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ResponseFinalProductDTO {

    private BigDecimal basePrice;

    private Short brandId;

    private BigDecimal co2Total;

    private BigDecimal discount;

    public List<ResponseOrderDiscountDTO> discounts;

    private List<ResponseFinalProductGroupDTO> groups;

    private Long id;

    private String image;

    private String name;

    private BigDecimal price;

    private Double productPrice;

    private Short quantity;

    private int sizeId;

    private String sizeName;

    private Double totalPrice;

    private List<ResponseTaxesDTO> taxes;

}

//public int brandId;
//public int co2Total;
//public ArrayList<Group> groups;
//public int id;
//public String image;
//public String name;
//public int price;
//public int quantity;
//public int basePrice;
//public int totalPrice;
//public int productPrice;
//public int discount;
//public ArrayList<Discount> discounts;
//public int sizeId;
//public String sizeName;
//public ArrayList<Taxis> taxes;
