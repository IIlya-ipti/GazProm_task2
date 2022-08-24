package com.example.gazprom_task2;

import engine.Engine;
import engine.UtilityFunctions;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
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
    private ImageView Adugaia;

    @FXML
    private ImageView Alanya;

    @FXML
    private ImageView Altay;

    @FXML
    private ImageView Altayskii;

    @FXML
    private ImageView Amurskaya;

    @FXML
    private ImageView Archangelsk;

    @FXML
    private ImageView Astrahanskaya;

    @FXML
    private ImageView Bashkortostan;

    @FXML
    private ImageView Belgorodskaya;

    @FXML
    private ImageView Branskaya;

    @FXML
    private ImageView Buratya;

    @FXML
    private ImageView Cabardin;

    @FXML
    private ImageView Cahalin;

    @FXML
    private ImageView Calushskaya;

    @FXML
    private ImageView Camchatka;

    @FXML
    private ImageView Chechenskaya;

    @FXML
    private ImageView Chelubinsk;

    @FXML
    private ImageView Cherkesia;

    @FXML
    private ImageView Chukotskyy;

    @FXML
    private ImageView Chuvachskaya;

    @FXML
    private ImageView Comi;

    @FXML
    private ImageView Dagestan;

    @FXML
    private ImageView Evreyskii;

    @FXML
    private Button Exit;

    @FXML
    private ImageView Habarovsk;

    @FXML
    private ImageView Hakasya;

    @FXML
    private ImageView Hantu;

    @FXML
    private ImageView Ingushatya;

    @FXML
    private ImageView Irkutsk;

    @FXML
    private ImageView Ivanovskaya;

    @FXML
    private ImageView Kaliningradskaya;

    @FXML
    private ImageView Kalmukuya;

    @FXML
    private ImageView Karelya;

    @FXML
    private ImageView Kemerovskaya;

    @FXML
    private ImageView Kirovskaya;

    @FXML
    private ImageView Kostromskaya;

    @FXML
    private ImageView Krasnodarskyy;

    @FXML
    private ImageView Krasnor_kray;

    @FXML
    private ImageView Krum;

    @FXML
    private ImageView Kurganskaya;

    @FXML
    private ImageView Kurskaya;

    @FXML
    private ImageView Leningradskaya;

    @FXML
    private ImageView Lipetsk;

    @FXML
    private ImageView Magadan;

    @FXML
    private ImageView MariiAl;

    @FXML
    private ImageView Mordoviya;

    @FXML
    private ImageView Moscow;

    @FXML
    private ImageView Moskovskaya_obl;

    @FXML
    private ImageView Murmansk;

    @FXML
    private ImageView Nenetskyy;

    @FXML
    private ImageView NishiyGorod;

    @FXML
    private ImageView Novgorod;

    @FXML
    private ImageView Novosibirsk;

    @FXML
    private ImageView Omskaya;

    @FXML
    private ImageView Orenburg;

    @FXML
    private ImageView Orlovskaya;

    @FXML
    private ImageView Pensenskaya;

    @FXML
    private ImageView Permskii;

    @FXML
    private ImageView Primorsky;

    @FXML
    private ImageView Pscov;

    @FXML
    private ImageView Razanskaya;

    @FXML
    private ImageView Rostov;

    @FXML
    private ImageView Sainkt_petersburg;

    @FXML
    private ImageView Samarskaya;

    @FXML
    private ImageView Saratovskaya;

    @FXML
    private ImageView Smolenskaya;

    @FXML
    private ImageView Stavropol;

    @FXML
    private ImageView Sverdlovskaya;

    @FXML
    private ImageView Tambov;

    @FXML
    private ImageView Tatarstan;

    @FXML
    private TextFlow TextField;

    @FXML
    private ImageView Tomskaya;

    @FXML
    private ImageView Tulskaya;

    @FXML
    private ImageView Tumenskaya;

    @FXML
    private ImageView Tuva;

    @FXML
    private ImageView Tverskaya;

    @FXML
    private ImageView Udmurtinskaya;

    @FXML
    private ImageView Vladimiskaya;

    @FXML
    private ImageView Volgogradskaya;

    @FXML
    private ImageView Vologorodskaya;

    @FXML
    private ImageView Voroneg;

    @FXML
    private ImageView Yakutia;

    @FXML
    private ImageView Yamal_Nenezkiy;

    @FXML
    private ImageView Yaroslavskaya;

    @FXML
    private ImageView Ylyanovsk;

    @FXML
    private ImageView Zabaykal;

    @FXML
    private Pane pane;

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

    @FXML
    void _500_action_first(ActionEvent event) {
        MG.setText(String.valueOf(Math.max(Double.parseDouble(MG.getText()) - 500, 0)));
    }

    @FXML
    void ___500_action_first(ActionEvent event) {
        MG.setText(String.valueOf(Double.parseDouble(MG.getText()) + 500));
    }

    @FXML
    void _1_action_second(ActionEvent event) {
        GPA.setText(String.valueOf(Math.max(Integer.parseInt(GPA.getText()) - 1, 0)));
    }

    @FXML
    void __1_action_second(ActionEvent event) {
        GPA.setText(String.valueOf(Integer.parseInt(GPA.getText()) + 1));

    }

    @FXML
    void _1_action_third(ActionEvent event) {
        GRC.setText(String.valueOf(Math.max(Integer.parseInt(GRC.getText()) - 1, 0)));
    }

    @FXML
    void __1_action_third(ActionEvent event) {
        GRC.setText(String.valueOf(Integer.parseInt(GRC.getText()) + 1));
    }

    @FXML
    void returnAct(ActionEvent event) {
        first.setVisible(true);
        first.setDisable(false);
        second.setVisible(false);
        second.setDisable(true);
    }

    @FXML
    void calculationOn(ActionEvent event) {
        engine.update(yearID.getValue(),
                Double.parseDouble(MG.getText()),
                Integer.parseInt(GPA.getText()),
                Integer.parseInt(GRC.getText()),
                true);
    }

    @FXML
    void employers_act(MouseEvent event) {
        engine.updateVals(employeesPost);
        engine.updateID(employeesID);
    }

    @FXML
    void managers_act(MouseEvent event) {
        engine.updateVals(managersPost);
        engine.updateID(managersID);
    }

    @FXML
    void workers_act(MouseEvent event) {
        engine.updateVals(workersPost);
        engine.updateID(workersID);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //AAA.setContent(post);
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