
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">

.bor{
	border:15px dotted darkblue;
	background-color: lightblue;
	width: 100%;
	
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
<body>
	<div class="bor" align="center" style="position: relative;">
		<h1> VOCALOID Search <br></h1>
		<form action='${requestUri}' method='get'>

			<h4>Song's Name<br>
				<input type="text" id="name" name="name"></h4>
			<h4>Singer<br>
				<input type="text" id="Singer" name="Singer"></h4>	
			<br>
  
			<br>

			<input type='submit' value='get result ' />
			<br>
		</form>
		<br>
	</div>
</body>
</html>