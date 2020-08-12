package br.edu.ufersa.multcare.web.resources;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.multcare.controllers.cda.DocumentoClinico;
import br.edu.ufersa.multcare.model.bean.cda.Componentes;
import br.edu.ufersa.multcare.persistence.entities.Alergia;
import br.edu.ufersa.multcare.persistence.entities.Analise;
import br.edu.ufersa.multcare.persistence.entities.Medicamento;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import br.edu.ufersa.multcare.persistence.repositories.AnaliseRepository;
import br.edu.ufersa.multcare.persistence.repositories.ExameRepository;
import br.edu.ufersa.multcare.service.AlergiaService;
import br.edu.ufersa.multcare.service.AnaliseDeRiscoDeDRCService;
import br.edu.ufersa.multcare.service.EmailService;
import br.edu.ufersa.multcare.service.MedicamentoService;
import br.edu.ufersa.multcare.service.UsuarioService;
import br.edu.ufersa.multcare.service.impl.AnaliseDeRiscoDeDRCServiceImpl;
import cdapi.bean.Authenticator;
import cdapi.bean.Author;
import cdapi.bean.Component;
import cdapi.bean.Header;
import cdapi.bean.Patient;
import cdapi.bean.RelatedDocument;
import cdapi.bean.ResponsibleParty;
import cdapi.document.ClinicalDocument;


/**
 *
 * @author Gyovanne Cavalcanti
 */
@RestController
@RequestMapping(value="/api/cda")
public class CriacaoDocumentoClinicoResource extends DocumentoClinico {

    @Autowired
    ServletContext context;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/escrever", method = RequestMethod.GET)
    public String criarDocumentoClinico(ModelMap model) {
        model.addAttribute("titlePage", "MultCare Paciente - Criar");
        return "./escrever";
    }

    /**
     *
     *
     * @param model
     * @param cda
     * @param comp
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/escrever", method = RequestMethod.POST)
    public String criarDocumentoClinico(Usuario user, ModelMap model, ClinicalDocument cda, Componentes comp, Analise analise) throws IOException {
    	Integer idUsuario = obterIdUsuarioAutenticado();
    	
    	cda.setHeader(alterarDadosHeader(null, 1));
        cda.setAuthor(obterDadosMedico(user));
       // Arquivo a = new Arquivo();
       // a.setIdUsuarioArquivo(idUsuario);
        //a.setDataArquivo(dataAtual("dd/MMMM/yyyy"));
//        a.setNomeArquivo(user.getNome() + " | V" + getVersion());
      //  a.setNomeArquivo("teste" + " | V" );
        //  a.setFileArquivo(readFileToByteArray(gravarDadosDocumentoClinico(cda, comp)));
        // gravarDadosDocumentoClinico(cda, comp);
        
    	
        gravarDadosDocumentoClinico(cda,comp, analise);
       // a.setVersaoArquivo(getVersion());
  /*      new ArquivoDAO().adicionarDocumentoClinico(a);
        model.addAttribute("titlePage", "MultCare Paciente - Manipular");
        model.addAttribute("status", false);
        model.addAttribute("notificacao", "Documento ClÃ­nico criado com sucesso!");
        listarDocumentosClinicos(model, user, 1); */
        return "./ler";
    }

    /**
     *
     *
     * @param model
     * @param cda
     * @param componentes
     * @param file
     * @return
     * @throws IOException
     */
 
    private File gravarDadosDocumentoClinico(ClinicalDocument clinicalDocument, Componentes c,Analise analise) throws IOException {
        clinicalDocument.setHeader(clinicalDocument.getHeader());
        clinicalDocument.setPatient((clinicalDocument.getPatient()));
        clinicalDocument.setAuthor(clinicalDocument.getAuthor());
        clinicalDocument.setRelated(alterarDadosDocumentosRelacionados(clinicalDocument.getHeader()));
         clinicalDocument.setResponsibleParty((clinicalDocument.getResponsibleParty()));
         
        clinicalDocument.setAuthenticator(alterarDadosAutenticacao());
              
        ArrayList<cdapi.bean.Component> components = escreverComponentes(c, analise);
        
        if (components != null) {
            clinicalDocument.setComponents(components);
            System.out.print("entrou");
            
        }
        return clinicalDocument.toGenerateCDAFile();
    }

    private static boolean validaCampos(String info) {
        return !(info == null || info.equals("") || info.isEmpty());
    }

