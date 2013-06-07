<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>


<div class="bootstrap-scope" ng-app>


    <openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular.min.js"/>
    <openmrs:htmlInclude file="/moduleResources/html5forms/js/custom/controller.js"/>
    <openmrs:htmlInclude file="/moduleResources/html5forms/styles/bootstrap/css/bootstrap.css"/>
    <openmrs:htmlInclude file="/moduleResources/html5forms/styles/font-awesome/css/font-awesome.min.css"/>

    <h2><spring:message code="Manage Forms"/></h2>

    <div class="row row-fluid" ng-controller="FormCtrl">
        <div class="span8">
        </div>
        <div class="span4">
            <table class="table table-bordered table-hover">
                <tr ng-repeat="form in forms">
                    <td>
                        <input type="checkbox" ng-model="form.selected">
                    </td>
                    <td>
                        <div>
                            <strong>{{form.name}}</strong>
                        </div>
                        <div>
                            <small>{{form.description}}</small>
                        </div>
                        <div>
                            <span style="margin-right: 2px" class="label" ng-repeat="tag in form.tags">{{tag}}</span>
                        </div>
                    </td>
                    <td>
                        <a class="btn" href="#">
                            <i class="icon-cog"></i></a>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div>

        <%--<form ng-submit="addForm()">--%>
        <%--<input type="text" ng-model="formName"--%>
        <%--placeholder="Form Name">--%>
        <%--<input class="btn-primary" type="submit" value="Add Form">--%>
        <%--</form>--%>

    </div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

