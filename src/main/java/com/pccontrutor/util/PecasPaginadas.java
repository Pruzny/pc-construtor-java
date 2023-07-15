package com.pccontrutor.util;

import com.pccontrutor.model.Peca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class PecasPaginadas {
    // Dá pra fazer página genérica?
    private long totalDePecas;
    private int totalDePaginas;
    private int paginaCorrente;
    private List<Peca> pecas;
}
