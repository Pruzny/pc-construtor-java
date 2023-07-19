package com.pccontrutor.util;

import com.pccontrutor.model.Montagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public class MontagensPaginadas {
    // Dá pra fazer página genérica?
    private long totalDeItens;
    private int totalDePaginas;
    private int paginaCorrente;
    private List<Montagem> itens;
}
