$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var oznaka=$("#oznaka").val();
	var kapacitet=$("#kapacitet").val();
	var bioskop=$("#bioskop").val();
	
	
	var newSalaJSON=formToJSON(oznaka,kapacitet,bioskop);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/add-sala",
		dataType:"json",
		contentType:"application/json",
		data:newSalaJSON,
		success:function(){
			alert("Sala je uspesno dodata");
			window.location.href="profil.html";
		},
		error:function(data){
			alert("Greska");
			window.location.href="salaAdd.html";
		}
		
	});
	
});

function formToJSON(oznaka,kapacitet,bioskop){
	return JSON.stringify({
		"oznaka":oznaka,
		"kapacitet":kapacitet,
		"bioskop":bioskop
		
	});
}