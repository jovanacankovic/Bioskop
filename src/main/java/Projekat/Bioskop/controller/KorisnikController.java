package Projekat.Bioskop.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Bioskop.service.FilmService;


import Projekat.Bioskop.entity.Projekcija;

import Projekat.Bioskop.entity.Film;
import Projekat.Bioskop.entity.Ocena;
import Projekat.Bioskop.entity.dto.FilmDTO;

import Projekat.Bioskop.service.AdministratorService;
import Projekat.Bioskop.service.GledalacService;
import Projekat.Bioskop.service.KorisnikService;
import Projekat.Bioskop.service.MenadzerService;
import Projekat.Bioskop.service.OcenaService;
import Projekat.Bioskop.service.ProjekcijaService;


import Projekat.Bioskop.entity.Administrator;
import Projekat.Bioskop.entity.Gledalac;
import Projekat.Bioskop.entity.Korisnik;
import Projekat.Bioskop.entity.Menadzer;

import Projekat.Bioskop.entity.dto.MenadzerDTO;
import Projekat.Bioskop.entity.dto.OcenjivanjeDTO;
import Projekat.Bioskop.entity.dto.ProjekcijaDTO;

@RestController
@RequestMapping(value="/api")
public class KorisnikController {

	
	@Autowired
	private GledalacService gledalacService;
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private ProjekcijaService projekcijaService;
	@Autowired
	private FilmService filmService;
	@Autowired 
	private OcenaService ocenaService;
	
