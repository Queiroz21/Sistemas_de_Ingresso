package br.gustavo.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.gustavo.spring.model.Cliente;
import br.gustavo.spring.service.ServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	private ServiceImpl service;
	
	//criar uma nova busca por email e senha
	
	@RequestMapping(value ="efetuaLogin", method = RequestMethod.POST)
    public String efetuaLogin(@ModelAttribute("cliente") Cliente cliente, HttpSession session) {
    	if(service.buscaVinculada(cliente) != null) {
            Cliente c = service.buscaVinculada(cliente);
    		session.setAttribute("usuarioLogado", c);
            return "redirect:/home";
    	}
    	return "redirect:loginForm";
    }
    
    @RequestMapping("loginForm")
    public String loginForm(Model model) {
    	model.addAttribute("cliente", new Cliente());
        return "login";
    }
    
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginForm";
    }
}
