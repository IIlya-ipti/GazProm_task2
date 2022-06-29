package engine;

import javafx.scene.control.CheckBox;

public class CheckBoxWrapper {
    static CheckBox actualCheckBox= null;
    private final CheckBox checkBox;
    private final Wrapper wrapper;
    private Connect connect;
    public CheckBoxWrapper(CheckBox checkBox, Wrapper wrapper){
        this.checkBox = checkBox;
        this.wrapper = wrapper;

        checkBox.setOnAction(actionEvent -> {
            if(!this.checkBox.isSelected()){
                if(wrapper != null) {
                    wrapper.off();
                }
            }else{
                if(wrapper != null) {
                    wrapper.on();
                }
                if(connect != null){
                    connect.setWrapper(wrapper);
                }
                if(actualCheckBox != null && actualCheckBox != checkBox){
                    actualCheckBox.fire();
                }
                actualCheckBox = checkBox;
            }
        });
    }
    public void setConnect(Connect connect){
        this.connect = connect;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public Wrapper getWrapper() {
        return wrapper;
    }
}
