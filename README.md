# ProjetoLP2
Projeto da disciplina
Observações e Inicio da formação do Relatório


“O SOOS (Sistema Orientado a Objetos para a Saúde) é um sistema que será usado por gestores e colaboradores de clínicas particulares que possuem parceria com o Sistema Único de Saúde (SUS). O objetivo é fornecer meios que possam agilizar e conduzir a gerência dos recursos humanos e físicos do hospital, como também auxiliar nos procedimentos internos inerentes à organização e seus departamentos”.

Controller e Façade

A Façade do nosso programa é a “fachada”, o que abstrai a lógica de negócios do mesmo, permitindo uma relação entre o usuário e o sistema, sem que seja necessário o conhecimento de como o mesmo funciona. Logo, possui os métodos necessários para realizar as operações almejadas pelo usuário.
O Controller do nosso sistema possui gerenciadores que evitam uma God Class, ou seja, uma classe com muitas responsabilidades, sendo repassada a responsabilidade da lógica de negócios para os seus gerenciadores, os quais farmácia e banco de órgãos são gerenciadores por definição dada.

Validações e Exceções

As exceções que acontecem em quanto o programa é executado, devido a casos inesperados de entradas de determinado usuário ou permissões para executar uma determinada função  são abstraídas pelos validadores da classe ValidadorDeDados e ValidadorDeLogica, o que encapsula a responsabilidade de validar dados ou permissões no momento de execução do programa, e, assim, permite uma manutenção mais rápida e simples, permite o reuso de código, além de modularizar, delegando responsabilidades para classes específicas.
As Exceções são subdividas em exceções de lógica e dados, obtendo uma hierarquia que visa orientar o programador a visualizar de forma mais rápida o tipo de erro acometido.


Constantes e Reuso

Constantes de nosso programa são encapsuladas na classe Constantes, a qual carrega consigo valores padrão constantes que serão utilizadas por diversas classes do sistema; na classe MensagensDeErro que possui as mensagens de erro que serão retornadas para o usuário, quando um determinada exceção ocorrer em nosso programa; na classe Util que possui métodos genericos que são usados em diversas classes do programa. Logo, essa modularização permite o reuso de funcionalidades e constantes em nosso programa.


Packages

Os packages foram criados obedecendo uma ordem hierárquica e uma divisão que abstrai mais a ideia do sistema possuir diferentes partes que podem encapsular sub-partes, a qual possuímos o package principal, projeto, e o subdividimos em três packages: exceptions, hospital e util. Os mesmos possuem caraterísticas comuns entre si, por exemplo, encontramos no package hospital, toda a lógica do hospital: funcionários, pacientes, farmácia, banco de órgãos, entre outros. Enquanto que o package exceptions possui toda hierarquia de exceptions utilizada no programa.

Caso 1

Neste caso 1, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, realizar a liberação de um sistema, o cadastro de um funcionário, login e logout, respeitando as regras do mesmo, nas quais determinado funcionário pode ou não ter acesso a função.
Foi criado um enumeration para as permissões, mantendo a integridade do código e facilitando a análise de permissões que dado funcionário possui.
Para o cargo que cada funcionário possui, foi criado uma herança , na qual existe a superclasse cargo,  uma entidade abstrata pois ela carrega apenas a ideia de como é e como funciona um cargo. Suas respectivas filhas, as quais estendem a classe mãe, cargo, implementam como a lógica de cada cargo deve ocorrer, possibilitando caracterizar cada funcionário com seu cargo, o qual possui um conjunto de permissões específicas para realizar dada função no hospital.



*OBS: 
Façade - Não seria melhor que o sistema fosse liberado no Controller, já que a Façade é apenas uma “fachada” do sistema, não devendo ter lógica de negócios. Vale salientar que nossa Façade seria o Controller e o Controller o nosso Model (Responsável pela lógica de negócios, abstraindo as informações do sistema).
    -(ERIC) Vou arrumar isso
Façade - Modificar o nome setObjeto da Util por “salvaController” ou “escreveArquivo” algo que torne mais intuítivel.
    -(ERIC) ver com estacio, se for usado no controller podemos mudar sim
Controller - utilizar o this.estaLogado() poderia ser melhor para visualização de que o método pertence a classe?
    -(ERIC) Como assim essa parte?
