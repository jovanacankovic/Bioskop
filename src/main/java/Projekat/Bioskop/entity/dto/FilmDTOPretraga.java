package Projekat.Bioskop.entity.dto;

import java.util.Date;

public class FilmDTOPretraga {
	private Long id;
	private String naziv;
	private String opis;
	private String zanr;
	private Double ocena;
	private int cena;
	private String datumOdrzavanja;
	
	public FilmDTOPretraga(Long id, String naziv, String opis, String zanr, Double ocena, int cena,
			String datumOdrzavanja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.ocena = ocena;
		this.cena = cena;
		this.datumOdrzavanja = datumOdrzavanja;
	}

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

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(String datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	
	
	
	
}
