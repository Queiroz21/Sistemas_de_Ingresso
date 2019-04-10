package br.gustavo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.gustavo.spring.model.Cliente;
import br.gustavo.spring.service.ServiceImpl;


@Controller
@RequestMapping("cliente")
public class ClienteController {
	
	@Autowired
	private ServiceImpl service;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String showForm(Model model) {
		
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("clientes", service.busca(Cliente.class));
		
		return "cliente";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processForm(@ModelAttribute("cliente") Cliente cliente, Model model) {
		
		if(cliente.getId() == 0)
			service.adiciona(cliente);
		else
			service.atualiza(cliente);
		
		
		return "redirect:/cliente/";
	}
	
	@GetMapping(value="/edit/{id}")
	public String processEdit(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("cliente", service.buscaPorId(Cliente.class, id));
		model.addAttribute("clientes", service.busca(Cliente.class));
		
		return "cliente";
	}
	
	@GetMapping(value="/remove/{id}")
	public String processDelete(@PathVariable("id") int id) {
		
		Cliente cli = service.buscaPorId(Cliente.class, id);
		
		service.remove(cli);
		
		return "redirect:/cliente/";
	}

}
