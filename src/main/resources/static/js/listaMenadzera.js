$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/menadzeri",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS:",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['korisnickoIme']+"</td>";
				row+="<td>"+data[i]['ime']+"</td>";
				row+="<td>"+data[i]['prezime']+"</td>";
			
				
				 var btn = "<button class='btnUkloni btn btn-primary' id = " + data[i]['id'] + ">Ukloni</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);
	             $("#menadzeri").removeClass("d-none").show();

			}
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});

$(document).on('click', '.btnUkloni', function () {          
                                         


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/menadzeri-ukloni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function () {
        	alert("Uspesno uklonjen");
             window.location.href="listaMenadzera.html";                           
           
        },
        error: function (data) {
        	alert("Bioskop ne sme ostati bez menadzera, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});