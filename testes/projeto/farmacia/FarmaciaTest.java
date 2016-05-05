package projeto.farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import projeto.hospital.gerencia.farmacia.Farmacia;
import projeto.util.MensagensDeErro;

public class FarmaciaTest {

	private Farmacia farmacia;

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
			assertEquals(e.getMessage(), "Erro no cadastro de medicamento. " + "Nao existe este tipo de medicamento.");
		}
		try {
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "",
					"analgesico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Erro no cadastro de medicamento. " + "Nao existe este tipo de medicamento.");
		}
		try {
			farmacia.addMedicamento("Aciclovir", 10.50, 600, "genericoo",
					"analgesico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Erro no cadastro de medicamento. " + "Nao existe este tipo de medicamento.");
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

}