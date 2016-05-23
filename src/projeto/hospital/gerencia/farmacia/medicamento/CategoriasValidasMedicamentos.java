package projeto.hospital.gerencia.farmacia.medicamento;

public enum CategoriasValidasMedicamentos {

	ANALGESICO("Analgesico"), ANTIBIOTICO("Antibiotico"), ANTITERMICO("Antitermico"), ANTIINFLAMATORIO("Antiinflamatorio"), HORMONAL("Hormonal"), ANTIEMETICO("Antiemetico");

	private String nome;
	
	private CategoriasValidasMedicamentos(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
}
