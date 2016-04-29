package projeto.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.dados.DataInvalidaException;
import projeto.exceptions.dados.ObjetoNuloException;
import projeto.exceptions.dados.StringVaziaException;

public class Util {

	public static void validaString(String atributo) {
		validaNaoNulo(atributo);
		if (atributo.trim().length() == Constantes.ZERO)
			throw new StringVaziaException();
	}

	public static void validaString(String nomeAtributo, String atributo) {
		validaNaoNulo(nomeAtributo, atributo);
		if (atributo.trim().length() == Constantes.ZERO)
			throw new StringVaziaException(nomeAtributo
					+ " nao pode ser vazio(a)!");
	}

	public static void validaNaoNulo(Object atributo) {
		if (atributo == null)
			throw new ObjetoNuloException("Objeto nao pode ser nulo!");
	}

	public static void validaNaoNulo(String nomeAtributo, Object atributo) {
		if (atributo == null)
			throw new ObjetoNuloException(nomeAtributo
					+ " nao pode ser nulo(a)!");
	}

	public static void validaData(String nomeData, String data) {
		validaString(nomeData, data);

		Pattern padrao = Pattern.compile(Constantes.DATA_REGEX);
		Matcher validadorDePadrao = padrao.matcher(data);

		// Verificacao da regex
		if (!validadorDePadrao.matches())
			throw new DataInvalidaException(nomeData + " nao eh valida!");

		validadorDePadrao.reset();
		// Verificacao no calendario
		if (validadorDePadrao.find()) {

			String dia = validadorDePadrao.group(1);
			String mes = validadorDePadrao.group(2);
			int ano = Integer.parseInt(validadorDePadrao.group(3));

			if (dia.equals("31"))
				if (mes.equals("11") || mes.equals("04") || mes.equals("06")
						|| mes.equals("09"))
					throw new DataInvalidaException(nomeData
							+ " nao eh valida! Mes fornecido nao tem dia 31!");

			if (mes.equals("02")) {
				if (dia.equals("30") || dia.equals("31"))
					throw new DataInvalidaException(nomeData
							+ " nao eh valida! Fevereiro nao tem dias 30 e 31!");

				if (ano % 4 != 0 && dia.equals("29"))
					throw new DataInvalidaException(
							nomeData
									+ " nao eh valida! Ano nao eh bissexto. Fevereiro nao tem dia 29!");
			}
		}
	}

	public static String getCodigoPorCargo(String cargo) {
		// Validacao do cargo ja deve ter sido feita antes de chamar esse metodo

		if (cargo.equals(Constantes.DIRETOR))
			return Constantes.CODIGO_DIRETOR;
		if (cargo.equals(Constantes.MEDICO))
			return Constantes.CODIGO_MEDICO;
		if (cargo.equals(Constantes.TECNICO))
			return Constantes.CODIGO_TECNICO;
		throw new DadoInvalidoException("Cargo inexistente!");
	}

	public static String getAnoPorData(String dataNascimento) {
		// Validacao da data ja deve ter sido feita antes de chamar esse metodo
		return dataNascimento.split(Constantes.BARRA)[Constantes.INDICE_ANO];
	}

	public static String quatroDigitosIniciais(String numero) {
		return numero.substring(Constantes.ZERO, Constantes.QUATRO);
	}

	public static String quatroDigitosIniciais(int numero) {
		return quatroDigitosIniciais(Integer.toString(numero));
	}

	public static String getCodigoPorMatricula(String matricula) {
		return Character.toString(matricula.charAt(Constantes.ZERO));
	}

}
