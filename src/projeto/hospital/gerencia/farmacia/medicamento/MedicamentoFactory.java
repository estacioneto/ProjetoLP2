package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;

/**
 * Classe responsavel por criar um medicamento.
 * 
 * @author Thaynan
 *
 */
public class MedicamentoFactory implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 3926794298888896493L;

	// Metodo responsavel pela criacao de um medicamento do tipo Generico.
	private Medicamento criaMedicamentoGenerico(String nome, Double preco,
			int quantidade, String tipoMedicamento, String categorias) {
		Medicamento medicamento = new MedicamentoGenerico(nome, preco,
				quantidade, categorias);
		return medicamento;
	}

	// Metodo responsavel pela criacao de um medicamento do tipo de Referencia.
	private Medicamento criaMedicamentoReferencia(String nome, Double preco,
			int quantidade, String tipoMedicamento, String categorias) {
		Medicamento medicamento = new MedicamentoReferencia(nome, preco,
				quantidade, categorias);
		return medicamento;
	}

	/**
	 * Metodo responsavel pela criacao de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @param tipoMedicamento
	 *            TipoMedicamento do medicamento.
	 * @param preco
	 *            Preco do medicamento.
	 * @param quantidade
	 *            Quantidade do medicamento.
	 * @param categorias
	 *            Categoria do medicamento.
	 * @return Medicamento criado
	 */
	public Medicamento criaMedicamento(String nome, String tipoMedicamento,
			Double preco, int quantidade, String categorias) {
		try {
			Medicamento medicamento;
			if (tipoMedicamento.equalsIgnoreCase(Constantes.TIPO_GENERICO)) {
				medicamento = this.criaMedicamentoGenerico(nome, preco,
						quantidade, tipoMedicamento, categorias);
			} else if (tipoMedicamento
					.equalsIgnoreCase(Constantes.TIPO_REFERENCIA)) {
				medicamento = this.criaMedicamentoReferencia(nome, preco,
						quantidade, tipoMedicamento, categorias);
			} else {
				throw new DadoInvalidoException(
						"Nao existe este tipo de medicamento.");
			}
			return medicamento;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_MEDICAMENTO + e.getMessage());
		}
	}
}