Controller - Vários lançamentos de exceções estam com a mensagem “pura” no mesmo, ao invés de está em MensagensDeErro.
    -(ERIC) só precisa colocar como constante se for usada em varios lugares a mesma mensagem, precisamos ver isso no refatoramento tambem
GerenciadorDeFuncionario - há uma exceção no método cadastraFuncinario que não é constante.


Caso 2

Neste caso 2, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, atualizar as informações de um usuário, assim como, excluir um determinado usuário, respeitando a permissão concedida ao mesmo para execução da função.

*OBS:
Conversor - A classe Conversor possui o método doubleParaString repetido, sem nenhuma diferença na lógica.
    -(ERIC) fixed
Permissão - Seria interessante ter dois arrays constantes de Permissão, um de corpo clínico e outro de de cadastro/modificaoes, pois o primeiro só quem tem acesso é o médico, enquanto o segundo só quem tem acesso é o técnico, e o diretor possui acesso a tudo.

Caso 3

Neste caso 3, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, cadastrar um paciente, salvar suas informações em um prontuário, e consultá-las a partir de um id único que caracteriza o paciente.
O prontuário (composite) compõe um paciente, e por ele tendo acesso a todas suas informações para fins de consulta e para também gerar o histórico do hospital. Os id’s dos pacientes são gerados automaticamente de forma dinâmica pela classe GeradroIdPaciente.

*OBS: alterar o getGastosPaciente e getPontuacao, das anotações do paciente, para uma constanteReflection.

Caso 4

Neste caso 2, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, no qual é possível criar e gerenciar medicamentos, definidos pelos seus tipos.
Foi utilizado uma interface TipoMedicamento para caracterizar o tipo de medicamento existente, o qual difere-se apenas pelo preço final do medicamento, e pelo seu tipo declarado, logo, cada medicamento possui um tipo, como solicitado pelo cliente. 
A farmácia como é responsável por gerenciar os medicamentos, a mesma cria e armazena os medicamentos em uma lista, e a partir dos comparadores definidos com lambda, ordenamos a lista de medicamentos, dado o requisito do usuário.

*OBS: 
Deve ser apagado o GerenciadorDeFaramacia.
Medicamento getCategorias - Alterar o nome novo em getCategorias para que fique mais intuítivel ao desenvolvedor compreender que ali temos uma lista ordenada de categorias.
Não há mais factory de medicamento, logo, deve mudar o javcadoc do addMedicamento em farmácia.
Controller - cadastraMedicamento a mensagem do lançamento de exceção não está como constante.

Caso 5

Neste caso 5, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, cadastrar e gerencias órgãos que serão utilizados futuramente em transplantes.
Orgao é uma classe que define o tipo órgão, e é gerenciado no banco de órgãos, o qual cadastra, retira e busca órgãos.

*OBS:
Orgao - poderia modificar o equals do órgão para return apenas duas vezes, lá está três vezes. Ou seja, se um órgão não eh instancia, ele não entra no bloco e já retorna false, se não, ele entra e verifica os casos e se possivel, retorna true, se não, vai até o fim e retorna false. 
    -(ERIC) fixed

Caso 6

Neste caso 6, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, realizar determinados procedimentos solicitado no hospital, e o mesmo ser armazenado no prontuário do paciente, indicando data, valor e médico que realizou o devido procedimento.
Há uma supreclasse Procedimento, a qual é uma entidade abstrata, pois carrega apenas a ideia do que é e como se realiza um procedimento. As classes filhas que caracterizam um determinado procedimento, extendem da classe Procedimento. Escolhemos herança, pois todo Procedimento possui dados em comum que devem ser armazenados.

Caso 7

Neste caso 7, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, todo paciente possui um cartão fidelidade, o qual é responsável por calcular pontos ganhos pelo paciente devido determinado procedimento, assim como ganhar descontos devido seu cartão.
Escolheu o strategy, pois com o acumulo de pontos, o paciente muda dinamicamente de cartão fidelidade, o que proporciona um maior desconto no pagamento para realizar determinado procedimento.

Caso 8

Neste caso 8, o programa foi modificado para ter uma nova funcionalidade e poder exportar fichas de pacientes, contendo informações do paciente e sobre os procedimentos já realizados pelo mesmo. As fichas são geradas e guardadas de acordo como pedido na especificação.


Caso 9

Neste caso 9, o programa foi modificado para que todo o sistema possa ser guardado, para que mesmo depois de que ele seja fechado depois possa se recuperar as informações previamente cadastradas.
