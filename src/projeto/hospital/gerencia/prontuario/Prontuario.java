package projeto.hospital.gerencia.prontuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projeto.hospital.gerencia.prontuario.paciente.Paciente;

/**
 * Classe para representar os prontuarios
 * 
 * @author Eric
 */
public class Prontuario implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 21463754771L;

	private Paciente paciente;
	private List<String> procedimentosRealizados;

	/**
	 * Construtor
	 * 
	 * @param paciente
	 *            Paciente relacionado ao prontuario
	 */
	public Prontuario(Paciente paciente) {
		this.paciente = paciente;
		this.procedimentosRealizados = new ArrayList<>();
	}

	/**
	 * @return Paciente relacionado ao prontuario
	 */
	public Paciente getPaciente() {
		return this.paciente;
	}

	/**
	 * @return Id do paciente do prontuario
	 */
	public String getId() {
		return paciente.getId();
	}

	/**
	 * Registra um procedimento realizado no paciente
	 * 
	 * @param procedimentoRealizado
	 */
	public void registraProcedimento(String procedimentoRealizado) {
		this.procedimentosRealizados.add(procedimentoRealizado);
	}

	/**
	 * @return Quantidade de procedimentos ja realizados
	 */
	public int qtdProcedimentos() {
		return this.procedimentosRealizados.size();
	}
}
