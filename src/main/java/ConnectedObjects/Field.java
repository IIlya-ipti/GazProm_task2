package ConnectedObjects;

import engine.UtilityFunctions;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Field extends UsedObject{
    private Menu menu;
    private final List<College> collegeList = new ArrayList<>();

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
        List<Point2D> point2DList = UtilityFunctions.getPipePointsList(this.getImageObject());

        // add the three closest collages to the array
        UtilityFunctions.filterColleges(colleges, point2DList, this.collegeList);
    }
}
