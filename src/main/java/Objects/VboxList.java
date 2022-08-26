package Objects;

import ConnectedObjects.College;
import ConnectedObjects.Pipe;
import engine.Styles;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.List;


/**
 * list of CheckBoxWrapper (list of pipes)
 * */
public class VboxList {
    private final VBox vBox;
    public static List<College> collegeList =  null;
    public VboxList(Double spacing){
        this.vBox = new VBox();
        vBox.setSpacing(spacing);
    }
    public void add(String val, Pipe pipe){
        CheckBox checkBox = getCheckBox(val);
        vBox.getChildren().add(checkBox);
        new CheckBoxWrapper(checkBox,pipe);
    }
    public VBox getVBox() {
        return vBox;
    }

    private CheckBox getCheckBox(String val){
        CheckBox checkBox = new CheckBox(val);
        checkBox.getStylesheets().add(String.valueOf(getClass().getResource("/styles.css")));
        checkBox.setFont(Font.font(Styles.fontFamily, FontWeight.BOLD, FontPosture.REGULAR, 9));
        return checkBox;
    }
    public void setLayoutX(double xVal){
        vBox.setLayoutX(xVal);
    }
    public void setLayoutY(double yVal){
        vBox.setLayoutY(yVal);
    }
    public void setTranslateX(double xVal){
        vBox.setTranslateX(xVal);
    }
    public void setTranslateY(double yVal){
        vBox.setTranslateY(yVal);
    }

}


/**
 * entity for linking a pipe and list of pipes
 * */
record CheckBoxWrapper(CheckBox checkBox, Pipe wrapper) {
    static CheckBoxWrapper actualCheckBox = null;

    CheckBoxWrapper(CheckBox checkBox, Pipe wrapper) {
        this.checkBox = checkBox;
        this.wrapper = wrapper;

        checkBox.setOnAction(actionEvent -> {
            if (!this.checkBox.isSelected()) {
                if (wrapper != null) {
                    wrapper.off();
                }
                for(College college : VboxList.collegeList){
                    college.on();
                }
                actualCheckBox = null;
            } else {
                if (actualCheckBox != null && actualCheckBox != this) {
                    actualCheckBox.checkOff();

                }
                for(College college: VboxList.collegeList){
                    college.off();
                }
                if (wrapper != null) {
                    wrapper.on();
                }
                actualCheckBox = this;
            }
        });
    }


    public void checkOff(){
        if(wrapper != null) {
            wrapper.off();
        }
        checkBox.fire();
    }
    public void checkOn(){
        wrapper.on();
    }
    public void off() {
        checkBox.setVisible(false);
        checkBox.setDisable(true);
    }

    public void on() {
        checkBox.setVisible(true);
        checkBox.setDisable(false);
    }
}
