package engine;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Pair;

import java.util.*;

public class Wrapper implements ImageInterface {
    private final Pane parent;
    private Pipe pipe;
    private final List<Point2D>     pipesPoints;
    private final Set<Marker>       markerSet =                      new HashSet<>();
    private final List<Marker>      markerList =                    new ArrayList<>();
    private final List<Line>        lineList =                      new ArrayList<>();
    Wrapper(Pipe pipe, Pane pane){
        this.parent = pane;
        this.pipe = pipe;
        this.pipesPoints = UtilityFunctions.getPipePointsList(pipe.getImageView());
        off();

    }
    public void off(){
        pipe.off();
        for(Line line : lineList){
            line.setVisible(false);
            line.setDisable(true);
        }
    }
    public void on(){
        pipe.on();
        for(Line line : lineList){
            line.setVisible(true);
            line.setDisable(false);
        }
    }
    public void connect(Marker marker){
        if(markerSet.contains(marker)){
            return;
        }

        marker.connect(this);
        markerList.add(marker);
        markerSet.add(marker);

        // create line
        Point2D firstPoint = UtilityFunctions.getCenterObject(marker.getImageView());
        Point2D secondPoint = UtilityFunctions.getMinPoint(firstPoint,pipesPoints);

        Line line = new Line(firstPoint.getX(),firstPoint.getY(),secondPoint.getX(),secondPoint.getY());
        line.setScaleY(0.9);
        line.setScaleX(0.9);
        parent.getChildren().add(line);
        lineList.add(line);

        if(!pipe.getImageView().isVisible()) {
            line.setVisible(false);
            line.setDisable(true);
        }
    }

    public void disConnect(Marker marker){
        int index = markerList.indexOf(marker);
        if(index != -1) {
            markerSet.remove(markerList.get(index));
            parent.getChildren().remove(lineList.get(index));
            lineList.remove(index);
            markerList.remove(index);
        }
    }

    @Override
    public boolean contains(double X, double Y) {
        return pipe.contains(X,Y);
    }
}
