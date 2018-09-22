var importImg = function (canvasId, fileInputId) {
    var pane = $(canvasId)[0];
    var context = pane.getContext("2d");
    var files = $(fileInputId).prop("files");
    if (files.length == 0 || files[0].type.indexOf("image") == -1) {
        return;
    }
    else {
        var fileReader = new FileReader();
        fileReader.readAsDataURL(files[0]);
        fileReader.onload = function (fileReaderEv) {
            var base64Str = this.result;
            var image = new Image();
            image.src = base64Str;
            image.onload = function (imageEv) {
                //将图片从(0, 0)至(width,height) 画入 canvas(0,0)至(width,height)
                context.drawImage(image, 0, 0, image.width, image.height, 0, 0, pane.width, pane.height);
            }
        }
    }
};