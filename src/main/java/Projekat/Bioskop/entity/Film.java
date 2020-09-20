package Projekat.Bioskop.entity;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;










@Entity
public class Film implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naziv;
	
	@Column
	private String opis;
	
	@Column(nullable = false)
	private String zanr;
	
	@Column(nullable = false)
	private Long trajanje;
	
	@Column
	private double ocena;
	
	
	//GLEDALAC REZERVISANI
	@ManyToMany(mappedBy="rezervisani_filmovi")
	private Set<Gledalac> gledaoci_koji_su_rezervisali_film=new HashSet<>();
	
	//PROJEKCIJA
	@OneToMany(mappedBy="film")
	private List<Projekcija> projekcije = new ArrayList<Projekcija>();
	
	//OCENE
	@OneToMany(mappedBy="film",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Ocena> ocene=new HashSet<>();
	
	//GETERI I SETERI
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public Long getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Long trajanje) {
		this.trajanje = trajanje;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}



	

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public List<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projekcija> projekcije) {
		this.projekcije = projekcije;
	}

	public Film(String naziv, String opis, String zanr, Long trajanje, double ocena) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.ocena = ocena;
		this.trajanje=trajanje;
	}
	
	
	public Film() {
		
	}
	
}