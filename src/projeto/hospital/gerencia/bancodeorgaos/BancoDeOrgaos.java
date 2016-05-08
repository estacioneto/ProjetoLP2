package projeto.hospital.gerencia.bancodeorgaos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.exceptions.logica.OperacaoInvalidaException;
import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineo;
import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineoFactory;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;

public class BancoDeOrgaos implements Serializable {
	/**
	 * Id gerado automaticamente 
	 */
	private static final long serialVersionUID = -6166436844133077051L;
	
	private List<Orgao> orgaos;
	private TipoSanguineoFactory tipoSanguineoFactory;

	public BancoDeOrgaos() {
		this.orgaos = new ArrayList<Orgao>();
		this.tipoSanguineoFactory = TipoSanguineoFactory.getInstacia();
	}

	public void adicionaOrgao(String nome, String tipoSanguineo) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME, nome);
			TipoSanguineo sangue = tipoSanguineoFactory.criaTipo(tipoSanguineo);
			
			this.orgaos.add(new Orgao(nome, sangue));
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CADASTRO_ORGAO + excecao.getMessage());
		}
	}

	public void removeOrgao(String nome, String tipoSanguineo) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME, nome);
			TipoSanguineo sangue = tipoSanguineoFactory.criaTipo(tipoSanguineo);
			
			Orgao orgao = new Orgao(nome, sangue);

			if (this.orgaos.contains(orgao)) {
				int indice = this.orgaos.indexOf(orgao);
				this.orgaos.remove(indice);
			} else {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_ORGAO_INEXISTENTE);
			}
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_REMOCAO_ORGAO + excecao.getMessage());
		}
	}

	public Orgao getOrgao(String nome, String tipoSanguineo) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME, nome);
			TipoSanguineo sangue = tipoSanguineoFactory.criaTipo(tipoSanguineo);

			Orgao orgao = new Orgao(nome, sangue);

			if (this.orgaos.contains(orgao)) {
				int indice = this.orgaos.indexOf(orgao);
				return this.orgaos.get(indice);
			} else {
				throw new DadoInvalidoException(MensagensDeErro.ERRO_ORGAO_INEXISTENTE);
			}
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_REMOCAO_ORGAO + excecao.getMessage());
		}
	}

	public int getQuantidadeOrgao(String nome) {
		try {
			ValidadorDeDados.validaString(Constantes.NOME, nome);
			int quantidade = Constantes.ZERO;

			for (Orgao orgao : this.orgaos) {
				if (orgao.getNome().equals(nome))
					quantidade++;
			}

			return quantidade;
		} catch (DadoInvalidoException excecao) {
			throw new OperacaoInvalidaException(MensagensDeErro.ERRO_CONSULTA_ORGAO + excecao.getMessage());
		}
	}

	public int getQuantidadeTotal() {
		return this.orgaos.size();
	}
}
