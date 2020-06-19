package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.CodigoExame;
import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.AnaliseRepository;
import br.edu.ufersa.multcare.persistence.repositories.ExameRepository;
import br.edu.ufersa.multcare.service.AnaliseDeRiscoDeDRCService;
import br.edu.ufersa.multcare.service.UsuarioService;
import org.springframework.stereotype.Component;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Date;
import java.util.List;

import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.*;
import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;
import static br.edu.ufersa.multcare.shared.baseaprendizagem.BaseAprendizagemSingleton.obterBaseAprendizagem;

@Component
public class AnaliseDeRiscoDeDRCServiceImpl implements AnaliseDeRiscoDeDRCService {

    private final AnaliseRepository analiseRepository;
    private final ExameRepository exameRepository;
    private UsuarioService usuarioService;

    public AnaliseDeRiscoDeDRCServiceImpl(AnaliseRepository analiseRepository, ExameRepository exameRepository, UsuarioService usuarioService) {
        this.analiseRepository = analiseRepository;
        this.exameRepository = exameRepository;
        this.usuarioService = usuarioService;
    }


    @Override
    public Analise realizarClassificacaoAnalise() throws Exception {

        Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
        Analise analise = obterExamesUsuarioParaAnalise(usuario );

        Instances instances = obterBaseAprendizagem();
        // 1.1 - espeficicação do atributo classe
        instances.setClassIndex(8);
        // (2) Construção do modelo classificador (treinamento)
        //------------------------------------------------------
        J48 k3 = new J48();
        k3.setOptions(new String[] { "-C", "0.25", "-M", "2", "-doNotMakeSplitPointActualValue" });
        k3.buildClassifier(instances);
        System.out.println(k3);
        //        // 3.1 criação de uma nova instância
        Instance novo = new DenseInstance(10);
        novo.setDataset(instances);
        novo.setValue(0, String.valueOf(analise.getHas())); // hipertensao
        novo.setValue(1, String.valueOf(analise.getDm())); // diabets
        novo.setValue(2, analise.getCreatinina()); // creatina
        novo.setValue(3, analise.getUreia()); // ureia
        novo.setValue(4, analise.getMicroalbuminaria()); // microalbuminuria
        novo.setValue(5, analise.getIdade()); // idade
        novo.setValue(6, String.valueOf(analise.getSexo())); // sexo
        novo.setValue(7, analise.getTfg()); // tfg
//
//     // 3.2 classificação de uma nova instância
        double pred = k3.classifyInstance(novo);

        Attribute a = novo.attribute(8);
        String predClass = a.value((int) pred);
 
        analise.setClassificacao(predClass);
        analise.setUsuario(usuario);
        analise.setDataCadastro(new Date());
        return analiseRepository.save(analise);
    }
    
    
    @Override
    public Analise realizarMonitoramento() throws Exception {

    	   Usuario usuario = usuarioService.obterUsuarioPorId(obterIdUsuarioAutenticado());
           Analise analise = obterExamesUsuarioParaAnalise(usuario );  
           
    if (analise.getGlicemia_jejum() < 110) {    	   
  	   	 String pred = "Glicose normal"; //salvar no banco assim	 
         String predClass = pred;  
         System.out.println("Sua taxa de glicose está atingindo os objetivos para um melhor controle."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } else if (analise.getGlicemia_jejum() > 110)  {
 	   String pred = "Glicose acima do normal."; //salvar no banco assim	 
 	   String predClass = pred;  
 	   System.out.println("Sua taxa de glicose está acima do normal."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } else if (analise.getGlicemia_pre_pran() < 110) {    	   
 	   	 String pred = "Glicose normal"; //salvar no banco assim	 
        String predClass = pred;  
        System.out.println("Sua taxa de glicose está atingindo os objetivos para um melhor controle."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } else if (analise.getGlicemia_pre_pran() > 110) {
	   String pred = "Glicose acima do normal."; //salvar no banco assim	 
	   String predClass = pred;  
	   System.out.println("Sua taxa de glicose está acima do normal."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } else if (analise.getGlicemia_pos_pran() < 140) {    	   
 	   	 String pred = "Glicose normal"; //salvar no banco assim	 
        String predClass = pred;  
        System.out.println("Sua taxa de glicose está atingindo os objetivos para um melhor controle."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } else if (analise.getGlicemia_pos_pran() >  140) {
	   String pred = "Glicose acima do normal."; //salvar no banco assim	 
	   String predClass = pred;  
	   System.out.println("Sua taxa de glicose está acima do normal."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } /* else if (analise.getPressao_arterialSistolica() < 120 and analise.getPressao_arterialDiastolica() < 80 ) {    	   
 	   	String pred = "PA Ótima"; //salvar no banco assim	 
        String predClass = pred;  
        System.out.println("Sua taxa de pressão arterial está ótima."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
    } else if (analise.getPressao_arterialSistolica() < 130 and analise.getPressao_arterialDiastolica() < 85 ) {    	   
	   	String pred = "PA normal"; //salvar no banco assim	 
        String predClass = pred;  
        System.out.println("Sua taxa de pressão arterial está normal."); //imprimir essa mensagem
		analise.setClassificacao(predClass);
	 } else if ((analise.getPressao_arterialSistolica() >= 130 and analise.getPressao_arterialSistolica() < 139) and 
			 (analise.getPressao_arterialDiastolica() >= 85 and analise.getPressao_arterialDiastolica() < 80 )) {    	   
	   	 String pred = "PA limitrofe"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está limitrofe."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
	 } else if ((analise.getPressao_arterialSistolica() >= 140 and analise.getPressao_arterialSistolica() < 159) and 
			 (analise.getPressao_arterialDiastolica() >= 90 and analise.getPressao_arterialDiastolica() < 99 )) {    	   
	   	 String pred = "PA Hipertensão estágio 1"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está Hipertensão estágio 1."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
	 } else if ((analise.getPressao_arterialSistolica() >= 169 and analise.getPressao_arterialSistolica() < 179) and 
			 (analise.getPressao_arterialDiastolica() >= 100 and analise.getPressao_arterialDiastolica() < 109 )) {    	   
	   	 String pred = "PA Hipertensão estágio 2"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está Hipertensão estágio 2."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
	 } else if ((analise.getPressao_arterialSistolica() >= 169 and analise.getPressao_arterialSistolica() < 179) and 
			 (analise.getPressao_arterialDiastolica() >= 100 and analise.getPressao_arterialDiastolica() < 109 )) {    	   
	   	 String pred = "PA Hipertensão estágio 2"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está Hipertensão estágio 2."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
	 } else if (analise.getPressao_arterialSistolica() >= 180 and analise.getPressao_arterialDiastolica() >= 110 ) {    	   
	   	 String pred = "PA Hipertensão estágio 2"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está Hipertensão estágio 2."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
	 } else if (analise.getPressao_arterialSistolica() >= 140 and analise.getPressao_arterialDiastolica() < 90 ) {    	   
	   	 String pred = "PA Hipertensão estágio 3"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está Hipertensão estágio 3."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
    }else if (analise.getPressao_arterialSistolica() >= 140 AND analise.getPressao_arterialDiastolica() < 90 ) {    	   
	   	 String pred = "PA Hipertensão estágio 2"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Sua taxa de pressão arterial está Hipertensão sistólica isolada."); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
    }else {    	   
	   	 String pred = "Inconclusivo"; //salvar no banco assim	 
	     String predClass = pred;  
	     System.out.println("Inconclusivo"); //imprimir essa mensagem
		 analise.setClassificacao(predClass);
   } */
	analise.setUsuario(usuario);
	analise.setDataCadastro(new Date());
	return analiseRepository.save(analise);
  }
     

	@Override
    public List<Analise> obterAnalisesDoUsuarioAutenticado() {
        Integer idUsuario = obterIdUsuarioAutenticado();
        return analiseRepository.obterAnalisesPorUsuario(idUsuario);
    }

    private Analise obterExamesUsuarioParaAnalise(Usuario usuario) {

        Analise analise = new Analise();

        analise.setDm(usuario.getDiabetico());
        analise.setHas(usuario.getHipertenso());
        analise.setCreatinina(obterResultadoExame(usuario.getId(), CREATININA));
        analise.setUreia(obterResultadoExame(usuario.getId(), UREIA));
        analise.setMicroalbuminaria(obterResultadoExame(usuario.getId(), MICROALBUMINURIA));
        analise.setIdade(usuario.getIdade());
        analise.setSexo(usuario.getSexo());
        analise.setTfg(obterResultadoExame(usuario.getId(), TFG));

        return analise;
    }

    private Double obterResultadoExame(Integer idUser, CodigoExame codigo) {
        String resultado = obterExameMaisRecente(idUser, codigo).getResultado();
        return Double.valueOf(resultado.replace(",", "."));
    }

    private Exame obterExameMaisRecente(Integer idUsuario, CodigoExame codigoExame){
        return exameRepository.obterExameMaisRecentePorUsuarioCodExame(codigoExame.getCodigo(), idUsuario);
    }
}
