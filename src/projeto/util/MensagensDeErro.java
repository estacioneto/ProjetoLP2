package projeto.util;

public abstract class MensagensDeErro {
	public static final String ERRO_PERMISSAO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. O funcionario %s nao tem permissao para cadastrar funcionarios.";
	public static final String ERRO_PERMISSAO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. O funcionario %s nao tem permissao para cadastrar pacientes.";
	public static final String ERRO_PERMISSAO_CADASTRO_MEDICAMENTO = "Erro no cadastro de medicamento. O funcionario %s nao tem permissao para cadastrar medicamentos.";

	public static final String ERRO_LOGIN = "Nao foi possivel realizar o login. ";
	public static final String ERRO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. ";
	public static final String ERRO_CADASTRO = "Erro no cadastro ";
	public static final String CARGO_FUNCIONARIO = "Nome do cargo";
	public static final String CARGO_INVALIDO_FUNCIONARIO = "Cargo invalido.";
	public static final String ERRO_CADASTRO_DIRETOR_FUNCIONARIO = "Nao eh possivel criar mais de um Diretor Geral.";
	public static final String ERRO_CONSULTA_FUNCIONARIO = "Erro na consulta de funcionario. ";
	public static final String ERRO_EXCLUSAO_FUNCIONARIO = "Erro ao excluir funcionario. ";
	public static final String ERRO_FUNCIONARIO_NAO_CADASTRADO = "Funcionario nao cadastrado.";
	public static final String SENHA_INVALIDA = "Senha invalida.";
	public static final String PADRAO_MATRICULA = "A matricula nao segue o padrao.";
	public static final String ERRO_ATUALIZA_FUNCIONARIO = "Erro ao atualizar funcionario. ";
	public static final String ATRIBUTO_FUNCIONARIO = "Atributo do funcionario ";
	public static final String ATUALIZAR_MATRICULA = "Nao eh possivel atualizar matricula do funcionario.";
	public static final String ATRIBUTO_INVALIDO = "Atributo nao valido.";
	public static final String NOME_TAMANHO_INVALIDO = "Nome deve ter menos de 16 caracteres.";
	public static final String NOME_CARACTER_INVALIDO = "Nome deve ter apenas caracteres alfabeticos.";
	public static final String PERMISSAO_NEGADA_ATUALIZACAO = "Funcionario nao pode atualizar informacao.";

	public static final String ERRO_CONSULTAR_PRONTUARIO = "Erro ao consultar prontuario. ";
	public static final String INDICE_PRONTUARIO = "Indice do prontuario";
	public static final String ERRO_PRONTUARIOS_INSUFICIENTES = "Nao ha prontuarios suficientes (max = %d).";

	public static final String ERRO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. ";
	public static final String TIPO_SANGUINEO = "Tipo sanguineo";
	public static final String PACIENTE_JA_CADASTRADO = "Paciente ja cadastrado.";
	
	public static final String ERRO_CADASTRO_MEDICAMENTO = "Erro no cadastro de medicamento. ";
	public static final String ERRO_ATUALIZAR_MEDICAMENTO = "Erro ao atualizar medicamento. ";
	public static final String ERRO_CATEGORIA_INVALIDA_MEDICAMENTO = ERRO_CADASTRO_MEDICAMENTO + Constantes.CATEGORIAS + " do medicamento nao pode ser nula ou vazia.";
	public static final String ERRO_ATRIBUTO_MEDICAMENTO_NAO_ATUALIZAVEL = "%s do medicamento nao pode ser alterado.";
	public static final String ERRO_CONSULTA_MEDICAMENTOS_NA_CATEGORIA = "Nao ha remedios cadastrados nessa categoria.";
	public static final String ERRO_MEDICAMENTO_INEXISTENTE = "Medicamento nao cadastrado.";
	public static final String ERRO_MEDICAMENTO_CATEGORIA_INVALIDA = "Categoria invalida.";
	public static final String ERRO_MEDICAMENTO_NAO_CADASTRADO = "Medicamento nao cadastrado.";
	public static final String ERRO_CONSULTA_MEDICAMENTO = "Erro na consulta de medicamentos. ";
	public static final String ERRO_ORDENCAO_MEDICAMENTO = "Tipo de ordenacao invalida.";
}
