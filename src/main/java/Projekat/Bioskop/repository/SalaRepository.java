package Projekat.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Projekat.Bioskop.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala,Long>{
	Sala findByOznakaIgnoreCase(String s);
}
