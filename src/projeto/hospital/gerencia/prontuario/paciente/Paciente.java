package projeto.hospital.gerencia.prontuario.paciente;

import java.io.Serializable;
import java.time.LocalDate;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.hospital.gerencia.tipo_sanguineo.TipoSanguineo;
import projeto.util.Constantes;
import projeto.util.Util;
import projeto.util.reflexao.ConstantesReflection;
import projeto.util.reflexao.MetodoAssociado;
import projeto.util.reflexao.Validacao;

/**
 * Classe para representar os pacientes
 * 
 * @author Eric
 */
public class Paciente implements Serializable, Comparable<Paciente> {
	/**
	 * Serial gerado automaticamente.
	 */
	private static final long serialVersionUID = 1697453654897L;

	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.NOME + Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_NOME)
	private String nome;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_DATA, erro = Constantes.DATA)
	@MetodoAssociado(get = ConstantesReflection.GET_DATA_NASCIMENTO)
	private String data;
	
	@MetodoAssociado(get = ConstantesReflection.GET_IDADE)
	private Integer idade;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_POSITIVO, erro = Constantes.PESO + Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_PESO)
	private Double peso;
	
	@MetodoAssociado(get = ConstantesReflection.GET_TIPO_SANGUINEO)
	private TipoSanguineo tipoSanguineo;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_SEXO, erro = Constantes.SEXO + Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_SEXO)
	private String sexo;
	
	@Validacao(metodo = ConstantesReflection.VALIDA_STRING, erro = Constantes.GENERO + Constantes.DO_PACIENTE)
	@MetodoAssociado(get = ConstantesReflection.GET_GENERO)
	private String genero;
	
	@MetodoAssociado(get = "getGastosPaciente")
	private Double gastos;
	
	private Long id;

	/**
	 * Construtor
	 * 
	 * @param nome
	 *            Nome
	 * @param dataNascimento
	 *            Data de nascimento
	 * @param peso
	 *            Peso
	 * @param tipoSanguineo
	 *            Tipo sanguineo
	 * @param sexoBiologico
	 *            Sexo biologico
	 * @param genero
	 *            Genero
	 */
	public Paciente(String nome, String dataNascimento, Double peso, TipoSanguineo tipoSanguineo, String sexoBiologico,
			String genero) {
		this.nome = nome;
		this.data = dataNascimento;
		this.peso = peso;
		this.tipoSanguineo = tipoSanguineo;
		this.sexo = sexoBiologico;
		this.genero = genero;
		this.gastos = Double.parseDouble(Integer.toString(Constantes.ZERO));
	}

	/**
	 * @return Nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Muda o nome do paciente
	 * 
	 * @param nome
	 *            Nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso
	 *            Peso
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * @return Data de nascimento
	 */
	public String getDataNascimento() {
		return Util.transformaFormatoData(data);
	}

	/**
	 * @return Tipo sanguineo
	 */
	public String getTipoSanguineo() {
		return tipoSanguineo.toString();
	}

	/**
	 * @return Sexo biologico
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @return Genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @return Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Seta um novo id para o paciente
	 * 
	 * @param id
	 *            Novo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Calcula a idade do paciente
	 * 
	 * @return Idade do paciente
	 */
	public Integer getIdade() {
		LocalDate hoje = LocalDate.now();

		String[] nascimento = data.split(Constantes.BARRA);
		int ano = Integer.parseInt(nascimento[Constantes.INDICE_ANO]),
				mes = Integer.parseInt(nascimento[Constantes.INDICE_MES]),
				dia = Integer.parseInt(nascimento[Constantes.INDICE_DIA]);

		int idade = hoje.getYear() - ano - 1;
		if (mes < hoje.getMonthValue() || mes == hoje.getMonthValue() && dia <= hoje.getDayOfMonth())
			idade++;

		return idade;
	}

	public void verificaCompatibilidadeSanguineaCom(String outroTipo) throws DadoInvalidoException{
		this.tipoSanguineo.recebeDe(outroTipo);		
	}
	
	/**
	 * Registra gastos do paciente em procedimentos
	 * 
	 * @param gasto
	 */
	public void registraGasto(double gasto) {
		this.gastos += gasto;
	}

	/**
	 * Metodo temporario, provavelmente vai ser usado algo assim
	 * 
	 * @return Gastos do paciente
	 */
	public String getGastosPaciente() {
		// TODO mudar isso quando sair os testes
		return "O paciente ja gastou R$ " + this.gastos + ".";
	}
	
	/**
	 * Muda o genero do paciente
	 */
	public void mudaGenero() {
		if(this.genero.equalsIgnoreCase(Constantes.MASCULINO))
			this.genero = Util.capitalizaString(Constantes.FEMININO);
		
		else
			this.genero = Util.capitalizaString(Constantes.MASCULINO);
	}

	/**
	 * Compara pacientes pelo nome
	 */
	@Override
	public int compareTo(Paciente paciente) {
		return nome.compareTo(paciente.nome);
	}

	@Override
	public String toString() {
		String saida = "Nome: " + nome + ". ID: " + id + ". Data Nascimento: " + data;
		return saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Compara a partir do nome do paciente
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Paciente))
			return false;

		Paciente paciente = (Paciente) obj;
		return paciente.nome.equals(this.nome);
	}
}
