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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;
import javafx.stage.Stage;
import parser.Config;
import parser.Parser;

public class FirstController implements Initializable {
    public Parent first;
    public Parent second; // next slide
    private final List<Pipe> pipes =        new ArrayList<>();
    private final List<College> colleges =  new ArrayList<>();

    public void setNextScene(Parent one, Parent two, HelloController controller){
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
    private ImageView Mark;

    @FXML
    private ImageView Child;

    @FXML
    void ExitAction(ActionEvent event) {
        ((Stage)Adugaia.getScene().getWindow()).close();
        StringBuilder vl = new StringBuilder("");
        for(Pipe pipe : pipes){
            vl.append(pipe.pipeToConfig());
        }
        for(College college:colleges){
            vl.append(college.CollegeToConfig());
        }

        try(FileWriter writer = new FileWriter("config_new.txt", false))
        {
            writer.write(vl.toString());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    private Button Button_One;

    @FXML
    private Button Button_Two;

    @FXML
    private Button Button_Three;

    @FXML
    private Button Button_Four;

    @FXML
    private Button Button_One1;

    @FXML
    private Button Button_Two1;

    @FXML
    private Button Button_Three1;

    @FXML
    private Button Button_Four1;


    @FXML
    private Pane Pane_first;

    @FXML
    private Button shortPlan;

    @FXML
    private Button longPlan;

    private VboxList pipeList;





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
        pane.getChildren().add(pipeList.getVBox());

        // parse a config
        readConfig();
    }

    private void readConfig(){
        Parser parser = new Parser("C:\\Users\\Ilya\\IdeaProjects\\GazProm_task2_\\src\\main\\java\\com\\example\\gazprom_task2\\config.txt");
        List<Config> configs = parser.getConfigs();

        for(Config config : configs){
            if(config.configCollege != null){
                // set colleges
                College college = new College(
                        config.configCollege.path,
                        config.configCollege.width,
                        config.configCollege.height,
                        pane
                );
                college.setLayoutX(config.configCollege.cordX);
                college.setLayoutY(config.configCollege.cordY);
                colleges.add(college);
            }
            Menu menu = null;
            if (config.configTable != null) {
                menu = new Menu(pane);
                menu.setFieldsOfConfig(config.configTable);
            }
            if(config.name != null) {
                Pipe pipe2 = new Pipe(pane);
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
        double coeff = Math.min(width/Pane_first.getPrefWidth(),height/Pane_first.getPrefHeight());
        Pane_first.setScaleX(coeff);
        Pane_first.setScaleY(coeff);

        Pane_first.setTranslateX((Pane_first.getPrefWidth() * coeff - Pane_first.getPrefWidth())/2
             + (width - Pane_first.getPrefWidth() * coeff)/2
        );
        Pane_first.setTranslateY((Pane_first.getPrefHeight() * coeff - Pane_first.getPrefHeight())/2
                + (height - (Pane_first.getPrefHeight() + 28)* coeff)/2
        );
    }
    public Pane getPane(){
        return Pane_first;
    }
}
