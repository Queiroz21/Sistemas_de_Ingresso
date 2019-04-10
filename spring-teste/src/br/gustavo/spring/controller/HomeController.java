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

import br.gustavo.spring.model.Evento;
import br.gustavo.spring.model.Venda;
import br.gustavo.spring.service.ServiceImpl;

@Controller
@RequestMapping("home")
public class HomeController {

	@Autowired
	private ServiceImpl service;
	
	public HomeController() {}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("eventos",service.buscaAtual(Evento.class));
		
		return "home";
	}
	
	
	@GetMapping(value="form/{id_evento}")
	public String processForm(@ModelAttribute("home")  Venda home, Model model,  @PathVariable("id_evento")  int id_evento, HttpSession session) throws java.text.ParseException {
		Evento e = service.buscaPorId(Evento.class, id_evento);
		session.setAttribute("ingressoEscolhido", e);
		
		return "redirect:/venda/cad/{id_evento}";
	}
}
