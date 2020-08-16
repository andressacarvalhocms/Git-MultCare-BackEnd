package br.edu.ufersa.multcare.model.bean.cda;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.Alergia;
import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.persistence.entities.Medicamento;

public class Componentes {

    private List<Exame> exameslaboratoriais;
    private List<Alergia> listarAlergiasPorUsuario;
    private List<Medicamento> medicamentos;
    private List<Analise> diagnostico;
    private List<Exame> exames;
    private List<Exame> exameslaboratoriaisfixos;

    /**
     *
     */
    public Componentes() {
    }

    /**
     *
     * @return
     */
    public List<Exame> getExameslaboratoriaisfixos() {
        return exameslaboratoriaisfixos ;
    }

    /**
     *
     * @param exameslaboratoriaisfixos
     */
    public void setExameslaboratoriaisfixos(List<Exame> exameslaboratoriaisfixos) {
        this.exameslaboratoriaisfixos = exameslaboratoriaisfixos;
    }

    /**
     *
     * @return
     */
    public List<Exame> getExames() {
        return exames;
    }

    /**
     *
     * @param exames
     */
    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    /**
     *
     * @return
     */
    public List<Alergia> getAlergias() {
        return listarAlergiasPorUsuario;
    }

    /**
     *
     * @param alergias
     */
    public void setAlergias(List<Alergia> alergias) {
        this.listarAlergiasPorUsuario = alergias;
    }

    /**
     *
     * @return
     */
    public List<Analise> getDiagnostico() {
        return diagnostico;
    }

    /**
     *
     * @param diagnostico
     */
    public void setDiagnostico(List<Analise> diagnostico) {
        this.diagnostico = diagnostico;
    }

    public List<Exame> getExameslaboratoriais() {
        return exameslaboratoriais;
    }

    /**
     *
     * @param exameslaboratoriais
     */
    public void setExameslaboratoriais(List<Exame> exameslaboratoriais) {
        this.exameslaboratoriais = exameslaboratoriais;
    }

    /**
     *
     * @return
     */
    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    /**
     *
     * @param medicamentos
     */
    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }


}
