package engine;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Marker  implements Comparable<Marker>,ImageInterface {
    private boolean active = false;
    public static UserPath actualUserPath;
    private UserPath userPath;
    private final ImageView imageView;
    private Wrapper wrapper;
    public Marker(UserPath userPath, Pane pane, double X, double Y){

        this.imageView = userPath.getImageViewNotScale();
        imageView.setScaleX(userPath.startScale.getX());
        imageView.setScaleY(userPath.startScale.getY());
        pane.getChildren().add(imageView);
        imageView.toFront();
        //imageView.setFitWidth(10);
        //imageView.setFitHeight(10);
        setScaleX();

        Point2D center = UtilityFunctions.getCenterObject(imageView);

        Point2D pos = new Point2D(
                X - center.getX(),
                Y - center.getY()
        );
        imageView.setLayoutY(pos.getY());
        imageView.setLayoutX(pos.getX());

        System.out.println(" === " + imageView.getBoundsInParent().getMinX());
    }
    private void setScaleX(){
        System.out.println(imageView.getFitWidth());
    }

    public void connect(Wrapper wrapper){
        this.wrapper = wrapper;
    }


    public ImageView getImageView() {
        return imageView;
    }

    public void disConnect(){
        wrapper.disConnect(this);
    }

    @Override
    public int compareTo(Marker o) {
        return (int) ((this.imageView.getBoundsInParent().getMinX() - o.getImageView().getBoundsInParent().getMinX()) * 10);
    }

    public void off(){
        imageView.setVisible(false);
        imageView.setDisable(true);
    }
    public void on(){
        imageView.setVisible(true);
        imageView.setDisable(false);
    }
    public void clickOn(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetY(0);
        dropShadow.setOffsetX(0);
        dropShadow.setRadius(100);
        dropShadow.setColor(Color.DODGERBLUE);
        imageView.setEffect(dropShadow);
        active = true;
    }
    public void clickOff(){
        active = false;
        imageView.setEffect(null);
    }


    @Override
    public boolean contains(double X, double Y) {
        if(X >= imageView.getBoundsInParent().getMinX() &&
        X <= imageView.getBoundsInParent().getMaxX()){
            return Y >= imageView.getBoundsInParent().getMinY() &&
                    Y <= imageView.getBoundsInParent().getMaxY();
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }
}
