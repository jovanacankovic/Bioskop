package Projekat.Bioskop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Projekat.Bioskop.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator,Long>{

	Administrator findByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
}
