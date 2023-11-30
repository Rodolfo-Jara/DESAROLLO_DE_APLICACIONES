package com.sistema.trailers.servicio;

import com.sistema.trailers.modelo.Rol;
import com.sistema.trailers.modelo.Usuario;
import com.sistema.trailers.modelo.UsuarioRegistro;
import com.sistema.trailers.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario guardar(UsuarioRegistro registroDTO) {
        Usuario usuario = new Usuario(
            registroDTO.getNombre(),
            registroDTO.getApellido(),
            registroDTO.getEmail(),
            passwordEncoder.encode(registroDTO.getPassword()),
            Arrays.asList(new Rol("USER")) // Agregar el rol ADMIN aquí
        );
        return usuarioRepositorio.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usuario usuario = usuarioRepositorio.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        
        // Imprimir o registrar los roles del usuario
        System.out.println("Roles del usuario: " + usuario.getRoles());
        return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }
}
