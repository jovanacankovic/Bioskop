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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projekat.Bioskop.entity.Bioskop;
import Projekat.Bioskop.entity.Raspored;
import Projekat.Bioskop.entity.Sala;
import Projekat.Bioskop.entity.dto.SalaDTO;
import Projekat.Bioskop.entity.dto.ProjekcijaDTO;
import Projekat.Bioskop.service.BioskopService;
import Projekat.Bioskop.service.MenadzerService;
import Projekat.Bioskop.service.SalaService;

@RestController
@RequestMapping(value="/api")
public class MenadzerController {
	@Autowired
	private MenadzerService menadzerService;
	@Autowired
	private BioskopService bioskopService;
	@Autowired 
	private SalaService salaService;
	
	
	@GetMapping(
			value="/sale/{id}", //id bioskopa
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> sale(@PathVariable(name="id") Long id){
			Bioskop b=this.bioskopService.findOne(id);
			if(b==null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Set<Sala> sale= b.getSale();
			
			List<SalaDTO> povratna=new ArrayList<>();
			for(Sala s:sale) {
				SalaDTO sd=new SalaDTO();
				sd.setId(s.getId());
				sd.setKapacitet(s.getKapacitet());
				sd.setOznaka(s.getOznaka());
				sd.setBioskop(b.getNaziv());
				povratna.add(sd);
			}
			
			return new ResponseEntity<>(povratna,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/saleUkloni/{id}",  //id sale
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SalaDTO> ukloniSalu(@PathVariable(name="id")Long id){
			Sala s=this.salaService.findOne(id);
			 SalaDTO salaDTO=new SalaDTO(s.getId(), s.getOznaka(), s.getKapacitet(), s.getBioskop().getNaziv());
			 this.salaService.deleteById(id);
			 
			 return new ResponseEntity<>(salaDTO,HttpStatus.OK);
	}
	
	@GetMapping(
			value="/repertoar/{id}",  //dobijem id bioskopa 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjekcijaDTO>> repertoar(@PathVariable(name="id")Long id){
			Bioskop b=this.bioskopService.findOne(id);
			List<ProjekcijaDTO> povratna=new ArrayList<>();
			List<Raspored> rasporedi=b.getRasporedi();
			
			for(Raspored r:rasporedi) {
				ProjekcijaDTO t=new ProjekcijaDTO();
				t.setId(r.getProjekcija().getId());
				t.setBioskop(b.getNaziv());
				t.setOznaka(r.getProjekcija().getSala().getOznaka());
				t.setDatumOdrzavanja(r.getProjekcija().getDatumOdrzavanja());
				t.setNaziv(r.getProjekcija().getFilm().getNaziv());
				t.setVremePocetka(r.getProjekcija().getVremePocetka());
				t.setOznaka(r.getProjekcija().getSala().getOznaka());
				t.setCena(r.getProjekcija().getCena());
				t.setBrojRezervacija(r.getProjekcija().getBrojRezervacija());
				povratna.add(t);
				
			}
			
			return new ResponseEntity<>(povratna,HttpStatus.OK);
			
			
	}
}
