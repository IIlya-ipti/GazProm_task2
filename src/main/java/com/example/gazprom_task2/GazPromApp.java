package com.example.gazprom_task2;

import engine.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class GazPromApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // settings
        stage.setFullScreen(true);
        stage.initStyle(StageStyle.UTILITY);
        FXMLLoader fxmlLoader = new FXMLLoader(GazPromApp.class.getResource("Map3.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1000,700);
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        PleaseProvideControllerClassName controllerClassName = fxmlLoader.getController();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, t -> {
            if(t.getCode()== KeyCode.ESCAPE)
            {
                stage.close();
            }
            if(t.getCode() == KeyCode.F11){
                Engine.status = Status.ADMIN;
            }
            if(t.getCode() == KeyCode.F10){
                Engine.status = Status.USER;
            }
            if(t.getCode() == KeyCode.F9){
                Engine.status = Status.CONNECT;
            }
            controllerClassName.update(Engine.status);
        });
        Engine.status = Status.USER;
        stage.show();
        controllerClassName.afterInit();
        controllerClassName.update(Engine.status);

    }

    public static void main(String[] args) {
        launch();
    }
}