package Projekat.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import Projekat.Bioskop.entity.Film;
import Projekat.Bioskop.entity.Projekcija;
import Projekat.Bioskop.repository.ProjekcijaRepository;



@Service
public class ProjekcijaService {
	
	@Autowired
	private ProjekcijaRepository projekcijaRepository;
	
	public Projekcija findOne(Long id) {
        Projekcija projekcija= this.projekcijaRepository.getOne(id);
        return projekcija;
    }
	
	public List<Projekcija> findAll(){
		List<Projekcija> projekcije=this.projekcijaRepository.findAll();
		return projekcije;
	}
	
	public List<Projekcija> findByFilm(Film f){
		List<Projekcija> projekcije=this.projekcijaRepository.findAllByFilm(f);
		return projekcije;
	}
	public Projekcija save(Projekcija t) {
		Projekcija te=this.projekcijaRepository.save(t);
		return te;
	}
}
