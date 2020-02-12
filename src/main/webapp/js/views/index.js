$(function () {
    $("#menu").tree({
        url: '/menu',
        onClick: function (node) {
            if (node.attributes && typeof node.attributes == 'string') {
                node.attributes = $.parseJSON(node.attributes);
            }
            // 菜单折叠
            $('#menu').tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);
            //在选项中添加新面板
            var mainTabs = $("#main_tabs");
            //在选项卡中是否已经有该节点的面板.
            if (mainTabs.tabs("exists", node.text)) {
                //选中面板
                mainTabs.tabs("select", node.text);
            } else {
                mainTabs.tabs("add", {
                    title: node.text,
                    closable: true,
                    // href: node.attributes.url
                    content: "<iframe src='" + node.attributes.url + "' style='width:100%;height:100%' frameborder=0></iframe>"
                });
            }

        }
    });
});


