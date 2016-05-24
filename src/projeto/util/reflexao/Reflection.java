package projeto.util.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.funcionario.cargo.Cargo;
import projeto.util.Constantes;
import projeto.util.Conversor;
import projeto.util.Util;
import projeto.util.ValidadorDeDados;

public abstract class Reflection {

	// REFLECTION

	/**
	 * Pega informacao de um determinado objeto.
	 * 
	 * @param objeto
	 *            Objeto a ser usado.
	 * @param atributo
	 *            Atributo a ser retornado.
	 * @return Atributo.
	 * @throws DadoInvalidoException
	 *             Caso o atributo seja invalido
	 */
	public static Object getInfo(Object objeto, String atributo) throws DadoInvalidoException {
		// Classe do objeto
		Class<?> clazz = objeto.getClass();
		// Campo a ser requisitado.
		Field campo = null;
		// Metodo possivel de ser invocado.
		Method metodo;

		/*
		 * Caso o campo nao seja da classe, pega o da superclasse.
		 */
		do {
			try {
				// Pega o campo referente ao atributo
				campo = clazz.getDeclaredField(Util.getNomeCampo(atributo));
			} catch (NoSuchFieldException noField) {
				// Caso o campo nao exista, procura na superclasse.
				clazz = clazz.getSuperclass();
			}
		} while (campo == null);

		try {
			// Faz com que seja possivel acessar o campo.
			campo.setAccessible(true);
			// Caso precise executar um metodo, executa.
			if (campo.isAnnotationPresent(MetodoAssociado.class)) {
				// Pega a anotacao do metodo.
				MetodoAssociado anotacao = campo.getAnnotation(MetodoAssociado.class);
				// Pega o metodo.
				metodo = clazz.getMethod(anotacao.get());
				// Invoca o metodo pelo objeto.
				return metodo.invoke(objeto);
			}
			// Caso nao precise executar nenhum metodo, retorna o proprio campo.
			return campo.get(objeto);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
			// Caso o atributo passado nao seja compativel.
			throw new DadoInvalidoException("Atributo nao valido: " + Util.getNomeCampo(atributo) + ".");
		} catch (InvocationTargetException excecao) {
			// Caso o metodo lance uma excecao.
			throw new DadoInvalidoException(excecao.getCause().getMessage());
		}
	}

	/**
	 * Atualiza a informacao de uma entidade.
	 * 
	 * @param objeto
	 *            Entidade.
	 * @param atributo
	 *            Atributo a ser atualizado.
	 * @param novoValor
	 *            Novo valor da entidade.
	 * @param erroAtualizacao
	 *            Mensagem de erro
	 * @throws DadoInvalidoException
	 *             Caso algo nao saia como desejado.
	 */
	public static void atualizaInfo(Object objeto, String atributo, Object novoValor, String erroAtualizacao)
			throws DadoInvalidoException {
		// Classe do objeto
		Class<?> clazz = objeto.getClass();
		// Campo a ser requisitado.
		Field campo = null;
		// Metodo possivel de ser invocado.
		Method metodo;

		/*
		 * Caso o campo nao seja da classe, pega o da superclasse.
		 */
		do {
			try {
				// Pega o campo referente ao atributo
				campo = clazz.getDeclaredField(Util.getNomeCampo(atributo));
			} catch (NoSuchFieldException noField) {
				// Caso o campo nao exista, procura na superclasse.
				clazz = clazz.getSuperclass();
			}
		} while (campo == null);

		try {
			// Faz com que seja possivel acessar o campo.
			campo.setAccessible(true);
			// Caso precise executar um metodo, executa.
			if (campo.isAnnotationPresent(MetodoAssociado.class)) {
				// Pega a anotacao do metodo.
				MetodoAssociado anotacao = campo.getAnnotation(MetodoAssociado.class);
				// Se tiver validacao
				if (campo.isAnnotationPresent(Validacao.class)) {
					// Se a validacao deve ser feita na atualizacao
					if (campo.getAnnotation(Validacao.class).atualizacao()) {
						// Pega a anotacao de validacao do campo.
						Validacao anotacaoValidacao = campo.getAnnotation(Validacao.class);
						// Caso precise realizar conversao
						if (campo.isAnnotationPresent(Conversao.class)) {
							Conversao conversao = campo.getAnnotation(Conversao.class);
							Method converte = Conversor.class.getMethod(conversao.conversor(), novoValor.getClass());
							// Converte o dado
							novoValor = converte.invoke(null, novoValor);
						}
						Method valida = ValidadorDeDados.class.getMethod(anotacaoValidacao.metodo(),
								anotacaoValidacao.erro().getClass(), novoValor.getClass());
						// Valida o dado
						valida.invoke(null, anotacaoValidacao.erro(), novoValor);
					}
				}
				// Pega o metodo.
				metodo = clazz.getMethod(anotacao.set(), novoValor.getClass());
				// Invoca o metodo pelo objeto.
				metodo.invoke(objeto, novoValor);
			} else {
				// Caso nao tenha como atualizar
				throw new DadoInvalidoException(erroAtualizacao);
			}
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
			// Caso o atributo passado nao seja compativel.
			// e.printStackTrace();
			throw new DadoInvalidoException(erroAtualizacao);
		} catch (InvocationTargetException excecao) {
			// Caso o metodo lance uma excecao.
			throw new DadoInvalidoException(excecao.getCause().getMessage());
		}
	}

