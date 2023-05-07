package it.uniroma3.diadia.comandi;

import java.util.HashMap;
import java.util.Set;


public class FabbricaDiComandiMap implements FabbricaDiComandi {
	private HashMap<String,Comando> Comandi;
	public FabbricaDiComandiMap() {
		this.Comandi= new HashMap<>();
		Comandi.put("vai",new ComandoVai());		
		Comandi.put("fine", new ComandoFine());
		Comandi.put("aiuto", new ComandoAiuto());
		Comandi.put("prendi", new ComandoPrendi());
		Comandi.put("posa", new ComandoPosa());
		Comandi.put("borsa", new ComandoBorsa());
		Comandi.put("default", new ComandoNonValido());
		this.elencoDiComandi();
	}
	@Override
	public Comando costruisciComando(String istruzione) {
		String[] istruzioni;
		istruzione = istruzione.toLowerCase();
		istruzioni = istruzione.split("[\\W]+");
        String nomeComando="";
        String Parametro=null; 
        if(istruzioni.length>0)
        	  nomeComando=istruzioni[0];
        if(istruzioni.length>1)
              Parametro=istruzioni[1];
			if(Comandi.containsKey(nomeComando)==true) {
				Comandi.get(nomeComando).setParametro(Parametro);
				return Comandi.get(nomeComando);
			}
			return Comandi.get("default");
	}
	public void elencoDiComandi() {
		ComandoAiuto aiuto=(ComandoAiuto) Comandi.get("aiuto");
		Set<String> key= Comandi.keySet();
		key.remove("Default");
		aiuto.setElencoComandi(key);
	}
}
