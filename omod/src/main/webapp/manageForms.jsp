<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>


<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular.min.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular-resource.js"/>
<%--<openmrs:htmlInclude file="/moduleResources/html5forms/styles/bootstrap/css/bootstrap.css"/>--%>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/flatui/bootstrap/css/bootstrap.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/flatui/css/flat-ui.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/ui-bootstrap-0.3.0.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/font-awesome/css/font-awesome.min.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/custom/custom.css"/>

<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/app.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/controller.js"/>

<h2><spring:message code="Manage Forms"/></h2>

<div class="bootstrap-scope" ng-app="html5forms">

    <div ng-view></div>

</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

