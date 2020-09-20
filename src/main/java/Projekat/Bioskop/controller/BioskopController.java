package Projekat.Bioskop.controller;

import java.util.ArrayList;
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



import Projekat.Bioskop.entity.Projekcija;
import Projekat.Bioskop.entity.Raspored;
import Projekat.Bioskop.entity.dto.ProjekcijaDTO;

import Projekat.Bioskop.entity.Film;
//import Projekat.Bioskop.entity.Raspored_filmova;
import Projekat.Bioskop.entity.Sala;
import Projekat.Bioskop.entity.Projekcija;
import Projekat.Bioskop.entity.dto.SalaDTO;

import Projekat.Bioskop.service.FilmService;

import Projekat.Bioskop.service.SalaService;
import Projekat.Bioskop.service.ProjekcijaService;
import Projekat.Bioskop.service.RasporedService;
import Projekat.Bioskop.entity.dto.BioskopDTO;

import Projekat.Bioskop.service.BioskopService;
import Projekat.Bioskop.service.GledalacService;
import Projekat.Bioskop.service.MenadzerService;
import Projekat.Bioskop.entity.Bioskop;
import Projekat.Bioskop.entity.Menadzer;

import Projekat.Bioskop.entity.dto.BioskopDTOAdd;
import Projekat.Bioskop.entity.dto.ProjekcijaDTO;



@RestController
@RequestMapping(value="/api")
public class BioskopController {
	
	@Autowired
	private BioskopService bioskopService;
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private GledalacService gledalacService;
	@Autowired
	private SalaService salaservice;
	@Autowired 
	private FilmService filmService;
	@Autowired 
	private RasporedService rasporedService;
	@Autowired
	private ProjekcijaService projekcijaService;
	

