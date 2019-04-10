package br.gustavo.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gustavo.spring.model.CasaShow;
import br.gustavo.spring.model.Evento;

@Repository
@Transactional
public class EventoDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	//Compara aqui onde o code de 1 for igual o de outra E o code do obj que vem, for igual ao code do que veio do banco
	
		
	private Session getCurrentSession() {
			
		return this.sessionFactory.getCurrentSession();
			
	}


	public <T> List<T> buscaSincro(Class<T> type) {
		//Dá ERRO: de org.apache.jasper.JasperException: java.lang.NumberFormatException: Para string de entrada: "id"
		//em org.apache.jasper.servlet.JspServletWrapper.service (JspServletWrapper.java:473)
		//quando esta query abaixo roda
		//return getCurrentSession().createQuery("SELECT a, b FROM Evento a LEFT JOIN a.casaShow b").list();
		//VOLTA PRO NORMAL, E VE COMO FAZER UM WHERE NA CRITERIA
		return getCurrentSession().createQuery("Select e from Evento ev inner join ev.casashow_id = :casaShow.id").list();
	}
	
	public <T> int busca_qtd_ingresso(Evento ev) {
		String qtd_ingresso =""+ getCurrentSession().createQuery("Select capacidade from Evento");
		return Integer.parseInt(qtd_ingresso);
	}
}
