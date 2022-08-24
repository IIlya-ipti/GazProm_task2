package com.example.gazprom_task2;

import engine.Engine;
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
    private Engine engine;
    public Parent first;
    public Parent second;

    public void setNextScene(Parent one, Parent two){
        this.first = one;
        this.second = two;
    }

    @FXML
    private Button Exit;

    /**
     * list of years (for choice)
     * */
    @FXML
    private ComboBox<String> yearID;

    /**
     * prime parent node with exit button
     * */
    @FXML
    private Pane paneWith;

    /**
     * prime parent node without exit button
     * */
    @FXML
    private Pane paneNoWith;

    @FXML
    private TextField MG;

    @FXML
    private TextField GPA;

    @FXML
    private TextField GRC;

    @FXML
    private Text employeesID;

    @FXML
    private Text employeesPost;

    @FXML
    private Text managersPost;

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

    /**
     * professional standard code table
     * */
    @FXML
    private VBox ID;

    /**
     * List of positions or professions
     * */
    @FXML
    private VBox post;

    /**
     * table information
     * */
    @FXML
    private Pane info;

    /**
     * button for calculate
     * */
    @FXML
    private Button score;


    /**
     * press exit button
     * */
    @FXML
    public void ExitAction(ActionEvent actionEvent) {
        ((Stage)Exit.getScene().getWindow()).close();
    }

    /**
     * -500 MG
     * */
    @FXML
    private void _500_action_first(ActionEvent event) {
        MG.setText(String.valueOf(Math.max(Double.parseDouble(MG.getText()) - 500, 0)));
    }

    /**
     * +500 NG
     * */
    @FXML
    private void ___500_action_first(ActionEvent event) {
        MG.setText(String.valueOf(Double.parseDouble(MG.getText()) + 500));
    }

    /**
     * -1 GRA
     * */
    @FXML
    private void _1_action_second(ActionEvent event) {
        GPA.setText(String.valueOf(Math.max(Integer.parseInt(GPA.getText()) - 1, 0)));
    }

    /**
     * +1 GRA
     * */
    @FXML
    private void __1_action_second(ActionEvent event) {
        GPA.setText(String.valueOf(Integer.parseInt(GPA.getText()) + 1));

    }

    /**
     * -1 GRC
     * */
    @FXML
    private void _1_action_third(ActionEvent event) {
        GRC.setText(String.valueOf(Math.max(Integer.parseInt(GRC.getText()) - 1, 0)));
    }

    /**
     * +1 GRC
     * */
    @FXML
    private void __1_action_third(ActionEvent event) {
        GRC.setText(String.valueOf(Integer.parseInt(GRC.getText()) + 1));
    }


    /**
     * come back
     * */
    @FXML
    private void returnAct(ActionEvent event) {
        first.setVisible(true);
        first.setDisable(false);
        second.setVisible(false);
        second.setDisable(true);
    }

    /**
     * calculate table
     * */
    @FXML
    private void calculationOn(ActionEvent event) {
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
    private void employers_act(MouseEvent event) {
        engine.updateVals(employeesPost);
        engine.updateID(employeesID);
    }

    /**
     *  print (table) information of managers
     * */
    @FXML
    private void managers_act(MouseEvent event) {
        engine.updateVals(managersPost);
        engine.updateID(managersID);
    }

    /**
     *  print (table) information of workers
     * */
    @FXML
    private void workers_act(MouseEvent event) {
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
                totalID,post,ID);
        afterInit();

    }

    private void afterInit() {
    }

    /**
     * update map node after set new bounds (scene bounds)
     * */
    public void update(Scene scene){
        Pane pane = paneNoWith;

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

    public void setYear(String Data){
        int index = yearID.getItems().indexOf(Data);
        if(index == -1){
            yearID.getItems().add(Data);
            index = yearID.getItems().size() - 1;
        }
        yearID.getSelectionModel().select(index);

    }
    public void setMG(int valMG){
        MG.setText(String.valueOf(valMG));
    }
    public void setGPA(int valGPA){
        GPA.setText(String.valueOf(valGPA));
    }
    public void setGRC(int valGRC){
        GRC.setText(String.valueOf(valGRC));
    }
    public void calc(){
        score.fire();
    }
}