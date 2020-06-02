package estacionamento;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import estacionamento.entidade.Carro;
import estacionamento.estadia.Estadia;

public class Estacionamento {
	public static void main(String[] args) {
		
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println("                                BEM VINDO AO SISTEMA DE ESTACIONAENTO                                       ");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println(" ");

		programa();
		
	}

	private static void programa() {
		Scanner scanner = new Scanner(System.in);
		
		int opcao=0;
		// Map é uma interface ( que na verdade é um mapa que fornece os dados chaveados)
		// HashMap é uma implementação
		// O map trabalha com chaves no caso a nossa chave é a placa do carro
		// e o valor estadia que é composto de Entrada Sainda e Carro.
		// HashMap<String, Estadia>
		// String é a chave do tipo String no caso a (placa)
		// Estadia é o objeto no caso é a Estadia (VALOR )
		Map < String, Estadia > estadias = new HashMap<String, Estadia>();
		
		
		do {
		
			System.out.println("Escolha uma opcao: [1] Entrada | [2] Saida [3] Permanencia [4] Qtd de carros [5] Remover [6] Listar [0] Sair");
			try {
				opcao = scanner.nextInt();
			}	catch (Exception e) {
				erro();
			}
			
			if(opcao==1) {
				Estadia estadia = new Estadia();
				Carro carro = new Carro();
				estadia.setEntrada(LocalTime.now());
				System.out.println("Digite uma Placa");
				carro.setPlaca(scanner.next());
				System.out.println(carro.getPlaca());
				
				System.out.println("Digite um Modelo");
				carro.setModelo(scanner.next());
				System.out.println(carro.getModelo());
				
				System.out.println(estadia.getEntrada());
				estadia.setCarro(carro);
				estadias.put(carro.getPlaca(), estadia); // o carro.getPlaca é a chave ||  estadia é o valor
				
			}else if(opcao==2) {
				
				System.out.println("Digite uma Placa");
				// Declara a placaDaSaida como String
				String placaDaSaida = scanner.next(); // scanner.next() => armazena a placa digitada next retorna uma string
				
				if( estadias.get(placaDaSaida) != null) { 
				
					Estadia estadiaDaSaida = estadias.get(placaDaSaida); //Procura a placa DA SAIDA digitada no Map (na lista) depois que achar ele retorna a estadia (estadiaDaSaida(saida))
					estadiaDaSaida.setSaida(LocalTime.now());
					// put recebe a placa e a estadia
					estadias.put(placaDaSaida, estadiaDaSaida); // devolvendo para dentro do Map com a hora de saida
					System.out.println(estadiaDaSaida.getSaida());	
				} else {
					System.out.println("Placa não encontrada");
				}
				
			}else if (opcao==3) {
				
				System.out.println("Digite uma Placa");
				String placaParaConsulta = scanner.next(); // scanner.next() => armazena a placa digitada que retorna uma string
				Estadia estadiaDeConsulta = estadias.get(placaParaConsulta); // Pesquisa no Map o registro para me devolver uma estadiaDeConsulta
				try {
					
					
					System.out.println("Permanencia " + calculaPermanencia(estadiaDeConsulta));
				} catch (Exception e) {
					System.out.println("Não existe registro de entrada/saída para a placa informada");
					programa();
				}
				
			}else if (opcao==4) {
				
				System.out.println("Você tem " + estadias.size() + " carro(s) estacionados");
				
			}else if(opcao==5) {
				
				System.out.println("Digite uma Placa");
				String placa = scanner.next();
				estadias.remove(placa);
				
			}else if(opcao==6) {
				// Map.Entry<String, Estadia> => Percorre no mapa as suas entradas que sao do tipo String na chave e estadia de
				// valor E nomeei como entradas (entry) e elas sao de onde? Sao de uma lista (Set) conjunto de
				// entradas( estadias.entrySet()
				for ( Map.Entry<String, Estadia> entry : estadias.entrySet() ) {
					StringBuilder mensagem = new StringBuilder();
					mensagem.append("O Carro Placa: [ " + entry.getKey());
					mensagem.append(" ] Modelo: [ " + entry.getValue().getCarro().getModelo());
					mensagem.append(" ] Estacionou: [ " + entry.getValue().getEntrada() + "] ");
					
					if(entry.getValue().getSaida()!=null) {
						mensagem.append("Até: [ " + entry.getValue().getSaida() + " ] ");
						mensagem.append(" A Permanência Foi De: [ " + calculaPermanencia(entry.getValue()) + " ] ");
					}
					
										
					System.out.println(mensagem);
				}
			} else if (opcao==7) {
				for ( Map.Entry<String, Estadia> entry : estadias.entrySet() ) {
					System.out.println(entry.getValue().getCarro().getPlaca() + " / " + " / " + entry.getValue().getCarro().getModelo() );
				}
			}
			
			
		}while(opcao!=0);
		scanner.close();
	}

	private static long calculaPermanencia(Estadia estadiaDeConsulta) {
		return estadiaDeConsulta.getEntrada().until(estadiaDeConsulta.getSaida(), SECONDS);
	}

	private static void erro() {
		System.out.println("Opcao invalida");
		programa();
		
	}
}
