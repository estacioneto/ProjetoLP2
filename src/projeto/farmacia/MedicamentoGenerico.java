package projeto.farmacia;

import java.io.Serializable;

/**
 * 
 * @author Thaynan
 *
 */
public class MedicamentoGenerico extends Medicamento implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1L;

	public static final double DESCONTO_GENERICO = 60;
	public static final double DESCONTO_GENERICO_PORCENTAGEM = 100;
	public static final String TIPO = "Generico";

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
	public MedicamentoGenerico(String nome, Double preco, int quantidade,
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
		return super.getPreco() * DESCONTO_GENERICO
				/ DESCONTO_GENERICO_PORCENTAGEM;
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		String formatacao = "Medicamento " + TIPO + ":" + super.toString();
		return formatacao;
	}
}