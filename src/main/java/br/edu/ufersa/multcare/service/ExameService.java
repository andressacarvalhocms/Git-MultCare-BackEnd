package br.edu.ufersa.multcare.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.ufersa.multcare.persistence.entities.Exame;

@Service
public interface ExameService {

    List<Exame> listarExamesUsuarioLogado();

    Exame cadastrarExame(Exame exame);

    Exame atualizarExame(Exame exame);

    void deletarExame(Exame exame);

    Map<String, Boolean> examesCadastradosUsuarioLogado();

	public List<Exame> cadastrarExamePorXml(List<Exame> examesXmlDTO);
	
}
