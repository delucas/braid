<html>

<head>
	<title>dashboard</title>
	
	<meta name="layout" content="main">
	<parameter name="dashboard" value="active"/>
	
</head>

<body>
	
	<div class="span12">
		<legend> Dashboard </legend>

		<div class="span6">
			<div id="homework_chart" style="width: 100%; height: 400px;"></div>
		</div>

	</div>

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

    		google.load("visualization", "1", {packages:["corechart"]});
    	      google.setOnLoadCallback(drawChart);
    	      function drawChart() {
    	        var data = google.visualization.arrayToDataTable([
    	          ['Nombre', 'Tareas entregadas', 'Puntos totales', 'Promedio'],

					<g:each in="${homeworkGraph}" var="bubble">
					['${bubble[0]}', ${bubble[1]}, ${bubble[2]}, ${bubble[3]}],
					</g:each>

    	          
    	        ]);

    	        var options = {
    	          title: 'Correlación entre tareas entregadas, puntos obtenidos y promedio por alumno',
    	          hAxis: {title: 'Tareas entregadas'},
    	          vAxis: {title: 'Puntos totales'},
    	          bubble: {textStyle: {fontSize: 11}},
    	       	  sortBubblesBySize: true
    	        };

    	        var chart = new google.visualization.BubbleChart(document.getElementById('homework_chart'));
    	        chart.draw(data, options);
    	      }

    </script>
</body>
</html>