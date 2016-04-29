package projeto.hospital.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.util.Constantes;

public class ControllerTest {

	@Test
	public void testCadastrafuncionario() {
		Controller controller = new Controller();
		
		try{
			controller.cadastrafuncionario("Estacio", "Diretor", "18/04/1998");
			controller.cadastrafuncionario("Eric", "Medico", "29/04/1997");
		}catch(DadoInvalidoException dadoInvalido){
			fail("Nao deveria lancar excecao: " + dadoInvalido.getMessage());
		}
		try{
			controller.cadastrafuncionario("Thaynan", "Diretor", "00/00/000");
			fail("Deveria lancar excecao!");
		}catch(DadoInvalidoException dadoInvalido){
			Assert.assertEquals("Data de nascimento nao eh valida!", dadoInvalido.getMessage());
		}
		
		controller.realizaLogin("12016001", "19981201");
		controller.realizaLogin("22016002", "19972201");
	}

	@Test
	public void testNovaMatricula() {
		Controller controller = new Controller();
		
		Assert.assertEquals("12016001", controller.novaMatricula(Constantes.CODIGO_DIRETOR));
		Assert.assertEquals("12016002", controller.novaMatricula(Constantes.CODIGO_DIRETOR));
		Assert.assertEquals("22016003", controller.novaMatricula(Constantes.CODIGO_MEDICO));
		Assert.assertEquals("22016004", controller.novaMatricula(Constantes.CODIGO_MEDICO));
		Assert.assertEquals("12016005", controller.novaMatricula(Constantes.CODIGO_DIRETOR));
		Assert.assertEquals("32016006", controller.novaMatricula(Constantes.CODIGO_TECNICO));
		Assert.assertEquals("32016007", controller.novaMatricula(Constantes.CODIGO_TECNICO));
	}

}
