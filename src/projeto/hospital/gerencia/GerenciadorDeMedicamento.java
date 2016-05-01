package projeto.hospital.gerencia;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.farmacia.Farmacia;
import projeto.util.Constantes;
import projeto.util.MensagensDeErro;
import projeto.util.ValidadorDeDados;
import projeto.farmacia.Medicamento;

public class GerenciadorDeMedicamento {

	private Farmacia farmacia;
	
	public GerenciadorDeMedicamento(){
		farmacia = new Farmacia();
	}
	public String cadastraMedicamento(String nome, String tipo, Double preco, int quantidade, String categorias){
		return farmacia.addMedicamento(nome, preco, quantidade, tipo, categorias);
	}
	
	public Object getInfoMedicamento(String atributo, String nome){
		Medicamento medicamento = farmacia.verificaMedicamentoExistente(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO + MensagensDeErro.ERRO_MEDICAMENTO_NAO_CADASTRADO, nome);
		switch (ValidadorDeDados.capitalizaString(atributo)) {
		case Constantes.TIPO:
			return medicamento.getTipo();
		case Constantes.NOME:
			return medicamento.getNome();
		case Constantes.CATEGORIAS:
			return medicamento.getCategorias();
		case Constantes.PRECO:
			return medicamento.calculaPreco();
		case Constantes.QUANTIDADE:
			return medicamento.getQuantidade();
		default:
			throw new DadoInvalidoException();
		}
	}
	
	public void atualizaMedicamento(String nome, String atributo, String novoValor){
		Medicamento medicamento = farmacia.verificaMedicamentoExistente(MensagensDeErro.ERRO_ATUALIZAR_MEDICAMENTO_INVALIDO, nome);
		atributo = ValidadorDeDados.capitalizaString(atributo); 
		switch (atributo) {
		case Constantes.PRECO:
			Double novoPreco = Double.parseDouble(novoValor);
			medicamento.setPreco(novoPreco);
			break;
		case Constantes.QUANTIDADE:
			Integer novaQtd = Integer.parseInt(novoValor);
			medicamento.setQuantidade(novaQtd);
			break;
		default:
			throw new DadoInvalidoException(String.format(MensagensDeErro.ERRO_ATUALIZAR_ATRIBUTO_MEDICAMENTO, atributo));
		}
	}
	
	public String consultaMedCategoria(String categoria){
		return farmacia.consultaMedicamentoPorCategoria(categoria);
	}
	
	public String consultaMedNome(String nome){
		return farmacia.verificaMedicamentoExistente(MensagensDeErro.ERRO_CONSULTA_MEDICAMENTO_INEXISTENTE, nome).toString();
	}
	
	public String getEstoqueFarmacia(String ordenacao){
		if(ordenacao.equalsIgnoreCase("preco")){
			return farmacia.verificaMedicamentosOrdemPreco();
		}else if(ordenacao.equalsIgnoreCase("alfabetica")){
			return farmacia.verificaMedicamentosOrdemAlfabetica();
		}else{
			throw new DadoInvalidoException(MensagensDeErro.ERRO_ORDENCAO_MEDICAMENTO);
		}
	}
}
