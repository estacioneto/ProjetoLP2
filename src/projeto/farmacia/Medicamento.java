package projeto.farmacia;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;

/**
 * 
 * @author Thaynan
 *
 */
public abstract class Medicamento implements Serializable {
	
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -7166091567180515070L;
	private String nome;
	private Double preco;
	private int quantidade;
	private String categorias;

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
	public Medicamento(String nome, Double preco, int quantidade,
			String categorias) {
		ValidadorDeDados.validaString(MensagensDeErro.ERRO_NOME_MEDICAMENTO,
				nome);
		ValidadorDeDados.validaPositivo(MensagensDeErro.ERRO_PRECO_MEDICAMENTO,
				preco);
		ValidadorDeDados.validaPositivo(
				MensagensDeErro.ERRO_QUANTIDADE_MEDICAMENTO, quantidade);
		ValidadorDeDados.validaCategoriaMedicamento(
				MensagensDeErro.ERRO_CATEGORIA_MEDICAMENTO, categorias);
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = categorias;
	}

	/**
	 * @return Tipo do medicamento.
	 */
	public abstract String getTipo();

	/**
	 * @return Preco original do medicamento.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * 
	 * @param quantidade
	 *            Quantidade nova do medicamento.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * 
	 * @param preco
	 *            Preco novo do medicamento.
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * 
	 * @return Nome do medicamento.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @return Quantidade do medicamento.
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * 
	 * @return Categorias do medicamento.
	 */
	public String getCategorias() {
		Set<String> novo = new TreeSet<>(Arrays.asList(this.categorias
				.split(",")));
		return String.join(",", novo);
	}

	/**
	 * 
	 * @return Preco final do medicamento.
	 */
	public abstract Double calculaPreco();

	/**
	 * Metodo resposavel por verificar se o medicamento possui determinada
	 * categoria.
	 * 
	 * @param categoria
	 *            Categoria do medicamento.
	 * @return Boolean que indica se contem.
	 */
	public boolean contemCategoria(String categoria) {
		String[] arrayCategorias = this.categorias.split(",");
		for (int i = 0; i < arrayCategorias.length; i++) {
			if (arrayCategorias[i].equalsIgnoreCase(categoria)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		String formatacao = String.format(
				" %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s",
				this.getNome(), this.calculaPreco(), this.getQuantidade(),
				this.getCategorias());
		return formatacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categorias == null) ? 0 : categorias.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Compara dois medicamentos pelo nome, preco e categorias.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Medicamento) {
			Medicamento objMedicamento = (Medicamento) obj;
			if (objMedicamento.calculaPreco() == this.calculaPreco()
					&& objMedicamento.getNome().equals(this.getNome())
					&& objMedicamento.getCategorias().equals(
							this.getCategorias())) {
				return true;
			}
		}
		return false;
	}
}