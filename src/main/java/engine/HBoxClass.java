package engine;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HBoxClass {
    private static final double SPACING = 20;
    private final double WIDTH_CONST;
    private final List<UserPath> userPathList;
    private final HBox hbox;


    public HBoxClass(HBox hBox, List<UserPath> userPathList, double WIDTH_CONST){
        this.WIDTH_CONST = WIDTH_CONST;
        this.hbox = hBox;
        this.userPathList = userPathList;
        hbox.setStyle(Styles.styleHBox);
        setUserPathList(userPathList);

        // correcting hBox
        hbox.setSpacing(SPACING);
        hbox.setScaleX(0.6);
        hbox.setScaleY(0.6);
        hbox.setLayoutY(hbox.getLayoutY() - 30);
        hbox.setLayoutX(hbox.getLayoutX() - 60);
    }
    private void setUserPathList(List<UserPath> userPathList){
        for(UserPath userPath: userPathList){
            hbox.getChildren().add( UserPath.getImageViewSetWidthHeight(userPath,WIDTH_CONST,0,true));
        }
    }
    public void off(){
        hbox.setDisable(true);
        hbox.setVisible(false);
    }
    public void on(){
        hbox.setDisable(false);
        hbox.setVisible(true);
    }

    private void updateHBoxChild(int index){
        if(index < hbox.getChildren().size()){
            Node node = hbox.getChildren().get(index);
            node.setEffect(new DropShadow(30, Color.DODGERBLUE));
            Marker.actualUserPath = userPathList.get(index);
        }
    }
    private void deleteHBoxChild(int index){
        if(index < hbox.getChildren().size()){
            Node node = hbox.getChildren().get(index);
            node.setEffect(null);
        }
    }
    public void setMouseEvent(){
        AtomicInteger indexChild = new AtomicInteger(0);
        updateHBoxChild(indexChild.get());

        hbox.setOnMouseClicked(mouseEvent -> {
            int index = (int) (mouseEvent.getX()/(hbox.getWidth()/(hbox.getWidth()/(WIDTH_CONST + 20))));
            if(index < hbox.getChildren().size()){
                Node node = hbox.getChildren().get(index);
                if(mouseEvent.getX() <= node.getBoundsInParent().getMaxX()){
                    updateHBoxChild(index);
                    if(indexChild.get() != index) {
                        deleteHBoxChild(indexChild.get());
                        indexChild.set(index);
                    }
                }
            }
        });
    }
}
