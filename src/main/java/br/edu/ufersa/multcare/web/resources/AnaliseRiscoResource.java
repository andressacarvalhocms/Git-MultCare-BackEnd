package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.service.AnaliseDeRiscoDeDRCService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/api/analise")
public class AnaliseRiscoResource {

	private final AnaliseDeRiscoDeDRCService analiseDeRiscoDeDRCService;

	public AnaliseRiscoResource(AnaliseDeRiscoDeDRCService analiseDeRiscoDeDRCService) {
		this.analiseDeRiscoDeDRCService = analiseDeRiscoDeDRCService;
	}

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
	
	@GetMapping("/obter-ultimaanalises")
	public ResponseEntity<?> obterUltimaAnalisesUsuarioAutenticado() {
		List<Analise> analises = analiseDeRiscoDeDRCService.obterUltimaAnalisesPorUsuario();
		return ResponseEntity.ok(analises);
	}
}