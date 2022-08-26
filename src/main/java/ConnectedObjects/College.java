package ConnectedObjects;

import Objects.Arrow;
import engine.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import parser.Config;
import parser.ConfigCollege;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class College extends UsedObject {
    private final ArrayList<ImageView> imageViews = new ArrayList<>();
    private final UserPath userPath;
    private final Pane parentPane;

    // two version of image
    private final Image greyImage;
    private final Image image;

    // work with circle animation
    private Timeline circleAnimation;
    private ImageView circleImageView;
    private Image circleOn;
    private Image circleOff;

    public College(UserPath userPath,double width, double height,Pane pane)  {
        super(UserPath.getImageViewSetWidthHeight(
                    userPath,
                    width,
                    height,
                    true)
        );
        ImageView imageView = getImageObject();
        this.userPath = userPath;
        this.parentPane = pane;

        // get grey image version
        this.greyImage = UserPath.getImageViewSetWidthHeight(
                userPath,
                width,
                height,
                true,
                true
        ).getImage();
        this.image = getImageObject().getImage();

        // add circle and animation for circle
        addCircle();

        pane.getChildren().add(imageView);
        imageView.toFront();
    }

    /**
     * add background circle and animation for circle
     * */
    private void addCircle(){
        ImageView imageView = getImageObject();

        // make a circle on the background
        this.circleImageView = UserPath.getImageViewSetWidthHeight(UserPath.Circle, 50, 50, true);
        parentPane.getChildren().add(this.circleImageView);

        // set grey version off image
        this.circleOff = UserPath.getImageViewSetWidthHeight(UserPath.Circle,50,50,true,true)
                .getImage();
        this.circleOn = this.circleImageView.getImage();

        // set circle position to middle
        this.circleImageView.setLayoutX(imageView.getLayoutX() + imageView.getBoundsInParent().getWidth() / 2 -
                this.circleImageView.getFitWidth() / 2);
        this.circleImageView.setLayoutY(imageView.getLayoutY() + imageView.getBoundsInParent().getHeight() / 2 -
                this.circleImageView.getFitHeight() / 2);
        imageViews.add(this.circleImageView);

        // add animation for circle
        circleAnimation = new Timeline(new KeyFrame(Duration.millis(10000), new KeyValue(
                this.circleImageView.rotateProperty(), 360)));
        circleAnimation.setCycleCount(100000);
        circleAnimation.play();

    }

    private double getCirclePointX(ImageView imageView1){
        ImageView imageView = getImageObject();
        return imageView.getLayoutX() + imageView.getBoundsInParent().getWidth()/2 - imageView1.getFitWidth()/2;
    }
    private double getCirclePointY(ImageView imageView1){
        ImageView imageView = getImageObject();
        return imageView.getLayoutY() + imageView.getBoundsInParent().getHeight()/2 - imageView1.getFitHeight()/2;
    }

    @Override
    public void connect(Information information) {
        /*
        * connect college's middle point and information's nearest point
        * */
        List<Point2D> point2DList = UtilityFunctions.getPointListPane(information.getPane());
        Point2D first =     UtilityFunctions.getCenterObject(this.getImageObject());
        Point2D second =    UtilityFunctions.getMinPoint(first,point2DList);
        Pane parent = (Pane) information.getPane().getParent();
        Connect connect = new Connect(information,this,parent);
        connect.setLine(first,second, Arrow.ArrowType.TwoDirection);
    }

    @Override
    public void on() {
        if(!active) {
            active = true;
            for(Connect connect : connectedObjectConnectHashMap.values()){
                connect.on();
            }
            this.getImageObject().setImage(image);
            this.circleImageView.setImage(circleOn);
            circleAnimation.play();
        }
    }

    @Override
    public void off() {
        if(active) {
            active = false;
            for(Connect connect: connectedObjectConnectHashMap.values()){
                connect.off();
            }
            this.getImageObject().setImage(greyImage);
            this.circleImageView.setImage(circleOff);
            circleAnimation.pause();
        }
    }

    @Override
    public void connect(Connect connect, ConnectedObject key) {
        super.connect(connect, key);
    }

    @Override
    public void disConnect(ConnectedObject connectedObject) {
        super.disConnect(connectedObject);
        Connect connect = connectedObjectConnectHashMap.get(connectedObject);
        if(connect != null) {
            connectedObjectConnectHashMap.remove(connectedObject);
            connect.remove();
        }
    }

    public void setLayoutX(double val){
        getImageObject().setLayoutX(val);
        for(ImageView imageView:imageViews){
            imageView.setLayoutX(getCirclePointX(imageView));
        }
    }
    public void setLayoutY(double val){
        getImageObject().setLayoutY(val);
        for(ImageView imageView:imageViews){
            imageView.setLayoutY(getCirclePointY(imageView));
        }
    }

    /**
    * get config object of college
    * */
    public Config CollegeToConfig(){
        Config config = new Config();
        config.configCollege = new ConfigCollege();
        config.configCollege.path = this.userPath;
        config.configCollege.cordX = getImageObject().getLayoutX();
        config.configCollege.cordY = getImageObject().getLayoutY();
        config.configCollege.width = getImageObject().getFitWidth();
        config.configCollege.height = getImageObject().getFitHeight();
        return config;
    }

    public Point2D getCenterCirclePoint2D(){
        return UtilityFunctions.getCenterObject(circleImageView);
    }

}
