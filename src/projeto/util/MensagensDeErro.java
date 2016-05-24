package projeto.util;

public abstract class MensagensDeErro {
	// ERROS DE PERMISSAO
	public static final String ERRO_PERMISSAO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. O funcionario %s nao tem permissao para cadastrar funcionarios.";
	public static final String ERRO_PERMISSAO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. O funcionario %s nao tem permissao para cadastrar pacientes.";
	public static final String ERRO_PERMISSAO_CADASTRO_MEDICAMENTO = "Erro no cadastro de medicamento. O funcionario %s nao tem permissao para cadastrar medicamentos.";
	public static final String LOGIN_NECESSARIO = "Voce deve estar logado para acessar o sistema.";
	public static final String ERRO_LOGIN = "Nao foi possivel realizar o login. ";
	public static final String PERMISSAO_NEGADA_ATUALIZACAO = "Funcionario nao pode atualizar informacao.";
	// ERROS DE PERMISSAO
	// FUNCIONARIO
	public static final String ERRO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. ";
	public static final String CARGO_INVALIDO_FUNCIONARIO = "Cargo invalido.";
	public static final String ERRO_CADASTRO_DIRETOR_FUNCIONARIO = "Nao eh possivel criar mais de um Diretor Geral.";
	public static final String ERRO_CONSULTA_FUNCIONARIO = "Erro na consulta de funcionario. ";
	public static final String ERRO_EXCLUSAO_FUNCIONARIO = "Erro ao excluir funcionario. ";
	public static final String ERRO_FUNCIONARIO_NAO_CADASTRADO = "Funcionario nao cadastrado.";
	public static final String ERRO_ATUALIZA_FUNCIONARIO = "Erro ao atualizar funcionario. ";
	public static final String ATRIBUTO_FUNCIONARIO = "Atributo do funcionario ";
	public static final String ATUALIZAR_MATRICULA = "Nao eh possivel atualizar matricula do funcionario.";
	public static final String ERRO_ATUALIZA_INFO = "Erro ao atualizar funcionario. ";
	// FUNCIONARIO
	// PACIENTE
	public static final String ERRO_CONSULTAR_PRONTUARIO = "Erro ao consultar prontuario. ";
	public static final String INDICE_PRONTUARIO = "Indice do prontuario";
	public static final String ERRO_PRONTUARIOS_INSUFICIENTES = "Nao ha prontuarios suficientes (max = %d).";
	public static final String ERRO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. ";
	public static final String TIPO_SANGUINEO = "Tipo sanguineo";
	public static final String TIPO_SANGUINEO_INVALIDO = TIPO_SANGUINEO + " invalido.";
	public static final String PACIENTE_JA_CADASTRADO = "Paciente ja cadastrado.";
	// PACIENTE
	// MEDICAMENTO
	public static final String ERRO_CADASTRO_MEDICAMENTO = "Erro no cadastro de medicamento. ";
	public static final String ERRO_ATUALIZAR_MEDICAMENTO = "Erro ao atualizar medicamento. ";
	public static final String ERRO_ATRIBUTO_MEDICAMENTO_NAO_ATUALIZAVEL = "%s do medicamento nao pode ser alterado.";
	public static final String ERRO_CONSULTA_MEDICAMENTO = "Erro na consulta de medicamentos. ";
	public static final String ERRO_ORDENCAO_MEDICAMENTO = "Tipo de ordenacao invalida.";
	public static final String ERRO_TIPO_MEDICAMENTO = "Tipo do medicamento invalido.";
	// MEDICAMENTO
	// ORGAO
	public static final String ERRO_CADASTRO_ORGAO = "Erro ao cadastrar orgao. ";
	public static final String ERRO_CONSULTA_ORGAO = "Erro na consulta de orgaos. ";
	public static final String ERRO_RETIRADA_ORGAO = "Erro na retirada de orgaos. ";
	public static final String ERRO_BANCO_ORGAO = "O banco de orgaos apresentou um erro. ";
	public static final String ORGAO_TIPO_NAO_CADASTRADO = "Nao ha orgaos cadastrados para esse tipo sanguineo.";
	public static final String ORGAO_NAO_CADASTRADO = "Orgao nao cadastrado.";
	// ORGAO
	// GERAL
	public static final String ERRO_CADASTRO = "Erro no cadastro ";
	public static final String NOME_TAMANHO_INVALIDO = "Nome deve ter menos de 16 caracteres.";
	public static final String NOME_CARACTER_INVALIDO = "Nome deve ter apenas caracteres alfabeticos.";
	public static final String ERRO_CONSULTA_MEDICAMENTOS_NA_CATEGORIA = "Nao ha remedios cadastrados nessa categoria.";
	public static final String ATRIBUTO_NAO_ATUALIZAVEL = "Atributo nao atualizavel.";
	public static final String ERRO_INESPERADO = "Ocorreu um erro inesperado, contate o suporte!";
	// GERAL
	// ERROS DE DADOS
	public static final String CARGO_FUNCIONARIO = "Nome do cargo";
	public static final String SENHA_INVALIDA = "Senha invalida.";
	public static final String PADRAO_MATRICULA = "A matricula nao segue o padrao.";
	public static final String ERRO_CATEGORIA_INVALIDA_MEDICAMENTO = "Erro no cadastro de medicamento. Categoria do medicamento nao pode ser nula ou vazia.";
	public static final String ATRIBUTO_INVALIDO = "Atributo nao valido.";
	public static final String ERRO_MEDICAMENTO_INEXISTENTE = "Medicamento nao cadastrado.";
	public static final String ERRO_MEDICAMENTO_CATEGORIA_INVALIDA = "Categoria invalida.";
	public static final String ERRO_MEDICAMENTO_NAO_CADASTRADO = "Medicamento nao cadastrado.";
	public static final String ERRO_ORGAO_INEXISTENTE = "Orgao nao cadastrado.";
	public static final String SEXO_INVALIDO = "Sexo biologico invalido.";
	public static final String NAO_PODE_SER_NEGATIVO = " nao pode ser negativo.";
	// ERROS DE DADOS
	// ERROS DE PROCEDIMENTOS
	public static final String ERRO_REALIZAR_PROCEDIMENTO = "Erro na realizacao de procedimentos. ";
	public static final String ERRO_SEM_PERMISSAO_PROCEDIMENTO = " nao tem permissao para realizar procedimentos.";
	public static final String FUNCIONARIO_PROIBIDO_REALIZAR_PROCEDIMENTO = "O funcionario %s" + ERRO_SEM_PERMISSAO_PROCEDIMENTO;
	public static final String PROCEDIMENTO_INVALIDO = "Procedimento invalido.";
	// ERROS DE PROCEDIMENTOS
}
