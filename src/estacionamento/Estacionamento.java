package estacionamento;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import estacionamento.entidade.Carro;
import estacionamento.estadia.Estadia;

import static java.time.temporal.ChronoUnit.MINUTES;

public class Estacionamento {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Carro carro = new Carro();
		Estadia estadia = new Estadia();
		int opcao = 0;

		System.out.println("Bem vindo ao sistema de estacionamento");
		
		do {
		System.out.println("Escolha uma opção: [1] Entrada | [2] Saída | [3] Permanência | [0] Sair");
		opcao = scanner.nextInt();
			
			if(opcao==1) {
				
				estadia.setEntrada(LocalTime.now());
				
				System.out.println("Digite uma placa");
				carro.setPlaca(scanner.next());
				System.out.println(carro.getPlaca());
				
				System.out.println("Digite um modelo");
				carro.setModelo(scanner.next());
				System.out.println(carro.getModelo());
				
				System.out.println(estadia.getEntrada());
				
			}else if(opcao==2) {
				
				estadia.setSaida(LocalTime.now());
				System.out.println(estadia.getSaida());
			}else if(opcao==3) {
				System.out.println("Permanência usando LocalTime Until " + estadia.getEntrada().until(estadia.getSaida(), MINUTES));
				System.out.println("Permanência usando ChronoUnit Duration " + ChronoUnit.MINUTES.between(estadia.getEntrada(),estadia.getSaida()));
			}
			
		}while(opcao!=0);
		
		scanner.close();
	}

}
