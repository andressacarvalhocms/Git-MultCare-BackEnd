package br.edu.ufersa.multcare.controllers.cda;

/**
 * 
 * @author Daniel San
 *
 */

public class GerarCDA {

	/*private String documento;
	private XmlSerializer serializer = Xml.newSerializer();
	private StringWriter stringWriter = new StringWriter();
	private final String TAG = "gerarXmlCdaTest";
	private CDAModel cdaModel;

//	public GerarCDA(Context context, CDAModel cdaModel) {
//		this.context = context;
//	}

	public String gerar(CDAModel cdaModel) {
		
		this.cdaModel = cdaModel;
		try {
			serializer.setOutput(stringWriter);
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "levelone");

			gerarCabecalho();
			gerarCorpo();

			serializer.endTag(null, "levelone");

			serializer.endDocument();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}
		
		documento = stringWriter.toString();
		return documento;
	}
	
	public String getDocumentoCDA() {
		return documento;
	}

	private void gerarCabecalho() {
		try {
			serializer.startTag(null, "clinical_document_header");

			serializer.startTag(null, "id").attribute(null, "EX", cdaModel.getIdDocumento())
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "set_id").attribute(null, "EX", "B")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "set_id");
			serializer.startTag(null, "version_nbr").attribute(null, "V", "2");
			serializer.endTag(null, "version_nbr");
			serializer.startTag(null, "document_type_cd")
					.attribute(null, "V", cdaModel.cdTipoDocumento.getCodigo())
					.attribute(null, "S", "LOINC")
					.attribute(null, "DN", cdaModel.cdTipoDocumento.getDescricao());
			serializer.endTag(null, "document_type_cd");
			serializer.startTag(null, "origination_dttm").attribute(null, "V",
					"6-6-2012");
			serializer.endTag(null, "origination_dttm");
			serializer.startTag(null, "confidentiality_cd")
					.attribute(null, "ID", "CONF1").attribute(null, "V", "N")
					.attribute(null, "S", "HL7_Confidentiality");
			serializer.endTag(null, "confidentiality_cd");
			serializer.startTag(null, "confidentiality_cd")
					.attribute(null, "ID", "CONF2").attribute(null, "V", "R")
					.attribute(null, "S", "HL7_Confidentiality");
			serializer.endTag(null, "confidentiality_cd");

			gerarRelacao();
			gerarAssinatura();
			gerarOriginador();
			gerarOrganizacaoOriginador();
			gerarPaciente();
			gerarOrinadorDevice();
			gerarLocalHeader();

			serializer.endTag(null, "clinical_document_header");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void gerarCorpo() {
		try {
			serializer.startTag(null, "body").attribute(null,
					"confidentiality", "CONF1");
			gerarHistoricoDoenca("Hist�ria da Doen�a Atual");
			gerarHistoricoMedico("Hist�ria Medico");
			gerarMedicacoes("Medica��es");
			gerarAlergias("Alergias");
			gerarHistoricoSocial("Hist�rico Social");
			gerarExamesFisicos("Exames F�sicos");
			gerarAvaliacao();

			serializer.endTag(null, "body");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void gerarRelacao() {
		try {
			serializer.startTag(null, "document_relationship");
			serializer.startTag(null, "document_relationship.type_cd")
					.attribute(null, "V", "RPLC");
			serializer.endTag(null, "document_relationship.type_cd");
			serializer.startTag(null, "related_document");
			serializer.startTag(null, "id").attribute(null, "EX", "a123")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "set_id").attribute(null, "EX", "B")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "set_id");
			serializer.startTag(null, "version_nbr").attribute(null, "V", "1");
			serializer.endTag(null, "version_nbr");
			serializer.endTag(null, "related_document");
			serializer.endTag(null, "document_relationship");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	private void gerarOriginador() {

		try {
			serializer.startTag(null, "originator");
			serializer.startTag(null, "originator.type_cd").attribute(null,
					"V", cdaModel.getTipoOriginacao());
			serializer.endTag(null, "originator.type_cd");
			serializer.startTag(null, "participation_tmr").attribute(null, "V",
					cdaModel.getDataCriacao());
			serializer.endTag(null, "participation_tmr");
			serializer.startTag(null, "person");
			serializer.startTag(null, "id").attribute(null, "EX", "KP00017")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "name");
			serializer.text(cdaModel.getNomeSistema());
			serializer.endTag(null, "name");
			serializer.endTag(null, "person");
			serializer.endTag(null, "originator");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	private void gerarOrganizacaoOriginador() {
		try {
			serializer.startTag(null, "originating_organization");
			serializer.startTag(null, "originating_organization.type_cd")
					.attribute(null, "V", "CST");
			serializer.endTag(null, "originating_organization.type_cd");
			serializer.startTag(null, "organization");
			serializer.startTag(null, "id").attribute(null, "EX", "M345")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "organization.nm").attribute(null, "V",
					cdaModel.getOrganizacao());
			serializer.endTag(null, "organization.nm");
			serializer.endTag(null, "organization");
			serializer.endTag(null, "originating_organization");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	// por enquanto no nome e sobrenome deixa como ta que vou alterar na
	// aplicação pra no cadastro ser informado e nome e o sobrenome
	private void gerarPaciente() {
		try {
			serializer.startTag(null, "patient");
			serializer.startTag(null, "patient.type_cd").attribute(null, "V",
					"PATSBJ");
			serializer.endTag(null, "patient.type_cd");
			serializer.startTag(null, "person");
			serializer.startTag(null, "id").attribute(null, "EX", "12345")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "name");
			serializer.text(cdaModel.paciente.getNome());
			serializer.endTag(null, "name");
			serializer.endTag(null, "person");
			// aqui vou ver como faço pra preencher, deixa vazio
			serializer.startTag(null, "birth_dttm").attribute(null, "V", cdaModel.paciente.getDataAniversario());
			serializer.endTag(null, "birth_dttm");
			serializer.startTag(null, "administrative_gender_cd")
					.attribute(null, "V", cdaModel.paciente.getSexo())
					.attribute(null, "S", "HL7_AdministrativeGender");
			serializer.endTag(null, "administrative_gender_cd");
			serializer.endTag(null, "patient");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	private void gerarOrinadorDevice() {
		try {
			serializer.startTag(null, "originating_device").attribute(null,
					"ID", "DEV1");
			serializer.startTag(null, "originating_device.type_cd").attribute(
					null, "V", "ODV");
			serializer.endTag(null, "originating_device.type_cd");
			serializer.startTag(null, "device");
			serializer.startTag(null, "id").attribute(null, "EX", "devX3498")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "responsibility");
			serializer.startTag(null, "responsibility.type_cd")
					.attribute(null, "V", "MNT")
					.attribute(null, "S", "HL7_RelationshipResponsibility");
			serializer.endTag(null, "responsibility.type_cd");
			serializer.startTag(null, "person");
			serializer.startTag(null, "id").attribute(null, "EX", "KP03257")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.endTag(null, "person");
			serializer.endTag(null, "responsibility");
			serializer.endTag(null, "device");
			serializer.endTag(null, "originating_device");

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	// talvez isso sirva para alguma coisa
	private void gerarLocalHeader() {
		try {
			serializer.startTag(null, "local_header")
					.attribute(null, "ignore", "all")
					.attribute(null, "descriptor", "MyLocalTag");
			serializer.text("Material extra que s�o usado localmente");
			serializer.endTag(null, "local_header");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	private void gerarHistoricoDoenca(String titulo) {
		try {
			serializer.startTag(null, "section");
			serializer.startTag(null, "caption");
			serializer.startTag(null, "caption_cd")
					.attribute(null, "V", "10164-2")
					.attribute(null, "S", "LOINC");
			serializer.endTag(null, "caption_cd");
			serializer.text(titulo);
			serializer.endTag(null, "caption");
			serializer.startTag(null, "paragraph");
			serializer.startTag(null, "content");
			for(String historico : cdaModel.paciente.getHistoricoDoenca()) {
				serializer.text(historico);
			}
			serializer.endTag(null, "content");
			serializer.endTag(null, "paragraph");
			serializer.endTag(null, "section");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}
	}

	private void gerarHistoricoMedico(String titulo) {
		
		ArrayList<ListaModel> arrayListaModel = cdaModel.paciente.getHistoricoMedico();
		if(arrayListaModel.size() != 0)
			gerarLista(titulo, null, arrayListaModel);

		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// Log.i(TAG, e.getMessage());
		// }

	}

	// aqui vou ver como faço ainda
	private void gerarHistoricoSocial(String titulo) {

		ArrayList<ListaModel> arrayListaModel = new ArrayList<ListaModel>();
		ListaModel listaModel1 = new ListaModel();
		listaModel1.setDescricao("Fumante");
		ListaModel listaModel2 = new ListaModel();
		listaModel2.setDescricao("Bebe");
		
		arrayListaModel.add(listaModel1);
		arrayListaModel.add(listaModel2);

		if (arrayListaModel.size() != 0)

			gerarLista(titulo, null, arrayListaModel);

		
	}

	private void gerarMedicacoes(String titulo) {

		ArrayList<ListaModel> arrayListaModel = cdaModel.paciente.getMedicacoes();
		if (arrayListaModel.size() != 0)
			gerarLista(titulo, null, arrayListaModel);

		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// Log.i(TAG, e.getMessage());
		// }
	}

	private void gerarAlergias(String titulo) {

		ArrayList<ListaModel> arrayListaModel = cdaModel.paciente.getAlergias();
		if (arrayListaModel.size() != 0)
			gerarLista(titulo, null, arrayListaModel);

		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// Log.i(TAG, e.getMessage());
		// }

	}

	private void gerarExamesFisicos(String titulo) {

		ArrayList<ExameFisico> listaExameFisico = cdaModel.paciente.getExamesFisicos();
		
		if(listaExameFisico.size() != 0) {
			try {
				serializer.startTag(null, "section");
				serializer.startTag(null, "caption");
				serializer.startTag(null, "caption_cd")
						.attribute(null, "V", "11384-5")
						.attribute(null, "S", "LOINC");
				serializer.endTag(null, "caption_cd");
				serializer.text(titulo);
				serializer.endTag(null, "caption");

				for (ExameFisico exameFisico : listaExameFisico) {
					ArrayList<ListaModel> arrayListaModel = exameFisico
							.getExames();
					if (arrayListaModel.size() != 0)
						gerarLista(exameFisico.getTitulo(),
								exameFisico.getCodigoLoinc(), arrayListaModel);
				}

				serializer.endTag(null, "section");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.i(TAG, e.getMessage());
			}
		}
	}

	// este aqui deixa assim mesmo porque não foi implementado ainda na
	// aplicação
	private void gerarAvaliacao() {
		
		ArrayList<ListaModel> arrayListaModel = cdaModel.paciente.getAvaliacao();

		if (arrayListaModel.size() != 0)

			gerarLista("Avalia��o", "11496-7", arrayListaModel);

		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// Log.i(TAG, e.getMessage());
		// }
	}

	// vai colocando os parametros que faltam
	private void gerarAssinatura() {
		try {
			serializer.startTag(null, "legal_authenticator");
			serializer.startTag(null, "legal_authenticator.type_cd").attribute(
					null, "V", "SPV");
			serializer.endTag(null, "legal_authenticator.type_cd");
			serializer.startTag(null, "legal_authenticator.tmr").attribute(
					null, "V", cdaModel.getDataAutenticacaoLegal());
			serializer.endTag(null, "legal_authenticator.tmr");
			serializer.startTag(null, "signature_cd").attribute(null, "V", "S");
			serializer.endTag(null, "signature_cd");
			serializer.startTag(null, "person");
			serializer.startTag(null, "id").attribute(null, "EX", "KP00017")
					.attribute(null, "RT", cdaModel.getRtDocumento());
			serializer.endTag(null, "id");
			serializer.startTag(null, "name");
			serializer.text(cdaModel.getNomeSistema());
			serializer.endTag(null, "name");
			serializer.startTag(null, "addr");
			serializer.startTag(null, "HNR").attribute(null, "V", "970");
			serializer.endTag(null, "HNR");
			serializer.startTag(null, "STR").attribute(null, "V", "Post ST");
			serializer.endTag(null, "STR");
			serializer.startTag(null, "DIR").attribute(null, "V", "NE");
			serializer.endTag(null, "DIR");
			serializer.startTag(null, "CTY").attribute(null, "V", "Alameda");
			serializer.endTag(null, "CTY");
			serializer.startTag(null, "STA").attribute(null, "V", "CA");
			serializer.endTag(null, "STA");
			serializer.startTag(null, "ZIP").attribute(null, "V", "94501");
			serializer.endTag(null, "ZIP");
			serializer.endTag(null, "addr");
			serializer.startTag(null, "telecom")
					.attribute(null, "V", cdaModel.getTelefone())
					.attribute(null, "USE", "WPN");
			serializer.endTag(null, "telecom");
			serializer.endTag(null, "person");
			serializer.endTag(null, "legal_authenticator");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}
	}

	
//	private void gerarTextoLivre(String nomeTag, String valorTag) {
//
//		try {
//			serializer.startTag(null, "section");
//			serializer.startTag(null, "caption");
//			serializer.startTag(null, "caption_cd").attribute(null, "V", " ")
//					.attribute(null, "S", " ");
//			serializer.endTag(null, "caption_cd");
//			serializer.text(nomeTag);
//			serializer.endTag(null, "caption");
//			serializer.startTag(null, "paragraph");
//			serializer.startTag(null, "content");
//			serializer.text(valorTag);
//			serializer.endTag(null, "content");
//			serializer.endTag(null, "paragraph");
//			serializer.endTag(null, "section");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			Log.i(TAG, e.getMessage());
//		}
//
//	}

	private void gerarLista(String titulo, String codigo, ArrayList<ListaModel> arrayListaModel) {

		try {
			serializer.startTag(null, "section");
			serializer.startTag(null, "caption");
			
			if(codigo != null)

				serializer.startTag(null, "caption_cd")
						.attribute(null, "V", codigo)
						.attribute(null, "S", "LOINC").endTag(null, "caption_cd");
			
			serializer.text(titulo);
			serializer.endTag(null, "caption");
			
			serializer.startTag(null, "list");

			for(ListaModel listaModel : arrayListaModel) {
				if(listaModel.isFull()) {
					serializer.startTag(null, "item");
					serializer.startTag(null, "content");
					serializer.text(listaModel.getTitulo() + " "
							+ listaModel.getDescricao());
					serializer.endTag(null, "content");
					serializer.endTag(null, "item");
				} else {
					serializer.startTag(null, "item");
					serializer.startTag(null, "content");
					serializer.text(listaModel.getDescricao());
					serializer.endTag(null, "content");
					serializer.endTag(null, "item");
				}
			}
			
			serializer.endTag(null, "list");
			serializer.endTag(null, "section");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i(TAG, e.getMessage());
		}

	}*/
	
}