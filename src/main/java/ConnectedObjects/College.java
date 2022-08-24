package ConnectedObjects;

import Objects.Arrow;
import engine.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import parser.Config;
import parser.ConfigCollege;

import java.util.ArrayList;
import java.util.List;

public class College extends UsedObject {
    private final ArrayList<ImageView> imageViews = new ArrayList<>();
    private UserPath userPath;
    public College(UserPath userPath,double width, double height,Pane pane){
        this(
            UserPath.getImageViewSetWidthHeight(
                    userPath,
                    width,
                    height,
                    true
                    ),pane
        );
        this.userPath = userPath;

    }
    public College(ImageView imageView, Pane pane) {
        super(imageView);
        pane.getChildren().add(imageView);

        // make a circle on the background
        ImageView imageView1 = UserPath.getImageViewSetWidthHeight(UserPath.Circle, 50, 50, true);
        pane.getChildren().add(imageView1);

        // set circle position to middle
        imageView1.setLayoutX(imageView.getLayoutX() + imageView.getBoundsInParent().getWidth() / 2 - imageView1.getFitWidth() / 2);
        imageView1.setLayoutY(imageView.getLayoutY() + imageView.getBoundsInParent().getHeight() / 2 - imageView1.getFitHeight() / 2);
        imageViews.add(imageView1);

        // add animation for circle
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10000), new KeyValue(imageView1.rotateProperty(), 360)));
        timeline.setCycleCount(100000);
        timeline.play();


        imageView.toFront();
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
            imageObject.setDisable(false);
            imageObject.setVisible(true);
            active = !active;
            for(ImageView imageView: imageViews){
                imageView.setVisible(true);
                imageView.setDisable(false);
            }
            for(Connect connect : connectedObjectConnectHashMap.values()){
                connect.on();
            }
        }
    }

    @Override
    public void off() {
        if(active) {
            getImageObject().setDisable(true);
            getImageObject().setVisible(false);
            active = !active;
            for(ImageView imageView: imageViews){
                imageView.setVisible(false);
                imageView.setDisable(true);
            }
            for(Connect connect: connectedObjectConnectHashMap.values()){
                connect.off();
            }
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
}
