var eraser = function (canvasId) {

    var pane = $(canvasId)[0];
    var context = pane.getContext("2d");
    var offsetX = pane.offsetLeft;
    var offsetY = pane.offsetTop;
    var mousedown = false;
    var ERASER_RADIUS = 15;

    context.globalCompositeOperation = "destination-out"; //设置原图像覆盖目标图像，且原图像为透明可以实现橡皮擦效果

    $(canvasId).unbind("mousedown").mousedown(function (ev) {
        ev.preventDefault();
        mousedown = true;
    });

    $(canvasId).unbind("mousemove").mousemove(function (ev) {
            ev.preventDefault();
            if (mousedown) {
                var x = (ev.clientX + document.body.scrollLeft || ev.pageX) - offsetX || 0;
                var y = (ev.clientY + document.body.scrollTop || ev.pageY) - offsetY || 0;
                context.beginPath();
                context.arc(x, y, ERASER_RADIUS, 0, Math.PI * ERASER_RADIUS); //x, y, r, sAngle, eAngle
                context.fill();
            }
        }
    );

    $(canvasId).unbind("mouseup").mouseup(function (ev) {
        ev.preventDefault();
        mousedown = false;
    });

    $(canvasId).unbind("mouseout").mouseout(function (ev) {
        ev.preventDefault();
        mousedown = false;
    });

};