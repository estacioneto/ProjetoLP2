package projeto.hospital.controller;

import java.util.HashMap;
import java.util.Map;

import projeto.exceptions.acesso.AcessoBloqueadoException;
import projeto.util.Constantes;
import projeto.util.Util;

public class ValidadorDeLogica {

	private Map <String, String> logins;
	
	public ValidadorDeLogica(){
		this.logins = new HashMap<String, String>();
	}
	
	public void validaAcesso(String matricula, String senha){
		Util.validaString(Constantes.MATRICULA, matricula);
		Util.validaString(Constantes.SENHA, senha);
		if(!this.logins.containsKey(matricula))
			throw new AcessoBloqueadoException("Login falhou. Matricula ou senha incorretos.");
		if(!this.logins.get(matricula).equals(senha))
			throw new AcessoBloqueadoException("Login falhou. Matricula ou senha incorretos.");
	}
	
	public void cadastraLogin(String matricula, String senha){
		this.logins.put(matricula, senha);
	}
}
