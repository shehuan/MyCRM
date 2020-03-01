$(function () {
    let employeeDatagrid = $("#employee_datagrid");
    let employeeDialog = $("#employee_dialog");
    let employeeForm = $("#employee_form");
    let employeeEditAndDeleteBtn = $("#employee_edit, #employee_delete");
    let employeeRoles = $("#employee_roles");
    // 表格初始化
    employeeDatagrid.datagrid({
        url: contextPath + "/employee/list",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        toolbar: "#employee_toolbar",
        pageList: [1, 5, 10, 20],
        columns: [[
            {field: "username", title: "员工账号", align: "center", width: 1},
            {field: "realname", title: "真实姓名", align: "center", width: 1},
            {field: "phone", title: "电话", align: "center", width: 1},
            {field: "email", title: "邮箱", align: "center", width: 1},
            {field: "department", title: "部门", align: "center", width: 1, formatter: departmentFormatter},
            {field: "createTime", title: "入职时间", align: "center", width: 1},
            {field: "state", title: "状态", align: "center", width: 1, formatter: stateFormatter},
            {field: "admin", title: "是否是超级管理员", align: "center", width: 1, formatter: adminFormatter},
        ]],
        onClickRow: function (rowIndex, rowData) {
            if (rowData.state === 0) {// 已离职
                employeeEditAndDeleteBtn.linkbutton('disable');
            } else {
                employeeEditAndDeleteBtn.linkbutton('enable');
            }
        }
    });
    // 弹窗初始化
    employeeDialog.dialog({
        width: 300,
        height: 350,
        buttons: "#employee_dialog_btn",
        closed: true
    });

    // 统一管理方法
    let cmdObj = {
        // 新增
        add: function () {
            employeeDialog.dialog("open");
            employeeDialog.dialog("setTitle", "新增");
            employeeForm.form("clear")
        },
        // 保存
        save: function () {
            let id = $("#employee_form [name=id]").val();
            // id 有值则是编辑
            let url;
            if (id) {
                url = contextPath + "/employee/update"
            } else {
                url = contextPath + "/employee/save"
            }
            employeeForm.form("submit", {
                url: url,
                onSubmit: function (params) {// 提交前触发，添加额外参数
                    let values = employeeRoles.combobox("getValues");
                    console.log(values)
                    for (let i = 0; i < values.length; i++) {
                        params["roles[" + i + "].id"] = values[i];
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        showAlter(data.message, function () {
                            // 关闭对话框
                            employeeDialog.dialog("close");
                            // 刷新表格
                            employeeDatagrid.datagrid("load");
                        })
                    } else {
                        showAlter(data.message);
                    }
                }
            })
        },
        // 取消
        cancel: function () {
            employeeDialog.dialog("close");
        },
        // 编辑
        edit: function () {
            let row = employeeDatagrid.datagrid("getSelected");
            if (row) {
                if (row.department) {
                    row["department.id"] = row.department.id;
                }
                employeeDialog.dialog("open");
                employeeDialog.dialog("setTitle", "编辑");
                employeeForm.form("clear");
                // 同步请求
                $.ajax({
                    async: false,
                    url: contextPath + '/role/queryRoleByEmployeeId?employeeId=' + row.id,
                    dataType: 'json',
                    success: function (data) {
                        employeeRoles.combobox('setValues', data);
                    }
                });
                employeeForm.form("load", row);
            } else {
                showAlter("请选择要编辑的员工");
            }
        },
        // 离职
        delete: function () {
            let row = employeeDatagrid.datagrid("getSelected");
            if (row) {
                $.messager.confirm('温馨提示', '确定要修改员工为离职状态？', function (r) {
                    if (r) {
                        $.get(contextPath + "/employee/delete?id=" + row.id, function (data) {
                            if (data.success) {
                                showAlter(data.message, function () {
                                    // 刷新表格
                                    employeeDatagrid.datagrid("reload");
                                });
                            } else {
                                showAlter(data.message);
                            }
                        }, "json");
                    }
                });
            } else {
                showAlter("请选择要离职的员工");
            }
        },
        // 刷新
        reload: function () {
            employeeDatagrid.datagrid("reload");
        },
        // 搜索
        search: function () {
            let keyword = $("input[name='keyword']").val();
            employeeDatagrid.datagrid('load', {
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

function departmentFormatter(value, record, index) {
    return value ? value.name : "";
}

function stateFormatter(value, record, index) {
    return value ? "<span style='color: green; '>正常</span>" : "<span style='color: red; '>离职</span>";
}

function adminFormatter(value, record, index) {
    return value ? "是" : "否";
}