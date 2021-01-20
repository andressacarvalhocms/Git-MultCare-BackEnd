package br.edu.ufersa.multcare.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Getter @Setter @ToString
public class Monitoramento {
	
	@Id
	@GeneratedValue
	@GenericGenerator(name = "increment", strategy = "increment") 
	
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String status;

	@Column(name = "usuario_id", insertable = false, updatable = false)
	private int idUsuario;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	private Exame exame;

	@Transient
	private String mensagem;

}
