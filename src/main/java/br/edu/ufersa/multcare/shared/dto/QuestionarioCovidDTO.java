package br.edu.ufersa.multcare.shared.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class QuestionarioCovidDTO {

    private String Temperatura;
    private String QtdDiasSintomas;
    private String ProgressaoSintomas;
    private List<Integer> sintomasAdicional;
    private String ContatoSuspeito;
    private String TipoContatoSuspeito;
    private List<Integer> patologia;
    private String FaixaEtaria;
    private String Sexo;
    private String TipoSanguineo;
    private List<Integer> sintomas;
}
