package Projekat.Bioskop.entity.dto;

import Projekat.Bioskop.entity.Bioskop;
import Projekat.Bioskop.entity.Projekcija;


public class RasporedDTO {
	private Long id;
	private Bioskop bioskop;
	private Projekcija t;
	
	public RasporedDTO() {}
	
	public RasporedDTO(Long id,Bioskop bioskop, Projekcija t) {
		super();
		this.id=id;
		this.bioskop = bioskop;
		this.t = t;
	}
	
	
	
	
	public Bioskop getBioskop() {
		return bioskop;
	}
	public void setBioskop(Bioskop bioskop) {
		this.bioskop = bioskop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projekcija getT() {
		return t;
	}

	public void setT(Projekcija t) {
		this.t = t;
	}
	
}
