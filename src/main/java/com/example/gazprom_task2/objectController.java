package com.example.gazprom_task2;

import engine.Engine;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class objectController implements Initializable {
    private Timeline startInfoTimeline;
    private Timeline endInfoTimeline;
    private Timeline startPumpAnimation;
    private Timeline endPumpAnimation;
    private Engine engine;
    public Parent first;
    public Parent second;
    private boolean infoAct = false;

    public void setNextScene(Parent one, Parent two){
        this.first = one;
        this.second = two;
    }

    @FXML
    private Button Exit;

    @FXML
    private ComboBox<String> yearID;

    @FXML
    public void ExitAction(ActionEvent actionEvent) {
        ((Stage)Exit.getScene().getWindow()).close();
    }

    @FXML
    private Pane TotalPane;

    @FXML
    private Pane PANE;

    @FXML
    private TextField MG;

    @FXML
    private TextField GPA;

    @FXML
    private TextField GRC;

    @FXML
    private VBox post;

    @FXML
    private TextFlow id;

    @FXML
    private Text employeesID;

    @FXML
    private Text employeesPost;

    @FXML
    private Text managersPost;

    @FXML
    private ScrollPane AAA;

    @FXML
    private Text managersID;

    @FXML
    private Text workersPost;

    @FXML
    private Text workersID;

    @FXML
    private Text totalID;

    @FXML
    private Text totalPost;

    @FXML
    private Pane workers;

    @FXML
    private Pane managers;

    @FXML
    private Pane emplyeers;

    @FXML
    private VBox ID;

    @FXML
    private Pane info;

    @FXML
    private Button score;

    /**
     * -500 MG
     * */
    @FXML
    void _500_action_first(ActionEvent event) {
        MG.setText(String.valueOf(Math.max(Double.parseDouble(MG.getText()) - 500, 0)));
    }

    /**
     * +500 NG
     * */
    @FXML
    void ___500_action_first(ActionEvent event) {
        MG.setText(String.valueOf(Double.parseDouble(MG.getText()) + 500));
    }

    /**
     * -1 GRC
     * */
    @FXML
    void _1_action_second(ActionEvent event) {
        GPA.setText(String.valueOf(Math.max(Integer.parseInt(GPA.getText()) - 1, 0)));
    }

    /**
     * +1 GRA
     * */
    @FXML
    void __1_action_second(ActionEvent event) {
        GPA.setText(String.valueOf(Integer.parseInt(GPA.getText()) + 1));

    }

    /**
     * -1 GRC
     * */
    @FXML
    void _1_action_third(ActionEvent event) {
        GRC.setText(String.valueOf(Math.max(Integer.parseInt(GRC.getText()) - 1, 0)));
    }

    /**
     * +1 GRC
     * */
    @FXML
    void __1_action_third(ActionEvent event) {
        GRC.setText(String.valueOf(Integer.parseInt(GRC.getText()) + 1));
    }


    /**
     * come back
     * */
    @FXML
    void returnAct(ActionEvent event) {
        first.setVisible(true);
        first.setDisable(false);
        second.setVisible(false);
        second.setDisable(true);
    }

    /**
     * calculate table
     * */
    @FXML
    void calculationOn(ActionEvent event) {
        engine.update(yearID.getValue(),
                Double.parseDouble(MG.getText()),
                Integer.parseInt(GPA.getText()),
                Integer.parseInt(GRC.getText()),
                true);
    }

    /**
     *  print (table) information of employers
     * */
    @FXML
    void employers_act(MouseEvent event) {
        engine.updateVals(employeesPost);
        engine.updateID(employeesID);
    }

    /**
     *  print (table) information of managers
     * */
    @FXML
    void managers_act(MouseEvent event) {
        engine.updateVals(managersPost);
        engine.updateID(managersID);
    }

    /**
     *  print (table) information of workers
     * */
    @FXML
    void workers_act(MouseEvent event) {
        engine.updateVals(workersPost);
        engine.updateID(workersID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set of list years (for choice)
        yearID.getItems().add("2021");
        yearID.getItems().add("2022");
        yearID.getItems().add("2023");
        yearID.getItems().add("2024");
        yearID.getItems().add("2025");
        yearID.getItems().add("2026");
        yearID.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 14;");
        engine = new Engine(employeesPost,employeesID,workersPost,workersID,managersPost,managersID,totalPost,
                totalID,post,ID,id);
        afterInit();

    }

    private void afterInit() {
    }

    void update(Scene scene){
        Pane pane = PANE;

        double width = scene.getWidth();
        double height = scene.getHeight();
        double WIDTH_CONST  = width/30;

        double coeff = Math.min(width/pane.getPrefWidth(),height/pane.getPrefHeight());

        pane.setScaleX(coeff);
        pane.setScaleY(coeff);


        pane.setTranslateX((pane.getPrefWidth() * coeff - pane.getPrefWidth())/2
                + (width - pane.getPrefWidth() * coeff)/2
        );
        pane.setTranslateY((pane.getPrefHeight() * coeff - pane.getPrefHeight())/2
                + (height - (pane.getPrefHeight())* coeff)/2
        );

    }
    void setData(String Data){
        int index = yearID.getItems().indexOf(Data);
        if(index == -1){
            yearID.getItems().add(Data);
            index = yearID.getItems().size() - 1;
        }
        yearID.getSelectionModel().select(index);

    }
    void setMG(int valMG){
        MG.setText(String.valueOf(valMG));
    }
    void setGPA(int valGPA){
        GPA.setText(String.valueOf(valGPA));
    }
    void setGRC(int valGRC){
        GRC.setText(String.valueOf(valGRC));
    }
    void calc(){
        score.fire();
    }
}