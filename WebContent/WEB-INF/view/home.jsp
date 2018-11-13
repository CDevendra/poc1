
<!DOCTYPE HTML>
<html>
  <head>
    <title>Spring MVC - Ajax</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
    	body { background-color: #eee; font: helvetica; }
    	#container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
    	.green { font-weight: bold; color: green; }
    	.message { margin-bottom: 10px; }
    	label { width:70px; display:inline-block;}
    	.hide { display: none; }
    	.error { color: red; font-size: 0.8em; }
    </style>
  </head>
  <body>
	
	<div id="container">
	
		<%-- <h1>Person Page</h1>
		<p>This page demonstrates Spring MVC's powerful Ajax functionality. Retrieve a
		random person, retrieve a person by ID, or save a new person, all without page reload.
		</p>
		
		<h2>Random Person Generator</h2>
		<input type="submit" id="randomPerson" value="Get Random Person" /><br/><br/>
		<div id="personResponse"> </div>
		
		<hr/>
		
		<h2>Get By ID</h2>
		<form id="idForm">
			<div class="error hide" id="idError">Please enter a valid ID in range 0-3</div>
			<label for="personId">ID (0-3): </label><input name="id" id="personId" value="0" type="number" />
			<input type="submit" value="Get Person By ID" /> <br /><br/>
			<div id="personIdResponse"> </div>
		</form>
		
		<hr/> --%>
		
		<h2>Submit new Person</h2>
		<form id="newPersonForm">
			<label for="nameInput">Name: </label>
			<input type="text" name="name" id="nameInput" value='d'/>
			<br/>
			
			<label for="ageInput">Price: </label>
			<input type="text" name="price" id="priceInput" value='1' />
			<br/>
			
			<label for="ageInput">Description: </label>
			<input type="text" name="desc" id="descInput" value='d'/>
			<br/><br />
			
			<div id="path">
				<span id="result"></span>
			</div>
			
			<input type="submit" value="Save Person" /><br/><br/>
			<div id="personFormResponse" class="green"> </div>			
		</form>
	
        <h3>Upload Single File</h3>
        <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
		    <input type="text" name="extraField"/><br/><br/>
		    <input type="file" name="file"/><br/><br/>
		    <input type="file" name="file"/><br/><br/>
		    <input type="submit" value="Submit" id="btnSubmit"/>
		</form>
		
		<h1>Ajax Post Result</h1>
		<pre>
		    <span id="result"></span>
		</pre>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			$('#newPersonForm').submit(function(e) {
				debugger
				 var obj = {};
				 obj.productName = $("#nameInput").val();
				 obj.productPrice = $("#priceInput").val();
				 obj.productDesc = $("#descInput").val();
				 obj.productImagePath = $("#path span").text();
				 
				 var settings = {
						  "async": true,
						  "crossDomain": true,
						  "url": "/product/saveproduct",
						  "method": "POST",
						  "headers": {
						    "content-type": "application/json",
						    "cache-control": "no-cache",
						    "postman-token": "c5422411-11d7-f595-7d1f-487c581bbfd9"
						  },
						  "processData": false,
						  "data":JSON.stringify(obj)
				 }
				$.ajax(settings).done(function (response) {
				  console.log(response);
				});
				
			});
			
			
			$("#btnSubmit").click(function (event) {

		        //stop submit the form, we will post it manually.
		        event.preventDefault();

		        fire_ajax_submit();

		    });
			function fire_ajax_submit() {
				// Get form
			    var form = $('#fileUploadForm')[0];

			    var data = new FormData(form);

			    data.append("CustomField", "This is some extra data, testing");

			    $("#btnSubmit").prop("disabled", true);

			    $.ajax({
			        type: "POST",
			        enctype: 'multipart/form-data',
			        url: "/product/upload",
			        data: data,
			        //http://api.jquery.com/jQuery.ajax/
			        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
			        processData: false, //prevent jQuery from automatically transforming the data into a query string
			        contentType: false,
			        cache: false,
			        timeout: 600000,
			        success: function (data) {
			            $("#result").text(data);
			            console.log("SUCCESS : ", data);
			            $("#btnSubmit").prop("disabled", false);
			        },
			        error: function (e) {
			            $("#result").text(e.responseText);
			            console.log("ERROR : ", e);
			            $("#btnSubmit").prop("disabled", false);
			        }
			    });
			}		
			
			
			
		});
		
			
		
		
	</script>
	
  </body>
</html>