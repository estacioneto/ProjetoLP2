package projeto.farmacia;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.util.*;

public abstract class Medicamento{

	private String nome;
	private Double preco;
	private int quantidade;
	private String categorias;
	
	public Medicamento(String nome, Double preco, int quantidade, String categorias){
		ValidadorDeDados.validaString(MensagensDeErro.ERRO_NOME_MEDICAMENTO, nome);
		ValidadorDeDados.validaPositivo(MensagensDeErro.ERRO_PRECO_MEDICAMENTO, preco);
		ValidadorDeDados.validaPositivo(MensagensDeErro.ERRO_QUANTIDADE_MEDICAMENTO, quantidade);
		ValidadorDeDados.validaString(MensagensDeErro.ERRO_CATEGORIA_MEDICAMENTO, categorias);
		
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = categorias;
	}
	
	public abstract String getTipo();
	
	public double getPreco(){
		return preco;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getCategorias() {
		Set<String> novo = new TreeSet<>(Arrays.asList(this.categorias.split(",")));
		return String.join(",", novo);
	}
	
	public abstract Double calculaPreco();

	public void recebeQutMedicamentos(int quantidade){
		if(quantidade <= 0){
			throw new DadoInvalidoException("Nao existe quantidade negativa ou nula.");
		}
		this.quantidade += quantidade;
	}
	
	public void pegaQutMedicamentos(int quantidade){
		if(quantidade <= 0){
			throw new DadoInvalidoException("Nao existe quantidade negativa ou nula.");
		}
		if(quantidade > this.quantidade){
			throw new DadoInvalidoException("Nao ha medicamentos suficientes.");
		}
		this.quantidade -= quantidade;
	}
	
	public boolean contemCategoria(String categoria){
		String[] arrayCategorias = this.categorias.split(",");
		for(int i=0; i<arrayCategorias.length; i++){
			if(arrayCategorias[i].equalsIgnoreCase(categoria)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String formatacao = String.format(" %s - Preco: R$ %.2f - Disponivel: %d - Categorias: %s", this.getNome(), this.calculaPreco(), this.getQuantidade(), this.getCategorias());
		return formatacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categorias == null) ? 0 : categorias.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (categorias == null) {
			if (other.categorias != null)
				return false;
		} else if (!categorias.equals(other.categorias))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(preco) != Double
				.doubleToLongBits(other.preco))
			return false;
		return true;
	}
}