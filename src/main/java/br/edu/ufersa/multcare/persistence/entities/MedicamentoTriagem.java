package br.edu.ufersa.multcare.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
@Getter @Setter @ToString
public class MedicamentoTriagem {

	@Id
	@GeneratedValue
	@GenericGenerator(name = "increment", strategy = "increment") 
	
	private Integer id;
	private String descricao;

}
