package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.service.MonitoramentoExameService;
import br.edu.ufersa.multcare.shared.dto.MonitoramentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/monitoramento")
public class MonitoramentoResource {

	private final MonitoramentoExameService monitoramentoExameService;

	public MonitoramentoResource(MonitoramentoExameService monitoramentoExameService) {
		this.monitoramentoExameService = monitoramentoExameService;
	}


	@PostMapping("/realizar")
	public ResponseEntity<String> realizarMonitoramento(@RequestBody MonitoramentoDTO monitoramentoDTO) throws Exception {

		String analise = monitoramentoExameService.realizarMonitoramento(monitoramentoDTO);
		return ResponseEntity.ok(analise);
	}

}