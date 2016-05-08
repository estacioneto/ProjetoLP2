package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import projeto.util.ConstantesReflection;
import projeto.util.MetodoAssociado;

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
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;
	@MetodoAssociado(get = ConstantesReflection.GET_QUANTIDADE, set = ConstantesReflection.SET_QUANTIDADE)
	private int quantidade;
	@MetodoAssociado(get = ConstantesReflection.GET_CATEGORIAS)
	private String categorias;
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO)
	private String tipo;
	@MetodoAssociado(get = ConstantesReflection.GET_PRECO, set = ConstantesReflection.SET_PRECO)
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
	public Medicamento(String nome, Double preco, int quantidade, String categorias) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = categorias;
	}

	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Define o tipo do medicamento.
	 * 
	 * @param tipo
	 *            Tipo do medicamento.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * @param quantidade
	 *            Quantidade nova do medicamento.
	 */
	public void setQuantidade(String quantidade) {
		this.quantidade = Integer.parseInt(quantidade);
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
	 * @param preco
	 *            Preco novo do medicamento.
	 */
	public void setPreco(String preco) {
		this.setPreco(Double.parseDouble(preco));
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
		Set<String> novo = new TreeSet<>(Arrays.asList(this.categorias.split(",")));
		return String.join(",", novo);
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
		String formatacao = String.format(" %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", this.getNome(),
				this.getPreco(), this.getQuantidade(), this.getCategorias());
		return formatacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categorias == null) ? 0 : categorias.hashCode());
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
			if (objMedicamento.getPreco() == this.getPreco() && objMedicamento.getNome().equals(this.getNome())
					&& objMedicamento.getCategorias().equals(this.getCategorias())) {
				return true;
			}
		}
		return false;
	}
}