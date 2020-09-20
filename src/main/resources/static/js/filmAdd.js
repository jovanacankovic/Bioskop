$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var naziv=$("#naziv").val();
	var opis=$("#opis").val();
	var zanr=$("#zanr").val();
	var trajanje=$("#trajanje").val();

	
	var newFilmJSON=formToJSON(naziv,opis,zanr,trajanje);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/filmovi/add-film",
		dataType:"json",
		contentType:"application/json",
		data:newFilmJSON,
		success:function(){
			alert("Film je uspesno dodat");
			window.location.href="filmovi.html";
		},
		error:function(data){
			alert("Greska");
			
		}
		
	});
	
});

function formToJSON(naziv,opis,zanr,trajanje){
	return JSON.stringify({
		"naziv":naziv,
		"opis":opis,
		"zanr":zanr,
		"trajanje":trajanje
		
	});
}