package com.robinfood.paymentmethodsbc.controllers.v1;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.annotations.BasicLog;
import com.robinfood.paymentmethodsbc.controllers.v1.docs.CreditCardsDocs;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.CreditCardDetailDTO;
import com.robinfood.paymentmethodsbc.dto.api.creditcards.UpdateCreditCardRequestDTO;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.exceptions.EntityNotFoundException;
import com.robinfood.paymentmethodsbc.exceptions.PaymentStepException;
import com.robinfood.paymentmethodsbc.mappers.CreditCardMapper;
import com.robinfood.paymentmethodsbc.model.CreditCard;
import com.robinfood.paymentmethodsbc.services.CreditCardService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/api/v1/backoffice/credit-cards")
public class CreditCardsController implements CreditCardsDocs {
    private final CreditCardService crudCreditCardService;

    public CreditCardsController(CreditCardService crudCreditCardService) {
        this.crudCreditCardService = crudCreditCardService;
    }

    @Override
    @BasicLog
    @GetMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
        "hasAuthority(@Permissions.PERM_CREDIT_CARD_LIST) or hasAuthority(@Permissions.PERM_SERVICE)"
    )
    public List<CreditCardDetailDTO> findAllByUserAndCountryAndPaymentGateway(
        Long userId,
        Long countryId,
        Long generalPaymentMethodId
    )
        throws BaseException, EntityNotFoundException, PaymentStepException {
        return crudCreditCardService
            .findAllByUserAndCountryAndPaymentMethodId(userId, countryId, generalPaymentMethodId)
            .stream()
            .map(CreditCardMapper::getCreditCardDetailDTO)
            .collect(Collectors.toList());
    }

    @Override
    @BasicLog
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(
        "hasAuthority(@Permissions.PERM_CREDIT_CARD_CREATE) or hasAuthority(@Permissions.PERM_SERVICE)"
    )
    public CreditCardDetailDTO create(
        @Valid @RequestBody CreateCreditCardRequestDTO creditCardDTO
    )
        throws BaseException, EntityNotFoundException, PaymentStepException {

        CreditCard creditCard = crudCreditCardService.create(creditCardDTO);
        return CreditCardMapper.getCreditCardDetailDTO(creditCard);
    }

    @Override
    @BasicLog
    @DeleteMapping("/users/{userId}/credit-card/{creditCardId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize(
        "hasAuthority(@Permissions.PERM_CREDIT_CARD_DELETE) or hasAuthority(@Permissions.PERM_SERVICE)"
    )
    public ResponseDTO<String> delete(Long userid, Long creditCardId)
        throws BaseException, EntityNotFoundException, PaymentStepException {
        String result = crudCreditCardService.delete(userid, creditCardId);
        return new ResponseDTO<>(
            ResponseCode.SUCCESS.getCode(),
            result,
            result,
            new ResponseDTO.ErrorDTO()
        );
    }

    @Override
    @BasicLog
    @PutMapping("/users/{userId}/credit-card/{creditCardId}")
    @PreAuthorize(
        "hasAuthority(@Permissions.PERM_CREDIT_CARD_UPDATE) or hasAuthority(@Permissions.PERM_SERVICE)"
    )
    public CreditCardDetailDTO update(
        Long userId, 
        Long creditCardId, 
        @Valid @RequestBody UpdateCreditCardRequestDTO updateCreditCardRequestDTO
    ) throws BaseException, EntityNotFoundException, PaymentStepException {

        CreditCard creditCard = crudCreditCardService.update(
            userId, 
            creditCardId, 
            updateCreditCardRequestDTO
        );

        return CreditCardMapper.getCreditCardDetailDTO(creditCard);
    }
}
