package projeto.hospital.gerencia.farmacia.medicamento.tipos;

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
	 * Metodo responsavel por calcular o preco final de um medicamento.
	 * 
	 * @param preco
	 *            Preco atual sem desconto.
	 * @return Preco final do medicamento.
	 */
	public abstract Double calculaPreco(Double preco);
}