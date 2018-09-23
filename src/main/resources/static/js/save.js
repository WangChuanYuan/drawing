var save = function (canvasId, imgName) {
    var pane = $(canvasId)[0];
    var base64Str = pane.toDataURL("image/png");

    $.ajax({
        url: "/modify_img",
        type: "post",
        dataType: "json",
        scriptCharset: "utf-8",
        data: {imgName: imgName, base64Str: base64Str},
        success: function (data) {
            if (data == "SUCCESS") {
                alert("保存"+ imgName + "成功");
            }
            else {
                alert("保存失败");
            }
        },
        error: function (error) {
            console.log("save error");
        }
    })
};