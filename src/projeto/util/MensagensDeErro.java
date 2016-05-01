package projeto.util;

public class MensagensDeErro {
	public static final String ERRO_PERMISSAO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. O funcionario %s nao tem permissao para cadastrar funcionarios.";
	public static final String ERRO_PERMISSAO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. O funcionario %s nao tem permissao para cadastrar pacientes.";

	public static final String ERRO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. ";
	public static final String NOME_FUNCIONARIO = Constantes.NOME + " do funcionario";
	public static final String MATRICULA_FUNCIONARIO = Constantes.MATRICULA + " do funcionario ";
	public static final String DATA_FUNCIONARIO = Constantes.DATA;
	public static final String SENHA_FUNCIONARIO = Constantes.SENHA;
	public static final String CARGO_FUNCIONARIO = "Nome do cargo";
	public static final String CARGO_INVALIDO_FUNCIONARIO = "Cargo invalido.";
	public static final String ERRO_CADASTRO_DIRETOR_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO
			+ "Nao eh possivel criar mais de um Diretor Geral.";
	public static final String ERRO_CONSULTA_FUNCIONARIO = "Erro na consulta de funcionario. ";
	public static final String ERRO_EXCLUSAO_FUNCIONARIO = "Erro ao excluir funcionario. ";
	public static final String ERRO_FUNCIONARIO_NAO_CADASTRADO = "Funcionario nao cadastrado.";
	public static final String SENHA_INVALIDA = "Senha invalida.";
	public static final String PADRAO_MATRICULA = "A matricula nao segue o padrao.";
	public static final String ERRO_ATUALIZA_INFO = "Erro ao atualizar funcionario. ";
	public static final String ATRIBUTO_FUNCIONARIO = "Atributo do funcionario ";
	public static final String ATUALIZAR_MATRICULA = "Nao eh possivel atualizar matricula do funcionario.";
	public static final String ATRIBUTO_INVALIDO = "Atributo nao valido.";
	public static final String NOME_TAMANHO_INVALIDO = "Nome deve ter menos de 16 caracteres.";
	public static final String NOME_CARACTER_INVALIDO = "Nome deve ter apenas caracteres alfabeticos.";
	public static final String PERMISSAO_NEGADA_ATUALIZACAO = "Funcionario nao pode atualizar informacao.";

	public static final String ERRO_CONSULTAR_PRONTUARIO = "Erro ao consultar prontuario. ";
	public static final String INDICE_PRONTUARIO = ERRO_CONSULTAR_PRONTUARIO + "Indice do prontuario";
	public static final String ERRO_PRONTUARIOS_INSUFICIENTES = ERRO_CONSULTAR_PRONTUARIO
			+ "Nao ha prontuarios suficientes ";

	public static final String ERRO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. ";
	public static final String ERRO_NOME_PACIENTE = ERRO_CADASTRO_PACIENTE + Constantes.NOME + " do paciente";
	public static final String ERRO_TIPO_SANGUINEO_PACIENTE = ERRO_CADASTRO_PACIENTE + "Tipo sanguineo";
	public static final String ERRO_PESO_PACIENTE = ERRO_CADASTRO_PACIENTE + Constantes.PESO + " do paciente";
	public static final String ERRO_DATA_PACIENTE = ERRO_CADASTRO_PACIENTE + Constantes.DATA;
	public static final String ERRO_PACIENTE_JA_CADASTRADO = ERRO_CADASTRO_PACIENTE + "Paciente ja cadastrado.";

}
