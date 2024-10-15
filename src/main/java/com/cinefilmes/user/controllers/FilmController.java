package com.cinefilmes.user.controllers;

import com.cinefilmes.user.entitiy.Titulo;
import com.cinefilmes.user.exceptions.FilmNotFoundException;
import com.cinefilmes.user.method.FilmMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/titulo")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

    @Autowired
    private FilmMethod filmMethod;

    // Criar Filme
    @PostMapping("/criar")
    public ResponseEntity<Titulo> criarFilme(@RequestBody Titulo film) {
        Titulo novoFilme = filmMethod.criarFilme(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFilme);
    }

    // Editar Filme
    @PutMapping("/{id}")
    public ResponseEntity<Titulo> editarFilme(@PathVariable Long id, @RequestBody Titulo filmDetails) {
        try {
            Titulo filmeAtualizado = filmMethod.editarFilme(id, filmDetails);
            return ResponseEntity.ok(filmeAtualizado);
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obter Filme por ID
    @GetMapping("/{id}")
    public ResponseEntity<Titulo> obterFilmePorId(@PathVariable Long id) {
        try {
            Titulo filme = filmMethod.buscarFilmePorId(id);
            return ResponseEntity.ok(filme);
        } catch (FilmNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir Filme (Inativar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluirFilme(@PathVariable Long id) {
        try {
            filmMethod.inativarFilme(id); // Inativar o filme
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Filme inativado com sucesso!");
            return ResponseEntity.ok(response); // Retorna um objeto JSON
        } catch (FilmNotFoundException e) {
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Filme não encontrado para exclusão.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Retorna um JSON com mensagem de erro
        }
    }

    // Buscar Filmes Ativos por Título
    @GetMapping(value = "/consulta")
    public ResponseEntity<?> buscarFilmesAtivosPorTitulo(@RequestParam("titulo") String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("O campo 'título' não pode ser vazio."));
        }

        if (titulo.length() < 3) {
            return ResponseEntity.badRequest().body(new ErrorResponse("O campo 'título' deve ter pelo menos 3 caracteres."));
        }

        List<Titulo> film = filmMethod.buscarFilmesAtivos(titulo);

        if (film.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Nenhum filme encontrado com o título especificado."));
        }
        return ResponseEntity.ok(film);
    }

    // Listar Filmes Ativos com Filtros
    @GetMapping("/listar")
    public ResponseEntity<List<Titulo>> listarFilmesAtivosComFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String diretor,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) Integer anoLancamento,
            @RequestParam(required = false) String keyword) {

        List<Titulo> film = filmMethod.listarFilmesAtivosComFiltros(titulo, diretor, genero, anoLancamento, keyword);
        return ResponseEntity.ok(film);
    }
}

// Classe para representar erros (pode ser usada por ambos os controladores)
class ErrorResponse {
    private String mensagem;

    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
