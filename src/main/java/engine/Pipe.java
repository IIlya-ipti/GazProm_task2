package engine;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.List;

public class Pipe implements ImageInterface{
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

    public void off(){
        imageView.setVisible(false);
        imageView.setDisable(true);
    }
    public void on(){
        imageView.setVisible(true);
        imageView.setDisable(false);
    }

    @Override
    public boolean contains(double X, double Y) {
        if(X >= imageView.getBoundsInParent().getMinX() &&
                X <= imageView.getBoundsInParent().getMaxX()){
            if(Y >= imageView.getBoundsInParent().getMinY() &&
                    Y <= imageView.getBoundsInParent().getMaxY()){
                X -= imageView.getLayoutX();
                Y -= imageView.getLayoutY();
                Color color = UtilityFunctions.getPixelColor(imageView,X ,Y);
                System.out.println(color);
                System.out.print(color.getBlue() + " ");
                System.out.print(color.getRed() + " ");
                System.out.print(color.getGreen() + " ");
                System.out.println(color.getOpacity());
                return UtilityFunctions.TrueColor(color);
            }
        }
        return false;
    }

}
