package br.edu.ufersa.multcare.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.GLICEMIA_JEJUM;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.GLIGEMICA_POS_PRAN;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.GLIGEMICA_PRE_PRAN;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.PRESSAO_ARTERIAL;

import java.util.Date;


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
	private String classificacao;
	private Date dataCadastro;
	private Double glicemia_jejum;
	private Double glicemia_pre_pran; 
	private Double glicemia_pos_pran; 
	private Double pressao_arterial;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

}
