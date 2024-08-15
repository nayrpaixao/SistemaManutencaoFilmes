package com.cinefilmes.user.method;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinefilmes.user.exceptions.DuplicateFilmException;
import com.cinefilmes.user.exceptions.FilmNotFoundException;
import com.cinefilmes.user.entitiy.Titulo;
import com.cinefilmes.user.repositories.FilmRepository;

@Service
public class FilmMethod {
	
	@Autowired
	private FilmRepository filmRepository;
	
	 public Titulo criarFilme(Titulo film) {
	        	        Optional<Titulo> existingFilm = filmRepository.findByTitulo(film.getTitulo());
	        if (existingFilm.isPresent()) {
	            throw new DuplicateFilmException("Já existe um filme com este título");
	        }
	       
	        return filmRepository.save(film);
		}
	
	
	public Titulo editarFilme( Long id, Titulo filmDetails) {
		Titulo film = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException("Filme não encontrado"));
        film.setGenero(filmDetails.getGenero());
        film.setDiretor(filmDetails.getDiretor());
        film.setSinopse(filmDetails.getSinopse());
        film.setDataLancamento(filmDetails.getDataLancamento());
        return filmRepository.save(film);
    }

	 public String excluirFilme(Long id) {
	        Titulo film = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException("Filme não encontrado"));
	        film.setAtivo(false);
	        filmRepository.save(film);
	        return "O filme de título \"" + film.getTitulo() + "\" foi excluído.";
	    }

	 public List<Titulo> buscarFilmesAtivosPorTitulo(String keyword) {
	        return filmRepository.findByTituloContainingIgnoreCaseAndAtivoTrue(keyword);
	    }

	
	 public List<Titulo> listarFilmesAtivosComFiltros(String titulo, String diretor, String genero, Integer dataLancamento, String keyword) {
	        return filmRepository.findByFilters(titulo, diretor, genero, dataLancamento, keyword);
	    }	
	}


		


