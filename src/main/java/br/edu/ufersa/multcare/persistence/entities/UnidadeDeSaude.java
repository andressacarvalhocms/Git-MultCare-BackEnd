package br.edu.ufersa.multcare.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
public class UnidadeDeSaude {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String endereco;
	private String telefone;
	private int longitude;
	private int latitude;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Usuario usuario;

}
