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
								<span title="${homeworks.avg} entregas promedio por tarea">
									${homeworks.submissions}
								</span><span title="${homeworks.total} tareas en total" class="small">/${homeworks.total}</span>
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
								<span
									title="${assignments.avg} entregas promedio por trabajo práctico">
									${assignments.submissions}
								</span><span title="${assignments.total} trabajos prácticos en total"
									class="small">/${assignments.total}</span>
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
			$('#assignments-chart').highcharts({
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
					categories : ${assignments.bars[0].collect {"'$it'"}}
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
					data : ${assignments.bars[1]},
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
				xAxis : {
					categories : ${homeworks.bars[0]}
				},
				yAxis: {
	                min: 0,
	                max: 4,
	                title: {
	                    text: null
	                },
	                stackLabels: {
	                    enabled: true,
	                    style: {
	                        fontWeight: 'bold',
	                        color: 'gray'
	                    }
	                }
	            },
	            legend: {
	                align: 'right',
	                x: -100,
	                verticalAlign: 'top',
	                y: 10,
	                floating: true,
	                backgroundColor: 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            },
				tooltip : {
					enabled : false
				},
				plotOptions: {
	                column: {
	                    stacking: 'normal',
	                    dataLabels: {
	                        enabled: true,
	                        color: 'white'
	                    }
	                }
	            },
				series : [ {
						name : '3pts',
						data : ${homeworks.bars[3]},
						color: '#737426'
					}, {
						name : '2pts',
						data : ${homeworks.bars[2]},
						color: '#d0b273'
					}, {
						name : '1pt',
						data : ${homeworks.bars[1]},
						color: '#af673a'
					}
				]
			});
		});
	</script>

</body>
</html>