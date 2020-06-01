package br.edu.ufersa.multcare.persistence.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class QuestionarioCovid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String Temperatura;
	private String Sintomas;
	private String QtdDiasSintomas;
	private String ProgressaoSintomas;
	private String SintomasAdicionais;
	private String ContatoSuspeito;
	private String TipoContatoSuspeito;
	private String Patologias;
	private String FaixaEtaria;
	private String Sexo;
	private String TipoSanguineo;
	private Date dataCadastro;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

}
