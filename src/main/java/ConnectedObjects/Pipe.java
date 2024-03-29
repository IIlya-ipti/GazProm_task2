package ConnectedObjects;

import Objects.Arrow;
import engine.UtilityFunctions;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import parser.Config;
import parser.ConfigPipe;
import parser.ConfigTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Pipe implements ConnectedObject,Animation{
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

    // nearest objects (colleges)
    private final List<College> collegeList = new ArrayList<>();

    public Pipe(Pane paneParent, ConfigPipe configPipe){
        this.paneParent = paneParent;
        this.setConfig(configPipe);
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
    /**
     * add new point at line
     * */
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

    /**
     * add new lines of doublelist (connect points) to arraylist
     * */
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

    /**
     * add new point to last line
     * */
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

    /**
     * to add points to new line
     * */
    public void reset(){
        endY = -1;
        endX = -1;
    }



    @Override
    public void connect(Information information) {

    }

    @Override
    public void connect(Menu menu){
        Pane pane = (Pane) menu.getPane().getParent();

        //List<Point2D> point2DList = UtilityFunctions.getListLinePoints(this.getAllLines());
        // set new layout points for menu (now this coords in config.txt)
        /*if(point2DList.size() > 0){
            //Point2D point2D = UtilityFunctions.getMenuPoint(point2DList,menu.getPane().getPrefWidth(),menu.getPane().getPrefHeight(),pane.getBoundsInLocal());

            //menu.getPane().setLayoutX(point2D.getX());
            //menu.getPane().setLayoutY(point2D.getY());
        }*/

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

    @Override
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
        // enable colleges (first three)
        for (College college:collegeList){
            college.on();
        }
    }

    @Override
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
        // disable colleges (first three)
        for (College college:collegeList){
            college.off();
        }
    }

    @Deprecated
    @Override
    public boolean isActive() {
        return false;
    }

    public List<Line> getAllLines(){
        List<Line> lines = new ArrayList<>();
        for(List<Line> lineList : listsOfLines){
            lines.addAll(lineList);
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
    public void setConfig(ConfigPipe configPipe){
        this.name = configPipe.name;
        if(configPipe.coords != null) {
            for(double[] doubleValues : configPipe.coords) {
                addLine(doubleValues);
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Config toConfig(){
        Config config = new Config();
        config.configPipe = new ConfigPipe();
        config.configPipe.name = this.name;
        config.configPipe.coords = this.getAllPoints();
        if(this.menu != null) {
            config.configTable = menu.toConfig();
        }
        return config;
    }


    public void setColleges(List<College> colleges){
        // all points off pipe
        List<Point2D> point2DList = UtilityFunctions.getListLinePoints(
                this.getAllLines()
        );

        // add the three closest collages to the array
        UtilityFunctions.filterColleges(colleges, point2DList, this.collegeList);
    }

}
