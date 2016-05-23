package projeto.hospital.gerencia.farmacia.medicamento.tipos;

public enum TipoMedicamentoValido {
	
	GENERICO("Generico"), REFERENCIA("Referencia");
	
	private String nome;
	
	private TipoMedicamentoValido(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
