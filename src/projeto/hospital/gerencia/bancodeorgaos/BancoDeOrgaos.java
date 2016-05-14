package projeto.hospital.gerencia.bancodeorgaos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;
import projeto.util.reflexao.Reflection;

/**
 * Banco de ogaos. Gerencia os orgaos do hospital.
 * 
 * @author Estacio Pereira
 *
 */
public class BancoDeOrgaos implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = -6166436844133077051L;

	private List<Orgao> orgaos;

	/**
	 * Construtor padrao.
	 */
	public BancoDeOrgaos() {
		this.orgaos = new ArrayList<Orgao>();
	}

	/**
	 * Cadastra um orgao no banco.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao.
	 */
	public void cadastraOrgao(String nome, String tipoSanguineo) {
		try {
			this.orgaos.add((Orgao) Reflection.godFactory(Orgao.class, nome,
					tipoSanguineo));
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Retira um orgao do banco de orgaos.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao.
	 */
	public void retiraOrgao(String nome, String tipoSanguineo) {
		try {
			Orgao orgao = (Orgao) Reflection.godFactory(Orgao.class, nome,
					tipoSanguineo);

			if (this.orgaos.contains(orgao)) {
				int indice = this.orgaos.indexOf(orgao);
				this.orgaos.remove(indice);
			} else {
				throw new DadoInvalidoException(
						MensagensDeErro.ERRO_ORGAO_INEXISTENTE);
			}
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_RETIRADA_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Retorna o orgao com os atributos indicados.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao.
	 * @return Orgao solicitado.
	 */
	public Orgao getOrgao(String nome, String sanguePaciente) {
		try {
			for(Orgao orgao : this.orgaos){
				if(orgao.getNome().equalsIgnoreCase(nome) && sanguePaciente.equals((orgao.getTipoSanguineo()))){
					this.orgaos.remove(orgao);
					return orgao;
				}
			}
			throw new DadoInvalidoException(MensagensDeErro.ERRO_ORGAO_INEXISTENTE);
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Verifica se determinado orgao foi cadastrado no banco.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 * @param tipoSanguineo
	 *            Tipo sanguineo do orgao.
	 * @return {@code true} se o orgao foi cadastrado.
	 */
	public boolean buscaOrgao(String nome, String tipoSanguineo) {
		try {
			Orgao orgao = (Orgao) Reflection.godFactory(Orgao.class, nome,
					tipoSanguineo);
			return this.orgaos.contains(orgao);
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Busca os orgaos que possuem o nome informado.
	 *
	 * @param nome
	 *            Nome do orgao.
	 * @return Uma {@code String} dos tipos sanguineos disponiveis para aquele
	 *         orgao separados por virgula.
	 */
	public String buscaOrgPorNome(String nome) {
		try {
			ValidadorDeDados.validaString(
					Constantes.NOME + Constantes.DO_ORGAO, nome);
			ArrayList<String> orgaos = new ArrayList<>();
			for (Orgao orgao : this.orgaos) {
				if (orgao.getNome().equals(nome)) {
					orgaos.add(orgao.getTipoSanguineo());
				}
			}
			StringBuilder retorno = new StringBuilder();
			if (orgaos.size() == Constantes.ZERO) {
				throw new DadoInvalidoException(
						MensagensDeErro.ORGAO_NAO_CADASTRADO);
			}
			for (int i = 0; i < orgaos.size(); i++) {
				retorno.append(orgaos.get(i));
				if (i != orgaos.size() - 1) {
					retorno.append(Constantes.VIRGULA);
				}
			}
			return retorno.toString();

		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Busca os orgaos que possuem o tipo sanguineo informado.
	 *
	 * @param tipoSanguineo
	 *            Tipo sanguineo a ser pesquisado.
	 * @return Uma {@code String} dos nomes dos orgaos disponiveis para aquele
	 *         tipo sanguineo separados por virgula.
	 */
	public String buscaOrgPorSangue(String tipoSanguineo) {
		try {
			ValidadorDeDados.validaTipoSanguineo(
					MensagensDeErro.TIPO_SANGUINEO_INVALIDO, tipoSanguineo);
			ArrayList<String> orgaos = new ArrayList<>();
			for (Orgao orgao : this.orgaos) {
				if (orgao.getTipoSanguineo().equals(tipoSanguineo)) {
					if (!orgaos.contains(orgao.getNome())) {
						orgaos.add(orgao.getNome());
					}
				}
			}
			if (orgaos.size() == Constantes.ZERO) {
				throw new DadoInvalidoException(
						MensagensDeErro.ORGAO_TIPO_NAO_CADASTRADO);
			}
			StringBuilder retorno = new StringBuilder();

			for (int i = 0; i < orgaos.size(); i++) {
				retorno.append(orgaos.get(i));
				if (i != orgaos.size() - 1) {
					retorno.append(Constantes.VIRGULA);
				}
			}
			return retorno.toString();
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Retorna a quantidade de orgaos com o nome informado.
	 * 
	 * @param nome
	 *            Nome do orgao.
	 * @return Quantidade de orgaos com o nome requisitado.
	 */
	public int qtdOrgaos(String nome) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME, nome);
			int quantidade = Constantes.ZERO;

			for (Orgao orgao : this.orgaos) {
				if (orgao.getNome().equals(nome))
					quantidade++;
			}

			if (quantidade == Constantes.ZERO) {
				throw new DadoInvalidoException(
						MensagensDeErro.ORGAO_NAO_CADASTRADO);
			}
			return quantidade;
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(
					MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	/**
	 * Retorna o total de orgaos no banco.
	 * 
	 * @return Total de orgaos no banco.
	 */
	public int totalOrgaosDisponiveis() {
		return this.orgaos.size();
	}
}
