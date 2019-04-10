package br.gustavo.spring.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.gustavo.spring.service.ServiceImpl;

public class VendaDTO {
	
	private int id;
	
	private String data;
	
	private int quantidadeIngressos;
	
	private int evento;
	
	private int cliente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getQuantidadeIngressos() {
		return quantidadeIngressos;
	}

	public void setQuantidadeIngressos(int quantidadeIngressos) {
		this.quantidadeIngressos = quantidadeIngressos;
	}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public Venda transformaEnvia(ServiceImpl service) {
		Venda v = new Venda();
		Cliente c = service.buscaPorId(Cliente.class, this.cliente);
		Evento e = service.buscaPorId(Evento.class, this.evento);
		v.setId(this.id);
		v.setQuantidadeIngressos(this.quantidadeIngressos);
		
		//achar uma forma de atualizar os ingressos
		e.atualizaCapacidade(v.getQuantidadeIngressos());
		v.setCliente(c);
		v.setEvento(e);
		String totalS = e.getIngressoUnitario()+"".replace("R$", "").replace(",", ".");
		float i = Float.parseFloat(totalS)*this.quantidadeIngressos;
		
		v.setTotal(new BigDecimal(i));
//		Aqui salva apenas
//		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
//		setData(sdt.format(Calendar.getInstance().getTime()));
//		String d[] = this.data.split("/");
//		Calendar gc = new GregorianCalendar(Integer.parseInt(d[2]),
//				Integer.parseInt(d[1])-1,Integer.parseInt(d[0])+1);
		
		//Salvar a hora tbm
		Calendar gc = new GregorianCalendar();
		gc.setTime(Calendar.getInstance().getTime());
		v.setData(gc);
		
		service.atualiza(e);
		return v;
		
	}

}
