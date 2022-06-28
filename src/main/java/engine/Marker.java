package engine;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Marker {
    public static UserPath actualUserPath;
    private UserPath userPath;
    private final ImageView imageView;
    private Wrapper wrapper;
    public Marker(UserPath userPath, Pane pane, double X, double Y){

        this.imageView = userPath.getImageViewNotScale();
        pane.getChildren().add(imageView);
        imageView.setScaleX(userPath.startScale.getX());
        imageView.setScaleY(userPath.startScale.getY());

        Point2D center = UtilityFunctions.getCenterObject(imageView);

        Point2D pos = new Point2D(
                X - center.getX(),
                Y - center.getY()
        );
        imageView.setLayoutY(pos.getY());
        imageView.setLayoutX(pos.getX());
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
}
