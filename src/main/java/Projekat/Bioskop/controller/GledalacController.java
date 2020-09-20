package Projekat.Bioskop.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Projekat.Bioskop.entity.Film;
import Projekat.Bioskop.entity.Ocena;

import Projekat.Bioskop.entity.Administrator;
import Projekat.Bioskop.entity.Gledalac;
import Projekat.Bioskop.entity.Menadzer;
import Projekat.Bioskop.service.GledalacService;
import Projekat.Bioskop.service.MenadzerService;

@Controller
public class GledalacController {

	@Autowired
	private GledalacService gledalacService;
	
	
	
	
	/*
	@PostMapping("/novi")
	public String sacuvaj(@ModelAttribute Gledalac gledalac)
	{
		if(gledalac.getUloga().equalsIgnoreCase("gledalac"))
		{
			Gledalac g=new Gledalac();
			g.setKorisnickoIme(gledalac.getKorisnickoIme());
			g.setLozinka(gledalac.getLozinka());
			g.setIme(gledalac.getIme());
			g.setPrezime(gledalac.getPrezime());
			g.setEmailAdresa(gledalac.getEmailAdresa());
			g.setDatumRodjenja(gledalac.getDatumRodjenja());
			g.setKontakt_telefon(gledalac.getKontaktTelefon());
			g.setUloga(gledalac.getUloga());
			g.setAktivan(true);
			this.gledalacService.save(g);
			return "prijava.html";
		} else {
			Menadzer m=new Menadzer();
			m.setKorisnickoIme(gledalac.getKorisnickoIme());
			m.setLozinka(gledalac.getLozinka());
			m.setIme(gledalac.getIme());
			m.setPrezime(gledalac.getPrezime());
			m.setEmailAdresa(gledalac.getEmailAdresa());
			m.setDatumRodjenja(gledalac.getDatumRodjenja());
			m.setKontakt_telefon(gledalac.getKontaktTelefon());
			m.setUloga(gledalac.getUloga());
			m.setAktivan(false);
			//this.menadzerService.save(m);
			return "prijava.html";
		}
	}
	*/
	 

	/*@GetMapping("/gledaociOdgledani/{id}")
	public String odgledaniFilmovi(@PathVariable(name="id")Long id,Model model) {
		Gledalac g=this.gledalacService.findOne(id);
		Set<Film> filmovi=g.getOdgledani_filmovi();
		model.addAttribute("filmovi", filmovi);
		return "odgledaniFilmovi.html";
	}
	
	@GetMapping("gledaociOcjenjeni/{id}")
	public String OcjenjeniFilmovi(@PathVariable(name="id")Long id,Model model) {
		Gledalac g=this.gledalacService.findOne(id);
		Set<Ocena> ocene=g.getOcene();
		model.addAttribute("ocene", ocene);
		return "ocenjeniFilmovi.html";
		
	}
	
	@GetMapping("gledaociNeocjenjeni/{id}")
	public String NeocjenjeniFilmovi(@PathVariable(name="id")Long id,Model model) {
		Gledalac g=this.gledalacService.findOne(id);
		Set<Film> odgledani=g.getOdgledani_filmovi();
		Set<Ocena> ocene=g.getOcene(); //lista ocenjenih filmova
		Set<Film> ocenjeni=new HashSet<>();
		Film f=new Film();
		for (Ocena o : ocene) {
			f=o.getFilm();
			ocenjeni.add(f); //dodajm ocjenjene filmove
			
		}
		Set<Film> neocenjeni=new HashSet<>();
		for (Film film : odgledani) {
			if(!ocenjeni.contains(film)) {
				neocenjeni.add(film);
			}
		}
		model.addAttribute("neocenjeni", neocenjeni);
		return "neocjenjeniFilmovi.html";
		
	}
	*/
	 
	
	
	
}