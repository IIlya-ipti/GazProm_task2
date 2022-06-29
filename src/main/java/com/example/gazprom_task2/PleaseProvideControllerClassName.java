package com.example.gazprom_task2;

import engine.*;

import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class PleaseProvideControllerClassName implements Initializable {
    private Engine engine;
    private List<UserPath> userPathList;
    private int indexChild;

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
    private ImageView Sainkt_petersburg;

    @FXML
    private Pane pane;

    @FXML
    private Button Exit;

    @FXML
    void ExitAction(ActionEvent event) {
        ((Stage)pane.getScene().getWindow()).close();

    }

    @FXML
    private Text text;

    @FXML
    private Pane Total;

    @FXML
    private ImageView Pipe;

    @FXML
    private HBox hbox;

    @FXML
    private Pane pane_left;

    @FXML
    private VBox Vbox_list;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        engine = new Engine(List.of(
                        new Pipe(Pipe)
                ),
                List.of(
                        new Pair<>(new Marker(UserPath.CollegeOne,pane,250,250),0),
                        new Pair<>(new Marker(UserPath.CollegeTwo,pane,350,350),0),
                        new Pair<>(new Marker(UserPath.CollegeThree,pane,350,220),0)
                ),
                pane
                );

        //Marker marker = new Marker(UserPath.CollegeOne,pane,100,100);
        //engine.getWrapperList().get(0).connect(marker);
        Vbox_list.getChildren().addAll(
                getCheckBox("Сила Сибири"),
                getCheckBox("Сила Сибири2"),
                getCheckBox("Сила Сибири3")
                );
        Vbox_list.setSpacing(30);

    }
    public void update(boolean admin){
        if(!admin) {
            hbox.setVisible(false);
            hbox.setDisable(true);
            text.setVisible(true);
        }else{
            hbox.setVisible(true);
            hbox.setDisable(false);
            text.setVisible(false);
            engine.activeOff();
        }
    }
    public void afterInit(){

        Scene scene = pane.getScene();

        double width = scene.getWidth();
        double height = scene.getHeight();
        double WIDTH_CONST_AMENDMENT = 20;
        double WIDTH_CONST  = width/30;         //  width of one HBox object



        // init HBox (list of images with spacing)
        this.userPathList = List.of(
                UserPath.CollegeOne,
                UserPath.CollegeOne,
                UserPath.CollegeOne,
                UserPath.CollegeTwo,
                UserPath.CollegeTwo,
                UserPath.CollegeThree,
                UserPath.CollegeThree,
                UserPath.CollegeThree,
                UserPath.CollegeFour,
                UserPath.CollegeFive,
                UserPath.CollegeFive
        );
        HBoxClass hBoxClass = new HBoxClass(hbox,userPathList,WIDTH_CONST);
        hBoxClass.setMouseEvent();
        hBoxClass.off();

        text.setVisible(true);



        //amendment
        width -= WIDTH_CONST_AMENDMENT;

        double coeff = Math.min(width/pane.getWidth(),height/pane.getHeight());
        pane.setScaleX(coeff);
        pane.setScaleY(coeff);
        if(pane.getBoundsInParent().getMinX() != 0) {
            pane.setLayoutX((int) (-1 * pane.getBoundsInParent().getMinX()));
            pane.setLayoutY(-pane.getBoundsInParent().getMinY());
        }
        engine.update();


        pane.setOnMouseClicked(mouseEvent -> {
            Marker marker = new Marker(Marker.actualUserPath,pane,mouseEvent.getX(),mouseEvent.getY());
            engine.getWrapperList().get(0).connect(marker);

        });


        // correct pane with map
        pane.setLayoutY(pane.getLayoutY() + 30);



    }

    private Node getCheckBox(String val){
        CheckBox checkBox = new CheckBox(val);
        checkBox.getStylesheets().add(String.valueOf(getClass().getResource("/styles.css")));
        checkBox.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        return checkBox;
    }



}
