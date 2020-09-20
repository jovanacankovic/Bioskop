package Projekat.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Projekat.Bioskop.entity.Bioskop;



public interface BioskopRepository extends JpaRepository<Bioskop,Long>{
	Bioskop findByNazivIgnoreCase(String naziv);
}
