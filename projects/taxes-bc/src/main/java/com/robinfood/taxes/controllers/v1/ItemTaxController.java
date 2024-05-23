package com.robinfood.taxes.controllers.v1;

import static com.robinfood.taxes.constants.PermissionsConstants.LIST_ITEM_TAX;
import static com.robinfood.taxes.constants.PermissionsConstants.SERVICE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.controllers.v1.docs.ItemTaxControllerDocs;
import com.robinfood.taxes.domain.Item;
import com.robinfood.taxes.domain.TaxableItem;
import com.robinfood.taxes.dto.v1.ApiResponseDTO;
import com.robinfood.taxes.dto.v1.TaxableItemDTO;
import com.robinfood.taxes.dto.v1.ItemListDTO;
import com.robinfood.taxes.exceptions.BusinessRuleException;
import com.robinfood.taxes.facades.TaxableItemFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/items")
public class ItemTaxController implements ItemTaxControllerDocs {

    private final TaxableItemFacade listTaxesFacade;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;

    public ItemTaxController(

        TaxableItemFacade listTaxesFacade, ObjectMapper objectMapper,
        ModelMapper modelMapper) {
        this.listTaxesFacade = listTaxesFacade;

        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
    }

    @BasicLog
    @Override
    @PostMapping(value = "/taxes", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + SERVICE + "') || hasRole('" + LIST_ITEM_TAX + "')")
    public ResponseEntity<ApiResponseDTO<List<TaxableItemDTO>>> list(
        @Valid @RequestBody ItemListDTO itemTaxList)
        throws BusinessRuleException, JsonProcessingException {

        log.trace("Request to list item tax received on controller with body: {}", itemTaxList);

        log.trace("Generating Item object from DTO {}", itemTaxList.getItems());
        List<Item> items = itemTaxList.getItems().stream()
            .map(item -> modelMapper.map(item, Item.class))
            .collect(Collectors.toList());
        log.trace("List Item object generated successfully. {}", items);

        log.trace("Generating Criteria Map from DTO. {}", itemTaxList.getCriteria());
        Map<String, Object> criteria = transformCriteria(itemTaxList.getCriteria());
        log.trace("Criteria map generated successfully. {}", criteria);

        List<TaxableItem> taxableItems = listTaxesFacade.findAndCalculate(items, criteria);

        log.trace("Generating DTO from list item tax {}", taxableItems);
        List<TaxableItemDTO> itemDetailDTOS = taxableItems.stream()
            .map(item -> modelMapper.map(item, TaxableItemDTO.class))
            .collect(Collectors.toList());
        log.trace("DTO generated successfully {}", itemDetailDTOS);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ApiResponseDTO.<List<TaxableItemDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Tax for given items retrieved successfully")
                .data(itemDetailDTOS)
                .build());
    }

    private Map<String, Object> transformCriteria(Map<String, Object> criteria)
        throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(criteria);
        return objectMapper.readValue(json,
            new TypeReference<HashMap<String, Object>>() {
            });
    }
}
