package projeto.paciente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import projeto.hospital.gerencia.funcionario.Funcionario;
import projeto.hospital.gerencia.prontuario.GerenciadorDePacienteProntuario;
import projeto.hospital.gerencia.prontuario.paciente.GeradorIdPaciente;

public class GerenciadorPacienteProntuarioTest {

	GeradorIdPaciente geradorIdPaciente;
	GerenciadorDePacienteProntuario gerenciador;
	Funcionario tecnico;

	/**
	 * Inicia o gerenciador de pacientes e prontuario e cadastra pacientes
	 * validos, os quais serao utilizados em todos os testes da classe.
	 * 
	 */
	@Before
	public void iniciaGerenciadorDePacienteProntuario() {
		this.gerenciador = new GerenciadorDePacienteProntuario();
		geradorIdPaciente = new GeradorIdPaciente();
		tecnico = new Funcionario("Ze cadastro", "Tecnico Administrativo",
				"10/10/1980");
		this.gerenciador.cadastraPaciente("Thaynan", "19/10/1996", 82.5,
				"masculino", "Masculino", "A+", tecnico);
		this.gerenciador.cadastraPaciente("Eric", "29/04/1997", 72.5,
				"masculino", "Masculino", "O-", tecnico);
		this.gerenciador.cadastraPaciente("Estacio", "18/04/1998", 60.5,
				"masculino", "Masculino", "A+", tecnico);
	}

	/**
	 * Metodo responsavel por testar o cadastro de pacientes validos.
	 */
	@Test
	public void testCadastraPaciente() {
		try {
			this.gerenciador.cadastraPaciente("Bruno", "27/02/1997", 62.0,
					"masculino", "Masculino", "AB-", tecnico);
			this.gerenciador.cadastraPaciente("Luan", "12/12/1996", 71.5,
					"masculino", "Masculino", "A+", tecnico);
			this.gerenciador.cadastraPaciente("Sergio", "15/07/1993", 55.5,
					"masculino", "Masculino", "O+", tecnico);
			this.gerenciador.cadastraPaciente("Bruna", "27/08/1996", 71.5,
					"feminino", "Feminino", "B-", tecnico);
		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * Metodo responsavel por testar o cadastro de pacientes invalidos.
	 */
	@Test
	public void testCadastraPacienteInvalido() {
		try {
			this.gerenciador.cadastraPaciente("  ", "19/10/1996", 82.5,
					"masculino", "Masculino", "A+", tecnico);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.");
		}
		try {
			this.gerenciador.cadastraPaciente("Eric", "129/04/1997", 72.5,
					"masculino", "Masculino", "O-", tecnico);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao foi possivel cadastrar o paciente. Data invalida.");
		}
		try {
			this.gerenciador.cadastraPaciente("Estacio", "18/04/1998", -60.5,
					"masculino", "Masculino", "A+", tecnico);
			fail();
		} catch (Exception e) {
			assertEquals(
					e.getMessage(),
					"Nao foi possivel cadastrar o paciente. Peso do paciente nao pode ser negativo.");
		}
		try {
			this.gerenciador.cadastraPaciente("Bruna", "27/08/1996", 71.5,
					"feminino", "Feminino", "C-", tecnico);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(),
					"Nao foi possivel cadastrar o paciente. Tipo sanguineo invalido.");
		}
		try {
			Funcionario medico = new Funcionario("Marcao", "Medico",
					"08/12/1998");
			this.gerenciador.cadastraPaciente("Bruna", "27/08/1996", 71.5,
					"feminino", "Feminino", "C-", medico);
			fail();
		} catch (Exception e) {
			assertEquals(
					e.getMessage(),
					"Nao foi possivel cadastrar o paciente. O funcionario Marcao nao tem permissao para cadastrar pacientes.");
		}
	}

	/**
	 * Metodo responsavel por testar se as informacoes pegas de um usuario estao
	 * corretas.
	 */
	@Test
	public void testGetInfoPaciente() {
		try {
			assertEquals("Thaynan", gerenciador.getInfoPaciente("1", "Nome"));
			assertEquals("Estacio", gerenciador.getInfoPaciente("3", "Nome"));
			assertEquals("1997-04-29", gerenciador.getInfoPaciente("2", "Data"));
			assertEquals("masculino", gerenciador.getInfoPaciente("2", "Sexo"));
			assertEquals("A+",
					gerenciador.getInfoPaciente("3", "TipoSanguineo"));
			assertEquals(82.5, gerenciador.getInfoPaciente("1", "Peso"));
		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * Metodo responsavel por testar se o id retornado eh correto.
	 */
	@Test
	public void testGetIdProntuarioPosicao() {
		try {
			assertEquals("3", gerenciador.getIdProntuarioPosicao(1));
			assertEquals("1", gerenciador.getIdProntuarioPosicao(2));
			assertEquals("2", gerenciador.getIdProntuarioPosicao(0));
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Metodo responsavel por testar se o ID do paciente esta correto.
	 */
	@Test
	public void testGetIdPaciente() {
		try {
			assertEquals("1", gerenciador.getIdPaciente("Thaynan"));
			assertEquals("2", gerenciador.getIdPaciente("Eric"));
			assertEquals("3", gerenciador.getIdPaciente("Estacio"));
			this.gerenciador.cadastraPaciente("Pedro", "19/02/1997", 62.0,
					"masculino", "Masculino", "AB-", tecnico);
			this.gerenciador.cadastraPaciente("Luana", "27/03/1997", 62.0,
					"feminino", "Feminino", "AB-", tecnico);
			assertEquals("5", gerenciador.getIdPaciente("Luana"));
			assertEquals("4", gerenciador.getIdPaciente("Pedro"));
		} catch (Exception e) {
			fail();
		}
		try {
			gerenciador.getIdPaciente("Lucas");
			fail();
		} catch (Exception e) {
			assertEquals(
					"Erro ao consultar prontuario. Paciente nao encontrado.",
					e.getMessage());
		}
	}

}
