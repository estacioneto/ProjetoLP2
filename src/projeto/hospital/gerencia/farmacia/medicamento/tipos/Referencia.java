package projeto.hospital.gerencia.farmacia.medicamento.tipos;

/**
 * Classe que representa o tipo medicamento de referencia, seus comportaemntos e
 * estados.
 * 
 * @author Thaynan
 */
public class Referencia implements TipoMedicamento {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 6053591316062764788L;

	public static final String TIPO = "de Referencia";

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
	public Double calculaPreco(Double preco) {
		return preco;
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();
		saida.append("Medicamento " + this.getTipo() + ":");
		return saida.toString();
	}
}