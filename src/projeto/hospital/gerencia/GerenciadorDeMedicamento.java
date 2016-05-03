package projeto.hospital.gerencia;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.farmacia.Farmacia;
import projeto.farmacia.Medicamento;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;

/**
 * 
 * @author Thaynan
 *
 */
public class GerenciadorDeMedicamento implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1L;
	private Farmacia farmacia;

	/**
	 * Construtor
	 */
	public GerenciadorDeMedicamento() {
		farmacia = new Farmacia();
	}

	/**
	 * Metodo que cadastra um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @param tipo
	 *            Tipo do medicamento.
	 * @param preco
	 *            Preco do medicamento.
	 * @param quantidade
	 *            Quantidade do medicamento.
	 * @param categoriasCategorias
	 *            do medicamento.
	 * @return Nome do medicamento.
	 */
	public String cadastraMedicamento(String nome, String tipo, Double preco,
			int quantidade, String categorias) {
		return farmacia.addMedicamento(nome, preco, quantidade, tipo,
				categorias);
	}

	/**
	 * Metodo que retorna um determinado atributo de um medicamento, passado o
	 * seu nome.
	 * 
	 * @param atributo
	 *            Atributo do medicamento.
	 * @param nomeMedicamento
	 *            Nome do medicamento.
	 * @return atributo do medicamento.
	 */
	public Object getInfoMedicamento(String atributo, String nomeMedicamento) {
		Medicamento medicamento = farmacia.verificaMedicamentoExistente(
				MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO
						+ MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO,
				nomeMedicamento);
		switch (ValidadorDeDados.capitalizaString(atributo)) {
		case Constantes.TIPO:
			return medicamento.getTipo();
		case Constantes.NOME:
			return medicamento.getNome();
		case Constantes.CATEGORIAS:
			return medicamento.getCategorias();
		case Constantes.PRECO:
			return medicamento.calculaPreco();
		case Constantes.QUANTIDADE:
			return medicamento.getQuantidade();
		default:
			throw new DadoInvalidoException();
		}
	}

	/**
	 * Metodo que atualiza um atributo de um medicamento.
	 * 
	 * @param nomeMedicamento
	 *            Nome do medicamento.
	 * @param atributo
	 *            Atributo a ser atualizado.
	 * @param novoValor
	 *            Novo valor do atributo
	 */
	public void atualizaMedicamento(String nomeMedicamento, String atributo,
			String novoValor) {
		Medicamento medicamento = farmacia.verificaMedicamentoExistente(
				MensagensDeErro.ERRO_ATUALIZAR_MEDICAMENTO_INVALIDO,
				nomeMedicamento);
		atributo = ValidadorDeDados.capitalizaString(atributo);
		switch (atributo) {
		case Constantes.PRECO:
			Double novoPreco = Double.parseDouble(novoValor);
			medicamento.setPreco(novoPreco);
			break;
		case Constantes.QUANTIDADE:
			Integer novaQtd = Integer.parseInt(novoValor);
			medicamento.setQuantidade(novaQtd);
			break;
		default:
			throw new DadoInvalidoException(String.format(
					MensagensDeErro.ERRO_ATUALIZAR_ATRIBUTO_MEDICAMENTO,
					atributo));
		}
	}

	/**
	 * Metodo que retorna uma lista em String de todos os medicamentos com
	 * determinada categoria.
	 * 
	 * @param categoria
	 *            Categoria do medicamento desejada.
	 * @return lista em String dos medicamentos.
	 */
	public String consultaMedCategoria(String categoria) {
		return farmacia.consultaMedicamentoPorCategoria(categoria);
	}

	/**
	 * Metodo que retorna as caracteristicas de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @return Caracteristicas do medicamento.
	 */
	public String consultaMedNome(String nome) {
		return farmacia.verificaMedicamentoExistente(
				MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO_INEXISTENTE, nome)
				.toString();
	}

	/**
	 * Metodo que retorna uma lista em String dos medicamentos a partir de uma
	 * ordem definida.
	 * 
	 * @param ordenacao
	 *            Ordenacao desejada.
	 * @return lista ordenada em String dos medicamentos.
	 */
	public String getEstoqueFarmacia(String ordenacao) {
		if (ordenacao.equalsIgnoreCase("preco")) {
			return farmacia.consultaMedicamentosOrdemPreco();
		} else if (ordenacao.equalsIgnoreCase("alfabetica")) {
			return farmacia.consultaMedicamentosOrdemAlfabetica();
		} else {
			throw new DadoInvalidoException(
					MensagensDeErro.ERRO_ORDENCAO_MEDICAMENTO);
		}
	}
}
