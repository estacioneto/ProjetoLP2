package projeto.farmacia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

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
					"Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("   ", 9.98, 550,
					"analgesico,antiemetico,antitermico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Erro no cadastro de medicamento. Nome do medicamento nao pode ser vazio.");
		}
		try {
			Medicamento isotretinoina = new MedicamentoGenerico(null, 79.90,
					200, "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Erro no cadastro de medicamento. Nome do medicamento nao pode ser nulo(a)!");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", -9.8,
					550, "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Erro no cadastro de medicamento. Preco do medicamento nao pode ser negativo.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", 9.8,
					-10, "analgesico");
			fail();
		} catch (Exception e) {
			assertEquals(
					e.getMessage(),
					"Erro no cadastro de medicamento. Quantidade do medicamento nao pode ser negativo.");
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

		} catch (Exception e) {
			fail();
		}
	}
}