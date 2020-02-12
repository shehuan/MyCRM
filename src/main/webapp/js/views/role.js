$(function () {
    let roleDatagrid = $("#role_datagrid");
    let roleDialog = $("#role_dialog");
    let roleForm = $("#role_form");

    let allPermission = $("#allPermission");
    let selfPermission = $("#selfPermission");
    // 表格初始化
    roleDatagrid.datagrid({
        url: "/role/list",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#role_toolbar",
        pageList: [1, 5, 10, 20],
        columns: [[
            {field: "sn", title: "角色编号", align: "center", width: 1},
            {field: "name", title: "角色名称", align: "center", width: 1},
        ]],
    });

    allPermission.datagrid({
        url: "/permission/list",
        width: 300,
        height: 300,
        title: "所有权限",
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        columns: [[
            {field: "name", title: "权限名称"},
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            // 双击所有权限时添加到已有权限
            let rows = selfPermission.datagrid('getRows');
            let index = -1;
            for (let i = 0; i < rows.length; i++) {
                if (rows[i].id === rowData.id) {
                    index = i;
                    break;
                }
            }
            if (index === -1) {// 未找到，则添加
                selfPermission.datagrid("appendRow", rowData);
            } else {// 否则选中
                selfPermission.datagrid("selectRow", index);
            }
        }
    });

    selfPermission.datagrid({
        width: 300,
        height: 300,
        title: "已有权限",
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: "name", title: "权限名称"}
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            // 双击删除已有权限
            selfPermission.datagrid('deleteRow', rowIndex);
        }
    });

    // 修改所有权限表格的分页组件
    let pager = allPermission.datagrid("getPager");
    pager.pagination({
        total: 114,
        showPageList: false,
        showRefresh: false,
        displayMsg: ''
    });

    // 弹窗初始化
    roleDialog.dialog({
        width: 700,
        height: 450,
        buttons: "#role_dialog_btn",
        closed: true
    });

    // 统一管理方法
    let cmdObj = {
        // 新增
        add: function () {
            roleDialog.dialog("open");
            roleDialog.dialog("setTitle", "新增");
            $("input[name='id'], input[name='sn'], input[name='name']").val("");
            selfPermission.datagrid("loadData", {rows: []});
        },
        // 保存
        save: function () {
            let id = $("#role_form [name=id]").val();
            // id 有值则是编辑
            let url;
            if (id) {
                url = "/role/update"
            } else {
                url = "/role/save"
            }
            roleForm.form("submit", {
                url: url,
                onSubmit: function (params) {// 提交前触发，添加额外参数
                    let rows = selfPermission.datagrid('getRows');
                    for (let i = 0; i < rows.length; i++) {
                        params["permissions[" + i + "].id"] = rows[i].id;
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        showAlter(data.message, function () {
                            // 关闭对话框
                            roleDialog.dialog("close");
                            // 刷新表格
                            roleDatagrid.datagrid("load");
                        })
                    } else {
                        showAlter(data.message);
                    }
                }
            })
        },
        // 取消
        cancel: function () {
            roleDialog.dialog("close");
        },
        // 编辑
        edit: function () {
            let rowData = roleDatagrid.datagrid("getSelected");
            if (rowData) {
                roleDialog.dialog("open");
                roleDialog.dialog("setTitle", "编辑");

                $("input[name='id'], input[name='sn'], input[name='name']").val("");

                // 加载该角色已有的权限
                let options = selfPermission.datagrid("options");
                options.url = "/permission/queryPermissionByRoleId";
                selfPermission.datagrid("load", {
                    roleId: rowData.id,
                });
                roleForm.form("load", rowData);//把rowData加载到弹窗显示
            } else {
                showAlter("请选择要编辑的角色");
            }
        },
        // 删除
        delete: function () {
            let row = roleDatagrid.datagrid("getSelected");
            if (row) {
                $.messager.confirm('温馨提示', '确定要删除该角色吗？', function (r) {
                    if (r) {
                        $.get("/role/delete?id=" + row.id, function (data) {
                            if (data.success) {
                                showAlter(data.message, function () {
                                    // 刷新表格
                                    roleDatagrid.datagrid("reload");
                                });
                            } else {
                                showAlter(data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                showAlter("请选择要删除的角色");
            }
        },
        // 刷新
        reload: function () {
            roleDatagrid.datagrid("reload");
        },
        // 搜索
        search: function () {
            let keyword = $("input[name='keyword']").val();
            roleDatagrid.datagrid('load', {
                keyword: keyword
            });
        }
    };

    // 对按钮进行统一的监听
    $("a[data-cmd]").on("click", function () {
        let cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

});