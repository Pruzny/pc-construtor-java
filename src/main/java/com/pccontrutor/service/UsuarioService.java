package com.pccontrutor.service;

import com.pccontrutor.exception.EntidadeNaoEncontradaException;
import com.pccontrutor.model.Usuario;
import com.pccontrutor.repository.MontagemRepository;
import com.pccontrutor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MontagemService montagemService;

    @Autowired
    private MontagemRepository montagemRepository;

    public List<Usuario> getTodos() {
        return usuarioRepository.findAll(Sort.by("id"));
    }

    public Usuario cadastrar(Usuario produto) {
        return usuarioRepository.save(produto);
    }

    public Usuario get(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário número " + id + " não encontrado."));
    }

    public Usuario atualiza(Usuario usuario) {
        // Precisa usar lock?
        return usuarioRepository.save(usuario);
    }

    public void remover(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario remover(Usuario usuario) {
        Usuario outroProduto = get(usuario.getId());
        usuarioRepository.deleteById(outroProduto.getId());
        return outroProduto;
    }

    public List<Usuario> login(String email, String senha) {
        return usuarioRepository.login(email, senha);
    }

    public Page<Usuario> getPaginados(String nome, Pageable pageable) {
        return usuarioRepository.getPaginado(nome, pageable);
    }
}
