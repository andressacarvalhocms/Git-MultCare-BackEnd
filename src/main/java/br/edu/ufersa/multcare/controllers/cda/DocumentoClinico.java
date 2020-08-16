package br.edu.ufersa.multcare.controllers.cda;


import static br.edu.ufersa.multcare.security.SecurityUtils.obterIdUsuarioAutenticado;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import cdapi.bean.Componente;
import cdapi.document.ClinicalDocument;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import br.edu.ufersa.multcare.model.bean.cda.Arquivo;
import br.edu.ufersa.multcare.model.dao.cda.ArquivoDAO;
import br.edu.ufersa.multcare.persistence.entities.Usuario;

/**
 *
 * @author Gyovanne Cavalcanti
 */
@Controller
public class DocumentoClinico {

    protected File file;
    private int idArquivo;
    private int idUsuario;
    private double version;

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public DocumentoClinico(File file, int idArquivo, int idUsuario) {
        this.file = file;
        this.idArquivo = idArquivo;
        this.idUsuario = idUsuario;
    }

    public DocumentoClinico() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getIdArquivo() {
        return idArquivo;
    }
    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public File consultarDocumentoClinico(Usuario user, int op) throws IOException {
    	Integer idUsuario = obterIdUsuarioAutenticado();
    	
    	Arquivo arquivo = new ArquivoDAO().buscarDocumentoClinico(getIdArquivo(), idUsuario, op);
        setFile(convertByteToFile(arquivo.getFileArquivo()));
        return getFile();
    }
    public File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
    public String dataAtual(String formato) {
        SimpleDateFormat format = new SimpleDateFormat(formato);
        Calendar today = Calendar.getInstance();
        return (format.format(today.getTime()));
    }

    public File convertByteToFile(byte[] arquivo) throws FileNotFoundException, IOException {
        byte[] bytes = arquivo;
        File f = new File("D:\\Mestrado\\DISSERTACAO GITHUB MULTCARE\\Git-MultCare-BackEnd\\DocumentoClÃ­nico.xml");
        try (OutputStream os = new FileOutputStream(f)) {
			os.write(bytes);
		}
        return f;
    }
    private ArrayList<Componente> components;

    public ArrayList<Componente> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Componente> components) {
        this.components = components;
    }

public ArrayList <Componente> getListComponent(){
        return getComponents();
    }
    

    public void done(ModelMap model, ClinicalDocument cda) throws IOException {
        model.addAttribute("cabecalho", cda.getHeader());
        model.addAttribute("patient", cda.getPatient());
        model.addAttribute("author", cda.getAuthor());
        model.addAttribute("related", cda.getRelated());
        model.addAttribute("responsibleParty", cda.getResponsibleParty());
        model.addAttribute("authenticator", cda.getAuthenticator());
        setComponents(cda.getComponents());
        model.addAttribute("component", cda.getComponents());
    }

    public void listarDocumentosClinicos(@Validated ModelMap model, Usuario user, int op) {
    	Integer idUsuario = obterIdUsuarioAutenticado();
    	
        model.addAttribute("documentos", new ArquivoDAO().listarDocumentosClinicos(idUsuario, op));
    }
}
