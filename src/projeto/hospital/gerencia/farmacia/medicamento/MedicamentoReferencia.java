package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;

/**
 * Classe que representa o tipo medicamento de referencia, seus comportaemntos e
 * estados.
 * 
 * @author Thaynan
 *
 */
public class MedicamentoReferencia extends Medicamento implements Serializable {

	
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 6053591316062764788L;

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
	public MedicamentoReferencia(String nome, Double preco, int quantidade, String categorias) {
		super(nome, preco, quantidade, categorias);
	}
	
	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo() {
		return TIPO;
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		String formatacao = "Medicamento " + this.getTipo() + ":";
		return formatacao + super.toString();
	}
}