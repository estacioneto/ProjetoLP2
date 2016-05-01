package projeto.hospital.funcionarios;

import java.io.Serializable;
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
public class Cargo implements Serializable {

	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 8465811114464412946L;
	
	private HashSet<Permissao> permissoes;
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
		if (cargo.equals(Constantes.DIRETOR_GERAL)) {
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
		} else if(codigo.equals(Constantes.CODIGO_TECNICO)){
			permissoes = getPermissoesTecnico();
		}
		return permissoes;
	}

	/**
	 * Metodo que retorna as permissoes do tecnico.
	 * 
	 * @return Conjunto de permissoes do tecnico
	 */
	private static Set<Permissao> getPermissoesTecnico() {
		HashSet<Permissao> permissoes = new HashSet<Permissao>();
		permissoes.add(Permissao.CADASTRAR_PACIENTES);
		return permissoes;
	}

	/**
	 * Metodo que retorna as permissoes do diretor.
	 * 
	 * @return Conjunto de permissoes do diretor.
	 */
	public static HashSet<Permissao> getPermissoesDiretor() {
		HashSet<Permissao> permissoes = new HashSet<Permissao>();
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

	public boolean temPermissao(Permissao permissao) {
		return this.permissoes.contains(permissao);
	}
}
