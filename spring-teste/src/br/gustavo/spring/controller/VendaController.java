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

import br.gustavo.spring.model.Cliente;
import br.gustavo.spring.model.Evento;
import br.gustavo.spring.model.Venda;
import br.gustavo.spring.model.VendaDTO;
import br.gustavo.spring.model.EventoDTO;
import br.gustavo.spring.service.ServiceImpl;

@Controller
@RequestMapping("venda")
public class VendaController {
	
	@Autowired
	private ServiceImpl service;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("venda", new Venda());
		//mudar p vendas por cliente, exibir só as compras daqueles clientes
		model.addAttribute("vendas", service.busca(Venda.class));
		
		return "venda";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processForm(@ModelAttribute("venda") VendaDTO vendaDTO, Model model, Evento evento, Cliente cliente) {
	//Ajuste de erro na questã ode cadastro inválido
		Venda venda = vendaDTO.transformaEnvia(service);
		if(venda.getId() == 0) {
			service.adiciona(venda);
		}
		else {
			service.atualiza(venda);
		}
		return "redirect:/home";
	}
	
	@GetMapping(value="/cad/{id_e}")
	public String processEdit(@PathVariable("id_e") int id_ev ,Model model ) {
		Venda v = new Venda();
		//Criar isso pra na hora de exibir na tela, ele carregar o service aqui
		Evento e = service.buscaPorId(Evento.class, id_ev);
		v.setEvento(e);
		model.addAttribute("venda", v);
		model.addAttribute("evento", service.buscaPorId(Evento.class, id_ev));
	
		return "venda";
	}
	
	@GetMapping(value="/remove/{id}")
	public String processDelete(@PathVariable("id") int id) {
		
		Venda v = service.buscaPorId(Venda.class, id);
		
		service.remove(v);
		
		return "redirect:/venda/";
	}

}

