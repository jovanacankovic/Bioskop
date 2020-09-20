$(document).ready(function(){
			$("#bioskopiAdmin").hide();
			
			
			
			var uloga = window.localStorage.getItem("uloga");
	if(uloga == "Admin"){
		$("#zaAdmina").show();
		$("#zaMenadzere").hide();
		$("#zaGledaoce").hide();
	}
	if(uloga == "Menadzer"){
		$("#zaMenadzere").show();
		$("#zaAdmina").hide();
		$("#zaGledaoce").hide();
	}
	if(uloga == "Gledalac"){
		$("#zaGledaoce").show();
		$("#zaAdmina").hide();
		$("#zaMenadzere").hide();
	}
			
			
			
			$(".korisnickoIme").append(window.localStorage.getItem("korisnickoIme"));
			$(".lozinka").append(window.localStorage.getItem("lozinka"));
			$(".ime").append(window.localStorage.getItem("ime"));
			$(".prezime").append(window.localStorage.getItem("prezime"));
			$(".kontaktTelefon").append(window.localStorage.getItem("kontaktTelefon"));
			$(".emailAdresa").append(window.localStorage.getItem("korisnickoIme"));
			$(".datumRodjenja").append(window.localStorage.getItem("datumRodjenja"));
			$(".uloga").append(window.localStorage.getItem("uloga"));
			
			
			
});

$(document).on('click', '.btnRezervisani', function () {            // kada je button (čija je class = btnSeeMore) kliknut
    $("#odgledaniFilmovi").hide();
    $("#oceneniFilmovi").hide();
    $("#ocenivanje").hide();
    $("#neocenjeniFilmovi").hide();
    $(".sakrij").empty();

    // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
    // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/gledalac-rezervisaneKarte/" + window.localStorage.getItem("id"),  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function (data) {
        
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vremePocetka']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['oznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnOtkazi btn btn-primary' value="+data[i]['gledalacId']+" id= " + data[i]['id']+ ">Otkaži</button>";
	              row += "<td>" + btn + "</td>"; 

	              var btn1 = "<button class='btnPotvrdi btn btn-primary' value="+data[i]['gledalacId']+" id= " + data[i]['id']+ ">Potvrdi kupovinu</button>";
		          row += "<td>" + btn1 + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#listaKarte").removeClass("d-none").show();
	              
        	}                          
           
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});


$(document).on('click', '.btnSvi', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	  $("#listaKarte").hide();
	  $("#ocenjeniFilmovi").hide();
	  $("#neocenjeniFilmovi").hide();
	  $("#ocenjivanje").hide();
	  $(".sakrij").empty();

    // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
    // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/gledalac-odgledaniFilmovi/" + window.localStorage.getItem("id"),  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['zanr']+"</td>";
        		row+="<td>"+data[i]['opis']+"</td>";
        		row+="<td>"+data[i]['trajanje']+"</td>";
        		row+="<td>"+data[i]['ocena']+"</td>";
        		
        		var btn = "<button class='btnOceni btn btn-primary' value="+data[i]['gledalacId']+"  id = " + data[i]['id'] + ">Oceni</button>";
             row += "<td>" + btn + "</td>"; 
             row+="</tr>";
        		
	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	              
	              $("#odgledaniFilmovi").removeClass("d-none").show();
	              
	             
        	}                          
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '.btnOceni', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:8080/api/gledalac-oceniFilm/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			//alert("Uspjesno ocijenjen film");
			var red="<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='podatakOcena' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
			
             $('#prikazi').append(red);
             $("#ocenjivanje").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="profil.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#oceniFilm', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnickoImeOcena").val();
	var lozinka=$("#lozinkaOcena").val();
	var ocena=$("#ocenaOcena").val();
	var id=$("#podatakOcena").val();
   
	var newKorisnikJSON=formToJSONOcena(korisnickoIme,lozinka,ocena,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/ocenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Uspesno");
			window.location.href="filmovi.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unijetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mjesta. Pokusajte opet!!");
			window.location.href="filmovi.html";
        }
    });
});

function formToJSONOcena(korisnickoIme,lozinka,ocena,id){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka,
		"ocena":ocena,
		"id":id
		
	});
	}
	
