package projeto.hospital.facade;

import projeto.exceptions.logica.AcessoBloqueadoException;
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
			String matricula = this.controller.novaMatricula(Constantes.DIRETOR);
			this.controller.cadastraLogin(matricula, chave);
			return matricula;
		}else{
			throw new AcessoBloqueadoException("Voce nao tem acesso ao sistema!");
		}
	}
	
	public void acessaSistema(String matricula, String senha){
		this.controller.acessaSistema(matricula, senha);
	}
	
	public boolean cadastrafuncionario(String nome, String cargo, String dataNascimento) {
		return this.controller.cadastraFuncionario(nome, cargo, dataNascimento);
	}

	public boolean demiteFuncionario(String matriculaDiretor, String senhaDiretor, String matriculaFuncionario){
		return this.controller.demiteFuncionario(matriculaDiretor, senhaDiretor, matriculaFuncionario);
	}

}
