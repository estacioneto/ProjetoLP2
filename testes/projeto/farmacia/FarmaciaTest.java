package projeto.farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import projeto.farmacia.Farmacia;

public class FarmaciaTest {

	private Farmacia farmacia;

	@Before
	public void inicializaFarmacia() {
		try {
			farmacia = new Farmacia();
			farmacia.addMedicamento("Isotretinoina", 79.90, 200,
					"analgesico,antiemetico,antitermico", "generico");
			farmacia.addMedicamento("Morfina", 374.90, 100,
					"analgesico,antitermico", "referencia");
			farmacia.addMedicamento("Prednisona", 10.38, 172, "antitermico",
					"generico");
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
			farmacia.addMedicamento("Dipirona", 9.98, 550,
					"analgesico,antiemetico,antitermico", "referencia");
			farmacia.addMedicamento("Dorflex", 10.50, 600, "antibiotico",
					"referencia");
			farmacia.addMedicamento("Neosaldina", 19.62, 200,
					"analgesico,antiinflamatorio", "referencia");
			farmacia.addMedicamento("Albendazol", 374.90, 100, "analgesico",
					"generico");
			farmacia.addMedicamento("Aciclovir", 10.50, 600,
					"analgesico,antitermico", "generico");
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
			farmacia.addMedicamento("Dipirona", 9.98, 550,
					"analgesico,antiemetico,antitermico", "referenciado");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao existe este tipo de medicamento.");
		}
		try {
			farmacia.addMedicamento("Aciclovir", 10.50, 600,
					"analgesico,antitermico", "");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao existe este tipo de medicamento.");
		}
		try {
			farmacia.addMedicamento("Aciclovir", 10.50, 600,
					"analgesico,antitermico", "       ");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao existe este tipo de medicamento.");
		}
	}

	/**
	 * Teste que verfica o retorno correto da lista de medicamentos de dada
	 * categoria
	 */
	@Test
	public void testConsultaMedicamentoPorCategoria() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550,
					"analgesico,antiemetico,antitermico", "referencia");
			farmacia.addMedicamento("Dorflex", 10.50, 600,
					"antibiotico,analgesico", "referencia");
			farmacia.addMedicamento("Neosaldina", 19.62, 200,
					"analgesico,antiinflamatorio", "referencia");

			assertEquals(
					farmacia.consultaMedicamentoPorCategoria("analgesico"),
					"[Dipirona, Dorflex, Neosaldina, Isotretinoina, Morfina]");

			farmacia.addCategoriaMedicamento("Isotretinoina", "antiemetico");

			assertEquals(
					farmacia.consultaMedicamentoPorCategoria("antitermico"),
					"[Prednisona, Dipirona, Isotretinoina, Morfina]");

			assertEquals(
					farmacia.consultaMedicamentoPorCategoria("analgesico"),
					"[Dipirona, Dorflex, Neosaldina, Isotretinoina, Morfina]");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Testa o retorno valido da busca de medicamentos por nome
	 */
	@Test
	public void testBuscaMedicamento() {
		try {
			farmacia.addMedicamento("Dipirona", 9.98, 550,
					"analgesico,antiemetico,antitermico", "referencia");
			farmacia.addMedicamento("Dorflex", 10.50, 600,
					"antibiotico,analgesico", "referencia");
			farmacia.addMedicamento("Neosaldina", 19.62, 200,
					"analgesico,antiinflamatorio", "referencia");

			assertEquals(farmacia.buscaMedicamento("Dipirona"),
					"Medicamento: Dipirona; preco: 9,98; quantidade atual: 550; Tipo: Referencia;");
			assertEquals(farmacia.buscaMedicamento("Dorflex"),
					"Medicamento: Dorflex; preco: 10,50; quantidade atual: 600; Tipo: Referencia;");
			assertEquals(
					farmacia.buscaMedicamento("Isotretinoina"),
					"Medicamento: Isotretinoina; preco: 47,94; quantidade atual: 200; Tipo: Generico;");
			assertEquals(farmacia.buscaMedicamento("Morfina"),
					"Medicamento: Morfina; preco: 374,90; quantidade atual: 100; Tipo: Referencia;");
			assertEquals(farmacia.buscaMedicamento("Prednisona"),
					"Medicamento: Prednisona; preco: 6,23; quantidade atual: 172; Tipo: Generico;");
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
			farmacia.addMedicamento("Dipirona", 9.98, 550,
					"analgesico,antiemetico,antitermico", "referencia");

			assertEquals(farmacia.verificaMedicamentosOrdemAlfabetica(),
					farmacia.verificaMedicamentoExistente("Dipirona"));
			assertEquals(farmacia.verificaMedicamentosOrdemAlfabetica(),
					farmacia.verificaMedicamentoExistente("Isotretinoina"));
			assertEquals(farmacia.verificaMedicamentosOrdemPreco(),
					farmacia.verificaMedicamentoExistente("Morfina"));
			assertEquals(farmacia.verificaMedicamentosOrdemPreco(),
					farmacia.verificaMedicamentoExistente("Prednisona"));

		} catch (Exception e) {
			fail();
		}
	}

}