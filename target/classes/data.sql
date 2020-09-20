INSERT INTO ADMINISTRATOR (korisnicko_ime,datum_rodjenja,kontakt_telefon,lozinka,ime,prezime,uloga,aktivan) VALUES ('jovanac','11.08.1998','0643447633','jovana','Jovana','Cankovic','Admin',true);

INSERT INTO MENADZER (email_adresa,korisnicko_ime,datum_rodjenja,kontakt_telefon,lozinka,ime,prezime,uloga,aktivan) VALUES ('nikolica@gmail.com','nikola12','20.03.1999','0645787663','skfckh','Nikola','Nikolic','Menadzer',true);
INSERT INTO MENADZER (email_adresa,korisnicko_ime,datum_rodjenja,kontakt_telefon,lozinka,ime,prezime,uloga,aktivan) VALUES ('damjan@gmail.com','damjanje','10.09.1998','0655723676','damjan','Damjan','Jerkic','Menadzer',true);

INSERT INTO BIOSKOP(naziv, adresa, broj_centrale, email, MENADZER_ID) VALUES ('Arena Cineplex','Bulevara Mihajla Pupina 3','063636363','cineplex@gmail.com',1);
INSERT INTO BIOSKOP(naziv, adresa, broj_centrale, email, MENADZER_ID) VALUES ('Arena Cinestar','Bulevar Oslobodjenja 56','061121221','cinestar@gmail.com',1);

INSERT INTO GLEDALAC(datum_rodjenja, korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, aktivan, uloga) VALUES ('01.01.1999','nemus98', 'nebitno','Nemanja','Markovic','0600233479','nemusbog98@gmail.com', true, 'Gledalac');
INSERT INTO GLEDALAC(datum_rodjenja, korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, aktivan, uloga) VALUES ('02.12.1999','saleza', 'nebitno','Nikola','Savic','060733479','nikolaa@gmail.com', true, 'Gledalac');
INSERT INTO GLEDALAC(datum_rodjenja, korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, aktivan, uloga) VALUES ('15.02.1998','srkilegenda', 'nebitno','Srdjan','Todorovic','0665433479','srkikralj@gmail.com', true, 'Gledalac');
INSERT INTO GLEDALAC(datum_rodjenja, korisnicko_ime, lozinka, ime, prezime, kontakt_telefon, email_adresa, aktivan, uloga) VALUES ('10.06.1999','markele', 'nebitno','Marko','Spasojevic','061063479','markozarko@gmail.com', true, 'Gledalac');

INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('The Lord of the Rings : The Fellowship of the Ring',9,'Prvi deo','epska fantastika',120);
INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('The Lord of the Rings : The Two Towers',8,'Drugi deo','epska fantastika',139);
INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('The Lord of the Rings : The Return of the King',10,'Treci deo','epska fantastika',100);
INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('The Hobbit : An Unexpected Journey',9,'Nastavak serijala The Lord of the Rings','epska fantastika',120);
INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('Dark',9,'Porodicna saga','drama',120);
INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('The Hobbit : The Battle of the Five Armies',8,'Treci deo Hobita','epska fantastika',150);
INSERT INTO FILM(naziv,ocena, opis, zanr, trajanje) VALUES ('Love Actually',6,'Ljubav je procvetala izmedju 2 mlada para','romanticna komedija',160);

INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (200,'Sala 1',1);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (80,'Sala 2',1);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (60,'Sala 3',1);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (250,'Sala 4',1);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (400,'Sala 5',1);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (250,'Sala 6',2);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (120,'Sala 7',2);
INSERT INTO SALA(kapacitet, oznaka, bioskop_id) VALUES (50,'Sala 8',2);

INSERT INTO OCENA (film_id,ocena) VALUES (1,8);
INSERT INTO OCENA (film_id,ocena) VALUES (2,9);
INSERT INTO OCENA (film_id,ocena) VALUES (3,10);
INSERT INTO OCENA (film_id,ocena) VALUES (1,9);
INSERT INTO OCENA (film_id,ocena) VALUES (2,10);
INSERT INTO OCENA (film_id,ocena) VALUES (4,9);
INSERT INTO OCENA (film_id,ocena) VALUES (2,10);
INSERT INTO OCENA (film_id,ocena) VALUES (4,9);


INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (10,200,'1.1.2020','15:00',1,1,1);
INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (15,220,'15.4.2020','19:00',2,2,2);
INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (10,150,'12.6.2020','20:00',1,3,3);
INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (15,250,'18.3.2020','19:00',2,4,4);
INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (10,300,'1.1.2020','21:00',1,5,5);
INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (15,350,'15.4.2020','19:00',2,6,6);
INSERT INTO PROJEKCIJA(BROJ_REZERVACIJA,CENA,DATUM_ODRZAVANJA,VREME_POCETKA, BIOSKOP_ID,FILM_ID, SALA_ID) VALUES (15,250,'15.4.2020','15:00',2,7,1);

INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (1,1);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (1,3);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (2,1);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (3,3);
INSERT INTO LISTA_ODGLEDANIH_FILMOVA (gledalac_id,film_id) VALUES (2,4);

INSERT INTO LISTA_OCENJENIH_FILMOVA (gledalac_id,ocena_id) VALUES (1,1);
INSERT INTO LISTA_OCENJENIH_FILMOVA (gledalac_id,ocena_id) VALUES (2,2);
INSERT INTO LISTA_OCENJENIH_FILMOVA (gledalac_id,ocena_id) VALUES (3,3);

INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,projekcija_id) VALUES (1,1);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,projekcija_id) VALUES (2,2);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,projekcija_id) VALUES (3,3);
INSERT INTO LISTA_REZERVISANIH_FILMOVA (gledalac_id,projekcija_id) VALUES (2,4);


INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (1,1);
INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (1,2);
INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (1,3);
INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (2,4);
INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (2,5);
INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (1,6);
INSERT INTO RASPORED (bioskop_id,projekcija_id) VALUES (1,7);









