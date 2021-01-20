package br.edu.ufersa.multcare.persistence.entities;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class QuestionarioTriagem {

    @Id
    @GeneratedValue
	@GenericGenerator(name = "increment", strategy = "increment") 
	
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Diabetico;
    private String Insulina;
    private String MedirGlicemia;
 //   private String MedirPotassio;
    private double ResultadoGlicemia;
    private String Potassio;
    private double Resultadopotassio;
    private String Alcool;
    private Date dataCadastro;
    private String TipoMedicamento;
    private String TipoSintomas;
    private String resultadoClassificacao;
    private String resultadoEncaminhamento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "questionario_triagem_sintoma", joinColumns =
            {@JoinColumn(name = "questionario_sintoma_id")}, inverseJoinColumns =
            {@JoinColumn(name = "sintoma_id")})
    private List<SintomaTriagem> sintomaTriagem;

    @ManyToMany
    @JoinTable(name = "questionario_triagem_medicamento", joinColumns =
            {@JoinColumn(name = "questionario_triagem_id")}, inverseJoinColumns =
            {@JoinColumn(name = "medicamento_id")})
    private List<MedicamentoTriagem> medicamentoTriagem;

    public Boolean possuiSintomaPorDescricao(String descricao) {

        return this.sintomaTriagem
                .stream()
                .anyMatch(x -> descricao.equals(x.getDescricao()));
    }

    public Boolean possuiMedicamentoPorDescricao(String descricao) {
        return this.medicamentoTriagem
                .stream()
                .anyMatch(x -> descricao.equals(x.getDescricao()));
    }
}

