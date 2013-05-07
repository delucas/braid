<html>

<head>
<title>dashboard</title>

<meta name="layout" content="main">
<parameter name="dashboard" value="active" />
</head>

<body>

	<legend> Dashboard </legend>

	<div class="row-fluid" style="overflow-y: hidden;">

		<div id="homework_chart" class="span6" style="height: 400px;"></div>

		<div class="span6">

			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Nombre</th>
						<th><abbr title="Puntos totales">PT</abbr></th>
						<th><abbr title="Tareas resueltas">TR</abbr></th>
						<th><abbr title="Promedio">Pr</abbr></th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${homeworkGraph}" var="bubble">
						<tr>
							<td>
								${bubble[0]}
							</td>
							<td>
								${bubble[2]}
							</td>
							<td>
								${bubble[1]}
							</td>
							<td>
								<g:formatNumber number="${bubble[3]}" type="number" minFractionDigits="2" maxFractionDigits="2" roundingMode="HALF_DOWN" />
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
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
			['${bubble[0]}', ${bubble[1]}, ${bubble[2]}, ${bubble[3]}],</g:each>
			]);
		
			var options = {
				title: 'Correlación entre tareas entregadas, puntos obtenidos y promedio por alumno',
				hAxis: {title: 'Tareas entregadas', minValue: 0, maxValue: ${homeworkGraph.max { it[1] }[1]} + 1},
				vAxis: {title: 'Puntos totales', minValue: 0, maxValue: ${homeworkGraph.max { it[2] }[2]} + 1},
				bubble: {textStyle: {fontSize: 11}},
				legend: {position: 'none'},
				sortBubblesBySize: true
			};
			
			var chart = new google.visualization.BubbleChart(document.getElementById('homework_chart'));
			chart.draw(data, options);
		}

    </script>
</body>
</html>