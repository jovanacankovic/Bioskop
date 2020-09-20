package Projekat.Bioskop.entity.dto;

import java.util.Date;

public class ProjekcijaDTO {
	private Long id;
	private String naziv;
	private String zanr;
	private String opis;
	private Long trajanje;
	private Double ocena;
	private String datumOdrzavanja;
	private String vremePocetka;
	private int cena;
	private int brojRezervacija;
	private String oznaka;
	private String bioskop;
	private Long gledalacId;
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
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	public Long getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(Long trajanje) {
		this.trajanje = trajanje;
	}
	public Double getOcena() {
		return ocena;
	}
	public void setOcena(Double ocena) {
		this.ocena = ocena;
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
	public int getBrojRezervacija() {
		return brojRezervacija;
	}
	public String getDatumOdrzavanja() {
		return datumOdrzavanja;
	}
	public void setDatumOdrzavanja(String datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}
	public void setBrojRezervacija(int brojRezervacija) {
		this.brojRezervacija = brojRezervacija;
	}
	
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	public String getBioskop() {
		return bioskop;
	}
	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}
	public Long getGledalacId() {
		return gledalacId;
	}
	public void setGledalacId(Long gledalacId) {
		this.gledalacId = gledalacId;
	}
	
	
	
}
