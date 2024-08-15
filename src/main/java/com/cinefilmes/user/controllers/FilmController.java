package com.cinefilmes.user.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cinefilmes.user.entitiy.Titulo;
import com.cinefilmes.user.method.FilmMethod;

@RestController
@RequestMapping("/titulo")
public class FilmController {

	@Autowired
    private FilmMethod filmMethod;

    @PostMapping
    public Titulo criarFilme(@RequestBody Titulo film) {
        return filmMethod.criarFilme(film);
    }

        @PutMapping("/{id}")
    public Titulo editarFilme(@PathVariable Long id, @RequestBody Titulo filmDetails) {
        return filmMethod.editarFilme(id, filmDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirFilme(@PathVariable Long id) {
       String mensagem =  filmMethod.excluirFilme(id);
        return ResponseEntity.ok(mensagem);
    }

    @GetMapping(value = "/consulta")
    public ResponseEntity<List<Titulo>> buscarFilmesAtivosPorTitulo(@RequestParam("titulo") String titulo) {
        List<Titulo> film = filmMethod.buscarFilmesAtivosPorTitulo(titulo);
        return new ResponseEntity<>(film, HttpStatus.OK);
         }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Titulo>> listarFilmesAtivosComFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String diretor,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer dataLancamento,
    		@RequestParam(required = false) String keyword)     {
        
        List<Titulo> film = filmMethod.listarFilmesAtivosComFiltros(titulo, diretor, genero, dataLancamento, keyword);
        return ResponseEntity.ok(film);
}
}