$(document).on('click', '.btnOcenjeni', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#neocenjeniFilmovi").hide();
	  $(".sakrij").empty();
	 

  // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
  // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
  $.ajax({
      type: "GET",
      url: "http://localhost:8080/api/gledalac-ocenjeniFilmovi/" + window.localStorage.getItem("id"),  // this.id je button id, a kao button id je postavljen id zaposlenog
      dataType: "json",
      success: function (data) {
     
      	for(i=0;i<data.length;i++){
      		var row="<tr class='sakrij'>";
      		row+="<td>"+data[i]['naziv']+"</td>";
      		row+="<td>"+data[i]['zanr']+"</td>";
      		row+="<td>"+data[i]['opis']+"</td>";
      		row+="<td>"+data[i]['trajanje']+"</td>";
      		row+="<td>"+data[i]['ocena']+"</td>";
      		
      		  
	              
	              $('#tabela2').append(row);
	              
	              $("#ocenjeniFilmovi").removeClass("d-none").show();
	              
      	}                          
         
      },
      error: function (data) {
      	alert("Neuspesno, pokusajte opet");
          console.log("ERROR : ", data);
      }
  });
});


$(document).on('click', '.btnNeocenjeni', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#ocenjeniFilmovi").hide();
	  $("#ocenjivanje").hide();
	  $(".sakrij").empty();

// nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
// tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
$.ajax({
    type: "GET",
    url: "http://localhost:8080/api/gledalac-neocenjeniFilmovi/" + window.localStorage.getItem("id"),  // this.id je button id, a kao button id je postavljen id zaposlenog
    dataType: "json",
    success: function (data) {
    	for(i=0;i<data.length;i++){
    		var row="<tr class='sakrij'>";
    		row+="<td>"+data[i]['naziv']+"</td>";
    		row+="<td>"+data[i]['zanr']+"</td>";
    		row+="<td>"+data[i]['opis']+"</td>";
    		row+="<td>"+data[i]['trajanje']+"</td>";
    		row+="<td>"+data[i]['ocena']+"</td>";
    		

   		 var btn = "<button class='btnOceni btn btn-primary' value="+data[i]['gledalacId']+"  id = " + data[i]['id'] + ">Oceni</button>";
             row += "<td>" + btn + "</td>"; 
             row+="</tr>";
             
	              
	              $('#tabela3').append(row);
	              
	              $("#neocenjeniFilmovi").removeClass("d-none").show();
	              
    	}                          
       
    },
    error: function (data) {
    	alert("Neuspesno, pokusajte opet");
        console.log("ERROR : ", data);
    }
});
});

$(document).on('click', '.btnOtkazi', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:8080/api/gledalac-otkaziRezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspesno uklonjena rezervacija");
		     window.location.href="profil.html";
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="profil.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnPotvrdi', function () {        

	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:8080/api/gledalac-potvrdiRezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspesno kupljena karta");
		     window.location.href="profil.html";
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="profil.html";
		    console.log("ERROR : ", data);
		    }
		});
});


//MENADZER


$(document).on('click', '.btnBioskopi', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#sale").hide();
	$("#repertoar").hide();
	$(".sakrij").empty();
	$("#Izmjena-sala").hide();
	  $("#Izmjena-repertoar").hide();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:8080/api/bioskopi/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data) {
			for(i=0;i<data.length;i++){
				var row="<tr class='sakrij'>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['adresa']+"</td>";
				row+="<td>"+data[i]['brojCentrale']+"</td>";
				row+="<td>"+data[i]['email']+"</td>";
		
				var btn = "<button class='btnSale btn btn-primary' id = " + data[i]['id'] + ">Sale</button>";
				var btn1 = "<button class='btnRepertoar btn btn-primary' id = " + data[i]['id'] + ">Repertoar</button>";
				row += "<td>" + btn + "</td>"; 
				row += "<td>" + btn1+ "</td>"; 
		        row+="</tr>";
		         
		              
		              $('#tabela4').append(row);
		              
		              $("#bioskopi").removeClass("d-none").show();
		              
			}                          
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnSale', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$("#repertoar").hide();
	$(".sakrij").empty();
	$("#Izmjena-sala").hide();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:8080/api/sale/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data) {
		
			for(i=0;i<data.length;i++){
				var row="<tr class='sakrij'>";
				row+="<td>"+data[i]['bioskop']+"</td>";
				row+="<td>"+data[i]['oznaka']+"</td>";
				row+="<td>"+data[i]['kapacitet']+"</td>";
				
				var btn = "<button class='btnIzmeniSalu btn btn-primary' id = " + data[i]['id'] + ">Izmeni</button>";
				var btn1 = "<button class='btnUkloniSalu btn btn-primary' id = " + data[i]['id'] + ">Ukloni</button>";
	             row += "<td>" + btn + "</td>"; 
	             row += "<td>" + btn1 + "</td>"; 
			
		
		        row+="</tr>";
		         
		        $('#tabela5').append(row);
				$("#sale").removeClass("d-none").show(); 
		             
		              
		             
		              
			} 
			var btn2 = "<a href='SalaAdd.html'><button class='btnDodajSalu btn btn-primary'>Dodaj novu salu</button></a>";
            
			row="<tr class='sakrij'><td colspan='5'>"+btn2+"</td></tr>";
			 $('#tabela5').append(row);
			$("#sale").removeClass("d-none").show();
			
		   
		},
		error: function (data) {
			alert("Nije pronadjena ni jedna sala,pokusajte opet!");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnUkloniSalu', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$("#repertoar").hide();
	$("#sale").hide();
	$(".sakrij").empty();
	$("#Izmjena-sala").hide();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:8080/api/saleUkloni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspesno uklonjena");
		              
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
		    console.log("ERROR : ", data);
		    }
		});
});




