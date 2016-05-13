package projeto.hospital.gerencia.bancodeorgaos;

import java.io.Serializable;

import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineo;
import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineoFactory;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.reflexao.ConstantesReflection;
import projeto.util.reflexao.MetodoAssociado;
import projeto.util.reflexao.Validacao;

public class Orgao implements Serializable {
	/**
	 * Id gerado automaticamente 
	 */
	private static final long serialVersionUID = -6346901598202920798L;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.NOME + Constantes.DO_ORGAO)
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_TIPO_SANGUINEO, erro = MensagensDeErro.TIPO_SANGUINEO_INVALIDO)
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO_SANGUINEO)
	private TipoSanguineo tipoSanguineo;
	
	private Integer quantidade;

	public Orgao(String nome, String tipoSanguineo) {
		this.nome = nome;
		this.setTipoSanguineo(tipoSanguineo);
		this.quantidade = Constantes.UM;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo.toString();
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = TipoSanguineoFactory.getInstacia().criaTipo(tipoSanguineo);
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
		System.out.println(this.nome + " " + orgao.nome);
		System.out.println(this.tipoSanguineo + " " + orgao.tipoSanguineo);
		return false;
	}
}
