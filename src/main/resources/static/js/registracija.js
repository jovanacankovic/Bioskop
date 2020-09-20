$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	var ime=$("#ime").val();
	var prezime=$("#prezime").val();
	var kontaktTelefon=$("#kontaktTelefon").val();
	var emailAdresa=document.getElementById("emailAdresa").value;
	var datumRodjenja=$("#datumRodjenja").val();
	
	var newKorisnikJSON=formToJSON(korisnickoIme,lozinka,ime,prezime,kontaktTelefon,emailAdresa,datumRodjenja);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/save-korisnik",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(){
			alert(ime+" "+prezime+" je uspesno registrovan kao gledalac");
			window.location.href="prijava.html";
		},
		error:function(data){
			alert("Greska! Pokušajte ponovo.");
		}
	});
	
});

//pomocna funkcija koja od polja praavi JSON
function formToJSON(korisnicko,lozinka,ime,prezime,telefon,emailAdresa,datum){
	return JSON.stringify({
		"korisnickoIme":korisnicko,
		"lozinka":lozinka,
		"ime":ime,
		"prezime":prezime,
		"kontaktTelefon":telefon,
		"emailAdresa":emailAdresa,
		"datumRodjenja":datum
	});
}
	