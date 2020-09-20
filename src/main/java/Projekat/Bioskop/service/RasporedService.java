package Projekat.Bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projekat.Bioskop.entity.Raspored;

import Projekat.Bioskop.repository.RasporedRepository;

@Service
public class RasporedService {
	@Autowired
	private RasporedRepository rasporedResposiotry;
	
	public Raspored save(Raspored r) {
		return this.rasporedResposiotry.save(r);
	}
}
