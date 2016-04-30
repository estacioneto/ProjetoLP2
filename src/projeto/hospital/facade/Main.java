package projeto.hospital.facade;

import easyaccept.EasyAccept;

public class Main {
	public static void main(String[] args) {
		args = new String[]{"projeto.hospital.facade.Facade", "acceptTests/usecase_1.txt", "acceptTests/usecase_3.txt"};//, "acceptTests/usecase_2.txt", "acceptTests/usecase_3.txt", "acceptTests/usecase_4.txt"};
		
		 EasyAccept.main(args);		
	}
}
