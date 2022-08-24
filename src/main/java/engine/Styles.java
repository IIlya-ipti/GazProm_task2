package engine;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * styles of all objects
 * */
public class Styles {
    public static String styleHBox = "-fx-background-color: white;" +
            "-fx-effect: dropshadow(gaussian, #788b98, " + 20 + ", 0, 0, 0);" +
            "-fx-background-insets: " + 5 + ";" +
            "-fx-background-radius: " + 20;
    public static String styleMarker = "-fx-effect: dropshadow(gaussian, #788b98, \" + 20 + \", 0, 0, 0);";
    public static String fontFamily = "calibri";
    public static Color fieldUserColor = Color.valueOf("#006699");
    public static Font fontUser = Font.font(fontFamily,8);
}
