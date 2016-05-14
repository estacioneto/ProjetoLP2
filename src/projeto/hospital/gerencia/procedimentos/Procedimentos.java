package projeto.hospital.gerencia.procedimentos;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.bancodeorgaos.Orgao;
import projeto.hospital.gerencia.prontuario.Prontuario;
import projeto.hospital.gerencia.prontuario.paciente.Paciente;
import projeto.util.Constantes;

/**
 * Classe responsavel por realizar os procedimentos
 * 
 * @author Er1c
 */
public class Procedimentos implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = 6731797048997438464L;
	
	private final double PRECO_CONSULTA = 350.0;
	private final double PRECO_CIRURGIA_BARIATRICA = 7600.00;
	private final double PRECO_REDESIGNICAO_SEXUAL = 9300.00;
	private final double PRECO_TRANSPLANTE = 12500.00;
	
	private final Integer PONTUACAO_CONSULTA_CLINICA = 50;
	private final Integer PONTUACAO_CIRURGIA_BARIATRICA = 100;
	private final Integer PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL = 130;
	private final Integer PONTUACAO_TRANSPLANTE_ORGAOS = 160;


	private final double REDUCAO_PESO_BARIATRICA = 10;
	
	/**
	 * Construtor
	 */
	public Procedimentos(){
	}
	
	/**
	 * ~ Procedimento de consulta clinica
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 */
	public void consultaClinica(Prontuario prontuarioPaciente, Double valorMedicamentos) {
		String procedimentoRealizado = "Consulta clinica";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();
		
		Double valorComDesconto = (PRECO_CONSULTA+valorMedicamentos) - paciente.calculaDesconto(PRECO_CONSULTA+valorMedicamentos);
		paciente.registraGasto(valorComDesconto);
		
		Integer pontuacaoAtual = paciente.getPontuacao() + paciente.calculaBonusPontuacao(PONTUACAO_CONSULTA_CLINICA);
		paciente.setPontuacao(pontuacaoAtual + PONTUACAO_CONSULTA_CLINICA);
	}

	/**
	 * Procedimento de cirugia bariatrica
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 */
	public void cirurgiaBariatrica(Prontuario prontuarioPaciente, Double valorMedicamentos) {
		String procedimentoRealizado = "Cirurgia Bariatrica";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();
		Double valorComDesconto = (PRECO_CIRURGIA_BARIATRICA+valorMedicamentos) - paciente.calculaDesconto(PRECO_CIRURGIA_BARIATRICA+valorMedicamentos);
		paciente.registraGasto(valorComDesconto);

		double pesoPaciente = paciente.getPeso();
		// Usado esse tipo de calculo para evitar erros de precisao
		double novoPeso = pesoPaciente - (pesoPaciente * REDUCAO_PESO_BARIATRICA / Constantes.PORCENTAGEM_TOTAL);
		paciente.setPeso(novoPeso);
		Integer pontuacaoAtual = paciente.getPontuacao() + paciente.calculaBonusPontuacao(PONTUACAO_CIRURGIA_BARIATRICA);
		paciente.setPontuacao(pontuacaoAtual + PONTUACAO_CIRURGIA_BARIATRICA);
	}

	/**
	 * Procedimento de redesignacao sexual, onde o genero do paciente eh mudado
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 */
	public void redesignacaoSexual(Prontuario prontuarioPaciente, Double valorMedicamentos) {
		String procedimentoRealizado = "Redesignacao Sexual";
		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Paciente paciente = prontuarioPaciente.getPaciente();

		Double valorComDesconto = (PRECO_REDESIGNICAO_SEXUAL+valorMedicamentos) - paciente.calculaDesconto(PRECO_REDESIGNICAO_SEXUAL+valorMedicamentos);
		paciente.registraGasto(valorComDesconto);

		paciente.mudaGenero();
		
		Integer pontuacaoAtual = paciente.getPontuacao() + paciente.calculaBonusPontuacao(PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL);
		paciente.setPontuacao(pontuacaoAtual + PONTUACAO_CIRURGIA_REDESIGNICAO_SEXUAL);
	}

	/**
	 * Realiza o procedimento de transplante de orgaos
	 * 
	 * @param prontuarioPaciente
	 *            Prontuario do paciente a ser atendido
	 * @param orgao
	 *            Orgao a ser transplantado
	 * @throws DadoInvalidoException
	 *             Caso nao haja compatibilidade entre os tipos sanguineos
	 */
	public void transplanteDeOrgaos(Prontuario prontuarioPaciente, Orgao orgao, Double valorMedicamentos) throws DadoInvalidoException {
		String procedimentoRealizado = "Transplante de " + orgao.getNome();
		Paciente paciente = prontuarioPaciente.getPaciente();

		prontuarioPaciente.registraProcedimento(procedimentoRealizado);
		Double valorComDesconto = (PRECO_TRANSPLANTE+valorMedicamentos) - paciente.calculaDesconto(PRECO_TRANSPLANTE+valorMedicamentos);
		paciente.registraGasto(valorComDesconto + valorMedicamentos);
		
		Integer pontuacaoAtual = paciente.getPontuacao() + paciente.calculaBonusPontuacao(PONTUACAO_TRANSPLANTE_ORGAOS);
		paciente.setPontuacao(pontuacaoAtual + PONTUACAO_TRANSPLANTE_ORGAOS);
	}
}
