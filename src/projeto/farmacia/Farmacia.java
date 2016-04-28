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
	
	public boolean addMedicamento(String nome, double preco, int quantidade, String tipoMedicamento) throws Exception{
		Medicamento medicamento = medicamentoFactory.criaMedicamento(nome, preco, quantidade, tipoMedicamento);
		boolean condicaoDeAdicao = this.listaMedicamentos.add(medicamento);
		return condicaoDeAdicao;
	}
	
	public void ordenaListaMedicamentos(){
		Collections.sort(this.listaMedicamentos);
	}
}