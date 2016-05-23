package projeto.hospital.gerencia.farmacia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.farmacia.medicamento.Medicamento;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;
import projeto.util.reflexao.Reflection;

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
	private Comparator<Medicamento> nomeComparator;
	private Comparator<Medicamento> precoComparator;

	/**
	 * Cosntrutor da Farmacia.
	 */
	public Farmacia() {
		this.listaMedicamentos = new ArrayList<>();
		this.inicializaComparators();
	}
	
	private void inicializaComparators() {
		this.nomeComparator = (Comparator<Medicamento> & Serializable)(Medicamento medicamentoA,
				Medicamento medicamentoB) -> {
			return medicamentoA.getNome().compareTo(medicamentoB.getNome());
		};
		
		this.precoComparator = (Comparator<Medicamento> & Serializable)(Medicamento medicamentoA,
				Medicamento medicamentoB) -> {
				if (medicamentoA.getPreco() > medicamentoB.getPreco()) {
					return 1;
				} else if (medicamentoA.getPreco() < medicamentoB.getPreco()) {
					return -1;
				}
				return 0;
		};
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
//		ValidadorDeDados.validaNome(Constantes.NOME + Constantes.DO_MEDICAMENTO, nome);
//		ValidadorDeDados.validaPositivo(Constantes.PRECO + Constantes.DO_MEDICAMENTO, preco);
//		ValidadorDeDados.validaPositivo(Constantes.QUANTIDADE + Constantes.DO_MEDICAMENTO, quantidade);
//		ValidadorDeDados.validaCategoriaMedicamento(Constantes.CATEGORIAS + Constantes.DO_MEDICAMENTO, categorias);
		Medicamento medicamento;
		medicamento = (Medicamento)Reflection.godFactory(Medicamento.class, nome, preco, quantidade,
				categorias, tipoMedicamento);
		//Validacao poderia ser mais simples
		//Reflection.validaObjeto(medicamento);
		this.listaMedicamentos.add(medicamento);
		return nome;
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
		try {
			ValidadorDeDados.validaString(atributo, novoValor);
			ValidadorDeDados.validaString(Constantes.NOME
					+ Constantes.DO_MEDICAMENTO, nomeMedicamento);
			Medicamento medicamento = this.pegaMedicamento(
					MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO,
					nomeMedicamento);
			atributo = ValidadorDeDados.capitalizaString(atributo);
			Reflection
					.atualizaInfo(
							medicamento,
							atributo,
							novoValor,
							String.format(
									MensagensDeErro.ERRO_ATRIBUTO_MEDICAMENTO_NAO_ATUALIZAVEL,
									atributo));
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_ATUALIZAR_MEDICAMENTO + e.getMessage());
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
			Medicamento medicamento = this.pegaMedicamento(
					MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO,
					nomeMedicamento);
			return Reflection.getInfo(medicamento, atributo);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
		}
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
		Collections.sort(medicamentosCategoria, this.precoComparator);
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
	 * Metodo que retorna uma lista em String dos medicamentos a partir de uma
	 * ordem definida.
	 * 
	 * @param ordenacao
	 *            Ordenacao desejada.
	 * @return lista ordenada em String dos medicamentos.
	 * @throws DadoInvalidoException 
	 */
	public String getEstoqueFarmacia(String ordenacao) throws DadoInvalidoException {
		if (ordenacao.equalsIgnoreCase("preco")) {
			return this.consultaMedicamentosOrdemPreco();
		} else if (ordenacao.equalsIgnoreCase("alfabetica")) {
			return this.consultaMedicamentosOrdemAlfabetica();
		} else {
			throw new DadoInvalidoException(
					MensagensDeErro.ERRO_ORDENCAO_MEDICAMENTO);
		}
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
		//MedicamentoNomeComparator comparator = new MedicamentoNomeComparator();
		Collections.sort(copiaLista, this.nomeComparator);
		return String.join(",", this.nomesNaLista(copiaLista));
	}

	/**
	 * Metodo que consulta medicamento pela ordenados pelo preco.
	 * 
	 * @return todos os medicamentos em ordenados pelos preco.
	 */
	public String consultaMedicamentosOrdemPreco() {
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
//		MedicamentoPrecoComparator comparator = new MedicamentoPrecoComparator();
		Collections.sort(copiaLista, this.precoComparator);
		return String.join(",", this.nomesNaLista(copiaLista));
	}
	
	/**
	 * Pega o valor dos medicamentos passados como string, separados por virgula
	 * 
	 * @param medicamentos
	 *            Medicamentos
	 * @return Valor
	 */
	public Double getValorMedicamentos(String medicamentos) {
		try {
			String[] nomesMedicamentos = medicamentos.split(",");
			Double valorMedicamentos = new Double(0);

			for (String nome : nomesMedicamentos) {
				ValidadorDeDados.validaString(Constantes.NOME
						+ Constantes.DO_MEDICAMENTO, nome);
				valorMedicamentos += this.pegaMedicamento(
						MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO, nome)
						.getPreco();
			}
			return valorMedicamentos;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(e.getMessage());
		}
	}

}