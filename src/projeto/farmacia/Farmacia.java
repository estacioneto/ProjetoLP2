package projeto.farmacia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.farmacia.medicamento.Medicamento;
import projeto.farmacia.medicamento.MedicamentoFactory;
import projeto.farmacia.medicamento.MedicamentoNomeComparator;
import projeto.farmacia.medicamento.MedicamentoPrecoComparator;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;

/**
 * Classe que representa uma farmacia, sedo a mesma responsavel pela gerencia e
 * cadastro de medicamentos.
 * 
 * @author Thaynan
 *
 */
public class Farmacia implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 4325301404289139683L;
	private List<Medicamento> listaMedicamentos;
	private MedicamentoFactory medicamentoFactory;

	/**
	 * Cosntrutor da Farmacia.
	 */
	public Farmacia() {
		this.listaMedicamentos = new ArrayList<>();
		this.medicamentoFactory = new MedicamentoFactory();
	}

	/**
	 * 
	 * @return copia da lista de medicamentos.
	 */
	public List<Medicamento> getListaMedicamentos() {
		List<Medicamento> copiaDaLista = new ArrayList<>(this.listaMedicamentos);
		return copiaDaLista;
	}

	/**
	 * Adiciona um medicamento cajo seja possivel, chamando a factory de
	 * Medicamento.
	 * 
	 * @param nome
	 *            Nome do medicamento.
	 * @param preco
	 *            Preco do medicamento.
	 * @param quantidade
	 *            Quantidade do medicamento.
	 * @param tipoMedicamento
	 *            Tipo do medicamento.
	 * @param categorias
	 *            Categorias do medicamento.
	 * @return Nome do medicamento.
	 * @throws DadoInvalidoException
	 *             Caso algum dos dados do medicamento seja invalido.
	 */
	public String addMedicamento(String nome, Double preco, int quantidade, String tipoMedicamento, String categorias)
			throws DadoInvalidoException {
		ValidadorDeDados.validaNome(Constantes.DO_MEDICAMENTO, nome);
		ValidadorDeDados.validaPositivo(Constantes.PRECO + Constantes.DO_MEDICAMENTO, preco);
		ValidadorDeDados.validaPositivo(Constantes.QUANTIDADE + Constantes.DO_MEDICAMENTO, quantidade);
		ValidadorDeDados.validaCategoriaMedicamento(Constantes.CATEGORIAS + Constantes.DO_MEDICAMENTO, categorias);
		Medicamento medicamento = medicamentoFactory.criaMedicamento(nome, tipoMedicamento, preco, quantidade,
				categorias);
		this.listaMedicamentos.add(medicamento);
		return nome;
	}

	/**
	 * Verifica se um determinado medicamento existe a partir do nome.
	 * 
	 * @param erro
	 *            Mensagem de erro, caso o medicamento nao exista.
	 * @param nomeMedicamento
	 *            Nome do medicamento em busca.
	 * @return Medicamento.
	 * @throws DadoInvalidoException
	 *             Caso o medicamento nao esteja cadasatrado.
	 */
	public Medicamento pegaMedicamento(String erro, String nomeMedicamento) throws DadoInvalidoException {
		for (Medicamento medicamentoAtual : this.listaMedicamentos) {
			if (medicamentoAtual.getNome().equalsIgnoreCase(nomeMedicamento)) {
				return medicamentoAtual;
			}
		}
		throw new DadoInvalidoException(erro);
	}

	/**
	 * Metodo que busca todos os medicamento que possuem tal categoria.
	 * 
	 * @param categoria
	 *            Categoria do medicamento
	 * @return lista de medicamento
	 */
	private List<Medicamento> medicamentoComCategoria(String categoria) {
		List<Medicamento> medicamentosCategoria = new ArrayList<>();

		for (Medicamento medicamentoAtual : this.listaMedicamentos) {
			if (medicamentoAtual.contemCategoria(categoria)) {
				medicamentosCategoria.add(medicamentoAtual);
			}
		}
		MedicamentoPrecoComparator comparator = new MedicamentoPrecoComparator();
		Collections.sort(medicamentosCategoria, comparator);
		return medicamentosCategoria;
	}

	// Metodo que recebe uma lista e retorna os nomes dos medicamentos dessa
	// lista em uma nova lista.
	private List<String> nomesNaLista(List<Medicamento> lista) {
		List<String> listaNomeMedicamentos = new ArrayList<>();
		for (Medicamento medicamentoAtual : lista) {
			listaNomeMedicamentos.add(medicamentoAtual.getNome());
		}
		return listaNomeMedicamentos;
	}

	/**
	 * Metodo que consulta todos o medicamentos que possuem uma determinada
	 * categoria.
	 * 
	 * @param categoria
	 *            Categoria referente ao medicamento.
	 * @return todos os medicamentos que possuem determinada categoria.
	 * @throws DadoInvalidoException
	 *             Caso a categoria nao existir.
	 */
	public String consultaMedicamentoPorCategoria(String categoria) throws DadoInvalidoException {
		ValidadorDeDados.validaCategoriaMedicamento(MensagensDeErro.ERRO_MEDICAMENTO_CATEGORIA_INVALIDA, categoria);
		List<String> listaNomeMedicamentosCategoria = this.nomesNaLista(this.medicamentoComCategoria(categoria));
		if (listaNomeMedicamentosCategoria.isEmpty()) {
			throw new DadoInvalidoException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTOS_NA_CATEGORIA);
		}
		return String.join(",", listaNomeMedicamentosCategoria);
	}

	/**
	 * Metodo que consulta medicamento pela ordem alfabetica.
	 * 
	 * @return todos os medicamentos em ordem alfabetica.
	 */
	public String consultaMedicamentosOrdemAlfabetica() {
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		MedicamentoNomeComparator comparator = new MedicamentoNomeComparator();
		Collections.sort(copiaLista, comparator);
		return String.join(",", this.nomesNaLista(copiaLista));
	}

	/**
	 * Metodo que consulta medicamento pela ordenados pelo preco.
	 * 
	 * @return todos os medicamentos em ordenados pelos preco.
	 */
	public String consultaMedicamentosOrdemPreco() {
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		MedicamentoPrecoComparator comparator = new MedicamentoPrecoComparator();
		Collections.sort(copiaLista, comparator);
		return String.join(",", this.nomesNaLista(copiaLista));
	}
}