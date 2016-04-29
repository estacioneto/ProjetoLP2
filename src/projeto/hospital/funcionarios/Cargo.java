package projeto.hospital.funcionarios;

import java.util.HashSet;
import java.util.Set;

import projeto.util.Constantes;
import projeto.util.Util;

public class Cargo {
	private Set<Permissao> permissoes;
	private String nome;
	
	public Cargo(String cargo){
		Util.validaString(Constantes.CARGO, cargo);
		this.nome = cargo;
		
		this.permissoes = new HashSet<Permissao>();
		if(cargo.equals(Constantes.DIRETOR)){
			this.permissoes.add(Permissao.CADASTRAR_FUNCIONARIOS);
			this.permissoes.add(Permissao.EXCLUIR_FUNCIONARIOS);
			this.permissoes.add(Permissao.ATUALIZAR_INFORMACOES_FUNCIONARIOS);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
