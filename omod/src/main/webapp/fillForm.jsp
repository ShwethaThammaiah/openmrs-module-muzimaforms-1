<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>


<openmrs:htmlInclude file="/moduleResources/html5forms/js/jquery/jquery.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/flatui/bootstrap/css/bootstrap.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/flatui/js/bootstrap.min.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular-resource.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular-sanitize.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/ui-bootstrap-0.3.0.js"/>
<%--<openmrs:htmlInclude file="/moduleResources/html5forms/styles/bootstrap/css/bootstrap.css"/>--%>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular-strap.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/flatui/css/flat-ui.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/font-awesome/css/font-awesome.min.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/styles/animate/animate.css"/>

<openmrs:htmlInclude file="/moduleResources/html5forms/styles/custom/custom.css"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/underscore/underscore-min.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/filters.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/app.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/directives/html5formBind.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/controllers/FormController.js"/>


<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/libraries-all-min.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/helpers.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/gui.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/storage_drishti.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/form.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/widgets.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/enketo/webform_drishti.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/util.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/id_factory.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/form_model_mapper.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/entity_relationship_loader.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/entity.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/entities.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/entity_definition.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/entity_definitions.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/relation_kind.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/entity_relationships.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/form_definition_loader.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/sql_query_builder.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/form_data_repository.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/form_submission_router.js"/>
<openmrs:htmlInclude file="/moduleResources/html5forms/js/form/ziggy/ziggy/src/form_data_controller.js">

<script type="text/javascript">
    console.log("Initialising enketo template.");
    window.onerror = function (m, u, l) {
        console.error("Javascript Error: , msg: {0}, url: {1}, line: {2}".format(m, u, l));
        return true;
    };

    var settings = {};
    settings['mapsDynamicAPIKey'] = 'AIzaSyDF5xYZfxN7r5SsNPGstjAeTzwa6dVU4Ik';
    settings['mapsStaticAPIKey'] = 'AIzaSyDF5xYZfxN7r5SsNPGstjAeTzwa6dVU4Ik';
    settings['supportEmail'] = '';
    settings['returnURL'] = '';

//    var modelStr = androidContext.getModel();
</script>


<h2><spring:message code="Fill a form"/></h2>

<div class="bootstrap-scope" ng-app="html5forms">

    <div ng-view></div>

</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

