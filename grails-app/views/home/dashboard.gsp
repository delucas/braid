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
				<li class="active"><a href="#homeworks" data-toggle="tab">Entregas</a></li>
				<li><a href="#course" data-toggle="tab">Cursada</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="homeworks">

					<div class="row-fluid">

						<div class="span4 dashboard-panel">
							<div class="dashboard-title">Entrega de tareas</div>
							<div class="dashboard-main-data">
								<span title="13.75 entregas promedio por tarea">110</span><span
									title="8 tareas en total" class="small">/8</span>
							</div>
						</div>

						<div class="span8">
							<div id="homeworks-chart"
								style="width: 640px; height: 200px; margin: 0 auto"></div>
						</div>

					</div>

					<div class="row-fluid">

						<div class="span8">
							<div id="assignments-chart"
								style="width: 640px; height: 200px; margin: 0 auto"></div>
						</div>

						<div class="span4 dashboard-panel">
							<div class="dashboard-title">Entrega de trabajos prácticos
							</div>
							<div class="dashboard-main-data">
								<span title="166 entregas promedio por trabajo práctico">332</span><span
									title="2 trabajos prácticos en total" class="small">/2</span>
							</div>
						</div>

					</div>

				</div>
				<div class="tab-pane" id="course">

					<div class="alert alert-info">Próximamente dashboard de
						Cursada</div>

				</div>
			</div>
		</div>

	</div>

	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script>
		$(function() {
			$('#assignments-chart').highcharts(
					{
						chart : {
							type : 'column'
						},
						title : {
							text : null
						},
						legend : {
							enabled : false
						},
						xAxis : {
							categories : [
									'Piedra, papel, tijera, lagarto, Spock',
									'Cuentas bancarias' ]
						},
						yAxis : {
							title : {
								text : null
							}
						},
						tooltip : {
							enabled : false
						},
						plotOptions : {
							line : {
								dataLabels : {
									enabled : true
								},
								enableMouseTracking : false
							}
						},
						series : [ {
							name : null,
							data : [ 95, 237 ],
							dataLabels : {
								enabled : true,
								color : '#fff',
								y : 25,
								style : {
									fontSize : '13px',
									fontFamily : 'Verdana, sans-serif'
								}
							}
						} ]
					});
		});

		$(function() {
			$('#homeworks-chart').highcharts({
				chart : {
					type : 'column'
				},
				title : {
					text : null
				},
				legend : {
					enabled : false
				},
				xAxis : {
					categories : [ 1, 2, 3, 4, 5, 6, 7, 8 ]
				},
				yAxis : {
					title : {
						text : null
					}
				},
				tooltip : {
					enabled : false
				},
				plotOptions : {
					line : {
						dataLabels : {
							enabled : true
						},
						enableMouseTracking : false
					}
				},
				series : [ {
					name : null,
					data : [ 18, 17, 19, 19, 10, 13, 14, 0 ],
					dataLabels : {
						enabled : true,
						color : '#fff',
						y : 25,
						style : {
							fontSize : '13px',
							fontFamily : 'Verdana, sans-serif'
						}
					}
				} ]
			});
		});
	</script>

</body>
</html>