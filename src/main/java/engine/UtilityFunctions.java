package engine;

import ConnectedObjects.College;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UtilityFunctions {
    static final int delta = 30;
    /**
     * update color
     * */
    public static void reColor(ImageView input){
        Image inputImage = input.getImage();

        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage writableImage = new WritableImage(W,H);

        PixelReader pixelReader = inputImage.getPixelReader();
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        //int nr = (int) newColor.getRed()    * 255;
        //int ng = (int) newColor.getGreen()  * 255;
        //int nb = (int) newColor.getBlue()   * 255;

        for(int j = 0;j < H;j++){
            for(int i = 0;i < W;i++){
                int argb = pixelReader.getArgb(i,j);
                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8)  & 0xFF;
                int b =  argb        & 0xFF;

                Color color = pixelReader.getColor(i,j);

                if(r + g + b < 255 * 3 - 10 && r + g + b > 0){
                    r -= delta;
                    g -= delta;
                    b -= delta;
                }
                argb = (a << 24) | (r << 16) | (g << 8) | b;
                pixelWriter.setArgb(i,j,argb);
            }
        }

        input.setImage(writableImage);
    }
    /**
     * get coeff of scale
     * */
    public static double getCoefficient(ImageView imageView){
        Image originalImage = imageView.getImage();

        double oW = originalImage.getWidth();
        double oH = originalImage.getHeight();

        double nW = imageView.getFitWidth();
        double nH = imageView.getFitHeight();

        if(nH == 0) {
            return oW / nW;
        }
        else{
            return oH / nH;
        }

    }
    public static Color getPixelColor(ImageView image, double localX, double localY) {
        Image originalImage = image.getImage();

        double oW = originalImage.getWidth();
        double oH = originalImage.getHeight();

        double nW = image.getFitWidth();
        double nH = image.getFitHeight();

        if(nH == 0) {
            double coefficientW = oW / nW;
            return originalImage.getPixelReader().getColor((int) (localX * coefficientW), (int) (localY * coefficientW));
        }
        else{
            double coefficientH = oH / nH;
            return originalImage.getPixelReader().getColor((int) (localX * coefficientH), (int) (localY * coefficientH));
        }
    }
    public static boolean TrueColor(Color color){
        int r = (int)(color.getRed()    * 255);
        int g = (int)(color.getGreen()  * 255);
        int b = (int)(color.getBlue()   * 255);
        return 0 < (r + g + b + color.getOpacity());
    }


    public static Pair<Point2D,Point2D> getPointCircleLine(Circle circle, Point2D point){
        double xc = circle.getLayoutX();
        double yc = circle.getLayoutY();
        double rad = circle.getRadius();
        double a4 = -2*xc;
        double a5 = 2*yc;
        double a6 = xc*xc + yc*yc - rad * rad;
        double xV = point.getX();
        double yV = point.getY();
        double a = (-a4/(2*yV) - xV/yV)/(1 + a5/(2*yV));
        double b = (-a5/2 - (xV * a4)/(yV * 2) - a6/yV)/(1 + a5/(2*yV));
        double A = -1 - a*a;
        double B = 2*xc - 2*a*b;
        double C = rad*rad - b*b - xc*xc;
        double D_val = Math.sqrt(B*B - 4*A*C);
        double x1 = (-B + D_val)/(2*A);
        double x2 = (-B - D_val)/(2*A);
        double y1 = Math.sqrt(rad*rad - (x1 - xc)*(x1 - xc)) + yc;
        double y2 = Math.sqrt(rad*rad - (x2 - xc)*(x2 - xc)) + yc;
        return new Pair<>(new Point2D(x1,y1),new Point2D(x2,y2));
    }

    public static Point2D getCenterObject(Node node){
        double radX = ( - node.getBoundsInParent().getMinX() + node.getBoundsInParent().getMaxX())/2;
        double radY = ( - node.getBoundsInParent().getMinY() + node.getBoundsInParent().getMaxY())/2;
        return new Point2D(
                node.getBoundsInParent().getMinX() + radX,
                node.getBoundsInParent().getMinY() + radY
        );
    }
    public static List<Point2D> getPipePointsList( ImageView imageView){
        Image inputImage = imageView.getImage();
        PixelReader pixelReader = inputImage.getPixelReader();

        Bounds bounds = imageView.getBoundsInParent();


        double coefficient = getCoefficient(imageView);

        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();

        List<Point2D> point2DList = new ArrayList<>();
        for(int i = 0; i < W ; i++){
            for(int j = 0;j < H ;j++){
                Color color_ = pixelReader.getColor(i,j);
                if(color_.getBlue() + color_.getGreen() + color_.getRed() > 0) {
                    point2DList.add(new Point2D(i/coefficient + bounds.getMinX(),j/coefficient + bounds.getMinY()));
                }
            }
        }
        return point2DList;
    }

    public static List<Point2D> getPipePointsList(List<ImageView> imageViews){
        List<Point2D> point2DList = new ArrayList<>();
        for(ImageView imageView : imageViews){
            point2DList.addAll(getPipePointsList(imageView));
        }
        return point2DList;
    }

    public static Point2D getMinPoint(Point2D point2D, ImageView imageView){
        List<Point2D> point2DList = getPipePointsList(imageView);
        return getPoint2D(point2D, point2DList);
    }
    public static Point2D getMinPoint(Point2D point2D,List<Point2D> pipePoints){
        return getPoint2D(point2D, pipePoints);
    }



    /**
     * the closest point from the array (pipePoints) to the point (point2D)
     * */
    private static Point2D getPoint2D(Point2D point2D, List<Point2D> pipePoints) {
        Point2D min = null;
        double dist = -1;
        double st_dist;
        for(Point2D point : pipePoints){
            st_dist = point.distance(point2D);
            if(min == null || st_dist < dist){
                dist = st_dist;
                min = point;
            }
        }
        return min;
    }

    /**
     * get list points (borders points) of pane
     * */
    public static List<Point2D> getPointListPane(Pane pane){
        double minX = pane.getLayoutX();
        double maxX = minX + pane.getPrefWidth();
        double minY = pane.getLayoutY();
        double maxY = minY + pane.getPrefHeight();
        List<Point2D> point2DList = new ArrayList<>();
        for(; minX <= maxX;++minX){
            for(double Y = minY; Y <= maxY;++Y){
                point2DList.add(new Point2D(minX,Y));
            }
        }
        return point2DList;
    }

    /**
     * get median point (>minY && <maxY) of point2DList
     * */
    public static Point2D medianPoint(List<Point2D> point2DList, double minY, double maxY){
        point2DList.sort(Comparator.comparingDouble(Point2D::getY));
        double MY = point2DList.get(point2DList.size() - 1).getY();
        double mY = point2DList.get(0).getY();
        for (Point2D point2D : point2DList) {
            if (point2D.getY() > minY && point2D.getY() >= (mY + MY)/2) {
                if(maxY >= 0 && point2D.getY() > maxY) {
                    return new Point2D(point2D.getX(),maxY - 30);
                }else{
                    return point2D;
                }
            }
        }
        return null;
    }
    public static Point2D getMenuPoint(List<Point2D> point2DList, Bounds bounds, Bounds clip){
        double width = bounds.getWidth();
        double height = bounds.getHeight();
        double clip_width = clip.getWidth();
        double clip_height = clip.getHeight();

        Point2D point2D = medianPoint(point2DList,height,clip_height - width);
        assert point2D != null;
        return new Point2D(point2D.getX() + clip_width * 0.05, point2D.getY() - clip_height* 0.05);
    }

    /**
     * points2DList - points of pipe
     * get point to set Layout menu
     *
     * prefWidth - width of menu
     * prefHeight - height of menu
     *
     * clip - bound of parent
     * */
    public static Point2D getMenuPoint(List<Point2D> point2DList, double prefWidth, double prefHeight, Bounds clip){

        double clip_width = clip.getWidth();
        double clip_height = clip.getHeight();

        Point2D point2D = medianPoint(point2DList,0,clip_height - prefWidth);
        assert point2D != null;

        if(point2D.getX() + prefWidth > clip_width - prefWidth){
            point2D = new Point2D(clip_width - prefWidth - 10, point2D.getY());
        }
        if(point2D.getX() < 0){
            point2D = new Point2D(10, point2D.getY());
        }
        return new Point2D(point2D.getX() + clip_width * 0.05, point2D.getY() - clip_height* 0.05);
    }

    /**
     * distribution points of line
     * */
    public static List<Point2D> getLineListPoint(Line line){
        // count of line random points
        final int step = 5;

        Pair<Double,Double> params = getParamsKB(line.getStartX(),line.getStartY(),line.getEndX(),line.getEndY());
        double minX = Math.min(line.getStartX(),line.getEndX());
        double maxX = Math.max(line.getStartX(),line.getEndX());

        List<Point2D> point2DList = new ArrayList<>();
        for(int i = 0;i <= step;i++){
            double a = minX + (maxX - minX) * Math.random() ;
            double b;
            if(params.getValue() == null){
                b = line.getEndY();
            }else {
                b = params.getKey() * a + params.getValue();
            }
            point2DList.add(new Point2D(a,b));
        }
        return point2DList;
    }
    public static List<Point2D> getListLinePoints(List<Line> lines){
        List<Point2D> point2DList = new ArrayList<>();
        for(Line line: lines){
            point2DList.addAll(getLineListPoint(line));
        }
        return point2DList;
    }


    /**
     *  calculate coefficients of line (param k and B)
     *
     *  first point
     *  second point
     * */
    public static Pair<Double, Double> getParamsKB(double firstX, double firstY, double secondX, double secondY){
        double k,b;
        if(secondX == firstX){
            k = 0;
        }else {
            k = (secondY - firstY) / (secondX - firstX);
        }
        if(firstY == secondY){
            System.out.println("!!!!!!!!");
            return new Pair<>(null,null);
        }
        b =-k* firstX + firstY;
        return new Pair<>(k,b);
    }

    public static double[] StringToDoubleArray(String str,String regex){
        String[] str_arr = str.split(regex);
        double[] doubles = new double[str_arr.length];
        for(int i = 0;i < str_arr.length;i++){
            doubles[i] = Double.parseDouble(str_arr[i]);
        }
        return doubles;
    }
    public static double[][] stringToDoubleDoubleArray(String str, String regexAr, String regex){
        String[] strArr = str.split(regexAr);
        double[][] doubles = new double[strArr.length][];
        for(int i = 0;i < strArr.length;i++){
            doubles[i] = StringToDoubleArray(strArr[i],regex);
        }
        return doubles;
    }

    /**
     * convert image to grayStyle
     * */
    public static Image convertToGrayScale(Image image) {
        WritableImage result = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader preader = image.getPixelReader();
        PixelWriter pwriter = result.getPixelWriter();

        for (int i = 0; i < result.getWidth(); i++)
            for (int j = 0; j < result.getHeight(); j++)
                pwriter.setColor(i , j, preader.getColor(i, j).grayscale());
        return result;
    }


    /**
     * pointsOfObject - points of the object under consideration,
     *                       relative to which we are looking for the nearest colleges.
     *  colleges - all collages.
     *  totalCollegeList - final list of colleges (link to it)
     */
    public static void filterColleges(List<College> colleges, List<Point2D> pointsOfObject, List<College> totalCollegeList) {
        // dist - college for search the nearest colleges
        List<Pair<Double,College>> collegeList = new ArrayList<>();
        if(pointsOfObject.size() != 0) {
            for (College college : colleges) {
                Point2D first = college.getCenterCirclePoint2D();
                Point2D second = UtilityFunctions.getMinPoint(first, pointsOfObject);
                collegeList.add(
                        new Pair<>(first.distance(second),college)
                );
            }
            collegeList.sort(Comparator.comparingDouble(Pair::getKey));

            // count of usage colleges
            final int nColleges = 3;

            // add
            for(int i = 0;i < nColleges;i++){
                totalCollegeList.add(collegeList.get(i).getValue());
            }
        }
    }


}