$(document).on('click', '.btnIzmeniSalu', function () {        
		$("#izmenaSala").empty();
		$("#Izmena-sala").hide();
	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:8080/api/sala-izmeni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
	
			
			var red="Kapacitet:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='number' class='form-control' id='kapacitet' placeholder='Kapacitet sale' value="+data['kapacitet']+" ></div>"
            
            red+="Oznaka sale:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='oznaka' placeholder='Oznaka sale' value="+data['oznaka']+"  ></div>"
            
            
			red+="<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='salaId' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
             $('#izmenaSala').append(red);
             $("#Izmena-sala").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="profil.html";
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '#izmeni1', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmjena-sala").hide();
	
	var kapacitet=$("#kapacitet").val();
	var oznaka=$("#oznaka").val();
	var idSale=$("#salaId").val();
   
	var newSalaJSON=formToJSON2(kapacitet,oznaka,idSale);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/sala/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newSalaJSON,
		success:function(data){
			alert("Uspesno");
			//window.location.href="login.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mesta. Pokusajte opet!!");
			window.location.href="profil.html";
        }
    });
});

function formToJSON2(kapacitet,oznaka,id){
	return JSON.stringify({
		"kapacitet":kapacitet,
		"oznaka":oznaka,
		"id":id	
	});
}


$(document).on('click', '.btnRepertoar', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$(".sakrij").empty();
	  $("#Izmena-repertoar").hide();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:8080/api/repertoar/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vremePocetka']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['oznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnIzmeniRepertoar btn btn-primary' id = " + data[i]['id'] + ">Izmeni</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela6').append(row);
	             
	              $("#repertoar").removeClass("d-none").show();
        	}       
			var btn2 = "<a href='rasporedAdd.html'><button class='btnDodajRepertoar btn btn-primary'>Dodaj novi repertoar</button></a>";
            
			row="<tr class='sakrij'><td colspan='8'>"+btn2+"</td></tr>";
			 $('#tabela6').append(row);
			$("#repertoar").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno,ovaj bioskop nema dostupnih projekcija");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnIzmeniRepertoar', function () {        
	//$("#repertoar").hide();
	  $('#izmenaRepertoara').empty();
	  $("#Izmena-repertoar").hide();
		 
	
	$.ajax({  //BioiskopController
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:8080/api/repertoar-izmeni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
	
			
			var red="Naziv filma:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='naziv' placeholder='Kapacitet sale' value="+data['naziv']+" ></div>"
            
            red+="Datum odrzavanja:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='date' class='form-control' id='datumOdrzavanja' placeholder='Datum odrzavanja' value="+data['datumOdrzavanja']+"  ></div>"
            
            red+="Vreme pocetka:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='vremePocetka' placeholder='Vreme pocetka' value="+data['vremePocetka']+"  ></div>"
            
            red+="Cena:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='number' class='form-control' id='cijena' placeholder='Cena' value="+data['cijena']+"  ></div>"
            
            red+="Sala:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='oznaka' placeholder='Oznaka sale' value="+data['oznaka']+"  ></div>"
            
            
			red+="<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control'  id='repertoarId' placeholder='Izabrati id' value="+data['id']+"  disabled='disabled'></div>"
             $('#izmenaRepertoara').append(red);
             $("#Izmena-repertoar").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="profil.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#izmeni2', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmena-repertoar").hide();
	
	var naziv=$("#naziv").val();
	var datum=$("#datumOdrzavanja").val();
	var vreme=$("#vremePocetka").val();
	var cena=$("#cena").val();
	var sala=$("#oznaka").val();
	var id=$("#repertoarId").val()
	
	

   
	var newRepertoarJSON=formToJSON3(naziv,datum,vreme,cena,sala,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/repertoar/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newRepertoarJSON,
		success:function(data){
			alert("Uspesno");
			window.location.href="profil.html";
			
			
		},
		error:function(data){
			alert("Greska! Uneli ste nepostojeci film ili salu. Pokusajte ponovo");
		
        }
    });
});

