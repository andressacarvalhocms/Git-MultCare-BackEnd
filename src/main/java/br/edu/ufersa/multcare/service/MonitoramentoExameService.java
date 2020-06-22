package br.edu.ufersa.multcare.service;

import br.edu.ufersa.multcare.shared.dto.MonitoramentoDTO;
import org.springframework.stereotype.Service;

@Service
public interface MonitoramentoExameService {

    String realizarMonitoramento(MonitoramentoDTO monitoramentoDTO) throws Exception;

}
