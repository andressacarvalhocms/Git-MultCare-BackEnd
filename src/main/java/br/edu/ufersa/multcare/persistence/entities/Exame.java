package br.edu.ufersa.multcare.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement
@Entity
@Getter @Setter @ToString
public class Exame  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String resultado;
	private Date dataCadastro;
	private Integer codigoExame;
	private String status;

	@Column(name = "usuario_id", insertable = false, updatable = false)
	private int idUsuario;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

	public Exame(){}
	  
	  public Exame(String nome, String resultado){
	    this.nome = nome;
	    this.resultado = resultado;
	  }
	  
	  @Override
	  public String toString() {
	    String info = String.format("Customer Info: nome = %s, resultado = %s", 
	    		nome, resultado);
	    return info;
	  }
}
