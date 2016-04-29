package projeto.hospital.facade;

import projeto.exceptions.acesso.AcessoBloqueadoException;
import projeto.hospital.controller.Controller;
import projeto.util.Constantes;

public class Facade {

	private final String CHAVE_DESBLOQUEIO = "c041ebf8";	
	private Controller controller;
	
	public Facade(){
		this.controller = new Controller();
	}
	
	public String liberaSistema(String chave){
		if(CHAVE_DESBLOQUEIO.equals(chave)){
			String matricula = this.controller.novaMatricula(Constantes.CODIGO_DIRETOR);
			this.controller.cadastraLogin(matricula, chave);
			return matricula;
		}else{
			throw new AcessoBloqueadoException("Voce nao tem acesso ao sistema!");
		}
	}
	
	public void realizaLogin(String matricula, String senha){
		this.controller.realizaLogin(matricula, senha);
	}

}
