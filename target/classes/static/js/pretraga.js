$(document).on("submit","form",function(event){
	event.preventDefault();
	$(".container").hide();
	$('#tabela').show();
	
	
	var naziv=$("#naziv").val();
	var zanr=$("#zanr").val();
	var opis=$("#opis").val();
	var ocena=$("#ocena").val();
	var cena=$("#cena").val();
	var datum=$("#datum").val();
	
	
	var newFilmJSON=formToJSON(naziv,zanr,opis,ocena,cena,datum);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/filmovi/pretraga",
		dataType:"json",
		contentType:"application/json",
		data:newFilmJSON,
		success:function(data){
			for(i=0;i<data.length;i++){
				var row="<tr>";
				
				
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['opis']+"</td>";
				row+="<td>"+data[i]['zanr']+"</td>";
				row+="<td>"+data[i]['ocena']+"</td>";
				
				 
	             
	             $('#tabela').append(row);
			}
			
		},
		error:function(data){
			alert("Nema pronadjenih filmova");
			window.location.href="pretraga.html";
			
		}
		
	});
	
});

function formToJSON(naziv,zanr,opis,ocena,cena,datum){
	return JSON.stringify({
		"naziv":naziv,
		"zanr":zanr,
		"opis":opis,
		"ocena":ocena,
		"cena":cena,
		"datumOdzavanja":datum
		
	});
}


           
   

