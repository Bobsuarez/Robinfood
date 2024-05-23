package com.robinfood.app.usecases.completereplacementportionsdata;

import com.robinfood.app.usecases.getsuggestedportions.IGetSuggestedPortionsUseCase;
import com.robinfood.core.dtos.OrderDetailChangedPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionDataDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.enums.Result;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implementation of ICompleteReplacementPortionsDataUseCase
 */
@Slf4j
public class CompleteReplacementPortionsDataUseCase implements ICompleteReplacementPortionsDataUseCase {

    private final IGetSuggestedPortionsUseCase getSuggestedPortionsUseCase;

    public CompleteReplacementPortionsDataUseCase(IGetSuggestedPortionsUseCase getSuggestedPortionsUseCase) {
        this.getSuggestedPortionsUseCase = getSuggestedPortionsUseCase;
    }

    @Override
    public void invoke(List<OrderDetailProductGroupPortionDTO> portions, String token) {

        log.info("Starting process to complete Portions Data with portion [{}]", portions);

        final List<Long> portionIds = portions
                .stream()
                .map(OrderDetailProductGroupPortionDTO::getId).collect(Collectors.toList());

        Result<List<MenuSuggestedPortionResponseDTO>> suggestedPortionsResult =
                getSuggestedPortionsUseCase.invoke(portionIds, token);

        if (suggestedPortionsResult instanceof Result.Error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Suggested portions not found for portions with ids " + portionIds
            );
        }
        List<MenuSuggestedPortionResponseDTO> suggestedPortions =
                ((Result.Success<List<MenuSuggestedPortionResponseDTO>>) suggestedPortionsResult)
                        .getData();

        portions.forEach((OrderDetailProductGroupPortionDTO orderPortion) ->
                replacePortionData(suggestedPortions, orderPortion)
        );
    }

    private void replacePortionData(List<MenuSuggestedPortionResponseDTO> suggestedPortions,
            OrderDetailProductGroupPortionDTO orderPortion) {
        final Long changedPortionId = orderPortion.getChangedPortion().getId();

        final MenuSuggestedPortionResponseDTO portionResponse = suggestedPortions
                .stream()
                .filter(menuSuggestedPortion -> orderPortion.getId().equals(menuSuggestedPortion.getId()))
                .findAny().orElse(null);

        if (portionResponse != null) {
            MenuSuggestedPortionDataDTO suggestedPortionFound = portionResponse.getChanges()
                    .stream()
                    .filter(menuSuggestedPortion -> menuSuggestedPortion.getId().equals(changedPortionId))
                    .findAny().orElse(null);

            if (suggestedPortionFound == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Suggested portion incoming in order detail not found in menu"
                );
            }
            setReplacementPortionData(orderPortion, suggestedPortionFound);
        }
    }

    private void setReplacementPortionData(
            OrderDetailProductGroupPortionDTO originalPortion,
            MenuSuggestedPortionDataDTO changedPortion
    ) {
        originalPortion.setChangedPortion(
                new OrderDetailChangedPortionDTO(
                        changedPortion.getId(),
                        changedPortion.getName(),
                        changedPortion.getParentId(),
                        changedPortion.getSku(),
                        changedPortion.getUnitId(),
                        changedPortion.getUnitNumber()
                )
        );
    }
}
