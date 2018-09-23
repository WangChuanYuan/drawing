package com.casual.drawing.util;

import com.casual.drawing.vo.ImageVO;
import com.casual.drawing.vo.Shape;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ShapeDetector {

    private static Scalar shapeColor(Shape shape) {
        Scalar scalar = null;
        switch (shape) {
            case UNDEFINED:
                scalar = new Scalar(0, 0, 0); //黑色
                break;
            case TRIANGLE:
                scalar = new Scalar(0, 255, 255); //黄色
                break;
            case SQUARE:
                scalar = new Scalar(0, 255, 0); //绿色
                break;
            case RECTANGLE:
                scalar = new Scalar(255, 0, 0); //蓝色
                break;
            case POLYGON:
                scalar = new Scalar(0, 0, 0); //黑色
                break;
            case CIRCLE:
                scalar = new Scalar(0, 0, 255); //红色
                break;
        }
        return scalar;
    }

    private static Shape detectShapeByContour(MatOfPoint mp, MatOfPoint2f mp2f) {
        Shape shape;
        double peri = Imgproc.arcLength(mp2f, true);
        //对图像轮廓点进行多边形拟合
        MatOfPoint2f polyShape = new MatOfPoint2f();
        Imgproc.approxPolyDP(mp2f, polyShape, 0.01 * peri, true);
        int shapeLen = polyShape.toArray().length;
        //根据轮廓凸点拟合结果，判断属于什么形状
        if (shapeLen < 3)
            shape = Shape.UNDEFINED;
        else if (shapeLen == 3)
            shape = Shape.TRIANGLE;
        else if (4 <= shapeLen && shapeLen <= 6) {
            Rect rect = Imgproc.boundingRect(mp);
            float width = rect.width;
            float height = rect.height;
            float ar = width / height;
            //计算宽高比，判断是矩形还是正方形
            if (ar >= 0.85 && ar <= 1.1) {
                shape = Shape.SQUARE;
            } else {
                shape = Shape.RECTANGLE;
            }
        } else {
            if (shapeLen > 8)
                shape = Shape.CIRCLE;
            else shape = Shape.POLYGON;
        }
        return shape;
    }

    public static ImageVO detectShapesInImg(String base64Str) {
        if (Base64Util.transToImage(base64Str)) {
            //读入图片
            Mat image = Imgcodecs.imread(Const.TEMP_IMG);
            //模糊图像
            Mat blurredImg = image.clone();
            Imgproc.GaussianBlur(image, blurredImg, new Size(3, 3), 0);
            //二值化图像，彩色图像转灰度图像
            Mat grayImg = blurredImg.clone();
            Imgproc.cvtColor(blurredImg, grayImg, Imgproc.COLOR_BGR2GRAY);
            Mat threshImg = grayImg.clone();
            Imgproc.adaptiveThreshold(grayImg, threshImg, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 25, 10);
            //寻找轮廓
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(threshImg, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            //进行轮廓描边区分不同形状
            //新建一张原图大小的白底图像
            Mat resImg = new Mat(image.rows(), image.cols(), CvType.CV_8UC3, new Scalar(255, 255, 255));
            for (int i = 0; i < contours.size(); i++) {
                MatOfPoint2f matOfPoint2f = new MatOfPoint2f(contours.get(i).toArray());
                Shape shape = detectShapeByContour(contours.get(i), matOfPoint2f);
                Imgproc.drawContours(resImg, contours, i, shapeColor(shape), 2, 8, hierarchy);
            }
            Imgcodecs.imwrite(Const.TEMP_IMG, resImg);
        }
        return new ImageVO(Base64Util.transToBase64(Const.TEMP_IMG));
    }
}
