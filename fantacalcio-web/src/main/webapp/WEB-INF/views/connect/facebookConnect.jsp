<h3>Connect to Facebook</h3>

<form action="${pageContext.request.contextPath}/connect/facebook" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect Spring Social Showcase with your Facebook account.</p>
	</div>
	<p><button type="submit"><img src="<c:url value="/resources/social/facebook/connect_light_medium_short.gif" />"/></button></p>
	<label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about Spring Social Showcase on your Facebook wall</label>
</form>
