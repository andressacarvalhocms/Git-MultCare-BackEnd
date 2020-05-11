package br.edu.ufersa.multcare.shared.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @ToString
public class LoginDTO {

    @NotNull(message = "O campo Password é obrigatório") @NotEmpty
    private String password;

    @NotNull(message = "O campo login é obrigatório") @NotEmpty
    private String username;

}
