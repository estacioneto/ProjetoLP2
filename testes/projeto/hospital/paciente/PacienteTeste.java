package projeto.hospital.paciente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.paciente.GeradorIdPaciente;
import projeto.paciente.Paciente;

public class PacienteTeste {
<<<<<<< HEAD
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
		geradorIdPaciente = GeradorIdPaciente.getInstancia();
		eric = new Paciente("Eric", "29/04/1997", 72.5, "O-", "masculino", "Masculino",
				geradorIdPaciente.getProximoId());
		estacio = new Paciente("Estacio", "18/04/1998", 60.5, "A+", "masculino", "Masculino",
				geradorIdPaciente.getProximoId());
		thaynan = new Paciente("Thaynan", "19/10/1996", 82.5, "A+", "masculino", "Masculino",
				geradorIdPaciente.getProximoId());
		sergio = new Paciente("Sergio", "15/07/1993", 55.5, "O+", "masculino", "Masculino",
				geradorIdPaciente.getProximoId());
		bruno = new Paciente("Bruno", "27/02/1997", 62, "AB-", "masculino", "Masculino",
				geradorIdPaciente.getProximoId());
		luan = new Paciente("Luan", "12/12/1996", 71.5, "A+", "masculino", "Masculino",
				geradorIdPaciente.getProximoId());
		bruna = new Paciente("Bruna", "27/08/1996", 71.5, "B-", "feminino", "Feminino",
				geradorIdPaciente.getProximoId());
	}

	/**
	 * Testa a criacao de um usuario
	 */
	@Test
	public void cadastroTeste() throws DadoInvalidoException {
		// new Paciente(String nome, String nascimento, double peso, TipoSangue
		// sangue, SexoBiologico sexo, String Genero)
		// Todos esses devem dar certo
		geradorIdPaciente = GeradorIdPaciente.getInstancia();
		new Paciente("Eric", "29/04/1997", 72.5, "O-", "masculino", "Masculino", geradorIdPaciente.getProximoId());
		new Paciente("Estacio", "18/04/1998", 60.5, "A+", "masculino", "Masculino", geradorIdPaciente.getProximoId());
		new Paciente("Thaynan", "19/10/1996", 82.5, "A+", "masculino", "Masculino", geradorIdPaciente.getProximoId());
		new Paciente("Sergio", "15/07/1993", 55.5, "O+", "masculino", "Masculino", geradorIdPaciente.getProximoId());
		new Paciente("Bruno", "07/02/1997", 62, "AB-", "masculino", "Masculino", geradorIdPaciente.getProximoId());
		new Paciente("Luan", "12/12/1996", 71.5, "A+", "masculino", "Masculino", geradorIdPaciente.getProximoId());
		new Paciente("Bruna", "27/08/1996", 71.5, "B-", "feminino", "Feminino", geradorIdPaciente.getProximoId());

		// Vai testar todos os casos de erro possiveis para um cadastro, por
		// ordem dos argumentos
		try {
			new Paciente("", "27/08/1996", 71.5, "B-", "feminino", "Feminino", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Nome nao pode ser vazio.", e.getMessage());
		}
		try {
			new Paciente(null, "27/08/1996", 71.5, "B-", "feminino", "Feminino", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Nome nao pode ser nulo(a)!", e.getMessage());
		}
		try {
			new Paciente("Daniela", null, 71.5, "B-", "feminino", "Feminino", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Data nao pode ser nulo(a)!", e.getMessage());
		}
		// TODO - devemos fazer uma verificacao de data minima valida?
		// try{
		// new Paciente("Daniela", LocalDate.of(1800, 8, 27), 71.5, "B-",
		// "feminino", "Feminino");
		// fail();
		// } catch(DadoInvalidoException e){
		// Assert.assertEquals("Data invalida.", e.getMessage());
		// }
		try {
			new Paciente("Daniela", "27/08/1996", -1, "B-", "feminino", "Feminino", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Peso nao pode ser negativo.", e.getMessage());
		}
		try {
			new Paciente("Daniela", "27/08/1996", -71.5, "B-", "feminino", "Feminino",
					geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Peso nao pode ser negativo.", e.getMessage());
		}
		try {
			new Paciente("Daniela", "27/08/1996", 71.5, null, "feminino", "Feminino", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Tipo sanguineo invalido.", e.getMessage());
		}
		try {
			new Paciente("Daniela", "27/08/1996", 71.5, "B-", null, "Feminino", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Sexo biologico invalido.", e.getMessage());
		}
		try {
			new Paciente("Daniela", "27/08/1996", 71.5, "B-", "feminino", "", geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Genero nao pode ser vazio.", e.getMessage());
		}
		try {
			new Paciente("Daniela", "27/08/1996", 71.5, "B-", "feminino", null, geradorIdPaciente.getProximoId());
			fail();
		} catch (DadoInvalidoException e) {
			assertEquals("Genero nao pode ser nulo(a)!", e.getMessage());
		}
	}

	/**
	 * Testa se os ids que estao sendo designados sao diferentes
	 */
	@Test
	public void idsTeste() throws DadoInvalidoException {
		this.resetaPacientes();

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
		this.resetaPacientes();

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
		
		// id
		assertNotEquals(eric.getId(), thaynan.getId(), 0.5);
		assertNotEquals(bruna.getId(), estacio.getId(), 0.5);
		assertNotEquals(luan.getId(), estacio.getId(), 0.5);

	}
=======
//	Paciente eric;
//	Paciente estacio;
//	Paciente thaynan;
//	Paciente sergio;
//	Paciente bruno;
//	Paciente luan;
//	Paciente bruna;
//	
//	private void resetaPacientes() throws DadoInvalidoException {
//		eric = new Paciente("Eric", LocalDate.of(1997, 04, 29), 72.5, TipoSangue.ONEGATIVO, SexoBiologico.MASCULINO, "Masculino");
//		estacio = new Paciente("Estacio", LocalDate.of(1998, 04, 18), 60.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		thaynan = new Paciente("Thaynan", LocalDate.of(1996, 10, 19), 82.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		sergio = new Paciente("Sergio", LocalDate.of(1993, 07, 15), 55.5, TipoSangue.OPOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		bruno = new Paciente("Bruno", LocalDate.of(1997, 02, 27), 62, TipoSangue.ABNEGATIVO, SexoBiologico.MASCULINO, "Masculino");
//		luan = new Paciente("Luan", LocalDate.of(1996, 12, 12), 71.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		bruna = new Paciente("Bruna", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//	}
//	
//	/**
//	 * Testa a criacao de um usuario
//	 */
//	@Test
//	public void cadastroTeste() throws DadoInvalidoException {
//		// new Paciente(String nome, String nascimento, double peso, TipoSangue sangue, SexoBiologico sexo, String Genero)
//		// Todos esses devem dar certo
//		new Paciente("Eric", LocalDate.of(1997, 04, 29), 72.5, TipoSangue.ONEGATIVO, SexoBiologico.MASCULINO, "Masculino");
//		new Paciente("Estacio", LocalDate.of(1998, 04, 18), 60.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		new Paciente("Thaynan", LocalDate.of(1996, 10, 19), 82.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		new Paciente("Sergio", LocalDate.of(1993, 07, 15), 55.5, TipoSangue.OPOSITIVO, SexoBiologico.MASCULINO, "Masculino");
//		new Paciente("Bruno", LocalDate.of(1997, 02, 27), 62, TipoSangue.ABNEGATIVO, SexoBiologico.MASCULINO, "Masculino");
//		new Paciente("Bruna", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//		
//		// Vai testar todos os casos de erro possiveis para um cadastro, por ordem dos argumentos
//		try{
//			new Paciente("", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Nome nao pode ser vazio ou nulo.", e.getMessage());
//		}
//		try{
//			new Paciente(null, LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Nome nao pode ser vazio ou nulo.", e.getMessage());
//		}
//		try{
//			new Paciente("Daniela", null, 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Data invalida.", e.getMessage());
//		}
////		TODO - devemos fazer uma verificacao de data minima valida?
////		try{
////			new Paciente("Daniela", LocalDate.of(1800, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
////			fail();
////		} catch(DadoInvalidoException e){
////			Assert.assertEquals("Data invalida.", e.getMessage());
////		}
//		try{
//			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 0, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Peso nao pode ser zero ou menor.", e.getMessage());
//		}
//		try{
//			new Paciente("Daniela", LocalDate.of(1996, 8, 27), -71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Nome nao pode ser vazio ou nulo.", e.getMessage());
//		}
//		try{
//			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, null, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Tipo sanguineo invalido.", e.getMessage());
//		}
//		try{
//			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, null, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Sexo biologico invalido.", e.getMessage());
//		}
//		try{
//			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "");
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Genero nao pode ser vazio ou nulo.", e.getMessage());
//		}
//		try{
//			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, null);
//			fail();
//		} catch(DadoInvalidoException e){
//			assertEquals("Genero nao pode ser vazio ou nulo.", e.getMessage());
//		}
//	}
//	
//	/**
//	 * Testa se os ids que estao sendo designados sao diferentes
//	 */
//	@Test
//	public void idsTeste() throws DadoInvalidoException {
//		this.resetaPacientes();
//		
//		assertNotEquals(eric.getId(), estacio.getId(), 0.5);
//		assertNotEquals(sergio.getId(), thaynan.getId(), 0.5);
//		assertNotEquals(bruno.getId(), luan.getId(), 0.5);
//		assertNotEquals(eric.getId(), bruna.getId(), 0.5);
//		
//		assertEquals(estacio.getId(), estacio.getId(), 0.5);
//		assertEquals(bruna.getId(), bruna.getId(), 0.5);
//		assertEquals(eric.getId(), eric.getId(), 0.5);
//	}
//	
//	/**
//	 * Testa gets
//	 */
//	@Test
//	public void getsTeste(){
//		this.resetaPacientes();
//		
//		// get nome
//		assertEquals("Eric", eric.getNome());
//		assertEquals("Bruna", bruna.getNome());
//		
//		// get peso
//		assertEquals(82.5, thaynan.getPeso(), 0.05);
//		assertEquals(71.1, luan.getPeso(), 0.05);
//		assertEquals(60.5, estacio.getPeso(), 0.05);
//		
//		// data
//		assertEquals(LocalDate.of(1993, 07, 15), sergio.getNascimento());
//		assertEquals(LocalDate.of(1996, 10, 19), thaynan.getNascimento());
//				
//	}
>>>>>>> ae2b8a741a5c98cbe1f3fa1da0e94014c7c2f72c

}
