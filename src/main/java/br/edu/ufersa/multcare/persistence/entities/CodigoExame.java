package br.edu.ufersa.multcare.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CodigoExame {

    PRESSAO_ARTERIAL("pressão arterial", 1),
    CREATININA("creatinina", 2),
    UREIA("uréia", 3),
    ALBUMINURIA("albuminúria", 4),
    POTASSIO("potássio", 5),
    TFG ("tfg", 6),
    MICROALBUMINURIA("microalbuminúria", 7),
    GLICEMIA_JEJUM("glicemia de jejum", 8),
    GLIGEMICA_PRE_PRAN ("glicemia pré-prandial", 9),
    GLIGEMICA_POS_PRAN ("glicemia pós-prandial", 10),
    ALBUMINA("albumina", 11);


    private String descricao;
    private Integer codigo;

    public static CodigoExame getCodigoPorDescricao(String descricao) {
        return Arrays.stream(values())
                .filter(bl -> bl.descricao.equals(descricao))
                .findFirst().get();
    }

}
