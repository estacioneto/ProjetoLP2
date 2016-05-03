package projeto.util;

public abstract class MensagensDeErro {
	public static final String ERRO_PERMISSAO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. O funcionario %s nao tem permissao para cadastrar funcionarios.";
	public static final String ERRO_PERMISSAO_CADASTRO_PACIENTE = "Nao foi possivel cadastrar o paciente. O funcionario %s nao tem permissao para cadastrar pacientes.";
	public static final String ERRO_PERMISSAO_CADASTRO_MEDICAMENTO = "Erro no cadastro de medicamento. O funcionario %s nao tem permissao para cadastrar medicamentos.";

	public static final String ERRO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. ";
	public static final String NOME_FUNCIONARIO = Constantes.NOME + " do funcionario";
	public static final String MATRICULA_FUNCIONARIO = Constantes.MATRICULA + " do funcionario ";
	public static final String DATA_FUNCIONARIO = Constantes.DATA;
	public static final String SENHA_FUNCIONARIO = Constantes.SENHA;
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
	public static final String ERRO_ATUALIZACAO_PACIENTE = "Nao foi possivel atualizar o paciente. ";
	public static final String ERRO_NOME_ATUALIZACAO_PACIENTE = ERRO_ATUALIZACAO_PACIENTE + Constantes.NOME + " do paciente";
	public static final String ERRO_PESO_ATUALIZACAO_PACIENTE = ERRO_ATUALIZACAO_PACIENTE + Constantes.PESO + " do paciente";
	
	public static final String ERRO_CADASTRO_MEDICAMENTO = "Erro no cadastro de medicamento. ";
	public static final String ERRO_ATUALIZA_MEDICAMENTO = "Erro ao atualizar medicamento. ";
	public static final String ERRO_NOME_MEDICAMENTO = ERRO_CADASTRO_MEDICAMENTO + Constantes.NOME + " do medicamento";
	public static final String ERRO_PRECO_MEDICAMENTO = ERRO_CADASTRO_MEDICAMENTO + Constantes.PRECO + " do medicamento";
	public static final String ERRO_QUANTIDADE_MEDICAMENTO = ERRO_CADASTRO_MEDICAMENTO + Constantes.QUANTIDADE + " do medicamento";
	public static final String ERRO_CATEGORIA_MEDICAMENTO = ERRO_CADASTRO_MEDICAMENTO + Constantes.CATEGORIAS + " do medicamento";
	public static final String ERRO_CATEGORIA_INVALIDA_MEDICAMENTO = ERRO_CADASTRO_MEDICAMENTO + Constantes.CATEGORIAS + " do medicamento nao pode ser nula ou vazia.";
	public static final String ERRO_ATUALIZAR_ATRIBUTO_MEDICAMENTO = ERRO_ATUALIZA_MEDICAMENTO + "%s do medicamento nao pode ser alterado.";
	public static final String ERRO_ATUALIZAR_MEDICAMENTO_INVALIDO = ERRO_ATUALIZA_MEDICAMENTO + "Medicamento nao cadastrado.";
	public static final String ERRO_CONSULTA_CATEGORIA_MEDICAMENTO = "Erro na consulta de medicamentos. Nao ha remedios cadastrados nessa categoria.";
	public static final String ERRO_CONSULTA_CATEGORIA_INVALIDA_MEDICAMENTO =  "Erro na consulta de medicamentos. Categoria invalida.";
	public static final String ERRO_CONSULTA_MEDICAMENTO_INEXISTENTE = "Erro na consulta de medicamentos. Medicamento nao cadastrado.";
	public static final String ERRO_CATEGORIA_INVALIDA = "Erro na consulta de medicamentos. Categoria invalida.";
	public static final String ERRO_MEDICAMENTO_NAO_CADASTRADO = "Medicamento nao cadastrado.";
	public static final String ERRO_CONSULTA_MEDICAMENTO = "Erro na consulta de medicamentos. ";
	public static final String ERRO_ORDENCAO_MEDICAMENTO = ERRO_CONSULTA_MEDICAMENTO + "Tipo de ordenacao invalida.";
	public static final String ERRO_ATUALIZACAO = "Erro de atualizacao.";
	public static final String ERRO_ATUALIZACAO_ENTIDADE = "Erro ao atualizar %s. ";
	public static final String ERRO_CADASTRO = "Erro de casastro.";
	public static final String ERRO_CADASTRO_ENTIDADE = "Erro ao casastrar %s.";
	
}
