package projeto.bancoDeOrgaos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import projeto.hospital.gerencia.bancodeorgaos.BancoDeOrgaos;

public class BancoDeOrgaosTest {

	private BancoDeOrgaos bancoDeOrgaos;

	/**
	 * Metodo responsavel por cadastras orgaos validos que seram utilizados em
	 * todos os testes.
	 */
	@Before
	public void iniciaVariaveis() {
		bancoDeOrgaos = new BancoDeOrgaos();
		bancoDeOrgaos.cadastraOrgao("coracao", "A+");
		bancoDeOrgaos.cadastraOrgao("pulmao", "AB+");
		bancoDeOrgaos.cadastraOrgao("rins", "B-");
	}

	/**
	 * Metodo responsavel por testar o cadastro de orgaos validos e invalidos.
	 */
	@Test
	public void testCadastraOrgao() {
		try {
			bancoDeOrgaos.cadastraOrgao("coracao", "A-");
			bancoDeOrgaos.cadastraOrgao("pulmao", "B+");
			bancoDeOrgaos.cadastraOrgao("rins", "B+");
			bancoDeOrgaos.cadastraOrgao("coracao", "A+");
			bancoDeOrgaos.cadastraOrgao("cerebro", "AB+");
			bancoDeOrgaos.cadastraOrgao("rins", "O-");
			bancoDeOrgaos.cadastraOrgao("coracao", "AB+");
			bancoDeOrgaos.cadastraOrgao("pulmao", "B+");
			bancoDeOrgaos.cadastraOrgao("rins", "AB-");
		} catch (Exception e) {
			fail();
		}
		try {
			bancoDeOrgaos.cadastraOrgao("rins", "ABC-");
			fail();
		} catch (Exception e) {
			assertEquals(
					"O banco de orgaos apresentou um erro. Tipo sanguineo invalido.",
					e.getMessage());
		}
		try {
			bancoDeOrgaos.cadastraOrgao("  ", "ABC-");
			fail();
		} catch (Exception e) {
			assertEquals(
					"O banco de orgaos apresentou um erro. Nome do orgao nao pode ser vazio.",
					e.getMessage());
		}
		try {
			bancoDeOrgaos.cadastraOrgao("", "ABC-");
			fail();
		} catch (Exception e) {
			assertEquals(
					"O banco de orgaos apresentou um erro. Nome do orgao nao pode ser vazio.",
					e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por testar a remocao valida e invalida de orgaos.
	 */
	@Test
	public void testRetiraOrgao() {
		try {
			bancoDeOrgaos.retiraOrgao("coracao", "A+");
			bancoDeOrgaos.retiraOrgao("pulmao", "AB+");
			bancoDeOrgaos.retiraOrgao("rins", "B-");
		} catch (Exception e) {
			fail();
		}
		try {
			bancoDeOrgaos.retiraOrgao("coracao", "A-");
		} catch (Exception e) {
			assertEquals("Erro na retirada de orgaos. Orgao nao cadastrado.",
					e.getMessage());
		}
		try {
			bancoDeOrgaos.retiraOrgao("coracaoo", "A+");
		} catch (Exception e) {
			assertEquals("Erro na retirada de orgaos. Orgao nao cadastrado.",
					e.getMessage());
		}
		try {
			bancoDeOrgaos.retiraOrgao("pulmao", "C-");
		} catch (Exception e) {
			assertEquals(
					"Erro na retirada de orgaos. Tipo sanguineo invalido.",
					e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por testar o getOrgao com valores validos e invalidos.
	 */
	@Test
	public void testGetOrgao() {
		try {
			bancoDeOrgaos.getOrgao("coracao", "A+");
			bancoDeOrgaos.getOrgao("pulmao", "AB+");
			bancoDeOrgaos.getOrgao("rins", "B-");
		} catch (Exception e) {
			fail();
		}
		try {
			bancoDeOrgaos.getOrgao("coracao", "A-");
		} catch (Exception e) {
			assertEquals(
					"O banco de orgaos apresentou um erro. Orgao nao cadastrado.",
					e.getMessage());
		}
		try {
			bancoDeOrgaos.getOrgao("coracaoo", "A+");
		} catch (Exception e) {
			assertEquals(
					"O banco de orgaos apresentou um erro. Orgao nao cadastrado.",
					e.getMessage());
		}
	}

	/**
	 * Metodo responsavel por testar a busca de orgaos existentes.
	 */
	@Test
	public void testBuscaOrgao() {
		try {
			assertTrue(bancoDeOrgaos.buscaOrgao("coracao", "A+"));
			assertTrue(bancoDeOrgaos.buscaOrgao("pulmao", "AB+"));
			assertTrue(bancoDeOrgaos.buscaOrgao("rins", "B-"));
			assertFalse(bancoDeOrgaos.buscaOrgao("rins", "B+"));
			assertFalse(bancoDeOrgaos.buscaOrgao("coracao", "B+"));

			bancoDeOrgaos.cadastraOrgao("coracao", "B+");
			bancoDeOrgaos.cadastraOrgao("rins", "B+");

			assertTrue(bancoDeOrgaos.buscaOrgao("rins", "B+"));
			assertTrue(bancoDeOrgaos.buscaOrgao("coracao", "B+"));

			bancoDeOrgaos.retiraOrgao("coracao", "B+");
			assertFalse(bancoDeOrgaos.buscaOrgao("coracao", "B+"));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Metodo responsavel por testar a realizacao da busca de orgaos que possuem
	 * um determinado tipo.
	 */
	@Test
	public void testBuscaOrgPorSangue() {
		try {
			assertEquals("coracao", bancoDeOrgaos.buscaOrgPorSangue("A+"));
			assertEquals("pulmao", bancoDeOrgaos.buscaOrgPorSangue("AB+"));
			assertEquals("rins", bancoDeOrgaos.buscaOrgPorSangue("B-"));

			bancoDeOrgaos.cadastraOrgao("cerebro", "A+");
			bancoDeOrgaos.cadastraOrgao("rins", "A+");
			assertEquals("coracao,cerebro,rins",
					bancoDeOrgaos.buscaOrgPorSangue("A+"));

			bancoDeOrgaos.retiraOrgao("rins", "A+");
			assertEquals("coracao,cerebro",
					bancoDeOrgaos.buscaOrgPorSangue("A+"));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Metodo responsavel por testar se o retorno da quantidade de um
	 * determinado orgao pelo qtdOrgaos eh correto.
	 */
	@Test
	public void testQtdOrgaos() {
		try {
			assertEquals((Integer) 1, bancoDeOrgaos.qtdOrgaos("coracao"));
			assertEquals((Integer) 1, bancoDeOrgaos.qtdOrgaos("pulmao"));
			assertEquals((Integer) 1, bancoDeOrgaos.qtdOrgaos("rins"));

			bancoDeOrgaos.cadastraOrgao("coracao", "A+");
			bancoDeOrgaos.cadastraOrgao("coracao", "O-");
			bancoDeOrgaos.cadastraOrgao("rins", "O-");
			assertEquals((Integer) 3, bancoDeOrgaos.qtdOrgaos("coracao"));
			assertEquals((Integer) 2, bancoDeOrgaos.qtdOrgaos("rins"));

			bancoDeOrgaos.retiraOrgao("coracao", "A+");
			assertEquals((Integer) 2, bancoDeOrgaos.qtdOrgaos("coracao"));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Metodo responsavel por testar se a quantidade de orgaos no Banco de
	 * Orgaos esta correto.
	 */
	@Test
	public void testTotalOrgaosDisponiveis() {
		try {
			assertEquals((Integer) 3, bancoDeOrgaos.totalOrgaosDisponiveis());

			bancoDeOrgaos.cadastraOrgao("coracao", "A-");
			bancoDeOrgaos.cadastraOrgao("pulmao", "B+");
			bancoDeOrgaos.cadastraOrgao("rins", "B+");
			bancoDeOrgaos.cadastraOrgao("coracao", "A+");
			bancoDeOrgaos.cadastraOrgao("cerebro", "AB+");
			bancoDeOrgaos.cadastraOrgao("rins", "O-");
			assertEquals((Integer) 9, bancoDeOrgaos.totalOrgaosDisponiveis());

			bancoDeOrgaos.retiraOrgao("coracao", "A+");
			bancoDeOrgaos.retiraOrgao("coracao", "A-");
			assertEquals((Integer) 7, bancoDeOrgaos.totalOrgaosDisponiveis());
		} catch (Exception e) {
			fail();
		}
	}

}
