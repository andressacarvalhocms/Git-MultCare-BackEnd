package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.CREATININA;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.GLICEMIA_JEJUM;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.GLIGEMICA_POS_PRAN;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.GLIGEMICA_PRE_PRAN;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.MICROALBUMINURIA;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.PRESSAO_ARTERIAL;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.TFG;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.UREIA;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.getCodigoPorDescricao;
import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;
import static br.edu.ufersa.multcare.util.StringUtil.removerAcentos;
import static br.edu.ufersa.multcare.util.StringUtil.substituirEspacosPorUnderScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.ExameRepository;
import br.edu.ufersa.multcare.service.ExameService;
import br.edu.ufersa.multcare.service.UsuarioService;

@Component
public class ExameServiceImpl implements ExameService {

    private final ExameRepository exameRepository;

    private final UsuarioService usuarioService;

    public ExameServiceImpl(ExameRepository exameRepository, UsuarioService usuarioService) {
        this.exameRepository = exameRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<Exame> listarExamesUsuarioLogado() {
        return exameRepository.listarExamesUsuario(obterIdUsuarioAutenticado());
    }

    @Override
    public Exame cadastrarExame(Exame exame) {
        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        exame.setUsuario(usuario);
        exame.setDataCadastro(new Date());
        exame.setStatus("A");
        exame.setCodigoExame(getCodigoPorDescricao(exame.getNome()).getCodigo());

        return exameRepository.save(exame);
    }

    @Override
    public List<Exame> cadastrarExamePorXml(List<Exame> exame) {

    	return exame.stream().map(this::cadastrarExame).collect(Collectors.toList());
	}
    
    @Override
    public Exame atualizarExame(Exame exame) {
        return exameRepository.save(exame);
    }

    @Override
    public void deletarExame(Exame exame) {
        exameRepository.delete(exame);
    }

    @Override
    public Map<String, Boolean> examesCadastradosUsuarioLogado() {
        Integer idUsuario = obterIdUsuarioAutenticado();
        String Status = "A";
        Map<String, Boolean> map = new HashMap<>();

        Arrays.asList(CREATININA, UREIA, MICROALBUMINURIA, TFG, GLICEMIA_JEJUM, GLIGEMICA_PRE_PRAN, GLIGEMICA_POS_PRAN, PRESSAO_ARTERIAL).forEach(codigoExame -> {
            Exame exame = exameRepository.findDistinctTopByCodigoExameEqualsAndIdUsuarioEqualsAndStatus(codigoExame.getCodigo(), idUsuario, Status);
            map.put(tratarDescricao(codigoExame.getDescricao()), exame != null);
        });
        return map;
    }

    private String tratarDescricao(String str) {
        return removerAcentos(substituirEspacosPorUnderScore(str)).toLowerCase();
    }

	

	
}
