package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.farmacia.medicamento.tipos.TipoMedicamento;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;
import projeto.util.reflexao.ConstantesReflection;
import projeto.util.reflexao.Conversao;
import projeto.util.reflexao.MetodoAssociado;
import projeto.util.reflexao.Reflection;
import projeto.util.reflexao.Validacao;

/**
 * Classe que possui o tipo basico que caracteriza todos os medicamentos.
 * 
 * @author Thaynan
 */
public class Medicamento implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -7166091567180515070L;

	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.NOME + Constantes.DO_MEDICAMENTO)
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;

	@Conversao(formato = Double.class, conversor = ConstantesReflection.STRING_DOUBLE)
	@Validacao(metodo = ConstantesReflection.VALIDA_POSITIVO, erro = Constantes.PRECO + Constantes.DO_MEDICAMENTO)
	@MetodoAssociado(get = ConstantesReflection.GET_PRECO, set = ConstantesReflection.SET_PRECO)
	private Double preco;

	@Conversao(formato = Integer.class, conversor = ConstantesReflection.STRING_INTEIRO)
	@Validacao(metodo = ConstantesReflection.VALIDA_POSITIVO, erro = Constantes.QUANTIDADE + Constantes.DO_MEDICAMENTO)
	@MetodoAssociado(get = ConstantesReflection.GET_QUANTIDADE, set = ConstantesReflection.SET_QUANTIDADE)
	private Integer quantidade;

	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.CATEGORIAS + Constantes.DO_MEDICAMENTO)
	@MetodoAssociado(get = ConstantesReflection.GET_CATEGORIAS)
	private String categorias;

	@Validacao(metodo = ConstantesReflection.VALIDA_TIPO_MEDICAMENTO, erro = Constantes.TIPO
			+ Constantes.DO_MEDICAMENTO, get = true)
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO)
	private TipoMedicamento tipo;

	/**
	 * Construtor
	 * 
	 * @param nome
	 *            Nome do medicamento
	 * @param preco
	 *            Preco do medicamento
	 * @param quantidade
	 *            Quantidade do medicamento
	 * @param categoria
	 *            Categorias do medicamento
	 * @param tipo
	 *            Tipo do medicamento.
	 */
	public Medicamento(String nome, Double preco, Integer quantidade, String categoria, String tipo) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = categoria;
		this.setTipo(tipo);
	}

	/**
	 * @return Tipo do medicamento.
	 */
	public String getTipo() {
		return this.tipo.getTipo();
	}

	/**
	 * Define o tipo do medicamento.
	 * 
	 * @param tipo
	 *            Tipo do medicamento.
	 */
	public void setTipo(String tipo) {
		try {
			this.tipo = (TipoMedicamento) Reflection.godFactory(Util.getNomeClasse(TipoMedicamento.class, tipo),
					MensagensDeErro.ERRO_TIPO_MEDICAMENTO);
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(excecao.getMessage());
		}
	}

	/**
	 * @return Preco original do medicamento.
	 */
	public double getPreco() {
		return this.tipo.calculaPreco(preco);
	}

	/**
	 * Seta a quantidade do medicamento
	 * 
	 * @param quantidade
	 *            Quantidade nova do medicamento.
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Seta a quantidade do medicamento, recebendo ela como {@code String}
	 * 
	 * @param quantidade
	 *            Quantidade nova do medicamento.
	 */
	public void setQuantidade(String quantidade) {
		this.setQuantidade(Integer.parseInt(quantidade));
	}

	/**
	 * Seta o preco do medicamento
	 * 
	 * @param preco
	 *            Preco novo do medicamento.
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * Seta o preco do medicamento recebendo como {@code String}
	 * 
	 * @param preco
	 *            Preco novo do medicamento.
	 */
	public void setPreco(String preco) {
		this.setPreco(Double.parseDouble(preco));
	}

	/**
	 * @return Nome do medicamento.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return Quantidade do medicamento.
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
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
	 * @return Boolean que indica se contem a categoria.
	 */
	public boolean contemCategoria(String categoria) {
		return this.categorias.contains(categoria);
	}

	/**
	 * @return caracteristicas do medicamento.
	 */
	@Override
	public String toString() {
		StringBuilder saida = new StringBuilder();
		saida.append(String.format("%s %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", this.tipo.toString(),
				this.getNome(), this.getPreco(), this.getQuantidade(), this.getCategorias()));
		return saida.toString();
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