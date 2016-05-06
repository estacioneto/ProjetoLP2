package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import projeto.util.Constantes;
import projeto.util.ExMetodo;

/**
 * Classe que possui o tipo basico que caracteriza todos os medicamentos.
 * 
 * @author Thaynan
 *
 */
public class Medicamento implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -7166091567180515070L;
	// Para pegar a informacao da subclasse, ela tem que conhecer os seus
	// atributos.
	private String nome;
	private int quantidade;
	@ExMetodo(metodo = Constantes.GET_CATEGORIAS_MEDICAMENTO)
	private String categorias;
	@ExMetodo(metodo = Constantes.GET_TIPO_MEDICAMENTO)
	private TipoMedicamento tipo;
	@ExMetodo(metodo = Constantes.CALCULA_PRECO_MEDICAMENTO)
	private Double preco;

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
			String categorias, TipoMedicamento tipo) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = categorias;
		this.tipo = tipo;
	}

	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo() {
		return this.tipo.getTipo();
	}

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
	public Double calculaPreco() {
		return this.tipo.calculaPreco(this.preco);
	}

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
				"%s %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s",
				this.tipo.toString(), this.getNome(), this.calculaPreco(),
				this.getQuantidade(), this.getCategorias());
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