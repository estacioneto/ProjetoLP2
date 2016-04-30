package projeto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.dados.DataInvalidaException;
import projeto.exceptions.dados.ObjetoNuloException;
import projeto.exceptions.dados.StringVaziaException;
import projeto.exceptions.logica.OperacaoInvalidaException;

/**
 * Se encarrega de varias validacoes de dados e de metodos mais genericos
 * utilizados em varias classes.
 * 
 * @author Estacio Pereira
 *
 */
public class Util {

	/**
	 * Verifica se a String eh nula ou vazia.
	 * 
	 * @param atributo
	 *            String a ser analisada.
	 */
	public static void validaString(String atributo) {
		validaNaoNulo(atributo);
		if (atributo.trim().length() == Constantes.ZERO)
			throw new StringVaziaException();
	}

	/**
	 * Verifica se a String eh nula ou vazia. O nomeAtributo eh utilizado para
	 * uma melhor criacao de excecao caso a String seja invalida.
	 * 
	 * @param erroAtributo
	 *            Nome do atributo que carrega a String a ser analisada.
	 * @param atributo
	 *            String a ser analisada.
	 */
	public static void validaString(String erroAtributo, String atributo) {
		validaNaoNulo(erroAtributo, atributo);
		if (atributo.trim().length() == Constantes.ZERO)
			throw new StringVaziaException(erroAtributo + " nao pode ser vazio.");
	}

	/**
	 * Verifica se um objeto eh nulo.
	 * 
	 * @param atributo
	 *            Objeto a ser analisado.
	 */
	public static void validaNaoNulo(Object atributo) {
		if (atributo == null)
			throw new ObjetoNuloException("Objeto nao pode ser nulo!");
	}

	/**
	 * Verifica se o objeto eh nulo. O nomeAtributo eh utilizado para uma melhor
	 * criacao de excecao.
	 * 
	 * @param nomeAtributo
	 *            Nome do atributo que carrega o Objeto a ser analisado.
	 * @param atributo
	 *            Objeto a ser analisado.
	 */
	public static void validaNaoNulo(String nomeAtributo, Object atributo) {
		if (atributo == null)
			throw new ObjetoNuloException(nomeAtributo + " nao pode ser nulo(a)!");
	}

	public static void validaCargo(String erroOperacao, String cargo) {
		validaString(erroOperacao + MensagensDeErro.CARGO_FUNCIONARIO, cargo);
		if (!Constantes.CARGOS_VALIDOS.contains(cargo.toLowerCase()))
			throw new DadoInvalidoException(erroOperacao + MensagensDeErro.CARGO_INVALIDO_FUNCIONARIO);
	}

	/**
	 * Verifica se a data corresponde ao padrao dd/mm/aaaa e se a mesma eh
	 * coerente.
	 * 
	 * @param erroData
	 *            Nome do atributo que carrega a data a ser analisada.
	 * @param data
	 *            Data a ser analisada.
	 */
	public static void validaData(String erroData, String data) {
		validaString(erroData, data);

		Pattern padrao = Pattern.compile(Constantes.DATA_REGEX);
		Matcher validadorDePadrao = padrao.matcher(data);

		// Verificacao da regex
		if (!validadorDePadrao.matches())
			throw new DataInvalidaException(erroData + " invalida.");

		validadorDePadrao.reset();
		// Verificacao no calendario
		if (validadorDePadrao.find()) {

			String dia = validadorDePadrao.group(1);
			String mes = validadorDePadrao.group(2);
			int ano = Integer.parseInt(validadorDePadrao.group(3));

			if (dia.equals("31"))
				if (mes.equals("11") || mes.equals("04") || mes.equals("06") || mes.equals("09"))
					throw new DataInvalidaException(erroData + " nao eh valida! Mes fornecido nao tem dia 31!");

			if (mes.equals("02")) {
				if (dia.equals("30") || dia.equals("31"))
					throw new DataInvalidaException(erroData + " nao eh valida! Fevereiro nao tem dias 30 e 31!");

				if (ano % 4 != 0 && dia.equals("29"))
					throw new DataInvalidaException(
							erroData + " nao eh valida! Ano nao eh bissexto. Fevereiro nao tem dia 29!");
			}
		}
	}

	/**
	 * Dado o nome do cargo, este metodo retorna o codigo do mesmo.
	 * 
	 * @param cargo
	 *            Nome do cargo.
	 * @return Codigo do cargo.
	 */
	public static String getCodigoPorCargo(String cargo) {
		// Validacao do cargo ja deve ter sido feita antes de chamar esse metodo

		if (cargo.equals(Constantes.DIRETOR_GERAL))
			return Constantes.CODIGO_DIRETOR;
		if (cargo.equals(Constantes.MEDICO))
			return Constantes.CODIGO_MEDICO;
		if (cargo.equals(Constantes.TECNICO_ADMINISTATIVO))
			return Constantes.CODIGO_TECNICO;
		throw new DadoInvalidoException("Cargo inexistente!");
	}

