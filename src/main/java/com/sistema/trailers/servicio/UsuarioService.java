package com.sistema.trailers.servicio;

import com.sistema.trailers.modelo.Usuario;
import com.sistema.trailers.modelo.UsuarioRegistro;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioService extends UserDetailsService {


    public Usuario guardar(UsuarioRegistro registroDTO);

    public List<Usuario> listarUsuarios();

}
