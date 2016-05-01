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
	
	public static final String SEXO = "Sexo";
	public static final String PESO = "Peso";
	public static final String GENERO = "Genero";
	public static final String TIPOS_SANGUINEO = "TipoSanguineo";
	public static final String ID = "Id";
	
	public static final Object MASCULINO = "masculino";
	public static final Object FEMININO = "feminino";
	
	public static final String DATA_REGEX = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d{2})";
	
	public static final HashSet<String> TIPOS_SANGUINEOS_VALIDOS = new HashSet<String>(Arrays.asList(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O-", "O+"}));
	
	public static final String DADOS_DIRETORIO = "BD/";
	public static final File ARQUIVO_DADOS = new File(DADOS_DIRETORIO);
	public static final String ARQUIVO_GERENCIADOR_FUNCIONARIOS = DADOS_DIRETORIO + "gerenciadorFuncionarios.txt";

	public static final HashSet<String> CARGOS_VALIDOS = new HashSet<String>(
			Arrays.asList(new String[] { DIRETOR_GERAL.toLowerCase(), MEDICO.toLowerCase(), TECNICO.toLowerCase(),
					TECNICO_ADMINISTATIVO.toLowerCase() }));

	public static final int ZERO = 0;
	public static final int UM = 1;
	public static final int INDICE_DIA = ZERO;
	public static final int INDICE_MES = UM;
	public static final int INDICE_ANO = 2;
	public static final int QUATRO = 4;
	public static final int NOME_TAMANHO_MAXIMO = 16;
	public static final int SENHA_TAMANHO_MINIMO = 8;
	public static final int SENHA_TAMANHO_MAXIMO = NOME_TAMANHO_MAXIMO;

}
