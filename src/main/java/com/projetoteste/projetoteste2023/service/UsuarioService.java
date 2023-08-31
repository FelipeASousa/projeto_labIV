package com.projetoteste.projetoteste2023.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoteste.projetoteste2023.entity.Anotacao;
import com.projetoteste.projetoteste2023.entity.Autorizacao;
import com.projetoteste.projetoteste2023.entity.Usuario;
import com.projetoteste.projetoteste2023.repository.AnotacaoRepository;
import com.projetoteste.projetoteste2023.repository.AutorizacaoRepository;
import com.projetoteste.projetoteste2023.repository.UsuarioRepository;

@Service
public class UsuarioService {

    // Repositorios
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private AutorizacaoRepository autorizacaorep;
    @Autowired
    private AnotacaoRepository anotacaorep;

    // Funções
    public Autorizacao buscarPorAutorizacaoId(Long id) {
        Optional<Autorizacao> autorizacaoOp = autorizacaorep.findById(id);
        if(autorizacaoOp.isPresent()) {
            return autorizacaoOp.get();
        }
        throw new IllegalArgumentException("Id inválido!");
    }
    @Transactional
    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if(usuarioOp.isPresent()) {
             return usuarioOp.get();
        }
        throw new IllegalArgumentException("Id inválido!");
    }

    public Usuario novoUsuario(Usuario usuario) {
        if(usuario == null ||
            usuario.getNome() == null ||
            usuario.getSenha() == null) {
        throw new IllegalArgumentException("Nome e senha inválidos!");
        }
        if(!usuario.getAutorizacoes().isEmpty()){
            Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();
            for (Autorizacao autorizacao: usuario.getAutorizacoes()){
                Autorizacao autorizacaoBd = buscarPorAutorizacaoId(autorizacao.getId());
                autorizacoes.add(autorizacaoBd);
            }
            usuario.setAutorizacoes(autorizacoes);
        }
        usuario =  usuarioRepo.save(usuario);
        Set<Anotacao> anotacoes = usuario.getAnotacoes();
        usuario.setAnotacoes(new HashSet<Anotacao>());

        for(Anotacao anotacao: anotacoes){
            anotacao.setUsuario(usuario);
            anotacaorep.save(anotacao);
        }
        return usuario;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepo.findAll();
    }


    }
