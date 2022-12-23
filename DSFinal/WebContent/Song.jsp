
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">

.bor{
	border:15px dotted darkblue;
	background-color: lightblue;
	width: 40%;
	margin-left:30% ;
	color: navy;
}
select{
	width: 33%;
	background-color: lightcyan;
}
button{
	background-color:LightCyan;
	width: 33%;
	border:2px;
	border-style: double;
	border-color: darkcyan;
}
button:hover{
	background-color: Cyan;
	color:darkcyan;
}
.borP{
	border-width:2px;
	border-style:dashed;
	border-color:darkcyan;
	width: 33%;
}
.borH4{
	border: 2px inset lightcyan;
	font-size: 12px;
	font:monospace;
}

</style>
</head>
+<body>
	<div class="bor" align="center" style="position: relative;">
		<h1> Jpop & VOCALOID Search <br></h1>
		<form action='${requestUri}' method='get'>

			<h4>Song's Name<br>
				<input type="text" id="name" name="name"></h4>
			<h4>Singer<br>
				<input type="text" id="Singer" name="Singer"></h4>	
			<h4>Producer<br>
				<input type="text" id="Producer" name="Producer"></h4>

			<p>
				<h4>Search derection ?<br>
				<div align="left" style="position: relative; left: 200px;">
					<input type="checkbox" id="derection" name="derection" checked>
			      		<label for="mv">MV</label><br>
			      	<input type="checkbox" id="derection" name="derection" >
			      		<label for="lyrics">Lyrics</label><br>
			      	<input type="checkbox" id="derection" name="derection" >
			      		<label for="background">Background</label><br>
			    </div>  </h4>		
			</p>
			<input type='submit' value='get result ' />
			</button> 
			<br>
			<h4 id="disp"> </h4>
		</form>
		<br>
		<script>
		function output(){

			var name=document.getElementById('name').value;
			var singer=document.getElementById('Singer').value;
			var producer=document.getElementById('Producer').value;
			
			var resultS='The result of['+name+', '+singer+', '+producer+' ]are as below:';
			var msg=document.createElement('p');
			msg.textContent=resultS;
			document.getElementById('disp').appendChild(msg);
		}
	</script>
	</div>
</body>
</html>