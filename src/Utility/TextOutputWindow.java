package Utility;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TextOutputWindow {
    public Label outputLabel;
    public TextOutputWindow() {
        Stage primaryStage = new Stage();
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        outputLabel = new Label("");
        HBox root = new HBox();
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        root.getChildren().add(outputLabel);

        primaryStage.setTitle("Text Output");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
