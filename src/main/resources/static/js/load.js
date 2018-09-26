/**
 * 加载图片
 * @param canvasId 画布id
 * @param imgName 图片名
 * @param modalId 模态框id
 * @public
 */
var load = function(canvasId, imgName, modalId) {
    var pane = $(canvasId)[0];
    var context = pane.getContext("2d");

    if (imgName == null || imgName.length == 0)
        alert("请选择");

    $.ajax({
        url: "/get_img_by_name",
        type: "get",
        dataType: "json",
        scriptCharset: "utf-8",
        data: {imgName: imgName},
        success: function (data) {
            if(data) {
                var image = new Image();
                var base64ImgPre = "data:image/png;base64,";
                image.src = base64ImgPre + data.base64Str;
                image.onload = function (imageEv) {
                    //将图片从(0, 0)至(width,height) 画入 canvas(0,0)至(width,height)
                    context.drawImage(image, 0, 0, image.width, image.height, 0, 0, pane.width, pane.height);
                }
                $(modalId).modal("hide");
                window.imgName = imgName;
            } else alert("图像不存在");
        },
        error: function (error) {
            console.log("load img error");
        }
    })
};

/**
 *
 * @returns {Array}
 * @private
 */
function _getAllImgNames() {
    var images = [];
    $.ajax({
        url: "/get_all_imgnames",
        type: "get",
        dataType: "json",
        async: false,
        scriptCharset: "utf-8",
        success: function (data) {
            images = data;
        },
        error: function (error) {
            console.log("load img names error");
        }
    });
    return images;
}