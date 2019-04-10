package br.gustavo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.gustavo.spring.model.CasaShow;
import br.gustavo.spring.service.ServiceImpl;

@Controller
@RequestMapping("casaShow")
public class CasaShowController {
	
	@Autowired
	private ServiceImpl service;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String showForm(Model model) {
		
		model.addAttribute("casaShow", new CasaShow());
		model.addAttribute("casa", service.busca(CasaShow.class));
		
		return "casaShow";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processForm(@ModelAttribute("casaShow") CasaShow casa, Model model) {
		
		if(casa.getId() == 0)
			service.adiciona(casa);
		else
			service.atualiza(casa);
		
		
		return "redirect:/casaShow/";
	}
	
	@GetMapping(value="/edit/{id}")
	public String processEdit(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("casaShow", service.buscaPorId(CasaShow.class, id));
		model.addAttribute("casa", service.busca(CasaShow.class));
		
		return "casaShow";
	}
	
	@GetMapping(value="/remove/{id}")
	public String processDelete(@PathVariable("id") int id) {
		
		CasaShow cs = service.buscaPorId(CasaShow.class, id);
		
		service.remove(cs);
		
		return "redirect:/casaShow/";
	}

}
