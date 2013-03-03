Seleccionar el usuario para logearse

<g:form action="singingUser">

	<g:select name="id" from="${users}"
	optionKey="id"
	optionValue="username"/>
	<g:submitButton name="Ingresar"/>

</g:form>


o <g:link action="register">registrar</g:link>