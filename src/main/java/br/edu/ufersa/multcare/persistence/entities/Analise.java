package br.edu.ufersa.multcare.persistence.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter @ToString
public class Analise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private char dm;
	private char has;
	private Double creatinina;
	private Double ureia;
	private Double microalbuminaria;
	private int idade;
	private char sexo;
	private Double tfg;
	private Double pred;
	private String classificacao;
	private Date dataCadastro;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

	@Transient
	private List<Exame> exames = new ArrayList<>();

	public void invalidarExamesUtilizados() {
		exames.forEach(Exame::alterarStatusParaInativo);
	}

}
