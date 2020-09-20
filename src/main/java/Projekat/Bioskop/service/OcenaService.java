package Projekat.Bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Bioskop.entity.Ocena;
import Projekat.Bioskop.repository.OcenaRepository;

@Service
public class OcenaService {
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	public Ocena save(Ocena o) {
		return this.ocenaRepository.save(o);
	}
}
