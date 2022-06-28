package engine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.Objects;

public enum UserPath {

    CollegeOne(
            "/marks/building.png",
            new Point2D(0.02,0.02),
            new Point2D(0.03,0.03)
            ),
    CollegeTwo(
            "/marks/College.png",
            new Point2D(0.01,0.01),
            new Point2D(0.015,0.015)
            ),
    CollegeThree(
            "/marks/college-studying.png",
            new Point2D(0.02,0.02),
            new Point2D(0.03,0.03)
    ),
    CollegeFour(
            "/marks/college (1).png",
            new Point2D(0.02,0.02),
            new Point2D(0.03,0.03)
    ),
    CollegeFive(
            "/marks/univeristy.png",
            new Point2D(0.02,0.02),
            new Point2D(0.03,0.03)
            );








    public final Point2D    oldScale;
    public final Point2D    startScale;
    public final String     path;
    UserPath(String path, Point2D startScale, Point2D oldScale){
        this.path = path;
        this.oldScale = oldScale;
        this.startScale = startScale;
    }

    public InputStream getPathStream() {
        return Objects.requireNonNull(getClass().getResourceAsStream(this.path));
    }
    public ImageView getImageViewNotScale(){
        return new ImageView(new Image(getPathStream()));
    }
    static public ImageView getImageViewSetWidthHeight(UserPath userPath,double width, double height, boolean preserveRatio){
        ImageView imageView = userPath.getImageViewNotScale();
        imageView.setPreserveRatio(preserveRatio);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}
