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


        stage.setFullScreenExitHint("");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.UTILITY);




        FXMLLoader fxmlLoader =  new FXMLLoader(FirstController.class.getResource("Map_one_one_copy.fxml"));
        FXMLLoader fxmlLoader_ = new FXMLLoader(HelloController.class.getResource("user_map.fxml"));

        Parent page_one = fxmlLoader.load();
        Parent page_two = fxmlLoader_.load();



        FirstController controller =    fxmlLoader.getController();
        HelloController controller1 =   fxmlLoader_.getController();

        controller.setNextScene(page_one,page_two,controller1);
        controller1.setNextScene(page_one,page_two );
        Pane vBox = new Pane(page_two,page_one);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(vBox,primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight(),null);
        page_two.setVisible(false);
        page_two.setDisable(true);
        stage.setScene(scene);
        controller.update(scene);
        controller1.update(scene);
        stage.show();

        /*ImageView imageView = UserPath.getImageViewSetWidthHeight(
                UserPath.GAZ_PROM,500,500,true
        );
        Group group = new Group(imageView);
        imageView.setTranslateX((primaryScreenBounds.getWidth() - imageView.getFitWidth())/2);
        imageView.setTranslateY((primaryScreenBounds.getHeight() - imageView.getFitHeight())/2);

*/
        /*scene.setRoot(group);*/
        stage.setFullScreen(false);

        scene.setRoot(vBox);
    }

    public static void main(String[] args) {
        launch();
    }
}