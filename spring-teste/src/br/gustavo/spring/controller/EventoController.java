package br.gustavo.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.gustavo.spring.model.CasaShow;
import br.gustavo.spring.model.Evento;
import br.gustavo.spring.model.EventoDTO;
import br.gustavo.spring.service.ServiceImpl;

@Controller
@RequestMapping("evento")
public class EventoController {
	
	@Autowired
	private ServiceImpl service;
	
	public EventoController() {}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String showForm(Model model, EventoDTO evdt) {
		model.addAttribute("evento", new Evento());
		model.addAttribute("eventos", service.busca(Evento.class));
		model.addAttribute("casas",service.busca(CasaShow.class));
		
		return "evento";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST )
	public String processForm(@ModelAttribute("evento")  EventoDTO eventoDto, Model model, CasaShow casa, HttpSession session) throws java.text.ParseException {
		
		//TG STYLE, COLOCAR DISPLAY
		Evento evento = eventoDto.transformaEnvia(service);
		if(service.verificaData(evento)) {
			session.setAttribute("erroData", true);
			if(evento.getId() == 0)
				service.adiciona(evento);
			else
				service.atualiza(evento);
		
		}
		//AJUSTAR EXIBIÇÃO NA PÁGINA DEPOIS
		//ADICIONAR EXTRAS COM A FOTO DO XUXA
		session.setAttribute("erroData", false);
		return "redirect:/evento";
	}
	
	@GetMapping(value="/edit/{id}")
	public String processEdit(@PathVariable("id") int id, Model model) {
		//Realizar busca e depois subir os valores
		model.addAttribute("evento", service.buscaPorId(Evento.class, id));
		model.addAttribute("eventos", service.busca(Evento.class));
		model.addAttribute("casas",service.busca(CasaShow.class));
		
		return "evento";
	}
	
	@GetMapping(value="/remove/{id}")
	public String processDelete(@PathVariable("id") int id) {
		
		Evento e = service.buscaPorId(Evento.class, id);
		
		service.remove(e);
		
		return "redirect:/evento";
	}
	
	
}
