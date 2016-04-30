package projeto.util;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

public class Constantes {

	public static final String STRING_VAZIA = "";
	public static final String BARRA = "/";
	public static final String FORMATO_TRES_NUMEROS = "000";

	public static final String DIRETOR_GERAL = "Diretor Geral";
	public static final String MEDICO = "Medico";
	public static final String TECNICO_ADMINISTATIVO = "Tecnico Administrativo";
	public static final String TECNICO = "Tecnico";
	public static final String FUNCIONARIO = "Funcionario";
	public final static String CODIGO_DIRETOR = "1";
	public final static String CODIGO_MEDICO = "2";
	public final static String CODIGO_TECNICO = "3";
	public final static String PRIMEIRO_CADASTRO = "001";

	public static final String NOME = "Nome";
	public static final String PRECO = "Preco";
	public static final String CARGO = "Cargo";
	public static final String ATRIBUTO = "Atributo";
	public static final String DATA = "Data";
	public static final String SENHA = "Senha";
	public static final String MATRICULA = "Matricula";

	public static final String DADOS_DIRETORIO = "BD/";
	public static final File ARQUIVO_DADOS = new File(DADOS_DIRETORIO);
	public static final String ARQUIVO_GERENCIADOR_FUNCIONARIOS = DADOS_DIRETORIO + "gerenciadorFuncionarios.txt";

	public static final String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d{2})";

	public static final String ERRO_CADASTRO_FUNCIONARIO = "Erro no cadastro de funcionario. ";
	public static final String ERRO_NOME_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO + NOME + " do funcionario ";
	public static final String ERRO_MATRICULA_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO + MATRICULA + " do funcionario ";
	public static final String ERRO_DATA_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO + DATA;
	public static final String ERRO_SENHA_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO + SENHA;
	public static final String ERRO_CARGO_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO + "Nome do cargo ";
	public static final String ERRO_CARGO_INVALIDO_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO + "Cargo invalido.";
	public static final String ERRO_CADASTRO_DIRETOR_FUNCIONARIO = ERRO_CADASTRO_FUNCIONARIO
			+ "Nao eh possivel criar mais de um Diretor Geral.";
	public static final String ERRO_CONSULTA_FUNCIONARIO = "Erro na consulta de funcionario. ";

	public static final HashSet<String> CARGOS_VALIDOS = new HashSet<String>(
			Arrays.asList(new String[] { DIRETOR_GERAL.toLowerCase(), MEDICO.toLowerCase(), TECNICO.toLowerCase(),
					TECNICO_ADMINISTATIVO.toLowerCase() }));

	public static final int ZERO = 0;
	public static final int INDICE_DIA = ZERO;
	public static final int INDICE_MES = 1;
	public static final int INDICE_ANO = 2;
	public static final int QUATRO = 4;

}
