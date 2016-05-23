package projeto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

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
	// CODIGOS/SUBSTRINGS
	/**
	 * Dado o nome do cargo, este metodo retorna o codigo do mesmo.
	 * 
	 * @param cargo
	 *            Nome do cargo.
	 * @return Codigo do cargo.
	 */
	public static String getCodigoPorCargo(String cargo) {
		// Validacao da string ser valida ja deve ter sido feita antes de chamar
		// esse metodo

		if (cargo.equals(Constantes.DIRETOR_GERAL))
			return Constantes.CODIGO_DIRETOR;
		if (cargo.equals(Constantes.MEDICO))
			return Constantes.CODIGO_MEDICO;
		return Constantes.CODIGO_TECNICO;
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
	public static String getNomeCampo(String string) {
		return string.substring(Constantes.ZERO, Constantes.UM).toLowerCase()
				+ string.substring(Constantes.UM).trim();
	}
	// CODIGOS/SUBSTRINGS
	
	// DATA
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
	 * Recebe um nome separado de classe e o transforma para um tipo valido de nome de classe
	 * 
	 * @param nomeEntrada Entrada
	 * @return Nome valido de classe
	 */
	public static String getNomeClasse(String nomeEntrada) {
		String saida = new String();
		String[] nomes = nomeEntrada.split(" ");
		
		for(int i=0; i<nomes.length; i++){
			saida += capitalizaString(nomes[i]);
		}
		
		return saida;
	}
	
	public static String getNomeClasse(Class<?> klazz, String nomeEntrada) {
		String pack = klazz.getPackage().toString().split(" ")[Constantes.UM];
		return pack + "." + getNomeClasse(nomeEntrada);
	}
	
	/**
	 * Retorna o ano atual.
	 * 
	 * @return Ano atual.
	 */
	public static String getAnoAtual() {
		LocalDate dataAtual = LocalDate.now();
		return Integer.toString(dataAtual.getYear());
	}
	// DATA
	// ARQUIVOS
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
		Object objeto = null;
		try (ObjectInputStream leitorDeObjetos = new ObjectInputStream(
				new FileInputStream(caminho));){
			objeto = leitorDeObjetos.readObject();
			return objeto;
		} catch (Exception excecao) {
			throw new DadoInvalidoException("Arquivo " + caminho
					+ "nao existe!");
		}
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
		try (ObjectOutputStream escritorObjeto = new ObjectOutputStream(new FileOutputStream(caminho))) {
			escritorObjeto.writeObject(objeto);
		} catch (Exception excecao) {
			criaArquivo(caminho);
			setObjeto(caminho, objeto);
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
	 * Cria um arquivo de relatorio de um paciente
	 * 
	 * @param nomeArq
	 *            Nome do arquivo que deve ser criado
	 * @param relatorio
	 *            Informacoes do relatorio
	 */
	public static void criaRelatorioPaciente(String nomeArq, String relatorio) {
		criaArquivo(Constantes.DADOS_PACIENTES_DIRETORIO + nomeArq);
		try (FileWriter escritor = new FileWriter(new File(Constantes.DADOS_PACIENTES_DIRETORIO + nomeArq))) {
			// Se um arquivo com mesmo nome existir ele sera sobrescrito
			escritor.write(relatorio);
		} catch (IOException e) {
			throw new OperacaoInvalidaException(e.getMessage());
		}
	}
	
	// ARQUIVOS
}
