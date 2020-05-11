package br.edu.ufersa.multcare.controllers.cda;

/**
 * 
 * @author Daniel San
 * Classe para gerar todas as informacoes necessarias para a criacao
 * do CDAModel e conseguentemente para a geracao do documento CDA.
 *
 */
public abstract class CriadorDocumentoCDA {
	
	// protected CDAModel cdaModel;
	private String documento;
	
	/**
	 * 
	 */
	public void criarDocumento() {
		// GerarCDA gerarCDA = new GerarCDA();
		// documento = gerarCDA.gerar(cdaModel);
	}
	
	/**
	 * 
	 * @return Um objeto CDAModel.
	 */
	public Object getCDAModel() {
		return null;
		// return cdaModel;
	}
	
	/**
	 * 
	 * @return Uma String contendo o documento CDA em XML.
	 */
	public String getDocumento() {
		return documento;
	}
	
	/**
	 * 
	 */
	protected abstract void gerarIDDocumento();
	
	/**
	 * 
	 */
	protected abstract void gerarRTDocumento();
	
	/**
	 * 
	 */
	protected abstract void setConfiabilidade();
	
	/**
	 * 
	 */
	protected abstract void setCDTipoDocumento();
	
	/**
	 * 
	 */
	protected abstract void setDataAutenticacao();
	
	/**
	 * 
	 */
	protected abstract void setDataCriacao();
	
	/**
	 * 
	 */
	protected abstract void setOrganizacao();
	
	/**
	 * 
	 */
	protected abstract void setTelefone();
	
	/**
	 * 
	 */
	protected abstract void setNomeSistema();
	
	/**
	 * Deve Preencher todas as informacoes do paciente.
	 */
	protected abstract void setInformacaoPaciente();

}
