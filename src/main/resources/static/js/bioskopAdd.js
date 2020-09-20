$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var brojCentrale=$("#broj").val();
	var email=$("#email").val();
	var menadzer=$("#menadzer").val();
	
	var newBioskopJSON=formToJSON(naziv,adresa,brojCentrale,email,menadzer);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/add-bioskop",
		dataType:"json",
		contentType:"application/json",
		data:newBioskopJSON,
		success:function(){
			alert("Bioskop je uspesno dodat");
			window.location.href="index.html";
		},
		error:function(data){
			alert("Greska");
			window.location.href="index.html";
		}
		
	});
	
});

function formToJSON(naziv,adresa,broj,email,menadzer){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"brojCentrale":broj,
		"email":email,
		"menadzer":menadzer
	});
}