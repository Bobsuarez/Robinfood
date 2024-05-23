package com.robinfood.usecases.findallresolutions;

import com.robinfood.dtos.v1.request.SearchResolutionsDTO;
import com.robinfood.dtos.v1.response.searchResolutions.DataResolutionResponseDTO;
import com.robinfood.dtos.v1.response.searchResolutions.PageableDTO;
import com.robinfood.dtos.v1.response.searchResolutions.ResolutionResponseDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import com.robinfood.repository.posresolution.PosResolutionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class FindAllResolutionsUseCase implements IFindAllResolutionsUseCase{

    private final IPosResolutionRepository posResolutionRepository;

    private static FindAllResolutionsUseCase instance;

    public FindAllResolutionsUseCase() {
        posResolutionRepository = PosResolutionRepository.getInstance();
    }

    public static FindAllResolutionsUseCase getInstance() {
        if (Objects.isNull(instance)) {
            instance = new FindAllResolutionsUseCase();
        }
        return instance;
    }

    @Override
    public DataResolutionResponseDTO invoke(SearchResolutionsDTO searchResolutions) {
        log.info("data = {}", searchResolutions);

        Integer total = posResolutionRepository
                .countSearchResolutions(
                        searchResolutions.getResolutionId(),
                        searchResolutions.getStatus(),
                        searchResolutions.getValueCustomFilter(),
                        searchResolutions.getWithPos()
                );

        List<PosResolutionEntity> listPosResolutionEntity =
                posResolutionRepository.searchResolutions(
                        searchResolutions.getStatus(),
                        searchResolutions.getOrderByEnum(),
                        searchResolutions.getPage(),
                        searchResolutions.getResolutionId(),
                        searchResolutions.getSize(),
                        searchResolutions.getValueCustomFilter(),
                        searchResolutions.getWithPos()
                );

        return DataResolutionResponseDTO.builder()
                .content(getResolutionResponseDTO(listPosResolutionEntity))
                .pageable(PageableDTO.builder()
                        .pageNumber(searchResolutions.getPage())
                        .pageSize(searchResolutions.getSize())
                        .total(total)
                        .build())
                .build();
    }

    private static List<ResolutionResponseDTO> getResolutionResponseDTO(
            List<PosResolutionEntity> listPosResolutionEntity
    ){
        return listPosResolutionEntity.stream()
                .map(FindAllResolutionsUseCase::mapPosResolutionEntityToDTO)
                .collect(Collectors.toList());
    }

    private static ResolutionResponseDTO mapPosResolutionEntityToDTO(PosResolutionEntity entity) {
        return ResolutionResponseDTO.builder()
                .current(Objects.equals(entity.getCurrent(), 1))
                .endDate(convertDateToString(entity.getEnd_date()))
                .id(entity.getId())
                .initialDate(convertDateToString(entity.getInitial_date()))
                .invoiceNumberEnd(entity.getInvoice_number_end())
                .invoiceNumberInitial(entity.getInvoice_number_initial())
                .invoiceNumberResolution(entity.getInvoice_number_resolutions())
                .name(entity.getName())
                .linkedToPos(entity.getPos_id()!=null)
                .prefix(entity.getPrefix())
                .resolutionId(entity.getResolution_id())
                .typeDocument(entity.getType_document())
                .build();
    }

    private static String convertDateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

}
