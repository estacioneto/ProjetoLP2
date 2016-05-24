package projeto.hospital.gerencia.farmacia.medicamento.tipos;

import java.io.Serializable;

/**
 * Tipo do medicamento. Inteface que define o calculo do preco e a impressao do
 * tipo.
 * 
 * @author Estacio
 */
public interface TipoMedicamento extends Serializable {
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