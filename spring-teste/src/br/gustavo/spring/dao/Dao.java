package br.gustavo.spring.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gustavo.spring.model.Cliente;
import br.gustavo.spring.model.Evento;

@Repository
@Transactional
public class Dao {
	
	@Autowired
	private SessionFactory sessionFactory;


	public <T> List<T> busca(Class<T> type) {
		return getCurrentSession().createCriteria(type).addOrder(Order.asc("id")).list();
	}
	//Vai rolar aqui q ele vai me mandar só o nome, fazer uma busca diferente do que pede e substituir

	public <T> List<T> buscaAtual(Class<T> type) {
		return getCurrentSession().createQuery("from Evento tipo where tipo.data > Now() order by tipo.id asc").list();
		
		//return getCurrentSession().createCriteria(type).addOrder(Order.asc("id")).add(Restrictions.ne("data", Calendar.getInstance())).list();
	}
	
	public <T> T buscaPorId(Class<T> type, int id) {
		//get retorna valores de uma session de um determinado tipo, indicado por um "apontador" (um where da vida vida +/-)
		return getCurrentSession().get(type, id);
	}
	
	public <T> void adiciona(T t) {
		
		getCurrentSession().persist(t);
		
	}
	
	public <T> void atualiza(T t) {
		
		getCurrentSession().update(t);
		
	}
	
	public <T> void remove(T t) {
		
		getCurrentSession().remove(t);
		
	}
	
	private Session getCurrentSession() {
		
		return this.sessionFactory.getCurrentSession();
		
	}

	public Cliente buscaVinculada(Cliente cli) {
		//return getCurrentSession().createQuery("from Cliente tipo where tipo.email = :email and tipo.senha = :senha").list();
		Cliente c =  (Cliente) getCurrentSession().createCriteria(Cliente.class).
				add(Restrictions.eq("email", cli.getEmail())).
				add(Restrictions.eq("senha", cli.getSenha())).uniqueResult();
		
		return c;
	}

	public boolean verificaData(Evento ev) {
		List<Evento> eventos = getCurrentSession().createCriteria(Evento.class).addOrder(Order.asc("id")).list();
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("teste chegada " + sdt.format(ev.getData().getTime()));
		
		for(Evento e: eventos) {
			
			if(sdt.format(e.getData().getTime()).equalsIgnoreCase(sdt.format(ev.getData().getTime()))) {
				System.out.println("Barrado");
				return false;
			}
		}
		System.out.println("Passou");
		return true;
	}

}
