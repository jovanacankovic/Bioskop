$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/filmovi",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS:",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['id']+"</td>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['opis']+"</td>";
				row+="<td>"+data[i]['zanr']+"</td>";
				row+="<td>"+data[i]['trajanje']+"</td>";
				row+="<td>"+data[i]['ocena']+"</td>";
				
				 var btn = "<button class='btnSeeMore btn btn-primary' id = " + data[i]['id'] + ">Projekcije</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);
				 $('#tabela1').hide();

			}
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});

$(document).on('click', '.sortOcena', function () {            
    var employeesDiv = $("#table");                      
    employeesDiv.hide(); 
    

   
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/filmovi/sortOcena", 
        dataType: "json",
        success: function (data) {
	
        	 $('#tabela').empty();
        	for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['id']+"</td>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['opis']+"</td>";
				row+="<td>"+data[i]['zanr']+"</td>";
				row+="<td>"+data[i]['trajanje']+"</td>";
				row+="<td>"+data[i]['ocena']+"</td>";
				
				 var btn = "<button class='btnSeeMore btn btn-primary' id = " + data[i]['id'] + ">Projekcije</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
				
	             $('#tabela').append(row);
			
			
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

$(document).on('click', '.sortNaziv', function () {            
    var employeesDiv = $("#table");                    
    employeesDiv.hide();  
	

   
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/filmovi/sortNaziv",  
        dataType: "json",
        success: function (data) {
        		            $('#tabela').empty();
        	for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['id']+"</td>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['opis']+"</td>";
				row+="<td>"+data[i]['zanr']+"</td>";
				row+="<td>"+data[i]['trajanje']+"</td>";
				row+="<td>"+data[i]['ocena']+"</td>";
				
				 var btn = "<button class='btnSeeMore btn btn-primary' id = " + data[i]['id'] + ">Projekcije</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	            
	             $('#tabela').append(row);
			
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

$(document).on('click', '.sortTrajanje', function () {            
    var employeesDiv = $("#table");                      
    employeesDiv.hide();       
   	
   
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/filmovi/sortTrajanje",  
        dataType: "json",
        success: function (data) {
        	
	            $('#tabela').empty();
        	for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['id']+"</td>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['opis']+"</td>";
				row+="<td>"+data[i]['zanr']+"</td>";
				row+="<td>"+data[i]['trajanje']+"</td>";
				row+="<td>"+data[i]['ocena']+"</td>";
				
				 var btn = "<button class='btnSeeMore btn btn-primary' id = " + data[i]['id'] + ">Projekcije</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);
			
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

$(document).on('click', '.btnSeeMore', function () {           
	
	$("#tabela").hide();
	$("#tabela1").show();
	$.ajax({
        type: "GET",
        url: "http://localhost:8080/api/filmovi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
        		
        		
				var row="<tr>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vremePocetka']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['oznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnRezervisi btn btn-primary' id = " + data[i]['id'] + ">Rezerviši</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	              
	              
        	}
        	
        	
                                  
           
        },
        error: function (data) {
        	alert("Za izabrani film jos uvijek nema projekcija");
        	window.location.href="filmovi.html";
            console.log("ERROR : ", data);
        }
    });
});



$(document).on('click', '.btnRezervisi', function () {            
	
        $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/filmovi/rezervisi/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function (data) {
        	
        	
        		//var red="<p value="+data['id']+"></p>";
        		
        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
	              $('#filmic').append(red);
	              $("#kartica1").removeClass("d-none").show();
	              
        	
        	 //window.location.href="index.html";
        	
                                  
           
        },
        error: function (data) {
        	alert("Neuspjesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '#rezervacija', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	var id=$("#podatak").val();
   
	var newKorisnikJSON=formToJSON1(korisnickoIme,lozinka,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/filmovi/rezervacija",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Uspesno");
			window.location.href="filmovi.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unietim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mesta. Pokusajte opet!!");
			window.location.href="filmovi.html";
        }
    });
});

function formToJSON1(korisnickoIme,lozinka,id){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka,
		"id":id
		
	});
}
