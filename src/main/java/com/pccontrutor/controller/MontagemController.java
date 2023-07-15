package com.pccontrutor.controller;

import com.pccontrutor.model.Montagem;
import com.pccontrutor.service.MontagemService;
import com.pccontrutor.util.MontagensPaginadas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("montagens")    // http://localhost:8080/montagens
public class MontagemController {

    @Autowired
    private MontagemService montagemService;

    @GetMapping
    public List<Montagem> getTodas() {
        return montagemService.getTodas();
    }

    @GetMapping("{idMontagem}")         // http://localhost:8080/montagens/1
    public Montagem get(@PathVariable("idMontagem") Long id) {
        return montagemService.get(id);
    }

    @GetMapping("usuario")       // http://localhost:8080/montagens/usuario?idUsuario=1
    public List<Montagem> getPorUsuarioParam(@RequestParam("idUsuario") Long idUsuario) {
        if (idUsuario == null) {
            return montagemService.getTodas();
        }
        return montagemService.getPorUsuario(idUsuario);
    }

    @GetMapping("usuario/{idUsuario}")         // http://localhost:8080/montagens/usuario/1
    public List<Montagem> getPorUsuarioPath(@PathVariable("idUsuario") Long id) {
        return montagemService.getPorUsuario(id);
    }

    @PostMapping
    public Montagem cadastrar(@RequestBody Montagem montagem) {
        return montagemService.cadastrar(montagem);
    }

    @PutMapping
    public Montagem atualizar(@RequestBody Montagem montagem) {
        return montagemService.atualiza(montagem);
    }

    @DeleteMapping("{idMontagem}")
    public void remover(@PathVariable("idMontagem") Long id) {
        montagemService.remover(id);
    }

    @PostMapping("remover")
    public Montagem remover(@RequestBody Montagem montagem) {
        return montagemService.remover(montagem);
    }

    @GetMapping("paginacao")   // http://localhost:8080/montagens/paginacao?pagina=0&tamanho=5
    public MontagensPaginadas getPaginadas(
            @RequestParam(name="pagina", defaultValue = "0") int pagina,
            @RequestParam(name="tamanho", defaultValue = "5") int tamanho,
            @RequestParam(name="nome", defaultValue = "") String nome
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Montagem> paginaDeMontagem = montagemService.getPaginadas(nome, pageable);
        return new MontagensPaginadas(
                paginaDeMontagem.getTotalElements(),
                paginaDeMontagem.getTotalPages(),
                paginaDeMontagem.getNumber(),
                paginaDeMontagem.getContent()
        );
    }
}
