package ConnectedObjects;

import engine.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import parser.ConfigTable;


public class Menu extends Information implements Animation {
    static Menu actualMenu = null;

    /**
     * information of pipe
     * */
    private Text name;
    private Text longProject;
    private Text shortProject;
    private Text totalWorkers;
    private Text period;

    @Override
    public void connect(Pipe pipe, Plan plan) {
    }
    public void connect(Pipe pipe) {
        pipe.connect(this);
    }

    public void setShortProject(String shortProject) {
        this.shortProject.setText(shortProject);
        setMiddle(this.shortProject,0 ,getPane().getPrefWidth()/2);
    }
    public void setLongProject(String longProject) {
        this.longProject.setText(longProject);
        setMiddle(this.longProject,getPane().getPrefWidth()/2 ,getPane().getPrefWidth());
    }
    public void setTotalWorkers(String totalWorkers) {
        this.totalWorkers.setText(totalWorkers);
        setMiddle(this.totalWorkers,0,getPane().getPrefWidth());
    }
    public void setPeriod(String period){
        this.period.setText(period);
        setMiddle(this.period,0,getPane().getPrefWidth());
    }
    public void setName(String name){
        this.name.setText(name);
        setMiddle(this.name,0,getPane().getPrefWidth());
    }

    public Menu(Pane parent){
        super();

        // set parent pane params (and set styles for pane)
        setParent(parent);
        getPane().getStylesheets().add("styles.css");
        getPane().setId("menu_item");
        getPane().setPrefWidth(119);
        getPane().setPrefHeight(166);

        // set inner of pane
        setSeparators();
        setFields();
        setUserFields();
    }
    private void setSeparators(){
        // lines
        Ellipse ellipse = new Ellipse(getPane().getPrefWidth()/2,getPane().getPrefHeight()* 3/4 - 20,0.3,15);
        getPane().getChildren().add(ellipse);
        for(int i = 1;i < 4;i++) {
            ellipse = new Ellipse(getPane().getPrefWidth() / 2, (getPane().getPrefHeight() - 50) / 4 * i, 50, 0.5);
            getPane().getChildren().add(ellipse);
        }
    }
    private double getLayoutField(int index){
        final double EPS = 10;
        return (getPane().getPrefHeight() - 50) / 4 *  index+ EPS;
    }
    private double getLayoutField(int index, double coefficientEps){
        final double EPS = 10;
        return (getPane().getPrefHeight() - 50) / 4 * index + coefficientEps * EPS;
    }
    private void setFields(){
        final double SIZE = 9;

        // set text
        Text textFirst =    new Text("Название:");
        Text textSecond =   new Text("Прогнозный период");
        Text textThird =    new Text("Общая численность");
        Text textFour =     new Text("Долгосрочное\nпланирование**");
        Text textFive =     new Text("Краткосрочное\nпланирование*");
        Text textSix =      new Text("** 10 лет.\n " +
                "     Расчет через регрессионный анализ");
        Text textSeven =    new Text("  * 3 года.\n" +
                "     Расчет по нормативным сборникам");

        // set fonts
        textFirst.setFont(  Font.font(Styles.fontFamily,SIZE));
        textSecond.setFont( Font.font(Styles.fontFamily,SIZE));
        textThird.setFont(  Font.font(Styles.fontFamily,SIZE));
        textFour.setFont(   Font.font(Styles.fontFamily,SIZE - 2));
        textFive.setFont(   Font.font(Styles.fontFamily,SIZE - 2));
        textSix.setFont(    Font.font(Styles.fontFamily,SIZE - 3));
        textSeven.setFont(  Font.font(Styles.fontFamily,SIZE - 3));

        // setLayout
        textFirst.setLayoutX(   17);
        textFirst.setLayoutY(   13);
        textSecond.setLayoutY(  getLayoutField(1));
        textThird.setLayoutY(   getLayoutField(2));
        textFour.setLayoutY(    getLayoutField(3));
        textFive.setLayoutY(    getLayoutField(3));
        textSix.setLayoutY(     148);
        textSeven.setLayoutY(   132);

        // set const width of first local pane
        final double width = textFour.getLayoutBounds().getWidth();

        // set position to middle (osX)
        setMiddle(textSecond,0,getPane().getPrefWidth());
        setMiddle(textThird,0,getPane().getPrefWidth());
        setMiddle(textFour,getPane().getPrefWidth()/2 ,getPane().getPrefWidth());
        setMiddle(textFive,width,10,getPane().getPrefWidth()/2);
        setMiddle(textSix,textSix.getLayoutBounds().getWidth(),0,getPane().getPrefWidth());
        setMiddle(textSeven,textSeven.getLayoutBounds().getWidth(),0,getPane().getPrefWidth());


        getPane().getChildren().addAll(textFirst,textSecond,textThird,textFour,textFive,textSix,textSeven);
    }
    private void setUserFields(){
        name =          new Text("null");
        period =        new Text("null");
        totalWorkers =  new Text("null");
        longProject =   new Text("null");
        shortProject =  new Text("null");

        name.           setFill(Styles.fieldUserColor);
        longProject.    setFill(Styles.fieldUserColor);
        shortProject.   setFill(Styles.fieldUserColor);
        totalWorkers.   setFill(Styles.fieldUserColor);
        period.         setFill(Styles.fieldUserColor);

        name.           setFont(Styles.fontUser);
        longProject.    setFont(Styles.fontUser);
        shortProject.   setFont(Styles.fontUser);
        totalWorkers.   setFont(Styles.fontUser);
        period.         setFont(Styles.fontUser);


        name.setLayoutY(23);
        period.setLayoutY(getLayoutField(1,2.3));
        totalWorkers.setLayoutY(getLayoutField(2,2.3));
        longProject.setLayoutY(getLayoutField(3,3));
        longProject.setLayoutX(70);
        shortProject.setLayoutX(12);


        setMiddle(name,0,getPane().getPrefWidth());
        setMiddle(period,0,getPane().getPrefWidth());
        setMiddle(totalWorkers,0,getPane().getPrefWidth());
        shortProject.setLayoutY(getLayoutField(3,3));

        getPane().getChildren().addAll(name,longProject,shortProject,totalWorkers,period);
    }

