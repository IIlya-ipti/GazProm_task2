package engine;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import java.util.*;

public class Wrapper {
    private final Pane parent;
    private Pipe pipe;
    private final List<Point2D>     pipesPoints;
    private final List<Marker>       markerList =                    new ArrayList<>();
    private final List<Line>         lineList =                      new ArrayList<>();
    Wrapper(Pipe pipe, Pane pane){
        this.parent = pane;
        this.pipe = pipe;
        this.pipesPoints = UtilityFunctions.getPipePointsList(pipe.getImageView());

    }
    public void connect(Marker marker){

        marker.connect(this);
        markerList.add(marker);

        // create line
        Point2D firstPoint = UtilityFunctions.getCenterObject(marker.getImageView());
        Point2D secondPoint = UtilityFunctions.getMinPoint(firstPoint,pipesPoints);

        Line line = new Line(firstPoint.getX(),firstPoint.getY(),secondPoint.getX(),secondPoint.getY());
        line.setScaleY(0.9);
        line.setScaleX(0.9);
        parent.getChildren().add(line);
        lineList.add(line);
    }

    public void disConnect(Marker marker){
        int index = markerList.indexOf(marker);
        parent.getChildren().remove(lineList.get(index));
        lineList.remove(index);
        markerList.remove(index);
    }

}
