package engine;

import javafx.scene.image.ImageView;

import java.util.List;

public class Pipe {
    private ImageView imageView;
    private int index;
    public Pipe(ImageView imageView){
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
