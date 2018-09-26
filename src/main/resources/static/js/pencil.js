/**
 * 画笔功能
 * @param canvasId 画布id
 * @public
 */
var pencil = function (canvasId) {
    var pane = $(canvasId)[0];
    var context = pane.getContext("2d");
    var mousedown = false;

    context.globalCompositeOperation = "source-over";

    $(canvasId).unbind("mousedown").mousedown(function (ev) {
        ev.preventDefault();
        var startX = ev.pageX - this.offsetLeft;
        var startY = ev.pageY - this.offsetTop;
        context.beginPath();
        context.moveTo(startX, startY);
        mousedown = true;
    });

    $(canvasId).unbind("mousemove").mousemove(function (ev) {
        ev.preventDefault();
        var endX = ev.pageX - this.offsetLeft;
        var endY = ev.pageY - this.offsetTop;
        if (mousedown) {
            context.lineTo(endX, endY);
            context.stroke();
        }
    });

    $(canvasId).unbind("mouseup").mouseup(function (ev) {
        ev.preventDefault();
        context.closePath();
        mousedown = false;
    });

    $(canvasId).unbind("mouseout").mouseout(function (ev) {
        ev.preventDefault();
        context.closePath();
        mousedown = false;
    });

};