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

public class BancoDeOrgaos implements Serializable {
	/**
	 * Id gerado automaticamente 
	 */
	private static final long serialVersionUID = -6166436844133077051L;
	
	private List<Orgao> orgaos;

	public BancoDeOrgaos() {
		this.orgaos = new ArrayList<Orgao>();
	}

	public void cadastraOrgao(String nome, String tipoSanguineo) {
		try {
			this.orgaos.add((Orgao) Reflection.godFactory(Orgao.class, nome, tipoSanguineo));
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	public void retiraOrgao(String nome, String tipoSanguineo) {
		try {
			Orgao orgao = (Orgao) Reflection.godFactory(Orgao.class, nome, tipoSanguineo);

			if (this.orgaos.contains(orgao)) {
				int indice = this.orgaos.indexOf(orgao);
				this.orgaos.remove(indice);
			} else {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_ORGAO_INEXISTENTE);
			}
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_RETIRADA_ORGAO + excecao.getMessage());
		}
	}

	public Orgao getOrgao(String nome, String tipoSanguineo) {
		try {
			Orgao orgao = (Orgao) Reflection.godFactory(Orgao.class, nome, tipoSanguineo);

			if (this.orgaos.contains(orgao)) {
				int indice = this.orgaos.indexOf(orgao);
				return this.orgaos.get(indice);
			} else {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_ORGAO_INEXISTENTE);
			}
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}
	
	public boolean buscaOrgao(String nome, String tipoSanguineo) {
		try {
			Orgao orgao = (Orgao) Reflection.godFactory(Orgao.class, nome, tipoSanguineo);
			return this.orgaos.contains(orgao);
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}
	
	public String buscaOrgPorNome(String nome) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME + Constantes.DO_ORGAO, nome);
			ArrayList<String> orgaos = new ArrayList<>();
			for(Orgao orgao : this.orgaos){
				if(orgao.getNome().equals(nome)){
					orgaos.add(orgao.getTipoSanguineo());
				}
			}
			StringBuilder retorno = new StringBuilder();
			if(orgaos.size() == Constantes.ZERO){
				throw new DadoInvalidoException(MensagensDeErro.ORGAO_NAO_CADASTRADO);
			}
			for(int i = 0; i < orgaos.size(); i++){
				retorno.append(orgaos.get(i));
				if(i != orgaos.size() - 1){
					retorno.append(Constantes.VIRGULA);
				}
			}
			return retorno.toString();
			
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}
	
	public String buscaOrgPorSangue(String tipoSanguineo) {
		try {
			ValidadorDeDados.validaTipoSanguineo(MensagensDeErro.TIPO_SANGUINEO_INVALIDO, tipoSanguineo);
			ArrayList<String> orgaos = new ArrayList<>();
			for(Orgao orgao : this.orgaos){
				if(orgao.getTipoSanguineo().equals(tipoSanguineo)){
					if(!orgaos.contains(orgao.getNome())){
						orgaos.add(orgao.getNome());
					}
				}
			}
			if(orgaos.size() == Constantes.ZERO){
				throw new DadoInvalidoException(MensagensDeErro.ORGAO_TIPO_NAO_CADASTRADO);
			}
			StringBuilder retorno = new StringBuilder();
			
			for(int i = 0; i < orgaos.size(); i++){
				retorno.append(orgaos.get(i));
				if(i != orgaos.size() - 1){
					retorno.append(Constantes.VIRGULA);
				}
			}
			return retorno.toString();
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	public int qtdOrgaos(String nome) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME, nome);
			int quantidade = Constantes.ZERO;

			for (Orgao orgao : this.orgaos) {
				if (orgao.getNome().equals(nome))
					quantidade++;
			}

			if(quantidade == Constantes.ZERO){
				throw new DadoInvalidoException(MensagensDeErro.ORGAO_NAO_CADASTRADO);
			}
			return quantidade;
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_BANCO_ORGAO + excecao.getMessage());
		}
	}

	public int totalOrgaosDisponiveis() {
		return this.orgaos.size();
	}
}
