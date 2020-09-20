package Projekat.Bioskop.entity.dto;

public class FilmDTO {
	private Long id;
	private String naziv;
	private String opis;
	private String zanr;
	private Long trajanje;
	private Double ocena;
	
	
	public FilmDTO(Long id, String naziv, String opis, String zanr, Long trajanje, Double ocena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.zanr = zanr;
		this.trajanje = trajanje;
		this.ocena = ocena;
	}


	public FilmDTO() {
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


	


	
	
	
	
}
