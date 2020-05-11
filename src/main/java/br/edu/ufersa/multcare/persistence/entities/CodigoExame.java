package br.edu.ufersa.multcare.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CodigoExame {

    PRESSAO_ARTERIAL("Pressão Arterial", 1),
    CREATININA("creatinina", 2),
    UREIA("Uréia", 3),
    ALBUMINURIA("Albuminúria", 4),
    POTASSIO("Potássio", 5),
    TFG ("TFG", 6),
    MICROALBUMINURIA("Microalbuminúria", 7),
    GLICEMIA_JEJUM("Glicemia de jejum", 8),
    GLIGEMICA_PRE_PRAN ("Glicemia pré-prandial", 9),
    GLIGEMICA_POS_PRAN ("Glicemia pós-prandial", 10),
    ALBUMINA("Albumina", 11);


    private String descricao;
    private Integer codigo;

    public static CodigoExame getCodigoPorDescricao(String descricao) {
        return Arrays.stream(values())
                .filter(bl -> bl.descricao.equals(descricao))
                .findFirst().get();
    }

}
