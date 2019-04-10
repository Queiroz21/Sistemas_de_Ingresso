package br.gustavo.spring.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gustavo.spring.dao.ClienteDao;
import br.gustavo.spring.dao.Dao;
import br.gustavo.spring.dao.EventoDao;
import br.gustavo.spring.model.Cliente;
import br.gustavo.spring.model.Evento;


@Service
public class ServiceImpl {

	@Autowired
	private Dao dao;
	
	@Autowired
	private EventoDao daoE;
	

	public <T> List<T> busca(Class<T> type) {

		try {
			
			return this.dao.busca(type);

		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			
			return null;

		}

	}
	
	public <T> List<T> buscaAtual(Class<T> type) {

		try {
			
			return this.dao.buscaAtual(type);

		} catch (Exception e) {
			
			System.err.println(e.getMessage());
			
			return null;

		}

	}

	public <T> T buscaPorId(Class<T> type, int id) {

		try {
			
			return this.dao.buscaPorId(type, id);

		} catch (HibernateException e) {

			System.err.println(e.getMessage());
			return null;

		} 

	}

	public <T> void adiciona(T t) {
		
		try {

			this.dao.adiciona(t);


		} catch (Exception e) {
			
			System.err.println(e.getMessage());

		} 
		
	}

	public <T> void atualiza(T t) {
		
		try {

			this.dao.atualiza(t);


		} catch (Exception e) {

			System.err.println(e.getMessage());

		} 
		
	}

	public <T> void remove(T t) {
		
		try {

			this.dao.remove(t);


		} catch (Exception e) {

			System.err.println(e.getMessage());

		} 
		
	}

	public Cliente buscaVinculada(Cliente cliente) {
		try {

			return this.dao.buscaVinculada(cliente);

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return null;

		}
	}

	public boolean verificaData(Evento ev) {
		try {

			return this.dao.verificaData(ev);

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return false;

		}
	}
	
	public int busca_Ingressos_Evento(Evento ev) {
		try {

			return this.daoE.busca_qtd_ingresso(ev);

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return -1;

		}
	}

}