	/**
	 * Cria um objeto dada sua classe e os seus atributos do construtor.
	 * 
	 * @param clazz
	 *            Classe do objeto.
	 * @param params
	 *            Parametros do construtor.
	 * @return Objeto criado com aqueles parametros.
	 * @throws DadoInvalidoException
	 *             Caso algum parametro seja invalido.
	 */
	public static Object godFactory(Class<?> clazz, Object... params) throws DadoInvalidoException {
		/*
		 * 1 - Recuperar campos; 2 - Validar os parametros na ordem dos campos 3
		 * - Construir objeto
		 */
		Class<?> klazz = clazz;
		// Campos do objeto.
		ArrayList<Field> campos = new ArrayList<>();
		/*
		 * Pega os campos tanto da classe em si, quanto da superclasse, no caso
		 * de um campo nao estar na subclasse, mas seja preciso ser utilizado na
		 * construcao do objeto.
		 */
		do {
			// Campos que necessitam de validacao.
			camposValidacao(klazz, campos);
			// Muda para a superclasse
			klazz = klazz.getSuperclass();
		} while (klazz != Object.class);

		int parametros = clazz.getConstructors()[0].getParameterCount();
		try {
			// Itera pelos parametros do construtor para validar cada campo
			for (int i = 0; i < parametros && i < campos.size(); i++) {
				Field campo = campos.get(i);
				campo.setAccessible(true);
				Validacao validacao = campo.getAnnotation(Validacao.class);
				ValidadorDeDados.validaNaoNulo(validacao.erro(), params[i]);
				Method valida = ValidadorDeDados.class.getMethod(validacao.metodo(), validacao.erro().getClass(),
						params[i].getClass());
				// Valida o dado.
				valida.invoke(null, validacao.erro(), params[i]);
			}
			// Retorna o objeto.
			// System.out.println(Arrays.asList(params));
			return clazz.getConstructors()[0].newInstance(params);
		} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException
				| InstantiationException excecao) {
			excecao.printStackTrace();
			throw new DadoInvalidoException(excecao.getCause().getMessage());
		} catch (InvocationTargetException excecao) {
			// Caso algum dado foi invalido
			throw new DadoInvalidoException(excecao.getCause().getMessage());
		}
	}

	/**
	 * Adiciona campos que necessitam de validacao.
	 * 
	 * @param klazz
	 *            Classe analisada.
	 * @param campos
	 *            Lista de campos.
	 */
	private static void camposValidacao(Class<?> klazz, ArrayList<Field> campos) {
		Field[] camposTemp = klazz.getDeclaredFields();
		for (int i = camposTemp.length - 1; i > -1; i--) {
			if (camposTemp[i].isAnnotationPresent(Validacao.class))
				if (camposTemp[i].getAnnotation(Validacao.class).cadastro())
					campos.add(Constantes.ZERO, camposTemp[i]);
		}
	}

	/**
	 * Pega um metodo de uma determinada classe
	 * 
	 * @param nomeMetodo
	 *            Nome do metodo a ser executado
	 * @param objeto
	 *            Objeto de que vai executar o metodo
	 * @param paramsClasses
	 *            Classes dos parametros do metodo
	 * @param params
	 *            Parametros a serem passados na execucao
	 * @throws DadoInvalidoException
	 *             Caso o metodo nao exista
	 */
	public static void executaMetodo(String nomeMetodo, Object objeto, Class<?>[] paramsClasses, Object[] params)
			throws DadoInvalidoException {
		try {
			Class<?> clazz = objeto.getClass();
			Method metodo = clazz.getMethod(nomeMetodo, paramsClasses);
			metodo.setAccessible(true);

			metodo.invoke(objeto, params);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new DadoInvalidoException("Este erro nao deveria ter acontecido! Contate o suporte.");
		}
	}

	/**
	 * Factory generica que permite criar qualquer objeto dado o nome
	 * <b>completo</b> de sua classe.
	 * 
	 * @param klazz
	 *            Nome completo da classe (package + nome da classe).
	 * @param mensagemErro
	 *            Mensagem de erro a ser lancada.
	 * @param params
	 *            Parametros do construtor.
	 * @return Objeto requisitado.
	 * @throws DadoInvalidoException
	 *             Caso a classe nao exista.
	 */
	public static Object godFactory(String klazz, String mensagemErro, Object... params) throws DadoInvalidoException {
		try {
			return godFactory(Class.forName(klazz), params);
		} catch (ClassNotFoundException classeNaoEncontrada) {
			throw new DadoInvalidoException(mensagemErro);
		}
	}

	// REFLECTION
}