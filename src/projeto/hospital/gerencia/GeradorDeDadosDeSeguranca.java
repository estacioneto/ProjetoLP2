package projeto.hospital.gerencia;

import java.text.DecimalFormat;

import projeto.util.Constantes;
import projeto.util.Util;

public class GeradorDeDadosDeSeguranca {

	private int cadastros;
	private DecimalFormat formatadorDecimal;

	public GeradorDeDadosDeSeguranca() {
		this.cadastros = 1;
		this.formatadorDecimal = new DecimalFormat(Constantes.FORMATO_TRES_NUMEROS);
	}
	
	public String geraSenha(String matricula, String ano){
		return ano + Util.quatroDigitosIniciais(matricula);
	}

	public String geraMatricula(String cargo, String ano) {
		String codigo = Util.getCodigoPorCargo(cargo);
		String matricula = codigo + ano + formatadorDecimal.format(cadastros);
		this.novoCadastroEfetuado();
		return matricula;
	}

	private void novoCadastroEfetuado() {
		this.cadastros++;
	}
}
