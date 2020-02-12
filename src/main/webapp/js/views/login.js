function submitForm() {
    $.post("/login", $("form").serialize(), function (data) {
        // 登录成功
        if (data.success) {
            // 跳转到首页
            window.location.href = "/index";
        } else {
            showAlter(data.message);
        }
    }, "json");
}

function resetForm() {
    $("form input[name]").val("");
}

// 回车登录
$(document).keyup(function (event) {
    if (event.keyCode === 13) {
        submitForm();
    }
});