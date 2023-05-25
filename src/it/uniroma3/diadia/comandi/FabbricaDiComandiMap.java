package it.uniroma3.diadia.comandi;

import java.util.HashMap;


public class FabbricaDiComandiMap implements FabbricaDiComandi {
	
	private HashMap<String,AbstractComando> Comandi;
	public FabbricaDiComandiMap() {
		this.Comandi= new HashMap<>();
		Comandi.put("comandononvalido", new ComandoNonValido());
		Comandi.put("vai",new ComandoVai());		
		Comandi.put("fine", new ComandoFine());
		Comandi.put("aiuto", new ComandoAiuto());
		Comandi.put("prendi", new ComandoPrendi());
		Comandi.put("posa", new ComandoPosa());
		Comandi.put("info", new ComandoInfo());
		this.elencoDiComandi();
	}
	
	@Override
	public AbstractComando costruisciComando(String istruzione) {
		String[] istruzioni;
		istruzione = istruzione.toLowerCase();
		istruzioni = istruzione.split("[\\W]+");
		String nomeComando="";
		String Parametro=null; 
		if(istruzioni.length>0) {
			nomeComando=istruzioni[0];
		}	
		if(istruzioni.length>1) {
			Parametro=istruzioni[1];
		}
		if(Comandi.containsKey(nomeComando)==true) {
			Comandi.get(nomeComando).setParametro(Parametro);
			return Comandi.get(nomeComando);
		}
		else {
			return Comandi.get("comandononvalido");
		}
	 }
	
	public void elencoDiComandi() {
		ComandoAiuto aiuto=(ComandoAiuto) Comandi.get("aiuto");
		aiuto.setElencoComandi(Comandi.keySet());
	}
}