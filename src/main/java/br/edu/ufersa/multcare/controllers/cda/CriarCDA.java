package br.edu.ufersa.multcare.controllers.cda;

public class CriarCDA extends CriadorDocumentoCDA {

	@Override
	protected void gerarIDDocumento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void gerarRTDocumento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setConfiabilidade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setCDTipoDocumento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDataAutenticacao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDataCriacao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setOrganizacao() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTelefone() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setNomeSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setInformacaoPaciente() {
		// TODO Auto-generated method stub
		
	}
	
	/*private Context context;
	private int idUsuario;
	private UsuarioModelo usuarioModel;
	
	public CriarCDA(Context context, int idUsuario) {
		
		cdaModel = new CDAModel();
		this.context = context;
		this.idUsuario = idUsuario;
		UsuarioControle usuarioControl = new UsuarioControle(this.context);
		usuarioModel = usuarioControl.consultar(this.idUsuario);
		
		gerarIDDocumento();
		gerarRTDocumento();
		setConfiabilidade();
		setCDTipoDocumento();
		setDataAutenticacao();
		setDataCriacao();
		setTelefone();
		setNomeSistema();
		setInformacaoPaciente();
	}

	@Override
	protected void gerarIDDocumento() {
		// TODO Auto-generated method stub
		super.cdaModel.setIdDocumento("a123");
	}

	@Override
	protected void gerarRTDocumento() {
		// TODO Auto-generated method stub
		super.cdaModel.setRtDocumento("2.16.840.1.113883.3.933");
	}

	@Override
	protected void setConfiabilidade() {
		// TODO Auto-generated method stub
		super.cdaModel.setConfidencialidadeDocumento("CONF1");
	}

	@Override
	protected void setCDTipoDocumento() {
		// TODO Auto-generated method stub
		super.cdaModel.cdTipoDocumento.setCodigo("11488-1");
	}

	@Override
	protected void setDataAutenticacao() {
		// TODO Auto-generated method stub
		super.cdaModel.setDataAutenticacaoLegal("99/99/9999");
	}

	@Override
	protected void setDataCriacao() {
		// TODO Auto-generated method stub
		super.cdaModel.setDataCriacao("55/55/5555");
	}

	@Override
	protected void setOrganizacao() {
		// TODO Auto-generated method stub
		super.cdaModel.setOrganizacao("COMPE");
		super.cdaModel.setTipoOriginacao("AUT");
	}

	@Override
	protected void setTelefone() {
		// TODO Auto-generated method stub
		super.cdaModel.setTelefone("tel: (82)5555-5555");
	}

	@Override
	protected void setNomeSistema() {
		// TODO Auto-generated method stub
		super.cdaModel.setNomeSistema("MultCare");
	}

	@Override
	protected void setInformacaoPaciente() {
		// TODO Auto-generated method stub
		
		String nome = usuarioModel.getNome();
		super.cdaModel.paciente.setNome(nome);
		String dataAniversario = String.valueOf(99) + "/" + String.valueOf(99) + "/" + String.valueOf(2012 - usuarioModel.getIdade());
		super.cdaModel.paciente.setDataAniversario(dataAniversario);
		String sexo = usuarioModel.getSexo();
		super.cdaModel.paciente.setSexo(sexo);
		
		//Adicao do historico da doenca atual
		super.cdaModel.paciente.addHistoricoDoenca(nome
				+ " � um homem de "
				+ String.valueOf(usuarioModel.getIdade())
				+ " anos encaminhado para o monitoramento para risco de desenvolvimento de Doen�a Renal Cr�nica.  O paciente possui quadro cl�nico de Diabetes Mellitus e  Hipertens�o Arterial.");

		//Adicao do historico medico
		
		ListaModel l1 = new ListaModel();
		l1.setDescricao("DM");
		ListaModel l2 = new ListaModel();
		l2.setDescricao("HAS");
		super.cdaModel.paciente.addHistoricoMedico(l1);		
		super.cdaModel.paciente.addHistoricoMedico(l2);
		
		//Adicao das medicacoes
		MedicamentoControle medicamentoControl = new MedicamentoControle(context);
		ArrayList<MedicamentoModelo> listMedicamentoModel = medicamentoControl
				.consultarTodos(idUsuario);

		for (int i = 0; i < listMedicamentoModel.size(); i++) {
			ListaModel listaaModel = new ListaModel();
			listaaModel.setTitulo(listMedicamentoModel.get(i).getNome());
			listaaModel.setDescricao(listMedicamentoModel.get(i).getTipo());
			super.cdaModel.paciente.addMedicacao(listaaModel);
		}
		
		//Adicao das alergias
		AlergiaControle alergiaControl = new AlergiaControle(context);
		ArrayList<AlergiaModelo> listAlergiaModel = alergiaControl
				.consultarTodos(idUsuario);

		for (int i = 0; i < listAlergiaModel.size(); i++) {
			ListaModel cdaModel = new ListaModel();
			cdaModel.setDescricao(listAlergiaModel.get(i).getNome());
			super.cdaModel.paciente.addAlergia(cdaModel);
		}
		
		//Adicao de exames fisicos
		ExameFisico exameFisico = new ExameFisico();
		
		exameFisico.setTitulo("Sinais Vitais");
		
		ListaModel listaModel1 = new ListaModel();
		listaModel1.setTitulo("PAS");
		listaModel1.setDescricao("PAS");
		ListaModel listaModel2 = new ListaModel();
		listaModel2.setTitulo("PAD");
		listaModel2.setDescricao("PAD");
		ListaModel listaModel3 = new ListaModel();
		listaModel3.setTitulo("Gli");
		listaModel3.setDescricao("Glicose");
		ListaModel listaModel4 = new ListaModel();
		listaModel4.setTitulo("P");
		listaModel4.setDescricao("Peso");
		
		exameFisico.addExame(listaModel1);
		exameFisico.addExame(listaModel2);
		exameFisico.addExame(listaModel3);
		exameFisico.addExame(listaModel4);
		
		super.cdaModel.paciente.addExameFisico(exameFisico);
		
		exameFisico = new ExameFisico();
		exameFisico.setTitulo("Testes");
	
		ExameControle exameControl = new ExameControle(context);
		ArrayList<ExameModelo> listExameModel = exameControl
				.consultarTodos(idUsuario);

		for (int i = 0; i < listExameModel.size(); i++) {
			ListaModel listaModel = new ListaModel();
			listaModel.setTitulo(listExameModel.get(i).getNome());
			listaModel.setDescricao(listExameModel.get(i).getResultado());
			
			exameFisico.addExame(listaModel);			
		}
		super.cdaModel.paciente.addExameFisico(exameFisico);
		
		
		//Adicao das avaliacoes feitas
		ListaModel lM1 = new ListaModel();
		ListaModel lM2 = new ListaModel();
		ListaModel lM3 = new ListaModel();
		ListaModel lM4 = new ListaModel();
	
		lM1.setDescricao("Poss�vel est�gio 1 da Doen�aa Renal Cr�nica");
		lM2.setDescricao("Taxas dos teste de creatinina, ur�ia, pot�ssio, e albumin�ria est�o anormais.");
		lM3.setDescricao("A press�o arterial est� controlada.");
		lM4.setDescricao("A taxa de glicose esta controlada.");
		super.cdaModel.paciente.addAvaliacao(lM1);
		super.cdaModel.paciente.addAvaliacao(lM2);
		super.cdaModel.paciente.addAvaliacao(lM3);
		super.cdaModel.paciente.addAvaliacao(lM4);		
		
	}*/

}