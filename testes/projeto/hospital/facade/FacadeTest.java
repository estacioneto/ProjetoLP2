package projeto.hospital.facade;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import projeto.exceptions.dados.StringVaziaException;
import projeto.exceptions.logica.AcessoBloqueadoException;
import projeto.util.Constantes;

public class FacadeTest {

	@Test
	public void liberaSistemaTest() {
		Facade facade = new Facade();
		String chaveCorreta = "c041ebf8";

		SimpleDateFormat formatadorAno = new SimpleDateFormat("yyyy");
		java.util.Date data = new java.util.Date();
		try {
			String ano = formatadorAno.format(data);
			String str = "1" + ano + "001";
			Assert.assertEquals(str, facade.liberaSistema("Estacio", "18/04/1998",chaveCorreta));
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("O acesso foi feito corretamente! Nao deveria lancar excecao: " + chaveCorreta);
		}

		try {
			facade.liberaSistema("Estacio", "18/04/1998",Constantes.STRING_VAZIA);
			fail("O acesso foi nao feito corretamente! Deveria lancar excecao.");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			Assert.assertEquals("Voce nao tem acesso ao sistema!", acessoBloqueado.getMessage());
		}

		try {
			facade.liberaSistema("Estacio", "18/04/1998",chaveCorreta.toUpperCase());
			fail("O acesso foi nao feito corretamente! Deveria lancar excecao.");
		} catch (AcessoBloqueadoException acessoBloqueado) {
			Assert.assertEquals("Voce nao tem acesso ao sistema!", acessoBloqueado.getMessage());
		}

		try {
			Facade facade2 = new Facade();
			String ano = formatadorAno.format(data);
			String str = "1" + ano + "001";
			Assert.assertEquals(str, facade2.liberaSistema("Estacio", "18/04/1998",chaveCorreta.toLowerCase()));

		} catch (AcessoBloqueadoException acessoBloqueado2) {
			fail("O acesso foi feito corretamente! Nao deveria lancar excecao: " + chaveCorreta);
		}
	}

	@Test
	public void realizaLoginTest() {
		Facade facade = new Facade();
		String chaveCorreta = "c041ebf8";

		SimpleDateFormat formatadorAno = new SimpleDateFormat("yyyy");
		java.util.Date data = new java.util.Date();
		try {
			String ano = formatadorAno.format(data);
			String str = "1" + ano + "001";
			Assert.assertEquals(str, facade.liberaSistema("Estacio", "18/04/1998", chaveCorreta));
			facade.login(str, "19981201");

			try {
				facade.login(str, Constantes.STRING_VAZIA);
				fail("Deveria lancar excecao!");
			} catch (StringVaziaException stringVazia) {
				Assert.assertEquals("Senha nao pode ser vazio(a)!", stringVazia.getMessage());
			}
		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("O acesso foi feito corretamente! Nao deveria lancar excecao: " + acessoBloqueado.getMessage());
		}

		try {
			Facade facade2 = new Facade();
			String ano = formatadorAno.format(data);
			String str = "1" + ano + "001";
			Assert.assertEquals(str, facade2.liberaSistema("Estacio", "18/04/1998",chaveCorreta.toLowerCase()));

		} catch (AcessoBloqueadoException acessoBloqueado) {
			fail("O acesso foi feito corretamente! Nao deveria lancar excecao: " + acessoBloqueado.getMessage());
		}
	}
}
