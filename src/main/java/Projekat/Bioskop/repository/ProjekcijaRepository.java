package Projekat.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Projekat.Bioskop.entity.Film;
import Projekat.Bioskop.entity.Projekcija;

public interface ProjekcijaRepository extends JpaRepository<Projekcija,Long>{

	List<Projekcija> findAllByFilm(Film film);
}
