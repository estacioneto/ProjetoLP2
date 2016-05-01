package projeto.farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public void addMedicamento(String nome, double preco, int quantidade, String tipoMedicamento, String categorias) throws Exception{
		Medicamento medicamento = medicamentoFactory.criaMedicamento(nome, preco, quantidade, tipoMedicamento, categorias);
		this.listaMedicamentos.add(medicamento);
	}
	
	public Medicamento verificaMedicamentoExistente(String nomeMedicamento){
		for(Medicamento medicamentoAtual : this.listaMedicamentos){
			if(medicamentoAtual.getNome().equalsIgnoreCase(nomeMedicamento)){
				return medicamentoAtual;
			}
		}
		return null;
	}
	
	public boolean addCategoriaMedicamento(String nomeMedicamento, String categoria) throws Exception{
		Medicamento medicamento = this.verificaMedicamentoExistente(nomeMedicamento);
		if(medicamento != null){
			return medicamento.addCategoria(categoria);
		}
		return false;
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
		List<String> listaNomeMedicamentosCategoria = new ArrayList<>();
		for(Medicamento medicamentoAtual : this.medicamentoComCategoria(categoria)){
			listaNomeMedicamentosCategoria.add(medicamentoAtual.getNome());
		}
		return listaNomeMedicamentosCategoria.toString();
	}
	
	public String buscaMedicamento(String nomeMedicamento) throws Exception{
		Medicamento medicamento = this.verificaMedicamentoExistente(nomeMedicamento);
		if(medicamento != null){
			return medicamento.toString();
		}
		throw new Exception("Medicamento inexistente."); 
	}
	
	public String verificaMedicamentosOrdemAlfabetica(){
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		MedicamentoNomeComparator comparator = new MedicamentoNomeComparator();
		Collections.sort(copiaLista, comparator);
		return copiaLista.toString();
	}
	
	public String verificaMedicamentosOrdemPreco(){
		List<Medicamento> copiaLista = new ArrayList<>(this.listaMedicamentos);
		MedicamentoPrecoComparator comparator = new MedicamentoPrecoComparator();
		Collections.sort(copiaLista, comparator);
		return copiaLista.toString();
	}
}