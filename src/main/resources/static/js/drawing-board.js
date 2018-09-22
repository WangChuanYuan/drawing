var graph = [];

function initDrawingBoard() {
    initPane();
    initToolbar();
}

/**
 * 工具栏
 */
function initToolbar() {
    $("#fileSelector").change(function () {
        importImg("#pane", "#fileSelector");
    });
    $("#pencil").click(function () {
        pencil("#pane");
    });
    $("#eraser").click(function () {
        eraser("#pane");
    });
    $("#tag").click(function () {
        tag("#pane");
    });
    $("#clear").click(function () {
        $("#pane").attr("width", $(".my-content").width() * 0.6);
        fillWhiteBackward();
    });
    //触发文件选择器的点击事件
    $("#import-img").click(function () {
        $("#fileSelector").trigger("click");
    });
    $("#export-img").click(function () {
        exportImg("#pane");
    });
}

function initPane() {
    $(window).resize(resizePane);
    resizePane();
    fillWhiteBackward();
    pencil("#pane", graph);
}

function fillWhiteBackward() {
    var pane = $("#pane")[0];
    var context = pane.getContext("2d");
    context.fillStyle = "white";
    context.fillRect(0, 0, pane.width, pane.height);
}

function resizePane() {
    var pane = $("#pane")[0];
    var context = pane.getContext("2d");
    var img = context.getImageData(0, 0, pane.width, pane.height); //保存画布
    $("#pane").attr("height", $(".my-content").height() * 0.9);
    $("#pane").attr("width", $(".my-content").width() * 0.6);
    context.putImageData(img, 0, 0); //恢复画布
}