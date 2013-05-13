<html>

<head>
<title>dashboard</title>

<meta name="layout" content="main">
<parameter name="dashboard" value="active" />
</head>

<body>

	<legend> Dashboard </legend>

	<div class="row-fluid" style="overflow-y: hidden;">

		<div class="tabbable">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#homeworks" data-toggle="tab">Tareas</a></li>
				<li><a href="#assignments" data-toggle="tab">Trabajos prácticos</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="homeworks">

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
										<td><g:formatNumber number="${bubble[3]}" type="number"
												minFractionDigits="2" maxFractionDigits="2"
												roundingMode="HALF_DOWN" /></td>
									</tr>
								</g:each>
							</tbody>
						</table>
					</div>


				</div>
				<div class="tab-pane" id="assignments">
					<div id="assignment_div" style="width: 900px; height: 500px;"></div>
				</div>
			</div>
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
        <script type="text/javascript">
      google.load('visualization', '1', {'packages':['motionchart']});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Fruit');
        data.addColumn('date', 'Date');
        data.addColumn('number', 'Sales');
        data.addColumn('number', 'Expenses');
        data.addRows([
          ['Apples',  new Date (1988,0,1), 1000, 300],
          ['Oranges', new Date (1988,0,1), 1150, 200],
          ['Bananas', new Date (1988,0,1), 300,  250],
          ['Apples',  new Date (1989,6,1), 1200, 400],
          ['Oranges', new Date (1989,6,1), 750,  150],
          ['Bananas', new Date (1989,6,1), 788,  617]
        ]);
        var chart = new google.visualization.MotionChart(document.getElementById('assignment_div'));
        chart.draw(data, {width: 600, height:300});
      }
    </script>
</body>
</html>