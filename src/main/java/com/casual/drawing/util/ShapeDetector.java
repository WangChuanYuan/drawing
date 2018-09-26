package com.casual.drawing.util;

import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.Shape;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王川源
 * 负责处理图像的检测
 */
public class ShapeDetector {

    /**
     * 图形的颜色表
     */
    private static Scalar[] colorTable = new Scalar[]{
            new Scalar(0, 0, 0), //黑色 UNDEFINED
            new Scalar(0, 128, 128), //橄榄色 TRIANGLE
            new Scalar(240, 32, 160), //紫色 SQUARE
            new Scalar(255, 0, 0), //蓝色 RECTANGLE
            new Scalar(0, 0, 255), //红色 CIRCLE
    };

    /**
     * 通过图形的轮廓判断形状
     */
    private static Shape detectShapeByContour(MatOfPoint contour) {
        Shape shape;
        MatOfPoint2f mp2f = new MatOfPoint2f(contour.toArray());
        double peri = Imgproc.arcLength(mp2f, true);
        //对图像轮廓点进行多边形拟合
        MatOfPoint2f polyShape = new MatOfPoint2f();
        Imgproc.approxPolyDP(mp2f, polyShape, 0.04 * peri, true);
        int shapeLen = polyShape.toArray().length;
        //根据轮廓凸点拟合结果，判断属于什么形状
        switch (shapeLen) {
            case 3:
                shape = Shape.TRIANGLE;
                break;
            case 4:
                Rect rect = Imgproc.boundingRect(contour);
                float width = rect.width;
                float height = rect.height;
                float ar = width / height;
                //计算宽高比，判断是矩形还是正方形
                if (ar >= 0.85 && ar <= 1.1) {
                    shape = Shape.SQUARE;
                } else {
                    shape = Shape.RECTANGLE;
                }
                break;
            default:
                if (shapeLen < 3)
                    shape = Shape.UNDEFINED;
                else shape = Shape.CIRCLE;
                break;
        }
        return shape;
    }

    /**
     * 检测图像中的所有图形，并根据形状高亮显示
     * @param base64Str 原图像的base64码
     * @return 处理后的图像
     */
    public static ImageVO detectShapesInImg(String base64Str) {
        String res = "";
        if (Base64Util.transToImage(base64Str, Const.TEMP_IMG)) {
            //读入图片
            Mat image = Imgcodecs.imread(Const.TEMP_IMG);
            //模糊图像
            Mat blurredImg = image.clone();
            Imgproc.GaussianBlur(image, blurredImg, new Size(5, 5), 0);
            //二值化图像，彩色图像转灰度图像，再进行阈值化处理
            Mat grayImg = blurredImg.clone();
            Imgproc.cvtColor(blurredImg, grayImg, Imgproc.COLOR_BGR2GRAY);
            Mat threshImg = grayImg.clone();
            //反向二值化处理，大于150设置为0(黑色),小于150设置为255(白色)
            Imgproc.threshold(grayImg, threshImg, 150, 255, Imgproc.THRESH_BINARY_INV);
            //寻找轮廓
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(threshImg, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            //进行轮廓描边区分不同形状
            //新建一张原图大小的白底图像
            Mat resImg = new Mat(image.rows(), image.cols(), CvType.CV_8UC3, new Scalar(255, 255, 255));
            for (int i = 0; i < contours.size(); i++) {
                Shape shape = detectShapeByContour(contours.get(i));
                Imgproc.drawContours(resImg, contours, i, colorTable[shape.ordinal()], 2, 8, hierarchy);
            }
            Imgcodecs.imwrite(Const.TEMP_IMG, resImg);
            res = Base64Util.transToBase64(Const.TEMP_IMG);
        }
        return new ImageVO(res);
    }
}
