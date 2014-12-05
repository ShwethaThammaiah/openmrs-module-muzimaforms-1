<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/font-awesome/css/font-awesome.min.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/animate/animate.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/bootstrap/css/bootstrap.min.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/custom/custom.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/openmrs2.0/index.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/openmrs2.0/openmrs.css"/>


<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/jquery/jquery.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/underscore/underscore-min.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/bootstrap/js/bootstrap.min.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular-resource.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular-sanitize.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/ui-bootstrap-0.3.0.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/angular/angular-strap.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/filters.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/app.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/FormsController.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/ImportController.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/XFormsController.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/UpdateController.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/controllers/FormViewController.js"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/directives/fileUpload.js"/>

<div class="bootstrap-scope" ng-app="muzimaforms">

    <div class="row-fluid">
        <div class="col-lg-12">
            <div ng-view></div>
        </div>
    </div>

</div>

