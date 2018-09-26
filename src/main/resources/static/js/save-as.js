/**
 * 图片另存为
 * @param canvasId 画布id
 * @param imgName 图片名
 * @param modalId 模态框id
 * @public
 */
var saveAs = function (canvasId, imgName, modalId) {
    var pane = $(canvasId)[0];
    var base64Str = pane.toDataURL("image/png");

    imgName = imgName.replace(/^\s+|\s+$/g, ""); //trim
    if (imgName == null || imgName.length == 0)
        alert("输入不可为空");

    $.ajax({
        url: "/save_img",
        type: "post",
        dataType: "json",
        scriptCharset: "utf-8",
        data: {imgName: imgName, base64Str: base64Str},
        success: function (data) {
            if (data == "SUCCESS") {
                $(modalId).modal("hide");
                alert("保存" + imgName + "成功");
                window.imgName = imgName;
            }
            else {
                alert("已存在相同名称");
            }
        },
        error: function (error) {
            console.log("save error");
        }
    })
};