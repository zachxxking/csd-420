/*
 * Zachariah King
 * 7/6/25
 * M7 Programming Assignment
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/*
 * JavaFX application that displays four styled circles using external CSS (modsevenstyle.css).
 */
public class CircleStyleDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Circle 1: white fill, black stroke
        Circle circle1 = new Circle(50); // radius 50
        circle1.getStyleClass().add("plaincircle"); // Apply .plaincircle style

        // Circle 2: white fill, black stroke + dashed stroke
        Circle circle2 = new Circle(50);
        circle2.getStyleClass().addAll("plaincircle", "circleborder"); // Add both styles

        // Circle 3: red fill and stroke
        Circle circle3 = new Circle(50);
        circle3.setId("redcircle"); // Use #redcircle style from CSS

        // Circle 4: green fill and stroke
        Circle circle4 = new Circle(50);
        circle4.setId("greencircle"); // Use #greencircle style from CSS

        // Wrap circles in StackPanes to optionally apply borders
        StackPane pane1 = new StackPane(circle1);
        StackPane pane2 = new StackPane(circle2);
        StackPane pane3 = new StackPane(circle3);
        StackPane pane4 = new StackPane(circle4);

        // Apply border ONLY to the first pane 
        pane1.getStyleClass().add("border");

        // Arrange panes horizontally
        HBox hbox = new HBox(20, pane1, pane2, pane3, pane4); // 20px spacing
        hbox.setStyle("-fx-padding: 20; -fx-alignment: center;"); // Center layout with padding

        // Set up the scene and apply CSS file
        Scene scene = new Scene(hbox, 500, 150);
        scene.getStylesheets().add("modsevenstyle.css");

        // Configure and show the stage
        primaryStage.setTitle("Module 7 Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch the application
    }
}