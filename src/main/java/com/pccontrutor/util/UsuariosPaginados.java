package com.pccontrutor.util;

import com.pccontrutor.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class UsuariosPaginados {
    // Dá pra fazer página genérica?
    private long totalDeUsuarios;
    private int totalDePaginas;
    private int paginaCorrente;
    private List<Usuario> usuarios;
}
