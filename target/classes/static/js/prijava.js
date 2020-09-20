$(document).on("submit","form",function(event){
	event.preventDefault();

		
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	
	var newKorisnikJSON=formToJSON(korisnickoIme,lozinka);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/get-korisnik",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success: function (data) {
        	$(".poruka").show();
            $(".poruka").text("Uspešno ste se prijavili");
			window.localStorage.setItem("id", data['id']);
            window.localStorage.setItem("korisnickoIme", data['korisnickoIme']);
            window.localStorage.setItem("lozinka", data['lozinka']);
			window.localStorage.setItem("ime", data['ime']);
			window.localStorage.setItem("prezime", data['prezime']);
			window.localStorage.setItem("kontaktTelefon", data['kontaktTelefon']);
			window.localStorage.setItem("emailAdresa", data['emailAdresa']);
			window.localStorage.setItem("datumRodjenja", data['datumRodjenja']);
			window.localStorage.setItem("uloga", data['uloga']);
            window.location.href = "profil.html";
        },
		error:function(data){
			alert("Greska! Korisnik sa unijetim podacima je neposotjeći");
		}
	});
	
});

function formToJSON(korisnickoIme,lozinka){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka
		
	});
}