	@GetMapping(
			value="/bioskopi/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BioskopDTO>> bioskopi(@PathVariable(name="id")Long id){
			Menadzer m=this.menadzerService.findOne(id);
			Set<Bioskop> bioskopi=m.getBioskopi();
			List<BioskopDTO> povratna=new ArrayList<>();
			
			for(Bioskop b:bioskopi) {
				BioskopDTO bd=new BioskopDTO();
				bd.setId(b.getId());
				bd.setNaziv(b.getNaziv());
				bd.setAdresa(b.getAdresa());
				bd.setBrojCentrale(b.getBrojCentrale());
				bd.setEmail(b.getEmail());
				povratna.add(bd);
			}
			
			return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	@PostMapping(
		value="/add-bioskop",
		consumes=MediaType.APPLICATION_JSON_VALUE,
		produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTOAdd> dodaj(@RequestBody BioskopDTOAdd b) throws Exception	{
		Bioskop bioskop=new Bioskop(b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEmail());
		String m=b.getMenadzer();
		Menadzer menadzer=this.menadzerService.findKorisnickoIme(m);
	
		if(menadzer==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			bioskop.setMenadzer(menadzer);
			this.bioskopService.save(bioskop);
			
			BioskopDTOAdd bioskopDTO=new BioskopDTOAdd(bioskop.getId(),bioskop.getNaziv(),bioskop.getAdresa(),bioskop.getBrojCentrale(),bioskop.getEmail());
			return new ResponseEntity<>(bioskopDTO,HttpStatus.OK);
		}
		
	}
	
	@GetMapping(
			value="/bioskopi",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BioskopDTO>> getBioskopi(){
		List<Bioskop> bioskopi=this.bioskopService.findAll();
		List<BioskopDTO> bioskopiDTO=new ArrayList<>();
		
		for (Bioskop b : bioskopi) {
			BioskopDTO bioskopDTO=new BioskopDTO(b.getId(), b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEmail());
			bioskopiDTO.add(bioskopDTO);
			
		}
		
		return new ResponseEntity<>(bioskopiDTO,HttpStatus.OK);
	}
	@GetMapping(
			value="/bioskopi/ukloni/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> ukloni(@PathVariable(name="id") Long id){
		Bioskop b=this.bioskopService.findOne(id);
		if(b==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		BioskopDTO bioskop=new BioskopDTO(b.getId(), b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEmail());
		
		this.bioskopService.delete(id);
		
		
		return new ResponseEntity<>(bioskop,HttpStatus.OK);
	}
	
	
	@PostMapping(
			value="/add-sala",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<SalaDTO> dodaj(@RequestBody SalaDTO s) throws Exception	{
			Bioskop b=this.bioskopService.findNaziv(s.getBioskop());
			if(b==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			else {
				Sala sala=new Sala(s.getKapacitet(), s.getOznaka(), b);
				Sala newSala=this.salaservice.save(sala);
				
				SalaDTO salaDTO=new SalaDTO(newSala.getId(), newSala.getOznaka(), newSala.getKapacitet(), newSala.getBioskop().getNaziv());
				return new ResponseEntity<>(salaDTO,HttpStatus.OK);
			}
		}
	
	@PostMapping(
			value="/add-raspored",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Raspored> dodajRaspored(@RequestBody ProjekcijaDTO t) throws Exception{
			Bioskop b=this.bioskopService.findNaziv(t.getBioskop());
			if(b==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Film f=this.filmService.findNaziv(t.getNaziv());
			if(f==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			Projekcija te=new Projekcija();
			
			
			Film f1=new Film(f.getNaziv(), f.getOpis(), f.getZanr(), f.getTrajanje(), f.getOcena());
			f1.setId(f.getId());
			this.filmService.save(f1);
			te.setFilm(f1);
			te.setBrojRezervacija(0);
			te.setCena(t.getCena());
			te.setDatumOdrzavanja(t.getDatumOdrzavanja());
			te.setVremePocetka(t.getVremePocetka());
			Sala s=this.salaservice.findOznaka(t.getOznaka());
			if(s==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			if(s.getBioskop()!=b) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			
			
			Bioskop b2=new Bioskop(b.getNaziv(), b.getAdresa(), b.getBrojCentrale(), b.getEmail());
			
			b2.setId(b.getId());
			Menadzer m2=new Menadzer();
			Menadzer m=b.getMenadzer();
			m2.setId(m.getId());
			m2.setAktivan(true);
			m2.setDatumRodjenja(m.getDatumRodjenja());
			m2.setIme(m.getIme());
			m2.setEmailAdresa(m.getEmailAdresa());
			m2.setKorisnickoIme(m.getKorisnickoIme());
			m2.setPrezime(m.getPrezime());
			m2.setLozinka(m.getLozinka());
			m2.setKontaktTelefon(m.getKontaktTelefon());
			m2.setUloga(m.getUloga());
			this.menadzerService.save(m2);
			b2.setMenadzer(m2);
			
			
			
			Raspored rf=new Raspored();
			
			this.bioskopService.save(b2);
			Sala s2=new Sala(s.getKapacitet(),s.getOznaka(),b2);
			s2.setId(s.getId());
			this.salaservice.save(s2);
			te.setSala(s2);
			this.projekcijaService.save(te);
			rf.setBioskop(b2);
			rf.setProjekcija(te);
			
			this.rasporedService.save(rf);
		
			
			return new ResponseEntity<>(rf,HttpStatus.OK);
			
	}
	
	//izmena sale
	@GetMapping(
			value="sala-izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> sala(@PathVariable(name="id") Long id){
		Sala s=this.salaservice.findOne(id);
		SalaDTO s1=new SalaDTO();
		s1.setId(s.getId());
		s1.setKapacitet(s.getKapacitet());
		s1.setOznaka(s.getOznaka());
		
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}
	
	@PostMapping(
			value="sala/izmenjivanje",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> salaIzmena(@RequestBody SalaDTO s)throws Exception{
		Sala sala=this.salaservice.findOne(s.getId());
		if(sala==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		sala.setKapacitet(s.getKapacitet());
		sala.setOznaka(s.getOznaka());
		this.salaservice.save(sala);
		SalaDTO s1=new SalaDTO();
		s1.setId(sala.getId());
		return new ResponseEntity<>(s1,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/repertoar-izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjekcijaDTO> repertoar(@PathVariable(name="id") Long id){
			Projekcija t=this.projekcijaService.findOne(id);
			ProjekcijaDTO t1=new ProjekcijaDTO();
			t1.setId(t.getId());
			t1.setNaziv(t.getFilm().getNaziv());
			t1.setDatumOdrzavanja(t.getDatumOdrzavanja());
			t1.setVremePocetka(t.getVremePocetka());
			t1.setCena(t.getCena());
			t1.setOznaka(t.getSala().getOznaka());
			
			return new ResponseEntity<>(t1,HttpStatus.OK);

	}
	
	@PostMapping(
			value="/repertoar/izmenjivanje",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjekcijaDTO> repertoarIzmjena(@RequestBody ProjekcijaDTO t)throws Exception{
			Projekcija tr=this.projekcijaService.findOne(t.getId());
			if(tr==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Film f=this.filmService.findNaziv(t.getNaziv());
			if(f==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Sala s=this.salaservice.findOznaka(t.getOznaka());
			if(s==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			if(t.getDatumOdrzavanja()!=null) {
				tr.setDatumOdrzavanja(t.getDatumOdrzavanja());
			}
			tr.setFilm(f);
			
			tr.setVremePocetka(t.getVremePocetka());
			tr.setCena(t.getCena());
			tr.setSala(s);
			this.projekcijaService.save(tr);
			ProjekcijaDTO tr1=new ProjekcijaDTO();
			tr1.setId(tr.getId());
			return new ResponseEntity<>(tr1,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/bioskop-izmeni/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> bioskop(@PathVariable(name="id") Long id){
			Bioskop b=this.bioskopService.findOne(id);
			BioskopDTO b1=new BioskopDTO();
			b1.setId(b.getId());
			b1.setNaziv(b.getNaziv());
			b1.setAdresa(b.getAdresa());
			b1.setBrojCentrale(b.getBrojCentrale());
			b1.setEmail(b.getEmail());
			
			return new ResponseEntity<>(b1,HttpStatus.OK);		
	}
	
	@PostMapping(
			value="bioskop/izmenjivanje",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BioskopDTO> bioskopIzmjena(@RequestBody BioskopDTO b)throws Exception{
		Bioskop bioskop=this.bioskopService.findOne(b.getId());
		if(bioskop==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Bioskop b1=new Bioskop();
		Bioskop b3=new Bioskop();
		b1.setId(bioskop.getId());
		b1.setAdresa(b.getAdresa());
		if(b.getAdresa()==null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		b1.setBrojCentrale(b.getBrojCentrale());
		b1.setNaziv(b.getNaziv());
		b1.setEmail(b.getEmail());
		this.bioskopService.save(b1);
		BioskopDTO b2=new BioskopDTO();
		b2.setId(bioskop.getId());
		return new ResponseEntity<>(b2,HttpStatus.OK);
		
		
	}
		
			
	
}
