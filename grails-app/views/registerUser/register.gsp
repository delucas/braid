<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Register new user</title>
	</head>

	<body>
        <div class="row-fluid">

            <section id="main" class="span8">

                <div class="page-header">
                    <h1>Register new user</h1>
                </div>

                <g:form controller="registerUser" action="save">
                    <div class="control-group">
                     User: ${user?.username}       
                     Email: <g:textField name="email" value="${user?.email}"/>
                    </div>

                    <input type="hidden" name="oauthProvider" value="${user?.oauthProvider}"/>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>

                </g:form>

            </section>

        </div>

	</body>
</html>