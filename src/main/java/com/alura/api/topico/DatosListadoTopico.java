package com.alura.api.topico;

import java.time.LocalDate;

public record DatosListadoTopico(Long id, String titulo, String mensaje, LocalDate fechaCreacion, String autor, String curso) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor(), topico.getCurso());
    }  

}
