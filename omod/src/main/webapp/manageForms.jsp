<%@ include file="/WEB-INF/template/include.jsp" %>

<%@ include file="/WEB-INF/template/header.jsp" %>

<h2><spring:message code="Manage Forms"/></h2>

<div ng-app>
    <openmrs:htmlInclude file="/moduleResources/html5forms/angular.min.js"/>

    <div>
        <label>Name:</label>
        <input type="text" ng-model="yourName" placeholder="Enter a name here">
        <hr>
        <h1>Hello {{yourName}}!</h1>
    </div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>
