<%@ page contentType="text/html"%>
<h1>
	${solution.assignment.title}
</h1>
<p>
	Apreciamos tu esfuerzo al resolver el trabajo. Es por ello que te
	acercamos la respuesta tan pronto como la tenemos disponible. Obtuviste
	un puntaje de <strong>
		${solution.score}
	</strong> sobre 10 puntos posibles. Ha sido un gran esfuerzo. ¡Bien hecho!
</p>

<g:if test="${solution.score}">
	<p>
		<em>Aún podés mejorar esta calificación si querés. Podés volver a
			enviar tu tarea para que la corrijamos.</em>
	</p>
	<p
		style="background-color: #f5fff5; padding: 1em; border-radius: 0.5em;">
		<strong>Protip:</strong> ¡podés utilizar las correcciones para mejorar
		tu calificación!
	</p>
</g:if>

<div
	style="background-color: #f5f5f5; padding: 1em; border-radius: 0.5em;">
	<h2>Informe de la herramienta de corrección</h2>

	<pre>
		${solution.feedback}
	</pre>
</div>

<p>
	El equipo de <strong>Braid</strong>
</p>