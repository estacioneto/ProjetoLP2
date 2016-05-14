package projeto.hospital.gerencia.farmacia.medicamento;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.farmacia.medicamento.tipos.MedicamentoGenerico;
import projeto.hospital.gerencia.farmacia.medicamento.tipos.MedicamentoReferencia;
import projeto.hospital.gerencia.farmacia.medicamento.tipos.TipoMedicamento;
import projeto.util.Constantes;
import projeto.util.reflexao.ConstantesReflection;
import projeto.util.reflexao.Conversao;
import projeto.util.reflexao.MetodoAssociado;
import projeto.util.reflexao.Reflection;
import projeto.util.reflexao.Validacao;

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
	
	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.NOME + Constantes.DO_MEDICAMENTO)
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_TIPO_MEDICAMENTO, erro = Constantes.TIPO + Constantes.DO_MEDICAMENTO, get = true)
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO)
	private TipoMedicamento tipo;
	
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
	public Medicamento(String nome,  String tipo, Double preco, int quantidade, String categoria) {
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
		try{
			if (tipo.equalsIgnoreCase(Constantes.TIPO_GENERICO)) {
				this.tipo = (TipoMedicamento) Reflection.godFactory(MedicamentoGenerico.class);
			} else if (tipo.equalsIgnoreCase(Constantes.TIPO_REFERENCIA)) {
				this.tipo = (TipoMedicamento) Reflection.godFactory(MedicamentoReferencia.class);
			}
		}catch(DadoInvalidoException excecao){
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
	 * 
	 * @param quantidade
	 *            Quantidade nova do medicamento.
	 */
	public void setQuantidade(Integer quantidade) {
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
		String formatacao = String.format("%s %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", this.tipo.toString(),this.getNome(),
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