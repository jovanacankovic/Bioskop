package Projekat.Bioskop.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;









@Entity
public class Gledalac extends Korisnik{
	


	//ODGLEDANI FILMOVI
	@ManyToMany
	@JoinTable(name="lista_odgledanih_filmova",
	joinColumns = @JoinColumn(name = "gledalac_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"))
	private Set<Film> odgledani_filmovi=new HashSet<>();
	 
	
	
	@ManyToMany
	@JoinTable(name="lista_rezervisanih_filmova",
	joinColumns = @JoinColumn(name = "gledalac_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "projekcija_id", referencedColumnName = "id"))
	private Set<Projekcija> rezervisani_filmovi=new HashSet<>();
	
	
	 //LISTA OCENJENIH FILMOVA
	 @ManyToMany
		@JoinTable(name="lista_ocenjenih_filmova",
		joinColumns = @JoinColumn(name = "gledalac_id", referencedColumnName = "id"),
	    inverseJoinColumns = @JoinColumn(name = "ocena_id", referencedColumnName = "id"))
		private Set<Ocena> ocene=new HashSet<>();


	public Set<Film> getOdgledani_filmovi() {
		return odgledani_filmovi;
	}


	public void setOdgledani_filmovi(Set<Film> odgledani_filmovi) {
		this.odgledani_filmovi = odgledani_filmovi;
	}


	public Set<Projekcija> getRezervisani_filmovi() {
		return rezervisani_filmovi;
	}


	public void setRezervisani_filmovi(Set<Projekcija> rezervisani_filmovi) {
		this.rezervisani_filmovi = rezervisani_filmovi;
	}


	public Set<Ocena> getOcene() {
		return ocene;
	}


	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}
	 
	 
	 //GETERI I SETERI
	

	

	
	
	 
	
}