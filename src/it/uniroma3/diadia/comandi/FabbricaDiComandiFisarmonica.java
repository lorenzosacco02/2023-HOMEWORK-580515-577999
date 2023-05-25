package it.uniroma3.diadia.comandi;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{
	
	@Override
	public AbstractComando costruisciComando(String istruzione) {
        String[] istruzioni;
		istruzione = istruzione.toLowerCase();
		istruzioni = istruzione.split("[\\W]+");
		AbstractComando comando = null;
        String nomeComando="";
        String Parametro=null; 
        if(istruzioni.length>0)
        	  nomeComando=istruzioni[0];
        if(istruzioni.length>1)
              Parametro=istruzioni[1];
	switch (nomeComando) {
		case "fine":
            comando = new ComandoFine();
            break;
		case "vai":
			comando = new ComandoVai();
			break;
		case "aiuto":
			comando = new ComandoAiuto();
			break;
		case "prendi":
			comando = new ComandoPrendi();
			break;
		case "posa":
			comando = new ComandoPosa();
			break;
		case "borsa":
			comando = new ComandoBorsa();
			break;
		default:
			comando = new ComandoNonValido();
			break;
	}
	comando.setParametro(Parametro);
	return comando;
	}
}
    
