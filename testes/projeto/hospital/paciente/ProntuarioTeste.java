package projeto.hospital.paciente;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import projeto.exceptions.dados.DadoInvalidoException;

public class ProntuarioTeste {
	Paciente eric;
	Paciente estacio;
	Paciente thaynan;
	Paciente sergio;
	GeradorIdPaciente geradorIdPaciente;

	/**
	 * Reseta os pacientes para os testes
	 * 
	 */
	private void resetaPacientes() throws DadoInvalidoException {
		geradorIdPaciente = GeradorIdPaciente.getInstancia();
		eric = new Paciente("Eric", "29/04/1997", 72.5, "O-", "masculino", "Masculino");
		estacio = new Paciente("Estacio", "18/04/1998", 60.5, "A+", "masculino", "Masculino");
		thaynan = new Paciente("Thaynan", "19/10/1996", 82.5, "A+", "masculino", "Masculino");
		sergio = new Paciente("Sergio", "15/07/1993", 55.5, "O+", "masculino", "Masculino");
	}
	
	/**
	 * Testa a criacao de prontuarios
	 */
	@Test
	public void testProntuario() {
		resetaPacientes();
		new Prontuario(eric);
		new Prontuario(estacio);
		new Prontuario(sergio);
		
		try{
			new Prontuario(null);
		} catch(DadoInvalidoException e){
			assertEquals("", e.getMessage());
		}
	}

	/**
	 * Testa se o prontuario esta guardando corretamente os pacientes
	 */
	@Test
	public void testGetPaciente() {
		resetaPacientes();
		Prontuario prontuarioEric = new Prontuario(eric);
		Prontuario prontuarioThaynan = new Prontuario(thaynan);
		
		assertTrue(prontuarioEric.getPaciente().equals(eric));
		assertTrue(prontuarioThaynan.getPaciente().equals(thaynan));
	}

	/**
	 * Testa se os ids sao diferentes
	 */
	@Test
	public void testGetId() {
		resetaPacientes();
		Prontuario prontuarioEric = new Prontuario(eric);
		Prontuario prontuarioThaynan = new Prontuario(thaynan);
		Prontuario prontuarioSergio = new Prontuario(sergio);
		
		assertFalse(prontuarioEric.getId().equals(prontuarioThaynan.getId()));
		assertFalse(prontuarioSergio.getId().equals(prontuarioThaynan.getId()));
		assertTrue(prontuarioThaynan.getId().equals(prontuarioThaynan.getId()));
	}
	
	/**
	 * Testa se os prontuarios estao ficando orndenados
	 */
	@Test
	public void testOrdenacao(){
		Prontuario prontuarioEric = new Prontuario(eric);
		Prontuario prontuarioThaynan = new Prontuario(thaynan);
		Prontuario prontuarioSergio = new Prontuario(sergio);
		Prontuario prontuarioEstacio = new Prontuario(estacio);
		
		Set<Prontuario> conjunto = new TreeSet<Prontuario>();
		conjunto.add(prontuarioEric);
		conjunto.add(prontuarioThaynan);
		conjunto.add(prontuarioEstacio);
		conjunto.add(prontuarioSergio);
		
		assertEquals("", conjunto.toString());
	}
}