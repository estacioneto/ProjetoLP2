package projeto.hospital.gerencia.funcionario.cargo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Cargo. Carrega as permissoes do mesmo.
 * 
 * @author Estacio Pereira
 * @author Eric
 * 
 */
public abstract class Cargo implements Serializable {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 8465811114464412946L;

	protected Set<Permissao> permissoes;
	private String nome;

	/**
	 * Construtor padrao.
	 * 
	 * @param cargo
	 *            Cargo do funcionario. 
	 */
	public Cargo(String cargo) {
		this.nome = cargo;
		this.permissoes = new HashSet<Permissao>();
	}

	/**
	 * Metodo que retorna as permissoes de um cargo.
	 * 
	 * @return Conjunto de permissoes do cargo.
	 */
	public Set<Permissao> getPermissoes() {
		return this.permissoes;
	}

	/**
	 * Retorna o nome do cargo
	 * 
	 * @return Nome do cargo
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Verifica se o cargo possui determinada permissao.
	 * 
	 * @param permissao
	 *            Permissao a ser verificada.
	 * @return true se o cargo permite determinada operacao.
	 */
	public boolean temPermissao(Permissao permissao) {
		return this.permissoes.contains(permissao);
	}
}
