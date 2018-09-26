/**
 * 识别图像
 * @param canvasId 画布id
 * @public
 */
var tag = function (canvasId) {
    var pane = $(canvasId)[0];
    var context = pane.getContext("2d");
    var base64Str = pane.toDataURL("image/png");

    $.ajax({
        url: "/detect_shapes",
        type: "post",
        dataType: "json",
        scriptCharset: "utf-8",
        data: {base64Str: base64Str},
        success: function (data) {
            var image = new Image();
            var base64ImgPre = "data:image/png;base64,";
            image.src = base64ImgPre + data.base64Str;
            image.onload = function (imageEv) {
                //将图片从(0, 0)至(width,height) 画入 canvas(0,0)至(width,height)
                context.drawImage(image, 0, 0, image.width, image.height, 0, 0, pane.width, pane.height);
            }
        },
        error: function (error) {
            console.log("detect error");
        }
    })
};