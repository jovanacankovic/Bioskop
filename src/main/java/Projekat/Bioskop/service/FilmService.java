package Projekat.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Bioskop.entity.Film;
import Projekat.Bioskop.repository.FilmRepository;

@Service
public class FilmService {

@Autowired
private FilmRepository filmRepository;

public Film findOne(Long id) {
    Film film = this.filmRepository.getOne(id);
    return film;
}

public List<Film> findAll(){
	List<Film> filmovi=this.filmRepository.findAll();
	return filmovi;
}
public List<Film> orderNaziv(){
	return this.filmRepository.findAllByOrderByNaziv();
}

public Film save(Film f) {
	return this.filmRepository.save(f);
}
public List<Film> orderOcena(){
	return this.filmRepository.findAllByOrderByOcena();
}

public List<Film> orderTrajanje(){
	return this.filmRepository.findAllByOrderByTrajanje();
}

public List<Film> findByNaziv(String naziv){
	List<Film> filmovi=this.filmRepository.findAllByNazivIgnoreCase(naziv);
	return filmovi;
}

	public List<Film> findByKriterijumi(String n,String z,String op,Double oc){
	List<Film> filmovi=this.filmRepository.findByNazivIgnoreCaseAndZanrIgnoreCaseAndOpisIgnoreCaseAndOcena(n, z, op,oc);
	return filmovi;
}
	
	
	public Film findNaziv(String n) {
		Film f=this.filmRepository.findByNazivIgnoreCase(n);
		return f;
	}
}