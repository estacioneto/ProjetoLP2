package projeto.hospital.facade;

import projeto.exceptions.acesso.AcessoBloqueadoException;
import projeto.hospital.controller.Controller;

public class Facade {

	private final String CHAVE_DESBLOQUEIO = "c041ebf8";
	private final String CODIGO_DIRETOR = "1";
	private final String CODIGO_MEDICO = "2";
	private final String CODIGO_TECNICO = "3";
	
	private Controller controller;
	
	public Facade(){
		this.controller = new Controller();
	}
	
	public String liberaSistema(String chave){
		if(CHAVE_DESBLOQUEIO.equals(chave)){
			String matricula = this.controller.novaMatricula(this.CODIGO_DIRETOR);
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
