package Projekat.Bioskop.entity;

import java.io.Serializable;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import Projekat.Bioskop.entity.Gledalac;



@Entity
public class Projekcija implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String datumOdrzavanja;
	
	@Column
	private String vremePocetka;
	
	@Column
	private int cena;
	
	@Column
	private int brojRezervacija;
	
	//FILM
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn
	private Film film;
	
	//SALE
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn
	private Sala sala; 
	
	//BIOSKOP
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Bioskop bioskop;
	
	//GLEDAOCI
	@ManyToMany(mappedBy="rezervisani_filmovi",cascade=CascadeType.REMOVE)
	private Set<Gledalac> gledaoci_koji_su_rezervisali_film=new HashSet<>();
	
	//RASPOREDI FILMOVA
	@OneToMany(mappedBy="projekcija",cascade=CascadeType.ALL)
	private Set<Raspored> rasporedi=new HashSet<>();
	
	
	//GETERI I SETERI
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(String datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	

	public String getVremePocetka() {
		return vremePocetka;
	}

	public void setVremePocetka(String vremePocetka) {
		this.vremePocetka = vremePocetka;
	}

	

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	

	public Set<Raspored> getRasporedi() {
		return rasporedi;
	}

	public void setRasporedi(Set<Raspored> rasporedi) {
		this.rasporedi = rasporedi;
	}

	public Set<Gledalac> getGledaoci_koji_su_rezervisali_film() {
		return gledaoci_koji_su_rezervisali_film;
	}

	public void setGledaoci_koji_su_rezervisali_film(Set<Gledalac> gledaoci_koji_su_rezervisali_film) {
		this.gledaoci_koji_su_rezervisali_film = gledaoci_koji_su_rezervisali_film;
	}

	public int getBrojRezervacija() {
		return brojRezervacija;
	}

	public void setBrojRezervacija(int brojRezervacija) {
		this.brojRezervacija = brojRezervacija;
	}

	
	
	
	
	
}