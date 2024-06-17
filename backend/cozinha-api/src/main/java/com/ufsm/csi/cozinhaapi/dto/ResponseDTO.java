package com.ufsm.csi.cozinhaapi.dto;

import com.ufsm.csi.cozinhaapi.model.Role;

public record ResponseDTO(String name, String token, Role role){
}
