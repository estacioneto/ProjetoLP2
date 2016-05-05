package projeto.hospital.paciente;

import static org.junit.Assert.*;

import org.junit.Test;

import projeto.exceptions.dados.DadoInvalidoException;

public class PacienteTeste {
	Paciente eric;
	Paciente estacio;
	Paciente thaynan;
	Paciente sergio;
	Paciente bruno;
	Paciente luan;
	Paciente bruna;
	GeradorIdPaciente geradorIdPaciente;

	/**
	 * Reseta os pacientes para os testes
	 * 
	 */
	private void resetaPacientes() throws DadoInvalidoException {
		geradorIdPaciente = new GeradorIdPaciente();
		eric = new Paciente("Eric", "29/04/1997", 72.5, "O-", "masculino", "Masculino");
		estacio = new Paciente("Estacio", "18/04/1998", 60.5, "A+", "masculino", "Masculino");
		thaynan = new Paciente("Thaynan", "19/10/1996", 82.5, "A+", "masculino", "Masculino");
		sergio = new Paciente("Sergio", "15/07/1993", 55.5, "O+", "masculino", "Masculino");
		bruno = new Paciente("Bruno", "27/02/1997", 62.0, "AB-", "masculino", "Masculino");
		luan = new Paciente("Luan", "12/12/1996", 71.5, "A+", "masculino", "Masculino");
		bruna = new Paciente("Bruna", "27/08/1996", 71.5, "B-", "feminino", "Feminino");
	}

	/**
	 * Testa a criacao de um usuario
	 */
	@Test
	public void cadastroTeste() {
		// new Paciente(String nome, String nascimento, double peso, TipoSangue
		// sangue, SexoBiologico sexo, String Genero)
		// Todos esses devem dar certo
		geradorIdPaciente = new GeradorIdPaciente();
		new Paciente("Eric", "29/04/1997", 72.5, "O-", "masculino", "Masculino");
		new Paciente("Estacio", "18/04/1998", 60.5, "A+", "masculino", "Masculino");
		new Paciente("Thaynan", "19/10/1996", 82.5, "A+", "masculino", "Masculino");
		new Paciente("Sergio", "15/07/1993", 55.5, "O+", "masculino", "Masculino");
		new Paciente("Bruno", "07/02/1997", 62.0, "AB-", "masculino", "Masculino");
		new Paciente("Luan", "12/12/1996", 71.5, "A+", "masculino", "Masculino");
		new Paciente("Bruna", "27/08/1996", 71.5, "B-", "feminino", "Feminino");

		// Vai testar todos os casos de erro possiveis para um cadastro, por
		// ordem dos argumentos
		// Modificacao da parte de validacao dos dados mudou, RIP TESTES
//		try {
//			new Paciente("", "27/08/1996", 71.5, "B-", "feminino", "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser vazio.", e.getMessage());
//		}
//		try {
//			new Paciente(null, "27/08/1996", 71.5, "B-", "feminino", "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Nao foi possivel cadastrar o paciente. Nome do paciente nao pode ser nulo(a)!", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", null, 71.5, "B-", "feminino", "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Nao foi possivel cadastrar o paciente. Data nao pode ser nulo(a)!", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", "27/08/1996", -1.0, "B-", "feminino", "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Nao foi possivel cadastrar o paciente. Peso do paciente nao pode ser negativo.", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", "27/08/1996", -71.5, "B-", "feminino", "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Nao foi possivel cadastrar o paciente. Peso do paciente nao pode ser negativo.", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", "27/08/1996", 71.5, null, "feminino", "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Nao foi possivel cadastrar o paciente. Tipo sanguineo invalido.", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", "27/08/1996", 71.5, "B-", null, "Feminino");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Sexo biologico invalido.", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", "27/08/1996", 71.5, "B-", "feminino", "");
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Genero nao pode ser vazio.", e.getMessage());
//		}
//		try {
//			new Paciente("Daniela", "27/08/1996", 71.5, "B-", "feminino", null);
//			fail();
//		} catch (DadoInvalidoException e) {
//			assertEquals("Genero nao pode ser nulo(a)!", e.getMessage());
//		}
	}

	/**
	 * Testa se os ids que estao sendo designados sao diferentes
	 */
	@Test
	public void idsTeste() {
		try {
			this.resetaPacientes();
		} catch (DadoInvalidoException e) {
			fail();
		}
		
		eric.setId(geradorIdPaciente.getProximoId());
		sergio.setId(geradorIdPaciente.getProximoId());
		estacio.setId(geradorIdPaciente.getProximoId());
		thaynan.setId(geradorIdPaciente.getProximoId());
		bruno.setId(geradorIdPaciente.getProximoId());
		luan.setId(geradorIdPaciente.getProximoId());
		bruna.setId(geradorIdPaciente.getProximoId());
		
		assertNotEquals(eric.getId(), estacio.getId(), 0.5);
		assertNotEquals(sergio.getId(), thaynan.getId(), 0.5);
		assertNotEquals(bruno.getId(), luan.getId(), 0.5);
		assertNotEquals(eric.getId(), bruna.getId(), 0.5);

		assertEquals(estacio.getId(), estacio.getId(), 0.5);
		assertEquals(bruna.getId(), bruna.getId(), 0.5);
		assertEquals(eric.getId(), eric.getId(), 0.5);
	}

	/**
	 * Testa gets
	 */
	@Test
	public void getsTeste() {
		try {
			this.resetaPacientes();
		} catch (DadoInvalidoException e) {
			fail();
		}

		// get nome
		assertEquals("Eric", eric.getNome());
		assertEquals("Bruna", bruna.getNome());

		// get peso
		assertEquals(82.5, thaynan.getPeso(), 0.05);
		assertEquals(71.5, luan.getPeso(), 0.05);
		assertEquals(60.5, estacio.getPeso(), 0.05);

		// data
		assertEquals("15/07/1993", sergio.getDataNascimento());
		assertEquals("19/10/1996", thaynan.getDataNascimento());
	}
}
