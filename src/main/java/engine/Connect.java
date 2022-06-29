package engine;

import java.util.ArrayList;
import java.util.List;

public class Connect {
    private Wrapper wrapper = null;
    private final List<Marker> markerList= new ArrayList<>();
    public void clear(){
        for(Marker marker : markerList){
            marker.clickOff();
        }
        markerList.clear();
    }
    public void addMarker(Marker marker){
        markerList.add(marker);
    }
    public void deleteMarker(Marker marker){
        markerList.remove(marker);
    }
    public void setWrapper(Wrapper wrapper){
        this.wrapper = wrapper;
    }
    public void connect(){
        if(wrapper != null) {
            for (Marker marker : markerList) {
                wrapper.connect(marker);
            }
            clear();
        }
    }
    public void disConnect(){
        if(wrapper != null) {
            for (Marker marker : markerList) {
                wrapper.disConnect(marker);
            }
            clear();
        }
    }
}
