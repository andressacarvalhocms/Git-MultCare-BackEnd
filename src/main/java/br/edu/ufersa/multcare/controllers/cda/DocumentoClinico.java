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

import org.cdapi.bean.Component;
import org.cdapi.document.ClinicalDocument;
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

    private File file;
    private int idArquivo;
    private int idUsuario;
    private double version;

    /**
     *
     * @return
     */
    public double getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(double version) {
        this.version = version;
    }

    /**
     *
     * @param file
     * @param idArquivo
     * @param idUsuario
     */
    public DocumentoClinico(File file, int idArquivo, int idUsuario) {
        this.file = file;
        this.idArquivo = idArquivo;
        this.idUsuario = idUsuario;
    }

    /**
     *
     */
    public DocumentoClinico() {
    }

    /**
     *
     * @return
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     *
     * @return
     */
    public int getIdArquivo() {
        return idArquivo;
    }

    /**
     *
     * @param idArquivo
     */
    public void setIdArquivo(int idArquivo) {
        this.idArquivo = idArquivo;
    }

    /**
     *
     * @return
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @param request
     * @param op
     * @return
     * @throws IOException
     */
    public File consultarDocumentoClinico(Usuario user, int op) throws IOException {
    	Integer idUsuario = obterIdUsuarioAutenticado();
    	
    	Arquivo arquivo = new ArquivoDAO().buscarDocumentoClinico(getIdArquivo(), idUsuario, op);
        setFile(convertByteToFile(arquivo.getFileArquivo()));
        return getFile();
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    /**
     *
     * @param formato
     * @return
     */
    public String dataAtual(String formato) {
        SimpleDateFormat format = new SimpleDateFormat(formato);
        Calendar today = Calendar.getInstance();
        return (format.format(today.getTime()));
    }

    /**
     *
     * @param arquivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public File convertByteToFile(byte[] arquivo) throws FileNotFoundException, IOException {
        byte[] bytes = arquivo;
        File f = new File("D:\\Mestrado\\DISSERTACAO GITHUB MULTCARE\\Git-MultCare-BackEnd\\DocumentoClÃ­nico.xml");
        try (OutputStream os = new FileOutputStream(f)) {
			os.write(bytes);
		}
        return f;
    }
    private ArrayList<Component> components;

    /**
     *
     * @return
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     *
     * @param components
     */
    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }
    
    /**
     *
     * @return
     */
    public ArrayList <Component> getListComponent(){
        return getComponents();
    }
    
    /**
     *
     * @param model
     * @param cda
     * @throws IOException
     */
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

    /**
     *
     * @param model
     * @param request
     * @param op
     */
    public void listarDocumentosClinicos(@Validated ModelMap model, Usuario user, int op) {
    	Integer idUsuario = obterIdUsuarioAutenticado();
    	
        model.addAttribute("documentos", new ArquivoDAO().listarDocumentosClinicos(idUsuario, op));
    }
}
