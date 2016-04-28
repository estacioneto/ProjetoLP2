package projeto.util;

import java.text.DecimalFormat;

public class GeradorDeMatricula {
	
	private int cadastros;
	private DecimalFormat formatadorDecimal;
	
	public GeradorDeMatricula(){
		this.cadastros = 1;
		this.formatadorDecimal = new DecimalFormat(Util.FORMATO_TRES_NUMEROS);
	}
	
	public String geraNovaMatricula(String codigo, String ano){
		String matricula = codigo + ano + formatadorDecimal.format(cadastros);
		this.novoCadastroEfetuado();
		return matricula;
	}

	private void novoCadastroEfetuado() {
		this.cadastros++;
	}
}
