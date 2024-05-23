package com.robinfood.core.dtos.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
public class RequestUserDTO {

        @NotBlank
        @Email
        private String email;

        @NotNull
        @Positive
        private Long id;

        private String lastName;

        @NotBlank
        private String mobile;

        @NotBlank
        private String name;

        private Long orderId;

        private String phoneCode;

        private Long storeId;

        /**
         * Full name user
         *
         * @return String combination of the first name plus the last name
         */
        public String getFullName () {
                return name + " " + lastName;
        }

}
