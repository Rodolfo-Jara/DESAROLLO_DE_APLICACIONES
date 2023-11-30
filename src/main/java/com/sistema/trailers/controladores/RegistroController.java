package com.sistema.trailers.controladores;

import com.sistema.trailers.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RegistroController {

    private final UsuarioService servicio;

    public RegistroController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/login")
    public String iniciarSesion(Model model) {
        return "auth/login";
    }

    @GetMapping("/login-error")
    public String mostrarErrorLogin(Model model) {
        model.addAttribute("error", true);
        return "auth/login";
    }
}
