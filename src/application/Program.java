package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import model.services.ServicoAluguel;
import model.services.TaxaDeServicoBR;

public class Program {

	public static void main(String[] args)throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc= new Scanner(System.in);
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyy HH:ss");
		System.out.println("Enter rental data");
		System.out.println("Modelo do carro: ");
		String modeloCarro=sc.nextLine();
		System.out.println("Inicio (dd/MM/yyyy hh:mm):");
		Date inicio=sdf.parse(sc.nextLine());
		System.out.println("Fim (dd/MM/yyyy hh:mm):");
		Date fim =sdf.parse(sc.nextLine());
		AluguelCarro al= new AluguelCarro(inicio, fim, new Veiculo(modeloCarro));
		
		System.out.println("Entre com preço da hora:");
		double precoPorHora=sc.nextDouble();
		System.out.println("Entre com preço do dia:");
		double precoPorDia=sc.nextDouble();
		
		ServicoAluguel servicoAluguel = new ServicoAluguel(precoPorDia, precoPorHora, new TaxaDeServicoBR());
		servicoAluguel.ProcessoDaFatura(al);
		
		System.out.println("Invoice: ");
		System.out.println("Pagamento basico: " + String.format("%.2f", al.getFatura().getPagamentoBasico()));
		System.out.println("Taxa: " + String.format("%.2f", al.getFatura().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", al.getFatura().getPagamentoTotal()));
	}

}
