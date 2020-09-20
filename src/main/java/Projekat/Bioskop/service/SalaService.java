package Projekat.Bioskop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Bioskop.entity.Sala;
import Projekat.Bioskop.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	private SalaRepository salaRepository;
	
	public Sala save(Sala s) {
		return this.salaRepository.save(s);
	}
	
	public Sala findOne(Long id) {
		return this.salaRepository.getOne(id);
	}
	
	public Sala findOznaka(String o) {
		return this.salaRepository.findByOznakaIgnoreCase(o);
	}
	
	public List<Sala> findAll() {
		return this.salaRepository.findAll();
	}
	
	public void deleteById(Long id) {
		this.salaRepository.deleteById(id);
	}
}