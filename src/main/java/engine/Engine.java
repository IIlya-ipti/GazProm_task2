package engine;


import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum EngineID{
    WORKERS,
    EMPLOYERS,
    MANAGERS
}
/**
 * This engine class fot second slide
 * */
public final class Engine {
    private final Text employees_post;
    private final Text employees_id;
    private final Text workers_post;
    private final Text workers_id;
    private final Text managers_post;
    private final Text managers_id;
    private final Text total_post;
    private final Text total_id;
    private final VBox post;
    private final VBox ID;
    private final List<Pair<String,String>> workers_post_id_list = new ArrayList<>();
    private final List<Pair<String,String>> managers_post_id_list = new ArrayList<>();
    private final List<Pair<String,String>> employees_post_id_list = new ArrayList<>();
    private double km = 1000;

    public Engine(Text employees_post, Text employees_id,
                  Text workers_post, Text workers_id,
                  Text managers_post, Text managers_id,
                  Text total_post, Text total_id, VBox post, VBox ID) {
        this.employees_post = employees_post;
        this.employees_id = employees_id;
        this.workers_post = workers_post;
        this.workers_id = workers_id;
        this.managers_post = managers_post;
        this.managers_id = managers_id;
        this.total_post = total_post;
        this.total_id = total_id;
        this.post = post;
        this.ID = ID;

    }

    public void update(String year, double MG, int GPA, int GRC, boolean smallPeopleTech) {
        int employeesPost;
        int employeesId;
        int workersPost;
        int workersId;
        int managersPost;
        int managersId;

        employeesPost = (int) (MG / 2 + GPA * 5 + GRC);
        employeesId = 10 + GPA;

        workersPost = (int) (MG / 2 + GPA * 3 + GRC);
        workersId = 10 + GPA;

        managersPost = (int) (MG / 2 + GPA * 9 + GRC);
        managersId = 10 + GPA;

        employees_post.setText(String.valueOf(employeesPost));
        employees_id.setText(String.valueOf(employeesId));

        workers_post.setText(String.valueOf(workersPost));
        workers_id.setText(String.valueOf(workersId));

        managers_post.setText(String.valueOf(managersPost));
        managers_id.setText(String.valueOf(managersId));

        total_id.setText(String.valueOf(managersId + workersId + employeesId));
        total_post.setText(String.valueOf(managersPost + workersPost + employeesPost));

        updateVals(workers_post);
        km = MG;

    }

    /**
     * update table (list of positions and professions and professional standard code)
     * */
    public void updateVals(Text total){
        double maxY = post.getBoundsInParent().getMaxY();
        post.getChildren().clear();

        Text text = new Text("first:" +(int)( Double.parseDouble(total.getText()) * 0.6));
        post.getChildren().add(text);
        text = new Text("second:" + (int)(Double.parseDouble(total.getText())*0.2));
        post.getChildren().add(text);
        text = new Text("third:" + (int)(Double.parseDouble(total.getText())*0.2));
        post.getChildren().add(text);
        text = new Text("four:" + (int)(Double.parseDouble(total.getText()) - (int)( Double.parseDouble(total.getText()) * 0.6)
                - (int)(Double.parseDouble(total.getText())*0.2)-
                (int)(Double.parseDouble(total.getText())*0.2)));
        post.getChildren().add(text);
    }

    public void updateID(Text total){
        ID.getChildren().clear();
        ID.getChildren().add(new Text("#rerw_43"));
        ID.getChildren().add(new Text("#rerw_43_43"));
    }


    public Text employees_post() {
        return employees_post;
    }

    public Text employees_id() {
        return employees_id;
    }

    public Text workers_post() {
        return workers_post;
    }

    public Text workers_id() {
        return workers_id;
    }

    public Text managers_post() {
        return managers_post;
    }

    public Text managers_id() {
        return managers_id;
    }

    public Text total_post() {
        return total_post;
    }

    public Text total_id() {
        return total_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Engine) obj;
        return Objects.equals(this.employees_post, that.employees_post) &&
                Objects.equals(this.employees_id, that.employees_id) &&
                Objects.equals(this.workers_post, that.workers_post) &&
                Objects.equals(this.workers_id, that.workers_id) &&
                Objects.equals(this.managers_post, that.managers_post) &&
                Objects.equals(this.managers_id, that.managers_id) &&
                Objects.equals(this.total_post, that.total_post) &&
                Objects.equals(this.total_id, that.total_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees_post, employees_id, workers_post, workers_id, managers_post, managers_id, total_post, total_id);
    }

    @Override
    public String toString() {
        return "Engine[" +
                "employees_post=" + employees_post + ", " +
                "employees_id=" + employees_id + ", " +
                "workers_post=" + workers_post + ", " +
                "workers_id=" + workers_id + ", " +
                "managers_post=" + managers_post + ", " +
                "managers_id=" + managers_id + ", " +
                "total_post=" + total_post + ", " +
                "total_id=" + total_id + ']';
    }


}
