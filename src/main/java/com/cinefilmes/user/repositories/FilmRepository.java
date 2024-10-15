package com.cinefilmes.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cinefilmes.user.entitiy.Titulo;

public interface FilmRepository extends JpaRepository<Titulo , Long> {

	List<Titulo> findByTituloContainingAndAtivoTrue(String titulo);

	List<Titulo> findByAtivoTrue();

	Optional<Titulo> findByTitulo(String titulo);

	@Query("SELECT f FROM Titulo f WHERE f.ativo = true AND LOWER(f.titulo) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Titulo> findByTituloContainingIgnoreCaseAndAtivoTrue(@Param("keyword") String keyword);

	@Query("SELECT f FROM Titulo f WHERE f.ativo = true " +
			"AND (:titulo IS NULL OR LOWER(f.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) " +
			"AND (:diretor IS NULL OR LOWER(f.diretor) LIKE LOWER(CONCAT('%', :diretor, '%'))) " +
			"AND (:genero IS NULL OR LOWER(f.genero) LIKE LOWER(CONCAT('%', :genero, '%'))) " +
			"AND (:dataLancamento IS NULL OR YEAR(f.dataLancamento) = :dataLancamento) " +
			"AND (:keyword IS NULL OR " +
			"LOWER(f.titulo) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(f.diretor) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"LOWER(f.genero) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
			"CAST(f.dataLancamento AS STRING) LIKE LOWER(CONCAT('%', :keyword, '%')))")
	List<Titulo> findByAtivoTrueAndFilters(
								@Param("titulo") String titulo,
							   @Param("diretor") String diretor,
							   @Param("genero") String genero,
							   @Param("dataLancamento") Integer dataLancamento,
							   @Param("keyword") String keyword);

	@Query("SELECT f FROM Titulo f WHERE f.ativo = true AND YEAR(f.dataLancamento) = :anoLancamento")
	List<Titulo> findByAnoLancamento(@Param("anoLancamento") Integer anoLancamento);

}

