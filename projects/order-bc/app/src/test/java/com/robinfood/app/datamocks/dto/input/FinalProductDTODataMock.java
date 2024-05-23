package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductArticleDTO;
import com.robinfood.core.dtos.request.order.FinalProductCategoryDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductDiscountDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO;
import com.robinfood.core.dtos.request.order.FinalProductSizeDTO;
import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Data
public class FinalProductDTODataMock {

    private FinalProductArticleDTO finalProductArticleDTO = new FinalProductArticleDTODataMock().getDataDefault();
    private BrandDTO brandDTO = new BrandDTODataMock().getDataDefault();
    private FinalProductCategoryDTO finalProductCategoryDTO = new FinalProductCategoryDTODataMock().getDataDefault();
    private List<FinalProductDiscountDTO> finalProductDiscountDTOList = new FinalProductDiscountDTODataMock().getDataDefaultList();
    private List<FinalProductPortionDTO> portionDTOList = new FinalProductPortionDTODataMock().getDataDefaultList();
    private List<FinalProductPortionDTO> portionReplacementDTOList = new FinalProductPortionDTODataMock().getDataDefaultReplacementPortionList();
    private List<FinalProductRemovedPortionDTO> removedPortionDTOList = new RemovedPortionDTODataMock().getDataDefaultList();
    private FinalProductSizeDTO finalProductSizeDTO = new FinalProductSizeDTODataMock().getDataDefault();
    private List<FinalProductTaxDTO> finalProductTaxDTOList = new FinalProductTaxDTODataMock().getDataDefaultList();

    public FinalProductDTO getDataDefault(@Nullable Long orderId) {
        return new FinalProductDTO(
                finalProductArticleDTO,
                brandDTO,
                finalProductCategoryDTO,
                List.of(new DeductionDTO(
                        1L,
                        BigDecimal.valueOf(2000)
                )),
                1L,
                finalProductDiscountDTOList,
                0.0,
                1L,
                orderId,
                "default-muy.png",
                "MUY Cubano",
                portionDTOList,
                1,
                removedPortionDTOList,
                finalProductSizeDTO,
                "sku",
                1L,
                finalProductTaxDTOList,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
    }
    public FinalProductDTO getDataDefaultBrasil(@Nullable Long orderId) {
        return new FinalProductDTO(
                finalProductArticleDTO,
                brandDTO,
                finalProductCategoryDTO,
                List.of(new DeductionDTO(
                        1L,
                        BigDecimal.valueOf(2000)
                )),
                5L,
                finalProductDiscountDTOList,
                0.0,
                1L,
                orderId,
                "default-muy.png",
                "MUY Cubano",
                portionDTOList,
                1,
                removedPortionDTOList,
                finalProductSizeDTO,
                "sku",
                1L,
                finalProductTaxDTOList,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
    }
    public FinalProductDTO getDataDefaultWithoutDeductions(@Nullable Long orderId) {
        return new FinalProductDTO(
                finalProductArticleDTO,
                brandDTO,
                finalProductCategoryDTO,
              null,
                1L,
                finalProductDiscountDTOList,
                0.0,
                1L,
                orderId,
                "default-muy.png",
                "MUY Cubano",
                portionDTOList,
                1,
                removedPortionDTOList,
                finalProductSizeDTO,
                "sku",
                1L,
                finalProductTaxDTOList,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
    }

    public FinalProductDTO getDataReplacementPortion(@Nullable Long orderId) {
        return new FinalProductDTO(
                finalProductArticleDTO,
                brandDTO,
                finalProductCategoryDTO,
                List.of(new DeductionDTO(
                    1L,
                        BigDecimal.valueOf(2000)
                )),
                1L,
                finalProductDiscountDTOList,
                0.0,
                1L,
                orderId,
                "default-muy.png",
                "Cubano",
                portionReplacementDTOList,
                1,
                removedPortionDTOList,
                finalProductSizeDTO,
                "sku",
                1L,
                finalProductTaxDTOList,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        );
    }

    private FinalProductArticleDTO article;

    private BrandDTO brand;

    private FinalProductCategoryDTO category;

    @Nullable
    private Long companyId;

    private List<FinalProductDiscountDTO> discounts;

    @Nullable
    private Double discountPrice;

    private Long id;

    @Nullable
    private Long orderId;

    private String image;

    private String name;

    private List<FinalProductPortionDTO> portions;

    private Integer quantity;

    private List<FinalProductRemovedPortionDTO> removedPortions;

    private FinalProductSizeDTO size;

    @Nullable
    private Long storeId;

    private List<FinalProductTaxDTO> taxes;

    private Double value;

    public List<FinalProductDTO> getDataDefaultList(@Nullable Long orderId) {
        return Arrays.asList(getDataDefault(orderId));
    }

    public List<FinalProductDTO> getDataDefaultListBrasil(@Nullable Long orderId) {
        return Arrays.asList(getDataDefaultBrasil(orderId));
    }

    public List<FinalProductDTO> getDataDefaultListWithOutDeductions(@Nullable Long orderId) {
        return Arrays.asList(getDataDefaultWithoutDeductions(orderId));
    }

    public List<FinalProductDTO> getDataWithReplacementPortionList(@Nullable Long orderId) {
        return Arrays.asList(getDataReplacementPortion(orderId));
    }

    public List<FinalProductDTO> getDataForCalculate(@Nullable Long orderId){

        FinalProductDTO finalProductDTOOne = getDataDefault(orderId);
        finalProductDTOOne.setValue(new BigDecimal("8900.0"));

        FinalProductDTO finalProductDTOTwo = getDataDefault(orderId);
        finalProductDTOTwo.setValue(new BigDecimal("8900.0"));

        FinalProductPortionDTO portionDTOFree = new FinalProductPortionDTODataMock().getDataDefault();

        FinalProductPortionDTO portionDTONotFreeOne = new FinalProductPortionDTODataMock().getDataDefault();
        portionDTONotFreeOne.setFree(1);
        portionDTONotFreeOne.setPrice(2000.0);

        FinalProductPortionDTO portionDTONotFreeTwo = new FinalProductPortionDTODataMock().getDataDefault();
        portionDTONotFreeTwo.setFree(1);
        portionDTONotFreeTwo.setPrice(1000.0);

        FinalProductPortionDTO portionDTONotFreeThree = new FinalProductPortionDTODataMock().getDataDefault();
        portionDTONotFreeThree.setFree(1);
        portionDTONotFreeThree.setPrice(3000.0);

        FinalProductPortionDTO portionDTONotFreeFour = new FinalProductPortionDTODataMock().getDataDefault();
        portionDTONotFreeFour.setFree(1);
        portionDTONotFreeFour.setPrice(2500.0);

        finalProductDTOOne.setPortions(Arrays.asList(
                portionDTOFree,
                portionDTONotFreeOne,
                portionDTOFree,
                portionDTONotFreeTwo
        ));

        finalProductDTOTwo.setPortions(Arrays.asList(
                portionDTOFree,
                portionDTONotFreeThree,
                portionDTOFree,
                portionDTONotFreeFour
        ));

        return Arrays.asList(finalProductDTOOne, finalProductDTOTwo);
    }

    public FinalProductDTO getDefaultWithFreePortions()
    {
        final FinalProductDTO finalProductDTO = getDataDefault(1L);
        finalProductDTO.setPortions(new FinalProductPortionDTODataMock().getDataDefaultFreeList());
        return finalProductDTO;
    }
}
