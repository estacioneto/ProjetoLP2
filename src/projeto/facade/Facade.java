package projeto.facade;

import projeto.exceptions.acesso.AcessoBloqueadoException;

public class Facade {

	public final String CHAVE_DESBLOQUEIO = "c041ebf8";
	
	
	public void liberaSistema(String chave){
		if(CHAVE_DESBLOQUEIO.equals(chave)){
			
		}else{
			throw new AcessoBloqueadoException("Você não tem acesso ao Sistema!");
		}
			
	}
}
