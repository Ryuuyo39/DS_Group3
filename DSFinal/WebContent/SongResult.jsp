
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
<body>
	<div class="bor" align="center">
	<form action='${requestUri}' method='get'>
		<h1> Jpop & VOCALOID Search <br></h1>
		<div align="left" style="position: relative; left: 100px;" >
		Song's Name: <%=request.getParameter("name")%> <br>
		Singer: <%=request.getParameter("Singer")%> <br>
		Producer: <%=request.getParameter("Producer")%> <br>
		<br>
		Search derection<br>
		MV: <%=request.getParameter("MV")%> <br>
 		Lyrics: <%=request.getParameter("Lyrics")%> <br>
 		Background: <%=request.getParameter("Background")%> <br>
	
			<h3>Search Results:<br></h3>
			<h5 id="disp">
				<a href="https://www.youtube.com/">YouTube</a><br>	
				<a href="https://utaten.com/">UtaTen</a><br>
			</h5>
			<br>
		</div>
		<button type="button"value="next result" onclick="output()">next </button> 

		<script>
		function output(){

			var name='new result';
			var link='https://www.joysound.com/web/';
			
			var resultS=name;
			var msg=document.createElement('a');
			var msg2=document.createElement('br');
			msg.textContent=resultS;
			msg.href=link;
			document.getElementById('disp').appendChild(msg);
			document.getElementById('disp').appendChild(msg2);
		}
	</script>
	</form>
	</div>
</body>
</html>