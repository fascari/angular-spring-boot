package io.github.fascari.clientes.service;

import io.github.fascari.clientes.exception.UsuarioCadastradoException;
import io.github.fascari.clientes.model.entity.Usuario;
import io.github.fascari.clientes.model.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public void salvar(Usuario usuario) {
        boolean hasUsuarioCadastrado = repository.existsByUsername(usuario.getUsername());
        if (hasUsuarioCadastrado) {
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));

        return User.builder().username(usuario.getUsername()).password(usuario.getPassword()).roles("USER").build();
    }
}
