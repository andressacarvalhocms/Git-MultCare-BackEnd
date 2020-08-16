package br.edu.ufersa.multcare.service.impl;

import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.CREATININA;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.MICROALBUMINURIA;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.TFG;
import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.UREIA;
import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;
import static br.edu.ufersa.multcare.shared.baseaprendizagem.BaseAprendizagemSingleton.obterBaseAprendizagem;
import static java.util.Arrays.asList;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.CodigoExame;
import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.AnaliseRepository;
import br.edu.ufersa.multcare.persistence.repositories.ExameRepository;
import br.edu.ufersa.multcare.service.AnaliseDeRiscoDeDRCService;
import br.edu.ufersa.multcare.service.CriarDocumentoClinicoService;
import br.edu.ufersa.multcare.service.EmailService;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.web.resources.CriacaoDocumentoClinicoResource;
import cdapi.document.ClinicalDocument;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

@Component
public class AnaliseDeRiscoDeDRCServiceImpl implements AnaliseDeRiscoDeDRCService {

    private final AnaliseRepository analiseRepository;
    private final ExameRepository exameRepository;
    private UsuarioService usuarioService;
    private EmailService emailService;
	private CriarDocumentoClinicoService criarDocumentoClinicoService;
	
    
    public AnaliseDeRiscoDeDRCServiceImpl(AnaliseRepository analiseRepository, ExameRepository exameRepository, 
   		UsuarioService usuarioService, EmailService emailService) {
        this.analiseRepository = analiseRepository;
        this.exameRepository = exameRepository;
        this.usuarioService = usuarioService;
        this.emailService = emailService;
     //   this.criarDocumentoClinicoService = criarDocumentoClinicoService;
    }


    @Override
    @Transactional
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
      
        analise.setPred(pred);
        
        analise.setClassificacao(predClass);
        analise.setUsuario(usuario);
        analise.setDataCadastro(new Date());

        analise.invalidarExamesUtilizados();
        atualizarStatusExames(analise.getExames());

        analise = analiseRepository.save(analise);
  
        return analise;
    }

	@Override
    public List<Analise> obterAnalisesDoUsuarioAutenticado() {
        Integer idUsuario = obterIdUsuarioAutenticado();
        return analiseRepository.obterAnalisesPorUsuario(idUsuario);
    }

    private Analise obterExamesUsuarioParaAnalise(Usuario usuario) {

        Analise analise = new Analise();

        Exame exameCreatina = obterExameMaisRecente(usuario.getId(), CREATININA);
        Exame exameUreia = obterExameMaisRecente(usuario.getId(), UREIA);
        Exame exameMicroalbuminuria = obterExameMaisRecente(usuario.getId(), MICROALBUMINURIA);
        Exame exameTfg = obterExameMaisRecente(usuario.getId(), TFG);

        analise.setDm(usuario.getDiabetico());
        analise.setHas(usuario.getHipertenso());
        analise.setCreatinina(obterResultadoExame(exameCreatina));
        analise.setUreia(obterResultadoExame(exameUreia));
        analise.setMicroalbuminaria(obterResultadoExame(exameMicroalbuminuria));
        analise.setIdade(usuario.getIdade());
        analise.setSexo(usuario.getSexo());
        analise.setTfg(obterResultadoExame(exameTfg));
        analise.setExames(asList(exameCreatina, exameUreia, exameMicroalbuminuria, exameTfg));

        
            return analise;
    }

    private Double obterResultadoExame(Exame exame) {
        String resultado = exame.getResultado();
        return Double.valueOf(resultado.replace(",", "."));
    }

    private Exame obterExameMaisRecente(Integer idUsuario, CodigoExame codigoExame){
        return exameRepository.obterExameMaisRecentePorUsuarioCodExame(codigoExame.getCodigo(), idUsuario);
    }

    private void atualizarStatusExames(List<Exame> exames) {
        exameRepository.saveAll(exames);
    }


	@Override
	public List<Analise> obterUltimaAnalisesPorUsuario() {
        Integer idUsuario = obterIdUsuarioAutenticado();
        return analiseRepository.obterUltimaAnalisesPorUsuario(idUsuario);
	}
}
