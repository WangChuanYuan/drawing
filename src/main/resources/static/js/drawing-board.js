//当前正在操作的图片名称，作为全局变量
window.imgName = "";

/**
 * 初始化画板
 * @public
 */
var initDrawingBoard = function () {
    _initPane();
    _initToolbar();
};

/**
 * 初始化画布
 * @private
 */
function _initPane() {
    $(window).resize(_resizePane);
    _resizePane();
    _fillWhiteBackward();
    pencil("#pane");
}

/**
 * 初始化工具栏
 * @private
 */
function _initToolbar() {
    _initPencil();
    _initEraser();
    _initTag();
    _initClear();

    _initImgImport();
    _initImgExport();

    _initImgSave();
    _initImgSaveAs();
    _initLoadModal();
    _initImgLoad();
}

/**
 * 画笔功能初始化
 * @private
 */
function _initPencil() {
    $("#pencil").click(function () {
        pencil("#pane");
    });
}

/**
 * 橡皮功能初始化
 * @private
 */
function _initEraser() {
    $("#eraser").click(function () {
        eraser("#pane");
    });
}

/**
 * 识别功能初始化
 * @private
 */
function _initTag() {
    $("#tag").click(function () {
        tag("#pane");
    });
}

/**
 * 清空功能初始化
 * @private
 */
function _initClear() {
    $("#clear").click(function () {
        $("#pane").attr("width", $(".my-content").width() * 0.6);
        _fillWhiteBackward();
    });
}

/**
 * 图片导入功能初始化
 * @private
 */
function _initImgImport() {
    $("#fileSelector").change(function () {
        importImg("#pane", "#fileSelector");
    });
    //触发文件选择器的点击事件
    $("#import-img").click(function () {
        $("#fileSelector").trigger("click");
    });
}

/**
 * 图片导出功能初始化
 * @private
 */
function _initImgExport() {
    $("#export-img").click(function () {
        exportImg("#pane");
    });
}

/**
 * 图片保存功能初始化
 * @private
 */
function _initImgSave() {
    $("#save").click(function () {
        if (window.imgName != null && window.imgName.length != 0)
            save("#pane", window.imgName);
        else $("#save-as").modal("show");
    });
}

/**
 * 图片另存为功能初始化
 * @private
 */
function _initImgSaveAs() {
    $("#save-as-confirm").click(function () {
        saveAs("#pane", $("#save-as-input").val(), "#save-as");
    });
}

/**
 * 图片导入功能初始化
 * @private
 */
function _initImgLoad() {
    $("#load-confirm").click(function () {
        load("#pane", $("#load-input").val(), "#load");
    })
}

/**
 * 载入模态框初始化
 * @private
 */
function _initLoadModal() {
    $("#load").on("show.bs.modal", function () {
        var imgNames = _getAllImgNames();
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

/**
 * 将画布填充为白色
 * @private
 */
function _fillWhiteBackward() {
    var pane = $("#pane")[0];
    var context = pane.getContext("2d");
    context.fillStyle = "white";
    context.fillRect(0, 0, pane.width, pane.height);
}

/**
 * 重置画布大小
 * @private
 */
function _resizePane() {
    var pane = $("#pane")[0];
    var context = pane.getContext("2d");
    var img = context.getImageData(0, 0, pane.width, pane.height); //保存画布
    $("#pane").attr("height", $(".my-content").height() * 0.9);
    $("#pane").attr("width", $(".my-content").width() * 0.6);
    context.putImageData(img, 0, 0); //恢复画布
}