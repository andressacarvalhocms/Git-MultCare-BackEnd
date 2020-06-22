package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.persistence.entities.*;
import br.edu.ufersa.multcare.persistence.repositories.MonitoramentoRepository;
import br.edu.ufersa.multcare.service.ExameService;
import br.edu.ufersa.multcare.service.MonitoramentoExameService;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.shared.dto.MonitoramentoDTO;
import org.springframework.stereotype.Component;

import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.*;
import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;
import static java.lang.Integer.parseInt;

@Component
public class MonitoramentoExameServiceImpl implements MonitoramentoExameService {

    private UsuarioService usuarioService;
    private ExameService exameService;
    private MonitoramentoRepository monitoramentoRepository;

    public MonitoramentoExameServiceImpl(UsuarioService usuarioService, ExameService exameService, MonitoramentoRepository monitoramentoRepository) {
        this.usuarioService = usuarioService;
        this.exameService = exameService;
        this.monitoramentoRepository = monitoramentoRepository;
    }

    @Override
    public String realizarMonitoramento(MonitoramentoDTO dto) throws Exception {

        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());

        Exame exame = new Exame();
        exame.setDataCadastro(dto.getDataCadastro());
        exame.setNome(dto.getNome());
        exame.setResultado(dto.getResultado());
        exame.setUsuario(usuario);

        exame = exameService.cadastrarExame(exame);

        if (GLICEMIA_JEJUM.getCodigo().equals(exame.getCodigoExame()) || GLIGEMICA_PRE_PRAN.getCodigo().equals(exame.getCodigoExame())) {
            Monitoramento monitoramento = analiseExameGlicemia(dto.getResultado(), 110);
            monitoramento.setExame(exame);
            monitoramento.setUsuario(usuario);

            return monitoramentoRepository.save(monitoramento).getMensagem();

        } else if ( GLIGEMICA_POS_PRAN.getCodigo().equals(exame.getCodigoExame())) {
            Monitoramento monitoramento = analiseExameGlicemia(dto.getResultado(), 140);
            monitoramento.setExame(exame);
            monitoramento.setUsuario(usuario);

            return monitoramentoRepository.save(monitoramento).getMensagem();

        } else if (PRESSAO_ARTERIAL.getCodigo().equals(exame.getCodigoExame())) {
            Monitoramento monitoramento = analiseExamePressaoArterial(exame.getResultado());
            monitoramento.setExame(exame);
            monitoramento.setUsuario(usuario);

            return monitoramentoRepository.save(monitoramento).getMensagem();
        }
        return null;
    }

    private Monitoramento analiseExameGlicemia(String resultado, Integer maximo) {

        Monitoramento  monitoramento = new Monitoramento();

        if (parseInt(resultado) > maximo) {
            monitoramento.setStatus("Glicose acima do normal");
            monitoramento.setMensagem("Sua taxa de glicose está acima do normal.");
        } else {
            monitoramento.setStatus("Glicose normal");
            monitoramento.setMensagem("Sua taxa de glicose está atingindo os objetivos para um melhor controle.");
        }
        return monitoramento;
    }

    private Monitoramento analiseExamePressaoArterial(String resultado) {

        Monitoramento monitoramento = new Monitoramento();
        String[] pressao = resultado.split("/");

        int sistolica = parseInt(pressao[0]);
        int diastolica = parseInt(pressao[1]);

        if (sistolica <= 120 && diastolica <= 80) {
            monitoramento.setStatus("PA Ótima");
            monitoramento.setMensagem("Sua taxa de pressão arterial está ótima.");

        } else if (sistolica < 130 && diastolica < 85) {
            monitoramento.setStatus("PA normal");
            monitoramento.setMensagem("Sua taxa de pressão arterial está normal.");

        } else if (sistolica >= 130 && sistolica < 139 && diastolica >= 85 && diastolica < 90) {
            monitoramento.setStatus("PA limitrofe");
            monitoramento.setMensagem("Sua taxa de pressão arterial está limitrofe.");

        } else if (sistolica >= 140 && sistolica < 159 && diastolica >= 90 && diastolica < 99) {
            monitoramento.setStatus("PA Hipertensão estágio 1");
            monitoramento.setMensagem("Sua taxa de pressão arterial está Hipertensão estágio 1.");

        } else if (sistolica >= 169 && sistolica < 179 && diastolica >= 100 && diastolica < 109) {
            monitoramento.setStatus("PA Hipertensão estágio 2");
            monitoramento.setMensagem("Sua taxa de pressão arterial está Hipertensão estágio 2.");

        } else if (sistolica >= 180 && diastolica >= 110) {
            monitoramento.setStatus("PA Hipertensão estágio 2");
            monitoramento.setMensagem("Sua taxa de pressão arterial está Hipertensão estágio 2.");

        } else if (sistolica >= 140 && diastolica < 90) {
            monitoramento.setStatus("PA Hipertensão estágio 3");
            monitoramento.setMensagem("Sua taxa de pressão arterial está Hipertensão estágio 3.");

        }

      /*  else if ( sistolica >= 140 && diastolica < 90){
            monitoramento.setStatus("PA Hipertensão estágio 2");
            monitoramento.setMensagem("Sua taxa de pressão arterial está Hipertensão sistólica isolada.");
        } */

        else {
            monitoramento.setStatus("Inconclusivo");
            monitoramento.setMensagem("Inconclusivo.");
        }
        return monitoramentoRepository.save(monitoramento);
    }

}
