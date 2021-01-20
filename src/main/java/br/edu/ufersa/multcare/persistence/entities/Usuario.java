package br.edu.ufersa.multcare.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue
	@GenericGenerator(name = "increment", strategy = "increment") 
	
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String login;
	private String senha;
	private int idade;
	private char sexo;
	private char hipertenso;
	private char diabetico;
	private float peso;
	private String nomeMedico;
	private String emailMedico;

}
