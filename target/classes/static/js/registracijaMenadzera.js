$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	var ime=$("#ime").val();
	var prezime=$("#prezime").val();
	var kontaktTelefon=$("#kontaktTelefon").val();
	var email=document.getElementById("mail").value;
	var datumRodjenja=$("#datum").val();
	
	var newKorisnikJSON=formToJSON(korisnickoIme,lozinka,ime,prezime,kontaktTelefon,email,datumRodjenja);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/save-menadzer",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(){
			alert(ime+" "+prezime+" je uspesno registrovan kao mendazer");
			window.location.href="registracijaMenadzera.html";
		},
		error:function(data){
			alert("Greska! Pokušajte ponovo.");
		}
	});
	
});

//pomocna funkcija koja od polja praavi JSON
function formToJSON(korisnicko,lozinka,ime,prezime,telefon,email,datum){
	return JSON.stringify({
		"korisnickoIme":korisnicko,
		"lozinka":lozinka,
		"ime":ime,
		"prezime":prezime,
		"kontaktTelefon":telefon,
		"email":email,
		"datumRodjenja":datum
	});
}