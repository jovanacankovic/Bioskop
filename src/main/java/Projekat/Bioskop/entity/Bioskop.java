package Projekat.Bioskop.entity;


import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import Projekat.Bioskop.entity.Sala;





@Entity
public class Bioskop implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String adresa;
	
	@Column
	private String brojCentrale;
	
	@Column
	private String email;
	
	
	
	//MENADZER
	//jedan bioskop ima jednog menadzera
		//cascade je persist jer ne zelim da mi se obrise menadzer ako obrisem bioskop,posto je taj menadzer mozda zaduzen za jos neki bioskop
		@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
		private Menadzer menadzer;
		
	//SALE
	@OneToMany(mappedBy = "bioskop", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Set<Sala> sale = new HashSet<>();
	
	//Rasporedi filmova
	@OneToMany(mappedBy="bioskop",cascade = CascadeType.REMOVE)
	private List<Raspored> rasporedi=new ArrayList<>();

	//GETERI I SETERI
	
	
	public Bioskop() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojCentrale() {
		return brojCentrale;
	}

	public void setBrojCentrale(String brojCentrale) {
		this.brojCentrale = brojCentrale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Menadzer getMenadzer() {
		return menadzer;
	}

	public void setMenadzer(Menadzer menadzer) {
		this.menadzer = menadzer;
	}

	

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public List<Raspored> getRasporedi() {
		return rasporedi;
	}

	public void setRasporedi(List<Raspored> rasporedi) {
		this.rasporedi = rasporedi;
	}

	public Bioskop(String naziv, String adresa, String brojCentrale, String email) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojCentrale= brojCentrale;
		this.email = email;
	}
	
	
	
	
}