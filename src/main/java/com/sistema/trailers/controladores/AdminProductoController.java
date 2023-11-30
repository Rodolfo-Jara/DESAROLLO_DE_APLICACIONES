package com.sistema.trailers.controladores;

import com.sistema.trailers.modelo.Productos;
import com.sistema.trailers.repositorios.ProductoRepository;
import com.sistema.trailers.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    @Autowired
    private ProductoRepository productRepository;


    private final ProductoService productoService;

    @Autowired
    public AdminProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("")
    public String listarProductos(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(name = "descripcion", required = false) String descripcion,
            Model model
    ) {
        Page<Productos> productos;
        if (descripcion != null && !descripcion.isEmpty()) {
            productos = productoService.buscarPorDescripcion(descripcion, pageable);
        } else {
            productos = productoService.listarProductosPaginados(pageable);
        }

        model.addAttribute("productos", productos);
        return "admin/producto/listar";
    }
    @GetMapping("/nuevo")
    public String guardarproducto(){
        return "admin/producto/nuevoProducto" ;
    }

    @PostMapping("/nuevo")
    public String guardarproducto(@ModelAttribute Productos nuevoProducto) {
        productRepository.save(nuevoProducto);
        return "redirect:/admin/productos";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Productos producto = productoService.obtenerProducto(id);
        model.addAttribute("producto", producto);
        return "admin/producto/actualizarProducto";
    }

    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable int id, @ModelAttribute Productos producto) {
        productoService.actualizarProducto(id, producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/productos";
    }

    
}
