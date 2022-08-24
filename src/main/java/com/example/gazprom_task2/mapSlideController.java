package com.example.gazprom_task2;

import ConnectedObjects.College;
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

public class mapSlideController implements Initializable {
    public Parent first;
    public Parent second; // next slide
    private final List<Pipe> pipes =        new ArrayList<>();
    private final List<College> colleges =  new ArrayList<>();

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

    @FXML
    void ExitAction(ActionEvent event) {
        ((Stage)paneMap.getScene().getWindow()).close();
        StringBuilder vl = new StringBuilder("");
        for(Pipe pipe : pipes){
            vl.append(pipe.pipeToConfig());
        }
        for(College college:colleges){
            vl.append(college.CollegeToConfig());
        }

        try(FileWriter writer = new FileWriter("configs/config_new.txt", false))
        {
            writer.write(vl.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void DynamicAction(ActionEvent event) throws IOException {
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
        readConfig();
    }

    /**
     * this method for parse config
     * */
    private void readConfig(){
        Parser parser = new Parser("configs/config.txt");
        List<Config> configs = parser.getConfigs();

        for(Config config : configs){
            if(config.configCollege != null){
                // set colleges
                College college = new College(
                        config.configCollege.path,
                        config.configCollege.width,
                        config.configCollege.height,
                        paneMap
                );
                college.setLayoutX(config.configCollege.cordX);
                college.setLayoutY(config.configCollege.cordY);
                colleges.add(college);
            }
            Menu menu = null;
            if (config.configTable != null) {
                menu = new Menu(paneMap);
                menu.setFieldsOfConfig(config.configTable);
            }
            if(config.name != null) {
                Pipe pipe2 = new Pipe(paneMap);
                pipe2.setConfig(config);
                pipeList.add(config.name, pipe2);
                pipes.add(pipe2);
                if(menu != null){
                    pipe2.connect(menu);
                }
                pipe2.off();
            }
        }
    }

    void update(Scene scene){

        double width = scene.getWidth();
        double height = scene.getHeight();

        // adapting to the screen
        double coeff = Math.min(width/parentPane.getPrefWidth(),height/parentPane.getPrefHeight());
        parentPane.setScaleX(coeff);
        parentPane.setScaleY(coeff);

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
