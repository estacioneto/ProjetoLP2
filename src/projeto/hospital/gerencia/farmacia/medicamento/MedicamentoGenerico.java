package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;

import projeto.util.Constantes;

/**
 * Classe que representa o tipo medicamento generico, seus comportaemntos e
 * estados.
 * 
 * @author Thaynan
 *
 */
public class MedicamentoGenerico extends Medicamento implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1556492773719602719L;

	public static final double DESCONTO_GENERICO = 60;
	public static final double DESCONTO_GENERICO_PORCENTAGEM = Constantes.PORCENTAGEM_TOTAL;
	public static final String TIPO = "Generico";
	
	/**
	 * Construtor
	 * 
	 * @param nome
	 *            Nome do medicamento
	 * @param preco
	 *            Preco do medicamento com desconto
	 * @param quantidade
	 *            Quantidade do medicamento
	 * @param categorias
	 *            Categorias do medicamento
	 */
	public MedicamentoGenerico(String nome, Double preco, int quantidade, String categorias) {
		super(nome, preco * DESCONTO_GENERICO / DESCONTO_GENERICO_PORCENTAGEM, quantidade, categorias);
	}

	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo() {
		return TIPO;
	}
	
	@Override
	public void setPreco(Double preco) {
		super.setPreco(preco * DESCONTO_GENERICO / DESCONTO_GENERICO_PORCENTAGEM);
	}

	@Override
	public void setPreco(String preco) {
		this.setPreco(Double.parseDouble(preco));
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		String formatacao = "Medicamento " + TIPO + ":";
		return formatacao + super.toString();
	}
}