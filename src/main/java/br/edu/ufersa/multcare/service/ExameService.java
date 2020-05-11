package br.edu.ufersa.multcare.service;

import br.edu.ufersa.multcare.persistence.entities.Exame;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ExameService {

    List<Exame> listarExamesUsuarioLogado();

    Exame cadastrarExame(Exame exame);

    Exame atualizarExame(Exame exame);

    void deletarExame(Exame exame);

    Map<String, Boolean> examesCadastradosUsuarioLogado();
}
