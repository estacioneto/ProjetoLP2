package projeto.hospital.gerencia.bancodeorgaos;

import projeto.util.Constantes;

public class Orgao {

	private String nome;
	private String tipoSanguineo;
	private int quantidade;

	public Orgao(String nome, String tipoSanguineo) {
		this.nome = nome;
		this.tipoSanguineo = tipoSanguineo;
		this.quantidade = Constantes.UM;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public void adicionaOrgao() {
		this.quantidade++;
	}

	public void removeOrgao() {
		this.quantidade--;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Orgao))
			return false;

		Orgao orgao = (Orgao) obj;
		if (this.nome.equals(orgao.nome)
				&& this.tipoSanguineo.equals(orgao.tipoSanguineo)) {
			return true;
		}
		return false;
	}
}
