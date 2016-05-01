package projeto.farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;

public class Farmacia {

	private List<Medicamento> listaMedicamentos;
	private MedicamentoFactory medicamentoFactory;
	
	public Farmacia(){
		this.listaMedicamentos = new ArrayList<>();
		this.medicamentoFactory = new MedicamentoFactory();
	}

	public List<Medicamento> getListaMedicamentos() {
		List<Medicamento> copiaDaLista = new ArrayList<>(this.listaMedicamentos);
		return copiaDaLista;
	}
	
	public String addMedicamento(String nome, Double preco, int quantidade, String tipoMedicamento, String categorias){
		Medicamento medicamento = medicamentoFactory.criaMedicamento(nome, tipoMedicamento, preco, quantidade, categorias);
		this.listaMedicamentos.add(medicamento);
		return nome;
	}
	
	public Medicamento verificaMedicamentoExistente(String erro, String nomeMedicamento){
		for(Medicamento medicamentoAtual : this.listaMedicamentos){
			if(medicamentoAtual.getNome().equalsIgnoreCase(nomeMedicamento)){
				return medicamentoAtual;
			}
		}
		throw new DadoInvalidoException(erro);
	}
	
	private List<Medicamento> medicamentoComCategoria(String categoria){
		List<Medicamento> medicamentosCategoria = new ArrayList<>();
		
		for(Medicamento medicamentoAtual : this.listaMedicamentos){
			if(medicamentoAtual.contemCategoria(categoria)){
				medicamentosCategoria.add(medicamentoAtual);
			}
		}
		MedicamentoPrecoComparator comparator = new MedicamentoPrecoComparator();
		Collections.sort(medicamentosCategoria, comparator);
		return medicamentosCategoria;
	}
	
	public String consultaMedicamentoPorCategoria(String categoria){
		ValidadorDeDados.validaCategoriaMedicamento(categoria);
		List<String> listaNomeMedicamentosCategoria = new ArrayList<>();
		for(Medicamento medicamentoAtual : this.medicamentoComCategoria(categoria)){
			listaNomeMedicamentosCategoria.add(medicamentoAtual.getNome());
		}
		if(listaNomeMedicamentosCategoria.isEmpty()){
			throw new DadoInvalidoException(MensagensDeErro.ERRO_CONSULTA_CATEGORIA_MEDICAMENTO);
		}
		return String.join(",", listaNomeMedicamentosCategoria);
	}
	
	private List<String> nomesNaLista(List<Medicamento> lista){
		List<String> listaNomeMedicamentos = new ArrayList<>();
		for(Medicamento medicamentoAtual : lista){
			listaNomeMedicamentos.add(medicamentoAtual.getNome());
		}
		return listaNomeMedicamentos;
	}
	
	public String verificaMedicamentosOrdemAlfabetica(){
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		MedicamentoNomeComparator comparator = new MedicamentoNomeComparator();
		Collections.sort(copiaLista, comparator);
		return String.join(",",this.nomesNaLista(copiaLista));
	}
	
	public String verificaMedicamentosOrdemPreco(){
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		MedicamentoPrecoComparator comparator = new MedicamentoPrecoComparator();
		Collections.sort(copiaLista, comparator);
		return String.join(",",this.nomesNaLista(copiaLista));
	}
}