	/**
	 * Dada uma determinada data, o metodo retorna o ano da mesma.
	 * 
	 * @param data
	 *            Data
	 * @return Ano da data.
	 */
	public static String getAnoPorData(String data) {
		// Validacao da data ja deve ter sido feita antes de chamar esse metodo
		return data.split(Constantes.BARRA)[Constantes.INDICE_ANO];
	}

	/**
	 * Dado um numero em String, retorna os quatro primeiros digitos em String.
	 * 
	 * @param numero
	 *            Numero base.
	 * @return Quatro digitos iniciais do numero.
	 */
	public static String quatroDigitosIniciais(String numero) {
		return numero.substring(Constantes.ZERO, Constantes.QUATRO);
	}

	/**
	 * Dado um numero em inteiro, retorna os quatro primeiros digitos em String.
	 * 
	 * @param numero
	 *            Numero base.
	 * @return Quatro digitos iniciais do numero.
	 */
	public static String quatroDigitosIniciais(int numero) {
		return quatroDigitosIniciais(Integer.toString(numero));
	}

	/**
	 * Dada uma matricula, retorna o codigo do cargo da funcionario dono da
	 * mesma.
	 * 
	 * @param matricula
	 *            Matricula do funcionario.
	 * @return Codigo do cargo do funcionario.
	 */
	public static String getCodigoPorMatricula(String matricula) {
		return Character.toString(matricula.charAt(Constantes.ZERO));
	}

	/**
	 * Vai pegar a data do formato recebido e guardado no sistema e transformar
	 * para o formato de apresentacao
	 * 
	 * @param dataSistema
	 *            data no formato do sistema
	 * @return data no formato de apresentacao
	 */
	public static String transformaFormatoData(String dataSistema) {
		String[] dataQuebrada = dataSistema.split("/");
		String formatoSaida = String.join("-", dataQuebrada[2], dataQuebrada[1], dataQuebrada[0]);
		return formatoSaida;
	}

	/**
	 * Valida se um valor double eh positivo
	 * 
	 * @param nomeAtributo
	 *            Nome do atributo
	 * @param valor
	 *            valor
	 */
	public static void validaPositivo(String nomeAtributo, double valor) {
		if (valor < 0)
			throw new DadoInvalidoException(nomeAtributo + " nao pode ser negativo.");
	}

	/**
	 * Valida se um valor inteiro eh positivo
	 * 
	 * @param nomeAtributo
	 *            Nome do atributo
	 * @param valor
	 *            valor
	 */
	public static void validaPositivo(String nomeAtributo, int valor) {
		if (valor < 0)
			throw new DadoInvalidoException(nomeAtributo + " nao pode ser negativo.");
	}

	/**
	 * Verifica se um tipo saguineo eh valido
	 * 
	 * @param tipoSanguineo
	 *            tipo sanguineo
	 */
	public static void validaTipoSanguineo(String tipoSanguineo) {
		if (!Constantes.TIPOS_SANGUINEOS_VALIDOS.contains(tipoSanguineo))
			throw new DadoInvalidoException("Tipo sanguineo invalido.");
	}

	/**
	 * Verifica se um sexo biologico eh valido
	 * 
	 * @param sexoBiologico
	 *            sexo biologico
	 */
	public static void validaSexoBiologico(String sexoBiologico) {
		if (sexoBiologico == null || !sexoBiologico.toLowerCase().equals(Constantes.MASCULINO)
				&& !sexoBiologico.toLowerCase().equals(Constantes.FEMININO))
			throw new DadoInvalidoException("Sexo biologico invalido.");
	}

	/*
	 * Pega o objeto de um arquivo.
	 * 
	 * @param caminho Caminho do arquivo.
	 * 
	 * @return Objeto.
	 */
	public static Object getObjeto(String caminho) {
		ObjectInputStream leitorDeObjetos = null;
		Object objeto = null;
		try {
			leitorDeObjetos = new ObjectInputStream(new FileInputStream(caminho));
			objeto = leitorDeObjetos.readObject();
			return objeto;
		} catch (Exception excecao) {
			throw new DadoInvalidoException("Arquivo " + caminho + "nao existe!");
		} finally {
			try {
				if (leitorDeObjetos != null) {
					leitorDeObjetos.close();
				}
			} catch (IOException excecao) {
				throw new DadoInvalidoException("Nao foi possivel fechar o arquivo " + caminho + "!");
			}
		}
	}

	/**
	 * Cria um arquivo se ja nao existir.
	 * 
	 * @param caminho
	 *            Caminho do arquivo.
	 */
	public static void criaArquivo(String caminho) {
		File arquivo = new File(caminho);
		// Verifica se sera necessario criar o arquivo
		if (arquivo.getParentFile() != null)
			arquivo.getParentFile().mkdirs();
	}

