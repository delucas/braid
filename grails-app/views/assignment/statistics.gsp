<html>
<head>
	<title>trabajo práctico: ${assignment.title}</title>
	<meta name="layout" content="main">
	<parameter name="assignments" value="active" />

	<r:require modules="bootstrap-modal" />
</head>

<body>

	<div class="span12">

		<legend>
			${assignment.title}
		</legend>

		<div id="assignment_div" style="width: 900px; height: 500px;"></div>

	</div>

	<script src="http://code.highcharts.com/highcharts.js"></script>
	<script src="http://code.highcharts.com/modules/exporting.js"></script>
    <script type="text/javascript">

	var graph = ${assignmentGraph}

	$.each(graph, function(i,v) {
		$.each(v.data, function(j,w){
			p = new Date(w[0])//.getTime()
			//w[0] = Date.UTC(p.getFullYear(),p.getMonth(),p.getDay(), p.getHours(),p.getMinutes(),p.getSeconds())
			w[0] = p
		})
		})

    $(function () {
        $('#assignment_div').highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: '${assignment.title}'
            },
            subtitle: {
                text: 'evolución de las notas a lo largo del tiempo'
            },
            xAxis: {
                type: 'Tiempo',
                dateTimeLabelFormats: { // don't display the dummy year
                    month: '%e. %b',
                    year: '%b'
                }
            },
            yAxis: {
                title: {
                    text: 'Nota'
                },
                min: 0, max: 10
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y;
                }
            },

            series: graph
        });
    });


    </script>

</body>
</html>