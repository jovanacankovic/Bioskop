package Projekat.Bioskop.entity.dto;

public class SalaDTO {
	private Long id;
	private String oznaka;
	private int kapacitet;
	private String bioskop;
	
	
	
	public SalaDTO () {}

	public SalaDTO(Long id, String oznaka, int kapacitet, String bioskop) {
		super();
		this.id = id;
		this.oznaka = oznaka;
		this.kapacitet = kapacitet;
		this.bioskop = bioskop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getBioskop() {
		return bioskop;
	}

	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}
	
	
	
}