function formToJSON3(naziv,datum,vreme,cena,sala,id){
	return JSON.stringify({
		"naziv":naziv,
		"datumOdrzavanja":datum,
		"vrijemePocetka":vreme,
		"cena":cena,
		"oznaka":sala,
		"id":id
	});
}



//ZA ADMINISTRATORA
$(document).on('click', '.btnBioskopiAdmin', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	
	$.ajax({
		type:"GET",
		url:"http://localhost:8080/api/bioskopi",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS:",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['adresa']+"</td>";
				row+="<td>"+data[i]['brojCentrale']+"</td>";
				row+="<td>"+data[i]['email']+"</td>";
				
				 var btn = "<button class='btnUkloni btn btn-primary' id = " + data[i]['id'] + ">Ukloni</button>";
	              row += "<td>" + btn + "</td>"; 
	              var btn1 = "<button class='btnIzmeni btn btn-primary' id = " + data[i]['id'] + ">Izmeni</button>";
	              row += "<td>" + btn1 + "</td>"; 
	            
	              
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabelaA').append(row);
	             $("#bioskopiAdmin").removeClass("d-none").show();
	             

			}
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});


$(document).on('click', '.btnUkloni', function () {            // kada je button (čija je class = btnSeeMore) kliknut

    // nakon što korisnik klikne button See more možemo i samo da se prebacimo na employee.html
    // tada ajax poziv za dobavljanje jednog zaposlenog moze da bude u fajlu employee.js
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/bioskopi/ukloni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
           alert("Uspesno");
           window.location.href="profil.html";
           
        },
        error: function (data) {
        	alert("Greska");
            console.log("ERROR : ", data);
           
        }
    });
});




$(document).on('click', '.btnIzmeni', function () {        
	//$("#repertoar").hide();
	$("#izmenaBioskop").empty();
	
	$.ajax({  //BioiskopController
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:8080/api/bioskop-izmeni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
	
			
			var red="Naziv bioskopa:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='naziv' placeholder='Naziv bioskopa' value="+data['naziv']+" ></div>"
            
           
            red+="Adresa:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='adresa' placeholder='Adresa' value="+data['adresa']+"  ></div>"
            
            red+="Broj centrale:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='brojCentrale' placeholder='broj' value="+data['brojCentrale']+"  ></div>"
            
            red+="E mail:<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='email' class='form-control' id='email' placeholder='email' value="+data['email']+"  ></div>"
            
          console.log("MAIL",data['email']);
            
			red+="<div class='input-group form-group'><div class='input-group-prepend'></div>";
            red+="<input type='text' class='form-control' id='bioskopId' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
             $('#izmenaBioskop').append(red);
             $("#Izmena-bioskop").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="profil.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#izmeni', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmena-bioskop").hide();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var broj=$("#brojCentrale").val();
	var mail=$("#email").val()
	var id=$("#bioskopId").val();
	

   
	var newBioskopJSON=formToJSON3(naziv,adresa,broj,mail,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:8080/api/bioskop/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newBioskopJSON,
		success:function(data){
			alert("Uspesno");
			window.location.href="profil.html";
			
			
		},
		error:function(data){
			
			alert("Greska!");
			window.location.href="profil.html";
        }
    });
});

function formToJSON3(naziv,adresa,broj,mail,id){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"brojCentrale":broj,
		"email":mail,
		"id":id
		
		
	});
}
