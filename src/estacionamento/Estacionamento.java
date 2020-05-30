package estacionamento;
import estacionamento.estadia.Estadia;
import estacionamento.model.Carro;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.SECONDS;

public class Estacionamento {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Carro carro = new Carro();
		int opcao=0;
		
		
		
		Map < String, Estadia > estadias = new HashMap<String, Estadia>();
		
		
		do {
		
			System.out.println("Escolha uma opcao: [1] Entrada | [2] Saida [3] Permanencia [4] Qtd de carros [5] Remover [6] Listar [0] Sair");
			opcao = scanner.nextInt();
			
			
			if(opcao==1) {
				
				Estadia estadia = new Estadia();
				
				estadia.setEntrada(LocalTime.now());
				
				System.out.println("Digite uma Placa");
				carro.setPlaca(scanner.next());
				
				System.out.println(carro.getPlaca());
				
				System.out.println("Digite um Modelo");
				carro.setModelo(scanner.next());
				System.out.println(carro.getModelo());
				
				System.out.println(estadia.getEntrada());
				
				estadia.setCarro(carro);
				
				estadias.put(carro.getPlaca(), estadia);
				
			}else if(opcao==2) {
				
				System.out.println("Digite uma Placa");
				String placaDaSaida = scanner.next();
				
				Estadia estadiaDaSaida = estadias.get(placaDaSaida);
				
				estadiaDaSaida.setSaida(LocalTime.now());
				
				estadias.put(placaDaSaida, estadiaDaSaida);
				
				System.out.println(estadiaDaSaida.getSaida());
				
				
			}else if (opcao==3) {
				System.out.println("Digite uma Placa");
				String placaParaConsulta = scanner.next();
				
				Estadia estadiaDeConsulta = estadias.get(placaParaConsulta);
				
				
				System.out.println("Permanencia " + estadiaDeConsulta.getEntrada().until(estadiaDeConsulta.getSaida(), SECONDS));
				
			}else if (opcao==4) {
				System.out.println("Você tem " + estadias.size() + " carro(s) estacionados");
			}else if(opcao==5) {
				System.out.println("Digite uma Placa");
				String placa = scanner.next();
				estadias.remove(placa);
			}else if(opcao==6) {
				for ( Map.Entry<String, Estadia> entry : estadias.entrySet() ) {
					System.out.println("O carro placa: " + entry.getKey() + " modelo: " + entry.getValue().getCarro().getModelo()
							+ "está estacionado desde " + entry.getValue().getEntrada());
				}
			}
				
			
		}while(opcao!=0);
		
		scanner.close();
	}
}
