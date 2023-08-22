package com.projetoteste.projetoteste2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoteste.projetoteste2023.entity.Usuario;
import com.projetoteste.projetoteste2023.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {
    
    @Autowired
    private UsuarioService servico;

    @GetMapping
    public List<Usuario> buscarTodos() {
        return servico.buscarTodos();
    }

    @PostMapping
    public Usuario novoUsuario(@RequestBody Usuario usuario) {
        return servico.novoUsuario(usuario);
    }

    @GetMapping(value = "/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }
}
