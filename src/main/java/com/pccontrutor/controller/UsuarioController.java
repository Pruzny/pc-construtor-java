package com.pccontrutor.controller;

import com.pccontrutor.model.Usuario;
import com.pccontrutor.service.UsuarioService;
import com.pccontrutor.util.UsuariosPaginados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("usuarios")   // http://localhost:8080/usuarios
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping       // http://localhost:8080/usuarios
    public List<Usuario> getTodos() {
        return usuarioService.getTodos();
    }

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }
    // http://localhost:8080/usuarios/1

    @GetMapping("{idUsuario}")       // http://localhost:8080/usuarios/1
    public Usuario get(@PathVariable("idUsuario") Long id) {
        return usuarioService.get(id);
    }

    @PutMapping                  // http://localhost:8080/usuarios
    public Usuario atualizar(@RequestBody Usuario usuario) {
        return usuarioService.atualiza(usuario);
    }

    // http://localhost:8080/produtos/1
    @DeleteMapping("{idUsuario}")
    public void remover(@PathVariable("idUsuario") Long id) {
        usuarioService.remover(id);
    }

    @PostMapping("remover")
    public Usuario remover(@RequestBody Usuario usuario) {
        return usuarioService.remover(usuario);
    }

    @GetMapping("login")        // http://localhost:8080/usuarios/login?email=aaa&senha=bbb
    public List<Usuario> login(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            return null;
        }
        return usuarioService.login(email, senha);
    }

    @GetMapping("paginacao")   // http://localhost:8080/usuarios/paginacao?pagina=0&tamanho=5
    public UsuariosPaginados getPaginados(
            @RequestParam(name="pagina", defaultValue = "0") int pagina,
            @RequestParam(name="tamanho", defaultValue = "5") int tamanho,
            @RequestParam(name="nome", defaultValue = "") String nome
    ) {
//        System.out.println("pagina = " + pagina);
//        System.out.println("tamanho = " + tamanho);
//        System.out.println("nome = " + nome);

        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Usuario> paginaDeUsuario = usuarioService.getPaginados(nome, pageable);
        return new UsuariosPaginados(
            paginaDeUsuario.getTotalElements(),
            paginaDeUsuario.getTotalPages(),
            paginaDeUsuario.getNumber(),
            paginaDeUsuario.getContent()
        );
    }


}
