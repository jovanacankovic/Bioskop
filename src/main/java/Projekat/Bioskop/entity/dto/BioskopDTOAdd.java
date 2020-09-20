package Projekat.Bioskop.entity.dto;

public class BioskopDTOAdd {
	private Long id;
	private String naziv;
	private String adresa;
	private String brojCentrale;
	private String email;
	private String menadzer;
	
	
	public BioskopDTOAdd(Long id, String naziv, String adresa, String brojCentrale, String email) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.brojCentrale = brojCentrale;
		this.email = email;	
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


	public String getMenadzer() {
		return menadzer;
	}


	public void setMenadzer(String menadzer) {
		this.menadzer = menadzer;
	}
	
    

}
