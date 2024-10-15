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
		if (film.getTitulo() == null || film.getTitulo().isEmpty()) {
			throw new IllegalArgumentException("O campo título é obrigatório.");
		}
		if (film.getDiretor() == null || film.getDiretor().isEmpty()) {
			throw new IllegalArgumentException("O campo diretor é obrigatório.");
		}
		if (film.getGenero() == null || film.getGenero().isEmpty()) {
			throw new IllegalArgumentException("O campo gênero é obrigatório.");
		}

		Optional<Titulo> existingFilm = filmRepository.findByTitulo(film.getTitulo());
		if (existingFilm.isPresent()) {
			throw new DuplicateFilmException("Já existe um filme com este título");
		}

		return filmRepository.save(film);
	}


	public Titulo editarFilme(Long id, Titulo filmDetails) {
		Titulo film = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException("Filme não encontrado"));
		film.setGenero(filmDetails.getGenero());
		film.setDiretor(filmDetails.getDiretor());
		film.setSinopse(filmDetails.getSinopse());
		film.setDataLancamento(filmDetails.getDataLancamento());
		return filmRepository.save(film);
	}

	public Titulo buscarFilmePorId(Long id) {
		return filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException("Filme não encontrado"));
	}


	public void inativarFilme(Long id) {
		Optional<Titulo> film = filmRepository.findById(id);
		if (film.isPresent()) {
			Titulo titulo = film.get();
			titulo.setAtivo(false);
			filmRepository.save(titulo);
		} else {
			throw new FilmNotFoundException("Filme não encontrado");
		}
	}



	public List<Titulo> buscarFilmesAtivos(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return filmRepository.findByAtivoTrue();
		}

		if (keyword.length() < 3) {
			throw new IllegalArgumentException("O parâmetro 'titulo' deve ter pelo menos 3 caracteres.");
		}

		return filmRepository.findByTituloContainingIgnoreCaseAndAtivoTrue(keyword);
	}

	public List<Titulo> buscarFilmesAtivos() {
		return filmRepository.findByAtivoTrue();
	}

	public List<Titulo> listarFilmesAtivosComFiltros(String titulo, String diretor, String genero, Integer anoLancamento, String keyword) {
		return filmRepository.findByAtivoTrueAndFilters(titulo, diretor, genero, anoLancamento, keyword);
	}
		public List<Titulo> buscarFilmesPorAnoLancamento (Integer anoLancamento){
			return filmRepository.findByAnoLancamento(anoLancamento);
		}
	}










		


