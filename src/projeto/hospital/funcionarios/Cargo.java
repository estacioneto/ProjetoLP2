package projeto.hospital.funcionarios;

import java.util.HashSet;
import java.util.Set;

import projeto.util.Constantes;
import projeto.util.Util;

/**
 * Classe Cargo. Carrega as permissoes do mesmo.
 * 
 * @author Estacio Pereira
 *
 */
public class Cargo {

	private Set<Permissao> permissoes;
	private String nome;

	/**
	 * Construtor padrao.
	 * 
	 * @param cargo
	 *            Cargo do funcionario.
	 */
	public Cargo(String cargo) {
		Util.validaString(Constantes.CARGO, cargo);
		this.nome = cargo;

		this.permissoes = new HashSet<Permissao>();
		if (cargo.equals(Constantes.DIRETOR)) {
			this.permissoes = Cargo.getPermissoesDiretor();
		}
	}

	/**
	 * Metodo que retorna as permissoes de um cago dado o codigo do mesmo.
	 * 
	 * @param codigo
	 *            Codigo do cargo.
	 * @return Conjunto de permissoes do cargo.
	 */
	public static Set<Permissao> getPermissoesPorCodigo(String codigo) {
		Set<Permissao> permissoes = new HashSet<Permissao>();
		if (codigo.equals(Constantes.CODIGO_DIRETOR)) {
			permissoes = getPermissoesDiretor();
		}
		return permissoes;
	}

	/**
	 * Metodo que retorna as permissoes do diretor.
	 * 
	 * @return Conjunto de permissoes do diretor.
	 */
	public static Set<Permissao> getPermissoesDiretor() {
		Set<Permissao> permissoes = new HashSet<Permissao>();
		permissoes.add(Permissao.CADASTRAR_FUNCIONARIOS);
		permissoes.add(Permissao.EXCLUIR_FUNCIONARIOS);
		permissoes.add(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS);

		return permissoes;
	}

	/**
	 * Retorna o nome do cargo
	 * 
	 * @return Nome do cargo
	 */
	public String getNome() {
		return nome;
	}
}
