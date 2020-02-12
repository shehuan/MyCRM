<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myFn" uri="http://www.shh.com/jsp/jstl/functions" %>
<html>
<head>
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script src="/js/views/employee.js"></script>
</head>
<body>
<table id="employee_datagrid"></table>
<div id="employee_toolbar">
    <div>
        <c:if test="${myFn:checkPermission('com.shh.crm.controller.EmployeeController:save')}">
            <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        </c:if>
        <c:if test="${myFn:checkPermission('com.shh.crm.controller.EmployeeController:edit')}">
            <a id="employee_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        </c:if>
        <c:if test="${myFn:checkPermission('com.shh.crm.controller.EmployeeController:delete')}">
            <a id="employee_delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">离职</a>
        </c:if>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        关键字查询：<input name="keyword"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="search">搜索</a>
    </div>
</div>
<div id="employee_dialog">
    <form id="employee_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>账号</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>真实姓名</td>
                <td><input type="text" name="realname"/></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="phone"/></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>部门</td>
                <td><input type="text" name="department.id" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/department/list'"></td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td><input type="text" name="createTime" class="easyui-datebox"/></td>
            </tr>
            <tr>
                <td>角色</td>
                <td><input id="employee_roles" type="text" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/role/queryAllRole', multiple:true"></td>
            </tr>
        </table>
    </form>
</div>
<div id="employee_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
