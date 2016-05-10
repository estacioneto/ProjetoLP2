package projeto.util.reflexao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import projeto.exceptions.dados.DadoInvalidoException;
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
	 * @param erro
	 *            Erro caso algo saia errado.
	 * @return Atributo.
	 * @throws DadoInvalidoException
	 *             Caso o atributo seja invalido
	 */
	public static Object getInfo(Object objeto, String atributo) throws DadoInvalidoException {
		Class clazz = objeto.getClass(); // Classe do objeto
		Field campo = null; // Campo a ser requisitado.
		Method metodo; // Metodo possivel de ser invocado.
		
		/*
		 * Caso o campo nao seja da classe,
		 * pega o da superclasse.
		 */
		do{
			try{
				campo = clazz.getDeclaredField(Util.getNomeCampo(atributo)); // Pega o campo referente ao atributo
			}catch(NoSuchFieldException noField){ 
				clazz = clazz.getSuperclass(); // Caso o campo nao exista, procura na superclasse.
			}
		}while(campo == null);
		
		try {
			campo.setAccessible(true); // Faz com que seja possivel acessar o campo.
			if (campo.isAnnotationPresent(MetodoAssociado.class)) { // Caso precise executar um metodo, executa.
				MetodoAssociado anotacao = campo.getAnnotation(MetodoAssociado.class); // Pega a anotacao do metodo.
				metodo = clazz.getMethod(anotacao.get()); // Pega o metodo.
				return metodo.invoke(objeto); // Invoca o metodo pelo objeto.
			}
			return campo.get(objeto); // Caso nao precise executar nenhum metodo, retorna o proprio campo.
		} catch (IllegalArgumentException
				| IllegalAccessException | NoSuchMethodException
				| SecurityException e){
			//Caso o atributo passado nao seja compativel.
			throw new DadoInvalidoException("Atributo nao valido: " + Util.getNomeCampo(atributo) + "."); 
		}catch(InvocationTargetException excecao) {
			throw new DadoInvalidoException(excecao.getCause().getMessage()); // Caso o metodo lance uma excecao.
		}
	}
	
	/**
	 * Atualiza a informacao de uma entidade.
	 * 
	 * @param objeto 
	 *               Entidade.
	 * @param atributo
	 *               Atributo a ser atualizado.
	 * @param novoValor
	 *               Novo valor da entidade.
	 * @throws DadoInvalidoException 
	 *               Caso algo nao saia como desejado.
	 */
	public static void atualizaInfo(Object objeto, String atributo, Object novoValor, String erroAtualizacao) throws DadoInvalidoException {
		Class clazz = objeto.getClass(); // Classe do objeto
		Field campo = null; // Campo a ser requisitado.
		Method metodo; // Metodo possivel de ser invocado.
		
		/*
		 * Caso o campo nao seja da classe,
		 * pega o da superclasse.
		 */
		do{
			try{
				campo = clazz.getDeclaredField(Util.getNomeCampo(atributo)); // Pega o campo referente ao atributo
			}catch(NoSuchFieldException noField){ 
				clazz = clazz.getSuperclass(); // Caso o campo nao exista, procura na superclasse.
			}
		}while(campo == null);
		
		try {
			campo.setAccessible(true); // Faz com que seja possivel acessar o campo.
			if (campo.isAnnotationPresent(MetodoAssociado.class)) { // Caso precise executar um metodo, executa.
				MetodoAssociado anotacao = campo.getAnnotation(MetodoAssociado.class); // Pega a anotacao do metodo.
				if(campo.isAnnotationPresent(Validacao.class)){
					Validacao anotacaoValidacao = campo.getAnnotation(Validacao.class); // Pega a anotacao de validacao do campo.
					if(campo.isAnnotationPresent(Conversao.class)){ // Caso precise realizar conversao 
						Conversao conversao = campo.getAnnotation(Conversao.class);
						Method converte = Conversor.class.getMethod(conversao.conversor(), novoValor.getClass());
						novoValor = converte.invoke(null, novoValor); // Converte o dado
					}
					Method valida = ValidadorDeDados.class.getMethod(anotacaoValidacao.metodo(), anotacaoValidacao.erro().getClass(), novoValor.getClass());
					valida.invoke(null, anotacaoValidacao.erro(), novoValor); // Valida o dado
				}
				metodo = clazz.getMethod(anotacao.set(), novoValor.getClass()); // Pega o metodo.
				metodo.invoke(objeto, novoValor); // Invoca o metodo pelo objeto.
			}else{
				throw new DadoInvalidoException(erroAtualizacao); // Caso nao tenha como atualizar 
			}
		} catch (IllegalArgumentException
				| IllegalAccessException | NoSuchMethodException
				| SecurityException e){
			//Caso o atributo passado nao seja compativel.
			throw new DadoInvalidoException(erroAtualizacao); 
		}catch(InvocationTargetException excecao) {
			throw new DadoInvalidoException(excecao.getCause().getMessage()); // Caso o metodo lance uma excecao.
		}
	}
	// REFLECTION

	public static void validaObjeto(Object objeto) throws DadoInvalidoException {
		Class clazz = objeto.getClass(); // Classe do objeto
		Field campos[] = null; // Campo a ser requisitado.
		
		/*
		 * Caso o campo nao seja da classe,
		 * pega o da superclasse.
		 */
		do{
			campos = clazz.getDeclaredFields();// Pega o campo referente ao atributo
			for(Field campo : campos){
				campo.setAccessible(true); // Faz com que seja possivel acessar o campo.
				Object valor = null;
				try{
					if(campo.isAnnotationPresent(Validacao.class)){
						Validacao anotacaoValidacao = campo.getAnnotation(Validacao.class); // Pega a anotacao de validacao do campo.
						if(anotacaoValidacao.get()){
							MetodoAssociado get = campo.getAnnotation(MetodoAssociado.class);
							Method metodoGet = clazz.getMethod(get.get());
							valor = metodoGet.invoke(objeto);
						}else{
							valor = campo.get(objeto);
						}
						Method valida = ValidadorDeDados.class.getMethod(anotacaoValidacao.metodo(), anotacaoValidacao.erro().getClass(), valor.getClass());
						valida.invoke(null, anotacaoValidacao.erro(), valor); // Valida o dado.
					}
				} catch(NoSuchMethodException | IllegalAccessException | 
						IllegalArgumentException excecao) {
				} catch(InvocationTargetException excecao){
					throw new DadoInvalidoException(excecao.getCause().getMessage()); // Caso o metodo lance uma excecao.
				}
			}			
			clazz = clazz.getSuperclass(); // Caso o campo nao exista, procura na superclasse.
		}while(clazz != Object.class);
	}
	
}
