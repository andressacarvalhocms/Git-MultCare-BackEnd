package br.edu.ufersa.multcare.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import cdapi.document.ClinicalDocument;

@Service
public interface CriarDocumentoClinicoService {

	String criarDocumentoClinico(Usuario user, ClinicalDocument cda, Analise analise) throws IOException;
	


}
