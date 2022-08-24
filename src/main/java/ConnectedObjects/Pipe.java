package ConnectedObjects;

import Objects.Arrow;
import engine.UtilityFunctions;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import parser.Config;
import parser.ConfigTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Pipe implements ConnectedObject{
    private final HashMap<ConnectedObject, Connect> connectedObjectConnectHashMap = new HashMap<>();

    // all lines of pipe
    private final List<List<Line>> listsOfLines = new ArrayList<>();

    // list of pipe's circles
    private final List<Node> nodesList = new ArrayList<>();
    private Menu menu = null;
    private final Pane paneParent;

    // coords of last select point
    private double endX = -1;
    private double endY = -1;

    // name of pipe
    private String name;

    public Pipe(Pane paneParent){
        this.paneParent = paneParent;
    }

    public Pane getPaneParent() {
        return paneParent;
    }

    /**
     * add new line (with two circles)
     * */
    public void addLine(double... doubleList){
        List<Line> arrayList = new ArrayList<>();
        listsOfLines.add(arrayList);
        addPointLine(arrayList, doubleList);
    }
    public void addLine(int index, double... doubleList){
        if(index == -1){
            index = listsOfLines.size() - 1;
        }
        if(listsOfLines.size() == 0){
            addLine(doubleList);
        }else {
            List<Line> arrayList = listsOfLines.get(index);
            addPointLine(arrayList, doubleList);
        }
    }

    private void addPointLine(List<Line> arrayList, double[] doubleList) {
        Circle circleStart = new Circle(doubleList[0],doubleList[1],2);
        Circle circleEnd = new Circle(doubleList[doubleList.length - 2],doubleList[doubleList.length - 1],2);
        paneParent.getChildren().addAll(circleStart,circleEnd);
        nodesList.add(circleStart);
        nodesList.add(circleEnd);
        for(int i = 0;i + 3 < doubleList.length;i+=2){
            Line line = new Line(doubleList[i],
                    doubleList[i + 1],
                    doubleList[i + 2],
                    doubleList[i + 3]);
            endX = doubleList[i + 2];
            endY = doubleList[i + 3];
            paneParent.getChildren().add(line);
            arrayList.add(line);
            line.setStrokeDashOffset(5);
            line.setVisible(false);
            line.setDisable(true);
        }
    }

    public void addPoint(double x, double y){
        if(endX != -1) {
            addLine(-1,endX, endY, x, y);
        }else{
            listsOfLines.add(new ArrayList<>());
            endX = x;
            endY = y;
            Circle circle = new Circle(endX,endY,2);
            nodesList.add(circle);
            paneParent.getChildren().add(circle);
        }
    }
    public void addPointNewLine(double x, double y){
        if(endX != -1) {
            addLine(endX, endY, x, y);
        }else{
            endX = x;
            endY = y;
            Circle circle = new Circle(endX,endY,2);
            nodesList.add(circle);
            paneParent.getChildren().add(circle);
        }
    }
    public void reset(){
        endY = -1;
        endX = -1;
    }



    @Override
    public void connect(Information information) {

    }

    public void connect(Menu menu){

        List<Point2D> point2DList = UtilityFunctions.getListLinePoints(this.getAllLines());

        Pane pane = (Pane) menu.getPane().getParent();

        if(point2DList.size() > 0){
            Point2D point2D = UtilityFunctions.getMenuPoint(point2DList,menu.getPane().getPrefWidth(),menu.getPane().getPrefHeight(),pane.getBoundsInLocal());

            menu.getPane().setLayoutX(point2D.getX());
            menu.getPane().setLayoutY(point2D.getY());
        }

        new Connect(this,menu,pane);
        this.menu = menu;
    }

    @Override
    public void connect(Worker worker) {
        connect(worker, Plan.SHORT);
    }

    @Override
    public void connect(UsedObject usedObject, Plan plan) {
        connectToLines(usedObject,plan);
    }

    private void connectToLines(UsedObject usedObject, Plan plan){
        List<Line> linePipe = listsOfLines.get(0);
        if(linePipe != null) {
            List<Point2D> point2DList = UtilityFunctions.getListLinePoints(linePipe);
            connectPoints(usedObject, point2DList, (Pane) linePipe.get(0).getParent());
        }
    }

    private void connectPoints(UsedObject usedObject, List<Point2D> point2DList, Pane parent) {
        Point2D first = UtilityFunctions.getCenterObject(usedObject.getImageObject());
        Point2D second = UtilityFunctions.getMinPoint(first,point2DList);
        Connect connect = new Connect(this,usedObject, parent);
        connect.setLine(first,second, Arrow.ArrowType.OneDirection,5,5);
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


    public void on(){
        for(Node node : nodesList){
            node.setVisible(true);
            node.setDisable(false);
        }
        if(menu != null) {
            menu.on();
        }
        for(List<Line> lineList: listsOfLines){
            for(Line line: lineList){
                line.setVisible(true);
                line.setDisable(false);
            }
        }
    }
    public void off(){
        for(Node node : nodesList){
            node.setVisible(false);
            node.setDisable(true);
        }
        this.menu.off();
        for(List<Line> lineList: listsOfLines){
            for(Line line: lineList){
                line.setVisible(false);
                line.setDisable(true);
            }
        }
    }

    public List<Line> getAllLines(){
        List<Line> lines = new ArrayList<>();
        for(List<Line> imageViews : listsOfLines){
            lines.addAll(imageViews);
        }
        return lines;
    }
    public double[][] getAllPoints(){
        double[][] doublesLine = new double[listsOfLines.size()][];
        for(int i = 0;i < listsOfLines.size();i++){
            doublesLine[i] = getOneListPoints(listsOfLines.get(i));
        }
        return doublesLine;
    }
    public double[] getOneListPoints(List<Line> lineList){
        int listSize = lineList.size();
        double[] doubleList = new double[listSize * 2 + 2];
        for (int i = 0; i < listSize; i++) {
            doubleList[2 * i] = lineList.    get(i).getStartX();
            doubleList[2 * i + 1] = lineList.get(i).getStartY();
        }
        doubleList[listSize * 2]     = lineList.get(listSize - 1).getEndX();
        doubleList[listSize * 2 + 1] = lineList.get(listSize - 1).getEndY();
        return doubleList;
    }
    public void setConfig(Config config){
        this.name = config.name;
        if(config.coords != null) {
            for(double[] doubleValues : config.coords) {
                addLine(doubleValues);
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Config pipeToConfig(){
        Config config = new Config();
        config.name = this.name;
        config.coords = this.getAllPoints();
        if(this.menu != null) {
            config.configTable = new ConfigTable();
            config.configTable.name = this.menu.getName().getText();
            config.configTable.period = this.menu.getPeriod().getText();
            config.configTable.longProject = this.menu.getLongProject().getText();
            config.configTable.shortProject = this.menu.getShortProject().getText();
            config.configTable.totalWorkers = this.menu.getTotalWorkers().getText();
            config.configTable.vlA = String.valueOf(this.menu.getPane().getLayoutX());
            config.configTable.vlB = String.valueOf(this.menu.getPane().getLayoutY());
        }
        return config;
    }
}
