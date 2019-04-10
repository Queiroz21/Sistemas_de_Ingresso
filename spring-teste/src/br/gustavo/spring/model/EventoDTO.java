package br.gustavo.spring.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.gustavo.spring.service.ServiceImpl;

public class EventoDTO {
	
	//String nome2, Calendar data2, int capacidade2, BigDecimal iu2, CasaShow cs2
	private String nome, data, ingressoUnitario; 
	private int capacidade, casaShow, id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getIngressoUnitario() {
		return ingressoUnitario;
	}
	public void setIngressoUnitario(String ingressoUnitario) {
		this.ingressoUnitario = ingressoUnitario;
	}
	public int getCasaShow() {
		return casaShow;
	}
	public void setCasaShow(int casaShow) {
		this.casaShow = casaShow;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public Evento transformaEnvia(ServiceImpl s) {
		//Adicionar mais 1 na hr de exibir
		Evento e = new Evento();
		e.setId(this.id);
		e.setNome(this.nome);
		e.setCapacidade(this.capacidade);
		e.setIngressoUnitario(new BigDecimal
				(this.ingressoUnitario.replace("R$", "").replace(",", ".").trim()));
		
		String d[] = this.data.split("/");
		Calendar gc = new GregorianCalendar(Integer.parseInt(d[2]),
				Integer.parseInt(d[1])-1,Integer.parseInt(d[0]));
		gc.set(Calendar.HOUR_OF_DAY, 03);
		
		e.setData(gc);
		e.setCasaShow(s.buscaPorId(CasaShow.class, this.casaShow));
		return e;
	}
	
}
