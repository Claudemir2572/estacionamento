package estacionamento.estadia;

import java.time.LocalTime;

public class Estadia {
	
	private LocalTime entrada;
	private LocalTime saida;
	
	public LocalTime getEntrada() {
		return entrada;
	}
	public void setEntrada(LocalTime entrada) {
		this.entrada = entrada;
	}
	public LocalTime getSaida() {
		return saida;
	}
	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}	
}
