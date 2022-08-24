package ConnectedObjects;

import Objects.Arrow;
import engine.UtilityFunctions;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.List;



/**
 * this class not actual
 *
 * information object (pane with important information)
 * */
@Deprecated
public class Information implements ConnectedObject, Animation {
    private final Pane pane;
    private final HashMap<ConnectedObject, Connect> connectedObjectConnectHashMap = new HashMap<>();
    protected boolean active = true;
    public Information(Pane pane){
        this.pane = pane;
    }
    public Information(){
        this.pane = new Pane();
    }

    public HashMap<ConnectedObject, Connect> getConnectedObjectConnectHashMap() {
        return connectedObjectConnectHashMap;
    }

    public void setParent(Pane parent){
        parent.getChildren().add(pane);
    }

    @Override
    public void connect(Information information) {

    }

    @Override
    public void connect(Worker worker) {
    }

    @Override
    public void connect(UsedObject usedObject, Plan plan) {
        List<Point2D> point2DList = UtilityFunctions.getPointListPane(pane);
        Point2D first =     UtilityFunctions.getCenterObject(usedObject.getImageObject());
        Point2D second =    UtilityFunctions.getMinPoint(first,point2DList);
        Pane parent = (Pane) pane.getParent();
        Connect connect = new Connect(this,usedObject,parent);
        connect.setLine(first,second, Arrow.ArrowType.TwoDirection);
        connectedObjectConnectHashMap.put(usedObject,connect);
    }

    @Override
    public void connect(Pipe pipe, Plan plan) {
    }

    @Override
    public void connect(Connect connect, ConnectedObject key) {
        connectedObjectConnectHashMap.put(key,connect);
    }

    @Override
    public void disConnect(ConnectedObject connectedObject) {
        Connect connect = connectedObjectConnectHashMap.get(connectedObject);
        if(connect != null) {
            connectedObjectConnectHashMap.remove(connectedObject);
            connect.remove();
        }
    }

    @Override
    public void on() {
        if(!active) {
            active = true;
            pane.setVisible(true);
            pane.setDisable(false);
        }
    }

    @Override
    public void off() {
        if(active) {
            pane.setVisible(false);
            pane.setDisable(true);
            active = false;
        }

    }

    @Override
    public boolean isActive() {
        return active;
    }

    public Pane getPane() {
        return pane;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
