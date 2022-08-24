package engine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.Objects;

/**
 * this entity for work with images
 * */
public enum UserPath {

    CollegeOne(
            "/marks/building.png"
    ),
    CollegeTwo(
            "/marks/College.png"
    ),
    CollegeThree(
            "/marks/college-studying.png"
    ),
    CollegeFour(
            "/marks/college (1).png"
    ),
    CollegeFive(
            "/marks/univeristy.png"
    ),
    ChildTwo(
            "/com/example/gazprom_task2/Child.png"
    ),
    ChildOne(
            "/com/example/gazprom_task2/Child2.png"
    ),
    Circle(
            "/com/example/gazprom_task2/Рисунок1.png"
    ),
    UDGU(
            "/marks/УДГУ.png"
    ),
    GORNUY(
            "/marks/Горный.png"
    ),
    UGORSKYi(
            "/marks/Югорский.png"
    ),
    POLYTECH(
            "/marks/tomskij_politehnicheskij_universitet (1).png"
    ),
    KOGALUM(
            "/marks/Когалымский_политехнический_колледж.png"
    ),
    TUMEN(
            "/marks/Тюменский_индустриальный.png"
    ),
    GUPKIN(
            "/marks/gubkin.png"
    ),
    AGNI(
            "/marks/АГНИ.png"
    ),
    UFIMSKI(
            "/marks/Уфимский_университет.png"
    ),
    UTAK(
            "/marks/УТЭК.png"
    ),
    UHTINSKII(
            "/marks/Ухтинский.png"
    ),
    VOLGOGRAD(
            "/marks/Волгоград.png"
    ),
    URENGOY(
            "/marks/Уренгой.png"
    ),
    GAZ_PROM(
            "/marks/gazprom_logo.png"
    );
    public final String path;
    UserPath(String path){
        this.path = path;
    }

    public InputStream getPathStream() {
        return Objects.requireNonNull(getClass().getResourceAsStream(this.path));
    }
    public ImageView getImageViewNotScale(){
        return new ImageView(new Image(getPathStream()));
    }

    /**
     * return ImageView object from the image pointed to by path
     *
     * width - set fitWidth for imageView
     * height - set fitHeight for imageView
     * */
    static public ImageView getImageViewSetWidthHeight(UserPath userPath,double width, double height, boolean preserveRatio){
        ImageView imageView = userPath.getImageViewNotScale();
        imageView.setPreserveRatio(preserveRatio);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

}