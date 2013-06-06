
<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>


<h2><spring:message code="Manage Forms"/></h2>

<div ng-app>
    <openmrs:htmlInclude file="/moduleResources/html5forms/js/angular/angular.min.js"/>
    <openmrs:htmlInclude file="/moduleResources/html5forms/js/tableController.js"/>

    <div ng-controller="TableCtrl">

        <form ng-submit="addForm()">
            <input type="text" ng-model="formName"
                   placeholder="Form Name">
            <input class="btn-primary" type="submit" value="Add Form">
        </form>

        </table>

        <table border="1">
            <tr>
                <th>Form Name</th>
                <th>
                    <button ng-click="deleteForm()">
                        Delete
                    </button>
                </th>
            </tr>
            <tr ng-repeat="form in forms">
                <td>{{form.name}}</td>
                <td>
                    <input type="checkbox" ng-model="form.deleteThis">
                </td>
            </tr>
        </table>

    </div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

