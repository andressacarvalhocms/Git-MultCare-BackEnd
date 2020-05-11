package br.edu.ufersa.multcare.shared.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @ToString
public class UsuarioDTO {

    @NotNull(message = "O campo idade é obrigatório")
    private Integer idade;

    @NotNull(message = "O campo nome é obrigatório") @NotEmpty
    private String nome;

    @NotNull(message = "O campo senha é obrigatório") @NotEmpty
    private String senha;

    @NotNull(message = "O campo sexo é obrigatório")
    private Character sexo;

    @NotNull(message = "O campo peso é obrigatório")
    private Float peso;

    @NotNull(message = "O campo login é obrigatório") @NotEmpty
    private String login;

    private Character hipertenso;

    private Character diabetico;

}
