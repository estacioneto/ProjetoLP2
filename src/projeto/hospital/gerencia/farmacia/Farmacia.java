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
import projeto.util.Util;
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

	/**
	 * Cosntrutor da Farmacia.
	 */
	public Farmacia() {
		this.listaMedicamentos = new ArrayList<>();
	}
	
	/**
	 * Constroi e retorna o comparador de medicamento por nome
	 * 
	 * @return Comparador
	 */
	private Comparator<Medicamento> nomeComparator() {
		return (Medicamento medicamentoA, Medicamento medicamentoB) -> {
			return medicamentoA.getNome().compareTo(medicamentoB.getNome());
		};
	}
	
	/**
	 * Constroi e retorna o comparador de medicamento por preco
	 * 
	 * @return Comparador
	 */
	private Comparator<Medicamento> precoComparator() {
		return (Medicamento medicamentoA, Medicamento medicamentoB) -> {
			if (medicamentoA.getPreco() > medicamentoB.getPreco()) {
				return 1;
			} else if (medicamentoA.getPreco() < medicamentoB.getPreco()) {
				return -1;
			}
			return 0;
		};
	}

	/**
	 * @return Copia da lista de medicamentos.
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
	 */
	public String addMedicamento(String nome, Double preco, Integer quantidade, String tipoMedicamento, String categorias) {
		try {
			Medicamento medicamento;
			medicamento = (Medicamento) Reflection.godFactory(Medicamento.class, nome, preco, quantidade, categorias,
					tipoMedicamento);
			this.listaMedicamentos.add(medicamento);
			return nome;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_MEDICAMENTO + e.getMessage());
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
			Medicamento medicamento = this.pegaMedicamento("", nomeMedicamento);
			atributo = Util.capitalizaString(atributo);
			Reflection.atualizaInfo(medicamento, atributo, novoValor,
					String.format(MensagensDeErro.ERRO_ATRIBUTO_MEDICAMENTO_NAO_ATUALIZAVEL, atributo));
		} catch (DadoInvalidoException | OperacaoInvalidaException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_ATUALIZAR_MEDICAMENTO + e.getMessage());
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
			Medicamento medicamento = this.pegaMedicamento(MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO,
					nomeMedicamento);
			return Reflection.getInfo(medicamento, atributo);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
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
	 */
	public Medicamento pegaMedicamento(String erro, String nomeMedicamento) {
		try {
			for (Medicamento medicamentoAtual : this.listaMedicamentos) {
				if (medicamentoAtual.getNome().equalsIgnoreCase(nomeMedicamento)) {
					return medicamentoAtual;
				}
			}
			throw new DadoInvalidoException(erro);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(e.getMessage() + MensagensDeErro.ERRO_MEDICAMENTO_INEXISTENTE);
		}
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
		Collections.sort(medicamentosCategoria, this.precoComparator());
		return medicamentosCategoria;
	}

	/**
	 * Metodo que recebe uma lista e retorna os nomes dos medicamentos dessa
	 * lista em uma nova lista
	 * 
	 * @param lista Lista
	 * @return List do nome dos medicamentos
	 */
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
	 */
	public String getEstoqueFarmacia(String ordenacao) {
		try {
			if (ordenacao.equalsIgnoreCase("preco")) {
				return this.consultaMedicamentosOrdemPreco();
			} else if (ordenacao.equalsIgnoreCase("alfabetica")) {
				return this.consultaMedicamentosOrdemAlfabetica();
			} else {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_ORDENCAO_MEDICAMENTO);
			}
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
		}
	}

	/**
	 * Metodo que consulta todos o medicamentos que possuem uma determinada
	 * categoria.
	 * 
	 * @param categoria
	 *            Categoria referente ao medicamento.
	 * @return todos os medicamentos que possuem determinada categoria.
	 */
	public String consultaMedicamentoPorCategoria(String categoria) {
		try {
			ValidadorDeDados.validaCategoriaMedicamento(MensagensDeErro.ERRO_MEDICAMENTO_CATEGORIA_INVALIDA, categoria);
			List<String> listaNomeMedicamentosCategoria = this.nomesNaLista(this.medicamentoComCategoria(categoria));
			if (listaNomeMedicamentosCategoria.isEmpty()) {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTOS_NA_CATEGORIA);
			}
			return String.join(",", listaNomeMedicamentosCategoria);
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + e.getMessage());
		}
	}

	/**
	 * Metodo que consulta medicamento pela ordem alfabetica.
	 * 
	 * @return todos os medicamentos em ordem alfabetica.
	 */
	public String consultaMedicamentosOrdemAlfabetica() {
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		Collections.sort(copiaLista, this.nomeComparator());
		return String.join(",", this.nomesNaLista(copiaLista));
	}

	/**
	 * Metodo que consulta medicamento pela ordenados pelo preco.
	 * 
	 * @return todos os medicamentos em ordenados pelos preco.
	 */
	public String consultaMedicamentosOrdemPreco() {
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		Collections.sort(copiaLista, this.precoComparator());
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
				ValidadorDeDados.validaString(Constantes.NOME + Constantes.DO_MEDICAMENTO, nome);
				valorMedicamentos += this.pegaMedicamento("", nome).getPreco();
			}
			return valorMedicamentos;
		} catch (DadoInvalidoException e) {
			throw new OperacaoInvalidaException(e.getMessage());
		}
	}

}