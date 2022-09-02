package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {
	@Autowired
	@Qualifier("clienteDao")
	private IClienteDao clienteDao;
	
	@RequestMapping(value ="listar", method=RequestMethod.GET )
	public String listar(Model model) {
		model.addAttribute("clientes",clienteDao.findAll());
		model.addAttribute("titulo", "Listado de clientes");
		return "listar";
	}
	
	@RequestMapping(value ="/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Ingresar cliente");
		return "form";
	}
	
	
	@RequestMapping(value="/formm", method = RequestMethod.POST)
	public String guardar(Cliente cliente) {
		clienteDao.save(cliente);
		return "redirect:listar";
	}
}
