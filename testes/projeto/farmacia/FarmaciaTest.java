package projeto.farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import projeto.hospital.gerencia.farmacia.Farmacia;
import projeto.hospital.gerencia.farmacia.medicamento.Medicamento;
import projeto.util.MensagensDeErro;

public class FarmaciaTest {

	private Farmacia farmacia;

	/**
	 * Metodo responsavel por criar uma farmacia e adicionar medicamentos que
	 * serao utilizados por todos os testes.
	 */
	@Before
	public void inicializaFarmacia() {
		try {
			farmacia = new Farmacia();
			farmacia.addMedicamento("Isotretinoina", 79.90, 200, "generico",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Morfina", 374.90, 100, "referencia",
					"analgesico,antitermico");
			farmacia.addMedicamento("Prednisona", 10.38, 172, "generico",
					"antitermico");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Teste de criacao de medicamentos validos
	 */
	@Test
	public void testCriaMedicamentos() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "Referencia",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Dorflex", 10.50, 600, "Referencia",
					"antibiotico");
			farmacia.addMedicamento("Morfina", 374.90, 100, "Referencia",
					"analgesico,antitermico");
			farmacia.addMedicamento("Roacutan", 189.80, 350, "Referencia",
					"analgesico,antiemetico");
			farmacia.addMedicamento("Viagra", 48.90, 70, "Referencia",
					"hormonal,antitermico");
			farmacia.addMedicamento("Neosaldina", 19.62, 200, "Referencia",
					"analgesico,antiinflamatorio");
			farmacia.addMedicamento("Claril", 10.38, 172, "Referencia",
					"antiinflamatorio");
			farmacia.addMedicamento("Prednisona", 10.38, 172, "Referencia",
					"antitermico");
			farmacia.addMedicamento("Isotretinoina", 79.90, 200, "Generico",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "Generico",
					"analgesico,antitermico");
			farmacia.addMedicamento("Albendazol", 374.90, 100, "Generico",
					"analgesico");
			farmacia.addMedicamento("Amoxicilina", 189.80, 350, "Generico",
					"analgesico,antiemetico,antitermico");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Testa a adicao de medicamentos na farmacia
	 */
	@Test
	public void testAddMedicamentoValido() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "referencia",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Dorflex", 10.50, 600, "referencia",
					"antibiotico");
			farmacia.addMedicamento("Neosaldina", 19.62, 200, "referencia",
					"analgesico,antiinflamatorio");
			farmacia.addMedicamento("Albendazol", 374.90, 100, "generico",
					"analgesico");
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "generico",
					"analgesico,antitermico");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Testa a adicao de medicamentos com tipo invalido na farmacia
	 */
	@Test
	public void testAddMedicamentoTipoInvalido() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "referenciado",
					"analgesico,antiemetico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Erro no cadastro de medicamento. Tipo do medicamento invalido.");
		}
		try {
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "",
					"analgesico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Erro no cadastro de medicamento. Tipo do medicamento invalido.");
		}
		try {
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "genericoo",
					"analgesico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Erro no cadastro de medicamento. Tipo do medicamento invalido.");
		}
	}

	/**
	 * Teste de criacao de medicamentos invalidos
	 */
	@Test
	public void testCriaMedicamentosInvalidos() {
		try {
			farmacia.addMedicamento("", 9.98, 550, "Referencia",
					"analgesico,antiemetico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.",
					e.getMessage());
		}
		try {
			farmacia.addMedicamento("       ", 9.98, 550, "Referencia",
					"analgesico,antiemetico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.",
					e.getMessage());
		}
		try {
			farmacia.addMedicamento(null, 79.90, 200, "Generico", "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Nome do medicamento nao pode ser nulo(a)!",
					e.getMessage());
		}
		try {
			farmacia.addMedicamento("Dipirona", -9.8, 550, "Referencia",
					"analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo.",
					e.getMessage());
		}
		try {
			farmacia.addMedicamento("Dipirona", 9.8, -10, "Referencia",
					"analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Quantidade do medicamento nao pode ser negativo.",
					e.getMessage());
		}
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "Generico", "");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Categorias do medicamento nao pode ser vazio.",
					e.getMessage());
		}
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "Generico", null);
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro no cadastro de medicamento. Categorias do medicamento nao pode ser nulo(a)!",
					e.getMessage());
		}
	}

	/**
	 * Teste que verfica o retorno correto da lista de medicamentos de dada
	 * categoria
	 */
	@Test
	public void testConsultaMedicamentoPorCategoria() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "referencia",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Dorflex", 10.50, 600, "referencia",
					"antibiotico");
			farmacia.addMedicamento("Neosaldina", 19.62, 200, "referencia",
					"analgesico,antiinflamatorio");

			assertEquals(
					farmacia.consultaMedicamentoPorCategoria("analgesico"),
					"Dipirona,Neosaldina,Isotretinoina,Morfina");

			assertEquals(
					farmacia.consultaMedicamentoPorCategoria("antitermico"),
					"Prednisona,Dipirona,Isotretinoina,Morfina");

			assertEquals(
					farmacia.consultaMedicamentoPorCategoria("antitermico"),
					"Prednisona,Dipirona,Isotretinoina,Morfina");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Testa o retorno valido da busca de medicamentos por nome
	 */
	@Test
	public void testVerificaMedicamentoExistente() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "referencia",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Dorflex", 10.50, 600, "referencia",
					"antibiotico");
			farmacia.addMedicamento("Neosaldina", 19.62, 200, "generico",
					"analgesico,antiinflamatorio");

			assertEquals(
					farmacia.pegaMedicamento(
							MensagensDeErro.ERRO_MEDICAMENTO_INEXISTENTE,
							"Dipirona").toString(), farmacia
							.getListaMedicamentos().get(3).toString());
			assertEquals(
					farmacia.pegaMedicamento(
							MensagensDeErro.ERRO_MEDICAMENTO_INEXISTENTE,
							"Dorflex").toString(), farmacia
							.getListaMedicamentos().get(4).toString());
			assertEquals(
					farmacia.pegaMedicamento(
							MensagensDeErro.ERRO_MEDICAMENTO_INEXISTENTE,
							"Neosaldina").toString(), farmacia
							.getListaMedicamentos().get(5).toString());
			assertEquals(
					farmacia.pegaMedicamento(
							MensagensDeErro.ERRO_MEDICAMENTO_INEXISTENTE,
							"Prednisona").toString(), farmacia
							.getListaMedicamentos().get(2).toString());

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Testa se a ordem da lista eh correta
	 */
	@Test
	public void testOrdenaListaMedicamentos() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550, "referencia",
					"analgesico,antiemetico,antitermico");

			assertEquals(farmacia.consultaMedicamentosOrdemAlfabetica(),
					"Dipirona,Isotretinoina,Morfina,Prednisona");
			assertEquals(farmacia.consultaMedicamentosOrdemPreco(),
					"Prednisona,Dipirona,Isotretinoina,Morfina");

			farmacia.addMedicamento("Dorflex", 10.50, 600, "referencia",
					"antibiotico");
			farmacia.addMedicamento("Neosaldina", 19.62, 200, "referencia",
					"analgesico,antiinflamatorio");

			assertEquals(farmacia.consultaMedicamentosOrdemAlfabetica(),
					"Dipirona,Dorflex,Isotretinoina,Morfina,Neosaldina,Prednisona");
			assertEquals(farmacia.consultaMedicamentosOrdemPreco(),
					"Prednisona,Dipirona,Dorflex,Neosaldina,Isotretinoina,Morfina");

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Verifica a corretude dos atributos dos objetos.
	 */
	@Test
	public void TestaEstadosDoObjeto() {

		try {
			Farmacia farmacia = new Farmacia();
			farmacia.addMedicamento("Apracur", 7.18, 300, "Referencia",
					"analgesico,hormonal");
			farmacia.addMedicamento("Dimeticona", 14.90, 200, "Generico",
					"analgesico,antiemetico");
			farmacia.addMedicamento("Prednisona", 10.38, 172, "Referencia",
					"antitermico");
			farmacia.addMedicamento("Isotretinoina", 79.90, 200, "Generico",
					"analgesico,antiemetico,antitermico");
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "Generico",
					"analgesico,antitermico");

			assertEquals(farmacia.getInfoMedicamento("Nome", "Apracur"),
					farmacia.getListaMedicamentos().get(0).getNome());
			assertEquals(farmacia.getInfoMedicamento("Nome", "Dimeticona"),
					farmacia.getListaMedicamentos().get(1).getNome());
			assertEquals(farmacia.getInfoMedicamento("Nome", "Isotretinoina"),
					farmacia.getListaMedicamentos().get(3).getNome());
			assertEquals(farmacia.getInfoMedicamento("Nome", "Prednisona"),
					farmacia.getListaMedicamentos().get(2).getNome());

			assertEquals(
					8.94,
					(Double) farmacia.getInfoMedicamento("Preco", "Dimeticona"),
					0.01);// Medicamento
			// generico
			// desconto 40%
			// (14.90 * 0.6
			// = 8.94)
			assertEquals(7.18,
					(Double) farmacia.getInfoMedicamento("Preco", "Apracur"),
					0.01);
			assertEquals(47.94, (Double) farmacia.getInfoMedicamento("Preco",
					"Isotretinoina"), 0.01);// Medicamento
			// generico
			// desconto
			// 40%
			// (79.90 *
			// 0.6 =
			// 47.94)
			assertEquals(6.3,
					(Double) farmacia.getInfoMedicamento("Preco", "Aciclovir"),
					0.01);// Medicamento
			// generico
			// desconto 40%
			// (10.50 * 0.6
			// = 6.3)

			assertEquals(300,
					farmacia.getInfoMedicamento("Quantidade", "Apracur"));
			assertEquals(200,
					farmacia.getInfoMedicamento("Quantidade", "Isotretinoina"));
			assertEquals(200,
					farmacia.getInfoMedicamento("Quantidade", "Dimeticona"));
			assertEquals(600,
					farmacia.getInfoMedicamento("Quantidade", "Aciclovir"));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Metodo responsavel por testar a busca de categorias de um medicamento
	 */
	@Test
	public void buscaCategoria() {
		try {
			Farmacia farmacia = new Farmacia();
			farmacia.addMedicamento("Apracur", 7.18, 300, "Referencia",
					"analgesico,hormonal");
			farmacia.addMedicamento("Dimeticona", 14.90, 200, "Generico",
					"analgesico,antiemetico");
			farmacia.addMedicamento("Isotretinoina", 79.90, 200, "Generico",
					"analgesico,antiemetico,antitermico");

			Medicamento isotretinoina = farmacia.getListaMedicamentos().get(2);
			assertTrue(isotretinoina.contemCategoria("antiemetico"));
			assertFalse(isotretinoina.contemCategoria("hormonal"));
			assertFalse(isotretinoina.contemCategoria("antibiotico"));

			Medicamento apracur = farmacia.getListaMedicamentos().get(0);
			assertTrue(apracur.contemCategoria("analgesico"));
			assertTrue(apracur.contemCategoria("hormonal"));
			assertFalse(apracur.contemCategoria("antibiotico"));
			assertFalse(apracur.contemCategoria("antiinflamatorio"));

			Medicamento dimeticona = farmacia.getListaMedicamentos().get(1);
			assertTrue(dimeticona.contemCategoria("analgesico"));
			assertTrue(dimeticona.contemCategoria("antiemetico"));
			assertFalse(dimeticona.contemCategoria("antibiotico"));
			assertFalse(dimeticona.contemCategoria(null));

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}