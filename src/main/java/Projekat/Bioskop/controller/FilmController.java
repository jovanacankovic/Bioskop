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



import Projekat.Bioskop.service.GledalacService;

import Projekat.Bioskop.entity.Gledalac;


import Projekat.Bioskop.entity.Projekcija;
import Projekat.Bioskop.entity.dto.FilmDTOPretraga;
import Projekat.Bioskop.entity.dto.ProjekcijaDTO;
import Projekat.Bioskop.entity.dto.RezervacijaDTO;
import Projekat.Bioskop.entity.Film;
import Projekat.Bioskop.entity.dto.FilmDTO;
import Projekat.Bioskop.service.FilmService;
//import Projekat.Bioskop.service.ProjekcijaService;
import Projekat.Bioskop.service.ProjekcijaService;

@RestController
@RequestMapping(value="/api/filmovi")
public class FilmController {
	
	
	
	@Autowired
	private FilmService filmService;
	@Autowired
	private ProjekcijaService projekcijaService;
	@Autowired
	private GledalacService gledalacService;
		
	@PostMapping(
			value="/add-film",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Film> dodaj(@RequestBody FilmDTO f)throws Exception{
		Film film=new Film();
		film.setNaziv(f.getNaziv());
		film.setOpis(f.getOpis());
		film.setZanr(f.getZanr());
		film.setTrajanje(f.getTrajanje());
		Double o=Double.parseDouble("0");
		film.setOcena(o);
		this.filmService.save(film);
		
		return new ResponseEntity<>(film,HttpStatus.OK);
	}
	
		@GetMapping(
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> getFilmovi(){
			List<Film> filmovi=this.filmService.findAll();
			
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for(Film film:filmovi) {
				FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
				filmoviDTO.add(filmDTO);
			}
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		}
		
		@GetMapping(
				value="/sortNaziv",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> sortNaziv(){
			List<Film> filmovi=this.filmService.orderNaziv();
		
			
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for(Film film:filmovi) {
				FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
				filmoviDTO.add(filmDTO);
			}
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		}
		

		@GetMapping(
				value="/sortOcena",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> sortOcena(){
			List<Film> filmovi=this.filmService.orderOcena();
		
			
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for(Film film:filmovi) {
				FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
				filmoviDTO.add(filmDTO);
			}
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		}
		

		@GetMapping(
				value="/sortTrajanje",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> sortTrajanje(){
			List<Film> filmovi=this.filmService.orderTrajanje() ;
		
			
			List<FilmDTO> filmoviDTO=new ArrayList<>();
			
			for(Film film:filmovi) {
				FilmDTO filmDTO=new FilmDTO(film.getId(),film.getNaziv(),film.getOpis(),film.getZanr(),film.getTrajanje(),film.getOcena());
				filmoviDTO.add(filmDTO);
			}
			return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
		}
		
		@PostMapping(
				value="/pretraga",
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<FilmDTO>> pretraga(@RequestBody FilmDTOPretraga f)throws Exception{
				List<Projekcija> projekcije=this.projekcijaService.findAll();
				List<FilmDTO> filmoviDTO =new ArrayList<>();
				
				int znak=0; //znak da je bar neki parametar bo kad kod nekog filma
				boolean r=true;  //razlicit
				for (Projekcija tr : projekcije) {
					r=true;
					//NAZIV
					if(f.getNaziv()!="") {
						if(tr.getFilm().getNaziv().equalsIgnoreCase(f.getNaziv())) {
							znak=1;	
						}else {
							r=false;	
						}
					}
					//ZANR
					if(f.getZanr()!="") {
						if(tr.getFilm().getZanr().equalsIgnoreCase(f.getZanr())) {
							znak=1;					
						}else {
							r=false;		
						}
					}
					//OPIS
					if(f.getOpis()!="") {
						if(tr.getFilm().getOpis().equalsIgnoreCase(f.getOpis())) {
							znak=1;
							
						}else {
							r=false;	
						}
					}
					//OCENA
					if(f.getOcena()!=null) {
						System.out.println(f.getNaziv());
						
							if(Double.compare(f.getOcena(), tr.getFilm().getOcena())==0) {
								znak=1;
							}else {
								r=false;
							}
					}
					//CENA
					if(f.getCena()!=0) {
						if(tr.getCena()==f.getCena()) {
							znak=1;
							
						}else {
							r=false;				
						}
					}
					//DATUM
						/*if(tr.getDatum_odrzavanja()!=null) {
							if(tr.getDatum_odrzavanja().compareTo(f.getDatumOdrzavanja())==0) {
								znak=1;
								//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
							}else {
								r=false;
								//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
							}
						}*/
					
					if(znak==1) {
						if(r==true) {
							FilmDTO filmDTO=new FilmDTO(tr.getFilm().getId(),tr.getFilm().getNaziv(),tr.getFilm().getOpis(),tr.getFilm().getZanr(),tr.getFilm().getTrajanje(),tr.getFilm().getOcena());
							
							/*if(!filmoviDTO.contains(filmDTO)) {
								filmoviDTO.add(filmDTO);
							}*/
							boolean postoji=false;
							for (FilmDTO film : filmoviDTO) {
								if(film.getNaziv().equalsIgnoreCase(filmDTO.getNaziv())) {
									postoji=true;
								}
							}
							if(postoji==false) {
								filmoviDTO.add(filmDTO);
							}	
						}
					}
				}
				
				
				if(filmoviDTO.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					
				}
				return new ResponseEntity<>(filmoviDTO,HttpStatus.OK);
				
				
				
		}
		@GetMapping(
				value="/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<ProjekcijaDTO>> prikaz(@PathVariable(name="id") Long id){
			Film f=this.filmService.findOne(id);
			List<ProjekcijaDTO> p=new ArrayList<>();
			List<Projekcija> projekcije=f.getProjekcije();
			if(projekcije.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			for (Projekcija t : projekcije) {
				ProjekcijaDTO tr=new ProjekcijaDTO();
				tr.setId(t.getId());
				tr.setBrojRezervacija(t.getBrojRezervacija());
				tr.setNaziv(t.getFilm().getNaziv());
				tr.setOpis(t.getFilm().getOpis());
				tr.setZanr(t.getFilm().getZanr());
				tr.setTrajanje(t.getFilm().getTrajanje());
				tr.setOcena(t.getFilm().getOcena());
				tr.setCena(t.getCena());
				tr.setDatumOdrzavanja(t.getDatumOdrzavanja());
				tr.setOznaka(t.getSala().getOznaka());
				tr.setVremePocetka(t.getVremePocetka());
				tr.setBioskop(t.getSala().getBioskop().getNaziv());
				p.add(tr);
			}
		
			
			return new ResponseEntity<>(p,HttpStatus.OK);
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		@GetMapping(
				value="/rezervisi/{id}",
				produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ProjekcijaDTO> rezervisi(@PathVariable(name="id")Long id){
				Projekcija t=this.projekcijaService.findOne(id);
				ProjekcijaDTO td=new ProjekcijaDTO();
				td.setId(t.getId());
				
				return new ResponseEntity<>(td,HttpStatus.OK);
		}
		
		@PostMapping(
				value="/rezervacija",
				consumes = MediaType.APPLICATION_JSON_VALUE,     // tip podataka koje metoda može da primi
		        produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<RezervacijaDTO> rez(@RequestBody RezervacijaDTO r)throws Exception{
				Gledalac g=this.gledalacService.Find(r.getKorisnickoIme(), r.getLozinka());
				RezervacijaDTO rd=new RezervacijaDTO();
				rd.setId(g.getId().toString());
				if(g==null) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}else {
					Long id=Long.parseLong(r.getId());
					Projekcija te=this.projekcijaService.findOne(id);
					if(te==null) {
					//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
					Set<Projekcija> rezervisani=g.getRezervisani_filmovi();
					//uslov da bi mogao da rezervise
					if(te.getSala().getKapacitet()>te.getBrojRezervacija()) {
						te.setBrojRezervacija(te.getBrojRezervacija()+1);
						rezervisani.add(te);

						this.gledalacService.save(g);
					}else {
						//nema dovoljno mjesta
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
					
				
					return new ResponseEntity<>(rd,HttpStatus.OK);
				}
		}
		
}
	

