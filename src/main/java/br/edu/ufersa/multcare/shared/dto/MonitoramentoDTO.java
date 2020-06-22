package br.edu.ufersa.multcare.shared.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MonitoramentoDTO {

    private Date dataCadastro;
    private String nome;
    private String resultado;

}
