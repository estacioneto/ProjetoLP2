package projeto.farmacia;

import java.util.HashSet;
import java.util.Set;

public abstract class Medicamento{

	private String nome;
	private double preco;
	private int quantidade;
	private Set<String> categorias;
	
	public Medicamento(String nome, double preco, int quantidade, String categorias) throws Exception{
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Nome de medicamento nao pode ser nulo ou vazio.");
		}
		if(preco < 0){
			throw new Exception("Nao ha medicamento com valor negativo.");
		}
		if(quantidade <= 0){
			throw new Exception("Nao ha quantidade nula ou negativa de medicamento.");
		}
		if(categorias == null || categorias.trim().isEmpty()){
			throw new Exception("Nao ha categoria nula ou vazia.");
		}
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.categorias = new HashSet<>();
		this.addCategoriasDoArray(categorias);
	}

	private void addCategoriasDoArray(String categorias){
		String[] arrayCategorias = categorias.split(",");
		
		for(int i=0; i<arrayCategorias.length; i++){
			this.categorias.add(arrayCategorias[i]);
		}
	}
	
	public double getPreco(){
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Set<String> getCategorias() {
		Set<String> copiaCategorias = new HashSet<>(this.categorias);
		return copiaCategorias;
	}
	
	public int getQutDeCategorias(){
		return this.categorias.size();
	}
	
	public abstract double calculaPreco();

	public void recebeQutMedicamentos(int quantidade)throws Exception{
		if(quantidade <= 0){
			throw new Exception("Nao existe quantidade negativa ou nula.");
		}
		this.quantidade += quantidade;
	}
	
	public void pegaQutMedicamentos(int quantidade)throws Exception{
		if(quantidade <= 0){
			throw new Exception("Nao existe quantidade negativa ou nula.");
		}
		if(quantidade > this.quantidade){
			throw new Exception("Nao ha medicamentos suficientes.");
		}
		this.quantidade -= quantidade;
	}
	
	public boolean addCategoria(String categoria) throws Exception{
		if(categoria == null || categoria.trim().isEmpty()){
			throw new Exception("Nao eh permitido a adicao de uma categoria nula ou vazia.");
		}
		boolean condicaoAdicaoCategoria = this.categorias.add(categoria);
		return condicaoAdicaoCategoria;
	}
	
	public boolean removeCategoria(String categoria) throws Exception{
		if(categoria == null || categoria.trim().isEmpty()){
			throw new Exception("Nao eh permitido a remocao de uma categoria nula ou vazia.");
		}
		boolean condicaoAdicaoCategoria = this.categorias.remove(categoria);
		return condicaoAdicaoCategoria;
	}
	
	public boolean contemCategoria(String categoria){
		for(String categoriaAtual : this.categorias){
			if(categoriaAtual.equalsIgnoreCase(categoria)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String formatacao = String.format("Medicamento: %s; preco: %.2f; quantidade atual: %d;", this.getNome(), this.calculaPreco(), this.getQuantidade());
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