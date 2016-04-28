package projeto.hospital.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import projeto.util.GeradorDeMatricula;

public class Controller {
	
	private LocalDate data;
	private GeradorDeMatricula geradorMatricula;
	private SimpleDateFormat formatadorAno;
	
	private SegurancaDoSistema segurancaSistema;

	public Controller(){
		this.data = LocalDate.now();
		this.formatadorAno = new SimpleDateFormat("yyyy");
		this.geradorMatricula = new GeradorDeMatricula();
		
		this.segurancaSistema = new SegurancaDoSistema();
	}
	

	public String novaMatricula(String codigo) {
		return this.geradorMatricula.geraNovaMatricula(codigo, this.getAno());
	}

	private String getAno() {
		return this.formatadorAno.format(data.getYear());
	}
	
	public void cadastraLogin(String matricula, String senha){
		this.segurancaSistema.cadastraLogin(matricula, senha);
	}
	
	public void realizaLogin(String matricula, String senha){
		this.segurancaSistema.validaAcesso(matricula, senha);
	}
	
}
