package engine;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;


public class Engine {
    public static Status status = Status.USER;
    private final List<Wrapper> wrapperList = new ArrayList<>();
    private final List<Marker> markerArrayList = new ArrayList<>();


    public Engine(List<Pipe> pipeViews, List<Pair<Marker,Integer>> markerList, Pane pane){
        for(int i = 0;i < pipeViews.size();i++){
            wrapperList.add(new Wrapper(pipeViews.get(i),pane));
            pipeViews.get(i).setIndex(i);
        }
        for(Pair<Marker,Integer> pair : markerList){
            markerArrayList.add(pair.getKey());
            wrapperList.get(pair.getValue()).connect(pair.getKey());
        }

    }

    public List<Marker> getMarkerArrayList() {
        return markerArrayList;
    }

    public List<Wrapper> getWrapperList() {
        return wrapperList;
    }

    public void activeOn(int indexPipe){

    }
    public void update(){
        //for(Wrapper wrapper : wrapperList){
        //    wrapper.update();
        //}
    }
    public void activeOff(){
        //for(Wrapper wrapper : wrapperList){
        //    if(wrapper.isActive()){
        //        wrapper.off();
        //    }
        //}
    }
}
