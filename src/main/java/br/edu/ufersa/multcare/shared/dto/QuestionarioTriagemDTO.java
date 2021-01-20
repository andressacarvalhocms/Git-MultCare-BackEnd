package br.edu.ufersa.multcare.shared.dto;


import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class QuestionarioTriagemDTO {

    
    
	private String Diabetico;
	private String Insulina;
	private String MedirGlicemia;
	private double ResultadoGlicemia;
	private String Potassio;
	private double Resultadopotassio;
	private String Alcool;
	private String TipoMedicamento;
	private String TipoSintomas;
	private String ResultadoEncaminhamento;
    private List<Integer> sintomaTriagem;
    private List<Integer> medicamentoTriagem;
}
