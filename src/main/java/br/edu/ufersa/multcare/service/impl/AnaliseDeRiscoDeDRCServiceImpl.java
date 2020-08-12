package br.edu.ufersa.multcare.service.impl;

import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.CodigoExame;
import br.edu.ufersa.multcare.persistence.entities.Exame;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.AnaliseRepository;
import br.edu.ufersa.multcare.persistence.repositories.ExameRepository;
import br.edu.ufersa.multcare.service.AnaliseDeRiscoDeDRCService;
import br.edu.ufersa.multcare.service.EmailService;
import br.edu.ufersa.multcare.service.UsuarioService;

import org.springframework.stereotype.Component;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static br.edu.ufersa.multcare.persistence.entities.CodigoExame.*;
import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;
import static br.edu.ufersa.multcare.shared.baseaprendizagem.BaseAprendizagemSingleton.obterBaseAprendizagem;
import static java.util.Arrays.asList;

@Component
public class AnaliseDeRiscoDeDRCServiceImpl implements AnaliseDeRiscoDeDRCService {

    private final AnaliseRepository analiseRepository;
    private final ExameRepository exameRepository;
    private UsuarioService usuarioService;
    private EmailService emailService;

    public AnaliseDeRiscoDeDRCServiceImpl(AnaliseRepository analiseRepository, ExameRepository exameRepository, UsuarioService usuarioService, EmailService emailService) {
        this.analiseRepository = analiseRepository;
        this.exameRepository = exameRepository;
        this.usuarioService = usuarioService;
        this.emailService = emailService;
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
 
        analise.setClassificacao(predClass);
        analise.setUsuario(usuario);
        analise.setDataCadastro(new Date());
        analise = analiseRepository.save(analise);

        analise.invalidarExamesUtilizados();
        atualizarStatusExames(analise.getExames());

        // TODO Montar conteudo do email.
        String exames = "Exames utilizados: <br> Creatina: " +
                analise.getCreatinina() + " mg/DL <br> Uréia: " +
                analise.getUreia() + " mg/DL <br> Microalbuminaria: " +
                analise.getMicroalbuminaria() + " mmHg <br> TFG: " +
                analise.getTfg() + " mL/min/1,73 m² <br><br>";
        String resultado = "O resultado da análise é: " + analise.getClassificacao();
		emailService.enviarEmail(usuario.getLogin(), exames.concat(resultado));
		
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
