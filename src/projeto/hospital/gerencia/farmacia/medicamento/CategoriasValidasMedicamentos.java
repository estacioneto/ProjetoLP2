package projeto.hospital.gerencia.farmacia.medicamento;

/**
 * Enumeracao com as categorias de medicamento
 * 
 * @author Thaynan
 */
public enum CategoriasValidasMedicamentos {

	ANALGESICO("Analgesico"), ANTIBIOTICO("Antibiotico"), ANTITERMICO("Antitermico"), ANTIINFLAMATORIO("Antiinflamatorio"), HORMONAL("Hormonal"), ANTIEMETICO("Antiemetico");

	private String nome;
	
	private CategoriasValidasMedicamentos(String nome){
		this.nome = nome;
	}
	
	/**
	 * @return Nome da categoria
	 */
	public String getNome() {
		return this.nome;
	}
}
