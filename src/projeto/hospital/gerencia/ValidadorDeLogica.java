package projeto.hospital.gerencia;

import java.util.HashMap;
import java.util.Map;

import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
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
			throw new AcessoBloqueadoException("Matricula nao cadastrada!");
		if(!this.logins.get(matricula).equals(senha))
			throw new AcessoBloqueadoException("Senha incorreta!");
	}
	
	public void cadastraLogin(String matricula, String senha){
		if(this.logins.containsKey(matricula))
			throw new OperacaoInvalidaException("Matricula ja cadastrada!");
		this.logins.put(matricula, senha);
	}

	public void removeLogin(String matricula) {
		if(this.logins.containsKey(matricula))
			this.logins.remove(matricula);
		else
			throw new OperacaoInvalidaException("Matricula nao cadastrada!");
	}

	public void validaExclusao(String matriculaDiretor, String senhaDiretor) {
		validaAcesso(matriculaDiretor, senhaDiretor);
		if(!Util.getCodigoPorMatricula(matriculaDiretor).equals(Constantes.CODIGO_DIRETOR))
			throw new OperacaoInvalidaException("Exclusao nao pode ser efetuada! Usuario nao eh diretor!");
	}
}
