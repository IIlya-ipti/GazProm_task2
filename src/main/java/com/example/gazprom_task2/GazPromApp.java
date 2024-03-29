package com.example.gazprom_task2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class GazPromApp extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        // delete prompt
        stage.setFullScreenExitHint("");

        // do not show in taskbar
        stage.initStyle(StageStyle.UTILITY);

        //
        FXMLLoader fxmlLoader =  new FXMLLoader(MapSlideController.class.getResource("Map_one_one_copy.fxml"));
        FXMLLoader fxmlLoader_ = new FXMLLoader(ObjectController.class.getResource("user_map.fxml"));

        // load fxml and control classes
        Parent page_one = fxmlLoader.load();
        Parent page_two = fxmlLoader_.load();


        MapSlideController controller =    fxmlLoader.getController();
        ObjectController controller1 =   fxmlLoader_.getController();

        // to switch between slides
        controller.setNextScene(page_one,page_two);
        controller1.setNextScene(page_one,page_two);

        // for invisible interactions
        Pane vBox = new Pane(page_two,page_one);

        // set bound for scene (for scale slides)
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(vBox,primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight(),null);

        // disable two slide
        page_two.setVisible(false);
        page_two.setDisable(true);

        stage.setScene(scene);

        // scale slides
        controller.update(scene);
        controller1.update(scene);

        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}