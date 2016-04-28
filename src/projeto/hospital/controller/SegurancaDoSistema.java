package projeto.hospital.controller;

import java.util.HashMap;
import java.util.Map;

import projeto.exceptions.acesso.AcessoBloqueadoException;

public class SegurancaDoSistema {

	private Map <String, String> logins;
	
	public SegurancaDoSistema(){
		this.logins = new HashMap<String, String>();
	}
	
	public void validaAcesso(String matricula, String senha){
		if(!this.logins.containsKey(matricula) && this.logins.get(matricula).equals(senha))
			throw new AcessoBloqueadoException("Login falhou. Matricula ou senha incorretos.");
	}
	
	public void cadastraLogin(String matricula, String senha){
		this.logins.put(matricula, senha);
	}
}
