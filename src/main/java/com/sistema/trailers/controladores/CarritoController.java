package com.sistema.trailers.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sistema.trailers.modelo.Carrito;
import com.sistema.trailers.modelo.Productos;
import com.sistema.trailers.repositorios.CarritoRepository;
import com.sistema.trailers.servicio.ProductoService;

@Controller
@RequestMapping("")
public class CarritoController {
		@Autowired
		private CarritoRepository carritoRepository;
	 private final ProductoService productoService = null;
	 
	 
	

	@PostMapping("/carrito/guardar-carrito")
	public String guardarCarrito(@ModelAttribute("carrito") Carrito carrito) {
	    carritoRepository.save(carrito);
	    return "redirect:/listado";
	}
	   
}
