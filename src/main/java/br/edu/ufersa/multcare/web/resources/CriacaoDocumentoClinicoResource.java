package br.edu.ufersa.multcare.web.resources;

import br.edu.ufersa.multcare.controllers.cda.DocumentoClinico;
import br.edu.ufersa.multcare.model.bean.cda.Arquivo;
import br.edu.ufersa.multcare.model.bean.cda.Componentes;
import br.edu.ufersa.multcare.model.dao.cda.ArquivoDAO;
import br.edu.ufersa.multcare.persistence.entities.Usuario;
import cdapi.bean.*;
import cdapi.document.ClinicalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;
import static org.apache.commons.io.FileUtils.readFileToByteArray;


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
    public String criarDocumentoClinico(Usuario user, ModelMap model, ClinicalDocument cda, Componentes comp) throws IOException {
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
        
    	
        gravarDadosDocumentoClinico(cda,comp);
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
 
    private File gravarDadosDocumentoClinico(ClinicalDocument clinicalDocument, Componentes c) throws IOException {
        clinicalDocument.setHeader(clinicalDocument.getHeader());
        clinicalDocument.setPatient((clinicalDocument.getPatient()));
        clinicalDocument.setAuthor(clinicalDocument.getAuthor());
        clinicalDocument.setRelated(alterarDadosDocumentosRelacionados(clinicalDocument.getHeader()));
         clinicalDocument.setResponsibleParty((clinicalDocument.getResponsibleParty()));
         
        clinicalDocument.setAuthenticator(alterarDadosAutenticacao());
              
        ArrayList<cdapi.bean.Component> components = escreverComponentes(c);
        
        if (components != null) {
            clinicalDocument.setComponents(components);
            System.out.print("entrou");
            
        }
        return clinicalDocument.toGenerateCDAFile();
    }

    private static boolean validaCampos(String info) {
        return !(info == null || info.equals("") || info.isEmpty());
    }

    private ArrayList<cdapi.bean.Component> escreverComponentes(Componentes c) {
        ArrayList<cdapi.bean.Component> components = new ArrayList<>();
        if (c.getExameslaboratoriaisfixos() != null) {
            ArrayList<Object> exameslaboratoriais = new ArrayList<>();
            for (int i = 0; i < c.getExameslaboratoriaisfixos().size(); i++) {
                exameslaboratoriais.add(c.getExameslaboratoriaisfixos().get(i).getNome());
            }
            if (c.getExameslaboratoriais() != null) {
                for (int i = 0; i < c.getExameslaboratoriais().size(); i++) {
                    if (validaCampos(c.getExameslaboratoriais().get(i).getNome().toString())) {
                        exameslaboratoriais.add(c.getExameslaboratoriais().get(i).getNome().toString());
                    }
                }
            }
            System.out.print("entrou");
            components.add(new cdapi.bean.Component("Exames Laboratoriais", exameslaboratoriais));
        }
        if (c.getDiagnostico() != null) {
            ArrayList<Object> diagnostico = new ArrayList<>();
            for (int i = 0; i < c.getDiagnostico().size(); i++) {
                diagnostico.add(c.getDiagnostico().get(i).getClassificacao());
            }
            components.add(new cdapi.bean.Component("Diagnostico da DRC", diagnostico));
        }
    /*    if (c.getAlergias() != null) {
            ArrayList<Object> alergias = new ArrayList<>();
            for (int i = 0; i < c.getAlergias().size(); i++) {
                if (validaCampos(c.getAlergias().get(i).getNome().toString())) {
                    alergias.add(c.getAlergias().get(i).getNome());
                }
            }
            components.add(new cdapi.bean.Component("Alergias", alergias));
        }
      */  if (c.getMedicamentos() != null) {
            ArrayList<Object> medicamentos = new ArrayList<>();
            for (int i = 0; i < c.getMedicamentos().size(); i++) {
                if (validaCampos(c.getMedicamentos().get(i).getNome().toString())) {
                    medicamentos.add(c.getMedicamentos().get(i).getNome());
                }
            }
            components.add(new cdapi.bean.Component("Medicamentos", medicamentos));
        }

        return components;
    }

    private static Author obterDadosMedico(Usuario user) {
        Author author = new Author();
        author.setName(user.getNomeMedico());
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
