package projeto.farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import projeto.farmacia.Medicamento;
import projeto.farmacia.MedicamentoGenerico;
import projeto.farmacia.MedicamentoReferencia;

public class MedicamentoTest {

	private Medicamento apracur;
	private Medicamento dimeticona;

	/**
	 * Criacao de medicamentos validos que serao utilizados em varios testes.
	 */
	@Before
	public void inicializaObjetosValidos() {
		try {
			apracur = new MedicamentoReferencia("Apracur", 7.18, 300,
					"analgesico,hormonal");
			dimeticona = new MedicamentoGenerico("Dimeticona", 14.90, 200,
					"analgesico,antiemetico");
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
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", 9.98,
					550, "analgesico,antiemetico,antitermico");
			Medicamento dorflex = new MedicamentoReferencia("Dorflex", 10.50,
					600, "antibiotico");
			Medicamento morfina = new MedicamentoReferencia("Morfina", 374.90,
					100, "analgesico,antitermico");
			Medicamento roacutan = new MedicamentoReferencia("Roacutan",
					189.80, 350, "analgesico,antiemetico");
			Medicamento viagra = new MedicamentoReferencia("Viagra", 48.90, 70,
					"hormonal,antitermico");
			Medicamento neosaldina = new MedicamentoReferencia("Neosaldina",
					19.62, 200, "analgesico,antiinflamatorio");
			Medicamento claril = new MedicamentoReferencia("Claril", 10.38,
					172, "antiinflamatorio");
			Medicamento prednisona = new MedicamentoReferencia("Prednisona",
					10.38, 172, "antitermico");

			Medicamento isotretinoina = new MedicamentoGenerico(
					"Isotretinoina", 79.90, 200,
					"analgesico,antiemetico,antitermico");
			Medicamento aciclovir = new MedicamentoGenerico("Aciclovir", 10.50,
					600, "analgesico,antitermico");
			Medicamento albendazol = new MedicamentoGenerico("Albendazol",
					374.90, 100, "analgesico");
			Medicamento amoxicilina = new MedicamentoGenerico("Amoxicilina",
					189.80, 350, "analgesico,antiemetico,antitermico");
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Teste de criacao de medicamentos invalidos
	 */
	@Test
	public void testCriaMedicamentosInvalidos() {
		try {
			Medicamento dipirona = new MedicamentoReferencia("", 9.98, 550,
					"analgesico,antiemetico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nome de medicamento nao pode ser nulo ou vazio.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("   ", 9.98, 550,
					"analgesico,antiemetico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nome de medicamento nao pode ser nulo ou vazio.");
		}
		try {
			Medicamento isotretinoina = new MedicamentoGenerico(null, 79.90,
					200, "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nome de medicamento nao pode ser nulo ou vazio.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", -9.8,
					550, "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao ha medicamento com valor negativo.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", 9.8,
					0, "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao ha quantidade nula ou negativa de medicamento.");
		}
	}

	/**
	 * Verifica a corretude dos atributos dos objetos.
	 */
	@Test
	public void TestaEstadosDoObjeto() {

		try {
			Medicamento prednisona = new MedicamentoReferencia("Prednisona",
					10.38, 172, "antitermico");
			Medicamento isotretinoina = new MedicamentoGenerico(
					"Isotretinoina", 79.90, 200,
					"analgesico,antiemetico,antitermico");
			Medicamento aciclovir = new MedicamentoGenerico("Aciclovir", 10.50,
					600, "analgesico,antitermico");

			assertEquals("Apracur", apracur.getNome());
			assertEquals("Dimeticona", dimeticona.getNome());
			assertEquals("Isotretinoina", isotretinoina.getNome());
			assertEquals("Prednisona", prednisona.getNome());

			assertEquals(8.94, dimeticona.calculaPreco(), 0.01);// Medicamento
																// generico
																// desconto 40%
																// (14.90 * 0.6
																// = 8.94)
			assertEquals(7.18, apracur.calculaPreco(), 0.01);
			assertEquals(47.94, isotretinoina.calculaPreco(), 0.01);// Medicamento
																	// generico
																	// desconto
																	// 40%
																	// (79.90 *
																	// 0.6 =
																	// 47.94)
			assertEquals(6.3, aciclovir.calculaPreco(), 0.01);// Medicamento
																// generico
																// desconto 40%
																// (10.50 * 0.6
																// = 6.3)

			assertEquals(300, apracur.getQuantidade());
			assertEquals(200, isotretinoina.getQuantidade());
			assertEquals(200, dimeticona.getQuantidade());
			assertEquals(600, aciclovir.getQuantidade());
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Metodo responsavel por testar a adicao ou remocao de categorias de um
	 * medicamento, e verificar se adicao foi possivel
	 */
	@Test
	public void adcionaOuRemoveCategoria() {
		try {
			Medicamento prednisona = new MedicamentoReferencia("Prednisona",
					10.38, 172, "antitermico");
			Medicamento isotretinoina = new MedicamentoGenerico(
					"Isotretinoina", 79.90, 200,
					"analgesico,antiemetico,antitermico");

			assertTrue(prednisona.addCategoria("analgesico"));
			assertTrue(prednisona.addCategoria("hormonal"));
			assertFalse(prednisona.addCategoria("analgesico"));

			assertTrue(isotretinoina.addCategoria("hormonal"));
			assertFalse(isotretinoina.addCategoria("hormonal"));

			assertFalse(apracur.addCategoria("analgesico"));
			assertTrue(apracur.addCategoria("antibiotico"));
			assertFalse(apracur.addCategoria("antibiotico"));

			assertTrue(prednisona.removeCategoria("analgesico"));
			assertTrue(prednisona.removeCategoria("hormonal"));
			assertFalse(prednisona.removeCategoria("antiemetico"));

			assertTrue(isotretinoina.removeCategoria("hormonal"));
			assertFalse(isotretinoina.removeCategoria("hormonal"));

			assertTrue(apracur.removeCategoria("analgesico"));
			assertTrue(apracur.removeCategoria("antibiotico"));
			assertTrue(apracur.removeCategoria("hormonal"));
			assertFalse(apracur.removeCategoria("analgesico"));
			assertFalse(apracur.removeCategoria("antibiotico"));

			assertTrue(isotretinoina.addCategoria("hormonal"));
			assertTrue(apracur.addCategoria("hormonal"));
		} catch (Exception e) {
			fail();
		}
		try {
			apracur.addCategoria(null);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao eh permitido a adicao de uma categoria nula ou vazia.");
		}
	}

	/**
	 * Metodo responsavel por testar a busca de categorias de um medicamento
	 */
	@Test
	public void buscaCategoria() {
		try {
			Medicamento isotretinoina = new MedicamentoGenerico(
					"Isotretinoina", 79.90, 200,
					"analgesico,antiemetico,antitermico");

			assertTrue(isotretinoina.contemCategoria("antiemetico"));
			assertFalse(isotretinoina.contemCategoria("hormonal"));
			assertFalse(isotretinoina.contemCategoria("antibiotico"));

			assertTrue(apracur.contemCategoria("analgesico"));
			assertTrue(apracur.contemCategoria("hormonal"));
			assertFalse(apracur.contemCategoria("antibiotico"));
			assertFalse(apracur.contemCategoria("antiinflamatorio"));

			assertTrue(dimeticona.contemCategoria("analgesico"));
			assertTrue(dimeticona.contemCategoria("antiemetico"));
			assertFalse(dimeticona.contemCategoria("antibiotico"));
			assertFalse(dimeticona.contemCategoria(null));

			isotretinoina.removeCategoria("antiemetico");
			isotretinoina.removeCategoria("hormonal");
			apracur.removeCategoria("analgesico");
			apracur.removeCategoria("hormonal");

			assertFalse(isotretinoina.contemCategoria("antiemetico"));
			assertFalse(apracur.contemCategoria("analgesico"));
			assertFalse(isotretinoina.contemCategoria("hormonal"));
			assertFalse(apracur.contemCategoria("hormonal"));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Teste com o intuito de verficiar se um determinada quatidade de
	 * medicamento foi adicionado ou removido
	 */
	@Test
	public void addOuDiminuiQtdMedicamento() {

		try {
			Medicamento isotretinoina = new MedicamentoGenerico(
					"Isotretinoina", 79.90, 200,
					"analgesico,antiemetico,antitermico");

			apracur.recebeQutMedicamentos(100);
			isotretinoina.recebeQutMedicamentos(200);
			dimeticona.recebeQutMedicamentos(120);

			assertEquals(apracur.getQuantidade(), 400);// 300 + 100 = 400
			assertEquals(dimeticona.getQuantidade(), 320);// 200 + 120 = 320
			assertEquals(isotretinoina.getQuantidade(), 400);// 200 + 200 = 400

			apracur.pegaQutMedicamentos(183);
			dimeticona.pegaQutMedicamentos(302);
			isotretinoina.pegaQutMedicamentos(298);

			assertEquals(apracur.getQuantidade(), 217);// 400 - 183 = 217
			assertEquals(dimeticona.getQuantidade(), 18);// 320 - 302 = 18
			assertEquals(isotretinoina.getQuantidade(), 102);// 400 - 298 = 102

			apracur.recebeQutMedicamentos(122);
			apracur.pegaQutMedicamentos(78);
			isotretinoina.recebeQutMedicamentos(93);
			isotretinoina.pegaQutMedicamentos(100);
			dimeticona.recebeQutMedicamentos(48);
			dimeticona.pegaQutMedicamentos(20);

			assertEquals(apracur.getQuantidade(), 261);// 217 + 122 - 78 = 261
			assertEquals(dimeticona.getQuantidade(), 46);// 18 + 48 - 20 = 46
			assertEquals(isotretinoina.getQuantidade(), 95);// 102 + 93 - 100 =
															// 95

			apracur.pegaQutMedicamentos(261);

			assertEquals(apracur.getQuantidade(), 0);// 261 - 261 = 0
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Teste com o intuito de verficiar se um determinada quatidade invalida de
	 * medicamento foi adicionado ou removido
	 */
	@Test
	public void addOuDiminuiQtdMedicamentoInvalido() {

		try {
			apracur.recebeQutMedicamentos(-122);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao existe quantidade negativa ou nula.");
		}
		try {
			apracur.pegaQutMedicamentos(-122);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao existe quantidade negativa ou nula.");
		}
		try {
			apracur.pegaQutMedicamentos(301);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao ha medicamentos suficientes.");
		}
		try {
			apracur.recebeQutMedicamentos(0);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao existe quantidade negativa ou nula.");
		}
		try {
			apracur.pegaQutMedicamentos(0);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao existe quantidade negativa ou nula.");
		}
	}

}