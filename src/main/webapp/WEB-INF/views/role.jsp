<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <%@include file="common.jsp" %>
    <script src="/js/views/role.js"></script>
</head>
<body>
<table id="role_datagrid"></table>
<div id="role_toolbar">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
        <a id="role_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
        <a id="role_delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="delete">删除</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    </div>
    <div>
        关键字查询：<input name="keyword"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="search">搜索</a>
    </div>
</div>
<div id="role_dialog">
    <form id="role_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tr>
                <td>角色名称：<input type="text" name="name"/></td>
                <td>角色编号：<input type="text" name="sn"/></td>
            </tr>
            <tr>
                <td>
                    <table id="allPermission"></table>
                </td>
                <td>
                    <table id="selfPermission"></table>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="role_dialog_btn">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
