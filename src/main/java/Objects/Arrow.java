package Objects;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Arrow {
    public enum ArrowType{
        TwoDirection,
        OneDirection
    }
    private Point2D one;
    private Point2D two;
    private Point2D intersectPointOne;
    private final List<Polygon> polygonList = new ArrayList<>();
    private Line line;
    private double k;
    private double k1;
    private double b;
    private double b1;
    final double EPS = 0.02;

    private void createArrow(ArrowType arrowType, boolean inverse){
        switch (arrowType){
            case OneDirection -> {
                if(inverse){
                    setFirstDirectionTwo();
                }else{
                    setFirstDirectionOne();
                }
            }
            case TwoDirection -> {
                Point2D point2DTwo = two;
                Point2D point2DOne = one;
                two = getPointLen(two, EPS, k, b);
                one = getPointLen(one, -EPS, k, b);
                this.line = new Line(one.getX(), one.getY(), two.getX(), two.getY());
                this.two = point2DTwo;
                this.one = point2DOne;
                addFirstDirection();
                addSecondDirection();

            }
            default -> this.line = new Line(one.getX(),one.getY(),two.getX(),two.getY());

        }
        this.line.setStrokeWidth(0.20);
    }
    /**
     * ArrowType : OneDirection, TwoDirection
     * one_ : first point
     * two_ : second point
     * */
    public Arrow(Point2D one_, Point2D two_, ArrowType arrowType){
        boolean inverse = false;
        if(two_.getX() > one_.getX()){
            this.two = two_;
            this.one = one_;
        }else {
            this.one = two_;
            this.two = one_;
            inverse = true;
        }
        calculateCoefficientsFirst();
        createArrow(arrowType,inverse);
    }
    /**
     * ArrowType : OneDirection, TwoDirection
     * one_ : first point
     * two_ : second point
     * deltaOne : Decrease length from first point
     * deltaTwo : Decrease length from second point
     * */
    public Arrow(Point2D one_, Point2D two_, ArrowType arrowType, double deltaOne, double deltaTwo){
        boolean inverse = false;
        if(two_.getX() > one_.getX()){
            this.two = two_;
            this.one = one_;
        }else {
            this.one = two_;
            this.two = one_;
            inverse = true;
        }
        calculateCoefficientsFirst();
        if(!inverse){
            one = moveArrow(deltaOne,one_,false);
            two = moveArrow(deltaTwo,two_,true);
        }else{
            one = moveArrow(deltaOne,two_,false);
            two = moveArrow(deltaTwo,one_,true);
        }
        createArrow(arrowType,inverse);
    }
    private Point2D moveArrow(double delta, Point2D point, boolean inverse){
        double coefficient = 1;
        if(inverse)coefficient*=-1;
        double x = point.getX() + delta * coefficient/Math.sqrt(1 + k*k);
        double y = x * k + b;
        return new Point2D(x,y);
    }
    private void setFirstDirectionOne(){
        Point2D point2D = two;
        two = getPointLen(two, EPS, k, b);
        this.line = new Line(one.getX(), one.getY(), two.getX(), two.getY());
        this.two = point2D;
        addSecondDirection();
    }
    private void setFirstDirectionTwo(){
        Point2D point2D = one;
        one = getPointLen(one, -EPS, k, b);
        this.line = new Line(one.getX(), one.getY(), two.getX(), two.getY());
        this.one = point2D;
        addFirstDirection();
    }
    private Point2D getPointLen(Point2D point2D,double eps,double k , double b){
        double l = one.distance(two);
        double l_eps = Math.signum(eps) * Math.max(Math.abs(l * eps),2);
        double x = point2D.getX() - l_eps/Math.sqrt(1 + k*k);
        double y = k*x + b;
        return new Point2D(x,y);
    }
    public void setParent(Pane parent){
        parent.getChildren().add(line);
        for(Polygon polygon : polygonList){
            parent.getChildren().add(polygon);
        }
    }
    public void removeParent(Pane parent){
        parent.getChildren().remove(line);
        for(Polygon polygon : polygonList){
            parent.getChildren().remove(polygon);
        }
    }
    private void addSecondDirection(){
        Point2D interOne = getPointLen(two,EPS,k,b);
        calculateCoefficientsSecond(interOne);
        getPolygon(interOne,two);
    }
    private void  addFirstDirection(){
        Point2D interOne = getPointLen(one,-EPS,k,b);
        calculateCoefficientsSecond(interOne);
        getPolygon(interOne,one);
    }
    private void calculateCoefficientsFirst(){
        this.k = (two.getY() - one.getY())/(two.getX() - one.getX());
        this.b = -k* one.getX() + one.getY();
    }
    private void calculateCoefficientsSecond(Point2D intersectPoint){
        this.k1 = -1/k;
        this.b1 = intersectPoint.getX()/k + intersectPoint.getY();
    }
    private Point2D getPoint(double k_ ,double b_, Point2D point2D, double len){
        double x2 = point2D.getX() + len/(Math.sqrt(1 + k_*k_));
        double y2 = k_*x2 + b_;
        return new Point2D(x2,y2);
    }
    private void getPolygon(Point2D intersectPoint, Point2D second){
        final double LEN = 0.8;
        Point2D one = getPoint(k1,b1,intersectPoint,-LEN);
        Point2D two = getPoint(k1,b1,intersectPoint,LEN);
        polygonList.add(new Polygon(one.getX(),one.getY(),two.getX(),two.getY(),second.getX(),second.getY()));
    }
    public void setVisible(boolean visible){
        line.setVisible(visible);
        for(Polygon polygon:polygonList){
            polygon.setVisible(visible);
        }

    }
    public void setDisable(boolean visible){
        line.setDisable(visible);
        for(Polygon polygon:polygonList){
            polygon.setDisable(visible);
        }
    }
    public Line getLine() {
        return line;
    }
    public List<Polygon> getPolygonList() {
        return polygonList;
    }
}
