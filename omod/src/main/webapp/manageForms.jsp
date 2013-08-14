<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/font-awesome/css/font-awesome.min.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/animate/animate.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/bootstrap/css/bootstrap.min.css"/>
<openmrs:htmlInclude file="/moduleResources/muzimaforms/styles/custom/custom.css"/>

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
<openmrs:htmlInclude file="/moduleResources/muzimaforms/js/custom/directives/fileUpload.js"/>

<div class="bootstrap-scope" ng-app="muzimaforms">
<!--TODO : text styles if put in css file are overridden by something else. Need to figure it out-->
    <div class="navbar navbar-inverse navbar-custom">
        <div>
            <a class="navbar-brand" href="#/list/forms" style="color: #ffffff; font-size: 20px">Muzima Forms</a>

            <ul class="nav navbar-nav pull-right">
                <li>
                    <button type="button" class="btn btn-success dropdown-toggle navbar-btn" data-toggle="dropdown">
                        Import <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#/list/xforms"
                               style="color: #323232 ;text-decoration: none">XForms Module</a></li>
                        <li class="divider"></li>
                        <li><a style="color: #323232 ;text-decoration: none">Choose html form</a></li>
                        <li><a href="#/import/xforms" style="color: #323232 ;text-decoration: none">Choose xform</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="row-fluid">
        <div class="col-lg-12">
            <div ng-view></div>
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

