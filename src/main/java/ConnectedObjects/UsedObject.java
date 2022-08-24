package ConnectedObjects;

import Objects.Arrow;
import engine.*;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class UsedObject implements ConnectedObject, Animation {
    protected final ImageView imageObject;

    /**
     * map to find a connection by entity
     * */
    protected final HashMap<ConnectedObject, Connect> connectedObjectConnectHashMap = new HashMap<>();

    protected boolean active;

    public UsedObject(ImageView imageView){
        this.imageObject = imageView;
        active = true;
        //off();
    }

    @Override
    public void connect(Information information) {
        information.connect(this, Plan.LONG);
    }

    @Override
    public void connect(Worker worker) {
        connect(worker, Plan.LONG);

    }

    @Override
    public void connect(UsedObject usedObject, Plan plan) {
        Point2D first = UtilityFunctions.getCenterObject(imageObject);
        Point2D second = UtilityFunctions.getCenterObject(usedObject.getImageObject());
        Pane pane = (Pane) imageObject.getParent();
        Connect connect = new Connect(usedObject,this,pane);
        connect.setOneAnimation(this);
        connect.setTwoAnimation(usedObject);
        connect.setLine(first,second, Arrow.ArrowType.OneDirection,5,20);
    }

    @Override
    public void connect(Pipe pipe, Plan plan) {
        pipe.connect(this,plan);
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

    public ImageView getImageObject() {
        return imageObject;
    }

    @Override
    public void on() {
        if(!active) {
            imageObject.setDisable(false);
            imageObject.setVisible(true);
            active = !active;
            for (Connect connect : connectedObjectConnectHashMap.values()) {
                if(!connect.isActive()) {
                    connect.on();
                }
            }
        }
    }

    @Override
    public void off() {
        if(active) {
            imageObject.setDisable(true);
            imageObject.setVisible(false);
            active = !active;
            for (Connect connect : connectedObjectConnectHashMap.values()) {
                if(connect.isActive()) {
                    connect.off();
                }
            }

        }
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
