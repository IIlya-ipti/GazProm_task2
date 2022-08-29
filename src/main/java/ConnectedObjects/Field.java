package ConnectedObjects;

import engine.UserPath;
import engine.UtilityFunctions;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import parser.Config;
import parser.ConfigField;
import parser.ConfigPipe;
import parser.ConfigTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * class for work with deposits
 * */
public class Field extends UsedObject{
    private Menu menu;
    private String name;
    public UserPath path;
    private final List<College> collegeList = new ArrayList<>();

    public Field(Pane parentPane, ConfigField configField){
        super(UserPath.getImageViewSetWidthHeight(
                configField.path,
                configField.width,
                configField.height,
                true));
        this.name = configField.name;
        this.path = configField.path;
        getImageObject().setLayoutX(configField.cordX);
        getImageObject().setLayoutY(configField.cordY);

        parentPane.getChildren().add(getImageObject());

    }
    public Field(ImageView imageView) {
        super(imageView);
    }

    @Override
    public void connect(Menu menu){
        Pane pane = (Pane) menu.getPane().getParent();
        new Connect(this,menu,pane);
        this.menu = menu;
    }

    public void setColleges(List<College> colleges){
        // all points off pipe
        Point2D point2DList = UtilityFunctions.getCenterObject(this.imageObject);

        // add the three closest collages to the array
        UtilityFunctions.filterColleges(colleges, List.of(point2DList), this.collegeList);
    }
    @Override
    public void on(){
        super.on();
        // enable colleges (first three)
        for (College college:collegeList){
            college.on();
        }
        menu.on();
    }
    @Override
    public void off(){
        super.off();
        menu.off();

        // disable colleges (first three)
        for (College college:collegeList){
            college.off();
        }
    }

    public Config toConfig(){
        Config config = new Config();
        config.configField = new ConfigField();
        config.configField.height = this.getImageObject().getFitHeight();
        config.configField.width = this.getImageObject().getFitWidth();
        config.configField.cordX = this.getImageObject().getLayoutX();
        config.configField.cordY = this.getImageObject().getLayoutY();
        config.configField.name  = this.name;
        config.configField.path  = this.path;
        if(this.menu != null) {
            config.configTable = menu.toConfig();
        }
        return config;
    }
}
