package br.edu.ufersa.multcare.model.bean.cda;

public class Email {

    private String nomeEmail;
    private String emailEmail;
    private String mensagemEmail;
    private byte[] arquivoEmail;

    /**
     *
     */
    public Email() {
    }

    /**
     *
     * @param nomeEmail
     * @param emailEmail
     * @param mensagemEmail
     * @param arquivoEmail
     */
    public Email(String nomeEmail, String emailEmail, String mensagemEmail, byte[] arquivoEmail) {
        this.nomeEmail = nomeEmail;
        this.emailEmail = emailEmail;
        this.mensagemEmail = mensagemEmail;
        this.arquivoEmail = arquivoEmail;
    }

    /**
     *
     * @return
     */
    public String getNomeEmail() {
        return nomeEmail;
    }

    /**
     *
     * @param nomeEmail
     */
    public void setNomeEmail(String nomeEmail) {
        this.nomeEmail = nomeEmail;
    }

    /**
     *
     * @return
     */
    public String getEmailEmail() {
        return emailEmail;
    }

    /**
     *
     * @param emailEmail
     */
    public void setEmailEmail(String emailEmail) {
        this.emailEmail = emailEmail;
    }

    /**
     *
     * @return
     */
    public String getMensagemEmail() {
        return mensagemEmail;
    }

    /**
     *
     * @param mensagemEmail
     */
    public void setMensagemEmail(String mensagemEmail) {
        this.mensagemEmail = mensagemEmail;
    }

    /**
     *
     * @return
     */
    public byte[] getArquivoEmail() {
        return arquivoEmail;
    }

    /**
     *
     * @param arquivoEmail
     */
    public void setArquivoEmail(byte[] arquivoEmail) {
        this.arquivoEmail = arquivoEmail;
    }

}