    /**
     * set text to middle of (minX ,maxX]
     * */
    private void setMiddle(Text text, double minX, double maxX){
        Bounds bounds = text.getLayoutBounds();
        double middleVal = ((maxX - minX) - bounds.getWidth() )/2;
        text.setLayoutX(middleVal + minX);
    }

    /**
     * set text to middle of (minX ,maxX] with const width
     * */
    private void setMiddle(Text text, double width, double minX, double maxX){
        double middleVal = (maxX - minX - width)/2;
        text.setLayoutX(middleVal + minX);
    }
    @Override
    public void connect(Worker worker) {
        //workerList.add(worker);
    }
    @Override
    public void on(){
        super.on();
        if(actualMenu != null) {
            // add animation to movement
            Timeline startPumpAnimation;
            KeyValue startXRect = new KeyValue(this.getPane().layoutXProperty(), this.getPane().getLayoutX(), Interpolator.LINEAR);
            KeyValue startYRect = new KeyValue(this.getPane().layoutYProperty(), this.getPane().getLayoutY(), Interpolator.LINEAR);
            this.getPane().setLayoutX(actualMenu.getPane().getLayoutX());
            this.getPane().setLayoutY(actualMenu.getPane().getLayoutY());
            KeyFrame frameRectStart = new KeyFrame(Duration.millis(1000), startXRect, startYRect);
            startPumpAnimation = new Timeline(frameRectStart);
            startPumpAnimation.play();
        }
        actualMenu = this;
        getPane().toFront();
    }
    @Override
    public void off() {
        super.off();
        getPane().setDisable(true);
        getPane().setVisible(false);
    }

    @Override
    public Pane getPane() {
        return super.getPane();
    }

    public Text getName() {
        return name;
    }

    public Text getPeriod() {
        return period;
    }

    public Text getLongProject() {
        return longProject;
    }

    public Text getShortProject() {
        return shortProject;
    }

    public Text getTotalWorkers() {
        return totalWorkers;
    }

    public void setFieldsOfConfig(ConfigTable configTable){
        this.setPeriod(configTable.period);
        this.setShortProject(configTable.shortProject);
        this.setName(configTable.name);
        this.setLongProject(configTable.longProject);
        this.setTotalWorkers(configTable.totalWorkers);
        if(configTable.cordX != null){
            getPane().setLayoutX(Double.parseDouble(configTable.cordX));
            getPane().setLayoutY(Double.parseDouble(configTable.cordY));
        }

    }
}