	//za registraciju gledaoca,kada popuni formu
		@PostMapping(
			value="/save-korisnik",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Korisnik> register(@RequestBody Korisnik k) throws Exception{
			Korisnik korisnik=new Korisnik(k.getKorisnickoIme(),k.getLozinka(),k.getIme(),k.getPrezime(),k.getKontaktTelefon(),k.getEmailAdresa(),k.getDatumRodjenja(),k.getUloga());
			Korisnik noviKorisnik=gledalacService.registracija(korisnik);
			return new ResponseEntity<>(noviKorisnik,HttpStatus.OK);
		}
		
		//za logovanje tj pronalazenje u bazi tog korisnika
		@PostMapping(
			value="/get-korisnik",
			consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
	        produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Korisnik> login(@RequestBody Korisnik korisnik) throws Exception{
			Gledalac g=this.gledalacService.Find(korisnik.getKorisnickoIme(),korisnik.getLozinka());
			Menadzer m=this.menadzerService.find(korisnik.getKorisnickoIme(),korisnik.getLozinka());
			Administrator a=this.administratorService.find(korisnik.getKorisnickoIme(), korisnik.getLozinka());
			if(g!=null) {
				Korisnik povratna=new Korisnik(g.getId(),g.getKorisnickoIme(),g.getLozinka(),g.getIme(),g.getPrezime(),g.getKontaktTelefon(),g.getEmailAdresa(),g.getDatumRodjenja(),g.getUloga());
				return new ResponseEntity<>(povratna,HttpStatus.OK);
			}else if(m!=null) {
				Korisnik povratna=new Korisnik(m.getId(),m.getKorisnickoIme(),m.getLozinka(),m.getIme(),m.getPrezime(),m.getKontaktTelefon(),m.getEmailAdresa(),m.getDatumRodjenja(),m.getUloga());
				return new ResponseEntity<>(povratna,HttpStatus.OK);
			}else if(a!=null) {
				Korisnik povratna=new Korisnik(a.getId(),a.getKorisnickoIme(),a.getLozinka(),a.getIme(),a.getPrezime(),a.getKontaktTelefon(),a.getEmailAdresa(),a.getDatumRodjenja(),a.getUloga());
				return new ResponseEntity<>(povratna,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		//za registraciju menadzera od strane admina
		@PostMapping(
				value="/save-menadzer",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Korisnik> registerMenadzer(@RequestBody Korisnik k) throws Exception{
				Korisnik korisnik=new Korisnik(k.getKorisnickoIme(),k.getLozinka(),k.getIme(),k.getPrezime(),k.getKontaktTelefon(),k.getEmailAdresa(),k.getDatumRodjenja(),k.getUloga());
				Korisnik noviKorisnik=menadzerService.registracija(korisnik);
				return new ResponseEntity<>(noviKorisnik,HttpStatus.OK);
			}
			
		//prikaz svih menadzera
		@GetMapping(
				value="/menadzeri",
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<MenadzerDTO>> getMenadzeri(){
			List<Menadzer> menadzeri=this.menadzerService.findAll();
			List<MenadzerDTO> menadzeriDTO=new ArrayList<>();
			
			for (Menadzer m : menadzeri) {
				MenadzerDTO menadzerDTO=new MenadzerDTO(m.getId(), m.getKorisnickoIme(),m.getIme(), m.getPrezime());
				menadzeriDTO.add(menadzerDTO);
				
			}
			
			return new ResponseEntity<>(menadzeriDTO,HttpStatus.OK);
		}
		
		@GetMapping(
				value = "/menadzeri-ukloni/{id}",
		        produces = MediaType.APPLICATION_JSON_VALUE)  
			public ResponseEntity<MenadzerDTO> ukloni(@PathVariable(name="id") Long id){
				Menadzer m=this.menadzerService.findOne(id);
				MenadzerDTO mDTO=new MenadzerDTO(m.getId(), m.getKorisnickoIme(), m.getIme(), m.getPrezime());
				if(m.getBioskopi().isEmpty()) {
					this.menadzerService.delete(id);
					return new ResponseEntity<>(mDTO,HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	
		@GetMapping(
				value="/gledalac-rezervisaneKarte/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<ProjekcijaDTO>> rezervisaneKarte(@PathVariable(name="id")Long id){
			 Gledalac g=this.gledalacService.findOne(id);
			 Set<Projekcija> karte=g.getRezervisani_filmovi();
			 List<ProjekcijaDTO> povratna=new ArrayList<>();
			for (Projekcija t : karte) {
				ProjekcijaDTO tr=new ProjekcijaDTO();
				tr.setId(t.getId());
				tr.setBrojRezervacija(t.getBrojRezervacija());
				tr.setNaziv(t.getFilm().getNaziv());
				tr.setCena(t.getCena());
				tr.setDatumOdrzavanja(t.getDatumOdrzavanja());
				System.out.println(t.getSala());
				tr.setOznaka(t.getSala().getOznaka());
				tr.setVremePocetka(t.getVremePocetka());
				tr.setBioskop(t.getSala().getBioskop().getNaziv());
				tr.setGledalacId(g.getId());
				povratna.add(tr);
			}
			return new ResponseEntity<>(povratna,HttpStatus.OK);
		}
		
		
		
		@GetMapping(
				value="/gledalac-odgledaniFilmovi/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> odgledaniFilmovi(@PathVariable(name="id") Long id){
				Gledalac g=this.gledalacService.findOne(id);
				Set<Film> filmovi=g.getOdgledani_filmovi();
				List<FilmDTO> filmoviDTO=new ArrayList<>();
				
				for (Film f : filmovi) {
					FilmDTO fd=new FilmDTO();
					fd.setId(f.getId());
					fd.setNaziv(f.getNaziv());
					fd.setZanr(f.getZanr());
					fd.setOpis(f.getOpis());
					fd.setTrajanje(f.getTrajanje());
					fd.setOcena(f.getOcena());
					filmoviDTO.add(fd);
				}
				
				return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		}

		
		@GetMapping(
				value="/gledalac-ocenjeniFilmovi/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> ocenjeniFilmovi(@PathVariable(name="id") Long id){
				Gledalac g=this.gledalacService.findOne(id);
				Set<Ocena> filmovi=g.getOcene();
				List<FilmDTO> filmoviDTO=new ArrayList<>();
				
				for (Ocena f : filmovi) {
					FilmDTO fd=new FilmDTO();
					fd.setId(f.getFilm().getId());
					fd.setNaziv(f.getFilm().getNaziv());
					fd.setZanr(f.getFilm().getZanr());
					fd.setOpis(f.getFilm().getOpis());
					fd.setTrajanje(f.getFilm().getTrajanje());
					
					
					fd.setOcena(f.getOcena());
							
					
					
					filmoviDTO.add(fd);
				}
				
				return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		}
		
		
		@GetMapping(
				value="/gledalac-neocenjeniFilmovi/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> neocjenjeniFilmovi(@PathVariable(name="id") Long id){
				Gledalac g=this.gledalacService.findOne(id);
				Set<Film> odgledani=g.getOdgledani_filmovi();
				Set<Ocena> ocene=g.getOcene();
				Set<Film> ocenjeni=new HashSet<>();
				Film film=new Film();
				for(Ocena o: ocene) {  //pravim listu ocjenjenih filmova
					film=o.getFilm();
					ocenjeni.add(film);
				}
				List<FilmDTO> neocenjeni=new ArrayList<>();
				for(Film f: odgledani) {
					if(!ocenjeni.contains(f)) {
						FilmDTO fd=new FilmDTO();
						fd.setId(f.getId());
						fd.setNaziv(f.getNaziv());
						fd.setZanr(f.getZanr());
						fd.setOpis(f.getOpis());
						fd.setTrajanje(f.getTrajanje());
						fd.setOcena(f.getOcena());
						fd.setId(g.getId());
						neocenjeni.add(fd);
					}
				}
				
				return new ResponseEntity<>(neocenjeni,HttpStatus.OK);
			
		}
		
		
		
		@GetMapping(
				value="gledalac-otkaziRezervaciju/{id}/{value}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Gledalac> otkazi(@PathVariable(name="id") Long id,@PathVariable(name="value") Long gledalacId){
				Projekcija tr=this.projekcijaService.findOne(id);
				//smanjenje broja rezervacija jer je otkazano 
				tr.setBrojRezervacija(tr.getBrojRezervacija()-1);
				if(tr==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Gledalac g=this.gledalacService.findOne(gledalacId);
				if(g==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Set<Projekcija> rezervisani=g.getRezervisani_filmovi();
				rezervisani.remove(tr);
				this.gledalacService.save(g);
				Gledalac g1=new Gledalac();
				g1.setId(g.getId());
				
				return new ResponseEntity<>(g1,HttpStatus.OK);
				
		}
		
		@GetMapping(
				value="gledalac-potvrdiRezervaciju/{id}/{value}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Gledalac> potvrdi(@PathVariable(name="id") Long id,@PathVariable(name="value") Long gledalacId){
				Projekcija tr=this.projekcijaService.findOne(id);
				//smanjenje broja rezervacija jer je otkazano 
				tr.setBrojRezervacija(tr.getBrojRezervacija()-1);
				if(tr==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Gledalac g=this.gledalacService.findOne(gledalacId);
				if(g==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				Set<Projekcija> rezervisani=g.getRezervisani_filmovi();
				rezervisani.remove(tr);
				Set<Film> odgledani=g.getOdgledani_filmovi();
				odgledani.add(tr.getFilm());
				this.gledalacService.save(g);
				Gledalac g1=new Gledalac();
				g1.setId(g.getId());
				
				return new ResponseEntity<>(g1,HttpStatus.OK);
				
		}
		
		
		@GetMapping(
				value="gledalac-oceniFilm/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Film> ocijeni(@PathVariable(name="id") Long id){
				Film f=this.filmService.findOne(id);
				Film f1=new Film();
				f1.setId(f.getId());
				
				return new ResponseEntity<>(f1,HttpStatus.OK);	
		}
		
		@PostMapping(
				value="/ocenjivanje",
				consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
		        produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<OcenjivanjeDTO> ocenjivanje(@RequestBody OcenjivanjeDTO o)throws Exception{
				Gledalac g=this.gledalacService.Find(o.getKorisnickoIme(), o.getLozinka());
				OcenjivanjeDTO oc=new OcenjivanjeDTO();
				System.out.println(o);
				System.out.println(oc);
				oc.setId(o.getId().toString());
				
				if(g==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}else {
					Long id=Long.parseLong(o.getId());
					Film f=this.filmService.findOne(id);
					if(f==null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					Double ocena=o.getOcena();
					Double srednjaOcena=(ocena+f.getOcena())/2;
					f.setOcena(srednjaOcena);
					Set<Ocena> ocenjeni=g.getOcene();
					Ocena o1=new Ocena();
					o1.setFilm(f);
					o1.setOcena(ocena);
					this.ocenaService.save(o1);
					ocenjeni.add(o1);
					this.gledalacService.save(g);
					
				}
				return new ResponseEntity<>(oc,HttpStatus.OK);
		}
		
		
}
