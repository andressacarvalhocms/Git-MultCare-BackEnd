package br.edu.ufersa.multcare.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	private String QtdDiasSintomas;
	private String ProgressaoSintomas;
	//private String SintomasAdicionais;
	private String ContatoSuspeito;
	private String TipoContatoSuspeito;
	//private String Patologias;
	private String FaixaEtaria;
	private String Sexo;
	private String TipoSanguineo;
	private Date dataCadastro;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

	@ManyToMany
	@JoinTable(name = "questionario_covid_sintoma",  joinColumns=
			{@JoinColumn(name="questionario_covid_id")}, inverseJoinColumns=
			{@JoinColumn(name="sintoma_id")})
	private List<Sintoma> sintomas;


	@ManyToMany
	@JoinTable(name = "questionario_covid_sintomaAdicional",  joinColumns=
			{@JoinColumn(name="questionario_covid_id")}, inverseJoinColumns=
			{@JoinColumn(name="sintomaAdicional_id")})
	private List<SintomaAdicional> sintomasAdicional;
	

	@ManyToMany
	@JoinTable(name = "questionario_covid_patologia",  joinColumns=
			{@JoinColumn(name="questionario_covid_id")}, inverseJoinColumns=
			{@JoinColumn(name="patologia_id")})
	private List<Patologia> patologia;
}
