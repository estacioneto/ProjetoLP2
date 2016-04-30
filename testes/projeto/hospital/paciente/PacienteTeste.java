package projeto.hospital.paciente;

import java.time.LocalDate;

import org.junit.Test;


public class PacienteTeste {
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

}
