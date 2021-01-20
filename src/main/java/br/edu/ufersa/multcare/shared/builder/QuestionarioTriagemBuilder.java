package br.edu.ufersa.multcare.shared.builder;

import java.util.Date;
import java.util.List;

import br.edu.ufersa.multcare.persistence.entities.MedicamentoTriagem;
import br.edu.ufersa.multcare.persistence.entities.QuestionarioTriagem;
import br.edu.ufersa.multcare.persistence.entities.SintomaTriagem;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.shared.dto.QuestionarioTriagemDTO;

public class QuestionarioTriagemBuilder {

    private QuestionarioTriagem questionarioTriagem;

    public QuestionarioTriagemBuilder() {
        this.questionarioTriagem = new QuestionarioTriagem();
    }

    public static QuestionarioTriagemBuilder of(QuestionarioTriagemDTO dto) {
        return new QuestionarioTriagemBuilder()
                .comDiabetico(dto.getDiabetico())
                .comInsulina(dto.getInsulina())
                .comMedirGlicemia(dto.getMedirGlicemia())
                .comResultadoGlicemia(dto.getResultadoGlicemia())
                .comPotassio(dto.getPotassio())
                .comResultadopotassio(dto.getResultadopotassio())
                .comAlcool(dto.getAlcool())
                .comTipoMedicamento(dto.getTipoMedicamento())
                .comTipoSintomas(dto.getTipoSintomas())
                .comDataCadastro();

    }

    public static QuestionarioTriagemBuilder of() {return new QuestionarioTriagemBuilder();}

    public QuestionarioTriagemBuilder comDiabetico(String diabetico) {
        this.questionarioTriagem.setDiabetico(diabetico);
        return this;
    }
    public QuestionarioTriagemBuilder comInsulina(String insulina) {
        this.questionarioTriagem.setInsulina(insulina);
        return this;
    }
    public QuestionarioTriagemBuilder comMedirGlicemia(String medirGlicemia) {
        this.questionarioTriagem.setMedirGlicemia(medirGlicemia);
        return this;
    }
    public QuestionarioTriagemBuilder comSintomaTriagem(List<SintomaTriagem> sintomaTriagem) {
        this.questionarioTriagem.setSintomaTriagem(sintomaTriagem);
        return this;
    }
    public QuestionarioTriagemBuilder comResultadoGlicemia(double resultadoGlicemia) {
        this.questionarioTriagem.setResultadoGlicemia(resultadoGlicemia);
        return this;
    }
    public QuestionarioTriagemBuilder comPotassio(String potassio) {
        this.questionarioTriagem.setPotassio(potassio);
        return this;
    }
    public QuestionarioTriagemBuilder comMedicamentoTriagem(List<MedicamentoTriagem> medicamentoTriagem) {
        this.questionarioTriagem.setMedicamentoTriagem(medicamentoTriagem);
        return this;
    }
    public QuestionarioTriagemBuilder comResultadopotassio(double resultadopotassio) {
        this.questionarioTriagem.setResultadopotassio(resultadopotassio);
        return this;
    }
    public QuestionarioTriagemBuilder comTipoMedicamento(String tipoMedicamento) {
        this.questionarioTriagem.setTipoMedicamento(tipoMedicamento);
        return this;
    }
    public QuestionarioTriagemBuilder comTipoSintomas(String tipoSintomas) {
        this.questionarioTriagem.setTipoSintomas(tipoSintomas);
        return this;
    }
    public QuestionarioTriagemBuilder comAlcool(String alcool) {
        this.questionarioTriagem.setAlcool(alcool);
        return this;
    }

    public QuestionarioTriagemBuilder comDataCadastro() {
        this.questionarioTriagem.setDataCadastro(new Date());
        return this;
    }

    public QuestionarioTriagemBuilder comUsuario(Usuario usuario) {
        this.questionarioTriagem.setUsuario(usuario);
        return this;
    }

    public QuestionarioTriagem build() {
        return this.questionarioTriagem;
    }
}