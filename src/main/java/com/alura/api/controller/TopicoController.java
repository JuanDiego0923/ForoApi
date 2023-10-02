package com.alura.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.api.topico.DatosActualizarTopico;
import com.alura.api.topico.DatosListadoTopico;
import com.alura.api.topico.DatosRegistroTopico;
import com.alura.api.topico.DatosRespuestaTopico;
import com.alura.api.topico.Topico;
import com.alura.api.topico.TopicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico (@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getAutor(), topico.getCurso());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public java.util.List<DatosListadoTopico> listadoTopicos() {
        return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());

        topico.actualizarDatos(datosActualizarTopico);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosMedico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }

}