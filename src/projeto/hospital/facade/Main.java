package projeto.hospital.facade;

import projeto.util.Constantes;
import projeto.util.Util;
import easyaccept.EasyAccept;

public class Main {
	public static void main(String[] args) {
		Util.limpaDados(Constantes.ARQUIVO_DADOS);
		args = new String[]{"projeto.hospital.facade.Facade", "acceptTests/usecase_1.txt", "acceptTests/usecase_2.txt", "acceptTests/usecase_3.txt", "acceptTests/usecase_4.txt"};
		
		EasyAccept.main(args);	
	}
}