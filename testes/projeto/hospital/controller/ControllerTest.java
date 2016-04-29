package projeto.hospital.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.dados.ObjetoNuloException;
import projeto.exceptions.dados.StringVaziaException;
import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.util.Constantes;

public class ControllerTest {

	@Test
	public void testCadastraEDemitefuncionario() {
		Controller controller = new Controller();

		try {
			Assert.assertTrue(controller.cadastraFuncionario("Estacio",
					"Diretor", "18/04/1998"));
			Assert.assertTrue(controller.cadastraFuncionario("Eric", "Medico",
					"29/04/1997"));
		} catch (DadoInvalidoException dadoInvalido) {
			fail("Nao deveria lancar excecao: " + dadoInvalido.getMessage());
		}
		try {
			controller.cadastraFuncionario("Thaynan", "Diretor", "00/00/000");
			fail("Deveria lancar excecao!");
		} catch (DadoInvalidoException dadoInvalido) {
			Assert.assertEquals("Data de nascimento nao eh valida!",
					dadoInvalido.getMessage());
		}
		try {
			controller.acessaSistema("12016001", "19981201");
			controller.acessaSistema("22016002", "19972201");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("Nao deveria lancar execao: " + acessoBloqueado.getMessage());
		}
	}

	@Test
	public void testRealizaLogin() {
		Controller controller = new Controller();

		Assert.assertTrue(controller.cadastraFuncionario("Estacio", "Diretor",
				"18/04/1998"));
		Assert.assertTrue(controller.cadastraFuncionario("Eric", "Medico",
				"29/04/1997"));
		Assert.assertTrue(controller.cadastraFuncionario("Estacio", "Diretor",
				"18/04/1998"));
		Assert.assertTrue(controller.cadastraFuncionario("Eric", "Medico",
				"29/04/1997"));
		Assert.assertTrue(controller.cadastraFuncionario("Eric", "Diretor",
				"29/04/1997"));
		Assert.assertTrue(controller.cadastraFuncionario("Eric",
				"Tecnico Administrativo", "29/04/1997"));
		Assert.assertTrue(controller.demiteFuncionario("12016003", "19981201",
				"12016001"));
		try {
			controller.demiteFuncionario("22016003", "19981201", "12016001");
		} catch (OperacaoInvalidaException operacaoInvalida) {
			fail("Nao deveria lancar essa excecao!");
		} catch (AcessoBloqueadoException acessoBloqueado){
			Assert.assertEquals("Matricula nao cadastrada!", acessoBloqueado.getMessage());
		}
		
		try {
			controller.demiteFuncionario("12016003", "19981201", "12016001");
		} catch (OperacaoInvalidaException operacaoInvalida) {
			// Ja foi removido
			Assert.assertEquals("Matricula nao cadastrada!", operacaoInvalida.getMessage());
		} catch (AcessoBloqueadoException acessoBloqueado){
			fail("Nao deveria lancar essa excecao: " + acessoBloqueado.getMessage());
		}

		try {
			controller.acessaSistema("9999999999", "");
			fail("Deveria lancar excecao!");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("Nao deveria lancar essa excecao!");
		} catch (StringVaziaException stringVazia) {
			Assert.assertEquals("Senha nao pode ser vazio(a)!",
					stringVazia.getMessage());
		}

		try {
			controller.acessaSistema("9999999999", null);
			fail("Deveria lancar excecao!");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("Nao deveria lancar essa excecao!");
		} catch (StringVaziaException stringVazia) {
			fail("Nao deveria lancar essa excecao!");
		} catch (ObjetoNuloException objetoNulo) {
			Assert.assertEquals("Senha nao pode ser nulo(a)!",
					objetoNulo.getMessage());
		}

		try {
			controller.acessaSistema("12016001", "c041ebf8");
			fail("Deveria lancar excecao!");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			Assert.assertEquals("Matricula nao cadastrada!",
					acessoBloqueado.getMessage());
		} catch (StringVaziaException stringVazia) {
			fail("Nao deveria lancar essa excecao!");
		} catch (ObjetoNuloException objetoNulo) {
			fail("Nao deveria lancar essa excecao!");
		}

		try {
			controller.acessaSistema("", "A");
			fail("Deveria lancar excecao!");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("Nao deveria lancar essa excecao!");
		} catch (StringVaziaException stringVazia) {
			Assert.assertEquals("Matricula nao pode ser vazio(a)!",
					stringVazia.getMessage());
		} catch (ObjetoNuloException objetoNulo) {
			fail("Nao deveria lancar essa excecao!");
		}

		try {
			controller.acessaSistema(null, null);
			fail("Deveria lancar excecao!");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("Nao deveria lancar essa excecao!");
		} catch (StringVaziaException stringVazia) {
			fail("Nao deveria lancar essa excecao!");
		} catch (ObjetoNuloException objetoNulo) {
			Assert.assertEquals("Matricula nao pode ser nulo(a)!",
					objetoNulo.getMessage());
		}
	}

	@Test
	public void testNovaMatricula() {
		Controller controller = new Controller();

		Assert.assertEquals("12016001",
				controller.novaMatricula(Constantes.DIRETOR));
		Assert.assertEquals("12016002",
				controller.novaMatricula(Constantes.DIRETOR));
		Assert.assertEquals("22016003",
				controller.novaMatricula(Constantes.MEDICO));
		Assert.assertEquals("22016004",
				controller.novaMatricula(Constantes.MEDICO));
		Assert.assertEquals("12016005",
				controller.novaMatricula(Constantes.DIRETOR));
		Assert.assertEquals("32016006",
				controller.novaMatricula(Constantes.TECNICO));
		Assert.assertEquals("32016007",
				controller.novaMatricula(Constantes.TECNICO));
	}

}
