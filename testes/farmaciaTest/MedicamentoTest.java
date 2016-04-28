package farmaciaTest;

import static org.junit.Assert.*;

import org.junit.Test;

import farmacia.Medicamento;
import farmacia.MedicamentoGenerico;
import farmacia.MedicamentoReferencia;

public class MedicamentoTest {

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
}