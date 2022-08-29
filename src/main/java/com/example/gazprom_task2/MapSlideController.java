package com.example.gazprom_task2;

import ConnectedObjects.College;
import ConnectedObjects.Field;
import ConnectedObjects.Menu;
import ConnectedObjects.Pipe;
import engine.*;
import Objects.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import parser.Config;
import parser.Parser;

public class MapSlideController implements Initializable {
    public Parent first;
    public Parent second; // next slide
    private final List<Pipe> pipes =        new ArrayList<>();
    private final List<College> colleges =  new ArrayList<>();
    private final List<Field> fieldList =  new ArrayList<>();

    public void setNextScene(Parent one, Parent two){
        this.first = one;
        this.second = two;
    }

    @FXML
    private Pane paneMap;

    @FXML
    private Pane parentPane;

    @FXML
    private Button shortPlan;

    @FXML
    private Button longPlan;

    private VboxList pipeList;

    /**
     * press exit button action
     * */
    @FXML
    private void ExitAction(ActionEvent event) {
        ((Stage)paneMap.getScene().getWindow()).close();
        StringBuilder vl = new StringBuilder("");
        for(Pipe pipe : pipes){
            vl.append(pipe.toConfig());
        }
        for(Field field: fieldList){
            vl.append(field.toConfig());
        }
        for(College college:colleges){
            vl.append(college.toConfig());
        }

        try(FileWriter writer = new FileWriter("configs/config_copy.txt", false))
        {
            writer.write(vl.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * shotPlan button (and long plan)
     * */
    @FXML
    private void shortPlanAction(ActionEvent event) {
        first.setVisible(false);
        first.setDisable(true);
        second.setVisible(true);
        second.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        shortPlan.  setFont(Font.font(Styles.fontFamily,10));
        longPlan.   setFont(Font.font(Styles.fontFamily,10));


        // list of pipes
        pipeList = new VboxList(10.0);
        pipeList.setLayoutX(20);
        pipeList.setLayoutY(50);

        // set parent node
        paneMap.getChildren().add(pipeList.getVBox());

        // parse a config
        try {
            readConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method for parse config
     * */
    private void readConfig() throws IOException {
        Parser parser = new Parser("configs/config.txt");
        List<Config> configs = parser.getConfigs();

        for(Config config : configs){

            // set colleges
            if(config.configCollege != null){
                College college = new College(paneMap,config.configCollege);
                college.setLayoutX(config.configCollege.cordX);
                college.setLayoutY(config.configCollege.cordY);
                colleges.add(college);
            }
            Menu menu = null;

            // set menu
            if (config.configTable != null) {
                menu = new Menu(paneMap);
                menu.setConfig(config.configTable);
            }

            // set pipe
            if(config.configPipe != null) {
                Pipe pipe = new Pipe(paneMap,config.configPipe);
                pipeList.add(config.configPipe.name, pipe);
                pipes.add(pipe);
                if(menu != null){
                    pipe.connect(menu);
                }
                pipe.off();
            }

            // set field
            if(config.configField != null){
                Field field = new Field(paneMap,config.configField);
                pipeList.add(config.configField.name,field);
                fieldList.add(field);
                if(menu != null){
                    field.connect(menu);
                }
                field.off();
            }
        }
        for(Pipe pipe:pipes){
            pipe.setColleges(colleges);
        }
        for(Field field: fieldList){
            field.setColleges(colleges);
        }
        VboxList.collegeList = colleges;

    }

    /**
     * update map node after set new bounds (scene bounds)
     * */
    public void update(Scene scene){

        double width = scene.getWidth();
        double height = scene.getHeight();

        // adapting to the screen
        double coeff = Math.min(width/parentPane.getPrefWidth(),height/parentPane.getPrefHeight());
        parentPane.setScaleX(coeff);
        parentPane.setScaleY(coeff);

        // set to middle
        parentPane.setTranslateX((parentPane.getPrefWidth() * coeff - parentPane.getPrefWidth())/2
             + (width - parentPane.getPrefWidth() * coeff)/2
        );
        parentPane.setTranslateY((parentPane.getPrefHeight() * coeff - parentPane.getPrefHeight())/2
                + (height - (parentPane.getPrefHeight() + 28)* coeff)/2
        );
    }
    public Pane getPane(){
        return parentPane;
    }
}
