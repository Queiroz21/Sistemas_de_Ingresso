package br.gustavo.spring.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Evento {
	
	public Evento() {}
	
	public Evento(String nome2, Calendar data2, int capacidade2, BigDecimal iu2, CasaShow cs2) {
		this.nome = nome2;
		this.data = data2;
		this.capacidade = capacidade2;
		this.ingressoUnitario = iu2;
		this.casaShow = cs2;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nome;
	
	@Column
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	@Column
	private int capacidade;
	
	@Column
	@NumberFormat(pattern = "R$ #,###,###,##0.00")
	private BigDecimal ingressoUnitario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "casashow_id", nullable=false)
	private CasaShow casaShow;
		
	public CasaShow getCasaShow() {
		return casaShow;
	}

	public void setCasaShow(CasaShow casaShow) {
		this.casaShow = casaShow;
	}
	
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public BigDecimal getIngressoUnitario() {
		return ingressoUnitario;
	}

	public void setIngressoUnitario(BigDecimal ingressoUnitario) {
		this.ingressoUnitario = ingressoUnitario;
	}

	public void atualizaCapacidade(int qtdIngressosVendidos) {
		this.capacidade -= qtdIngressosVendidos;
		
	}
}
