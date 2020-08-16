package br.edu.ufersa.multcare.model.bean.cda;

import org.springframework.web.multipart.MultipartFile;

public class ArquivoModelo {

    private MultipartFile file;

    /**
     *
     * @return
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
