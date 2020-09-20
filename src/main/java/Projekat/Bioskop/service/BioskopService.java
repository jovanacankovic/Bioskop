package Projekat.Bioskop.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Bioskop.entity.Bioskop;
import Projekat.Bioskop.repository.BioskopRepository;


@Service
public class BioskopService {

	@Autowired
	private BioskopRepository bioskopRepository;
	
	public Bioskop findOne(Long id) {
		Bioskop bioskop=this.bioskopRepository.getOne(id);
		return bioskop;
	}
	
	public List<Bioskop> findAll(){
		return this.bioskopRepository.findAll();
	}
	
	public Bioskop findNaziv(String naziv) {
		Bioskop bioskop=this.bioskopRepository.findByNazivIgnoreCase(naziv);
		return bioskop;
	}
	
	public Bioskop save(Bioskop b) {
		return this.bioskopRepository.save(b);
	}
	
	public void delete(Long id) {
		this.bioskopRepository.deleteById(id);
	}
}
