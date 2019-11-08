package model.services;

import model.entities.AluguelCarro;
import model.entities.Fatura;


public class ServicoAluguel {
	
	private Double precoPorDia;
	private Double precoPorHora;
	
	private TaxaDeServico taxaDeServico;

	public ServicoAluguel(Double precoPorDia, Double precoPorHora, TaxaDeServico taxaDeServico) {
		this.precoPorDia = precoPorDia;
		this.precoPorHora = precoPorHora;
		this.taxaDeServico = taxaDeServico;
	}
	
	public void ProcessoDaFatura(AluguelCarro aluguelCarro) {
		//gera a nota de pagamento 
		//data em milissegundos
		long t1= aluguelCarro.getInicio().getTime();
		long t2=aluguelCarro.getFim().getTime();
		double hours =(double) (t2-t1)/1000/60/60;
		
		double pagamentoBasico;
		if(hours<12) {			//arredondar
			 pagamentoBasico =Math.ceil(hours)*precoPorHora;
		}else {
			pagamentoBasico= Math.ceil(hours/24)*precoPorDia;
		}
		
		double tax =taxaDeServico.tax(pagamentoBasico);
		
		aluguelCarro.setFatura(new Fatura(pagamentoBasico,tax));
	}
	
	
	
}
