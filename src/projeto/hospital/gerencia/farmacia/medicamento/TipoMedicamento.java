package projeto.hospital.gerencia.farmacia.medicamento;

/**
 * Tipo do medicamento. Inteface que define o calculo do preco e a impressao do
 * tipo.
 * 
 * @author Estacio
 */
public interface TipoMedicamento {

	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo();

	/**
	 * 
	 * @return Preco final do medicamento.
	 */
	public abstract Double calculaPreco(Double preco);
}
