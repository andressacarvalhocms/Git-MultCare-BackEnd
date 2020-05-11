package br.edu.ufersa.multcare.persistence.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "nome"),
		@UniqueConstraint(columnNames = "login")
})
@Getter @Setter @ToString
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String login;
	private String senha;
	private int idade;
	private char sexo;
	private char hipertenso;
	private char diabetico;
	private float peso;

}
