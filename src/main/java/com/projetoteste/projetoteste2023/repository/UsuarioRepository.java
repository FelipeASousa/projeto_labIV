package com.projetoteste.projetoteste2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoteste.projetoteste2023.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
