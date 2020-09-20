package Projekat.Bioskop.entity;

import javax.persistence.*;

@Entity
public class Ocena {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Double ocena;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Film film;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	
	
	
	
}