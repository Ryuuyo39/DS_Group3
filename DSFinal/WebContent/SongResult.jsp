
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
		width: 80%;
		position:relative;
		left: 10%;
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
		<h1>VOCALOID Search <br></h1>
		<div align="left" style="position: relative;  ">
		<h3>Song's Name: <%=request.getParameter("name")%> <br>
		Singer: <%=request.getParameter("Singer")%> <br>
		</h3>
		</div>
		<div class="borP" align="left">
			<h3>Search Results:<br></h3>
			<h3 id="disp">
				
		<%
		String[][] orderList = (String[][]) request.getAttribute("query");
		for (int i = 0; i < orderList.length; i++) {
			String s=orderList[i][1];
		%>
		<a href='<%=s%>'><%=orderList[i][0]%> </a><br> 
		<%}%>
			</h3>
			<br>
		</div>
	</div>
</body>
</html>