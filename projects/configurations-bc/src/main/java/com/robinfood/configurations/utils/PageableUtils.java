package com.robinfood.configurations.utils;

import com.robinfood.configurations.dto.v1.StoreDTO;
import com.robinfood.configurations.dto.v1.models.BrandDTO;
import com.robinfood.configurations.dto.v1.pageable.PageResponseDTO;
import com.robinfood.configurations.dto.v1.pageable.PageableDTO;
import com.robinfood.configurations.dto.v1.pageable.SortDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.UNSORTED;

public final class PageableUtils {

    private PageableUtils() {
    }

    public static <T> PageResponseDTO<T> createPageResponse(Page<T> objectPage,
                                                            List<T> sortedList, Sort sort) {

        return PageResponseDTO.<T>builder()
                .content(sortedList)
                .pageable(PageableDTO.builder()
                        .sort(SortDTO.builder()
                                .sorted(!sort.toString().equals(UNSORTED))
                                .unsorted(sort.toString().equals(UNSORTED))
                                .empty(objectPage.getPageable().getSort().isEmpty())
                                .build())
                        .offset(objectPage.getPageable().getOffset())
                        .pageNumber(objectPage.getPageable().getPageNumber())
                        .paged(objectPage.getPageable().isPaged())
                        .unpaged(objectPage.getPageable().isUnpaged())
                        .build())
                .totalPages(objectPage.getTotalPages())
                .totalElements(objectPage.getTotalElements())
                .last(objectPage.isLast())
                .numberOfElements(objectPage.getNumberOfElements())
                .number(objectPage.getNumber())
                .size(objectPage.getSize())
                .sort(SortDTO.builder()
                        .sorted(!sort.toString().equals(UNSORTED))
                        .unsorted(sort.toString().equals(UNSORTED))
                        .empty(objectPage.getSort().isEmpty())
                        .build())
                .first(objectPage.isFirst())
                .empty(objectPage.isEmpty())
                .build();
    }

    public static PageResponseDTO<StoreDTO> createEmptyPageResponse(Sort sort) {
        return PageResponseDTO.<StoreDTO>builder()
                .content(Collections.emptyList())
                .pageable(PageableDTO.builder()
                        .sort(SortDTO.builder()
                                .sorted(!sort.toString().equals(UNSORTED))
                                .unsorted(sort.toString().equals(UNSORTED))
                                .empty(true)
                                .build())
                        .offset(0)
                        .pageNumber(0)
                        .paged(false)
                        .unpaged(true)
                        .build())
                .totalPages(0)
                .totalElements(0)
                .last(false)
                .numberOfElements(0)
                .number(0)
                .size(1)
                .sort(SortDTO.builder()
                        .sorted(false)
                        .unsorted(true)
                        .empty(true)
                        .build())
                .first(false)
                .empty(true)
                .build();
    }

    public static PageResponseDTO<BrandDTO> createEmptyPageBrandResponse(Sort sort) {
        return PageResponseDTO.<BrandDTO>builder()
                .content(Collections.emptyList())
                .pageable(PageableDTO.builder()
                        .sort(SortDTO.builder()
                                .sorted(!sort.toString().equals(UNSORTED))
                                .unsorted(sort.toString().equals(UNSORTED))
                                .empty(true)
                                .build())
                        .offset(0)
                        .pageNumber(0)
                        .paged(false)
                        .unpaged(true)
                        .build())
                .totalPages(0)
                .totalElements(0)
                .last(false)
                .numberOfElements(0)
                .number(0)
                .size(1)
                .sort(SortDTO.builder()
                        .sorted(false)
                        .unsorted(true)
                        .empty(true)
                        .build())
                .first(false)
                .empty(true)
                .build();
    }

    public static void sortStores(List<StoreDTO> storeDTOList, String sort) {

        switch (sort) {
            case "name: ASC":
                storeDTOList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
                break;
            case "name: DESC":
                storeDTOList.sort((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName()));
                break;
            case "id: DESC":
                storeDTOList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                break;
            default:
                break;
        }
    }

    public static void sortBrands(List<BrandDTO> brandDTOList, String sort) {

        switch (sort) {
            case "name: ASC":
                brandDTOList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
                break;
            case "name: DESC":
                brandDTOList.sort((o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName()));
                break;
            case "id: DESC":
                brandDTOList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
                break;
            default:
                break;
        }
    }

    public static Pageable createPage(Integer page, Integer size, Sort sort) {

        if (sort.toString().startsWith("name")) {
            return PageRequest.of(page, size, sort);
        }

        return PageRequest.of(page, size);
    }
}
