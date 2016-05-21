# ProjetoLP2
Projeto da disciplina

Observações e Inicio da formação do Relatório


“O SOOS (Sistema Orientado a Objetos para a Saúde) é um sistema que será usado por gestores e colaboradores de clínicas
particulares que possuem parceria com o Sistema Único de Saúde (SUS). O objetivo é fornecer meios que possam agilizar e 
conduzir a gerência dos recursos humanos e físicos do hospital, como também auxiliar nos procedimentos internos inerentes
à organização e seus departamentos”.

Controller e Façade

A Façade do nosso programa é a “fachada”, o que abstrai a lógica de negócios do mesmo, permitindo uma relação entre
o usuário e o sistema, sem que seja necessário o conhecimento de como o mesmo funciona. Logo, possui os métodos necessários
para realizar as operações almejadas pelo usuário.
O Controller do nosso sistema possui gerenciadores que evitam uma God Class, ou seja, uma classe com muitas responsabilidades,
sendo repassada a responsabilidade da lógica de negócios para os seus gerenciadores ou bancos.

Validações e Exceções

As exceções que acontecem em quanto o programa é executado, devido a casos inesperados de entradas de determinado usuário
ou permissões para executar uma determinada função  são abstraídas pelos validadores da classe ValidadorDeDados e 
ValidadorDeLogica, o que encapsula a responsabilidade de validar dados ou permissões no momento de execução do programa, e,
assim, permite uma manutenção mais rápida e simples, permite o reuso de código, além de modularizar, delegando 
responsabilidades para classes específicas.


Constantes

Constantes de nosso programa são encapsuladas na classe Constantes, a qual carrega consigo string constantes que serão
utilizadas por diversas classes do sistema; na classe MensagensDeErro que possuem as mensagens de erro que serão retornadas
para o usuário, quando um determinada exceção ocorrer em nosso programa; na classe Util que possui métodos úteis para
utilização em diversas classes do programa. Logo, essa modularização permite o reuso de funcionalidades e constantes em nosso
programa.

Caso 1

Neste caso 1, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, realizar a liberação de um
sistema, realizar o cadastro de um funcionário, login e logout, respeitando as regras do mesmo, nas quais determinado 
funcionário pode ou não ter acesso a função.

*OBS: 
Façade - Não seria melhor que o sistema fosse liberado no Controller, já que a Façade é apenas uma “fachada” do sistema, não
devendo ter lógica de negócios. Vale salientar que nossa Façade seria o Controller e o Controller o nosso Model 
(Responsável pela lógica de negócios, abstraindo as informações do sistema).
Façade - Modificar o nome setObjeto da Util por “salvaController” ou “escreveArquivo” algo que torne mais intuítivel.
Controller - utilizar o this.estaLogado() poderia ser melhor para visualização de que o método pertence a classe?
Controller - Vários lançamentos de exceções estam com a mensagem “pura” no mesmo, ao invés de está em MensagensDeErro.


Caso 2

Neste caso 2, o programa cumpre com as metas pré-estabelecidas pelo cliente, no qual, é possível, atualizar as informações de
um usuário, assim como, excluir um determinado usuário, respeitando a permissão concedida ao mesmo para execução da função.

*OBS:
Conversor - A classe Conversor possui o método doubleParaString repetido, sem nenhuma diferença na lógica.
Permissão - Seria interessante ter dois arrays constantes de Permissão, um de corpo clínico e outro de de 
cadastro/modificaoes, pois o primeiro só quem tem acesso é o médico, enquanto o segundo só quem tem acesso é o técnico, e o
diretor possui acesso a tudo.

Caso 3


Caso 4


Caso 5


Caso 6


Caso 7


Caso 8


Caso 9




