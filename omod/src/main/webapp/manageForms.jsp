<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>


<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/jquery/jquery.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/flatui/bootstrap/css/bootstrap.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/flatui/js/bootstrap.min.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular-resource.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular-sanitize.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/ui-bootstrap-0.3.0.js"/>
<%--<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/bootstrap/css/bootstrap.css"/>--%>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular-strap.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/flatui/css/flat-ui.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/font-awesome/css/font-awesome.min.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/animate/animate.css"/>

<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/custom/custom.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/underscore/underscore-min.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/filters.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/app.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/directives/htmlformBind.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/FormsController.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/FormController.js"/>

<h2><spring:message code="Manage Forms"/></h2>

<div class="bootstrap-scope" ng-app="muzimaforms">

    <div ng-view></div>

</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

