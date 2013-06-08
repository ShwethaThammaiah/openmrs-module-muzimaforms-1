<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>


<openmrs:htmlInclude file="/moduleResources/html5forms/styles/bootstrap/css/bootstrap.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/custom/custom.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/font-awesome/css/font-awesome.min.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular.min.js"/>

<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/controller.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/app.js"/>

<h2><spring:message code="Manage Forms"/></h2>

<div class="bootstrap-scope" ng-app="html5forms">

    <div class="row-fluid" ng-controller="FormCtrl">
        <div class="span8" data-ng-include data-src="'../../moduleResources/html5forms/partials/form-preview.html'"></div>
        <div class="span4" data-ng-include data-src="'../../moduleResources/html5forms/partials/forms-list.html'"></div>
    </div>

    <%--<div ng-view></div>--%>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

