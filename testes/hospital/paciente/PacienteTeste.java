package hospital.paciente;

import java.util.Date;

import org.junit.Test;


public class PacienteTeste {

	@Test
	public void CadastroTeste() throws DadoInvalidoException {
		// new Paciente(String nome, String nascimento, double peso, TipoSangue sangue, SexoBiologico sexo, String Genero)	
		Paciente eric = new Paciente("Eric", new Date(1997, 04, 29), 72.5, TipoSangue.ONEGATIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente estacio = new Paciente("Estacio", new Date(1998, 04, 18), 60.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente thaynan = new Paciente("Thaynan", new Date(1996, 10, 19), 82.5, TipoSangue.APOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		Paciente sergio = new Paciente("Sergio", new Date(1993, 07, 15), 55.5, TipoSangue.OPOSITIVO, SexoBiologico.MASCULINO, "Masculino");
		
		
	}

}
