<%@ page contentType="text/html"%>
<div style="background-color: #ebebeb; padding: 1em 4em;">
	<div style="color: white; background-color: #27ae60; padding: 1em;">
		<div style="overflow: hidden;">
			<h1
				style="float: left; line-height: 0.2em; display: inline; margin-left: 0.5em; text-shadow: 1px 1px 0 #000000;">braid</h1>
		</div>
	</div>
	<div style="background-color: white; padding: 1em;">
		<h1>
			${homework.title}
		</h1>
		<p>
			Apreciamos tu esfuerzo al resolver la tarea. Es por ello que te
			acercamos la respuesta tan pronto como la tenemos disponible.
			Obtuviste un puntaje de <strong> ${feedback.score}
			</strong> sobre 3 puntos posibles. ¡Buen trabajo!
		</p>

		<div
			style="background-color: #f5f5f5; padding: 1em; border-radius: 0.5em; margin-bottom: 1em;">
			<h2>Consigna</h2>

			<p>${homework.wording}</p>
		</div>

		<div
			style="background-color: #f5f5f5; padding: 1em; border-radius: 0.5em; margin-bottom: 1em;">
			<h2>Tu respuesta</h2>

			<p>${solution.text}</p>
		</div>

		<div
			style="background-color: #f5f5f5; padding: 1em; border-radius: 0.5em; margin-bottom: 1em;">
			<h2>Feedback</h2>

			<p>${feedback.text}</p>
			<p><em>— ${reviewer.name}</em></p>
		</div>

		<p>
			El equipo de <strong>Braid</strong>
		</p>
	</div>
</div>