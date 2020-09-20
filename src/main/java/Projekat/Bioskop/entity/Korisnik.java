package Projekat.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;




@MappedSuperclass
public class Korisnik implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	protected Long id;

	@Column
	protected String korisnickoIme;

	@Column
	protected String lozinka;

	@Column
	protected String ime;

	@Column
	protected String prezime;

	@Column
	protected String kontaktTelefon;

	@Column
	protected String emailAdresa;

	@Column
	protected String datumRodjenja;

	@Column
	protected String uloga;

	@Column
	protected boolean aktivan;

	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	public void setKontakt_telefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String getEmailAdresa() {
		return emailAdresa;
	}

	public void setEmailAdresa(String emailAdresa) {
		this.emailAdresa = emailAdresa;
	}


	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	
	

	public Korisnik(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTelefon,
			String emailAdresa, String datumRodjenja, String uloga) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.kontaktTelefon = kontaktTelefon;
		this.emailAdresa = emailAdresa;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
	}

	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTelefon,
			String emailAdresa,String datumRodjenja, String uloga) {
		super();
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.kontaktTelefon = kontaktTelefon;
		this.emailAdresa = emailAdresa;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		
	}

	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}