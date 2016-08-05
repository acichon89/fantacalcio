<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div class="page-header">
    <h1>Register</h1>
</div>
	<span id="test">Hello</span>
    <!-- Login form -->
    <div class="panel panel-default">
        <div class="panel-body">
            <!-- Specifies action and HTTP method -->
            <form action="${pageContext.request.contextPath}/user" method="POST" role="form">
            	<input type="hidden" name="scope" value="email, public_profile, user_friends" />
                <!-- Add CSRF token -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div id="form-group-email" class="form-group col-lg-4">
                        <label class="control-label" for="user-email"><spring:message code="label.user.email"/>:</label>
                        <!-- Add username field to the login form -->
                        <input id="user-email" name="email" type="text" class="form-control"/>
                    </div>
                </div>
                
                <div class="row">
                    <div id="form-group-email" class="form-group col-lg-4">
                        <label class="control-label" for="user-email"><spring:message code="label.user.email"/>:</label>
                        <!-- Add username field to the login form -->
                        <input id="user-email" name="fullName" type="text" class="form-control"/>
                    </div>
                </div>
 
                <div class="row">
                    <div id="form-group-password" class="form-group col-lg-4">
                        <label class="control-label" for="user-password"><spring:message code="label.user.password"/>:</label>
                        <!-- Add password field to the login form -->
                        <input id="user-password" name="password" type="password" class="form-control"/>
                    </div>
                </div>
                 <div class="row">
                    <div id="form-group-password" class="form-group col-lg-4">
                        <label class="control-label" for="user-password"><spring:message code="label.user.password"/>:</label>
                        <!-- Add password field to the login form -->
                        <input id="user-password" name="confirmedPassword" type="password" class="form-control"/>
                    </div>
                </div>
                <input type="submit" value="Register" />
            </form>
        </div>
    </div>
</body>
</html>