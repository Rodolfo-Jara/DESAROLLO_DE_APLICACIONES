package com.sistema.trailers.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.trailers.modelo.Carrito;
import com.sistema.trailers.modelo.Pelicula;
import com.sistema.trailers.modelo.Productos;
import com.sistema.trailers.repositorios.CarritoRepository;
import com.sistema.trailers.repositorios.PeliculaRepositorio;
import com.sistema.trailers.repositorios.ProductoRepository;
import com.sistema.trailers.servicio.ProductoService;

@Controller
@RequestMapping("")
public class HomeControlador {
	@Autowired
	private CarritoRepository carritoRepository;
	@Autowired
    private ProductoRepository productRepository;
	@Autowired
	private PeliculaRepositorio peliculaRepositorio;


	@GetMapping("/index")
	public ModelAndView verPaginaDeInicio(Model model) {
	    List<Pelicula> ultimasPeliculas = peliculaRepositorio.findAll(PageRequest.of(0, 4, Sort.by("fechaEstreno").descending())).toList();
	    
	    List<Productos> productos = productRepository.findAll();

	    String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

	    model.addAttribute("nombreUsuario", nombreUsuario);
	    model.addAttribute("ultimasPeliculas", ultimasPeliculas);
	    model.addAttribute("productos", productos);

	    return new ModelAndView("index");
	}
	
	@GetMapping("/productos/{id}")
	public ModelAndView mostrarDetallesDeProducto(@PathVariable int id) {
	    Productos producto = productRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));

	    return new ModelAndView("product-details")
	            .addObject("producto", producto);
	}
	
	
	@PostMapping("/carrito/agregar-al-carrito")
	public String agregarAlCarrito(@ModelAttribute Carrito nuevoProducto) {
	    try {
	        carritoRepository.save(nuevoProducto);
	     
	return "carrito";
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        e.printStackTrace();
	        // You can also log to a logger if you have one configured
	        // logger.error("Error saving carrito: " + e.getMessage());
	        return "error"; // Redirect to an error page or handle it appropriately
	    }
	}
	
	
	@GetMapping("/peliculas")
	public ModelAndView listarPeliculas(@PageableDefault(sort = "fechaEstreno",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Pelicula> peliculas = peliculaRepositorio.findAll(pageable);
		return new ModelAndView("peliculas")
				        .addObject("peliculas",peliculas);
	}
	
	@GetMapping("/peliculas/{id}")
	public ModelAndView mostrarDetallesDePelicula(@PathVariable Integer id) {
		Pelicula pelicula = peliculaRepositorio.getById(id);
				return new ModelAndView("pelicula").addObject("pelicula",pelicula);
	}
}
