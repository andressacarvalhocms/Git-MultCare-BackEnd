package br.edu.ufersa.multcare.shared.dto;

import br.edu.ufersa.multcare.persistence.entities.Exame;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Getter @Setter @EqualsAndHashCode
public class ExamesXmlDTO {

    private List<Exame> exames;
}
