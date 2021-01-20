package br.edu.ufersa.multcare.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SistemaEspecialista {

	// comum hiper e hipo
	private String nauseas;
	private String fadiga;
	private String fome;
	private String dorDeCabeca;
	private String visaoEmbacada;
	private String sonolencia;
	private String bocaSeca;
	private String vontadeExcessivaUrinar;
	private String dificuldadeRespirar;
	private String halitoCetonico;
	private String sedeExcessiva;
	private String fraqueza;
	private String confusaoMental;
	private String tontura;
	private String falaArrastada;
	private String tremores;
	private String palpitacoes;
	private String incPensarClaramente;
	private String convulsoes;
	private String suorExcessivo;
	private String caimbra;	
	private String dorNoPeito;
	private String dormencia;
	private String vomitos;
	private String taquicardia;
	private String lesaoMuscular;
	private String paralisia;
	private String constipacao;

	private String medicamentosHiperglicemia;
	private String medicamentosHipoglicemia;
	private String medicamentosHipocalemia;
	private String medicamentosHipercalemia;
	private String medicamentosSulfMegHipog;
	private String suspeitaDoenca;
	private String tipoMedicamento;
	private String tipoSintomas;
	
	private String insulina;
	private String alcool;	
	private String diabetico;
	private String medirGlicemia;	
	private String medirPotassio;	
	private double resultadoGlicemia;
	private double resultadopotassio;

//med hiperglicemia
	private String corticosteroides;
	private String octreotida;
	private String betabloqueadores;
	private String epinefrina;
	private String diureticosTiazidicos;
	private String niacina;
	private String pentamidina;
	private String inibidoresDaProtease;
	private String lAsparaginase;
	private String antipsicoticos;
	private String olanzapina;
	private String duloxetina;
	private String antfetamina;
	private String paroxetina;
	private String pravastatina;
	
//med hipoglicemia
	private String glibenclamida;
	private String glimepirida;
	private String glipizida;
	private String clorpropamida;
	private String nateglinida;
	private String repaglinida;
	private String acarbose;
	private String sitagliptina;
	private String vildagliptina;
	private String saxagliptina;
	private String exenatida;
	private String gliclazida;
	private String espironolactona;
	private String amilorida;
	private String triantereno;
	private String inibidoresDoECA;
	private String antagonistaDosReceptoresDaAngiotensina;
	private String antiInflamatoriosNaoEsteroides;
	private String trimetoprim;
	private String ciclosporina;
	private String penicilinaGPotassica;
	private String betaAgonista;
	private String diureticosDeAlca;
	private String albuterol;
	private String terbutalina;
	private String angiotensina;
	private String metformina;
	private String pioglitazona;
	

	public SistemaEspecialista(QuestionarioTriagem questionarioTriagem) {

		this.nauseas = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("Nauseas"));
		this.fadiga =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("Fadiga"));
		this.fome =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("Fome"));
		this.dorDeCabeca =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("DorDeCabeca"));
		this.visaoEmbacada =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("VisaoEmbacada"));
		this.sonolencia =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("Sonolencia"));
		this.bocaSeca =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("BocaSeca"));
		this.vontadeExcessivaUrinar =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("vontadeExcessivaUrinar"));
		this.dificuldadeRespirar =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("dificuldadeRespirar"));
		this.halitoCetonico =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("halitoCetonico"));
		this.sedeExcessiva =  setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("sedeExcessiva"));
		this.fraqueza = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("fraqueza"));
		this.confusaoMental = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("confusaoMental"));
		this.tontura = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("tontura"));
		this.falaArrastada = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("falaArrastada"));
		this.tremores = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("tremores"));
		this.palpitacoes = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("palpitacoes"));
		this.incPensarClaramente = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("incPensarClaramente"));
		this.convulsoes = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("convulsoes"));
		this.suorExcessivo = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("suorExcessivo"));
		this.caimbra = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("caimbra"));
		this.taquicardia = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("taquicardia"));
		this.dorNoPeito = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("dorNoPeito"));
		this.dormencia = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("dormencia"));
		this.vomitos = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("vomitos"));
		this.lesaoMuscular = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("lesaoMuscular"));
		this.paralisia = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("paralisia"));
		this.constipacao = setValorBooleano(questionarioTriagem.possuiSintomaPorDescricao("constipacao"));

		this.diabetico = questionarioTriagem.getDiabetico();
		this.medirGlicemia = questionarioTriagem.getMedirGlicemia();
		this.medirPotassio = questionarioTriagem.getPotassio();
		this.resultadoGlicemia = questionarioTriagem.getResultadoGlicemia();
		this.resultadopotassio = questionarioTriagem.getResultadopotassio();
		this.insulina = questionarioTriagem.getInsulina();
		this.alcool = questionarioTriagem.getAlcool();

		this.medicamentosHiperglicemia = "F";
		this.medicamentosHipoglicemia = "F";
		this.medicamentosHipercalemia = "F";
		this.medicamentosHipocalemia = "F";
		this.medicamentosSulfMegHipog = "F";
		
    	this.corticosteroides = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Corticosteroides"));
		this.octreotida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Octreotida"));
		this.betabloqueadores = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Betabloqueadores"));
		this.epinefrina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Epinefrina"));
		this.diureticosTiazidicos = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("DiureticosTiazidicos"));
		this.niacina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Niacina"));
		this.pentamidina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Pentamidina"));
		this.inibidoresDaProtease = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("InibidoresDaProtease"));
		this.lAsparaginase = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("LAsparaginase"));
		this.antipsicoticos = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Antipsicoticos"));
		this.olanzapina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Olanzapina"));
		this.duloxetina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Duloxetina"));
		this.antfetamina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Antfetamina"));
		this.paroxetina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Paroxetina"));
		this.pravastatina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Pravastatina"));
		this.glibenclamida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Glibenclamida"));
		this.glimepirida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Glimepirida"));
		this.glipizida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Glipizida"));
		this.clorpropamida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Clorpropamida"));
		this.nateglinida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Nateglinida"));
		this.repaglinida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Repaglinida"));
		this.acarbose = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Acarbose"));
		this.metformina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Metformina"));
		this.pioglitazona = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Pioglitazona"));
		this.sitagliptina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Sitagliptina"));
		this.vildagliptina= setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Vildagliptina"));
		this.saxagliptina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Saxagliptina"));
		this.exenatida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Exenatida"));
		this.gliclazida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Gliclazida"));
		this.espironolactona = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Espironolactona"));
		this.amilorida = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Amilorida"));
		this.triantereno = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Triantereno"));
		this.inibidoresDoECA = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("InibidoresDoECA"));
		this.antagonistaDosReceptoresDaAngiotensina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("AntagonistaDosReceptoresDaAngiotensina"));
		this.antiInflamatoriosNaoEsteroides = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("antiInflamatoriosNaoEsteroides"));
		this.trimetoprim = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("trimetoprim"));
		this.ciclosporina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Ciclosporina"));
		this.penicilinaGPotassica = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("PenicilinaGPotassica"));
		this.betaAgonista = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("BetaAgonista"));
		this.diureticosDeAlca = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("DiureticosDeAlca"));
		this.albuterol = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Albuterol"));
		this.terbutalina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Terbutalina"));
		this.angiotensina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Angiotensina"));
		this.metformina = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Metformina"));
		this.pioglitazona = setValorBooleano(questionarioTriagem.possuiMedicamentoPorDescricao("Pioglitazona"));
		
	
	

	}

	private String setValorBooleano(Boolean valor) {
		return valor ? "S" : "F";
	}

}

