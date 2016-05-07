package projeto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;

/**
 * Se encarrega de metodos mais genericos utilizados em varias classes.
 * 
 * @author Estacio Pereira
 * @author Eric
 *
 */
public abstract class Util {
	/**
	 * Dado o nome do cargo, este metodo retorna o codigo do mesmo.
	 * 
	 * @param cargo
	 *            Nome do cargo.
	 * @return Codigo do cargo.
	 * 
	 * @throws DadoInvalidoException
	 *             Caso o cargo nao exista.
	 */
	public static String getCodigoPorCargo(String cargo) throws DadoInvalidoException {
		// Validacao da string ser valida ja deve ter sido feita antes de chamar
		// esse metodo

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
		String formatoSaida = String.join("-", dataQuebrada[2],
				dataQuebrada[1], dataQuebrada[0]);
		return formatoSaida;
	}

	/**
	 * Pega o objeto de um arquivo.
	 * 
	 * @param caminho
	 *            Caminho do arquivo.
	 * 
	 * @return Objeto.
	 * 
	 * @throws DadoInvalidoException
	 *             Caso o caminho nao exista.
	 */
	public static Object getObjeto(String caminho) throws DadoInvalidoException {
		ObjectInputStream leitorDeObjetos = null;
		Object objeto = null;
		try {
			leitorDeObjetos = new ObjectInputStream(
					new FileInputStream(caminho));
			objeto = leitorDeObjetos.readObject();
			return objeto;
		} catch (Exception excecao) {
			throw new DadoInvalidoException("Arquivo " + caminho
					+ "nao existe!");
		} finally {
			try {
				if (leitorDeObjetos != null) {
					leitorDeObjetos.close();
				}
			} catch (IOException excecao) {
				throw new OperacaoInvalidaException("Nao foi possivel fechar o arquivo " + caminho + "!");
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
			escritorObjeto = new ObjectOutputStream(new FileOutputStream(
					caminho));
			escritorObjeto.writeObject(objeto);
		} catch (Exception excecao) {
			criaArquivo(caminho);
			setObjeto(caminho, objeto);
		} finally {
			try {
				if (escritorObjeto != null)
					escritorObjeto.close();
			} catch (IOException excecao) {
				throw new OperacaoInvalidaException("Nao foi possivel fechar o arquivo " + caminho + "!");
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
	 * Retorna a String com a primeira letra maiuscula e as demais minusculas.
	 * 
	 * @param string
	 *            String a ser modificada.
	 * @return String capitalizada.
	 */
	public static String capitalizaString(String string) {
		return string.substring(Constantes.ZERO, Constantes.UM).toUpperCase()
				+ string.substring(Constantes.UM);
	}

	/**
	 * Formata a String para retornar o nome do atributo. 
	 * @param string Nome do atributo sem formatacao.
	 * @return Nome do atributo.
	 */
	private static String getNomeCampo(String string) {
		return string.substring(Constantes.ZERO, Constantes.UM).toLowerCase()
				+ string.substring(Constantes.UM);
	}

	/**
	 * Pega informacao de um determinado objeto.
	 * 
	 * @param objeto
	 *            Objeto a ser usado.
	 * @param atributo
	 *            Atributo a ser retornado.
	 * @param erro
	 *            Erro caso algo saia errado.
	 * @return Atributo.
	 */
	public static Object getInfo(Object objeto, String atributo, String erro) {
		Class clazz = objeto.getClass(); // Classe do objeto
		Field campo; // Campo a ser requisitado.
		Method metodo; // Metodo possivel de ser invocado.
		try {
			campo = clazz.getDeclaredField(Util.getNomeCampo(atributo)); // Pega o campo referente ao atributo.
			campo.setAccessible(true); // Faz com que seja possivel acessar o campo.
			if (campo.isAnnotationPresent(ExMetodo.class)) { // Caso precise executar um metodo, executa.
				ExMetodo anotacao = campo.getAnnotation(ExMetodo.class); // Pega a anotacao do metodo.
				metodo = clazz.getMethod(anotacao.metodo()); // Pega o metodo.
				return metodo.invoke(objeto); // Invoca o metodo pelo objeto.
			}
			return campo.get(objeto); // Caso nao precise executar nenhum metodo, retorna o proprio campo.
		} catch (NoSuchFieldException | IllegalArgumentException
				| IllegalAccessException | NoSuchMethodException
				| SecurityException e){
			throw new OperacaoInvalidaException(erro + "Atributo nao valido." + " " + Util.getNomeCampo(atributo) + " " + e.toString() + " " + objeto.getClass()); //Caso o atributo passado nao seja compativel.
		}catch(InvocationTargetException excecao) {
			throw new OperacaoInvalidaException(excecao.getCause().getMessage()); // Caso o metodo lance uma excecao.
		}
	}
	
	/**
	 * Valida a compatibilidade entre dois tipos sanguineos
	 * 
	 * @param tipoSanguineoPaciente
	 *            Tipo sanguineo do paciente
	 * @param tipoSanguineoOrgao
	 *            Tipo sanguineo do orgao
	 * @throws DadoInvalidoException
	 *             Caso os tipos nao sejam compativeis
	 */
	public static void validaCompatibilidadeTipoSanguineo(String tipoSanguineoPaciente, String tipoSanguineoOrgao)
			throws DadoInvalidoException {
		List<String> tipos = Constantes.TIPOS_SANGUINEOS_VALIDOS;
		int sanguePaciente = tipos.indexOf(tipoSanguineoPaciente);
		int sangueOrgao = tipos.indexOf(tipoSanguineoOrgao);

		// Matriz que identifica a compatibilidade dos tipos sanguineos
		// forma O- O+ A- A+ B- B+ AB- AB+ por ela mesma
		boolean[][] matrizCompatibilidade = { 
				{ true, false, false, false, false, false, false, false },
				{ true, true, false, false, false, false, false, false },
				{ true, false, true, false, false, false, false, false },
				{ true, true, true, true, false, false, false, false },
				{ true, false, false, false, true, false, false, false },
				{ true, true, false, false, true, true, false, false },
				{ true, false, true, false, true, false, true, false },
				{ true, true, true, true, true, true, true, true } };

		// TODO arrumar essa mensagem de erro quando sairem os testes
		if (!matrizCompatibilidade[sangueOrgao][sanguePaciente])
			throw new DadoInvalidoException("Tipo de sangue nao compativel.");
	}
}
