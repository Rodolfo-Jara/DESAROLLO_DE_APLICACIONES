package com.sistema.trailers.controladores;

import com.sistema.trailers.modelo.Usuario;
import com.sistema.trailers.modelo.UsuarioRegistro;
import com.sistema.trailers.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class UserController {

    private UsuarioService usuarioServicio;

    public UserController(UsuarioService usuarioServicio) {
        super();
        this.usuarioServicio = usuarioServicio;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistro retornarNuevoUsuarioRegistro() {
        return new UsuarioRegistro();
    }

    @GetMapping("/registro")
    public String mostrarPaginaRegistro() {
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute UsuarioRegistro registro, Model model) {
        // Crea un nuevo usuario a partir de los datos del formulario
        Usuario usuario = usuarioServicio.guardar(registro);

        // Agrega un mensaje de registro exitoso al modelo
        model.addAttribute("exito", true);

        // Redirecciona al index después del registro exitoso
        return "redirect:/login";
    }
}

