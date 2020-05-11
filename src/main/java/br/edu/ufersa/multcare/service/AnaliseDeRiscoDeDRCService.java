package br.edu.ufersa.multcare.service;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnaliseDeRiscoDeDRCService {

    Analise realizarClassificacaoAnalise() throws Exception;

    List<Analise> obterAnalisesDoUsuarioAutenticado();
}
