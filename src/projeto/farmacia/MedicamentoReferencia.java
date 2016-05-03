package projeto.farmacia;

import java.io.Serializable;

/**
 * 
 * @author Thaynan
 *
 */
public class MedicamentoReferencia extends Medicamento implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1L;

	public static final String TIPO = "de Referencia";

	/**
	 * Construtor
	 * 
	 * @param nome
	 *            Nome do medicamento
	 * @param preco
	 *            Preco do medicamento
	 * @param quantidade
	 *            Quantidade do medicamento
	 * @param categorias
	 *            Categorias do medicamento
	 */
	public MedicamentoReferencia(String nome, double preco, int quantidade,
			String categorias) {
		super(nome, preco, quantidade, categorias);
	}

	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo() {
		return TIPO;
	}

	/**
	 * @return Preco do medicamento final.
	 */
	@Override
	public Double calculaPreco() {
		return super.getPreco();
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		String formatacao = "Medicamento " + this.getTipo() + ":"
				+ super.toString();
		return formatacao;
	}
}