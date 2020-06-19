package br.edu.ufersa.multcare.persistence.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
@Getter @Setter @ToString
public class SintomaAdicional {

	@Id
	private Integer id;
	private String descricao;

}
