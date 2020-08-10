package br.edu.ufersa.multcare.model.bean.cda;

import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.Alergia;
import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.persistence.entities.Medicamento;

/**
 *
 * @author Gyovanne Cavalcanti
 */
public class Componentes {

    private List<Exame> exameslaboratoriais;
    private List<Alergia> alergias;
    private List<Medicamento> medicamentos;
    private List<Analise> diagnostico;
//    private List<Conteudo> historicomedico;
 //   private List<Conteudo> historicofamiliar;
 //   private List<Conteudo> historicosaude;
    private List<Exame> exames;
  //  private List<Conteudo> tratamento;
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
        return alergias;
    }

    /**
     *
     * @param alergias
     */
    public void setAlergias(List<Alergia> alergias) {
        this.alergias = alergias;
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

    /**
     *
     * @return
     */
 /*   public List<Conteudo> getHistoricomedico() {
        return historicomedico;
    }

    /**
     *
     * @param historicomedico
     */
   /* public void setHistoricomedico(List<Conteudo> historicomedico) {
        this.historicomedico = historicomedico;
    }

    /**
     *
     * @return
     */
  /*  public List<Conteudo> getHistoricofamiliar() {
        return historicofamiliar;
    }

    /**
     *
     * @param historicofamiliar
     */
  /*  public void setHistoricofamiliar(List<Conteudo> historicofamiliar) {
        this.historicofamiliar = historicofamiliar;
    }

    /**
     *
     * @return
     */
  /*  public List<Conteudo> getHistoricosaude() {
        return historicosaude;
    }

    /**
     *
     * @param historicosaude
     */
   /* public void setHistoricosaude(List<Conteudo> historicosaude) {
        this.historicosaude = historicosaude;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
   /* public List<Conteudo> getTratamento() {
        return tratamento;
    }

    /**
     *
     * @param tratamento
     */
  /*  public void setTratamento(List<Conteudo> tratamento) {
        this.tratamento = tratamento;
    }
*/
}
