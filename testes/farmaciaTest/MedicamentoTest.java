package farmaciaTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import farmacia.Categorias;
import farmacia.Medicamento;
import farmacia.MedicamentoGenerico;
import farmacia.MedicamentoReferencia;

public class MedicamentoTest {

	private Medicamento apracur;
	private Medicamento dimeticona;
	
	/**
	 * Criacao de medicamentos validos que serao utilizados em varios testes.
	 */
	@Before
	public void inicializaObjetosValidos() {
		try{
			apracur = new MedicamentoReferencia("Apracur", 7.18, 300);
			dimeticona = new MedicamentoGenerico("Dimeticona", 14.90, 200);
		}catch(Exception e){
			fail();
		}
	}
	
	/**
	 * Teste de criacao de medicamentos validos 
	 */
	@Test
	public void testCriaMedicamentos() {
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", 9.98, 550);
			Medicamento dorflex = new MedicamentoReferencia("Dorflex", 10.50, 600);
			Medicamento morfina = new MedicamentoReferencia("Morfina", 374.90, 100);
			Medicamento roacutan = new MedicamentoReferencia("Roacutan", 189.80, 350);
			Medicamento viagra = new MedicamentoReferencia("Viagra", 48.90, 70);
			Medicamento neosaldina = new MedicamentoReferencia("Neosaldina", 19.62, 200);
			Medicamento claril = new MedicamentoReferencia("Claril", 10.38, 172);
			Medicamento prednisona = new MedicamentoReferencia("Prednisona", 10.38, 172);
			
			Medicamento isotretinoina = new MedicamentoGenerico("Isotretinoina", 79.90, 200);
			Medicamento aciclovir = new MedicamentoGenerico("Aciclovir", 10.50, 600);
			Medicamento albendazol = new MedicamentoGenerico("Albendazol", 374.90, 100);
			Medicamento amoxicilina = new MedicamentoGenerico("Amoxicilina", 189.80, 350);
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
			Medicamento dipirona = new MedicamentoReferencia("", 9.98, 550);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nome de medicamento nao pode ser nulo ou vazio.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("   ", 9.98, 550);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nome de medicamento nao pode ser nulo ou vazio.");
		}
		try {
			Medicamento isotretinoina = new MedicamentoGenerico(null, 79.90, 200);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nome de medicamento nao pode ser nulo ou vazio.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", -9.8, 550);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao ha medicamento com valor negativo.");
		}
		try {
			Medicamento dipirona = new MedicamentoReferencia("Dipirona", 9.8, 0);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao ha quantidade nula ou negativa de medicamento.");
		}
	}
	
	/**
	 * Verifica a corretude dos atributos dos objetos.
	 */
	@Test
	public void TestaEstadosDoObjeto(){
	
		try {
			Medicamento prednisona = new MedicamentoReferencia("Prednisona", 10.38, 172);
			Medicamento isotretinoina = new MedicamentoGenerico("Isotretinoina", 79.90, 200);
			Medicamento aciclovir = new MedicamentoGenerico("Aciclovir", 10.50, 600);
			
			assertEquals("Apracur", apracur.getNome());
			assertEquals("Dimeticona", dimeticona.getNome());
			assertEquals("Isotretinoina", isotretinoina.getNome());
			assertEquals("Prednisona", prednisona.getNome());
			
			assertEquals(8.94, dimeticona.calculaPreco(), 0.01);//Medicamento generico desconto 40% (14.90 * 0.6 = 8.94)
			assertEquals(7.18, apracur.calculaPreco(), 0.01);
			assertEquals(47.94, isotretinoina.calculaPreco(), 0.01);//Medicamento generico desconto 40% (79.90 * 0.6 = 47.94)
			assertEquals(6.3, aciclovir.calculaPreco(), 0.01);//Medicamento generico desconto 40% (10.50 * 0.6 = 6.3)
			
			assertEquals(300, apracur.getQuantidade());
			assertEquals(200, isotretinoina.getQuantidade());
			assertEquals(200, dimeticona.getQuantidade());
			assertEquals(600, aciclovir.getQuantidade());
		} catch (Exception e) {
			fail();
		}
	}
	
	/**
	 * Metodo responsavel por testar a adicao ou remocao de categorias de um medicamento, e verificar se adicao foi possivel
	 */
	@Test
	public void adcionaOuRemoveCategoria(){
		try {
			Medicamento prednisona = new MedicamentoReferencia("Prednisona", 10.38, 172);
			Medicamento isotretinoina = new MedicamentoGenerico("Isotretinoina", 79.90, 200);
			
			assertTrue(prednisona.addCategoria(Categorias.ANALGESICO));
			assertTrue(prednisona.addCategoria(Categorias.HORMONAL));
			assertFalse(prednisona.addCategoria(Categorias.ANALGESICO));
			
			assertTrue(isotretinoina.addCategoria(Categorias.HORMONAL));
			assertTrue(isotretinoina.addCategoria(Categorias.ANTIEMETICO));
			assertFalse(isotretinoina.addCategoria(Categorias.HORMONAL));
			assertFalse(isotretinoina.addCategoria(Categorias.HORMONAL));
			
			assertTrue(apracur.addCategoria(Categorias.ANALGESICO));
			assertTrue(apracur.addCategoria(Categorias.HORMONAL));
			assertTrue(apracur.addCategoria(Categorias.ANTIBIOTICO));
			assertTrue(apracur.addCategoria(Categorias.ANTIEMETICO));
			assertTrue(apracur.addCategoria(Categorias.ANTIINFLAMATORIO));
			assertTrue(apracur.addCategoria(Categorias.ANTITERMICO));
			assertFalse(apracur.addCategoria(Categorias.ANALGESICO));
			
			assertTrue(prednisona.removeCategoria(Categorias.HORMONAL));
			assertTrue(prednisona.removeCategoria(Categorias.ANALGESICO));
			assertFalse(prednisona.removeCategoria(Categorias.ANTIBIOTICO));
			assertFalse(prednisona.removeCategoria(Categorias.HORMONAL));
			
			assertTrue(isotretinoina.removeCategoria(Categorias.HORMONAL));
			assertTrue(isotretinoina.removeCategoria(Categorias.ANTIEMETICO));
			assertFalse(isotretinoina.removeCategoria(Categorias.ANTIEMETICO));
			assertFalse(isotretinoina.removeCategoria(Categorias.ANALGESICO));
			
			assertTrue(apracur.removeCategoria(Categorias.ANALGESICO));
			assertTrue(apracur.removeCategoria(Categorias.HORMONAL));
			assertFalse(apracur.removeCategoria(Categorias.HORMONAL));
			
			assertTrue(isotretinoina.addCategoria(Categorias.HORMONAL));
			assertTrue(apracur.addCategoria(Categorias.HORMONAL));
		} catch (Exception e) {
			fail();
		}
		try {
			apracur.addCategoria(null);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nao eh permitido a adicao de uma categoria nula.");
		}
	}
	
	/**
	 * Metodo responsavel por testar a busca de categorias de um medicamento
	 */
	@Test
	public void buscaCategoria(){
		try {
			Medicamento isotretinoina = new MedicamentoGenerico("Isotretinoina", 79.90, 200);
			
			isotretinoina.addCategoria(Categorias.HORMONAL);
			isotretinoina.addCategoria(Categorias.ANTIEMETICO);
			
			apracur.addCategoria(Categorias.ANALGESICO);
			apracur.addCategoria(Categorias.HORMONAL);
			apracur.addCategoria(Categorias.ANTIBIOTICO);
			
			dimeticona.addCategoria(Categorias.ANTIEMETICO);
			dimeticona.addCategoria(Categorias.ANALGESICO);
			dimeticona.addCategoria(Categorias.HORMONAL);
			
			assertTrue(isotretinoina.contemCategoria(Categorias.ANTIEMETICO));
			assertTrue(isotretinoina.contemCategoria(Categorias.HORMONAL));
			assertFalse(isotretinoina.contemCategoria(Categorias.ANTIBIOTICO));
			
			assertTrue(apracur.contemCategoria(Categorias.ANALGESICO));
			assertTrue(apracur.contemCategoria(Categorias.HORMONAL));
			assertFalse(apracur.contemCategoria(Categorias.ANTITERMICO));
			assertFalse(apracur.contemCategoria(Categorias.ANTIINFLAMATORIO));
			
			assertTrue(dimeticona.contemCategoria(Categorias.ANALGESICO));
			assertTrue(dimeticona.contemCategoria(Categorias.HORMONAL));
			assertFalse(dimeticona.contemCategoria(Categorias.ANTIBIOTICO));
			assertFalse(dimeticona.contemCategoria(null));
			
			isotretinoina.removeCategoria(Categorias.ANTIEMETICO);
			isotretinoina.removeCategoria(Categorias.HORMONAL);
			apracur.removeCategoria(Categorias.ANALGESICO);
			apracur.removeCategoria(Categorias.HORMONAL);
			
			assertFalse(isotretinoina.contemCategoria(Categorias.ANTIEMETICO));
			assertFalse(apracur.contemCategoria(Categorias.ANALGESICO));
			assertFalse(isotretinoina.contemCategoria(Categorias.HORMONAL));
			assertFalse(apracur.contemCategoria(Categorias.HORMONAL));
		} catch (Exception e) {
			fail();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}