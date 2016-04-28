package projeto.hospital.paciente;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;


public class PacienteTeste {

	/**
	 * Testa a criacao de um usuario
	 */
	@Test
	public void CadastroTeste() throws DadoInvalidoException {
		// new Paciente(String nome, String nascimento, double peso, TipoSangue sangue, SexoBiologico sexo, String Genero)
		// Todos esses devem dar certo
		Paciente eric = new Paciente("Eric", LocalDate.of(1997, 04, 29), 72.5, TipoSangue.ONEGATIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente estacio = new Paciente("Estacio", LocalDate.of(1998, 04, 18), 60.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente thaynan = new Paciente("Thaynan", LocalDate.of(1996, 10, 19), 82.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente sergio = new Paciente("Sergio", LocalDate.of(1993, 07, 15), 55.5, TipoSangue.OPOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente bruno = new Paciente("Bruno", LocalDate.of(1997, 02, 27), 62, TipoSangue.ABNEGATIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente luan = new Paciente("Luan", LocalDate.of(1996, 12, 12), 71.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente bruna = new Paciente("Bruna", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
		
		// Vai testar todos os casos de erro possiveis para um cadastro, por ordem dos argumentos
		try{
			new Paciente("", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Nome nao pode ser vazio ou nulo.", e.getMessage());
		}
		try{
			new Paciente(null, LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Nome nao pode ser vazio ou nulo.", e.getMessage());
		}
		try{
			new Paciente("Daniela", null, 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Data invalida.", e.getMessage());
		}
//		TODO - devemos fazer uma verificacao de data minima valida?
//		try{
//			new Paciente("Daniela", LocalDate.of(1800, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
//			fail();
//		} catch(DadoInvalidoException e){
//			Assert.assertEquals("Data invalida.", e.getMessage());
//		}
		try{
			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 0, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Peso nao pode ser zero ou menor.", e.getMessage());
		}
		try{
			new Paciente("Daniela", LocalDate.of(1996, 8, 27), -71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Nome nao pode ser vazio ou nulo.", e.getMessage());
		}
		try{
			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, null, SexoBiologico.FEMININO, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Tipo sanguineo invalido.", e.getMessage());
		}
		try{
			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, null, "Feminino");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Sexo biologico invalido.", e.getMessage());
		}
		try{
			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, "");
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Genero nao pode ser vazio ou nulo.", e.getMessage());
		}
		try{
			new Paciente("Daniela", LocalDate.of(1996, 8, 27), 71.5, TipoSangue.BNEGATIVO, SexoBiologico.FEMININO, null);
			fail();
		} catch(DadoInvalidoException e){
			Assert.assertEquals("Genero nao pode ser vazio ou nulo.", e.getMessage());
		}
	}

}
