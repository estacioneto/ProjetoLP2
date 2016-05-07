package projeto.hospital.gerencia.procedimentos;

import java.io.Serializable;

import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.prontuario.Prontuario;

public class GerenciadorProcedimento implements Serializable {
	/**
	 * Id gerado automaticamente 
	 */
	private static final long serialVersionUID = 588210554771048672L;
	
	private Procedimentos procedimentos;

	/**
	 * Construtor
	 */
	public GerenciadorProcedimento(){
		this.procedimentos = new Procedimentos();
	}
	
	public void realizaProcedimento(String procedimento, Prontuario prontuario, Orgao orgao) {
	}
}
