/**
 * 导出图片功能
 * @param canvasId 画布id
 * @public
 */
var exportImg = function (canvasId) {
    var pane = $(canvasId)[0];

    var imgType = "png";
    var imgData = pane.toDataURL(imgType);

    //将mime-type改为image/octet-stream,强制让浏览器下载
    imgData = imgData.replace(_fixImgType(imgType), 'image/octet-stream');

    var filename = new Date().toLocaleDateString() + '.' + imgType;
    _saveFile(imgData, filename);
};

/**
 * 修正图片格式名
 * @param imgType
 * @returns {string}
 * @private
 */
function _fixImgType(imgType) {
    imgType = imgType.toLowerCase().replace(/jpg/i, "jpeg");
    var r = imgType.match(/png|jpeg|bmp|gif/)[0];
    return 'image/' + r;
}

/**
 * 保存文件
 * @param data
 * @param filename
 * @private
 */
function _saveFile(data, filename) {
    var link = document.createElement('a');
    link.href = data;
    link.download = filename;
    var event = document.createEvent('MouseEvents');
    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
    link.dispatchEvent(event);
}