/*
    private AnaliseDeRiscoDeDRCServiceImpl analiseDeRiscoDeDRCServiceImpl;
    private AnaliseRepository analiseRepository;
	private ExameRepository exameRepository;
	private UsuarioService usuarioService;
	private EmailService emailService;
    
    public void AnaliseDeRiscoDeDRCServiceImpl(AnaliseRepository analiseRepository, ExameRepository exameRepository, UsuarioService usuarioService, EmailService emailService) {
        this.analiseRepository = analiseRepository;
        this.exameRepository = exameRepository;
        this.usuarioService = usuarioService;
        this.emailService = emailService;
    }

    
    public List<Analise> obterAnalisesDoUsuarioAutenticado() {
        Integer idUsuario = obterIdUsuarioAutenticado();
        return analiseRepository.obterAnalisesPorUsuario(idUsuario);
    }
*/
	@Autowired
	private MedicamentoService medicamentoService;
	
	@Autowired
	private AlergiaService alergiaService;

	@Autowired
	private AnaliseDeRiscoDeDRCService analiseDeRiscoDeDRCService;

	@Transactional
    private ArrayList<cdapi.bean.Component> escreverComponentes(Componentes c, Analise analise) {
       ArrayList<cdapi.bean.Component> components = new ArrayList<>();


       List<Medicamento> medicamentoList = medicamentoService.listarMedicamentoUsuarioLogado();
       if (medicamentoList != null) {
       	ArrayList<Object> medicamentos = new ArrayList<>();
           for (int i = 0; i < medicamentoList.size(); i++) {
           		medicamentos.add(medicamentoList.get(i).getNome());
           }          
            components.add(new cdapi.bean.Component("Medicamentos", medicamentos));
       }

       List<Alergia> alergiaList = alergiaService.listarAlergiaUsuarioLogado();
       if (alergiaList != null) {
       	ArrayList<Object> alergias = new ArrayList<>();
           for (int i = 0; i < alergiaList.size(); i++) {
        	   alergias.add(alergiaList.get(i).getNome());
           }          
            components.add(new cdapi.bean.Component("Alergia", alergias));
       }
       
       List<Analise> analiseList = analiseDeRiscoDeDRCService.obterUltimaAnalisesPorUsuario();
       if (analiseList != null) {
       	ArrayList<Object> analises = new ArrayList<>();
           for (int i = 0; i < analiseList.size(); i++) {
           	analises.add(analiseList.get(i).getCreatinina());
           }           
            components.add(new cdapi.bean.Component("Creatinina", analises));
       }
       
       if (analiseList != null) {
          	ArrayList<Object> analises = new ArrayList<>();
              for (int i = 0; i < analiseList.size(); i++) {
              	analises.add(analiseList.get(i).getUreia());
              }           
               components.add(new cdapi.bean.Component("Ureia", analises));
          }

       if (analiseList != null) {
          	ArrayList<Object> analises = new ArrayList<>();
              for (int i = 0; i < analiseList.size(); i++) {
              	analises.add(analiseList.get(i).getTfg());
              }           
               components.add(new cdapi.bean.Component("TFG", analises));
          }
          

       if (analiseList != null) {
          	ArrayList<Object> analises = new ArrayList<>();
              for (int i = 0; i < analiseList.size(); i++) {
              	analises.add(analiseList.get(i).getMicroalbuminaria());
              }           
               components.add(new cdapi.bean.Component("Microalbuminaria", analises));
          }
       if (analiseList != null) {
        	ArrayList<Object> analises = new ArrayList<>();
            for (int i = 0; i < analiseList.size(); i++) {
            	analises.add(analiseList.get(i).getClassificacao());
            }           
             components.add(new cdapi.bean.Component("Diagnostico", analises));
        }

       
        return components;
    }

    private static Author obterDadosMedico(Usuario user) {
        Author author = new Author();
        author.setAddr("enderecoUsuario");
        author.setFamily("sobrenomeUsuario");
        author.setCrm("crmUsuario");
        author.setName("nomeUsuario");
        author.setPhone("telefoneUsuario");
        return author;
    }

    private Patient alterarDadosPaciente(Patient p) {
        p.setBirth(validarData("13/11/1991", "dd/MM/yyyy", 0));
        p.setCodeSystem("2.16.840.1.113883.5.1");
        p.setIdExtension("M555");
        return p;
    }

    private RelatedDocument alterarDadosDocumentosRelacionados(Header h) {
        RelatedDocument related = new RelatedDocument();
        related.setCode(h.getCode());
        related.setExtension(h.getExtension());
        related.setID(h.getId());
        related.setVersion(h.getVersion());
        return related;
    }

    private ResponsibleParty alterarDadosResponsavel(ResponsibleParty r) {
        r.setDate(validarData(r.getDate(), "dd/MM/yyyy", 0));
        return r;
    }

    private Authenticator alterarDadosAutenticacao() {
        Authenticator authenticator = new Authenticator("S");
        return authenticator;
    }

    private Header alterarDadosHeader(Header header, int op) {
        Header h = new Header();
        h.setEfetiveTime(dataAtual("ddMMyyyy"));
        h.setDisplayName("MultCare Paciente");
        h.setRealmCode("UV");
        h.setIdRoot("M345");
        h.setExtension("2.16.840.1.113883.3.933");
        h.setCode("410.9");
        h.setCodeSystem("2.16.840.1.113883.6.103");
        h.setCodeSystemName("ICD-9-CM");
        h.setId("MM1");
        if (op == 1) {
            setVersion(1.0);
        } else {
            setVersion(alterarVersaoDocumento(header));
        }
        h.setVersion(getVersion());
        return h;
    }

    private static Double alterarVersaoDocumento(Header header) {
        String v = String.format("%.1f", header.getVersion() + 0.1);
        String replaceAll = v.replaceAll(",", ".");
        return Double.parseDouble(replaceAll);
    }

    private static String validarData(String data, String form, int op) {
        SimpleDateFormat formato = new SimpleDateFormat(form);
        SimpleDateFormat format;
        if (op == 1) {
            format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy HH:mm:ss");
        } else {
            format = new SimpleDateFormat("ddMMyyyy");
        }
        try {
            Date dataFormatada = formato.parse(data);
            return format.format(dataFormatada);
        } catch (ParseException ex) {
            return "00000000";
        }
    }

}
