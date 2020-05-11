package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.service.AnaliseDeRiscoDeDRCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/api/analise")
public class AnaliseRiscoResource {

	@Autowired
	private AnaliseDeRiscoDeDRCService analiseDeRiscoDeDRCService;

	@GetMapping("/realizar")
	public ResponseEntity<Analise> realizarAnaliseRisco() throws Exception {

		Analise analise = analiseDeRiscoDeDRCService.realizarClassificacaoAnalise();
		return ResponseEntity.ok(analise);
	}

	@GetMapping("/obter-analises")
	public ResponseEntity<?> obterAnalisesUsuarioAutenticado() {
		List<Analise> analises = analiseDeRiscoDeDRCService.obterAnalisesDoUsuarioAutenticado();
		return ResponseEntity.ok(analises);
	}
}