package com.erp.controle.financeiro.dto;

import com.erp.controle.financeiro.enums.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {

    private Long id;
    private String username;
    private String password;
    private RoleName roleName;

    // getters e setters
}