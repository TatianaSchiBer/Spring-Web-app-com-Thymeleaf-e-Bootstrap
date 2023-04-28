package com.bernardi.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bernardi.mudi.entities.Pedido;
import com.bernardi.mudi.entities.StatusPedido;
import com.bernardi.mudi.repos.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	public String home(Model m) {
		List<Pedido> pedidos = pedidoRepository.findAll();
		m.addAttribute("pedidos", pedidos);
		return "home";
	}

	
	@GetMapping("/{status}")
	public String porStatus(@PathVariable("status") String status, Model m) {
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		m.addAttribute("pedidos", pedidos);
		m.addAttribute("status", status);
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
