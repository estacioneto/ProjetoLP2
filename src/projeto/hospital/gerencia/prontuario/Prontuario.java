package projeto.hospital.gerencia.prontuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projeto.hospital.gerencia.procedimento.procedimentos.Procedimento;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;
import projeto.util.Util;

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
	private List<Procedimento> procedimentosRealizados;

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
	 *            Procedimento realizado no momento.
	 */
	public void registraProcedimento(Procedimento procedimentoRealizado) {
		this.procedimentosRealizados.add(procedimentoRealizado);
	}

	/**
	 * @return Quantidade de procedimentos ja realizados
	 */
	public int qtdProcedimentos() {
		return this.procedimentosRealizados.size();
	}

	/**
	 * Gera uma ficha de um paciente e guarda
	 */
	public void exportaFichaPaciente() {
		LocalDate dataAtual = LocalDate.now();
		String[] dataSeparada = dataAtual.toString().split("-");
		// Substitui espacos no nome do paciente e data por underscores 
		String nomeArquivo = String.join("_", paciente.getNome().split(" ")) + "_" + String.join("_", dataSeparada) + ".txt";
		
		Util.criaRelatorioPaciente(nomeArquivo, this.toString());
	}
	
	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();
		saida.append("Paciente: " + paciente.getNome() + Constantes.QUEBRA_LINHA);
		saida.append(String.format("Peso: %.2f", paciente.getPeso()) + " kg Tipo Sanguineo: " + paciente.getTipoSanguineo() + Constantes.QUEBRA_LINHA);
		saida.append("Sexo: " + paciente.getSexo() + " Genero: " + paciente.getGenero() + Constantes.QUEBRA_LINHA);
		saida.append(String.format("Gasto total: R$ %.2f", paciente.getGastosPaciente()) + " Pontos acumulados: " + paciente.getPontuacao() + Constantes.QUEBRA_LINHA);
		saida.append("Resumo dos procedimentos: " + this.procedimentosRealizados.size() + " procedimento(s)" + Constantes.QUEBRA_LINHA);
		
		for(Procedimento procedimento : this.procedimentosRealizados)
			saida.append(procedimento.toString() + Constantes.QUEBRA_LINHA);
		
		return saida.toString();
	}
}
