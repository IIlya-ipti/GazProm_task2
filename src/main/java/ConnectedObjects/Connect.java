package ConnectedObjects;

import Objects.Arrow;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class Connect implements Animation {
    static Pane pane = new Pane();

    private final ConnectedObject objectFirst;
    private final ConnectedObject objectSecond;
    private Animation oneAnimation = null;
    private Animation twoAnimation = null;
    private boolean active = true;
    private final Pane parent;
    private Arrow line;
    public Connect(ConnectedObject objectFirst, ConnectedObject objectSecond, Pane parent){
        this.objectFirst = objectFirst;
        this.objectSecond = objectSecond;
        this.parent = parent;
        objectFirst.    connect(    this,   objectSecond);
        objectSecond.   connect(    this,   objectFirst);
    }
    public static Pane getPane() {
        return pane;
    }
    public void setLine(Point2D pointOne, Point2D pointTwo, Arrow.ArrowType arrowType){
        this.line = new Arrow(pointOne,pointTwo,arrowType);
        //line.setParent(pane);
        line.setParent(parent);

    }
    public void setLine(Point2D pointOne, Point2D pointTwo, Arrow.ArrowType arrowType,double deltaOne, double deltaTwo){
        //this.line = new Arrow(pointOne,pointTwo,arrowType,deltaOne,deltaTwo);
        //line.setParent(pane);
        //line.setParent(parent);

    }
    public void remove(){
        objectSecond.disConnect(objectFirst);
        objectFirst.disConnect(objectSecond);
        if(line != null) {
            line.removeParent(parent);
            line.setVisible(false);
            line.setDisable(true);
        }
    }
    public void setOneAnimation(Animation animation){
        this.oneAnimation = animation;
    }
    public void setTwoAnimation(Animation twoAnimation) {
        this.twoAnimation = twoAnimation;
    }
    @Override
    public void on() {
        if(!active) {
            if(line != null) {
                line.setVisible(true);
                line.setDisable(false);
            };
            active = !active;

            if (oneAnimation != null) {
                oneAnimation.on();
            }
            if (twoAnimation != null) {
                twoAnimation.on();
            }
        }
    }

    @Override
    public void off() {
        if(active) {
            active = false;
            if(line != null) {
                line.setVisible(false);
                line.setDisable(true);
            }
            if (oneAnimation != null) {
                oneAnimation.off();
            }
            if (twoAnimation != null) {
                twoAnimation.off();
            }
        }

    }

    @Override
    public boolean isActive() {
        return active;
    }
}
