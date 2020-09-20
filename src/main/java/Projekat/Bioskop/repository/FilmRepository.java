package Projekat.Bioskop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Projekat.Bioskop.entity.Film;

public interface FilmRepository extends JpaRepository<Film,Long>{

	List<Film> findAllByNazivIgnoreCase(String naziv);
	
    List<Film> findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndOcena(String naziv,String zanr,String opis,Double o);
	
	List<Film> findByOcenaBetween(Double ol,Double oh);
	
	List<Film> findAllByOrderByNaziv();
	
	List<Film> findAllByOrderByOcena();
	
	List<Film> findAllByOrderByTrajanje();
	
	Film findByNazivIgnoreCase(String naziv);
}