package Projekat.Bioskop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;





@Entity
public class Sala implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private int kapacitet;
	
	@Column(nullable = false)
	private String oznaka;
	
	//BIOSKOP
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    private Bioskop bioskop;
	
	//PROJEKCIJE //sala ima vise terminskih rasporeda,ako obrisem salu,obrisace mi se  teminski raspored koji je u njoj
	@OneToMany(mappedBy="sala",cascade=CascadeType.REMOVE)
	private List<Projekcija> projekcije=new ArrayList<>();

	
	//GETERI I SETERI
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Bioskop getBioskop() {
		return bioskop;
	}

	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public List<Projekcija> getProjekcije() {
		return projekcije;
	}

	public void setProjekcije(List<Projekcija> projekcije) {
		this.projekcije = projekcije;
	}

	public Sala(int kapacitet, String oznaka, Bioskop bioskop) {
		super();
		this.kapacitet = kapacitet;
		this.oznaka = oznaka;
		this.bioskop = bioskop;
		
	}

	public Sala(){}
	
}
