package com.sistema.trailers.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.trailers.modelo.Genero;
import com.sistema.trailers.modelo.Pelicula;
import com.sistema.trailers.repositorios.GeneroRepositorio;
import com.sistema.trailers.repositorios.PeliculaRepositorio;
import com.sistema.trailers.servicio.AlmacenServicioImpl;
@Controller
@RequestMapping("/admin")
public class AdminControlador {

	@Autowired
	private PeliculaRepositorio peliculaRepositorio;

	@Autowired
	private GeneroRepositorio generoRepositorio;

	@Autowired
	private AlmacenServicioImpl servicio;

	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5) Pageable pageable) {
		return new ModelAndView("admin/index")
				.addObject("peliculas", peliculaRepositorio.findAll(pageable));
	}
	
	//METODO PARA CREAR  LAPELICULA
		@GetMapping("/create")
		public String create(Model model) {
		    List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
		    
		    // Add an instance of Pelicula to the model
		    model.addAttribute("pelicula", new Pelicula());
		    model.addAttribute("generos", generos);
		return "admin/nueva-pelicula";
		}
		@PostMapping("/save")
		public String saveProducto(@ModelAttribute("producto") Pelicula pelicula) {
			String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());
			pelicula.setRutaPortada(rutaPortada);
			peliculaRepositorio.save(pelicula);
		    return "redirect:/admin";
		}
		//metodo para editar PELICULA
		@GetMapping("/edit/{id}")
	    public String edit(@PathVariable("id") Integer id, Model model) {
	        Pelicula pelicula = peliculaRepositorio.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada con ID: " + id));

	        List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

	        model.addAttribute("pelicula", pelicula);
	        model.addAttribute("generos", generos);
	        return "admin/editar-pelicula";
	    }
		@PostMapping("/update/{id}")
		public String update(@PathVariable("id") Integer id, @ModelAttribute("pelicula") Pelicula pelicula) {
		    Pelicula peliculaExistente = peliculaRepositorio.findById(id)
		            .orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada con ID: " + id));

		    peliculaExistente.setTitulo(pelicula.getTitulo());
		    peliculaExistente.setSinopsis(pelicula.getSinopsis());
		    peliculaExistente.setFechaEstreno(pelicula.getFechaEstreno());
		    peliculaExistente.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());

		    // Update genres
		    peliculaExistente.setGeneros(pelicula.getGeneros());

		    // Also, handle the update of the portada here if necessary
		    // ...

		    peliculaRepositorio.save(peliculaExistente);
		    return "redirect:/admin";
		}
		@GetMapping("/delete/{id}")
		public String deleteProducto(@PathVariable("id") Integer id) {
			peliculaRepositorio.deleteById(id);
		    return "redirect:/admin";
		}
	

	
}
