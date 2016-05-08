package projeto.hospital.gerencia.farmacia;

import java.io.Serializable;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.ValidadorDeLogica;
import projeto.hospital.gerencia.farmacia.medicamento.Medicamento;
import projeto.hospital.gerencia.funcionario.Funcionario;
import projeto.hospital.gerencia.funcionario.cargo.Permissao;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.Util;
import projeto.util.ValidadorDeDados;

/**
 * 
 * @author Thaynan
 *
 */
public class GerenciadorDeFarmacia implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = -7250140707171454261L;
	private Farmacia farmacia;

	/**
	 * Construtor
	 */
	public GerenciadorDeFarmacia() {
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
	 * @param categorias
	 *            do medicamento.
	 * @param funcionarioLogado funcionario Logado no sistema.
	 * @return Nome do medicamento.
	 */
	public String cadastraMedicamento(String nome, String tipo, Double preco, int quantidade, String categorias, Funcionario funcionarioLogado) {
		ValidadorDeLogica.validaOperacao(
				MensagensDeErro.ERRO_PERMISSAO_CADASTRO_MEDICAMENTO,
				Permissao.CADASTRAR_MEDICAMENTO, funcionarioLogado);
		try {
			return farmacia.addMedicamento(nome, preco, quantidade, tipo, categorias);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_MEDICAMENTO + e.getMessage());
		}
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
		try {
			Medicamento medicamento = farmacia.pegaMedicamento(MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO,
					nomeMedicamento);
			return Util.getInfo(medicamento, atributo);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
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
	public void atualizaMedicamento(String nomeMedicamento, String atributo, String novoValor) {
		try {
			ValidadorDeDados.validaString(atributo, novoValor);
			ValidadorDeDados.validaString(Constantes.NOME + Constantes.DO_MEDICAMENTO, nomeMedicamento);
			Medicamento medicamento = farmacia.pegaMedicamento(MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO,
					nomeMedicamento);
			atributo = ValidadorDeDados.capitalizaString(atributo);
			Util.atualizaInfo(medicamento, atributo, novoValor, String.format(MensagensDeErro.ERRO_ATRIBUTO_MEDICAMENTO_NAO_ATUALIZAVEL, atributo));
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZAR_MEDICAMENTO + e.getMessage());
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
		try {
			return farmacia.consultaMedicamentoPorCategoria(categoria);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
		}
	}

	/**
	 * Metodo que retorna as caracteristicas de um medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @return Caracteristicas do medicamento.
	 */
	public String consultaMedNome(String nome) {
		try {
			return farmacia.pegaMedicamento(MensagensDeErro.ERRO_MEDICAMENTO_INEXISTENTE, nome).toString();
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
		}
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
		try {
			if (ordenacao.equalsIgnoreCase("preco")) {
				return farmacia.consultaMedicamentosOrdemPreco();
			} else if (ordenacao.equalsIgnoreCase("alfabetica")) {
				return farmacia.consultaMedicamentosOrdemAlfabetica();
			} else {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_ORDENCAO_MEDICAMENTO);
			}
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
		}
	}
}