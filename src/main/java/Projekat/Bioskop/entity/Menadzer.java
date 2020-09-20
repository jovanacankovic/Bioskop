package Projekat.Bioskop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
public class Menadzer extends Korisnik{
	
	
	//BIOSKOP
	//jedan menadzer ima vise bioskopa, i cascadeType je PERSIST jer ne zelim da izbrisem Bioskop ako izbrisem Menadzera
		@OneToMany(mappedBy="menadzer",fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		private Set<Bioskop> bioskopi=new HashSet<>();

		public Set<Bioskop> getBioskopi() {
			return bioskopi;
		}

		public void setBioskopi(Set<Bioskop> bioskopi) {
			this.bioskopi = bioskopi;
		}
	
	
	
	
}
