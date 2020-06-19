package br.edu.ufersa.multcare.shared.builder;

import br.edu.ufersa.multcare.persistence.entities.Patologia;
import br.edu.ufersa.multcare.persistence.entities.QuestionarioCovid;
import br.edu.ufersa.multcare.persistence.entities.Sintoma;
import br.edu.ufersa.multcare.persistence.entities.SintomaAdicional;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.shared.dto.QuestionarioCovidDTO;

import java.util.Date;
import java.util.List;

public class QuestionarioCovidBuilder {

    private QuestionarioCovid questionarioCovid;

    public QuestionarioCovidBuilder() {
        this.questionarioCovid = new QuestionarioCovid();
    }

    public static QuestionarioCovidBuilder of(QuestionarioCovidDTO dto) {
        return new QuestionarioCovidBuilder()
                .comTemperatura(dto.getTemperatura())
                .comQtdDiasSintomas(dto.getQtdDiasSintomas())
                .comProgressaoSintomas(dto.getProgressaoSintomas())
             //   .comSintomaAdicional(dto.getSintomasAdicional())
                .comContatoSuspeito(dto.getContatoSuspeito())
                .comTipoSanguineo(dto.getTipoSanguineo())
               // .comPatologia(dto.getPatologia())
                .comTipoContatoSuspeito(dto.getTipoContatoSuspeito())
                .comFaixaEtaria(dto.getFaixaEtaria())
                .comSexo(dto.getSexo())
                .comDataCadastro();

    }

    public static QuestionarioCovidBuilder of() {return new QuestionarioCovidBuilder();}

    public QuestionarioCovidBuilder comTemperatura(String temperatura) {
        this.questionarioCovid.setTemperatura(temperatura);
        return this;
    }
    public QuestionarioCovidBuilder comQtdDiasSintomas(String qtdDiasSintomas) {
        this.questionarioCovid.setQtdDiasSintomas(qtdDiasSintomas);
        return this;
    }
    public QuestionarioCovidBuilder comProgressaoSintomas(String progressaoSintomas) {
        this.questionarioCovid.setProgressaoSintomas(progressaoSintomas);
        return this;
    }
    public QuestionarioCovidBuilder comSintomaAdicional(List<SintomaAdicional> sintomasAdicional) {
        this.questionarioCovid.setSintomasAdicional(sintomasAdicional);
        return this;
    }
    public QuestionarioCovidBuilder comContatoSuspeito(String contatoSuspeito) {
        this.questionarioCovid.setContatoSuspeito(contatoSuspeito);
        return this;
    }
    public QuestionarioCovidBuilder comTipoContatoSuspeito(String tipoContato) {
        this.questionarioCovid.setTipoContatoSuspeito(tipoContato);
        return this;
    }
    public QuestionarioCovidBuilder comPatologia(List<Patologia> patologia) {
        this.questionarioCovid.setPatologia(patologia);
        return this;
    }
    public QuestionarioCovidBuilder comFaixaEtaria(String faixaEtaria) {
        this.questionarioCovid.setFaixaEtaria(faixaEtaria);
        return this;
    }
    public QuestionarioCovidBuilder comTipoSanguineo(String tipoSanguineo) {
        this.questionarioCovid.setTipoSanguineo(tipoSanguineo);
        return this;
    }

    public QuestionarioCovidBuilder comSintomas(List<Sintoma> sintomas) {
        this.questionarioCovid.setSintomas(sintomas);
        return this;
    }

    public QuestionarioCovidBuilder comSexo(String sexo) {
        this.questionarioCovid.setSexo(sexo);
        return this;
    }

    public QuestionarioCovidBuilder comDataCadastro() {
        this.questionarioCovid.setDataCadastro(new Date());
        return this;
    }

    public QuestionarioCovidBuilder comUsuario(Usuario usuario) {
        this.questionarioCovid.setUsuario(usuario);
        return this;
    }

    public QuestionarioCovid build() {
        return this.questionarioCovid;
    }
}
