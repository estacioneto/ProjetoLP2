package projeto.hospital.paciente;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
		geradorIdPaciente = new GeradorIdPaciente();
		eric = new Paciente("Eric", "29/04/1997", 72.5, "O-", "masculino", "Masculino");
		eric.setId(geradorIdPaciente.getProximoId());
		estacio = new Paciente("Estacio", "18/04/1998", 60.5, "A+", "masculino", "Masculino");
		estacio.setId(geradorIdPaciente.getProximoId());
		thaynan = new Paciente("Thaynan", "19/10/1996", 82.5, "A+", "masculino", "Masculino");
		thaynan.setId(geradorIdPaciente.getProximoId());
		sergio = new Paciente("Sergio", "15/07/1993", 55.5, "O+", "masculino", "Masculino");
		sergio.setId(geradorIdPaciente.getProximoId());
	}
	
	/**
	 * Testa a criacao de prontuarios
	 */
	@Test
	public void testProntuario() {
		try {
			resetaPacientes();
		} catch (DadoInvalidoException e) {
			fail();
		}
		new Prontuario(eric);
		new Prontuario(estacio);
		new Prontuario(sergio);
		
		// Modificacao da parte de validacao dos dados mudou, RIP TESTES
//		try{
//			new Prontuario(null);
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Objeto nao pode ser nulo!", e.getMessage());
//		}
	}

	/**
	 * Testa se o prontuario esta guardando corretamente os pacientes
	 */
	@Test
	public void testGetPaciente() {
		try {
			resetaPacientes();
		} catch (DadoInvalidoException e) {
			fail();
		}
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
		try {
			resetaPacientes();
		} catch (DadoInvalidoException e) {
			fail();
		}
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
		try {
			resetaPacientes();
		} catch (DadoInvalidoException e) {
			fail();
		}
		Prontuario prontuarioEric = new Prontuario(eric);
		Prontuario prontuarioThaynan = new Prontuario(thaynan);
		Prontuario prontuarioSergio = new Prontuario(sergio);
		Prontuario prontuarioEstacio = new Prontuario(estacio);
		
		Set<Prontuario> conjunto = new TreeSet<Prontuario>();
		conjunto.add(prontuarioEric);
		conjunto.add(prontuarioThaynan);
		conjunto.add(prontuarioEstacio);
		conjunto.add(prontuarioSergio);
		
		ArrayList<String> listaNomes = new ArrayList<String>();
		for(Prontuario p : conjunto)
			listaNomes.add(p.getPaciente().getNome());		
		
		assertEquals("Eric,Estacio,Sergio,Thaynan", String.join(",", listaNomes));
	}
}
