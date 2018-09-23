//当前正在操作的图片名称，作为全局变量
window.imgName = "";

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

    $("#save").click(function () {
        if (window.imgName != null && window.imgName.length != 0)
            save("#pane", window.imgName);
        else $("#save-as").modal("show");
    });
    $("#save-as-confirm").click(function () {
        saveAs("#pane", $("#save-as-input").val(), "#save-as");
    });
    initLoadModal();
    $("#load-confirm").click(function () {
        load("#pane", $("#load-input").val(), "#load");
    })
}

function initLoadModal() {
    $("#load").on("show.bs.modal", function () {
        var imgNames = getAllImgNames();
        $("#load-items").empty();
        for (var i = 0; i < imgNames.length; i++) {
            $("#load-items").append("<li><a id=item" + i + ">" + imgNames[i] + "</a></li>");
            $("#item" + i).bind("click", {index: i}, getInput);

            function getInput(e) {
                var i = e.data.index;
                $("#load-input").val(imgNames[i]);
            }
        }
    });
}

function initPane() {
    $(window).resize(resizePane);
    resizePane();
    fillWhiteBackward();
    pencil("#pane");
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