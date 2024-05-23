package com.robinfood.paymentmethodsbc.controllers.v1;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.controllers.v1.docs.AdminDocs;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/api/v1/admin")
public class AdminController implements AdminDocs {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @GetMapping("/cache/reset")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority(@Permissions.PERM_RESET_GW_CONFIG)")
    public ResponseDTO<String> cacheReset() throws BaseException {
        String result = adminService.cacheReset();

        return new ResponseDTO<>(
            ResponseCode.SUCCESS.getCode(),
            result,
            result,
            new ResponseDTO.ErrorDTO()
        );
    }
}