	/**
	 * Escreve o objeto em um arquivo
	 * 
	 * @param caminho
	 *            Caminho do arquivo
	 * @param objeto
	 *            Objeto a ser escrito
	 */
	public static void setObjeto(String caminho, Object objeto) {
		ObjectOutputStream escritorObjeto = null;
		try {
			escritorObjeto = new ObjectOutputStream(new FileOutputStream(caminho));
			escritorObjeto.writeObject(objeto);
		} catch (Exception excecao) {
			criaArquivo(caminho);
			setObjeto(caminho, objeto);
		} finally {
			try {
				if (escritorObjeto != null)
					escritorObjeto.close();
			} catch (IOException excecao) {
				throw new DadoInvalidoException("Nao foi possivel fechar o arquivo " + caminho + "!");
			}
		}
	}

	/**
	 * Limpa os dados de algum diretorio.
	 * 
	 * @param diretorio
	 *            Diretorio a ser removido.
	 * @return true se removeu com sucesso.
	 */
	public static boolean limpaDados(File diretorio) {
		if (diretorio.isDirectory()) {
			String[] filhos = diretorio.list();
			for (String filho : filhos) {
				if (!limpaDados(new File(diretorio, filho))) {
					return false;
				}
			}
		}
		return diretorio.delete();
	}

	/**
	 * Valida padrao da matricula.
	 * 
	 * @param matricula
	 *            matricula a ser analisada.
	 * @param mensagemErro
	 *            Mensagem a ser utilizada caso nao obedeca o padrao.
	 */
	public static void validaPadraoMatricula(String matricula, String mensagemErro) {
		for (int indice = 0; indice < matricula.length(); indice++) {
			if (!Character.isDigit(matricula.charAt(indice)))
				throw new DadoInvalidoException(mensagemErro);
		}
	}

	/**
	 * Valida atributo de funcionario.
	 * 
	 * @param erroOperacao
	 *            Mensagem de erro correspondente a operacao.
	 * @param atributo
	 *            Atributo a ser analisado.
	 */
	public static void validaAtributo(String erroOperacao, String atributo, String valor) {
		validaString(erroOperacao + MensagensDeErro.ATRIBUTO_FUNCIONARIO, atributo);
		atributo = capitalizaString(atributo);

		if (atributo.equals(Constantes.MATRICULA))
			throw new OperacaoInvalidaException(erroOperacao + MensagensDeErro.ATUALIZAR_MATRICULA);
		if (!(atributo.equals(Constantes.NOME) || atributo.equals(Constantes.CARGO)
				|| atributo.equals(Constantes.DATA)))
			throw new OperacaoInvalidaException(erroOperacao + MensagensDeErro.ATRIBUTO_INVALIDO);

		if (atributo.equals(Constantes.NOME))
			validaNome(erroOperacao, valor);
		else if (atributo.equals(Constantes.DATA))
			validaData(erroOperacao + Constantes.DATA, valor);
	}

	/**
	 * Valida um nome.
	 * 
	 * @param erroOperacao
	 *            Mensagem de erro correspondente a operacao.
	 * @param nome
	 *            Nome a ser analisado.
	 */
	public static void validaNome(String erroOperacao, String nome) {
		Util.validaString(erroOperacao + MensagensDeErro.NOME_FUNCIONARIO, nome);
		if (nome.length() == Constantes.NOME_TAMANHO_MAXIMO)
			throw new OperacaoInvalidaException(erroOperacao + MensagensDeErro.NOME_TAMANHO_INVALIDO);
		for (int indice = 0; indice < nome.length(); indice++) {
			if (Character.isDigit(nome.charAt(indice)))
				throw new OperacaoInvalidaException(erroOperacao + MensagensDeErro.NOME_CARACTER_INVALIDO);
		}
	}

	/**
	 * Retorna a String com a primeira letra maiuscula e as demais minusculas.
	 * 
	 * @param string
	 *            String a ser modificada.
	 * @return String capitalizada.
	 */
	public static String capitalizaString(String string) {
		return string.substring(Constantes.ZERO, Constantes.UM).toUpperCase() + string.substring(Constantes.UM);
	}

	/**
	 * Valida uma nova senha.
	 * 
	 * @param erroOperacao
	 *            Mensagem de erro correspondente a operacao.
	 * @param senha
	 *            Senha a ser analisada.
	 */
	public static void validaSenha(String erroOperacao, String senha) {
		Util.validaString(erroOperacao + Constantes.SENHA, senha);

		if (senha.length() < Constantes.SENHA_TAMANHO_MINIMO || senha.length() > Constantes.SENHA_TAMANHO_MAXIMO)
			throw new OperacaoInvalidaException(
					erroOperacao + "A nova senha deve ter entre 8 - 12 caracteres alfanumericos.");

		for (int indice = 0; indice < senha.length(); indice++) {
			if (!(Character.isAlphabetic(senha.charAt(indice)) || Character.isDigit(senha.charAt(indice))
					|| senha.charAt(indice) == ' ')) {
				throw new OperacaoInvalidaException(
						erroOperacao + "A nova senha deve ter entre 8 - 12 caracteres alfanumericos.");
			}
		}
	}
}
